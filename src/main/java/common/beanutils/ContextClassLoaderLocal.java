package common.beanutils;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: wenchao
 * Date: 2017/8/19 0019 下午 2:02
 * desc: 为这个类添加一句注释
 */
public class ContextClassLoaderLocal<T> {

    /*如果key除了自己不在被其他对象引用 再被访问之后自动自动释放对象，详细演示在main方法， 此外此类也是线程不安全的*/
    private Map<ClassLoader, T> valueByClassLoader = new WeakHashMap<>();

    public T initValue() {
        return null;
    }

    public synchronized T get() {
        // 如果WeakHashMap的key已不被其它对象引用，则清除 因为内部调用了expungeStaleEntries();
        valueByClassLoader.isEmpty();

        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader != null) {
            T value = valueByClassLoader.get(contextClassLoader);
            if ((value == null) && !valueByClassLoader.containsKey(contextClassLoader)) {
                value = initValue();
                valueByClassLoader.put(contextClassLoader, value);
            }
            return value;
        }
        return null;
    }



    public static void main(String[] args) {
//        System.out.println("内存会溢出------java.lang.OutOfMemoryError: Java heap space");
//        List<WeakHashMap<byte[][], byte[][]>> maps = new ArrayList<WeakHashMap<byte[][], byte[][]>>();
//
//        for (int i = 0; i < 1000; i++) {
//            WeakHashMap<byte[][], byte[][]> d = new WeakHashMap<byte[][], byte[][]>();
//            d.put(new byte[10000][100000], new byte[10000][10000]);
//            maps.add(d);
//            System.gc();
//            System.out.println(i);
//        }

        // -------------------------------------------------------------------------------------------------

//        System.out.println("此方法不会内存溢出");
//        List<WeakHashMap<byte[][], byte[][]>> maps2 = new ArrayList<WeakHashMap<byte[][], byte[][]>>();
//        for (int i = 0; i < 1000; i++) {
//            WeakHashMap<byte[][], byte[][]> d = new WeakHashMap<byte[][], byte[][]>();
//            d.put(new byte[10000][100000], new byte[10000][10000]);
//            maps2.add(d);
//            System.gc();
//            System.out.println(i);
//            for (int j = 0; j < i; j++) {
//                System.out.println(j + " size" + maps2.get(j).size());
//            }
//        }

        Map<String, String> maps = new WeakHashMap<>();
        String aa = new String("aa");
        String bb = new String("bb");
        String cc = new String("cc");
        String dd = new String("dd");
        maps.put(aa, "aaa");
        maps.put(bb, "bbb");
        maps.put(cc, "ccc");
        maps.put(dd, "ddd");

//        Set<Map.Entry<String, String>> entrySet = maps.entrySet();
//        for (Map.Entry<String, String> entry : entrySet) {
//            System.out.println(entry.getKey());
//            System.out.println(entry.getValue());
//        }

        System.out.println(maps.size());
       // 执行以下这两步， 代表弱键没有再被其他对象引用，会被gc回收 会在下次调用其内容时回收
        aa = null;
//        System.gc();
        System.out.println(maps.size());
        Set<Map.Entry<String, String>> entrySets = maps.entrySet();
        for (Map.Entry entry : entrySets) {
            System.out.println(entry.getKey());
        }
        maps.isEmpty();
        System.out.println(maps.size());

    }
}