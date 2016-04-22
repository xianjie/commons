package data.xml;

import lang.string.StringTool;
import log.Log;
import log.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;

/**
 * DOM4J操作工具类
 */
public class Dom4jTool {

    protected static final Log logger = LogFactory.getLog(Dom4jTool.class);

    private Dom4jTool() {
    }

    /**
     * 从输入流读取xml文档，读完后输入流将被关闭
     *
     * @param in 输入流，为null将返回null
     * @return Document对象，参数为null将返回null
     */
    public static Document readXml(InputStream in) {
        Document document = null;
        if (in != null) {
            try {
                SAXReader saxReader = new SAXReader();
                document = saxReader.read(in);
            } catch (DocumentException e) {
                logger.error(e, "读取xml文件失败：" + e.getMessage());
            } finally {
                close(in);
            }
        } else {
            logger.error("调用Dom4jTool.readXml方法时传入的InputStream参数为null！");
        }
        return document;
    }

    /**
     * 读取指定路径的xml文档
     *
     * @param path 文件路径，为null或找不到将返回null
     * @return Document对象，若找不到文件路径或路径为null将返回null
     */
    public static Document readXml(String path) {
        Document document = null;
        if (StringTool.isNotBlank(path)) {
            File file = new File(path);
            if (file.exists()) {
                try {
                    SAXReader saxReader = new SAXReader();
                    document = saxReader.read(new File(path));
                } catch (DocumentException e) {
                    logger.error(e, "读取xml文件[{0}]失败：{1}", path, e.getMessage());
                }
            } else {
                logger.error("调用Dom4jTool.readXml方法时传入的path参数对应的文件路径找不到：" + path);
            }
        } else {
            logger.error("调用Dom4jTool.readXml方法时传入的path参数为空！");
        }
        return document;
    }

    /**
     * 将Document对象保存为一个xml文件到本地,默认UTF-8编码，Xml格式为压缩格式
     *
     * @param document 需要保存的document对象，为null将什么也不做
     * @param path     文件路径，为null或找不到将什么也不做
     */
    public static void writeXml(Document document, String path) {
        writeXml(document, path, "UTF-8", OutputFormat.createCompactFormat());
    }

    /**
     * 将Document对象保存为一个xml文件到本地
     *
     * @param document 需要保存的document对象，为null将什么也不做
     * @param path     文件路径，为null或找不到将什么也不做
     * @param encoding 编码，为空将什么也不做
     * @param format   xml文档格式，为null将什么也不做
     */
    public static void writeXml(Document document, String path, String encoding, OutputFormat format) {
        if (document == null) {
            logger.error("调用Dom4jTool.writeXml方法时传入的document参数为null！");
            return;
        }
        if (StringTool.isBlank(path)) {
            logger.error("调用Dom4jTool.writeXml方法时传入的path参数为空！");
            return;
        }
        if (StringTool.isBlank(encoding)) {
            logger.error("调用Dom4jTool.writeXml方法时传入的encoding参数为空！");
            return;
        }
        if (format == null) {
            logger.error("调用Dom4jTool.writeXml方法时传入的format参数为null！");
            return;
        }
        File file = new File(path);
        if (file.exists() == false) {
            logger.error("调用Dom4jTool.writeXml方法时传入的path参数对应的文件路径找不到：" + path);
            return;
        }

        try {
            format.setEncoding(encoding);
            XMLWriter writer = new XMLWriter(new FileWriter(file), format);
            writer.write(document);
            writer.close();
        } catch (IOException e) {
            logger.error(e, "往[" + path + "]写入xml失败！");
        }
    }

    /**
     * 将xml格式的字符串保存为一个xml文件到本地,默认UTF-8编码，Xml格式为压缩格式
     *
     * @param xmlStr xml格式的字符串，为空将什么也不做
     * @param path   文件路径，为null或找不到将什么也不做
     */
    public static void writeXml(String xmlStr, String path) {
        if (StringTool.isBlank(xmlStr)) {
            logger.error("调用Dom4jTool.writeXml方法时传入的xmlStr参数为空！");
            return;
        }
        Document document = stringToDocument(xmlStr);
        if (document != null) {
            writeXml(document, path, "UTF-8", OutputFormat.createCompactFormat());
        }
    }

    /**
     * 将xml格式的字符串保存为一个xml文件到本地
     *
     * @param xmlStr   xml格式的字符串，为空将什么也不做
     * @param path     文件路径，为null或找不到将什么也不做
     * @param encoding 编码，为空将什么也不做
     * @param format   xml文档格式，为null将什么也不做
     */
    public static void writeXml(String xmlStr, String path, String encoding, OutputFormat format) {
        if (StringTool.isBlank(xmlStr)) {
            logger.error("调用Dom4jTool.writeXml方法时传入的xmlStr参数为空！");
            return;
        }
        Document document = stringToDocument(xmlStr);
        if (document != null) {
            writeXml(document, path, encoding, format);
        }
    }

    /**
     * 将xml格式的字符串转为Document对象
     *
     * @param xmlStr xml格式的字符串，为空将返回null
     * @return Document对象，为参数为空或不能解析为xml都将返回null
     */
    public static Document stringToDocument(String xmlStr) {
        Document doc = null;
        if (StringTool.isNotBlank(xmlStr)) {
            try {
                doc = DocumentHelper.parseText(xmlStr);
            } catch (DocumentException e) {
                logger.error(e, "不能将指定的字符串解析为xml：\n" + xmlStr);
            }
        } else {
            logger.error("调用Dom4jTool.string2Document方法时传入的xmlStr参数为空！");
        }
        return doc;
    }

