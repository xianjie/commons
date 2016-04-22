package tree;

import support.ICallback;

import java.util.List;

/**
 * 树操作工具类
 */
public class TreeTool {

    private TreeTool() {
    }

    /**
     * 将列表结构转为树结构
     *
     * @param <T>        id值类型
     * @param <E>        树结点的实际对象类型
     * @param objectList 结点对象列表
     * @return List<树根结点>
     */
    public static <T, E extends IListToTreeRestrict<T>> List<TreeNode<E>> convertListToTree(List<E> objectList) {
        return ListToTreeConverter.convert(objectList);
    }

    /**
     * 深度遍历树，并执行回调
     *
     * @param nodes    树结点列表
     * @param callback 回调
     * @param <T>      树结点的实际对象类型
     * @param <R>      回调返回值类型
     */
    public static <T, R> void depthTraverse(List<TreeNode<T>> nodes, ICallback<TreeNode<T>, R> callback) {
        for (TreeNode<T> node : nodes) {
            depth(node, callback);
        }
    }

    private static <T, R> void depth(TreeNode<T> node, ICallback<TreeNode<T>, R> callback) {
        callback.execute(node);
        List<TreeNode<T>> children = node.getChildren();
        for (TreeNode<T> subNode : children) {
            depth(subNode, callback);
        }
    }

}
