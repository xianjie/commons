package bean;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import java.util.*;

public class BeanToolTest {

    private Person person;

    @Before
    public void setUp() throws Exception {
        Address address = new Address();
        address.setProvince("hunan");
        address.setCity("changsha");
        address.setStreet("wuyilu");
        address.setZipcode("410000");

        List<String> goods = new ArrayList<String>();
        goods.add("sporting");
        goods.add("singing");
        goods.add("dancing");

        Map<String, String> contact = new LinkedHashMap<String, String>();
        contact.put("student", "Tom");
        contact.put("teacher", "Lucy");

        person = new Person();
        person.setId("id");
        person.setName("Mike");
        person.setSex("male");
        person.setAge(25);
        person.setBirthday(new Date(60528873600000L));
        person.setAddress(address);
        person.setGoods(goods);
        person.setContact(contact);
    }

    @Test
    public void testCloneBean() {
        Person p = BeanTool.shallowClone(person);

        assertEquals(person, p);
        assertTrue(person.getAddress() == p.getAddress()); // 证明是浅克隆
    }

    @Test
    public void testDeepClone() {
        Person p = BeanTool.deepClone(person);

        assertEquals(person, p);
        assertTrue(person.getAddress() != p.getAddress()); // 证明是深克隆
    }

    @Test
    public void testCopyProperties() {
        Person p = new Person();

        BeanTool.copyProperties(person, p);

        assertEquals(person, p);
        assertTrue(person.getAddress() == p.getAddress()); // 证明是浅克隆
    }

    @Test
    public void testCopyProperty() {
        BeanTool.copyProperty(person, "address.zipcode", "361000");

        assertEquals("361000", person.getAddress().getZipcode());
    }

    @Test
    public void testCopyPropertiesWithoutCast() {
        BeanTool.copyProperty(person, "address.zipcode", "361000");

        assertEquals("361000", person.getAddress().getZipcode());
    }

    @Test
    public void testExtract() {
        Map<String, Object> map = BeanTool.extract(person);

        assertEquals(person.getAge(), map.get("age"));
        assertEquals(person.getName(), map.get("name"));
        assertEquals(person.getSex(), map.get("sex"));
        assertTrue(person.getAddress() == map.get("address"));
        assertTrue(person.getBirthday() == map.get("birthday"));
        assertTrue(person.getContact() == map.get("contact"));
        assertTrue(person.getGoods() == map.get("goods"));
    }

    @Test
    public void testGetProperty() {
        assertEquals(person.getAge(), BeanTool.getProperty(person, "age"));
        assertEquals(person.getGoods().get(0), BeanTool.getProperty(person, "goods[0]"));
        assertEquals(person.getContact().get("student"), BeanTool.getProperty(person, "contact(student)"));
        assertEquals(person.getAddress().getProvince(), BeanTool.getProperty(person, "address.province"));
    }

    @Test
    @SuppressWarnings("rawtypes")
    public void testCopyPropertiesByMap() {
        Class<Pair> destClass = Pair.class;
        Pair<String, List<String>> srcObj = new Pair<String, List<String>>();
        srcObj.setLeft("left");
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        srcObj.setRight(list);

        Map<String, String> propertyMap = new HashMap<String, String>();
        propertyMap.put("left", "right"); // left属性的值拷贝到right
        propertyMap.put("right[0]", "left"); // right属性的值拷贝到left

        Pair dest = BeanTool.copyProperties(destClass, srcObj, propertyMap);

        assertEquals(srcObj.getLeft(), dest.getRight());
        assertEquals(srcObj.getRight().get(0), dest.getLeft());
    }

    @Test
    public void testCopyPropertiesToClassInstance() {
        Person p = BeanTool.copyProperties(person, Person.class);

        assertEquals(person, p);
        assertTrue(person.getAddress() == p.getAddress()); // 证明是浅克隆
    }

    @Test
    public void testCopyPropertiesExcludeId() {
        Person dest = new Person();

        BeanTool.copyPropertiesExcludeId(person, dest);

        assertEquals(person.getAge(), dest.getAge());
        assertTrue(person.getAddress() == dest.getAddress());
        assertNull(dest.getId());
    }

    @Test
    public void testcopyPropertiesExclude() {
        Person p = new Person();

        BeanTool.copyPropertiesExclude(person, p, "age", "address");

        assertEquals(person.getId(), p.getId());
        assertEquals(0, p.getAge());
        assertNull(p.getAddress());
        assertTrue(person.getGoods() == p.getGoods()); // 浅克隆
    }

    @Test
    public void testResetPropertiesExcludeId() {
        Person p = BeanTool.shallowClone(person);

        BeanTool.resetPropertiesExcludeId(p);

        assertNull(p.getName());
        assertNull(p.getAddress());
        assertEquals(person.getId(), p.getId());
    }

}
