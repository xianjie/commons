package lang.string;

import log.Log;
import log.LogFactory;
import scanner.classpath.ClassPathScanner;
import scanner.support.Resource;

import java.util.*;

/**
 * 国际化工具
 */
public class I18nTool {

    private static final String DEFAULT_BASE_PATH = "conf/i18n/";
    private static final Map<String, ResourceBundle> bundleMap = new LinkedHashMap<String, ResourceBundle>();
    private static final Log logger = LogFactory.getLog(I18nTool.class);

    static {
        Resource[] resources = ClassPathScanner.scanForResources(DEFAULT_BASE_PATH, "", ".properties");
        Set<String> baseNames = new LinkedHashSet<String>();
        for (Resource resource : resources) {
            String filename = resource.getFilename();
            String baseName = filename.replaceFirst("\\.properties$", "").replaceFirst("_[a-z]{2}_[A-Z]{2}$", "");
            baseNames.add(DEFAULT_BASE_PATH + baseName);
        }
        for (String baseName : baseNames) {
            bundle(baseName);
        }
    }

    /**
     * 绑定本地运行环境和资源文件
     *
     * @param baseName 资源文件的基本名称(扣掉后缀)
     * @param locale   本地运行环境
     */
    public static synchronized ResourceBundle bundle(String baseName, Locale locale) {
        ResourceBundle bundle = null;
        try {
            bundle = ResourceBundle.getBundle(baseName, locale);
            bundleMap.put(baseName, bundle);
        } catch (Exception e) {
            logger.error(e, "绑定本地运行环境和资源文件时出错!");
        }
        return bundle;
    }

    /**
     * 绑定本地运行环境和资源文件
     *
     * @param baseName 资源文件的基本名称(扣掉后缀)
     * @return
     */
    public static ResourceBundle bundle(String baseName) {
        return bundle(baseName, Locale.getDefault());
    }

    /**
     * 根据多国语言Key, 取得本地环境的字符串
     *
     * @param i18nKey 多国语言Key
     * @return 返回本地环境的字符串, 如果传NULL或trim后为空串则返回空串
     */
    public static String getLocalStr(String i18nKey) {
        if (StringTool.isNotBlank(i18nKey)) {
            for (ResourceBundle bundle : bundleMap.values()) {
                if (bundle.containsKey(i18nKey)) {
                    return bundle.getString(i18nKey);
                }
            }
        }
        return "";
    }

    /**
     * 更改系统运行的环境
     *
     * @param locale {@link Locale}
     */
    public static void changeLocale(Locale locale) {
        Set<String> baseNameSet = bundleMap.keySet();
        Locale.setDefault(locale);
        for (String baseName : baseNameSet) {
            bundle(baseName, locale);
        }
    }

    /**
     * 更改系统运行的环境
     *
     * @param languageName 语言的名字(小写的两字母 ISO-639 代码)
     * @param countryName  国家的名字(大写的两字母 ISO-3166 代码)
     */
    public static void changeLocale(String languageName, String countryName) {
        changeLocale(new Locale(languageName, countryName));
    }

}
