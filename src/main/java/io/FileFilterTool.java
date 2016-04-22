package io;

import enums.CaseSensitivity;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.*;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 文件过滤工具类
 */
public class FileFilterTool {

    private FileFilterTool() {
    }

    // ----------------------------------------------------------------------------
    // 封装org.apache.commons.io.filefilter.FileFilterUtils
    // ----------------------------------------------------------------------------

    /**
     * 应用一个{@link IOFileFilter}过滤指定的{@link File}数组。
     *
     * @param filter 要应用的文件过滤器
     * @param files  待过滤的文件数组
     * @return 满足过滤条件的文件数组
     * @throws IllegalArgumentException 如果过滤器为{@code null}，或<code>files</code>包含
     *                                  {@code null} 值
     */
    public static File[] filter(IOFileFilter filter, File... files) {
        return FileFilterUtils.filter(filter, files);
    }

    /**
     * 应用一个{@link IOFileFilter}过滤指定的{@link File}迭代器。
     *
     * @param filter 要应用的文件过滤器
     * @param files  待过滤的文件迭代器
     * @return 满足过滤条件的文件数组
     * @throws IllegalArgumentException 如果过滤器为{@code null}，或<code>files</code>包含
     *                                  {@code null} 值
     */
    public static File[] filter(IOFileFilter filter, Iterable<File> files) {
        return FileFilterUtils.filter(filter, files);
    }

    /**
     * 应用一个{@link IOFileFilter}过滤指定的{@link File}迭代器。
     * 该方法返回的 {@link List} 不保证是线程安全的。
     * <pre>
     * Iterable&lt;File&gt; filesAndDirectories = ...
     * List&lt;File&gt; directories = FileFilterUtils.filterList(FileFilterUtils.directoryFileFilter(), filesAndDirectories);
     * </pre>
     *
     * @param filter 要应用的文件过滤器
     * @param files  待过滤的文件迭代器
     * @return 满足过滤条件的文件列表
     * @throws IllegalArgumentException 如果过滤器为{@code null}，或<code>files</code>包含
     *                                  {@code null} 值
     */
    public static List<File> filterList(IOFileFilter filter, Iterable<File> files) {
        return FileFilterUtils.filterList(filter, files);
    }

    /**
     * 应用一个{@link IOFileFilter}过滤指定的{@link File}数组。
     * 该方法返回的 {@link List} 不保证是线程安全的。
     * <pre>
     * File[] filesAndDirectories = ...
     * List&lt;File&gt; directories = FileFilterUtils.filterList(FileFilterUtils.directoryFileFilter(), filesAndDirectories);
     * </pre>
     *
     * @param filter 要应用的文件过滤器
     * @param files  待过滤的文件数组
     * @return 满足过滤条件的文件列表
     * @throws IllegalArgumentException 如果过滤器为{@code null}，或<code>files</code>包含
     *                                  {@code null} 值
     */
    public static List<File> filterList(IOFileFilter filter, File... files) {
        return FileFilterUtils.filterList(filter, files);
    }

    /**
     * 应用一个{@link IOFileFilter}过滤指定的{@link File}数组。
     * 该方法返回的 {@link Set} 不保证是线程安全的。
     * <pre>
     * File[] allFiles = ...
     * Set&lt;File&gt; javaFiles = FileFilterUtils.filterSet(FileFilterUtils.suffixFileFilter(".java"), allFiles);
     * </pre>
     *
     * @param filter 要应用的文件过滤器
     * @param files  待过滤的文件数组
     * @return 满足过滤条件的文件集合
     * @throws IllegalArgumentException 如果过滤器为{@code null}，或<code>files</code>包含
     *                                  {@code null} 值
     */
    public static Set<File> filterSet(IOFileFilter filter, File... files) {
        return FileFilterUtils.filterSet(filter, files);
    }

    /**
     * 应用一个{@link IOFileFilter}过滤指定的{@link File}迭代器。
     * 该方法返回的 {@link Set} 不保证是线程安全的。
     * <pre>
     * Iterable&lt;File&gt; allFiles = ...
     * Set&lt;File&gt; javaFiles = FileFilterUtils.filterSet(FileFilterUtils.suffixFileFilter(".java"), allFiles);
     * </pre>
     *
     * @param filter 要应用的文件过滤器
     * @param files  待过滤的文件迭代器
     * @return 满足过滤条件的文件集合
     * @throws IllegalArgumentException 如果过滤器为{@code null}，或<code>files</code>包含
     *                                  {@code null} 值
     */
    public static Set<File> filterSet(IOFileFilter filter, Iterable<File> files) {
        return FileFilterUtils.filterSet(filter, files);
    }

