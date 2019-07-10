package com.wn.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;

/**
 * Created by wangning on 2017/3/27.
 * 一、通道（channel）：用于源节点和目标节点的连接。在Java NIO 中负责缓冲区中数据的传输。channel本身不存储数据，因此需要配合缓冲区进行传输。
 * <p>
 * 二、通道的主要实现类
 * java.nio.channels.channel接口
 * |--FileChannel
 * |--SocketChannel
 * |--ServerSocketChannel
 * |--DatagramChannel
 * <p>
 * 三、获取通道
 * 1.java 针对支持通道的类提供了 getChannel() 方法
 * 本地 IO：
 * FileInputStream/FileOutputStream
 * RandomAccessStream
 * <p>
 * 网络IO
 * Socket
 * ServerSocket
 * DatagramSocket
 * 2. 在jdk 1.7 中NIO2 针对各个通道提供了静态方法open()
 * 3. 在jdk 1.7 中NIO2 的 Files 工具类的 newByteChannel()
 * <p>
 * 四、通道间的传输
 * transferFrom()
 * transferTo()
 * <p>
 * 五、分散（Scatter）和聚集（Gather）
 * 分散读取（Scatter Reads）:将通道中的数据分散到多个缓冲区中
 * 聚集写入（Gather Writes）:将多个缓冲区中的数据聚集到通道中
 * <p>
 * 六、字符集：Charset
 * 编码：字符串 --> 字符数组
 * 解码：字符数组 --> 字符串
 */
public class TestChannel {

    public static void main(String[] args) throws IOException {
//        test1();
//        test2();
//        test3();

//        test4();
//        test5();

        test6();
    }


    //通过字符集进行编码和解码
    public static void test6() throws CharacterCodingException {
        Charset charset = Charset.forName("GBK");
        //声明编码器
        CharsetEncoder cse = charset.newEncoder();
        //声明解码器
        CharsetDecoder csd = charset.newDecoder();

        CharBuffer cbf = CharBuffer.allocate(1024);
        cbf.put("中华人民共和国！");
        cbf.flip();

        System.out.println(cbf.position());


        ByteBuffer bbf = cse.encode(cbf);

        for (int i = 0; i < bbf.limit(); i++) {
            System.out.print(bbf.get());
            //注意get()和get(i)区别
        }
        System.out.println();

        bbf.flip();
        System.out.println(bbf.position());

       CharBuffer cbf2 = csd.decode(bbf);
        System.out.println(cbf2);



    }


    //获取字符集
    public static void test5() {
        Map<String, Charset> charsetMap = Charset.availableCharsets();

        Set<Map.Entry<String, Charset>> entrySet = charsetMap.entrySet();
        for (Map.Entry<String, Charset> entry : entrySet) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

    //分散和聚集
    public static void test4() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("E:\\mq_jstack.txt", "rw");

        //获取通道
        FileChannel fileChannel = raf.getChannel();

        //2.分配指定大小的缓冲区
        ByteBuffer buffer1 = ByteBuffer.allocate(100);
        ByteBuffer buffer2 = ByteBuffer.allocate(1024);

        //3.分散读取
        ByteBuffer[] buffers = {buffer1, buffer2};
        fileChannel.read(buffers);

        for (ByteBuffer buffer : buffers) {
            buffer.flip();
        }

        System.out.println(new String(buffers[0].array(), 0, buffers[0].limit()));
        System.out.println("----------------");
        System.out.println(new String(buffers[1].array(), 0, buffers[1].limit()));

        //4.聚集写入
        RandomAccessFile raf2 = new RandomAccessFile("E:\\mq_gather.txt", "rw");
        FileChannel fileChannel1 = raf2.getChannel();
        fileChannel1.write(buffers);

    }

    //通道间传输(直接缓冲区)
    public static void test3() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("E:\\mq_jstack.txt"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("E:\\mq_copy.txt"), StandardOpenOption.CREATE, StandardOpenOption.READ, StandardOpenOption.WRITE);

        inChannel.transferTo(0, inChannel.size(), outChannel);

        inChannel.close();
        outChannel.close();
    }

    //使用直接缓冲区进行文件的复制（内存映射文件）
    public static void test2() throws IOException {

        long start = System.currentTimeMillis();

        FileChannel inChannel = FileChannel.open(Paths.get("e:\\zqwhs.mp4"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("e:\\zqwhs_copy.mp4"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        //直接对缓冲区数据进行读写操作
        byte[] dst = new byte[inMappedBuf.limit()];
        inMappedBuf.get(dst);
        outMappedBuf.put(dst);

        inChannel.close();
        outChannel.close();

        long end = System.currentTimeMillis();
        System.out.println("直接缓存消耗时间为：" + (end - start));

    }

    //利用通道完成文件的复制（非直接缓冲区）
    public static void test1() {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        FileChannel inChannel = null;
        FileChannel outChannel = null;

        try {
            fis = new FileInputStream("E:\\mq_jstack.txt");
            fos = new FileOutputStream("E:\\mq_copy.txt");

            //获取通道
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            //分配指定大小的缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (inChannel.read(buffer) != -1) {
                //切换成读模式
                buffer.flip();
                //将缓冲区中的数据写入通道
                outChannel.write(buffer);
                //清空缓冲区
                buffer.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


}
