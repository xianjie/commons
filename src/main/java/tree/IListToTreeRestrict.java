package tree;

import java.io.Serializable;

/**
 * 列表转为树结构的约束的接口
 */
public interface IListToTreeRestrict<T> extends Serializable {

    /**
     * 获取当前结点的id
     *
     * @return 当前结点的id
     */
    T getId();

    /**
     * 获取父结点的id
     *
     * @return 父结点的id
     */
    T getParentId();
}
