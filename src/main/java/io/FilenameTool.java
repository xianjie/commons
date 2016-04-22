package io;

import enums.CaseSensitivity;
import exception.SystemException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;

import java.io.File;
import java.util.Collection;

/**
 * 文件名工具类
 */
public class FilenameTool {

    private FilenameTool() {
    }

    // ----------------------------------------------------------------------------
    // 封装org.apache.commons.io.FilenameUtils
    // ----------------------------------------------------------------------------

    /**
     * 标准化文件路径，移除两个和一个点的部分
     * 该方法将路径标准化。输入的分隔符有可能是Unix或Windows操作系统的格式， 输出的路径的分隔符将取决于当前的操作系统。
     * 结尾斜杠将被保留。双斜杠将被合并为单个斜杠（但处理UNC名称）。 一个圆点路径段将被删除。双点会导致这条路径段和前一个要删除的。
     * 如果双点没有父路径段，则返回null。
     * 输出内容除了分隔符外，在Unix和Windows操作系统上将是一致的
     * <p>
     * <pre>
     * /foo//               -->   /foo/
     * /foo/./              -->   /foo/
     * /foo/../bar          -->   /bar
     * /foo/../bar/         -->   /bar/
     * /foo/../bar/../baz   -->   /baz
     * //foo//./bar         -->   /foo/bar
     * /../                 -->   null
     * ../foo               -->   null
     * foo/bar/..           -->   foo/
     * foo/../../bar        -->   null
     * foo/../bar           -->   bar
     * //server/foo/../bar  -->   //server/bar
     * //server/../bar      -->   null
     * C:\foo\..\bar        -->   C:\bar
     * C:\..\bar            -->   null
     * ~/foo/../bar/        -->   ~/bar/
     * ~/../bar             -->   null
     * </pre>
     * <p>
     * (注意：输出的路径的分隔符将取决于当前的操作系统)
     *
     * @param filename 要标准化的文件路径，null将返回null
     * @return 标准化后的文件路径，无效路径将返回null
     */
    public static String normalize(String filename) {
        return FilenameUtils.normalize(filename);
    }

    /**
     * 标准化文件路径，移除两个和一个点的部分。能够指定要使用的分隔符
     * 该方法将路径标准化。输入的分隔符有可能是Unix或Windows操作系统的格式， 输出的路径的分隔符由参数指定。
     * 结尾斜杠将被保留。双斜杠将被合并为单个斜杠（但处理UNC名称）。 一个圆点路径段将被删除。双点会导致这条路径段和前一个要删除的。
     * 如果双点没有父路径段，则返回null。
     * 输出内容除了分隔符外，在Unix和Windows操作系统上将是一致的
     * <pre>
     * /foo//               -->   /foo/
     * /foo/./              -->   /foo/
     * /foo/../bar          -->   /bar
     * /foo/../bar/         -->   /bar/
     * /foo/../bar/../baz   -->   /baz
     * //foo//./bar         -->   /foo/bar
     * /../                 -->   null
     * ../foo               -->   null
     * foo/bar/..           -->   foo/
     * foo/../../bar        -->   null
     * foo/../bar           -->   bar
     * //server/foo/../bar  -->   //server/bar
     * //server/../bar      -->   null
     * C:\foo\..\bar        -->   C:\bar
     * C:\..\bar            -->   null
     * ~/foo/../bar/        -->   ~/bar/
     * ~/../bar             -->   null
     * </pre>
     * <p>
     * 输出的路径在Unix和Windows操作系统下将一致
     *
     * @param filename      要标准化的文件路径，null将返回null
     * @param unixSeparator true: 是否使用unix格式的分隔符。false: 使用windows格式的分隔符
     * @return 标准化后的文件路径，无效路径将返回null
     */
    public static String normalize(String filename, boolean unixSeparator) {
        return FilenameUtils.normalize(filename, unixSeparator);
    }

    // -----------------------------------------------------------------------

