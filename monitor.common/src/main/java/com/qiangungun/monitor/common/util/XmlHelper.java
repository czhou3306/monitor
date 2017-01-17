package com.qiangungun.monitor.common.util;

import java.io.StringReader;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

/**
 * XML 操作助手，使用Dom4j完成dom相关操作
 */
public final class XmlHelper {

    /**
     * 禁用构造函数
     */
    private XmlHelper() {
        // 禁用构造函数
    }

    /**
     * 报文原型转换成MAP对象，保持原来的顺序<br>
     * <br>
     * <p/>
     * 报文原型举例：
     * <p/>
     * <pre>
     * &lt;xml>
     *     &lt;map name=”merchantNo”>123456&lt;/map>
     *     &lt;map name=”merchantName”>仲景&lt;/map>
     * &lt;/xml>
     * </pre>
     * <p/>
     * 就返回键值对的Map<String, String>数据结构方式：
     * <p/>
     * <ul>
     * <li>merchantNo - 123456</li>
     * <li>merchantName - 仲景</li>
     * </ul>
     *
     * @param messagePrototype
     * @return
     * @throws DocumentException
     */
    public static Map<String, String> convertPrototype(String messagePrototype)
                                                                               throws DocumentException {
        List<Element> fields = getFields(messagePrototype, "map");

        Map<String, String> paramMap = new LinkedHashMap<String, String>();
        for (Element field : fields) {
            String fieldName = field.attributeValue("name");
            String fieldValue = field.getText();
            paramMap.put(fieldName, fieldValue);
        }

        return paramMap;
    }

    /**
     * 在<code>paramsElement</code>节点下面查找所有的附加参数，填充成Map数据结构<br>
     * <p/>
     * 例如：
     * <p/>
     * <pre>
     *    &lt;validator name="money">
     *        &lt;param name="max">1000.00&lt;/param>
     *        &lt;param name="min">0.00&lt;/param>
     *        &lt;message>交易金额不能超过1000.00元，不能小于0.00元&lt;/message>
     *    &lt;/validator>
     * </pre>
     * <p/>
     * 就返回键值对的Map<String, String>数据结构方式：
     * <p/>
     * <ul>
     * <li>max - 1000.00</li>
     * <li>min - 0.00</li>
     * </ul>
     *
     * @param paramsElement 附加参数节点
     * @return
     */
    public static Map<String, String> getParams(Element paramsElement) {
        LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
        List<Element> paramElements = children(paramsElement, "param");
        for (Element param : paramElements) {

            // 要么就不要配置，要么就要指定配置内容
            String text = param.getText();
            params.put(param.attributeValue("name"), text);
        }

        return params;
    }

    /**
     * 解析XML字符串，获取指定的每个节点
     *
     * @param xml  XML字符串
     * @param node XML节点
     * @return
     * @throws DocumentException
     */
    public static List<Element> getFields(String xml, String node) throws DocumentException {
        Element root = getField(xml);
        return children(root, node);
    }

