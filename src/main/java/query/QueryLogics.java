package query;

import lang.string.StringTool;
import log.Log;
import log.LogFactory;
import query.sort.Direction;
import query.sort.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询逻辑集
 */
public class QueryLogics implements java.io.Serializable {

    /**
     * 页面提交的排序参数名前缀
     */
    public static final String KEY_PREFIX_ORDER_BY = "_joy_key__order_by_";
    /**
     * 页面提交的排序参数值前缀
     */
    public static final String KEY_PREFIX_ORDER = "_joy_key__order_value_";
    /**
     * 页面提交的默认排序参数值前缀
     */
    public static final String KEY_PREFIX_ORDER_DEFAULT = "_joy_key__order_default_";

    private Paging paging; // 分页对象
    private List<QueryLogic> conditions = new ArrayList<QueryLogic>(0); // 查询条件列表
    private Map<String, String> orderMap = new HashMap<String, String>(0); // Map<排序属性名，排序顺序>
    protected static final Log logger = LogFactory.getLog(QueryLogic.class);

    public void addCondition(String property, QueryLogicOperator operator, Object value) {
        if (StringTool.isBlank(property)) {
            throw new IllegalArgumentException("字段名或属性名不为空！");
        }
        if (operator == null) {
            throw new IllegalArgumentException("查询逻辑操作符不为空！");
        }
        conditions.add(new QueryLogic(property, operator, value));
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public List<QueryLogic> getConditions() {
        return conditions;
    }

    public void setConditions(List<QueryLogic> conditions) {
        this.conditions = conditions;
    }

    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<Order>(orderMap.size());
        for (String key : orderMap.keySet()) {
            String order = orderMap.get(key);
            try {
                Direction direction = Direction.fromString(order);
                orders.add(new Order(key, direction));
            } catch (Exception e) {
                logger.error(e, e.getMessage());
            }
        }
        return orders;
    }

    public Order[] getOrderArray() {
        List<Order> orders = getOrders();
        return orders.toArray(new Order[orders.size()]);
    }

    public Map<String, String> getOrderMap() {
        return orderMap;
    }

    public void setOrderMap(Map<String, String> orderMap) {
        this.orderMap = orderMap;
    }

}
