package bean;

import java.io.Serializable;

/**
 * 持久化实体对象接口
 */
public interface IEntity<T> extends Serializable {

    /**
     * 取得主键
     *
     * @return 主键值
     */
    T getId();

    /**
     * 设置主键
     */
    void setId(T id);

}