    /**
     * 获得节点的属性值,如果为空,则返回默认值.
     *
     * @param node          节点，为null将返回null
     * @param attributeName 属性名 为空将返回null
     * @param defaultValue  默认值，可以为null
     * @return 指定属性名的值
     */
    public static String getAttributeValue(Element node, String attributeName, String defaultValue) {
        if (node == null) {
            logger.error("调用Dom4jTool.getAttributeValue方法时传入的node参数为null！");
            return null;
        }
        if (StringTool.isBlank(attributeName)) {
            logger.error("调用Dom4jTool.getAttributeValue方法时传入的attributeName参数为空！");
            return null;
        }

        String result = node.attributeValue(attributeName);
        if (StringTool.isNotBlank(result)) {
            return result;
        }
        return defaultValue;
    }

    /**
     * 获得节点的属性值,并以整型的类型输出.为空返回defaultValue指定的默认值。
     *
     * @param node          节点，为null将返回null
     * @param attributeName 属性名 为空将返回null
     * @param defaultValue  默认值
     * @return 指定属性名的整型值，如果node或attributeName为空，或属性值不是整型值都将返回null
     */
    public static Integer getIntegerAttribute(Element node, String attributeName, int defaultValue) {
        if (node == null) {
            logger.error("调用Dom4jTool.getIntegerAttribute方法时传入的node参数为null！");
            return null;
        }
        if (StringTool.isBlank(attributeName)) {
            logger.error("调用Dom4jTool.getIntegerAttribute方法时传入的attributeName参数为空！");
            return null;
        }

        String strValue = node.attributeValue(attributeName);
        if (StringTool.isNotBlank(strValue)) {
            int value;
            try {
                value = Integer.parseInt(strValue);
            } catch (NumberFormatException e) {
                logger.error("XML中的[" + node.getPath() + "]节点的[" + attributeName + "]属性值不是整型类型.");
                return null;
            }
            return value;
        } else {
            return defaultValue;
        }
    }

    /**
     * 获得节点的属性值,并以布尔类型输出.为空返回defaultValue指定的默认值。
     *
     * @param node          节点，为null将返回null
     * @param attributeName 属性名 为空将返回null
     * @param defaultValue  默认值
     * @return
     */
    public static Boolean getBooleanAttribute(Element node, String attributeName, boolean defaultValue) {
        if (node == null) {
            logger.error("调用Dom4jTool.getBooleanAttribute方法时传入的node参数为null！");
            return null;
        }
        if (StringTool.isBlank(attributeName)) {
            logger.error("调用Dom4jTool.getBooleanAttribute方法时传入的attributeName参数为空！");
            return null;
        }

        String strValue = node.attributeValue(attributeName);
        if (StringTool.isNotBlank(strValue)) {
            if (strValue.equals("false")) {
                return false;
            } else if (strValue.equals("true")) {
                return true;
            } else {
                logger.error("XML中的[" + node.getPath() + "]节点的[" + attributeName + "]属性值必须为false或者true.");
                return null;
            }
        } else {
            return defaultValue;
        }
    }

    /**
     * 将xml文档转化成xml字符串(默认UTF-8)
     *
     * @param document 待转化的Document文档，为null将返回null
     * @return Document文档的xml字符串表示, document参数为null将返回null
     */
    public static String asXml(Document document) {
        if (document == null) {
            logger.error("调用Dom4jTool.asXml方法时传入的document参数为null！");
            return null;
        }
        Element root = document.getRootElement();
        return asXml(root);
    }

    /**
     * 将xml文档元素转化成xml字符串(默认UTF-8)
     *
     * @param elem 待转化的Document文档Element对象，为null将返回null
     * @return Element对象的xml字符串表示, elem参数为null将返回null
     */
    public static String asXml(Element elem) {
        return asXml(elem, "UTF-8");
    }

    /**
     * 将xml文档元素转化成指定编码的xml字符串
     *
     * @param elem    待转化的Document文档Element对象，为null将返回null
     * @param charset 编码，为空将当作UTF-8
     * @return Element对象的xml字符串表示, elem参数为null将返回null
     */
    public static String asXml(Element elem, String charset) {
        if (elem == null) {
            logger.error("调用Dom4jTool.asXml方法时传入的elem参数为null！");
            return null;
        }
        if (StringTool.isBlank(charset)) {
            charset = "UTF-8";
        }
        return "<?xml version=\"1.0\" encoding=\"" + charset + "\"?>\n\n" + elementToString(elem);
    }

    /**
     * 将Element元素转化为xml字符串
     *
     * @param elem 待转化的Document文档Element对象，为null将返回null
     * @return Element对象的xml字符串表示, elem参数为null或转化失败都将返回null
     */
    public static String elementToString(Element elem) {
        if (elem == null) {
            logger.error("调用Dom4jTool.elementToString方法时传入的elem参数为null！");
            return null;
        }

        StringWriter out = new StringWriter();
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = new XMLWriter(out, format);
        try {
            writer.write(elem);
        } catch (IOException e) {
            logger.error("将Element元素转化为xml字符串出错！", e);
            return null;
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                logger.error("关闭XMLWriter出错！", e);
            }
        }
        return out.toString();
    }

    private static void close(InputStream in) {
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                logger.error("关闭InputStream出错！", e);
            }
        }
    }

}
