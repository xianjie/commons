package support;

import org.junit.Test;
import tree.IListToTreeRestrict;
import tree.ListToTreeConverter;
import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ListToTreeConverterTest {


    /**
     * Test method for {@link ListToTreeConverter#convert(List)}.
     */
    @Test
    public void testConvert() {
        List<TestRecord> list = new ArrayList<TestRecord>();
        list.add(new TestRecord("10", null, "根结点10"));
        list.add(new TestRecord("11", "10", "10的子结点11"));
        list.add(new TestRecord("12", "10", "10的子结点12"));
        list.add(new TestRecord("20", null, "根结点20"));
        list.add(new TestRecord("21", "20", "20的子结点21"));

        List<TreeNode<TestRecord>> treeList = ListToTreeConverter.convert(list);

        boolean result = treeList.size() == 2;
        TreeNode<TestRecord> treeNode10 = treeList.get(0);
        result = result && "10".equals(treeNode10.getObject().getId());
        result = result && treeNode10.getChildren().size() == 2;
        TreeNode<TestRecord> treeNode11 = treeNode10.getChildren().get(0);
        result = result && "11".equals(treeNode11.getObject().getId());
        TreeNode<TestRecord> treeNode12 = treeNode10.getChildren().get(1);
        result = result && "12".equals(treeNode12.getObject().getId());
        TreeNode<TestRecord> treeNode20 = treeList.get(1);
        result = result && "20".equals(treeNode20.getObject().getId());
        result = result && treeNode20.getChildren().size() == 1;
        TreeNode<TestRecord> treeNode21 = treeNode20.getChildren().get(0);
        result = result && "21".equals(treeNode21.getObject().getId());
        assertTrue(result);
    }

    protected class TestRecord implements IListToTreeRestrict<String> {

        private static final long serialVersionUID = -3832151541461087421L;
        private String id;
        private String parentId;
        private String name;

        public TestRecord() {
        }

        public TestRecord(String id, String parentId, String name) {
            super();
            this.id = id;
            this.parentId = parentId;
            this.name = name;
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public String getParentId() {
            return parentId;
        }

        public String getName() {
            return name;
        }

    }

}
