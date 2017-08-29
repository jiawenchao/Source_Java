package common.beanutils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wenchao
 * Date: 2017/8/20 0020 上午 12:38
 * desc: 为这个类添加一句注释
 */
public class BasicDynaClass implements DynaClass{
    protected Map<String, DynaProperty> propertiesMap = new HashMap<>();

    @Override
    public DynaProperty getDynaProperty(String name) {
        return propertiesMap.get(name);
    }

    @Override
    public DynaProperty[] getDynaProperties() {
        return new DynaProperty[0];
    }
}
