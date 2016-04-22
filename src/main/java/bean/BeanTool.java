package bean;

import exception.ExceptionTool;
import exception.SystemException;
import lang.SerializationTool;
import lang.string.StringTool;
import log.Log;
import log.LogFactory;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang3.SerializationException;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.Map.Entry;

/**
 * Bean操作工具类
 */
public class BeanTool {

    protected static final Log logger = LogFactory.getLog(BeanTool.class);

    static {
        ConvertUtils.register(new DateConverter(null), Date.class);
        ConvertUtils.register(new DateConverter(null), java.sql.Date.class);
        ConvertUtils.register(new DateConverter(null), java.sql.Timestamp.class);
    }

    private BeanTool() {
    }

    /**
     * 深度克隆指定的bean
     * 该方法比直接在对象图中的所有对象重写克隆方法慢很多倍. 但是, 对于复杂的对象图, 或那些不支持深底克隆的对象, 这提供了另一种实现. 当然, 所有对象都必须实现 {@code Serializable}接口.
     *
     * @param bean 被克隆的bean
     * @return 克隆后的bean
     * @throws SerializationException (运行时) 如果序列化失败
     */
    public static <T extends Serializable> T deepClone(T bean) {
        return SerializationTool.clone(bean);
    }

    /**
     * 根据字段映射，拷贝源对象的属性，到指定目标类对象的对应属性
     *
     * @param destClass   目标类
     * @param srcObj      源对象
     * @param propertyMap 字段映射 Map<源对象属性名，目标对象属性名>
     * @return 目标类的对象
     */
    public static <T> T copyProperties(Class<T> destClass, Object srcObj, Map<String, String> propertyMap) {
        if (destClass == null || srcObj == null || propertyMap == null) {
            return null;
        }
        T destObj = null;
        try {
            destObj = destClass.newInstance();
            copyProperties(destObj, srcObj, propertyMap);
        } catch (Exception e) {
            String msgPattern = "属性拷贝失败! destClass：{0}，srcObj：{1}， fieldMap: {2}";
            logger.error(e, msgPattern, destClass, srcObj, propertyMap);
        }
        return destObj;
    }

    /**
     * 根据字段映射，拷贝源对象的属性，到指定目标对象的对应属性
     *
     * @param destObj     目标对象
     * @param srcObj      源对象
     * @param propertyMap 字段映射 Map<源对象属性名，目标对象属性名>
     * @return 目标类的对象
     */
    public static void copyProperties(Object destObj, Object srcObj, Map<String, String> propertyMap) {
        if (destObj == null || srcObj == null || propertyMap == null) {
            return;
        }
        try {
            Set<Entry<String, String>> entrySet = propertyMap.entrySet();
            for (Entry<String, String> entry : entrySet) {
                String srcField = entry.getKey();
                String destFieldStr = entry.getValue();
                if (StringTool.isNotBlank(srcField) && StringTool.isNotBlank(destFieldStr)) {
                    Object result = getProperty(srcObj, srcField);
                    if (result != null) {
                        copyProperty(destObj, destFieldStr, result);
                    }
                }
            }
        } catch (Exception e) {
            String msgPattern = "属性拷贝失败! destObj：{0}，srcObj：{1}， fieldMap: {2}";
            logger.error(e, msgPattern, destObj, srcObj, propertyMap);
        }
    }

    /**
     * 拷贝源对象的所有属性到指定类的对象的对应属性
     *
     * @param srcObj      源对象
     * @param targetClass 目标类
     * @return 目标类的对象
     * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause:
     *                         InvocationTargetException 对被调用方法的包装异常
     *                         IllegalAccessException 如果请求的方法不能通过反射访问
     *                         InstantiationException 实例化异常
     */
    @SuppressWarnings("unchecked")
    public static <T> T copyProperties(Object srcObj, Class<T> targetClass) {
        Object target;
        try {
            target = targetClass.newInstance();
            copyProperties(target, srcObj);
        } catch (Exception e) {
            throw new SystemException(ExceptionTool.unwrapSystemException(e), "Bean对象拷贝出错！");
        }
        return (T) target;
    }

