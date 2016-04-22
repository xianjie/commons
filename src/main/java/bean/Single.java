package bean;

/**
 * 单个属性的对象
 * 应用场景如：想从内部类带回基本类型的值
 * <pre>
 * final Single<String> single = new Single<String>();
 * new ICommand() {
 *      @Override
 * 		public void execute() {
 * 			String str = "str";
 * 			single.setValue(str);
 * 		}
 * 	};
 * 	String value = single.getValue();
 * </pre>
 */
public class Single<T> {

    private T value; // 元素值

    public Single() {
    }

    public Single(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (value == null) {
            return "";
        }
        return value.toString();
    }

}
