package collections;

import junit.framework.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SetToolTest {

    @Test
    @SuppressWarnings("unchecked")
    public void newHashSet() {
        Set<?> hashSet = SetTool.newHashSet("1", 2, 3.0);
        assertTrue(hashSet instanceof HashSet);
        assertEquals(3, hashSet.size());
        assertTrue(hashSet.contains("1"));
        assertTrue(hashSet.contains(2));
        assertTrue(hashSet.contains(3.0));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void newLinkedSet() {
        Set<?> linkedHashSet = SetTool.newLinkedHashSet("1", 2, 3.0);
        assertTrue(linkedHashSet instanceof LinkedHashSet);
        assertEquals(3, linkedHashSet.size());
        Iterator<?> iterator = linkedHashSet.iterator();
        assertEquals("1", iterator.next());
        assertEquals(2, iterator.next());
        assertEquals(3.0, iterator.next());
    }

}
