package lang.string;

/**
 * 字符串转义工具类
 */
public class StringEscapeTool {

    private StringEscapeTool() {
    }

    // ----------------------------------------------------------------------------
    // 封装org.apache.commons.lang3.StringEscapeUtils
    // ----------------------------------------------------------------------------

    // Java and JavaScript
    // ----------------------------------------------------------------------------

    /**
     * 使用java的字符串规则将指定的字符串转义
     * 能够正确的处理引号和控制字符(tab, 反斜杠, 回车, 走纸换页等)
     * 一个tab字符将被转义为{@code '\\'} 和 {@code 't'}.
     * java字符串和javascript字符串的不同仅在于, 在javascript中, 单引号和斜杠将被转义.
     * 例如:
     * <pre>
     * 输入字符串: He didn't say, "Stop!"
     * 输出字符串: He didn't say, \"Stop!\"
     * </pre>
     *
     * @param input 要转义的字符串, 可以为 null
     * @return 转义后的字符串, {@code null}将返回{@code null}
     */
    public static String escapeJava(String input) {
        return org.apache.commons.lang3.StringEscapeUtils.escapeJava(input);
    }

    /**
     * 使用EcmaScript的字符串规则将指定的字符串转义
     * 能够正确的处理引号和控制字符(tab, 反斜杠, 回车, 走纸换页等)
     * 一个tab字符将被转义为{@code '\\'} 和 {@code 't'}.
     * java字符串和javascript字符串的不同仅在于, 在javascript中, 单引号和斜杠将被转义.
     * EcmaScript最出名的方言为JavaScript 和 ActionScript.
     * Example:
     * <pre>
     * 输入字符串: He didn't say, "Stop!"
     * 输出字符串: He didn't say, \"Stop!\"
     * </pre>
     *
     * @param input 要转义的字符串, 可以为 null
     * @return 转义后的字符串, {@code null}将返回{@code null}
     */
    public static String escapeEcmaScript(String input) {
        return org.apache.commons.lang3.StringEscapeUtils.escapeEcmaScript(input);
    }

    /**
     * 解码指定的转义后java字符串
     * 例如, 将{@code '\'} 和 {@code 'n'}转为换行符, 除非在{@code '\'}前有另一个{@code '\'}
     *
     * @param input 要解码转义的字符串, 可以为 null
     * @return 一个新的解码过的字符串 {@code String}, {@code null}将返回{@code null}
     */
    public static String unescapeJava(String input) {
        return org.apache.commons.lang3.StringEscapeUtils.unescapeJava(input);
    }

    /**
     * 解码指定的转义后EcmaScript字符串
     * 例如, 将{@code '\'} 和 {@code 'n'}转为换行符, 除非在{@code '\'}前有另一个{@code '\'}
     *
     * @param input 要解码转义的字符串, 可以为 null
     * @return 一个新的解码过的字符串 {@code String}, {@code null}将返回{@code null}
     */
    public static String unescapeEcmaScript(String input) {
        return org.apache.commons.lang3.StringEscapeUtils.unescapeEcmaScript(input);
    }

    // HTML and XML
    // -----------------------------------------------------------------------------

    /**
     * 使用HTML的实体将指定的字符串转义
     * 例如:
     * <code>"bread" & "butter"</code>
     * </p>
     * 转义成:
     * <code>&amp;quot;bread&amp;quot; &amp;amp; &amp;quot;butter&amp;quot;</code>.
     * 支持所有HTML 4.0实体.请注意平时使用的(&amp;apos;) 不是一个逻辑实体, 所以它是不被支持的.
     *
     * @param input 要转义的{@code String}, 可以为 null
     * @return 一个新的转义后的字符串, {@code null}将返回{@code null}
     */
    public static String escapeHtml4(String input) {
        return org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(input);
    }

    /**
     * 使用HTML的实体将指定的字符串转义
     * 只支持HTML 3.0的实体
     *
     * @param input 要转义的{@code String} , 可以为null
     * @return 一个新的转义后的字符串, {@code null}将返回{@code null}
     */
    public static String escapeHtml3(String input) {
        return org.apache.commons.lang3.StringEscapeUtils.escapeHtml3(input);
    }

    // --------------------------------------------------------------------------