    /**
     * 返回一个匹配文件名前缀的文件过滤器
     *
     * @param prefix 要匹配的文件名前缀
     * @return 匹配文件名前缀的文件过滤器
     */
    public static IOFileFilter prefixFileFilter(String prefix) {
        return FileFilterUtils.prefixFileFilter(prefix);
    }

    /**
     * 返回一个匹配文件名前缀的文件过滤器
     *
     * @param prefix          要匹配的文件名前缀
     * @param caseSensitivity 大小写匹配规则, null表示依赖于当前操作系统
     * @return 匹配文件名前缀的文件过滤器
     */
    public static IOFileFilter prefixFileFilter(String prefix, CaseSensitivity caseSensitivity) {
        IOCase ioCase = CaseSensitivity.adaptApacheIoCase(caseSensitivity);
        return FileFilterUtils.prefixFileFilter(prefix, ioCase);
    }

    /**
     * 返回一个匹配文件名后缀的文件过滤器
     *
     * @param suffix 要匹配的文件名后缀
     * @return 匹配文件名后缀的文件过滤器
     */
    public static IOFileFilter suffixFileFilter(String suffix) {
        return FileFilterUtils.suffixFileFilter(suffix);
    }

    /**
     * 返回一个匹配文件名后缀的文件过滤器
     *
     * @param suffix          要匹配的文件名后缀
     * @param caseSensitivity 大小写匹配规则, null表示依赖于当前操作系统
     * @return 匹配文件名后缀的文件过滤器
     */
    public static IOFileFilter suffixFileFilter(String suffix, CaseSensitivity caseSensitivity) {
        IOCase ioCase = CaseSensitivity.adaptApacheIoCase(caseSensitivity);
        return FileFilterUtils.suffixFileFilter(suffix, ioCase);
    }

    /**
     * 返回一个匹配文件名的文件过滤器
     *
     * @param name 文件名
     * @return 匹配文件名的文件过滤器
     */
    public static IOFileFilter nameFileFilter(String name) {
        return FileFilterUtils.nameFileFilter(name);
    }

    /**
     * 返回一个匹配文件名的文件过滤器
     *
     * @param name            文件名
     * @param caseSensitivity 大小写匹配规则, null表示依赖于当前操作系统
     * @return 匹配文件名的文件过滤器
     */
    public static IOFileFilter nameFileFilter(String name, CaseSensitivity caseSensitivity) {
        IOCase ioCase = CaseSensitivity.adaptApacheIoCase(caseSensitivity);
        return FileFilterUtils.nameFileFilter(name, ioCase);
    }

    /**
     * 返回一个文件目录(不能是文件)的文件过滤器
     *
     * @return 只接受文件目录(不能是文件)的文件过滤器
     */
    public static IOFileFilter directoryFileFilter() {
        return FileFilterUtils.directoryFileFilter();
    }

    /**
     * 返回一个只接受文件(不能是目录)的文件过滤器
     *
     * @return 只接受文件(不能是目录)的文件过滤器
     */
    public static IOFileFilter fileFileFilter() {
        return FileFilterUtils.fileFileFilter();
    }

    // -----------------------------------------------------------------------

    /**
     * 返回多个文件过滤器逻辑与关系的文件过滤器
     *
     * @param filters 要进行逻辑与的文件过滤器数组
     * @return 多个文件过滤器逻辑与关系的文件过滤器
     * @throws IllegalArgumentException 如果指定的过滤器数组为null或包含null的元素
     */
    public static IOFileFilter and(IOFileFilter... filters) {
        return FileFilterUtils.and(filters);
    }

    /**
     * 返回多个文件过滤器逻辑或关系的文件过滤器
     *
     * @param filters 要进行逻辑或的文件过滤器数组
     * @return 多个文件过滤器逻辑或关系的文件过滤器
     * @throws IllegalArgumentException 如果指定的过滤器数组为null或包含null的元素
     */
    public static IOFileFilter or(IOFileFilter... filters) {
        return FileFilterUtils.or(filters);
    }

    /**
     * 将文件过滤器数组转为列表
     *
     * @param filters 文件过滤器数组
     * @return 文件过滤器列表
     * @throws IllegalArgumentException 如果指定的过滤器数组为null或包含null的元素
     */
    public static List<IOFileFilter> toList(IOFileFilter... filters) {
        return FileFilterUtils.toList(filters);
    }

