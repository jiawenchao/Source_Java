package common.beanutils;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wenchao
 * Date: 2017/8/19 0019 下午 11:09
 * desc: 为这个类添加一句注释
 */
public class PropertyUtilsBean {

    private Resolver resolver = new DefaultResolver();

    public boolean isReadable(Object bean, String name) {
        if (bean == null) {
            throw new IllegalArgumentException("PropertyUtilsBean->isReadable(Object bean, String name); bean为空");
        }

        if (name == null) {
            throw new IllegalArgumentException("PropertyUtilsBean->isReadable(Object bean, String name); name为空");
        }

        while (resolver.hasNested(name)) {     //只要name包含'[' 、']' 、'(' 、')' 、‘.'就绝对返回true
            String next = resolver.next(name); // 截取[] 或者 () 或者。之前的字符串
            Object nestedBean = null;
            nestedBean = getProperty(bean, next);
        }

    }

    public Object getProperty(Object bean, String name) {
        return getNestedProperty(bean, name);
    }


    public Object getNestedProperty(Object bean, String name) {
        if (bean == null) {
            throw new IllegalArgumentException("PropertyUtilsBean->getNestedProperty(Object bean, String name); bean为空");
        }

        if (name == null) {
            throw new IllegalArgumentException("PropertyUtilsBean->getNestedProperty(Object bean, String name); name为空");
        }

        while (resolver.hasNested(name)) {
            String next = resolver.next(name);
            Object nestedBean = null;
            if (bean instanceof Map) {
                nestedBean = getPropertyOfMapBean((Map<?, ?>) bean, next);
            } else if (resolver.isMapped(name)) {
                nestedBean = getMappedProperty(bean, next);;
            }
        }
    }

    public Object getPropertyOfMapBean(Map<?, ?> bean, String propertyName) {
        if (resolver.isMapped(propertyName)) {
            String name = resolver.getProperty(propertyName);  //去除'[' 、 ‘]‘ 、 '(' 、 ')'

            if (name == null || name.length() == 0) {
                propertyName = resolver.getKey(propertyName);
            }
        }

        if (resolver.isIndexed(propertyName) || resolver.isMapped(propertyName)) {
            throw new IllegalArgumentException("map 是不支持 indexed () 或者双重 map[]" + propertyName);
        }

        return bean.get(propertyName);
    }

    public Object getMappedProperty(Object bean, String name) {
        String key = null;
        key = resolver.getKey(name);
        if (key == null) {
            throw new IllegalArgumentException("参数错误" + name + "来自" + bean.getClass());
        }

        name = resolver.getProperty(name);

        return getMappedProperty(bean, name, key);
    }

    public Object getMappedProperty(Object bean, String name, String key) {

        // 这里跳过对参数为空的异常

        if (bean instanceof DynaBean) {
            DynaProperty descriptor = ((DynaBean) bean).getDynaClass().getDynaProperty(name);

            if (descriptor == null) {
                throw new IllegalArgumentException("Unknown property " + name + " on bean class " + bean.getClass());
            }

            return ((DynaBean) bean).get(name, key);
        }

    }
}
