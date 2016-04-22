/**
 *
 */
package lang.string;

import org.junit.Test;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class I18nToolTest {

    @Test
    public void testBundle() {
        String baseName = "conf/i18n/language";
        Locale locale = Locale.ENGLISH;

        ResourceBundle bundle = I18nTool.bundle(baseName, locale);

        assertNotNull(bundle);
    }

    @Test
    public void testChangeLocale() {
        I18nTool.changeLocale(Locale.ENGLISH);
        assertEquals(Locale.ENGLISH, Locale.getDefault());
    }

    @Test
    public void testChangeLocaleOfStrStr() {
        I18nTool.changeLocale("zh", "CN");
        assertEquals("zh", Locale.getDefault().getLanguage());
        assertEquals("CN", Locale.getDefault().getCountry());
    }


    @Test
    public void testGetLocalStr() {
        String i18nKey = "OK";
        I18nTool.changeLocale(Locale.CHINA);
        String localStr = I18nTool.getLocalStr(i18nKey);
        assertEquals("确定", localStr);

        I18nTool.changeLocale(Locale.ENGLISH);
        localStr = I18nTool.getLocalStr(i18nKey);
        assertEquals("OK", localStr);
    }

}