    /**
     * 解码指定的转义后HTML 4.0实体字符串
     * 例如, 字符串 "&amp;lt;Fran&amp;ccedil;ais&amp;gt;" 将转为 "&lt;Fran&ccedil;ais&gt;"
     * 如果某个实体不被识别, 将保持原样, 并逐字插入到结果串中.如: "&amp;gt;&amp;zzzz;x" 将转为 "&gt;&amp;zzzz;x".
     *
     * @param input 要解码转义的字符串, 可以为 null
     * @return 一个新的解码过的字符串 {@code String}, {@code null}将返回{@code null}
     */
    public static String unescapeHtml4(String input) {
        return org.apache.commons.lang3.StringEscapeUtils.unescapeHtml4(input);
    }

    /**
     * 解码指定的转义后HTML 3.0实体字符串
     *
     * @param input 要解码转义的字符串, 可以为 null
     * @return 一个新的解码过的字符串 {@code String}, {@code null}将返回{@code null}
     */
    public static String unescapeHtml3(String input) {
        return org.apache.commons.lang3.StringEscapeUtils.unescapeHtml3(input);
    }

    // --------------------------------------------------------------------------

    /**
     * 使用XML的实体将指定的字符串转义
     * 例如: <tt>"bread" & "butter"</tt> => <tt>&amp;quot;bread&amp;quot; &amp;amp; &amp;quot;butter&amp;quot;</tt>.
     * 仅支持5个基本的XML实体 (gt, lt, quot, amp, apos). 不支持 DTDs 或外部实体.
     * 请注意大于0x7f的Unicode字符不会被转义. 如果想被转义, 可以这样做:
     * {@code StringEscapeUtils.ESCAPE_XML.with( NumericEntityEscaper.between(0x7f, Integer.MAX_VALUE) );}
     *
     * @param input 要转义的 {@code String} , 可以为null
     * @return 一个新的转义后的字符串, {@code null}将返回{@code null}
     */
    public static String escapeXml(String input) {
        return org.apache.commons.lang3.StringEscapeUtils.escapeXml(input);
    }

    // --------------------------------------------------------------------------

    /**
     * 解码指定的转义后XML实体字符串
     * 仅支持5个基本的XML实体 (gt, lt, quot, amp, apos). 不支持 DTDs 或外部实体.
     * 请注意: 数值的 \\u Unicode 编码不会被解码为对应的Unicode字符. 这在未来的版本可能解决.
     *
     * @param input 要解码转义的字符串, 可以为 null
     * @return 一个新的解码过的字符串 {@code String}, {@code null}将返回{@code null}
     */
    public static String unescapeXml(String input) {
        return org.apache.commons.lang3.StringEscapeUtils.unescapeXml(input);
    }

    // --------------------------------------------------------------------------

    /**
     * 如果需要, 将csv的列用双引号包起来
     * 如果值包含逗号, 换行或双引号, 字符串的值被被双引号包起来
     * 值中出现的双引号将用另一个额外的双引号转义
     * 如果值未包含逗号, 换行或双引号, 字符串将原样返回
     * 见 <a href="http://en.wikipedia.org/wiki/Comma-separated_values">Wikipedia</a>
     * 和 <a href="http://tools.ietf.org/html/rfc4180">RFC 4180</a>.
     *
     * @param input CSV列的值, 可以 null
     * @return 转义后的字符串, {@code null} 将返回 null
     */
    public static String escapeCsv(String input) {
        return org.apache.commons.lang3.StringEscapeUtils.escapeCsv(input);
    }

    /**
     * 解码转义过的csv列的值
     * 如果值以双引号包住, 并且包含逗号, 换行或双引号, 这样双引号将被移除
     * 两个连在一起的双引号将被去掉一个
     * 如果值没有以双引号包住, 或者有, 但是不包含含逗号, 换行或双引号, 这样字符串将原样返回
     * 见 <a href="http://en.wikipedia.org/wiki/Comma-separated_values">Wikipedia</a>
     * 和 <a href="http://tools.ietf.org/html/rfc4180">RFC 4180</a>.
     *
     * @param input CSV列的值, 可以 null
     * @return 解码过的CSV列的值
     */
    public static String unescapeCsv(String input) {
        return org.apache.commons.lang3.StringEscapeUtils.unescapeCsv(input);
    }

    // ----------------------------------------------------------------------------
    // 封装org.apache.commons.lang3.StringEscapeUtils
    // ----------------------------------------------------------------------------
}
