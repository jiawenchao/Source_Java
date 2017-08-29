package common.beanutils;


/**
 * Created with IntelliJ IDEA.
 * User: wenchao
 * Date: 2017/8/19 0019 下午 11:26
 * desc: 为这个类添加一句注释
 */
public class DefaultResolver implements Resolver {
    private static final char NESTED        = '.';
    private static final char MAPPED_START  = '(';
    private static final char MAPPED_END    = ')';
    private static final char INDEXED_START = '[';
    private static final char INDEXED_END   = ']';

    @Override
    public boolean hasNested(String expression) {
        if (expression == null || expression.length() == 0) {
            return false;
        } else {
            return (remove(expression) != null);
        }
    }


    public String remove(String expression) {
        if (expression == null || expression.length() == 0) {
            return null;
        }

        String property = next(expression);
        if (property.length() == expression.length()) {
            return  null;
        }

        int start = property.length();
        if (expression.charAt(start) == NESTED) {    //TODO 是否有[]. 或者 {}.的可能
            start ++;
        }
        return expression.substring(start);

    }


    @Override
    public String next(String expression) {
        if (expression == null || expression.length() == 0) {
            return null;
        }
        boolean indexed = false;
        boolean mapped  = false;

        for (int i =0; i < expression.length(); i ++) {
            char c = expression.charAt(i);

            if (indexed) {
                if (c == INDEXED_END) {
                    return expression.substring(0, i + 1);
                }
            } else if (mapped) {
                if (c == MAPPED_END) {
                    return expression.substring(0, i + 1);
                }
            } else {
                if (c == NESTED) {
                    return expression.substring(0, i);
                } else if (c == MAPPED_START) {
                    mapped = true;
                } else if (c == INDEXED_END) {
                    indexed = true;
                }
            }

        }
        return expression;
    }

    @Override
    public boolean isMapped(String expression) {
        if (expression == null || expression.length() == 0) {
            return false;
        }
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == NESTED || c == INDEXED_START) {
                return false;
            } else if (c == MAPPED_START) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isIndexed(String expression) {
        return false;
    }

    @Override
    public String getProperty(String expression) {
        if (expression == null || expression.length() == 0) {
            return expression;
        }

        // 获取'.' 、 '[' 、 ')'前面的内容
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == NESTED) {
                return expression.substring(0, i);
            } else if (c == MAPPED_START || c == INDEXED_START) {
                return expression.substring(0, i);
            }

        }
        return expression;
    }

    @Override
    public String getKey(String expression) {
        if (expression == null || expression.length() == 0) {
            return null;
        }

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == NESTED || c == INDEXED_START) {
                return null;
            } else if (c == MAPPED_START) {
                int end = expression.indexOf(MAPPED_END, i);
                if (end < 0) {
                    throw new IllegalArgumentException("失去结束符']'");
                }

                return expression.substring(i + 1, end);    // 获取[...] 里面的内容
            }
        }
        return null;
    }
}
