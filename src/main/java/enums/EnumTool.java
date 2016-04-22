package enums;

import exception.ExceptionTool;
import lang.string.StringTool;
import log.Log;
import log.LogFactory;
import org.apache.commons.lang3.EnumUtils;

import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 枚举工具类
 */
public class EnumTool {

    protected static final Log logger = LogFactory.getLog(EnumTool.class);

    private EnumTool() {
    }

    /**
     * 根据枚举类型和编码，取得对应的枚举
     *
     * @param enumClass 枚举类型, 不能为null
     * @param code      编码，可以为null
     * @return 枚举，根据编码找不到对应枚举时返回null
     * @throws IllegalArgumentException enumClass参数为null时
     */
    public static <E extends ICodeEnum> E enumOf(Class<E> enumClass, String code) {
        if (enumClass == null) {
            throw new IllegalArgumentException("enumClass参数不能为null");
        }
        for (E e : enumClass.getEnumConstants()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        logger.error(enumClass.getName() + "不存在code为【" + code + "】的枚举！");
        ExceptionTool.printStackTrace();
        return null;
    }

    /**
     * 根据枚举全类名和编码，取得对应的枚举
     *
     * @param enumClass 枚举全类名，不能为null或空串, 且对应的类必须实现ICodeEnum接口
     * @param code      表码，可以为null
     * @return 枚举，根据编码找不到对应枚举时返回null
     * @throws IllegalArgumentException 参数为空或根据参数查找失败时
     * @author Kevice
     * @time 2013-5-12 下午11:54:52
     * @since 1.0.0
     */
    public static ICodeEnum enumOf(String enumClass, String code) {
        Class<? extends ICodeEnum> enumClazz = getCodeEnumClass(enumClass);
        return enumOf(enumClazz, code);
    }

    /**
     * 取得指定表码枚举的所有表码信息
     *
     * @param enumClass 表码枚举，不能为null
     * @return Map<表码，描述>，不会为null
     * @throws IllegalArgumentException 参数为null时
     */
    public static Map<String, String> getCodeMap(Class<? extends ICodeEnum> enumClass) {
        if (enumClass == null) {
            throw new IllegalArgumentException("enumClass参数不能为null！");
        }
        ICodeEnum[] enumConstants = enumClass.getEnumConstants();
        Map<String, String> codeMap = new LinkedHashMap<String, String>(enumConstants.length);
        for (ICodeEnum e : enumConstants) {
            codeMap.put(e.getCode(), e.getTrans());
        }
        return codeMap;
    }

    /**
     * 取得指定表码枚举的所有表码信息
     *
     * @param enumClass 表码枚举，不能为null或空串
     * @return Map<表码，描述>，不会为null
     * @throws IllegalArgumentException 参数为空或根据参数查找失败时
     */
    public static Map<String, String> getCodeMap(String enumClass) {
        Class<? extends ICodeEnum> enumClazz = getCodeEnumClass(enumClass);
        return getCodeMap(enumClazz);
    }

    /**
     * 根据枚举全类名，取得对应的枚举类
     *
     * @param enumClass 枚举全类名，不能为null或空串, 且对应的类必须实现ICodeEnum接口
     * @return 枚举类，不会为null
     * @throws IllegalArgumentException 参数为空或根据参数查找失败时
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Class<? extends ICodeEnum> getCodeEnumClass(String enumClass) {
        if (StringTool.isBlank(enumClass)) {
            throw new IllegalArgumentException("enumClass参数不能为null！");
        }
        Class enumClazz;
        try {
            enumClazz = Class.forName(enumClass);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(enumClass + "不存在！");
        }
        if (enumClazz.isEnum() == false) {
            throw new IllegalArgumentException(enumClass + "不是枚举！");
        }
        if (ICodeEnum.class.isAssignableFrom(enumClazz) == false) {
            throw new IllegalArgumentException(enumClass + "没有实现" + ICodeEnum.class);
        }
        return enumClazz;
    }


    // ----------------------------------------------------------------------------
    // 封装org.apache.commons.lang3.EnumUtils
    // ----------------------------------------------------------------------------


    /**
     * 将枚举中的元素放以Map的形式返回
     *
     * @param <E>       枚举类型
     * @param enumClass 待查找的枚举类, 不能为null
     * @return 可修改的map, 不会为null. Map<枚举元素name，枚举元素>
     * @throws IllegalArgumentException enumClass参数为null时
     */
    public static <E extends Enum<E>> Map<String, E> getEnumMap(Class<E> enumClass) {
        if (enumClass == null) {
            throw new IllegalArgumentException("enumClass参数不能为null");
        }
        return EnumUtils.getEnumMap(enumClass);
    }

    /**
     * 将枚举中的元素放以List的形式返回
     *
     * @param <E>       枚举类型
     * @param enumClass 待查找的枚举类, 不能为null
     * @return 可修改的list, 不会为null. List<枚举元素>
     * @throws IllegalArgumentException enumClass参数为null时
     */
    public static <E extends Enum<E>> List<E> getEnumList(Class<E> enumClass) {
        if (enumClass == null) {
            throw new IllegalArgumentException("enumClass参数不能为null");
        }
        return EnumUtils.getEnumList(enumClass);
    }

    /**
     * 检查指定是名字是否为指定的枚举类的有效枚举元素
     * 该方法{@link Enum#valueOf}不同，当枚举名无效时它不会抛出异常。
     *
     * @param <E>       枚举类型
     * @param enumClass 待查找的枚举类, 不能为null
     * @param enumName  枚举元素名， null将返回false
     * @return true 如果枚举元素名有效, 否则为 false
     */
    public static <E extends Enum<E>> boolean isValidEnum(Class<E> enumClass, String enumName) {
        return EnumUtils.isValidEnum(enumClass, enumName);
    }

    /**
     * 根据枚举元素名称获取对应的枚举元素，如果没找到返回null
     * 该方法{@link Enum#valueOf}不同，当枚举名无效时它不会抛出异常。
     *
     * @param <E>       枚举类型
     * @param enumClass 待查找的枚举类, 不能为null
     * @param enumName  枚举元素名， null将返回null
     * @return 枚举元素, 如果没找到返回null
     */
    public static <E extends Enum<E>> E getEnum(Class<E> enumClass, String enumName) {
        return EnumUtils.getEnum(enumClass, enumName);
    }

    /**
     * 创建一个long型位向量来表示指定的枚举子集。
     * 该方法生成的值可以作为{@link EnumTool#processBitVector}的输入
     * 当您的枚举中有超过64个值时不要使用该方法，因为这将创建一个超过long型所允许的最大值的值。
     *
     * @param <E>       枚举类型
     * @param enumClass 枚举类, 不能为 {@code null}
     * @param values    需要转换的枚举元素的迭代器, 不能为 {@code null}
     * @return 一个长整形值， 它的位值代表枚举元素的值
     * @throws NullPointerException     如果 {@code enumClass} 或 {@code values} 为 {@code null}
     * @throws IllegalArgumentException 如果 {@code enumClass} 不是一个枚举类或超过64个枚举元素
     */
    public static <E extends Enum<E>> long generateBitVector(Class<E> enumClass, Iterable<E> values) {
        return EnumUtils.generateBitVector(enumClass, values);
    }

    /**
     * 创建一个long型位向量来表示指定的枚举数组
     * 该方法生成的值可以作为{@link EnumTool#processBitVector}的输入
     * 当您的枚举中有超过64个值时不要使用该方法，因为这将创建一个超过long型所允许的最大值的值。
     *
     * @param <E>       枚举类型
     * @param enumClass 枚举类, 不能为 {@code null}
     * @param values    需要转换的枚举元素的可变数组, 不能为 {@code null}
     * @return 一个长整形值， 它的位值代表枚举元素的值
     * @throws NullPointerException     如果 {@code enumClass} 或 {@code values} 为 {@code null}
     * @throws IllegalArgumentException 如果 {@code enumClass} 不是一个枚举类或超过64个枚举元素
     */
    public static <E extends Enum<E>> long generateBitVector(Class<E> enumClass, E... values) {
        return EnumUtils.generateBitVector(enumClass, values);
    }

    /**
     * 将{@link EnumTool#generateBitVector}创建的长整形值转换为它所表示的枚举元素集合
     * 如果您存储了该值，谨防枚举任何更改会影响序号值。
     *
     * @param <E>       枚举类型
     * @param enumClass 枚举类, 不能为 {@code null}
     * @param value     表示的枚举元素集合的长整形值
     * @return 枚举元素集合
     * @throws NullPointerException     如果 {@code enumClass} 为 {@code null}
     * @throws IllegalArgumentException 如果 {@code enumClass} 不是一个枚举类或超过64个枚举元素
     */
    public static <E extends Enum<E>> EnumSet<E> processBitVector(Class<E> enumClass, long value) {
        return EnumUtils.processBitVector(enumClass, value);
    }

    // ----------------------------------------------------------------------------
    // 封装org.apache.commons.lang3.EnumUtils
    // ----------------------------------------------------------------------------

}
