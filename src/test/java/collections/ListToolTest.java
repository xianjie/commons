package collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ListToolTest {


    @Test
    @SuppressWarnings("unchecked")
    public void newArrayList() {
        List<?> arrayList = ListTool.newArrayList("1", 2, 3.0);
        assertTrue(arrayList instanceof ArrayList);
        assertEquals(3, arrayList.size());
        assertEquals("1", arrayList.get(0));
        assertEquals(2, arrayList.get(1));
        assertEquals(3.0, arrayList.get(2));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void newLinkedList() {
        List<?> linkedList = ListTool.newLinkedList("1", 2, 3.0);
        assertTrue(linkedList instanceof LinkedList);
        assertEquals(3, linkedList.size());
        assertEquals("1", linkedList.get(0));
        assertEquals(2, linkedList.get(1));
        assertEquals(3.0, linkedList.get(2));
    }

}
