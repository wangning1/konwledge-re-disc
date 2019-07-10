package com.xml.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.sax.SAXSource;

import org.apache.commons.lang.StringUtils;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLFilterImpl;
import org.xml.sax.helpers.XMLReaderFactory;

public class XmlUtil {

    private final static Logger logger = LoggerFactory.getLogger(XmlUtil.class);

    /**
     * JAXB序列化器
     *
     * @param clazz
     * @return
     * @throws JAXBException
     */
    private static Marshaller initMarshaller(Class<?> clazz) throws JAXBException {
        // context
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        // marshaller
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);// 格式化输出
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");// 设置输出编码,默认为UTF-8

        return marshaller;
    }

    /**
     * 序列化过滤器，去掉JAXB命名空间的前缀，或加上固定前缀,或忽略名空间
     *
     * @param writer
     * @param isIgnoreNamespace
     * @param namespace
     * @return
     */
    private static XMLFilterImpl initMarshallerFilter(StringWriter writer, final boolean isIgnoreNamespace,
                                                      final String namespace) {

        // namespace prefix filter
        XMLFilterImpl filter = new XMLFilterImpl() {
            private boolean ignoreNamespace = isIgnoreNamespace;
            private String rootNamespace = namespace;
            private boolean isRootElement = true;

            @Override
            public void startDocument() throws SAXException {
                super.startDocument();
            }

            @Override
            public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
                if (this.ignoreNamespace)
                    uri = "";
                if (this.isRootElement)
                    this.isRootElement = false;
                else if (!uri.equals("") && !localName.contains("xmlns"))
                    localName = localName + " xmlns=\"" + uri + "\"";

                super.startElement(uri, localName, localName, atts);
            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                if (this.ignoreNamespace)
                    uri = "";
                super.endElement(uri, localName, localName);
            }

            @Override
            public void startPrefixMapping(String prefix, String url) throws SAXException {
                if (this.rootNamespace != null)
                    url = this.rootNamespace;
                if (!this.ignoreNamespace)
                    super.startPrefixMapping("", url);

            }
        };
        OutputFormat format = new OutputFormat();
        format.setIndent(false);
        format.setNewlines(false);
        format.setNewLineAfterDeclaration(false);
        XMLWriter xmlWriter = new XMLWriter(writer, format);
        filter.setContentHandler(xmlWriter);

        return filter;
    }

    /**
     * JAXB反序列化器
     *
     * @param clazz
     * @return
     * @throws JAXBException
     */
    public static Unmarshaller initUnmarshaller(Class<?> clazz) throws JAXBException {
        // context
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        // unmarshaller
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return unmarshaller;
    }

    /**
     * 反序列化过滤器，去掉JAXB命名空间的前缀，或忽略名空间
     *
     * @param isIgnoreNamespace
     * @return
     * @throws SAXException
     */
    private static XMLFilterImpl initUnmarshallerFilter(final boolean isIgnoreNamespace) throws SAXException {
        XMLFilterImpl filter = new XMLFilterImpl() {
            private boolean ignoreNamespace = false;
            private boolean isRootElement = true;

            @Override
            public void startDocument() throws SAXException {
                super.startDocument();
            }

            @Override
            public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
                if (this.ignoreNamespace)
                    uri = "";
                if (this.isRootElement)
                    this.isRootElement = false;
                else {
                    uri = "";
                    localName = "";
                }

                super.startElement(uri, localName, qName, atts);
            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                if (this.ignoreNamespace)
                    uri = "";
                super.endElement(uri, localName, localName);
            }

            @Override
            public void startPrefixMapping(String prefix, String url) throws SAXException {
                if (!this.ignoreNamespace)
                    super.startPrefixMapping("", url);
            }
        };
        filter.setParent(XMLReaderFactory.createXMLReader());
        return filter;
    }

    /**
     * 解析xml string为class对象
     *
     * @param <T>
     * @param xmlStr
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T toObject(String xmlStr, Class<T> clazz) {
        T object = null;
        if (StringUtils.isBlank(xmlStr)) {
            return null;
        }
        try {
            logger.info("toObject: " + clazz + " , str : " + xmlStr);

            Unmarshaller unmarshaller = initUnmarshaller(clazz);
            XMLFilterImpl filter = initUnmarshallerFilter(false);
            InputSource input = new InputSource(new StringReader(xmlStr));
            SAXSource source = new SAXSource(filter, input);

            object = (T) unmarshaller.unmarshal(source);

            logger.info("toObject: " + object);
        } catch (Exception e) {
            logger.error("String to map failed!", e);
        }
        return object;
    }

    /**
     * object对象转换给json string
     *
     * @param obj
     * @return
     */
    public static String toXmlString(Object obj) {
        String retStr = "";
        if (obj == null) {
            return retStr;
        }
        try {
            logger.info("toXmlString object : " + obj);

            logger.info("---:" + obj.getClass());
            Marshaller marshaller = initMarshaller(obj.getClass());
            StringWriter writer = new StringWriter();
            XMLFilterImpl filter = initMarshallerFilter(writer, false, null);
            marshaller.marshal(obj, filter);

            logger.info("toXmlString: " + obj.getClass() + " , xml string: " + writer.toString());
            return writer.toString();
        } catch (Exception e) {
            logger.error("Object to xml string failed!", e);
        }
        return retStr;
    }

    public static void  main(String[] args){

    }
}