    /**
     * 标准化文件路径，移除两个和一个点的部分，并移除结尾的任何分隔符
     * 该方法将路径标准化。输入的分隔符有可能是Unix或Windows操作系统的格式， 输出的路径的分隔符将取决于当前的操作系统。
     * 结尾斜杠将被保留。双斜杠将被合并为单个斜杠（但处理UNC名称）。 一个圆点路径段将被删除。双点会导致这条路径段和前一个要删除的。
     * 如果双点没有父路径段，则返回null。
     * 输出内容除了分隔符外，在Unix和Windows操作系统上将是一致的
     * <pre>
     * /foo//               -->   /foo
     * /foo/./              -->   /foo
     * /foo/../bar          -->   /bar
     * /foo/../bar/         -->   /bar
     * /foo/../bar/../baz   -->   /baz
     * //foo//./bar         -->   /foo/bar
     * /../                 -->   null
     * ../foo               -->   null
     * foo/bar/..           -->   foo
     * foo/../../bar        -->   null
     * foo/../bar           -->   bar
     * //server/foo/../bar  -->   //server/bar
     * //server/../bar      -->   null
     * C:\foo\..\bar        -->   C:\bar
     * C:\..\bar            -->   null
     * ~/foo/../bar/        -->   ~/bar
     * ~/../bar             -->   null
     * </pre>
     * (注意：输出的路径的分隔符将取决于当前的操作系统)
     *
     * @param filename 要标准化的文件路径，null将返回null
     * @return 标准化后的文件路径，无效路径将返回null
     */
    public static String normalizeNoEndSeparator(String filename) {
        return FilenameUtils.normalizeNoEndSeparator(filename);
    }

    /**
     * 标准化文件路径，移除两个和一个点的部分，并移除结尾的任何分隔符. 能够指定要使用的分隔符
     * 该方法将路径标准化。输入的分隔符有可能是Unix或Windows操作系统的格式， 输出的路径的分隔符由参数指定。
     * 结尾斜杠将被保留。双斜杠将被合并为单个斜杠（但处理UNC名称）。 一个圆点路径段将被删除。双点会导致这条路径段和前一个要删除的。
     * 如果双点没有父路径段，则返回null。
     * 输出内容除了分隔符外，在Unix和Windows操作系统上将是一致的
     * <pre>
     * /foo//               -->   /foo
     * /foo/./              -->   /foo
     * /foo/../bar          -->   /bar
     * /foo/../bar/         -->   /bar
     * /foo/../bar/../baz   -->   /baz
     * //foo//./bar         -->   /foo/bar
     * /../                 -->   null
     * ../foo               -->   null
     * foo/bar/..           -->   foo
     * foo/../../bar        -->   null
     * foo/../bar           -->   bar
     * //server/foo/../bar  -->   //server/bar
     * //server/../bar      -->   null
     * C:\foo\..\bar        -->   C:\bar
     * C:\..\bar            -->   null
     * ~/foo/../bar/        -->   ~/bar
     * ~/../bar             -->   null
     * </pre>
     *
     * @param filename      要标准化的文件路径，null将返回null
     * @param unixSeparator true: 是否使用unix格式的分隔符。false: 使用windows格式的分隔符
     * @return 标准化后的文件路径，无效路径将返回null
     */
    public static String normalizeNoEndSeparator(String filename, boolean unixSeparator) {
        return FilenameUtils.normalizeNoEndSeparator(filename, unixSeparator);
    }

    // -----------------------------------------------------------------------

    /**
     * 将一个子路径连接到一个基础路径，使用标准命令行样式规则。
     * 第一个参数为基础路径，第二个参数为要连接的路径。返回的路径总是通过 {@link #normalize(String)}标准化的， 这样
     * <code>..</code>能够被正确地处理。
     * 如果要连接的路径是一个绝对路径(有绝对路径的前缀)，那么它将被标准化后返回。 否则，该路径将被连接到基础路径后，将标准化后返回。
     * 输出内容除了分隔符外，在Unix和Windows操作系统上将是一致的
     * <pre>
     * /foo/ + bar          -->   /foo/bar
     * /foo + bar           -->   /foo/bar
     * /foo + /bar          -->   /bar
     * /foo + C:/bar        -->   C:/bar
     * /foo + C:bar         -->   C:bar (*)
     * /foo/a/ + ../bar     -->   foo/bar
     * /foo/ + ../../bar    -->   null
     * /foo/ + /bar         -->   /bar
     * /foo/.. + /bar       -->   /bar
     * /foo + bar/c.txt     -->   /foo/bar/c.txt
     * /foo/c.txt + bar     -->   /foo/c.txt/bar (!)
     * </pre>
     * <p>
     * 注意： (*)使用该方法时，带有Windows操作系统盘符的相对路径是不可靠的。
     * (!)第一个参数必须是一个路径，如果它以一个文件名结尾，该文件名将被连接到结果中。 如果这是个问题，请对第一个参数使用
     * {@link #getFullPath(String)}方法。
     *
     * @param basePath          要被连接的基础路径, 总是被当作路径
     * @param fullFilenameToAdd 要连接到基础路径的文件名（或路径）
     * @return 连接后的路径, 无效路径将返回null
     */
    public static String concat(String basePath, String fullFilenameToAdd) {
        return FilenameUtils.concat(basePath, fullFilenameToAdd);
    }

