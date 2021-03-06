package tree;

import log.Log;
import log.LogFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 列表到树结构的转换器
 */
public class ListToTreeConverter {

    protected static final Log logger = LogFactory.getLog(ListToTreeConverter.class);

    private ListToTreeConverter() {
    }

    /**
     * 将列表结构转为树结构
     *
     * @param objectList 结点对象列表
     * @return List<树根结点>
     */
    public static <T, E extends IListToTreeRestrict<T>> List<TreeNode<E>> convert(List<E> objectList) {
        Map<T, TreeNode<E>> treeNodeMap = new HashMap(objectList.size());
        for (E object : objectList) {
            treeNodeMap.put(object.getId(), new TreeNode<E>(object));
        }

        List<TreeNode<E>> treeNodeList = new ArrayList();
        for (E obj : objectList) {
            TreeNode<E> node = treeNodeMap.get(obj.getId());
            T pId = obj.getParentId();
            if (pId == null || "".equals(pId)) { // 根
                treeNodeList.add(node);
            } else {
                TreeNode<E> pNode = treeNodeMap.get(pId);
                if (pNode != null) { // 存在父结点
                    node.setParentObject(pNode.getObject());
                    pNode.getChildren().add(node);
                } else {
                    logger.error("结点#" + node.getObject().getId() + "的父结点#" + pId + "不存在！");
                }
            }
        }
        return treeNodeList;
    }

}
