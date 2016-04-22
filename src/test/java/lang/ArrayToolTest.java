/**
 *
 */
package lang;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ArrayToolTest {

    @Test
    public void testMapToArrOfArr() {
        Map<Object, Object> map = new LinkedHashMap<Object, Object>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");
        Object[][] arrOfArr = ArrayTool.mapToArrOfArr(map);

        assertEquals(3, arrOfArr.length);
        assertEquals("k1", arrOfArr[0][0]);
        assertEquals("v1", arrOfArr[0][1]);
        assertEquals("k2", arrOfArr[1][0]);
        assertEquals("v2", arrOfArr[1][1]);
        assertEquals("k3", arrOfArr[2][0]);
        assertEquals("v3", arrOfArr[2][1]);
    }

}