    public static Map<String, String> parseXml(String message) {

        try {
            Map<String, String> m = new HashMap<String, String>();

            Element root = getField(message);
            List<Element> list = root.elements();
            for (Element e : list) {
                m.put(e.getName(), e.getStringValue());
            }
            return m;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 解析XML字符串，获取指定的每个节点
     *
     * @param xml     XML字符串
     * @param node    XML节点
     * @param subNode XML子节点
     * @return
     * @throws DocumentException
     */
    public static List<Element> getFields(String xml, String node, String subNode)
                                                                                  throws DocumentException {
        Element root = getField(xml);
        Element nodeElement = child(root, node);
        return children(nodeElement, subNode);
    }

    /**
     * 获取XML报文的根节点
     *
     * @param xml
     * @return
     * @throws DocumentException
     */
    public static Element getField(String xml) throws DocumentException {
        StringReader stringReader = null;

        try {
            stringReader = new StringReader(xml);
            SAXReader reader = new SAXReader();

            Document doc = reader.read(stringReader);
            return doc.getRootElement();
        } finally {
            //IOUtils.closeQuietly(stringReader);
            stringReader.close();
        }

    }

    /**
     * Return the child element with the given name. The element must be in the
     * same name space as the parent element.
     *
     * @param element The parent element
     * @param name    The child element name
     * @return The child element
     */
    public static Element child(Element element, String name) {
        return element.element(new QName(name, element.getNamespace()));
    }

    /**
     * Return the descendant element with the given xPath. Remember to remove
     * the heading and tailing backslash ( / )
     *
     * @param element The parent element
     * @param xPath   e.g: "foo/bar"
     * @return The child element. Return null if any sub node name is not
     * matched
     */
    public static Element descendant(Element element, String xPath) {
        if (element == null || xPath == null || xPath.trim().isEmpty()) {
            return null;
        }

        String[] paths = xPath.split("/");

        Element tempElement = element;
        for (String nodeName : paths) {
            tempElement = child(tempElement, nodeName);
        }
        return tempElement;
    }

    /**
     * Return the child elements with the given name. The elements must be in
     * the same name space as the parent element.
     *
     * @param element The parent element
     * @param name    The child element name
     * @return The child elements
     */
    @SuppressWarnings("unchecked")
    public static List<Element> children(Element element, String name) {
        return element.elements(new QName(name, element.getNamespace()));
    }

    /**
     * Return the value of the child element with the given name. The element
     * must be in the same name space as the parent element.
     *
     * @param element The parent element
     * @param name    The child element name
     * @return The child element value
     */
    public static String elementAsString(Element element, String name) {
        return element.elementTextTrim(new QName(name, element.getNamespace()));
    }

    /**
     * 转化元素为int
     *
     * @param element 元素
     * @param name    名称
     * @return 转化后int
     */
    public static int elementAsInteger(Element element, String name) {
        String text = elementAsString(element, name);
        if (text == null) {
            return 0;
        }

        return Integer.parseInt(text);
    }

    /**
     * 转化元素为boolean
     *
     * @param element 元素
     * @param name    名称
     * @return 转化后boolean
     */
    public static boolean elementAsBoolean(Element element, String name) {
        String text = elementAsString(element, name);
        if (text == null) {
            return false;
        }
        return Boolean.valueOf(text);
    }

    @SuppressWarnings("rawtypes")
    private static final Map<Class, XStream> CLASS_2_XSTREAM_INSTANCE = configXStreamInstance();

    /**
     * pojo -> xml
     *
     * @param object
     * @return
     */
    public static String toXml(Object object) {
        return CLASS_2_XSTREAM_INSTANCE.get(object.getClass()).toXML(object);
    }

    @SuppressWarnings("rawtypes")
    private static Map<Class, XStream> configXStreamInstance() {
        Map<Class, XStream> map = new HashMap<Class, XStream>();
        //map.put(ApiAutoReplyMsgResponse.class, configTextOutMessage());
        return map;
    }

    private static XStream getInstance() {
        XStream xstream = new XStream(new XppDriver() {

            @Override
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out, getNameCoder()) {
                    protected String PREFIX_CDATA    = "<![CDATA[";
                    protected String SUFFIX_CDATA    = "]]>";
                    protected String PREFIX_MEDIA_ID = "<MediaId>";
                    protected String SUFFIX_MEDIA_ID = "</MediaId>";

                    @Override
                    protected void writeText(QuickWriter writer, String text) {
                        if (text.startsWith(PREFIX_CDATA) && text.endsWith(SUFFIX_CDATA)) {
                            writer.write(text);
                        } else if (text.startsWith(PREFIX_MEDIA_ID)
                                   && text.endsWith(SUFFIX_MEDIA_ID)) {
                            writer.write(text);
                        } else {
                            super.writeText(writer, text);
                        }

                    }
                };
            }
        });
        xstream.ignoreUnknownElements();
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.addPermission(NullPermission.NULL);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        return xstream;
    }

}