    /**
     * 检查父目录是否包含指定的子目录或文件
     * 文件名将被标准化。
     * 边缘情况:
     * <p>
     * 父目录不能为null: 为null将抛出根异常为IllegalArgumentException的JoyException异常</li>
     * 一个目录不会包含它自己: 将返回false</li>
     * 文件或子目录为null将返回false</li>
     *
     * @param canonicalParent 父目录
     * @param canonicalChild  文件或子目录
     * @return true: 父目录包含指定的子目录或文件，否则返回false
     * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
     *                         IllegalArgumentException 对被调用方法的包装异常 <br>
     *                         IOException 如果请求的方法不能通过反射访问
     */
    public static boolean directoryContains(final String canonicalParent, final String canonicalChild) {
        try {
            return FilenameUtils.directoryContains(canonicalParent, canonicalChild);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    // -----------------------------------------------------------------------

    /**
     * 将所有分隔符转换为unix格式的分隔符
     *
     * @param path 待处理的路径, 为null将返回null
     * @return 更新后的路径
     */
    public static String separatorsToUnix(String path) {
        return FilenameUtils.separatorsToUnix(path);
    }

    /**
     * 将所有分隔符转换为Windows格式的分隔符
     *
     * @param path 待处理的路径, 为null将返回null
     * @return 更新后的路径
     */
    public static String separatorsToWindows(String path) {
        return FilenameUtils.separatorsToWindows(path);
    }

    /**
     * 将所有分隔符转换为当前系统格式的分隔符
     *
     * @param path 待处理的路径, 为null将返回null
     * @return 更新后的路径
     */
    public static String separatorsToSystem(String path) {
        return FilenameUtils.separatorsToSystem(path);
    }

    // -----------------------------------------------------------------------

    /**
     * 返回路径前缀，如<code>C:/</code> 或 <code>~/</code>
     * 该方法将以Unix或Windows的格式来处理文件。
     * 前缀的长度包括完整路径中的第一个斜杠(如果适用的话)。 因此，有可能返回的长度大于输入路径的长度。
     * <pre>
     * Windows:
     * a\b\c.txt           --> ""          --> 相对路径
     * \a\b\c.txt          --> "\"         --> 当前盘符绝对路径
     * C:a\b\c.txt         --> "C:"        --> 盘符相对路径
     * C:\a\b\c.txt        --> "C:\"       --> 绝对路径
     * \\server\a\b\c.txt  --> "\\server\" --> UNC
     *
     * Unix:
     * a/b/c.txt           --> ""          --> 相对路径
     * /a/b/c.txt          --> "/"         --> 绝对路径
     * ~/a/b/c.txt         --> "~/"        --> 当前用户目录
     * ~                   --> "~/"        --> 前用户目录 (后面有带斜杠)
     * ~user/a/b/c.txt     --> "~user/"    --> 用户目录
     * ~user               --> "~user/"    --> 用户目录 (后面有带斜杠)
     * </pre>
     * <p>
     * <p>
     * 输出结果在不同操作系统上将是一致的。如：无论Unix还是Windows，都不管前缀的匹配
     *
     * @param filename 要查找前缀的路径, null将返回-1
     * @return 路径前缀的长度, 路径无效或null将返回-1
     */
    public static int getPrefixLength(String filename) {
        return FilenameUtils.getPrefixLength(filename);
    }

    /**
     * 返回最后一个目录分隔符的下标
     * 该方法将以Unix或Windows的格式来处理文件。 最后一个斜杠或反斜杠的下标将被返回。
     * 输出结果在不同操作系统上将是一致的。
     *
     * @param filename 待查找的路径, null将返回-1
     * @return 最后一个目录分隔符的下标, 找不到或路径为null将返回-1
     */
    public static int indexOfLastSeparator(String filename) {
        return FilenameUtils.indexOfLastSeparator(filename);
    }

    /**
     * 返回最后一个扩展分隔符(一个.)的下标
     * 该方法同样检查在最后一个点后有没有目录分隔符。执行该动作将使用 {@link #indexOfLastSeparator(String)}
     * 方法，它将以Unix或Windows的格式来处理文件。
     * 输出结果在不同操作系统上将是一致的。
     *
     * @param filename 待查找的路径, null将返回-1
     * @return 最后一个扩展分隔符的下标, 找不到或路径为null将返回-1
     */
    public static int indexOfExtension(String filename) {
        return FilenameUtils.indexOfExtension(filename);
    }

    // -----------------------------------------------------------------------

    /**
     * 从一个完整的路径返回它的前缀，如<code>C:/</code> 或 <code>~/</code>.
     * 该方法将以Unix或Windows的格式来处理文件。 完整路径中如果有第一个斜杠将被包含在返回的前缀中
     * <pre>
     * Windows:
     * a\b\c.txt           --> ""          --> 相对路径
     * \a\b\c.txt          --> "\"         --> 当前盘符绝对路径
     * C:a\b\c.txt         --> "C:"        --> 盘符相对路径
     * C:\a\b\c.txt        --> "C:\"       --> 绝对路径
     * \\server\a\b\c.txt  --> "\\server\" --> UNC
     *
     * Unix:
     * a/b/c.txt           --> ""          --> 相对路径
     * /a/b/c.txt          --> "/"         --> 绝对路径
     * ~/a/b/c.txt         --> "~/"        --> 当前用户目录
     * ~                   --> "~/"        --> 当前用户目录 (后面带有斜杠)
     * //	 * ~user/a/b/c.txt     --> "~user/"    --> 用户目录
     * ~user               --> "~user/"    --> 用户目录 (后面带有斜杠)
     * </pre>
     * <p>
     * <p>
     * 输出结果在不同操作系统上将是一致的。如：无论Unix还是Windows，都不管前缀的匹配
     *
     * @param filename 待查找的路径, null将返回null
     * @return 路径的前缀, 路径无效或为null将返回null
     */
    public static String getPrefix(String filename) {
        return FilenameUtils.getPrefix(filename);
    }

    /**
     * 返回完整路径的不带前缀的路径
     * 该方法将以Unix或Windows的格式来处理文件。 该方法完全基于文本，它返回最后一个斜杠或反斜杠前(包括)的文本
     * <pre>
     * C:\a\b\c.txt --> a\b\
     * ~/a/b/c.txt  --> a/b/
     * a.txt        --> ""
     * a/b/c        --> a/b/
     * a/b/c/       --> a/b/c/
     * </pre>
     * 输出结果在不同操作系统上将是一致的。
     * 该方法丢弃结果中的前缀。要保留前缀，请查看{@link #getFullPath(String)}方法。
     *
     * @param filename 待查找的路径, null将返回null
     * @return 不带前缀的路径, 没有将返回空串，路径无效或为null将返回null
     */
    public static String getPath(String filename) {
        return FilenameUtils.getPath(filename);
    }

    /**
     * 返回完整路径的不带前缀的路径, 它同样不包括末尾的目录分隔符
     * 该方法将以Unix或Windows的格式来处理文件。 该方法完全基于文本，它返回最后一个斜杠或反斜杠前(包括)的文本
     * <pre>
     * C:\a\b\c.txt --> a\b
     * ~/a/b/c.txt  --> a/b
     * a.txt        --> ""
     * a/b/c        --> a/b
     * a/b/c/       --> a/b/c
     * </pre>
     * 输出结果在不同操作系统上将是一致的。
     * 该方法丢弃结果中的前缀。要保留前缀，请查看{@link #getFullPathNoEndSeparator(String)}方法。
     *
     * @param filename 待查找的路径, null将返回null
     * @return 不带前缀的路径, 没有将返回空串，路径无效或为null将返回null
     */
    public static String getPathNoEndSeparator(String filename) {
        return FilenameUtils.getPathNoEndSeparator(filename);
    }

    /**
     * 返回指定文件的完整路径，它包含前缀和路径
     * 该方法将以Unix或Windows的格式来处理文件。 该方法完全基于文本，它返回最后一个斜杠或反斜杠前(包括)的文本
     * <pre>
     * C:\a\b\c.txt --> C:\a\b\
     * ~/a/b/c.txt  --> ~/a/b/
     * a.txt        --> ""
     * a/b/c        --> a/b/
     * a/b/c/       --> a/b/c/
     * C:           --> C:
     * C:\          --> C:\
     * ~            --> ~/
     * ~/           --> ~/
     * ~user        --> ~user/
     * ~user/       --> ~user/
     * </pre>
     * 输出结果在不同操作系统上将是一致的。
     *
     * @param filename 待查找的路径, null将返回null
     * @return 不带前缀的路径, 没有将返回空串，路径无效或为null将返回null
     */
    public static String getFullPath(String filename) {
        return FilenameUtils.getFullPath(filename);
    }

    /**
     * 返回指定文件的完整路径，它包含前缀和路径, 并且不包括末尾的目录分隔符
     * 该方法将以Unix或Windows的格式来处理文件。 该方法完全基于文本，它返回最后一个斜杠或反斜杠前(包括)的文本
     * <pre>
     * C:\a\b\c.txt --> C:\a\b
     * ~/a/b/c.txt  --> ~/a/b
     * a.txt        --> ""
     * a/b/c        --> a/b
     * a/b/c/       --> a/b/c
     * C:           --> C:
     * C:\          --> C:\
     * ~            --> ~
     * ~/           --> ~
     * ~user        --> ~user
     * ~user/       --> ~user
     * </pre>
     * 输出结果在不同操作系统上将是一致的。
     *
     * @param filename 待查找的路径, null将返回null
     * @return 带前缀的路径, 没有将返回空串，路径无效或为null将返回null
     */
    public static String getFullPathNoEndSeparator(String filename) {
        return FilenameUtils.getFullPathNoEndSeparator(filename);
    }

    /**
     * 返回完整文件名去掉路径后的名称
     * 该方法将以Unix或Windows的格式来处理文件。 该方法完全基于文本，它返回最后一个斜杠或反斜杠前(包括)的文本
     * <pre>
     * a/b/c.txt --> c.txt
     * a.txt     --> a.txt
     * a/b/c     --> c
     * a/b/c/    --> ""
     * </pre>
     * 输出结果在不同操作系统上将是一致的。
     *
     * @param filename 待查找的路径, null将返回null
     * @return 去掉路径的文件名, 没有将返回空串，路径无效或为null将返回null
     */
    public static String getName(String filename) {
        return FilenameUtils.getName(filename);
    }

    /**
     * 返回完整文件名去掉路径和扩展名后的名称
     * 该方法将以Unix或Windows的格式来处理文件。 最后一个斜杠或反斜杠后，最后一个点之前的文本将被返回。
     * <pre>
     * a/b/c.txt --> c
     * a.txt     --> a
     * a/b/c     --> c
     * a/b/c/    --> ""
     * </pre>
     * 输出结果在不同操作系统上将是一致的。
     * </p>
     *
     * @param filename 待查找的路径, null将返回null
     * @return 去掉路径和扩展名后的文件名, 没有将返回空串，路径无效或为null将返回null
     */
    public static String getBaseName(String filename) {
        return FilenameUtils.getBaseName(filename);
    }

    /**
     * 返回文件名的扩展名
     * 该方法返回文件名中点符后的文本。该点后面必须没有目录分隔符。
     * <pre>
     * foo.txt      --> "txt"
     * a/b/c.jpg    --> "jpg"
     * a/b.txt/c    --> ""
     * a/b/c        --> ""
     * </pre>
     * 输出结果在不同操作系统上将是一致的。
     *
     * @param filename 要获取扩展名的文件名
     * @return 文件的扩展名，没有将返回空串，路径为null将返回null
     */
    public static String getExtension(String filename) {
        return FilenameUtils.getExtension(filename);
    }

    // -----------------------------------------------------------------------

    /**
     * 移除扩展名
     * 该方法返回文件名中点符前的文本。该点后面必须没有目录分隔符。
     * <pre>
     * foo.txt    --> foo
     * a\b\c.jpg  --> a\b\c
     * a\b\c      --> a\b\c
     * a.b\c      --> a.b\c
     * </pre>
     * 输出结果在不同操作系统上将是一致的。
     *
     * @param filename 待查找的路径, null将返回null
     * @return 去掉扩展名后的文件名, 路径为null将返回null
     */
    public static String removeExtension(String filename) {
        return FilenameUtils.removeExtension(filename);
    }

    // -----------------------------------------------------------------------

    /**
     * 检查两个文件名是否精确相等。
     * 该方法除了比较操作之外没有对两个文件名作任何处理， 仅仅是一个空安全、大小写敏感的equals操作。
     *
     * @param filename1 要比较的第一个文件名, 可以为null
     * @param filename2 要比较的第二个文件名, 可以为null
     * @return true：如果两个文件名相等， 都为null将作相等
     */
    public static boolean equals(String filename1, String filename2) {
        return FilenameUtils.equals(filename1, filename2);
    }

    /**
     * 检查两个文件名是否相等, 依赖于操作系统的大小写规则。
     * 该方法除了比较操作之外没有对两个文件名作任何处理。 Unix下为大小写敏感的比较，Windows则是大小写不敏感的比较。
     *
     * @param filename1 要比较的第一个文件名, 可以为null
     * @param filename2 要比较的第二个文件名, 可以为null
     * @return true：如果两个文件名相等， 都为null将作相等
     */
    public static boolean equalsOnSystem(String filename1, String filename2) {
        return FilenameUtils.equalsOnSystem(filename1, filename2);
    }

    // -----------------------------------------------------------------------

    /**
     * 标准化两个文件名后，比较它们是否相等
     * 两个文件名都首先用{@link #normalize(String)}处理， 然后进行大小写敏感的比较操作，
     *
     * @param filename1 要比较的第一个文件名, 可以为null
     * @param filename2 要比较的第二个文件名, 可以为null
     * @return true：如果两个文件名相等， 都为null将作相等
     */
    public static boolean equalsNormalized(String filename1, String filename2) {
        return FilenameUtils.equalsNormalized(filename1, filename2);
    }

    /**
     * 标准化两个文件名后，比较它们是否相等, 依赖于操作系统的大小写规则。
     * 两个文件名都首先用{@link #normalize(String)}处理，然后进行比较操作，
     * Unix下为大小写敏感的比较，Windows则是大小写不敏感的比较。
     *
     * @param filename1 要比较的第一个文件名, 可以为null
     * @param filename2 要比较的第二个文件名, 可以为null
     * @return true：如果两个文件名相等， 都为null将作相等
     */
    public static boolean equalsNormalizedOnSystem(String filename1, String filename2) {
        return FilenameUtils.equalsNormalizedOnSystem(filename1, filename2);
    }

    /**
     * 检查是否两个文件名相等， 可以选择是否标准化和大小写比较规则。
     *
     * @param filename1       要比较的第一个文件名, 可以为null
     * @param filename2       要比较的第二个文件名, 可以为null
     * @param normalized      是否对文件名进行标准化
     * @param caseSensitivity 大小写比较规则, null将当作CaseSensitivity.SYSTEM
     * @return true：如果两个文件名相等， 都为null将作相等
     */
    public static boolean equals(String filename1, String filename2, boolean normalized, CaseSensitivity caseSensitivity) {
        IOCase ioCase = CaseSensitivity.adaptApacheIoCase(caseSensitivity);
        return FilenameUtils.equals(filename1, filename2, normalized, ioCase);
    }

    // -----------------------------------------------------------------------

    /**
     * 检查文件的扩展名是否为指定的扩展名
     * 该方法将文件名中“.”后面的文本部分当作扩展名。"."后不能有目录分隔符。 在所有平台上的扩展符检查都是大小写敏感的。
     *
     * @param filename  要检查的文件名, 可以为null
     * @param extension 扩展名, null或空串代表对没有扩展名的检查
     * @return true：如果文件名的扩展名为指定的扩展名
     */
    public static boolean isExtension(String filename, String extension) {
        return FilenameUtils.isExtension(filename, extension);
    }

    /**
     * 检查文件的扩展名是否为指定的扩展名数组中的一个
     * 该方法将文件名中“.”后面的文本部分当作扩展名。"."后不能有目录分隔符。 在所有平台上的扩展符检查都是大小写敏感的。
     *
     * @param filename   要检查的文件名, 可以为null
     * @param extensions 扩展名数组, null或空串代表对没有扩展名的检查
     * @return true：如果文件名的扩展名为指定的扩展名数组中的一个
     */
    public static boolean isExtension(String filename, String[] extensions) {
        return FilenameUtils.isExtension(filename, extensions);
    }

    /**
     * 检查文件的扩展名是否为指定的扩展名容器中的一个
     * 该方法将文件名中“.”后面的文本部分当作扩展名。"."后不能有目录分隔符。 在所有平台上的扩展符检查都是大小写敏感的。
     *
     * @param filename   要检查的文件名, 可以为null
     * @param extensions 扩展名容器, null或空串代表对没有扩展名的检查
     * @return true：如果文件名的扩展名为指定的扩展名容器中的一个
     */
    public static boolean isExtension(String filename, Collection<String> extensions) {
        return FilenameUtils.isExtension(filename, extensions);
    }

    // -----------------------------------------------------------------------

    /**
     * 判断文件名是匹配指定的可能带有通配符的字符串，大小写敏感
     * 使用'?' 和 '*'来代表单个和多个(0或多个)字符。这和Dos/Unix的命令行是一样的。 该检查总是大小写敏感的。
     * <pre>
     * wildcardMatch("c.txt", "*.txt")      --> true
     * wildcardMatch("c.txt", "*.jpg")      --> false
     * wildcardMatch("a/b/c.txt", "a/b/*")  --> true
     * wildcardMatch("c.txt", "*.???")      --> true
     * wildcardMatch("c.txt", "*.????")     --> false
     * </pre>
     * 注意："*?"序列在当前的字符串比较里不能正确工作。
     *
     * @param filename        待检查的文件名，可以为null
     * @param wildcardMatcher 带有通配符的字符串，可以为null
     * @return true：如果匹配，两者都null当作匹配
     */
    public static boolean wildcardMatch(String filename, String wildcardMatcher) {
        return FilenameUtils.wildcardMatch(filename, wildcardMatcher);
    }

    /**
     * 判断文件名是匹配指定的可能带有通配符的字符串，大小写敏感性依赖于当前系统
     * 使用'?' 和 '*'来代表单个和多个(0或多个)字符。这和Dos/Unix的命令行是一样的。
     * Unix下为大小写敏感的比较，Windows则是大小写不敏感的比较。
     * <pre>
     * wildcardMatch("c.txt", "*.txt")      --> true
     * wildcardMatch("c.txt", "*.jpg")      --> false
     * wildcardMatch("a/b/c.txt", "a/b/*")  --> true
     * wildcardMatch("c.txt", "*.???")      --> true
     * wildcardMatch("c.txt", "*.????")     --> false
     * </pre>
     * 注意："*?"序列在当前的字符串比较里不能正确工作。
     *
     * @param filename        待检查的文件名，可以为null
     * @param wildcardMatcher 带有通配符的字符串，可以为null
     * @return true：如果匹配，两者都null当作匹配
     */
    public static boolean wildcardMatchOnSystem(String filename, String wildcardMatcher) {
        return FilenameUtils.wildcardMatchOnSystem(filename, wildcardMatcher);
    }

    /**
     * 判断文件名是匹配指定的可能带有通配符的字符串，可以指定大小写敏感规则
     * 使用'?' 和 '*'来代表单个和多个(0或多个)字符。 注意："*?"序列在当前的字符串比较里不能正确工作。
     *
     * @param filename        待检查的文件名，可以为null
     * @param wildcardMatcher 带有通配符的字符串，可以为null
     * @param caseSensitivity 大小写比较规则, null将当作CaseSensitivity.SYSTEM
     * @return true：如果匹配，两者都null当作匹配
     */
    public static boolean wildcardMatch(String filename, String wildcardMatcher, CaseSensitivity caseSensitivity) {
        IOCase ioCase = CaseSensitivity.adaptApacheIoCase(caseSensitivity);
        return FilenameUtils.wildcardMatch(filename, wildcardMatcher, ioCase);
    }

    // ----------------------------------------------------------------------------
    // 封装org.apache.commons.io.FilenameUtils
    // ----------------------------------------------------------------------------

}
