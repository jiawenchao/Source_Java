package common.beanutils;

/**
 * Created with IntelliJ IDEA.
 * User: wenchao
 * Date: 2017/8/19 0019 下午 10:23
 * desc: 为这个类添加一句注释
 */
public interface DynaBean {

    public boolean contains(String name, String key);

    public Object get(String name);
    public Object get(String name, String index);

    public DynaClass getDynaClass();
}
