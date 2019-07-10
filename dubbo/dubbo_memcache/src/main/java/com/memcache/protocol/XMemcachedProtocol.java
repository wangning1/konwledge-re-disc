package com.memcache.protocol;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.RpcResult;
import com.alibaba.dubbo.rpc.protocol.AbstractInvoker;
import com.alibaba.dubbo.rpc.protocol.memcached.MemcachedProtocol;
import com.google.code.yanf4j.core.Session;
import net.rubyeye.xmemcached.CommandFactory;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.MemcachedSessionLocator;
import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.transcoders.WhalinTranscoder;
import net.rubyeye.xmemcached.utils.AddrUtil;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by wangning on 2017/7/7.
 */
public class XMemcachedProtocol extends MemcachedProtocol {


    /**
     *
     */
    public XMemcachedProtocol() {
        super();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.alibaba.dubbo.rpc.protocol.memcached.MemcachedProtocol#refer(java
     * .lang.Class, com.alibaba.dubbo.common.URL)
     */
    @Override
    public <T> Invoker<T> refer(final Class<T> type, final URL url) throws RpcException {
        try {
            String address = url.getAddress();
            String backup = url.getParameter(Constants.BACKUP_KEY);
            logger.info("---refer---url:" + url + " [url:address]:" + address + " [url:backup]:" + backup);
            if (backup != null && backup.length() > 0) {
                address += "," + backup;
            }
            String charset = url.getParameter("charset", "UTF-8");
            boolean primitiveAsString = url.getParameter("primitiveAsString", false);
            int compressionThreshold = url.getParameter("compressionThreshold", 15360);
            boolean packZeros = url.getParameter("packZeros", false);
            WhalinTranscoder transcoder = new WhalinTranscoder();
            transcoder.setCharset(charset);
            transcoder.setPrimitiveAsString(primitiveAsString);
            transcoder.setCompressionThreshold(compressionThreshold);
            transcoder.setPackZeros(packZeros);

            int connectionPoolSize = url.getParameter("connectionPoolSize", 1);
            String commandFactory = url.getParameter("commandFactory",
                    "net.rubyeye.xmemcached.command.TextCommandFactory");
            String sessionLocator = url.getParameter("sessionLocator",
                    "net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator");

            logger.info("---[refer]:connectionPoolSize "+ connectionPoolSize + "[refer]:commandFactory "+commandFactory + " [:sessionLocator] " + sessionLocator );
            MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(address));
            builder.setTranscoder(transcoder);
            builder.setConnectionPoolSize(connectionPoolSize);
            Class<?> commandFactoryClazz = Class.forName(commandFactory);
            if (commandFactoryClazz == null) {
                commandFactoryClazz = Class.forName("net.rubyeye.xmemcached.command.TextCommandFactory");
            }
            builder.setCommandFactory((CommandFactory) commandFactoryClazz.newInstance());
            Class<?> sessionLocatorClazz = Class.forName(sessionLocator);
            if (sessionLocatorClazz == null) {
                sessionLocatorClazz = Class.forName("net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator");
            }
            builder.setSessionLocator((MemcachedSessionLocator) sessionLocatorClazz.newInstance());

            final MemcachedClient memcachedClient = builder.build();
            long opTimeout = url.getParameter("opTimeout", 1000);
            memcachedClient.setOpTimeout(opTimeout);

            return new XMemcachedInvoker<T>(type, url, memcachedClient);

        } catch (Throwable t) {
            throw new RpcException("Failed to refer memecached service. interface: " + type.getName() + ", url: " + url
                    + ", cause: " + t.getMessage(), t);
        }
    }

    public class XMemcachedInvoker<T> extends AbstractInvoker<T> {

        private Integer expiry = null;
        private String get = null;
        private String set = null;
        private String delete = null;
        private String add = null;
        private String incr = null;
        private String decr = null;
        private MemcachedClient memcachedClient = null;

        public XMemcachedInvoker(Class<T> type, URL url, MemcachedClient memcachedClient) {
            super(type, url);

            expiry = url.getParameter("expiry", 0);
            get = url.getParameter("get", "get");
            set = url.getParameter("set", Map.class.equals(type) ? "put" : "set");
            delete = url.getParameter("delete", Map.class.equals(type) ? "remove" : "delete");
            add = url.getParameter("add", "add");
            incr = url.getParameter("incr", "incr");
            decr = url.getParameter("decr", "decr");
            this.memcachedClient = memcachedClient;
        }

        /*
         * (non-Javadoc)
         *
         * @see com.alibaba.dubbo.rpc.protocol.AbstractInvoker#destroy()
         */
        @Override
        public void destroy() {
            super.destroy();
            if (!memcachedClient.isShutdown()) {
                try {
                    memcachedClient.shutdown();
                    logger.info("shutown the xmemcached client.");
                } catch (Throwable e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

        @Override
        protected Result doInvoke(Invocation invocation) throws Throwable {
            logger.info("--doInvoke--");
            Class<T> type = getInterface();
            URL url = getUrl();
            try {
                if (get.equals(invocation.getMethodName())) {
                    if (invocation.getArguments().length == 1) {
                        return new RpcResult(get(String.valueOf(invocation.getArguments()[0])));
                    } else {
                        throw new IllegalArgumentException(
                                "The memcached get method arguments mismatch, must only one arguments. interface: "
                                        + type.getName() + ", method: " + invocation.getMethodName() + ", url: " + url);
                    }
                } else if (set.equals(invocation.getMethodName())) {
                    if (invocation.getArguments().length == 2) {
                        return new RpcResult(set(String.valueOf(invocation.getArguments()[0]),
                                invocation.getArguments()[1], expiry));
                    } else if (invocation.getArguments().length == 3) {
                        return new RpcResult(set(String.valueOf(invocation.getArguments()[0]),
                                invocation.getArguments()[1], (Integer) invocation.getArguments()[2]));
                    } else {
                        throw new IllegalArgumentException(
                                "The memcached set method arguments mismatch, must be two arguments. interface: "
                                        + type.getName() + ", method: " + invocation.getMethodName() + ", url: " + url);
                    }
                } else if (delete.equals(invocation.getMethodName())) {
                    if (invocation.getArguments().length == 1) {
                        return new RpcResult(delete(String.valueOf(invocation.getArguments()[0])));
                    } else {
                        throw new IllegalArgumentException(
                                "The memcached delete method arguments mismatch, must only one arguments. interface: "
                                        + type.getName() + ", method: " + invocation.getMethodName() + ", url: " + url);
                    }
                } else if (add.equals(invocation.getMethodName())) {
                    if (invocation.getArguments().length == 2) {
                        return new RpcResult(add(String.valueOf(invocation.getArguments()[0]),
                                invocation.getArguments()[1], expiry));
                    } else if (invocation.getArguments().length == 3) {
                        return new RpcResult(add(String.valueOf(invocation.getArguments()[0]),
                                invocation.getArguments()[1], (Integer) invocation.getArguments()[2]));
                    } else {
                        throw new IllegalArgumentException(
                                "The memcached add method arguments mismatch, must only one arguments. interface: "
                                        + type.getName() + ", method: " + invocation.getMethodName() + ", url: " + url);
                    }
                } else if (incr.equals(invocation.getMethodName())) {
                    if (invocation.getArguments().length == 2) {
                        return new RpcResult(incr(String.valueOf(invocation.getArguments()[0]),
                                (Long) invocation.getArguments()[1]));
                    } else if (invocation.getArguments().length == 3) {
                        return new RpcResult(incr(String.valueOf(invocation.getArguments()[0]),
                                (Long) invocation.getArguments()[1], (Long) invocation.getArguments()[2]));
                    } else if (invocation.getArguments().length == 4) {
                        return new RpcResult(incr(String.valueOf(invocation.getArguments()[0]),
                                (Long) invocation.getArguments()[1], (Long) invocation.getArguments()[2],
                                (Integer) invocation.getArguments()[3]));
                    } else {
                        throw new IllegalArgumentException(
                                "The memcached incr method arguments mismatch, must only one arguments. interface: "
                                        + type.getName() + ", method: " + invocation.getMethodName() + ", url: " + url);
                    }
                } else if (decr.equals(invocation.getMethodName())) {
                    if (invocation.getArguments().length == 2) {
                        return new RpcResult(decr(String.valueOf(invocation.getArguments()[0]),
                                (Long) invocation.getArguments()[1]));
                    } else if (invocation.getArguments().length == 3) {
                        return new RpcResult(decr(String.valueOf(invocation.getArguments()[0]),
                                (Long) invocation.getArguments()[1], (Long) invocation.getArguments()[2]));
                    } else if (invocation.getArguments().length == 4) {
                        return new RpcResult(decr(String.valueOf(invocation.getArguments()[0]),
                                (Long) invocation.getArguments()[1], (Long) invocation.getArguments()[2],
                                (Integer) invocation.getArguments()[3]));
                    } else {
                        throw new IllegalArgumentException(
                                "The memcached decr method arguments mismatch, must only one arguments. interface: "
                                        + type.getName() + ", method: " + invocation.getMethodName() + ", url: " + url);
                    }
                } else {
                    throw new UnsupportedOperationException("Unsupported method " + invocation.getMethodName()
                            + " in memcached service.");
                }
            } catch (Throwable t) {
                RpcException re = new RpcException("Failed to invoke memecached service method. interface: "
                        + type.getName() + ", method: " + invocation.getMethodName() + ", url: " + url + ", cause: "
                        + t.getMessage(), t);
                if (t instanceof TimeoutException || t instanceof SocketTimeoutException) {
                    re.setCode(RpcException.TIMEOUT_EXCEPTION);
                } else if (t instanceof MemcachedException || t instanceof IOException) {
                    re.setCode(RpcException.NETWORK_EXCEPTION);
                }
                throw re;
            }
        }

        public Object get(String key) {

            Object o = null;
            try {
                o = memcachedClient.get(key);
                logger.debug("get key=" + key);
            } catch (Exception e) {
                XMemcachedClient xmemcachedClient = (XMemcachedClient) this.memcachedClient;
                Session selectSession = xmemcachedClient.getSessionLocator().getSessionByKey(key);
                if (selectSession != null) {
                    logger.error(
                            String.format("Memcached error, server:%s, key:%s", new Object[] {
                                    selectSession.getRemoteSocketAddress().toString(), key }), e);
                } else {
                    logger.error("Memcached error", e);
                }
            }
            return o;
        }

        public boolean set(String key, Object value, int expires) {
            boolean result = false;
            try {
                result = memcachedClient.set(key, expires, value);
                logger.debug("add key=" + key);
            } catch (Exception e) {
                XMemcachedClient xmemcachedClient = (XMemcachedClient) this.memcachedClient;
                Session selectSession = xmemcachedClient.getSessionLocator().getSessionByKey(key);
                if (selectSession != null) {
                    logger.error(
                            String.format("Memcached error, server:%s, key:%s", new Object[] {
                                    selectSession.getRemoteSocketAddress().toString(), key }), e);
                } else {
                    logger.error("Memcached error", e);
                }
            }
            return result;
        }

        public boolean delete(String key) {
            boolean result = false;
            try {
                result = memcachedClient.delete(key);
                logger.debug("remove key=" + key);
            } catch (Exception e) {
                XMemcachedClient xmemcachedClient = (XMemcachedClient) this.memcachedClient;
                Session selectSession = xmemcachedClient.getSessionLocator().getSessionByKey(key);
                if (selectSession != null) {
                    logger.error(
                            String.format("Memcached error, server:%s, key:%s", new Object[] {
                                    selectSession.getRemoteSocketAddress().toString(), key }), e);
                } else {
                    logger.error("Memcached error", e);
                }
            }
            return result;
        }

        public boolean add(String key, Object value, int expire) {
            boolean result = false;
            try {
                result = memcachedClient.add(key, expire, value);
            } catch (Exception e) {
                XMemcachedClient xmemcachedClient = (XMemcachedClient) this.memcachedClient;
                Session selectSession = xmemcachedClient.getSessionLocator().getSessionByKey(key);
                if (selectSession != null) {
                    logger.error(
                            String.format("Memcached error, server:%s, key:%s", new Object[] {
                                    selectSession.getRemoteSocketAddress().toString(), key }), e);
                } else {
                    logger.error("Memcached error", e);
                }
            }
            return result;
        }

        public long incr(String key, long delta) {
            long result = -1;
            try {
                result = memcachedClient.incr(key, delta);
            } catch (Exception e) {
                XMemcachedClient xmemcachedClient = (XMemcachedClient) this.memcachedClient;
                Session selectSession = xmemcachedClient.getSessionLocator().getSessionByKey(key);
                if (selectSession != null) {
                    logger.error(
                            String.format("Memcached error, server:%s, key:%s", new Object[] {
                                    selectSession.getRemoteSocketAddress().toString(), key }), e);
                } else {
                    logger.error("Memcached error", e);
                }
            }
            return result;
        }

        public long incr(String key, long delta, long init) {
            long result = -1;
            try {
                result = memcachedClient.incr(key, delta, init);
            } catch (Exception e) {
                XMemcachedClient xmemcachedClient = (XMemcachedClient) this.memcachedClient;
                Session selectSession = xmemcachedClient.getSessionLocator().getSessionByKey(key);
                if (selectSession != null) {
                    logger.error(
                            String.format("Memcached error, server:%s, key:%s", new Object[] {
                                    selectSession.getRemoteSocketAddress().toString(), key }), e);
                } else {
                    logger.error("Memcached error", e);
                }
            }
            return result;
        }

        public long incr(String key, long delta, long init, int expiry) {
            long result = -1;
            try {
                result = memcachedClient.incr(key, delta, init, memcachedClient.getOpTimeout(), expiry);
            } catch (Exception e) {
                XMemcachedClient xmemcachedClient = (XMemcachedClient) this.memcachedClient;
                Session selectSession = xmemcachedClient.getSessionLocator().getSessionByKey(key);
                if (selectSession != null) {
                    logger.error(
                            String.format("Memcached error, server:%s, key:%s", new Object[] {
                                    selectSession.getRemoteSocketAddress().toString(), key }), e);
                } else {
                    logger.error("Memcached error", e);
                }
            }
            return result;
        }

        public long decr(String key, long delta) {
            long result = -1;
            try {
                result = memcachedClient.decr(key, delta);
            } catch (Exception e) {
                XMemcachedClient xmemcachedClient = (XMemcachedClient) this.memcachedClient;
                Session selectSession = xmemcachedClient.getSessionLocator().getSessionByKey(key);
                if (selectSession != null) {
                    logger.error(
                            String.format("Memcached error, server:%s, key:%s", new Object[] {
                                    selectSession.getRemoteSocketAddress().toString(), key }), e);
                } else {
                    logger.error("Memcached error", e);
                }
            }
            return result;
        }

        public long decr(String key, long delta, long init) {
            long result = -1;
            try {
                result = memcachedClient.decr(key, delta, init);
            } catch (Exception e) {
                XMemcachedClient xmemcachedClient = (XMemcachedClient) this.memcachedClient;
                Session selectSession = xmemcachedClient.getSessionLocator().getSessionByKey(key);
                if (selectSession != null) {
                    logger.error(
                            String.format("Memcached error, server:%s, key:%s", new Object[] {
                                    selectSession.getRemoteSocketAddress().toString(), key }), e);
                } else {
                    logger.error("Memcached error", e);
                }
            }
            return result;
        }

        public long decr(String key, long delta, long init, int expiry) {
            long result = -1;
            try {
                result = memcachedClient.decr(key, delta, init, memcachedClient.getOpTimeout(), expiry);
            } catch (Exception e) {
                XMemcachedClient xmemcachedClient = (XMemcachedClient) this.memcachedClient;
                Session selectSession = xmemcachedClient.getSessionLocator().getSessionByKey(key);
                if (selectSession != null) {
                    logger.error(
                            String.format("Memcached error, server:%s, key:%s", new Object[] {
                                    selectSession.getRemoteSocketAddress().toString(), key }), e);
                } else {
                    logger.error("Memcached error", e);
                }
            }
            return result;
        }
    }
}
