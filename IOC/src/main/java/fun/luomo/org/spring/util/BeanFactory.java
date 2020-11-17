package fun.luomo.org.spring.util;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author fun.luomo
 * @date 2020/11/17 15:36
 */
public class BeanFactory {

    Map<String, Object> map = new HashMap<>();

    public BeanFactory(String xml) {
        parseXml(xml);
    }

    private void parseXml(String xml)  {

        try {
            String path = this.getClass().getResource("/").getPath() + "\\" + xml;
            File file = new File(path);
            SAXReader reader = new SAXReader();
            Document document = reader.read(file);
            Element rootElement = document.getRootElement();
            Attribute attribute = rootElement.attribute("default-autowire");
            boolean flag = false;
            if (attribute != null) {
                flag = true;
            }


            for (Iterator<Element> first = rootElement.elementIterator(); first.hasNext(); ) {
                /**
                 * ①
                 */
                Element elementFirst = first.next();
                Attribute attributeId = elementFirst.attribute("id");
                String beanName = attributeId.getValue();
                Attribute attributeClazz = elementFirst.attribute("class");
                String clazzName = attributeClazz.getValue();
                Class<?> clazz = Class.forName(clazzName);

                Object object = null;
                // Object object = clazz.newInstance();
                /**
                 * ②
                 */
                for (Iterator<Element> second = elementFirst.elementIterator(); second.hasNext(); ) {
                    Element elementSecond = second.next();
                    if (elementSecond.getName().equals("property")) {
                        object = clazz.newInstance();
                        String refValue = elementSecond.attribute("ref").getValue();
                        Object injetObject = map.get(refValue);
                        String nameValue = elementSecond.attribute("name").getValue();
                        Field field = clazz.getDeclaredField(nameValue);
                        field.setAccessible(true);
                        field.set(object, injetObject);
                    } else if (elementSecond.getName().equals("constructor-arg")) {
//                        String refValue = elementSecond.attribute("ref").getValue();
//                        Object injetObject = map.get(refValue);
//                        Class<?> injetObjectClazz = injetObject.getClass();
//                        Constructor<?> constructor = clazz.getConstructor(injetObjectClazz.getInterfaces()[0]);
//                        object = constructor.newInstance(injetObject);

                        String refValue = elementSecond.attribute("ref").getValue();
                        Object injetObject = map.get(refValue);
                        String nameValue = elementSecond.attribute("name").getValue();
                        Field field = clazz.getDeclaredField(nameValue);

                        Constructor<?> constructor = clazz.getConstructor(field.getType());
                        object = constructor.newInstance(injetObject);

                    }
                }

                if (flag) {
                    if (attribute.getValue().equals("byType")) {
                        Field[] fields = clazz.getDeclaredFields();
                        for (Field field : fields) {
                            Class<?> type = field.getType();

                            int count = 0;
                            Object injetObject = null;
                            for (String key : map.keySet()) {
                                Class<?> temp = map.get(key).getClass().getInterfaces()[0];
                                if (temp.getName().equals(type.getName())) {
                                    injetObject = map.get(key);
                                    count++;
                                }
                            }
                            if (count > 1) {
                                throw new MySpringException("需要一个，找到两个");
                            }else {
                                object = clazz.newInstance();
                                field.setAccessible(true);
                                field.set(object, injetObject);
                            }


                        }

                    }
                }

                if (object == null) {
                    object = clazz.newInstance();
                }
                map.put(beanName, object);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(map);

    }

    public Object getBean(String BeanName) {
        return map.get(BeanName);
    }

}
