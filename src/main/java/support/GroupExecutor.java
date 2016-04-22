package support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 分组执行器
 * 应用场景如：Hibernate限定超过1000个元素的in查询，这时可以用分组执行器进行分组执行
 * </p>
 * <p>
 * <pre>
 * 	Collection<Object> elements = ...;
 * 	int groupSize = ...;
 *  new GroupExecutor<Object>(elems, groupSize) {
 *
 *     protected void groupExecute(List<Object> subList) {
 *        ... // 如分组执行更新
 *     }
 *
 *  }.execute();
 * </pre>
 */
public abstract class GroupExecutor<E> {

    private final int groupSize; // 每组大小
    private final Collection<E> elements; // 元素集合

    /**
     * 构造器，分组大小默认为1000
     *
     * @param elements 所有要分组的元素集合
     */
    public GroupExecutor(Collection<E> elements) {
        this(elements, 1000);
    }

    /**
     * 构造器
     *
     * @param elements  所有要分组的元素集合
     * @param groupSize 分组大小
     */
    public GroupExecutor(Collection<E> elements, int groupSize) {
        this.elements = elements;
        this.groupSize = groupSize;
    }

    /**
     * 执行操作
     */
    public void execute() {
        int size = elements.size();
        int groupCount = (int) Math.ceil(size / (double) groupSize);
        List<E> elemList = new ArrayList<E>(elements);
        for (int index = 0; index < groupCount; index++) {
            int from = index * groupSize;
            int end = (index == groupCount - 1 ? elemList.size() : from + groupSize);
            List<E> subList = elemList.subList(from, end);
            groupExecute(subList);
        }
    }

    /**
     * 分组执行
     *
     * @param subList 分组元素列表
     */
    protected abstract void groupExecute(List<E> subList);

}