    /**
     * 返回指定文件过滤器的逻辑非的文件过滤器
     *
     * @param filter 要进行逻辑非的文件过滤器
     * @return 指定文件过滤器的逻辑非的文件过滤器
     */
    public static IOFileFilter notFileFilter(IOFileFilter filter) {
        return FileFilterUtils.notFileFilter(filter);
    }

    // -----------------------------------------------------------------------

    /**
     * 返回总是返回true的文件过滤器
     *
     * @return 总是返回true的文件过滤器
     */
    public static IOFileFilter trueFileFilter() {
        return FileFilterUtils.trueFileFilter();
    }

    /**
     * 返回总是返回false的文件过滤器
     *
     * @return 总是返回false的文件过滤器
     */
    public static IOFileFilter falseFileFilter() {
        return FileFilterUtils.falseFileFilter();
    }

    // -----------------------------------------------------------------------

    /**
     * 将<code>FileFilter</code>包装为<code>IOFileFilter</code>
     *
     * @param filter 要被包装的文件过滤器
     * @return 一个实现IOFileFilter接口的过滤器
     */
    public static IOFileFilter asFileFilter(FileFilter filter) {
        return FileFilterUtils.asFileFilter(filter);
    }

    /**
     * 将<code>FilenameFilter</code>包装为<code>IOFileFilter</code>
     *
     * @param filter 要被包装的文件过滤器
     * @return 一个实现IOFileFilter接口的过滤器
     */
    public static IOFileFilter asFileFilter(FilenameFilter filter) {
        return FileFilterUtils.asFileFilter(filter);
    }

    // -----------------------------------------------------------------------

    /**
     * 返回一个只接受最后修改时间大于指定毫秒数的文件过滤器
     *
     * @param cutoff 毫秒数
     * @return 满足最后修改时间大于指定毫秒数的文件过滤器
     */
    public static IOFileFilter ageFileFilter(long cutoff) {
        return FileFilterUtils.ageFileFilter(cutoff);
    }

    /**
     * 返回一个基于指定毫秒数的文件过滤器
     *
     * @param cutoff      毫秒数
     * @param acceptOlder true： 只接受更旧的文件, false：只接受更新的文件
     * @return 更新/旧的文件过滤器
     */
    public static IOFileFilter ageFileFilter(long cutoff, boolean acceptOlder) {
        return FileFilterUtils.ageFileFilter(cutoff, acceptOlder);
    }

    /**
     * 返回一个只接受最后修改时间大于指定日期的文件过滤器
     *
     * @param cutoffDate 日期
     * @return 满足最后修改时间大于指定日期的文件过滤器
     */
    public static IOFileFilter ageFileFilter(Date cutoffDate) {
        return FileFilterUtils.ageFileFilter(cutoffDate);
    }

    /**
     * 返回一个基于指定日期的文件过滤器
     *
     * @param cutoffDate  日期
     * @param acceptOlder true： 只接受更旧的文件, false：只接受更新的文件
     * @return 更新/旧的文件过滤器
     */
    public static IOFileFilter ageFileFilter(Date cutoffDate, boolean acceptOlder) {
        return FileFilterUtils.ageFileFilter(cutoffDate, acceptOlder);
    }

    /**
     * 返回一个只接受最后修改时间大于指定文件最后修改时间的文件过滤器
     *
     * @param cutoffReference 要比较最后修改时间的文件
     * @return 只接受最后修改时间大于指定文件最后修改时间的文件过滤器
     */
    public static IOFileFilter ageFileFilter(File cutoffReference) {
        return FileFilterUtils.ageFileFilter(cutoffReference);
    }

    /**
     * 返回一个基于指定文件的最后修改日期的文件过滤器
     *
     * @param cutoffReference 要比较最后修改时间的文件
     * @param acceptOlder     true： 只接受更旧的文件, false：只接受更新的文件
     * @return 更新/旧的文件过滤器
     */
    public static IOFileFilter ageFileFilter(File cutoffReference, boolean acceptOlder) {
        return FileFilterUtils.ageFileFilter(cutoffReference, acceptOlder);
    }

    // -----------------------------------------------------------------------

    /**
     * 返回一个只接受文件大小大于指定大小的文件过滤器
     *
     * @param threshold 待比较的文件大小
     * @return 只接受文件大小大于指定大小的文件过滤器
     */
    public static IOFileFilter sizeFileFilter(long threshold) {
        return FileFilterUtils.sizeFileFilter(threshold);
    }

    /**
     * 返回一个基于文件大小的文件过滤器
     *
     * @param threshold    待比较的文件大小
     * @param acceptLarger true： 只接受更大的文件, false：只接受更小的文件
     * @return 更大/小的文件过滤器
     */
    public static IOFileFilter sizeFileFilter(long threshold, boolean acceptLarger) {
        return FileFilterUtils.sizeFileFilter(threshold, acceptLarger);
    }

