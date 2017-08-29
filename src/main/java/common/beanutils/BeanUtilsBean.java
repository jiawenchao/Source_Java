package common.beanutils;

/**
 * Created with IntelliJ IDEA.
 * User: wenchao
 * Date: 2017/8/19 0019 下午 1:53
 * desc: 为这个类添加一句注释
 */
public class BeanUtilsBean {

    private PropertyUtilsBean propertyUtilsBean;
    private ConvertUtilsBean convertUtilsBean;

    public BeanUtilsBean() {}

    public BeanUtilsBean(PropertyUtilsBean propertyUtilsBean, ConvertUtilsBean convertUtilsBean) {
        this.propertyUtilsBean = propertyUtilsBean;
        this.convertUtilsBean  = convertUtilsBean;
    }

    private static final ContextClassLoaderLocal<BeanUtilsBean>
            BEANS_BY_CLASSLOADER   = new ContextClassLoaderLocal<BeanUtilsBean>() {
                    @Override
                    public BeanUtilsBean initValue() {
                        return new BeanUtilsBean();
                    }
    };

    public BeanUtilsBean getInstance() {
        return BEANS_BY_CLASSLOADER.get();
    }

    public void copyProperties(Object dest, Object orig) {
        if (dest == null) {
            throw new IllegalArgumentException("参数dest为空");
        }

        if (orig == null) {
            throw new IllegalArgumentException("参数orig为空");
        }

        if (orig instanceof DynaBean) {
            DynaProperty[] origDescriptors = ((DynaBean) orig).getDynaClass().getDynaProperties();
            for (DynaProperty origDescriptor : origDescriptors) {
                String name = origDescriptor.getName();

                if ()
            }
        }

    }

    public PropertyUtilsBean getPropertyUtils() {
        return propertyUtilsBean;
    }

}
