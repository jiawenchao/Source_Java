package common.beanutils;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: wenchao
 * Date: 2017/8/19 0019 下午 10:29
 * desc: 为这个类添加一句注释
 */
public class DynaProperty implements Serializable{

    protected String name;
    protected Class<?> type;
    protected Class<?> contentType;

    private static final int BOOLEAN_TYPE = 1;
    private static final int BYTE_TYPE    = 2;
    private static final int CHAR_TYPE    = 3;
    private static final int DOUBLE_TYPE  = 4;
    private static final int FLOAT_TYPE   = 5;
    private static final int INT_TYPE     = 6;
    private static final int LONG_TYPE    = 7;
    private static final int SHORT_TYPE   = 8;

    // ---------------------------------------------Constructors

    public DynaProperty(String name) {

    }

    public String getName() {
        return this.name;
    }

    public DynaProperty(String name, Class<?> type) {
        super();
        this.name = name;
        this.type = type;

        if (type != null && type.isArray()) {
            this.contentType = type.getComponentType();      // 返回数组类型的class 比如int[] -> int String[] -> Stirng
        }
    }
}
