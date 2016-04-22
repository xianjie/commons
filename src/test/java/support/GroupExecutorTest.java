package support;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GroupExecutorTest {

    /**
     * Test method for
     * {@link GroupExecutor#execute()}.
     */
    @Test
    public void testExecute() {
        List<Integer> elements = new ArrayList<Integer>();
        for (int i = 1; i <= 50; i++) {
            elements.add(i);
        }

        final StringBuilder sb = new StringBuilder();
        new GroupExecutor<Integer>(elements, 10) {

            @Override
            protected void groupExecute(List<Integer> subList) {
                if (subList.isEmpty() == false) {
                    sb.append(subList.get(0)).append(",");
                }
            }

        }.execute();

        assertEquals("1,11,21,31,41,", sb.toString());
    }
}
