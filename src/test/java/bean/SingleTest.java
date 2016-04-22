package bean;

import org.junit.Test;
import support.ICommand;

import static org.junit.Assert.assertEquals;

public class SingleTest {

    @Test
    public void testToString() {
        final Single<String> single = new Single<String>();
        new ICommand() {

            private static final long serialVersionUID = 2176028612149873088L;

            @Override
            public void execute() {
                String str = "str";
                single.setValue(str);
            }
        }.execute();
        String value = single.getValue();
        System.out.println("value: " + value);  // 使用内部类方法中设置的值
        assertEquals("str", single.toString());
    }

}