    /**
     * 拷贝除了主键外的属性
     *
     * @param src  源对象
     * @param dest 目标对象
     * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause:
     *                         InvocationTargetException 对被调用方法的包装异常
     *                         IllegalAccessException 如果请求的方法不能通过反射访问
     */
    public static <T> void copyPropertiesExcludeId(IEntity<T> src, IEntity<T> dest) {
        T id = dest.getId();
        try {
            copyProperties(src, dest);
            dest.setId(id);
        } catch (Exception e) {
            throw new SystemException(ExceptionTool.unwrapSystemException(e), "Bean属性拷贝出错！");
        }
    }

    /**
     * 拷贝对象，排除指定的属性(不支持嵌套/索引/映射/组合)
     *
     * @param source            源对象
     * @param target            目标对象
     * @param excludeProperties 不拷贝的属性可变数组
     * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause:
     *                         InvocationTargetException 对被调用方法的包装异常
     *                         IllegalAccessException 如果请求的方法不能通过反射访问
     *                         NoSuchMethodException 如果找不到指定的可访问的方法
     *                         IntrospectionException 内省异常
     */
    public static void copyPropertiesExclude(Object source, Object target, String... excludeProperties) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(target.getClass());
            PropertyDescriptor[] targetPds = beanInfo.getPropertyDescriptors();
            List<String> ignoreList = (excludeProperties != null) ? Arrays.asList(excludeProperties) : null;
            for (PropertyDescriptor targetPd : targetPds) {
                if (targetPd.getWriteMethod() != null
                        && (excludeProperties == null || (!ignoreList.contains(targetPd.getName())))) {
                    PropertyDescriptor sourcePd = PropertyUtils.getPropertyDescriptor(source, targetPd.getName());
                    if (sourcePd != null && sourcePd.getReadMethod() != null) {

                        Method readMethod = sourcePd.getReadMethod();
                        if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                            readMethod.setAccessible(true);
                        }
                        Object value = readMethod.invoke(source);
                        Method writeMethod = targetPd.getWriteMethod();
                        if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                            writeMethod.setAccessible(true);
                        }
                        writeMethod.invoke(target, value);
                    }
                }
            }
        } catch (Throwable e) {
            throw new SystemException(e, "Bean属性拷贝出错！");
        }
    }

    /**
     * 重置所有的非id属性的值
     *
     * @param entity 目标bean
     * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause:
     *                         InvocationTargetException 对被调用方法的包装异常
     *                         IllegalAccessException 如果请求的方法不能通过反射访问
     *                         InstantiationException 实例化异常
     */
    @SuppressWarnings("unchecked")
    public static <T> void resetPropertiesExcludeId(IEntity<T> entity) {
        T id = entity.getId();
        try {
            IEntity<T> emptyEntity = entity.getClass().newInstance();
            copyProperties(emptyEntity, entity);
        } catch (Exception e) {
            throw new SystemException(ExceptionTool.unwrapSystemException(e), "重置bean属性出错！");
        }
        entity.setId(id);
    }

    /**
     * 批量属性拷贝
     *
     * @param targetClass 目标类
     * @param srcObjects  源对象集合
     * @return List<目标类对象>
     */
    public static <T> List<T> batchCopyProperties(Class<T> targetClass, Collection<?> srcObjects) {
        List<T> targetList = new ArrayList<T>(srcObjects.size());
        for (Object srcObj : srcObjects) {
            targetList.add(copyProperties(srcObj, targetClass));
        }
        return targetList;
    }

    // ----------------------------------------------------------------------------
    // 封装org.apache.commons.beanutils.BeanUtils和PropertyUtils
    // ----------------------------------------------------------------------------

    /**
     * 基于可用的属性的getters和setters克隆(浅克隆)一个bean，即使该bean本身未实现Cloneable接口
     *
     * @param bean 被克隆的bean
     * @return 克隆后的bean
     * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause:
     *                         InvocationTargetException 对被调用方法的包装异常
     *                         IllegalAccessException 如果请求的方法不能通过反射访问
     *                         NoSuchMethodException 如果找不到指定的可访问的方法
     *                         InstantiationException 实例化异常
     */
    @SuppressWarnings("unchecked")
    public static <T> T shallowClone(T bean) {
        try {
            return (T) BeanUtils.cloneBean(bean);
        } catch (Exception e) {
            throw new SystemException(e, "Bean浅克隆出错！");
        }
    }

    /**
     * 拷贝(浅克隆)所有源bean的属性值到目标bean相同的属性值，能进行类型转换
     *
     * @param orig 源bean
     * @param dest 目标bean
     * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause:
     *                         InvocationTargetException 对被调用方法的包装异常
     *                         IllegalAccessException 如果请求的方法不能通过反射访问
     */
    public static void copyProperties(Object orig, Object dest) {
        try {
            BeanUtils.copyProperties(dest, orig);
        } catch (Exception e) {
            throw new SystemException(e, "Bean拷贝x出错！");
        }
    }

    /**
     * 拷贝(浅克隆)所有源bean的属性值到目标bean相同的属性值，不能进行类型转换
     *
     * @param orig 源bean
     * @param dest 目标bean
     * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause:
     *                         InvocationTargetException 对被调用方法的包装异常
     *                         IllegalAccessException 如果请求的方法不能通过反射访问
     *                         NoSuchMethodException 如果找不到指定的可访问的方法
     */
    public static void copyPropertiesWithoutCast(Object orig, Object dest) {
        try {
            PropertyUtils.copyProperties(dest, orig);
        } catch (Exception e) {
            throw new SystemException(e, "Bean拷贝出错！");
        }
    }

    /**
     * 拷贝(浅克隆)一个指定的属性值到指定的目标bean, 能进行类型转换
     *
     * @param bean  目标bean
     * @param name  属性名(可以嵌套/索引/映射/组合)
     * @param value 属性值
     * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause:
     *                         InvocationTargetException 对被调用方法的包装异常
     *                         IllegalAccessException 如果请求的方法不能通过反射访问
     */
    public static void copyProperty(Object bean, String name, Object value) {
        try {
            BeanUtils.copyProperty(bean, name, value);
        } catch (Exception e) {
            throw new SystemException(e, "拷贝属性值出错！");
        }
    }

    /**
     * 返回指定bean的所有提供getter的属性及其值的Map
     *
     * @param bean 被提取属性的bean
     * @return Map<属性名，属性值>
     * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause:
     *                         InvocationTargetException 对被调用方法的包装异常
     *                         IllegalAccessException 如果请求的方法不能通过反射访问
     *                         NoSuchMethodException 如果找不到指定的可访问的方法
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> extract(Object bean) {
        try {
            return PropertyUtils.describe(bean);
        } catch (Exception e) {
            throw new SystemException(e, "提取属性值出错！");
        }
    }

    /**
     * 返回指定属性的值
     *
     * @param bean 目标bean
     * @param name 属性名(可以嵌套/索引/映射/组合)
     * @return 属性值
     * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause:
     *                         InvocationTargetException 对被调用方法的包装异常
     *                         IllegalAccessException 如果请求的方法不能通过反射访问
     *                         NoSuchMethodException 如果找不到指定的可访问的方法
     */
    public static Object getProperty(Object bean, String name) {
        try {
            return PropertyUtils.getProperty(bean, name);
        } catch (Exception e) {
            throw new SystemException(e, false, "获取属性的值出错！");
        }
    }

    // ----------------------------------------------------------------------------
    // 封装org.apache.commons.beanutils.BeanUtils和PropertyUtils
    // ----------------------------------------------------------------------------

}
