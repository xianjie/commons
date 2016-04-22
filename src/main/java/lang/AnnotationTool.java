package lang;

import java.lang.annotation.Annotation;

/**
 * 注解工具类
 */
public class AnnotationTool {

    private AnnotationTool() {
    }

    /**
     * 返回在指定类的类体系(向上)中，匹配类注解的类
     *
     * @param clazz           要查找的源
     * @param annotationClass 要找的注解类
     * @return 匹配类注解的类
     */
    public static Class<?> getClassUpHierarchy(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        if (clazz.equals(Object.class)) {
            return null;
        }
        boolean present = clazz.isAnnotationPresent(annotationClass);
        if (present) {
            return clazz;
        } else {
            return getClassUpHierarchy(clazz.getSuperclass(), annotationClass);
        }
    }

    // ----------------------------------------------------------------------------
    // 封装org.apache.commons.lang3.AnnotationUtils
    // // ----------------------------------------------------------------------------

    /**
     * 检查两个注解是否相等, 使用在{@link Annotation#equals(Object)}API文档描述中的标准.
     *
     * @param a1 第一个注解, {@code null} 返回 {@code false} 除非两个参数都为 {@code null}
     * @param a2 第二个注解, {@code null} 返回 {@code false} 除非两个参数都为 {@code null}
     * @return {@code true} 如果两个注解相等或都为{@code null}
     */
    public static boolean equals(Annotation a1, Annotation a2) {
        return org.apache.commons.lang3.AnnotationUtils.equals(a1, a2);
    }

    /**
     * 生成注解对象的哈希值, 使用{@link Annotation#hashCode()} API文档中描述的算法
     *
     * @param a 注解, 不能为 {@code null}
     * @return 注解对象的哈希值
     * @throws RuntimeException      如果在注解成员访问时产生 {@code Exception} 异常
     * @throws IllegalStateException 如果注解方法调用返回 {@code null}
     */
    public static int hashCode(Annotation a) {
        return org.apache.commons.lang3.AnnotationUtils.hashCode(a);
    }

    /**
     * 生成注解的字符串表示, 使用{@link Annotation#toString()}
     *
     * @param a 注解
     * @return 注解的字符串表示, 不会为 {@code null}
     */
    public static String toString(final Annotation a) {
        return org.apache.commons.lang3.AnnotationUtils.toString(a);
    }

    /**
     * 检查指定的类是否为某个注解的成员
     * java语言规范只允许在注解内使用某些类型。这些类型包括： {@link String}, {@link Class}, 基本类型, {@link Annotation}, {@link Enum}, 和一维数组.
     *
     * @param type 要检查的类, 可以为 {@code null}
     * @return {@code true} 如果指定的类为注解的成员
     */
    public static boolean isValidAnnotationMemberType(Class<?> type) {
        return org.apache.commons.lang3.AnnotationUtils.isValidAnnotationMemberType(type);
    }

    // // ----------------------------------------------------------------------------
    // 封装org.apache.commons.lang3.AnnotationUtils
    // // ----------------------------------------------------------------------------

}
