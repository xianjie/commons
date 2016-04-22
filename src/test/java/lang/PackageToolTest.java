package lang;

import lang.string.StringTool;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.util.Set;

public class PackageToolTest {

    @Test
    public void testGetClassesInPackage() {
        // in file
        String packageName = PackageTool.class.getPackage().getName();
        Set<Class<?>> classes = PackageTool.getClassesInPackage(packageName, true);
        assertTrue(classes.contains(PackageTool.class));
        assertTrue(classes.contains(StringTool.class));
        assertTrue(classes.contains(BooleanTool.class));

        // in jar
        classes = PackageTool.getClassesInPackage("org.apache.commons.lang3", true);
        assertTrue(classes.contains(org.apache.commons.lang3.StringUtils.class));
        assertTrue(classes.contains(org.apache.commons.lang3.BooleanUtils.class));
    }

    @Test
    public void testGetPackages() {
        // in file
        Set<String> packages = PackageTool.getPackages("org.joy.commons.*", true);
        assertTrue(packages.contains("org.joy.commons.lang"));
        assertTrue(packages.contains("org.joy.commons.enums"));

        // in jar using package pattern
        packages = PackageTool.getPackages("net.**.exception", true);
        assertTrue(packages.contains("net.sourceforge.pinyin4j.format.exception"));
    }

}
