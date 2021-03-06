package cn;

import exception.SystemException;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

/**
 * 拼音工具类
 */
public class PinYinTool {

    private PinYinTool() {
    }

    /**
     * 将中文转换为全拼
     *
     * @param cnStr 中文串
     * @return 全拼串
     */
    public static String getPinYin(String cnStr) {
        // 设置汉字拼音输出的格式
        HanyuPinyinOutputFormat fmt = new HanyuPinyinOutputFormat();
        fmt.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        fmt.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        fmt.setVCharType(HanyuPinyinVCharType.WITH_V);

        char[] charArray = cnStr.toCharArray();
        StringBuilder result = new StringBuilder();
        try {
            for (char ch : charArray) {
                if (Character.toString(ch).matches("[\\u4E00-\\u9FA5]+")) { // 判断是否为汉字字符
                    result.append(PinyinHelper.toHanyuPinyinStringArray(ch, fmt)[0]);
                } else {
                    result.append(Character.toString(ch));
                }
            }
        } catch (Exception e) {
            throw new SystemException(e, "汉字转换为全拼出错！");
        }
        return result.toString();
    }

    /**
     * 提取每个中文的首字母
     *
     * @param cnStr 中文串
     * @return 拼音首字母串
     */
    public static String getPinYinHeadChars(String cnStr) {
        HanyuPinyinOutputFormat fmt = new HanyuPinyinOutputFormat();
        fmt.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        fmt.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        fmt.setVCharType(HanyuPinyinVCharType.WITH_V);

        char[] charArray = cnStr.toCharArray();
        StringBuilder result = new StringBuilder();
        try {
            for (char ch : charArray) {
                // 提取汉字的首字母
                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(ch, fmt);
                if (pinyinArray != null) {
                    result.append(pinyinArray[0].charAt(0));
                } else {
                    result.append(ch);
                }
            }
        } catch (Exception e) {
            throw new SystemException(e, "汉字的首字母提取出错！");
        }
        return result.toString();
    }

}
