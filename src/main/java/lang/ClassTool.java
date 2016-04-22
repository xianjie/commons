package lang;

import exception.SystemException;
import log.Log;
import log.LogFactory;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.List;

/**
 * 类工具
 */
public class ClassTool {

    protected static final Log logger = LogFactory.getLog(ClassTool.class);
    private static final String CGLIB_CLASS_SEPARATOR = "$$";

    private ClassTool() {
    }

    /**
     * 取得经过cglib加强过的对象的真实类型
     *
     * @param instance cglib加强过的对象
     * @return 经过cglib加强过的对象的真实类型, 如果传入的对象为null将返回null
     */
    public static Class<?> getUserClass(Object instance) {
        if (instance == null) {
            return null;
        }
        Class<?> clazz = instance.getClass();
        if (clazz != null && clazz.getName().contains(CGLIB_CLASS_SEPARATOR)) {
            Class<?> superClass = clazz.getSuperclass();
            if (superClass != null && !Object.class.equals(superClass)) {
                return superClass;
            }
        }
        return clazz;
    }

    /**
     * 实例化指定名字的类
     *
     * @param className 要实例化的全限定名字的类
     * @return 实例
     * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
     *                         InstantiationException, IllegalAccessException, ClassNotFoundException
     */
    public static synchronized Object instantiate(String className) {
        try {
            return Class.forName(className, true, getClassLoader()).newInstance();
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    private static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 确认指定名称的类是否存在并且可被加载。当它自己或它依赖的类不存在或不能被加载时，将返回false
     *
     * @param className 全限定类名
     * @return 类是否存在并且可被加载
     */
    public static boolean isPresent(String className) {
        try {
            getClassLoader().loadClass(className);
            return true;
        } catch (Throwable ex) {
            // Class or one of its dependencies is not present...
            return false;
        }
    }

    /**
     * 获取类在磁盘上的物理位置
     *
     * @param aClass 要获取位置的类
     * @return 类文件的绝对路径
     */
    public static String getLocationOnDisk(Class<?> aClass) {
        try {
            String url = aClass.getProtectionDomain().getCodeSource().getLocation().getPath();
            return URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            //Can never happen.
            return null;
        }
    }

    // ----------------------------------------------------------------------------
    // 封装org.apache.commons.lang3.ClassUtils
    // ----------------------------------------------------------------------------

    // Short class name
    // ----------------------------------------------------------------------------

    /**
     * 取得一个{@code Object}的扣掉包名的简短类名
     *
     * @param object      要获取类名的对象, 可以为null
     * @param valueIfNull 当对象为空时返回的值
     * @return 扣掉包名的简短类名, 或null
     */
    public static String getShortClassName(Object object, String valueIfNull) {
        return org.apache.commons.lang3.ClassUtils.getShortClassName(object, valueIfNull);
    }

    /**
     * 取得一个{@code Class}的扣掉包名的简短类名
     * 可以考虑用Java5的API {@link Class#getSimpleName()} 代替. 不同的地方在于该方法将返回{@code "Map.Entry"}, 而{@code java.lang.Class}将简单地返回
     *
     * @param cls 要获取的类名的类
     * @return 扣掉包名的简短类名, 或空串
     */
    public static String getShortClassName(Class<?> cls) {
        return org.apache.commons.lang3.ClassUtils.getShortClassName(cls);
    }

    /**
     * 从字符串中取得扣掉包名的简短类名
     * 传入的字符串被假定为一个类的名称, 并且不会作任何检查
     * 请注意, 该方法和Class.getSimpleName()的不同在于, 它将返回{@code "Map.Entry"}, 而{@code java.lang.Class}将简单地返回{@code "Entry"}.
     *
     * @param className 要获取的类名的字符串
     * @return 扣掉包名的简短类名, 或空串
     */
    public static String getShortClassName(String className) {
        return org.apache.commons.lang3.ClassUtils.getShortClassName(className);
    }

    /**
     * <code>aClass.getSimpleName()的null安全版本</code>
     *
     * @param cls 要获取的类名的类
     * @return 扣掉包名的简短类名
     * @see Class#getSimpleName()
     */
    public static String getSimpleName(Class<?> cls) {
        return org.apache.commons.lang3.ClassUtils.getSimpleName(cls);
    }

    /**
     * <code>aClass.getSimpleName()的null安全版本</code>
     *
     * @param object      要获取类名的对象
     * @param valueIfNull 当<code>object</code>为null时要返回的值
     * @return 扣掉包名的简短类名
     * @see Class#getSimpleName()
     */
    public static String getSimpleName(Object object, String valueIfNull) {
        return org.apache.commons.lang3.ClassUtils.getSimpleName(object, valueIfNull);
    }

    // Package name
    // ----------------------------------------------------------------------

    /**
     * 取得一个{@code Object}的包名
     *
     * @param object      要获取类名的对象, 可以为null
     * @param valueIfNull 当<code>object</code>为null时要返回的值
     * @return 对象的包名, 或null
     */
    public static String getPackageName(Object object, String valueIfNull) {
        return org.apache.commons.lang3.ClassUtils.getPackageName(object, valueIfNull);
    }

    /**
     * 取得一个{@code Class}的包名
     *
     * @param cls 要获取类名的类, 可以为null
     * @return 类的包名或空串
     */
    public static String getPackageName(Class<?> cls) {
        return org.apache.commons.lang3.ClassUtils.getPackageName(cls);
    }

    /**
     * 从字符串中取得包名
     * 传入的字符串被假定为一个类的名称, 并且不会作任何检查
     * 如果类没有在包里, 将返回空串
     *
     * @param className 类名, 可以为 {@code null}
     * @return 包名或空串
     */
    public static String getPackageName(String className) {
        return org.apache.commons.lang3.ClassUtils.getPackageName(className);
    }

    // Superclasses/Superinterfaces
    // ----------------------------------------------------------------------

    /**
     * 取得指定类的所有父类
     *
     * @param cls 要查找的类, 可以为 {@code null}
     * @return 包括所有父类的List, 按类继承体系从下到上的顺序排序, 类为null将返回{@code null}
     */
    public static List<Class<?>> getAllSuperclasses(Class<?> cls) {
        return org.apache.commons.lang3.ClassUtils.getAllSuperclasses(cls);
    }

    /**
     * 取得指定类和其父类的所有实现的接口
     * 结果的顺序将决定于查找的每个接口在源文件中定义的顺序, 并按类继承体系从下到上的顺序. 每个父类以相同的方式进行. 重复的接口将被忽略.
     *
     * @param cls 要查找的类, 可以为 {@code null}
     * @return 包含接口的List, 类为null将返回{@code null}
     */
    public static List<Class<?>> getAllInterfaces(Class<?> cls) {
        return org.apache.commons.lang3.ClassUtils.getAllInterfaces(cls);
    }

    // Convert list
    // ----------------------------------------------------------------------

    /**
     * 批量将类名转为对应的类
     * 一个新的List将被返回. 如果类名对应的类不存在, {@code null}将被放入List. 如果类名为{@code null}, {@code null}也将被放入List.
     *
     * @param classNames List<类名>
     * @return List<类>
     */
    public static List<Class<?>> convertClassNamesToClasses(List<String> classNames) {
        return org.apache.commons.lang3.ClassUtils.convertClassNamesToClasses(classNames);
    }

    /**
     * 批量将类转为类名
     * 一个新的List将被返回. 如果类为{@code null}, {@code null}将被放入返回的List
     *
     * @param classes List<类>
     * @return List<类名>, 如果传入的list为null将返回{@code null}
     */
    public static List<String> convertClassesToClassNames(List<Class<?>> classes) {
        return org.apache.commons.lang3.ClassUtils.convertClassesToClassNames(classes);
    }

    // Is assignable
    // ----------------------------------------------------------------------

    /**
     * 检测类数组中的每个类能否被赋值给另一个数组中的类
     * 该方法为每一对输入类调用{@link #isAssignable(Class, Class) isAssignable}. 它能够被用于检测一组参数(第一个参数)是否兼容另一组参数(第二个参数)
     * 不像{@link Class#isAssignableFrom(Class)}方法, 该方法放宽对基本类型的处理和{@code null}
     * 基本类型放宽允许一个{@code int}被赋值到{@code long}, {@code float} 或 {@code double}. 该方法都能正确处理.
     * {@code Null} 能够被赋给任何类型. 该方法将返回 {@code true} 如果 {@code null} 被传入并且目标不是基本类型的类.
     * 特别的, 该方法测试是否通过一个标识转换放宽基本类型或放宽参考转换, 由指定的{@code Class}参数代表的类型转为由{@code Class}对象代表的类型. 详见
     * <em><a href="http://java.sun.com/docs/books/jls/">Java语言规范</a></em> , 5.1.1, 5.1.2 和 5.1.4.
     * 该方法在计算基本类型和包装类型间的赋值规则, 将依赖于运行的java版本. 例如: 自动装箱/拆箱将作为默认的行为如果运行的java版本>=1.5
     *
     * @param classArray   要检测的类的数组, 可以为 {@code null}
     * @param toClassArray 试图被赋值的类的数组, 可以为 {@code null}
     * @return {@code true} 如果可以赋值
     */
    public static boolean isAssignable(Class<?>[] classArray, Class<?>... toClassArray) {
        return org.apache.commons.lang3.ClassUtils.isAssignable(classArray, toClassArray);
    }

    /**
     * 检测类数组中的每个类能否被赋值给另一个数组中的类
     * 该方法为每一对输入类调用{@link #isAssignable(Class, Class) isAssignable}. 它能够被用于检测一组参数(第一个参数)是否兼容另一组参数(第二个参数)
     * 不像{@link Class#isAssignableFrom(Class)}方法, 该方法放宽对基本类型的处理和{@code null}
     * 基本类型放宽允许一个{@code int}被赋值到{@code long}, {@code float} 或 {@code double}. 该方法都能正确处理.
     * {@code Null} 能够被赋给任何类型. 该方法将返回 {@code true} 如果 {@code null} 被传入并且目标不是基本类型的类.
     * 特别的, 该方法测试是否通过一个标识转换放宽基本类型或放宽参考转换, 由指定的{@code Class}参数代表的类型转为由{@code Class}对象代表的类型. 详见
     * <em><a href="http://java.sun.com/docs/books/jls/">Java语言规范</a></em> , 5.1.1, 5.1.2 和 5.1.4.
     *
     * @param classArray   要检测的类的数组, 可以为 {@code null}
     * @param toClassArray 试图被赋值的类的数组, 可以为 {@code null}
     * @param autoboxing   处理基本类型和包装类型时, 是否显示的使用自动装箱/拆箱
     * @return {@code true} 如果可以赋值
     */
    public static boolean isAssignable(Class<?>[] classArray, Class<?>[] toClassArray, boolean autoboxing) {
        return org.apache.commons.lang3.ClassUtils.isAssignable(classArray, toClassArray, autoboxing);
    }

    /**
     * 检测给定的类型是否为基本类型, 或基本类型的包装类({@link Boolean}, {@link Byte}, {@link Character}, {@link Short}, {@link Integer},
     * {@link Long}, {@link Double}, {@link Float})
     *
     * @param type 待检测的类或null
     * @return true: 如果给定的类型为基本类型, 或基本类型的包装类
     */
    public static boolean isPrimitiveOrWrapper(Class<?> type) {
        return org.apache.commons.lang3.ClassUtils.isPrimitiveOrWrapper(type);
    }

    /**
     * 检测给定的类型是否为基本类型的包装类({@link Boolean}, {@link Byte}, {@link Character}, {@link Short}, {@link Integer}, {@link Long}, {@link Double}, {@link Float})
     *
     * @param type 待检测的类或null
     * @return true: 如果给定的类型为基本类型的包装类
     */
    public static boolean isPrimitiveWrapper(Class<?> type) {
        return org.apache.commons.lang3.ClassUtils.isPrimitiveWrapper(type);
    }

    /**
     * 检测类能否被赋值给另一个类
     * 该方法为每一对输入类调用{@link #isAssignable(Class, Class) isAssignable}. 它能够被用于检测一组参数(第一个参数)是否兼容另一组参数(第二个参数)
     * 不像{@link Class#isAssignableFrom(Class)}方法, 该方法放宽对基本类型的处理和{@code null}
     * 基本类型放宽允许一个{@code int}被赋值到{@code long}, {@code float} 或 {@code double}. 该方法都能正确处理.
     * {@code Null} 能够被赋给任何类型. 该方法将返回 {@code true} 如果 {@code null} 被传入并且目标不是基本类型的类.
     * 特别的, 该方法测试是否通过一个标识转换放宽基本类型或放宽参考转换, 由指定的{@code Class}参数代表的类型转为由{@code Class}对象代表的类型. 详见
     * <em><a href="http://java.sun.com/docs/books/jls/">Java语言规范</a></em> , 5.1.1, 5.1.2 和 5.1.4.
     * 该方法在计算基本类型和包装类型间的赋值规则, 将依赖于运行的java版本. 例如: 自动装箱/拆箱将作为默认的行为如果运行的java版本>=1.5
     *
     * @param cls     待检测的类, 可以为null
     * @param toClass 试图被赋值的类, null将返回false
     * @return {@code true}: 如果可以赋值
     */
    public static boolean isAssignable(Class<?> cls, Class<?> toClass) {
        return org.apache.commons.lang3.ClassUtils.isAssignable(cls, toClass);
    }

    /**
     * 检测类能否被赋值给另一个类
     * 该方法为每一对输入类调用{@link #isAssignable(Class, Class) isAssignable}. 它能够被用于检测一组参数(第一个参数)是否兼容另一组参数(第二个参数)
     * 不像{@link Class#isAssignableFrom(Class)}方法, 该方法放宽对基本类型的处理和{@code null}
     * 基本类型放宽允许一个{@code int}被赋值到{@code long}, {@code float} 或 {@code double}. 该方法都能正确处理.
     * {@code Null} 能够被赋给任何类型. 该方法将返回 {@code true} 如果 {@code null} 被传入并且目标不是基本类型的类.
     * 特别的, 该方法测试是否通过一个标识转换放宽基本类型或放宽参考转换, 由指定的{@code Class}参数代表的类型转为由{@code Class}对象代表的类型. 详见
     * <em><a href="http://java.sun.com/docs/books/jls/">Java语言规范</a></em> , 5.1.1, 5.1.2 和 5.1.4.
     *
     * @param cls        待检测的类, 可以为null
     * @param toClass    试图被赋值的类, null将返回false
     * @param autoboxing 处理基本类型和包装类型时, 是否显示的使用自动装箱/拆箱
     * @return {@code true}: 如果可以赋值
     */
    public static boolean isAssignable(Class<?> cls, Class<?> toClass, boolean autoboxing) {
        return org.apache.commons.lang3.ClassUtils.isAssignable(cls, toClass, autoboxing);
    }

    /**
     * 将基本类型转为对应的包装类型
     * {@code Void.TYPE} 将返回 {@code Void.TYPE}.
     *
     * @param cls 待转化的类型, 可以为null
     * @return {@code cls}的包装类型, 或{@code cls}如果{@code cls}不是基本类型, null将返回{@code null}
     */
    public static Class<?> primitiveToWrapper(Class<?> cls) {
        return org.apache.commons.lang3.ClassUtils.primitiveToWrapper(cls);
    }

    /**
     * 批量地将基本类型转为对应的包装类型
     *
     * @param classes 待转化的类型可变数组, 可以为null或空数组
     * @return 包装类数组, 如果源类型不是基本类型, 数组将直接放入该类型. null将返回{@code null}, 空数组将返回空数组.
     */
    public static Class<?>[] primitivesToWrappers(Class<?>... classes) {
        return org.apache.commons.lang3.ClassUtils.primitivesToWrappers(classes);
    }

    /**
     * 将包装类型转为对应的基本类型
     * 该方法与{@code primitiveToWrapper()}正好相反. 如果参数为基本类型的包装类, 基本类型将被返回. 如果是其他类, 或参数为null, 将返回null
     *
     * @param cls 待转化的类型, 可以为<b>null</b>
     * @return 如果{@code cls}为包装类型, 返回对应的基本类型, 否则返回null
     */
    public static Class<?> wrapperToPrimitive(Class<?> cls) {
        return org.apache.commons.lang3.ClassUtils.wrapperToPrimitive(cls);
    }

    /**
     * 批量地将包装类型转为对应的基本类型
     * 该方法对传入的数组元素逐个调用{@code wrapperToPrimitive()}方法
     *
     * @param classes 待转化的类型数组, 可以为null或空数组
     * @return 基本类型数组, 如果源类型不是包装类, 数组将直接放入该类型. null将返回{@code null}, 空数组将返回空数组.
     */
    public static Class<?>[] wrappersToPrimitives(Class<?>... classes) {
        return org.apache.commons.lang3.ClassUtils.wrappersToPrimitives(classes);
    }

    // Inner class
    // ----------------------------------------------------------------------

    /**
     * 检测给定的类是否为内部类或为静态的内嵌类
     *
     * @param cls 待检查的类, 可以为null
     * @return {@code true}: 如果定的类为内部类或为静态的嵌套类, false: 如果不是或为{@code null}
     */
    public static boolean isInnerClass(Class<?> cls) {
        return org.apache.commons.lang3.ClassUtils.isInnerClass(cls);
    }

    // Class loading
    // ----------------------------------------------------------------------

    /**
     * 使用指定的类加载器, 获取给定类名对应的类. 该方法支持这样的句法: "{@code java.util.Map.Entry[]}", "{@code java.util.Map$Entry[]}", "
     * {@code [Ljava.util.Map.Entry;}", 和 "{@code [Ljava.util.Map$Entry;}".
     *
     * @param classLoader 用来加载指定类的类加载器
     * @param className   类名
     * @param initialize  是否类必须被初始化
     * @return 类
     * @throws ClassNotFoundException 如果对应类找不到
     */
    public static Class<?> getClass(ClassLoader classLoader, String className, boolean initialize) {
        try {
            return org.apache.commons.lang3.ClassUtils.getClass(classLoader, className, initialize);
        } catch (Exception e) {
            logger.error(e, e.getMessage());
        }
        return null;
    }

    /**
     * 使用指定的类加载器, 获取给定类名对应的类(已经初始化). 该方法支持这样的句法: "{@code java.util.Map.Entry[]}", "{@code java.util.Map$Entry[]}", "
     * {@code [Ljava.util.Map.Entry;}", 和 "{@code [Ljava.util.Map$Entry;}".
     *
     * @param classLoader 用来加载指定类的类加载器
     * @param className   类名
     * @return 类
     * @throws ClassNotFoundException 如果对应类找不到
     */
    public static Class<?> getClass(ClassLoader classLoader, String className) {
        try {
            return org.apache.commons.lang3.ClassUtils.getClass(classLoader, className);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    /**
     * 使用当前线程上下文的类加载器, 获取给定类名对应的类(已经初始化). 该方法支持这样的句法: "{@code java.util.Map.Entry[]}", "{@code java.util.Map$Entry[]}
     * ", " {@code [Ljava.util.Map.Entry;}", 和 "{@code [Ljava.util.Map$Entry;}".
     *
     * @param className 类名
     * @return 类
     * @throws ClassNotFoundException 如果对应类找不到
     */
    public static Class<?> getClass(String className) {
        try {
            return org.apache.commons.lang3.ClassUtils.getClass(className);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    /**
     * 使用当前线程上下文的类加载器, 获取给定类名对应的类. 该方法支持这样的句法: "{@code java.util.Map.Entry[]}", "{@code java.util.Map$Entry[]}", "
     * {@code [Ljava.util.Map.Entry;}", 和 "{@code [Ljava.util.Map$Entry;}".
     *
     * @param className  类名
     * @param initialize 是否类必须被初始化
     * @return 类
     * @throws ClassNotFoundException 如果对应类找不到
     */
    public static Class<?> getClass(String className, boolean initialize) {
        try {
            return org.apache.commons.lang3.ClassUtils.getClass(className, initialize);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    // Public method
    // ----------------------------------------------------------------------

    /**
     * 返回指定方法名的方法, 很像{@code Class.getMethod}, 但是它能保存返回的方法是来自公开的类或接口, 并且不是来自匿名类. 这意味着返回的方法可被调用并且不会因为java的一个bug而失败: <a
     * href="http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4071957">4071957</a>.
     * <pre>
     *  Set set = Collections.unmodifiableSet(...);
     *  Method method = ClassUtils.getPublicMethod(set.getClass(), "isEmpty",  new Class[0]);
     *  Object result = method.invoke(set, new Object[]);
     * </pre>
     *
     * @param cls            被检测的类, 不能为null
     * @param methodName     方法名
     * @param parameterTypes 参数类型列表
     * @return 对应的方法
     * @throws NullPointerException  如果类参数为null
     * @throws SecurityException     如果有违反安全性的事发生
     * @throws NoSuchMethodException 如果方法在指定的类中找不到, 或该方法不满足要求
     */
    public static Method getPublicMethod(Class<?> cls, String methodName, Class<?>... parameterTypes) {
        try {
            return org.apache.commons.lang3.ClassUtils.getPublicMethod(cls, methodName, parameterTypes);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    // ----------------------------------------------------------------------

    /**
     * 批量地将对象转为类 <br>
     * 如果任何对象为null, null元素将被插入到结果数组中
     * {@code null} 将返回 {@code null}
     *
     * @param array {@code Object} 数组
     * @return {@code Class} 数组, {@code null} 将返回 {@code null}
     */
    public static Class<?>[] toClass(Object... array) {
        return org.apache.commons.lang3.ClassUtils.toClass(array);
    }

    // Short canonical name
    // ----------------------------------------------------------------------

    /**
     * 获取一个对象的规范名(扣掉包名)
     *
     * @param object      对象, 可以为null
     * @param valueIfNull 对象为null时要返回的值
     * @return 对象的规范名(扣掉包名), 或null
     */
    public static String getShortCanonicalName(Object object, String valueIfNull) {
        return org.apache.commons.lang3.ClassUtils.getShortCanonicalName(object, valueIfNull);
    }

    /**
     * 获取一个类的规范名(扣掉包名)
     *
     * @param cls 类
     * @return 类的规范名(扣掉包名), 或空串
     */
    public static String getShortCanonicalName(Class<?> cls) {
        return org.apache.commons.lang3.ClassUtils.getShortCanonicalName(cls);
    }

    /**
     * 获取一个字符串代表的规范名(扣掉包名)
     * 传入的字符串被假定为一个规范名, 这不会被检查
     *
     * @param canonicalName 规范名
     * @return 类的规范名(扣掉包名), 或空串
     */
    public static String getShortCanonicalName(String canonicalName) {
        return org.apache.commons.lang3.ClassUtils.getShortCanonicalName(canonicalName);
    }

    // Package name
    // ----------------------------------------------------------------------

    /**
     * 从一个对象的规范名获取包名
     *
     * @param object      对象, 可以为null
     * @param valueIfNull 对象为null时要返回的值
     * @return 对象的包名, 或null
     */
    public static String getPackageCanonicalName(Object object, String valueIfNull) {
        return org.apache.commons.lang3.ClassUtils.getPackageCanonicalName(object, valueIfNull);
    }

    /**
     * 从一个类的规范名获取包名
     *
     * @param cls 类, 可以为null
     * @return 包名, 或null
     */
    public static String getPackageCanonicalName(Class<?> cls) {
        return org.apache.commons.lang3.ClassUtils.getPackageCanonicalName(cls);
    }

    /**
     * 从一个规范名获取包名
     * 传入的字符串被假定为一个规范名, 这不会被检查
     * 如果类不在包里, 返回空串
     *
     * @param canonicalName 规范名 , 可以为null
     * @return 包名或空串
     */
    public static String getPackageCanonicalName(String canonicalName) {
        return org.apache.commons.lang3.ClassUtils.getPackageCanonicalName(canonicalName);
    }

    // ----------------------------------------------------------------------------
    // 封装org.apache.commons.lang3.ClassUtils
    // ----------------------------------------------------------------------------

}
