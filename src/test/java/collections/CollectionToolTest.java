package collections;

import bean.IEntity;
import bean.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CollectionToolTest {

    private List<Object> list;
    private List<TestBean> testBeanlist;

    @Before
    public void setUp() throws Exception {
        list = new ArrayList<Object>();
        list.add(new Pair<String, Integer>("1", 1));
        list.add(new Pair<String, Integer>("2", 2));
        list.add(new TestBean(1, "name1"));
        list.add(new TestBean(2, null));
        list.add(new TestBean(1, "name1"));

        testBeanlist = new ArrayList<TestBean>(2);
        testBeanlist.add(new TestBean(1, "name1"));
        testBeanlist.add(new TestBean(2, "name2"));
    }

//	@Test
//	public void containsProperty() {
//		String propertyName = "first";
//		Object propertyValue = "2";
//
//		// 能找到
//		boolean result = CollectionTool.contains(list, propertyName, propertyValue);
//		assertTrue(result);
//
//		// 找不到
//		propertyValue = "would not find";
//		result = CollectionTool.contains(list, propertyName, propertyValue);
//		assertFalse(result);
//
//		// 容器为null
//		result = CollectionTool.contains(null, propertyName, propertyValue);
//		assertFalse(result);
//
//		// propertyName为null
//		try {
//			result = CollectionTool.contains(list, null, propertyValue);
//			assertFalse(true);
//		} catch (IllegalArgumentException e) {
//			assertTrue(true);
//		}
//
//		// propertyValue为null
//		propertyName = "name";
//		result = CollectionTool.contains(list, propertyName, null);
//		assertTrue(result);
//	}
//
//	@Test
//	public void containsProperties() {
//		Map<String, Object> map = new HashMap<String, Object>(2);
//		map.put("first", "1");
//		map.put("second", 1);
//
//		// 能找到
//		assertTrue(CollectionTool.contains(list, map));
//
//		// 找不到
//		map.put("second", "would not find");
//		assertFalse(CollectionTool.contains(list, map));
//
//		// 容器为null
//		assertFalse(CollectionTool.contains(null, map));
//
//		// map为null
//		try {
//			CollectionTool.contains(list, null);
//			assertFalse(true);
//		} catch (IllegalArgumentException e) {
//			assertTrue(true);
//		}
//	}
//
//	@Test
//	public void getMatchPropertyBean() {
//		String propertyName = "first";
//		Object propertyValue = "2";
//
//		// 能找到
//		Object matchBean = CollectionTool.getMatchBean(list, propertyName, propertyValue);
//		assertTrue(matchBean == list.get(1));
//
//		// 找不到
//		propertyValue = "would not find";
//		matchBean = CollectionTool.getMatchBean(list, propertyName, propertyValue);
//		assertNull(matchBean);
//
//		// 容器为null
//		matchBean = CollectionTool.getMatchBean(null, propertyName, propertyValue);
//		assertNull(matchBean);
//
//		// propertyName为null
//		try {
//			CollectionTool.getMatchBean(list, null, propertyValue);
//			assertFalse(true);
//		} catch (IllegalArgumentException e) {
//			assertTrue(true);
//		}
//
//		// propertyValue为null
//		propertyName = "name";
//		matchBean = CollectionTool.getMatchBean(list, propertyName, null);
//		assertTrue(matchBean == list.get(3));
//	}
//
//	@Test
//	public void getMatchPropertiesBean() {
//		Map<String, Object> map = new HashMap<String, Object>(2);
//		map.put("id", 1);
//		map.put("name", "name1");
//
//		// 能找到
//		Object matchBean = CollectionTool.getMatchBean(list, map);
//		assertTrue(matchBean == list.get(2));
//
//		// 找不到
//		map.put("name", "would not find");
//		matchBean = CollectionTool.getMatchBean(list, map);
//		assertNull(matchBean);
//
//		// 容器为null
//		matchBean = CollectionTool.getMatchBean(null, map);
//		assertNull(matchBean);
//
//		// map为null
//		try {
//			CollectionTool.getMatchBean(list, null);
//			assertFalse(true);
//		} catch (IllegalArgumentException e) {
//			assertTrue(true);
//		}
//
//		// map的value为null
//		map = new HashMap<String, Object>(1);
//		map.put("id", 2);
//		map.put("name", null);
//		matchBean = CollectionTool.getMatchBean(list, map);
//		assertTrue(matchBean == list.get(3));
//	}
//
//	@Test
//	public void getMatchPropertyBeans() {
//		String propertyName = "name";
//		Object propertyValue = "name1";
//
//		// 能找到
//		List<?> matchBeans = CollectionTool.getMatchBeans(list, propertyName, propertyValue);
//		assertTrue(matchBeans.size() >= 2);
//		if (matchBeans.size() >= 2) {
//			assertTrue(matchBeans.get(0) == list.get(2));
//			assertTrue(matchBeans.get(1) == list.get(4));
//		}
//
//		// 找不到
//		propertyValue = "would not find";
//		matchBeans = CollectionTool.getMatchBeans(list, propertyName, propertyValue);
//		assertTrue(matchBeans.isEmpty());
//
//		// 容器为null
//		matchBeans = CollectionTool.getMatchBeans(null, propertyName, propertyValue);
//		assertTrue(matchBeans.isEmpty());
//
//		// map为null
//		try {
//			CollectionTool.getMatchBeans(list, null, propertyValue);
//			assertFalse(true);
//		} catch (IllegalArgumentException e) {
//			assertTrue(true);
//		}
//
//		// map的value为null
//		propertyValue = null;
//		matchBeans = CollectionTool.getMatchBeans(list, propertyName, propertyValue);
//		if (matchBeans.isEmpty() == false) {
//			assertTrue(matchBeans.get(0) == list.get(3));
//		}
//	}
//
//	@Test
//	public void getMatchPropertiesBeans() {
//		Map<String, Object> map = new HashMap<String, Object>(2);
//		map.put("id", 1);
//		map.put("name", "name1");
//
//		// 能找到
//		List<?> matchBeans = CollectionTool.getMatchBeans(list, map);
//		assertTrue(matchBeans.size() >= 2);
//		if (matchBeans.size() >= 2) {
//			assertTrue(matchBeans.get(0) == list.get(2));
//			assertTrue(matchBeans.get(1) == list.get(4));
//		}
//
//		// 找不到
//		map.put("name", "would not find");
//		matchBeans = CollectionTool.getMatchBeans(list, map);
//		assertTrue(matchBeans.isEmpty());
//
//		// 容器为null
//		matchBeans = CollectionTool.getMatchBeans(null, map);
//		assertTrue(matchBeans.isEmpty());
//
//		// map为null
//		try {
//			CollectionTool.getMatchBeans(list, null);
//			assertFalse(true);
//		} catch (IllegalArgumentException e) {
//			assertTrue(true);
//		}
//
//		// map的value为null
//		map = new HashMap<String, Object>(1);
//		map.put("id", 2);
//		map.put("name", null);
//		matchBeans = CollectionTool.getMatchBeans(list, map);
//		if (matchBeans.isEmpty() == false) {
//			assertTrue(matchBeans.get(0) == list.get(3));
//		}
//	}
//
//	@Test
//	public void removeMatchPropertyBean() {
//		List<Object> list = BeanTool.deepClone((ArrayList<Object>) this.list);
//		String propertyName = "first";
//		Object propertyValue = "2";
//
//		// 能找到
//		int count = CollectionTool.removeMatchBean(list, propertyName, propertyValue);
//		assertTrue(count >= 1);
//		assertFalse(CollectionTool.contains(list, propertyName, propertyValue));
//
//		// 找不到
//		propertyValue = "would not find";
//		count = CollectionTool.removeMatchBean(list, propertyName, propertyValue);
//		assertTrue(count == 0);
//
//		// 容器为null
//		count = CollectionTool.removeMatchBean(null, propertyName, propertyValue);
//		assertTrue(count == 0);
//
//		// propertyName为null
//		try {
//			CollectionTool.removeMatchBean(list, null, propertyValue);
//			assertFalse(true);
//		} catch (IllegalArgumentException e) {
//			assertTrue(true);
//		}
//
//		// propertyValue为null
//		propertyName = "name";
//		count = CollectionTool.removeMatchBean(list, propertyName, null);
//		assertTrue(count >= 1);
//		assertFalse(CollectionTool.contains(list, propertyName, null));
//	}
//
//	@Test
//	public void removeMatchPropertiesBean() {
//		List<Object> list = BeanTool.deepClone((ArrayList<Object>) this.list);
//		Map<String, Object> map = new HashMap<String, Object>(2);
//		map.put("id", 1);
//		map.put("name", "name1");
//
//		// 能找到
//		int count = CollectionTool.removeMatchBean(list, map);
//		assertTrue(count >= 1);
//		assertFalse(CollectionTool.contains(list, map));
//
//		// 找不到
//		map.put("name", "would not find");
//		count = CollectionTool.removeMatchBean(list, map);
//		assertTrue(count == 0);
//
//		// 容器为null
//		count = CollectionTool.removeMatchBean(null, map);
//		assertTrue(count == 0);
//
//		// map为null
//		try {
//			CollectionTool.removeMatchBean(list, null);
//			assertFalse(true);
//		} catch (IllegalArgumentException e) {
//			assertTrue(true);
//		}
//
//		// map的value为null
//		map = new HashMap<String, Object>(1);
//		map.put("id", 2);
//		map.put("name", null);
//		count = CollectionTool.removeMatchBean(list, map);
//		assertTrue(count >= 1);
//		assertFalse(CollectionTool.contains(list, map));
//	}

    @Test
    public void toEntityMap() {
        Map<Object, TestBean> entityMap = CollectionTool.toEntityMap(testBeanlist, "name");
        assertEquals(2, entityMap.size());
        assertTrue(testBeanlist.get(0) == entityMap.get("name1"));
        assertTrue(testBeanlist.get(1) == entityMap.get("name2"));

        // 容器为null
        entityMap = CollectionTool.toEntityMap(null, "name");
        assertTrue(entityMap.isEmpty());

        // propertyName为null
        try {
            CollectionTool.toEntityMap(testBeanlist, null);
            assertFalse(true);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        // propertyName非法
        try {
            CollectionTool.toEntityMap(testBeanlist, "invalidProperty");
            assertFalse(true);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void convertElementPropertyToString() {
        TestBean bean1 = new TestBean();
        bean1.setId(1);
        TestBean bean2 = new TestBean();
        bean2.setId(2);

        List<Object> list = new ArrayList<Object>();
        list.add(bean1);
        list.add(bean2);

        assertEquals("1,2", CollectionTool.extractToString(list, "id", ","));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void convertElementPropertyToList() {
        TestBean bean1 = new TestBean();
        bean1.setId(1);
        TestBean bean2 = new TestBean();
        bean2.setId(2);

        List<Object> list = new ArrayList<Object>();
        list.add(bean1);
        list.add(bean2);
        List<Integer> result = CollectionTool.extractToList(list, "id");
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).intValue());
    }

    @Test
    public void convertCollectionToString() {
        List<String> list = new ArrayList<String>();
        list.add("aa");
        list.add("bb");
        String result = CollectionTool.convertToString(list, ",");
        assertEquals("aa,bb", result);

        result = CollectionTool.convertToString(list, "<li>", "</li>");
        assertEquals("<li>aa</li><li>bb</li>", result);
    }

    public static class TestBean implements IEntity<Integer> {

        private static final long serialVersionUID = -6135354531806554241L;
        private Integer id;
        private String name;

        public TestBean() {
        }

        public TestBean(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }

}
