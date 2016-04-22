package bean;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PairTest {

    @Test
    public void testPutToMap() {
        Pair<Integer, String> pair = new Pair<Integer, String>();
        // 使用setter
        pair.setLeft(1);
        pair.setRight("one");

        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(2, "two");
        pair.putToMap(map); // 将Pair对象放入Map对象
        // 使用构造器
        new Pair<Integer, String>(3, "three").putToMap(map);

        boolean result = map.size() == 3 && "one".equals(map.get(1)) && "two".equals(map.get(2))
                && "three".equals(map.get(3));
        assertTrue(result);
    }

    @Test
    public void testToString() {
        String string = new Pair<Integer, String>(3, "three").toString();
        assertEquals("(3, three)", string);
    }

}
