package common.beanutils;

/**
 * Created with IntelliJ IDEA.
 * User: wenchao
 * Date: 2017/8/19 0019 下午 11:24
 * desc: 为这个类添加一句注释
 */
public interface Resolver {
    boolean hasNested(String expression);

    String next(String expression);

    boolean isMapped(String expression);

    boolean isIndexed(String expression);

    String getProperty(String expression);

    String getKey(String expression);
}