    /**
     * 返回一个只接受文件大小在指定大小范围内的文件过滤器
     *
     * @param minSizeInclusive 最小文件大小 (包括)
     * @param maxSizeInclusive 最大文件大小 (包括)
     * @return 只接受文件大小在指定大小范围内的文件过滤器
     */
    public static IOFileFilter sizeRangeFileFilter(long minSizeInclusive, long maxSizeInclusive) {
        return FileFilterUtils.sizeRangeFileFilter(minSizeInclusive, maxSizeInclusive);
    }

    /**
     * 返回一个只接受以指定魔数开头的文件过滤器
     *
     * @param magicNumber 匹配文件开头的魔数（字节序列）
     * @return an IOFileFilter 只接受以指定魔数开头的文件过滤器
     * @throws IllegalArgumentException 如果参数为null或空串
     */
    public static IOFileFilter magicNumberFileFilter(String magicNumber) {
        return FileFilterUtils.magicNumberFileFilter(magicNumber);
    }

    /**
     * 返回一个只接受在指定位置出现指定魔数的文件过滤器
     * Returns a filter that accepts files that contains the provided magic
     * number at a specified offset within the file.
     *
     * @param magicNumber 在指定位置匹配的魔数（字节序列）
     * @param offset      要匹配的文件位置偏移
     * @return 只接受在指定位置出现指定魔数的文件过滤器
     * @throws IllegalArgumentException 如果参数为null或空串， 或偏移量为负数
     */
    public static IOFileFilter magicNumberFileFilter(String magicNumber, long offset) {
        return FileFilterUtils.magicNumberFileFilter(magicNumber, offset);
    }

    /**
     * 返回一个只接受以指定魔数开头的文件过滤器
     *
     * @param magicNumber 匹配文件开头的魔数（字节序列）
     * @return an IOFileFilter 只接受以指定魔数开头的文件过滤器
     * @throws IllegalArgumentException 如果参数为null或长度为0
     */
    public static IOFileFilter magicNumberFileFilter(byte[] magicNumber) {
        return FileFilterUtils.magicNumberFileFilter(magicNumber);
    }

    /**
     * 返回一个只接受在指定位置出现指定魔数的文件过滤器
     *
     * @param magicNumber 在指定位置匹配的魔数（字节序列）
     * @param offset      要匹配的文件位置偏移
     * @return 只接受在指定位置出现指定魔数的文件过滤器
     * @throws IllegalArgumentException 如果参数为null或长度为0， 或偏移量为负数
     */
    public static IOFileFilter magicNumberFileFilter(byte[] magicNumber, long offset) {
        return FileFilterUtils.magicNumberFileFilter(magicNumber, offset);
    }

    // -----------------------------------------------------------------------

    /**
     * 装饰过滤器，使其忽略CVS目录。
     *
     * @param filter 要装饰的过滤器, null表示不受限制的过滤器
     * @return 装饰后的过滤器, 不会为null
     */
    public static IOFileFilter makeCVSAware(IOFileFilter filter) {
        return FileFilterUtils.makeCVSAware(filter);
    }

    /**
     * 装饰过滤器，使其忽略SVN目录。
     *
     * @param filter 要装饰的过滤器, null表示不受限制的过滤器
     * @return 装饰后的过滤器, 不会为null
     */
    public static IOFileFilter makeSVNAware(IOFileFilter filter) {
        return FileFilterUtils.makeSVNAware(filter);
    }

    // -----------------------------------------------------------------------

    /**
     * 装饰一个过滤器，以便它只能接受目录(而不能接受文件)
     *
     * @param filter 要装饰的过滤器, null表示不受限制的过滤器
     * @return 装饰后的过滤器, 不会为null
     */
    public static IOFileFilter makeDirectoryOnly(IOFileFilter filter) {
        return FileFilterUtils.makeDirectoryOnly(filter);
    }

    /**
     * 装饰一个过滤器，以便它只能接受文件(而不能接受目录)
     *
     * @param filter 要装饰的过滤器, null表示不受限制的过滤器
     * @return 装饰后的过滤器, 不会为null
     */
    public static IOFileFilter makeFileOnly(IOFileFilter filter) {
        return FileFilterUtils.makeFileOnly(filter);
    }

    // ----------------------------------------------------------------------------
    // 封装org.apache.commons.io.filefilter.FileFilterUtils
    // ----------------------------------------------------------------------------

}
