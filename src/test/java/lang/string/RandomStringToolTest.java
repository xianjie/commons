/**
 *
 */
package lang.string;

import org.junit.Test;

public class RandomStringToolTest {

    @Test
    public void demo() {
        System.out.println("uuid: " + RandomStringTool.uuid());
        System.out.println("randomLong:  " + RandomStringTool.randomLong());
        System.out.println("randomBase62:" + RandomStringTool.randomBase62(7));
    }

}
