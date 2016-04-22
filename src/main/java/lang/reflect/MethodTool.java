package lang.reflect;

import exception.SystemException;
import org.apache.commons.lang3.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 方法工具类
 */
public class MethodTool {

    private MethodTool() {
    }

    private static final String SETTER_PREFIX = "set";
    private static final String GETTER_PREFIX = "get";

    /**
     * 取得所有可读的方法
     *
     * @param clazz 类
     * @return List<方法实例>
     */
    public static List<Method> getReadMethods(Class<?> clazz) {
        List<Method> methods = new ArrayList<Method>();
        try {
            BeanInfo beaninfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] pds = beaninfo.getPropertyDescriptors();
            for (PropertyDescriptor propDesc : pds) {
                Method readMethod = propDesc.getReadMethod();
                methods.add(readMethod);
            }
            return methods;
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    /**
     * 调用指定属性的getter方法 <br>
     * 支持多级，如：对象名.对象名.属性
     *
     * @param obj          调用的对象
     * @param propertyName 属性名
     * @return 属性名对应get方法的返回值
     * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
     *                         NoSuchMethodException 如果找不到指定的可访问的方法 <br>
     *                         InvocationTargetException 对被调用方法的包装异常 <br>
     *                         IllegalAccessException 如果请求的方法不能通过反射访问
     */
    public static Object invokeGetter(Object obj, String propertyName) {
        Object object = obj;
        for (String name : StringUtils.split(propertyName, ".")) {
            String getterMethodName = GETTER_PREFIX + StringUtils.capitalize(name);
            object = invokeExactMethod(object, getterMethodName);
        }
        return object;
    }

    /**
     * 调用指定属性的setter方法 <br>
     * 支持多级，如：对象名.对象名.属性
     *
     * @param obj          调用的对象
     * @param propertyName 属性名
     * @param value        setter方法的参数值
     */
    public static void invokeSetter(Object obj, String propertyName, Object value) {
        Object object = obj;
        String[] names = StringUtils.split(propertyName, ".");
        for (int i = 0; i < names.length; i++) {
            if (i < names.length - 1) {
                String getterMethodName = GETTER_PREFIX + StringUtils.capitalize(names[i]);
                object = invokeExactMethod(object, getterMethodName);
            } else {
                String setterMethodName = SETTER_PREFIX + StringUtils.capitalize(names[i]);
                invokeMethod(object, setterMethodName, new Object[]{value});
            }
        }
    }

    // ----------------------------------------------------------------------------
    // 封装org.apache.commons.lang3.reflect.MethodUtils
    // ----------------------------------------------------------------------------

    /**
     * 在指定的对象上调用一个指定方法名和匹配参数类型的方法
     * 该方法是{@link #getMatchingAccessibleMethod(Class, String, Class[]) 方法的代理
     * 该方法调用时传入包装类能够匹配带有基本类型参数的方法. 例如: 一个<code>Boolean</code>对象将匹配<code>boolean</code>基本类型.
     * 该方法是对方法:
     * {@link #invokeMethod(Object object, String methodName, Object[] args, Class[] parameterTypes)} 的简单包装
     *
     * @param object     方法的调用对象
     * @param methodName 方法名
     * @param args       参数值可变数组 - null将被当作空数组
     * @return 指定方法调用的返回值
     * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
     *                         NoSuchMethodException 如果找不到指定的可访问的方法 <br>
     *                         InvocationTargetException 对被调用方法的包装异常 <br>
     *                         IllegalAccessException 如果请求的方法不能通过反射访问
     */
    public static Object invokeMethod(Object object, String methodName, Object... args) {
        try {
            return org.apache.commons.lang3.reflect.MethodUtils.invokeMethod(object, methodName, args);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    /**
     * 在指定的对象上调用一个指定方法名和匹配参数类型的方法
     * 该方法是{@link #getMatchingAccessibleMethod(Class, String, Class[]) 方法的代理
     * 该方法调用时传入包装类能够匹配带有基本类型参数的方法. 例如: 一个<code>Boolean</code>对象将匹配<code>boolean</code>基本类型.
     *
     * @param object         方法的调用对象
     * @param methodName     方法名
     * @param args           参数值数组 - null将被当作空数组
     * @param parameterTypes 参数类型数组 - null将被当作空数组
     * @return 指定方法调用的返回值
     * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
     *                         NoSuchMethodException 如果找不到指定的可访问的方法 <br>
     *                         InvocationTargetException 对被调用方法的包装异常 <br>
     *                         IllegalAccessException 如果请求的方法不能通过反射访问
     */
    public static Object invokeMethod(Object object, String methodName, Object[] args, Class<?>[] parameterTypes) {
        try {
            return org.apache.commons.lang3.reflect.MethodUtils.invokeMethod(object, methodName, args, parameterTypes);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    /**
     * 在指定的对象上调用一个指定方法名和精确匹配参数类型的方法
     * 该方法使用反射来调用从<code>getAccessibleMethod()</code>获取的方法.
     *
     * @param object     方法的调用对象
     * @param methodName 方法名
     * @param args       参数值数组 - null将被当作空数组
     * @return 指定方法调用的返回值
     * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
     *                         NoSuchMethodException 如果找不到指定的可访问的方法 <br>
     *                         InvocationTargetException 对被调用方法的包装异常 <br>
     *                         IllegalAccessException 如果请求的方法不能通过反射访问
     */
    public static Object invokeExactMethod(Object object, String methodName, Object... args) {
        try {
            return org.apache.commons.lang3.reflect.MethodUtils.invokeExactMethod(object, methodName, args);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    /**
     * 在指定的对象上调用一个指定方法名和精确匹配参数类型的方法
     * 该方法使用反射来调用从<code>getAccessibleMethod()</code>获取的方法.
     *
     * @param object         方法的调用对象
     * @param methodName     方法名
     * @param args           参数值数组 - null将被当作空数组
     * @param parameterTypes 参数类型数组 - null将被当作空数组
     * @return 指定方法调用的返回值
     * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
     *                         NoSuchMethodException 如果找不到指定的可访问的方法 <br>
     *                         InvocationTargetException 对被调用方法的包装异常 <br>
     *                         IllegalAccessException 如果请求的方法不能通过反射访问
     */
    public static Object invokeExactMethod(Object object, String methodName, Object[] args, Class<?>[] parameterTypes) {
        try {
            return org.apache.commons.lang3.reflect.MethodUtils.invokeExactMethod(object, methodName, args,
                    parameterTypes);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    /**
     * 在指定的类上调用一个指定方法名和匹配参数类型的静态方法
     * 该方法使用反射来调用从<code>getAccessibleMethod()</code>获取的方法.
     *
     * @param cls            方法的调用类
     * @param methodName     方法名
     * @param args           参数值数组 - null将被当作空数组
     * @param parameterTypes 参数类型数组 - null将被当作空数组
     * @return 指定方法调用的返回值
     * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
     *                         NoSuchMethodException 如果找不到指定的可访问的方法 <br>
     *                         InvocationTargetException 对被调用方法的包装异常 <br>
     *                         IllegalAccessException 如果请求的方法不能通过反射访问
     */
    public static Object invokeExactStaticMethod(Class<?> cls, String methodName, Object[] args,
                                                 Class<?>[] parameterTypes) {
        try {
            return org.apache.commons.lang3.reflect.MethodUtils.invokeExactStaticMethod(cls, methodName, args,
                    parameterTypes);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    /**
     * 在指定的类上调用一个指定方法名和匹配参数类型的静态方法
     * 该方法是{@link #getMatchingAccessibleMethod(Class, String, Class[]) 方法的代理
     * 该方法调用时传入包装类能够匹配带有基本类型参数的方法. 例如: 一个<code>Boolean</code>对象将匹配<code>boolean</code>基本类型.
     * 该方法是对方法:
     * {@link #invokeStaticMethod(Class objectClass, String methodName, Object [] args, Class[] parameterTypes)} 的简单包装
     *
     * @param cls        方法的调用类
     * @param methodName 方法名
     * @param args       参数值数组 - null将被当作空数组
     * @return 指定方法调用的返回值
     * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
     *                         NoSuchMethodException 如果找不到指定的可访问的方法 <br>
     *                         InvocationTargetException 对被调用方法的包装异常 <br>
     *                         IllegalAccessException 如果请求的方法不能通过反射访问
     */
    public static Object invokeStaticMethod(Class<?> cls, String methodName, Object... args) {
        try {
            return org.apache.commons.lang3.reflect.MethodUtils.invokeStaticMethod(cls, methodName, args);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    /**
     * 在指定的类上调用一个指定方法名和匹配参数类型的静态方法
     * 该方法是{@link #getMatchingAccessibleMethod(Class, String, Class[]) 方法的代理
     * 该方法调用时传入包装类能够匹配带有基本类型参数的方法. 例如: 一个<code>Boolean</code>对象将匹配<code>boolean</code>基本类型.
     *
     * @param cls            方法的调用类
     * @param methodName     方法名
     * @param args           参数值数组 - null将被当作空数组
     * @param parameterTypes 参数类型数组 - null将被当作空数组
     * @return 指定方法调用的返回值
     * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
     *                         NoSuchMethodException 如果找不到指定的可访问的方法 <br>
     *                         InvocationTargetException 对被调用方法的包装异常 <br>
     *                         IllegalAccessException 如果请求的方法不能通过反射访问
     */
    public static Object invokeStaticMethod(Class<?> cls, String methodName, Object[] args, Class<?>[] parameterTypes) {
        try {
            return org.apache.commons.lang3.reflect.MethodUtils.invokeStaticMethod(cls, methodName, args,
                    parameterTypes);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    /**
     * 在指定的类上调用一个指定方法名和精确匹配参数类型的静态方法
     * 该方法使用反射来调用从<code>getAccessibleMethod(Class, String, Class[])</code>获取的方法.
     *
     * @param cls        方法的调用类
     * @param methodName 方法名
     * @param args       参数值数组 - null将被当作空数组
     * @return 指定方法调用的返回值
     * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
     *                         NoSuchMethodException 如果找不到指定的可访问的方法 <br>
     *                         InvocationTargetException 对被调用方法的包装异常 <br>
     *                         IllegalAccessException 如果请求的方法不能通过反射访问
     */
    public static Object invokeExactStaticMethod(Class<?> cls, String methodName, Object... args) {
        try {
            return org.apache.commons.lang3.reflect.MethodUtils.invokeExactStaticMethod(cls, methodName, args);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    /**
     * 返回指定类的指定方法名和参数对应的可访问(能够通过反射调用)方法. <br>
     * 如果没有这样的方法, 返回<code>null</code>.
     * 该方法只是对 {@link #getAccessibleMethod(Method method)} 方法的简单包装.
     *
     * @param cls            要获取的方法所在的类
     * @param methodName     方法名
     * @param parameterTypes 参数类型数组
     * @return 可访问的方法
     */
    public static Method getAccessibleMethod(Class<?> cls, String methodName, Class<?>... parameterTypes) {
        return org.apache.commons.lang3.reflect.MethodUtils.getAccessibleMethod(cls, methodName, parameterTypes);
    }

    /**
     * 返回一个实现了指定方法的可访问(能够通过反射调用)的方法. <br>
     * 如果没有这样的方法, 返回<code>null</code>.
     *
     * @param method 希望调用的方法
     * @return 可访问的方法
     */
    public static Method getAccessibleMethod(Method method) {
        return org.apache.commons.lang3.reflect.MethodUtils.getAccessibleMethod(method);
    }

    /**
     * 查找一个匹配给定的方法名和可兼容参数的可访问的方法. <br>
     * 可兼容参数意味着每个方法参数都可从给定的参数进行赋值. 换句话, 要查找的方法要能够接受给定的参数值.
     * 该方法被
     * {@link #invokeMethod(Object object, String methodName, Object[] args, Class[] parameterTypes)} 方法使用.
     * 该方法调用时传入包装类能够匹配带有基本类型参数的方法. 例如: 一个<code>Boolean</code>对象将匹配<code>boolean</code>基本类型.
     *
     * @param cls            要查找的方法所在的类
     * @param methodName     方法名
     * @param parameterTypes 可兼容的方法参数可变数组
     * @return 可访问的方法
     */
    public static Method getMatchingAccessibleMethod(Class<?> cls, String methodName, Class<?>... parameterTypes) {
        return org.apache.commons.lang3.reflect.MethodUtils
                .getMatchingAccessibleMethod(cls, methodName, parameterTypes);
    }

    // ----------------------------------------------------------------------------
    // 封装org.apache.commons.lang3.reflect.MethodUtils
    // ----------------------------------------------------------------------------

}
