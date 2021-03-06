package lang.reflect;

import exception.SystemException;

import java.lang.reflect.Constructor;

/**
 * 构造方法工具类
 */
public class ConstructorTool {

    private ConstructorTool() {
    }

    // ----------------------------------------------------------------------------
    // 封装org.apache.commons.lang3.reflect.ConstructorUtils
    // ----------------------------------------------------------------------------

    /**
     * 返回指定类的一个新的实例, 使用指定的构造器参数
     * 该方法查找并调用构造器. 构造器的签名必须赋值兼容地匹配参数类型.
     *
     * @param <T>  要创建的实例的类型
     * @param cls  要创建的实例的类, 不能为null
     * @param args 构造器的参数可变数组, null将被当作空数组
     * @return 指定类的新实例, 不会为null
     * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
     *                         NoSuchMethodException 如果找不到匹配的构造器 <br>
     *                         IllegalAccessException 如果调用不被安全体制允许 <br>
     *                         InvocationTargetException 如果调用时发生错误 <br>
     *                         InstantiationException 如果实例化时发生错误
     */
    public static <T> T invokeConstructor(Class<T> cls, Object... args) {
        try {
            return org.apache.commons.lang3.reflect.ConstructorUtils.invokeConstructor(cls, args);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    /**
     * 返回指定类的一个新的实例, 使用指定的构造器参数和参数类型
     * 该方法查找并调用构造器. 构造器的签名必须赋值兼容地匹配参数类型.
     *
     * @param <T>            要创建的实例的类型
     * @param cls            要创建的实例的类, 不能为null
     * @param args           构造器的参数可变数组, null将被当作空数组
     * @param parameterTypes 参数类型数组, null将被当作空数组
     * @return 指定类的新实例, 不会为null
     * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
     *                         NoSuchMethodException 如果找不到匹配的构造器 <br>
     *                         IllegalAccessException 如果调用不被安全体制允许 <br>
     *                         InvocationTargetException 如果调用时发生错误 <br>
     *                         InstantiationException 如果实例化时发生错误
     */
    public static <T> T invokeConstructor(Class<T> cls, Object[] args, Class<?>[] parameterTypes) {
        try {
            return org.apache.commons.lang3.reflect.ConstructorUtils.invokeConstructor(cls, args, parameterTypes);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    /**
     * 返回指定类的一个新的实例, 使用指定的构造器参数
     * 该方法查找并调用构造器. 构造器的签名必须精确地匹配参数类型.
     *
     * @param <T>  要创建的实例的类型
     * @param cls  要创建的实例的类, 不能为null
     * @param args 构造器的参数可变数组, null将被当作空数组
     * @return 指定类的新实例, 不会为null
     * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
     *                         NoSuchMethodException 如果找不到匹配的构造器 <br>
     *                         IllegalAccessException 如果调用不被安全体制允许 <br>
     *                         InvocationTargetException 如果调用时发生错误 <br>
     *                         InstantiationException 如果实例化时发生错误
     */
    public static <T> T invokeExactConstructor(Class<T> cls, Object... args) {
        try {
            return org.apache.commons.lang3.reflect.ConstructorUtils.invokeExactConstructor(cls, args);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    /**
     * 返回指定类的一个新的实例, 使用指定的构造器参数和参数类型
     * 该方法查找并调用构造器. 构造器的签名必须精确地匹配参数类型.
     *
     * @param <T>            要创建的实例的类型
     * @param cls            要创建的实例的类, 不能为null
     * @param args           构造器的参数可变数组, null将被当作空数组
     * @param parameterTypes 参数类型数组, null将被当作空数组
     * @return 指定类的新实例, 不会为null
     * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
     *                         NoSuchMethodException 如果找不到匹配的构造器 <br>
     *                         IllegalAccessException 如果调用不被安全体制允许 <br>
     *                         InvocationTargetException 如果调用时发生错误 <br>
     *                         InstantiationException 如果实例化时发生错误
     */
    public static <T> T invokeExactConstructor(Class<T> cls, Object[] args, Class<?>[] parameterTypes) {
        try {
            return org.apache.commons.lang3.reflect.ConstructorUtils.invokeExactConstructor(cls, args, parameterTypes);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    // -----------------------------------------------------------------------

    /**
     * 查找指定类和签名的构造器, 会检查它的可访问性
     * 该方法查找构造器并保证它是可访问的. 构造器签名必须精确匹配给定的参数类型.
     *
     * @param <T>            构造器类型
     * @param cls            要查找的构造器的类, 不能为null
     * @param parameterTypes 参数类型数组, null将被当作空数组
     * @return 构造器, 如果没有找到匹配的构造器则返回null
     */
    public static <T> Constructor<T> getAccessibleConstructor(Class<T> cls, Class<?>... parameterTypes) {
        return org.apache.commons.lang3.reflect.ConstructorUtils.getAccessibleConstructor(cls, parameterTypes);
    }

    /**
     * 检查指定的构造器是否可访问
     * 该方法简单的确保指定的构造器是否可访问
     *
     * @param <T>  构造器类型
     * @param ctor 构造器对象, 不能为null
     * @return 构造器, 如果没有找到匹配的可访问的构造器则返回null
     */
    public static <T> Constructor<T> getAccessibleConstructor(Constructor<T> ctor) {
        return org.apache.commons.lang3.reflect.ConstructorUtils.getAccessibleConstructor(ctor);
    }

    /**
     * 查找指定类和签名的构造器, 会检查它的可访问性
     * 该方法检查所有构造器, 并找出一个能匹配给定参数类型的构造器. 它要求每一个参数都能够从给定的参数赋值.
     * 这是一种比正常的精确匹配算法更为灵活的查找方式.
     * 首先检查是否存在严格匹配的签名, 如果没有, 检查所有构造器看是否有赋值兼容的构造器. 第一个找到的构造器将被返回.
     *
     * @param <T>            构造器类型
     * @param cls            要查找的构造器的类, 不能为null
     * @param parameterTypes 匹配的参数类型可变数组
     * @return 构造器, 如果没有找到匹配的构造器则返回null
     */
    public static <T> Constructor<T> getMatchingAccessibleConstructor(Class<T> cls, Class<?>... parameterTypes) {
        return org.apache.commons.lang3.reflect.ConstructorUtils.getMatchingAccessibleConstructor(cls, parameterTypes);
    }

    // ----------------------------------------------------------------------------
    // 封装org.apache.commons.lang3.reflect.ConstructorUtils
    // ----------------------------------------------------------------------------

}
