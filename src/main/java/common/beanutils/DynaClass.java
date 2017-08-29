package common.beanutils;

/**
 * Created with IntelliJ IDEA.
 * User: wenchao
 * Date: 2017/8/19 0019 下午 10:29
 * desc: 为这个类添加一句注释
 */
public interface DynaClass {

    public DynaProperty getDynaProperty(String name);

    public DynaProperty[] getDynaProperties();
}
