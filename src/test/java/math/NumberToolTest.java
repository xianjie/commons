package math;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class NumberToolTest {
	
	@Test
	public void testToInt() {
		assertEquals(1, NumberTool.toInt(null, 1));
		assertEquals(1, NumberTool.toInt("", 1));
		assertEquals(1, NumberTool.toInt(" ", 1));
		assertEquals(1, NumberTool.toInt("str", 1));
		assertEquals(1, NumberTool.toInt("1234567899999999999", 1));
		assertEquals(1, NumberTool.toInt("1.23", 1));
		assertEquals(123, NumberTool.toInt("123", 1));
		assertEquals(-123, NumberTool.toInt("-123", 1));
	}

	@Test
	public void testToLong() {
		assertEquals(1L, NumberTool.toLong(null, 1L));
		assertEquals(1L, NumberTool.toLong("", 1L));
		assertEquals(1L, NumberTool.toLong(" ", 1L));
		assertEquals(1L, NumberTool.toLong("str", 1L));
		assertEquals(1L, NumberTool.toLong("12345678999999999999999999", 1L));
		assertEquals(1234567899999999999L, NumberTool.toLong("1234567899999999999", 1L));
		assertEquals(1L, NumberTool.toLong("1.23", 1L));
		assertEquals(123L, NumberTool.toLong("123", 1L));
		assertEquals(-123L, NumberTool.toLong("-123", 1L));
	}

	@Test
	public void testToDouble() {
		assertEquals(1.0, NumberTool.toDouble(null, 1.0));
		assertEquals(1.0, NumberTool.toDouble("", 1.0));
		assertEquals(1.0, NumberTool.toDouble(" ", 1.0));
		assertEquals(1.0, NumberTool.toDouble("str", 1.0));
		assertEquals(1.2345679E25, NumberTool.toDouble("12345678999999999999999999", 1.0));
		assertEquals(1.23, NumberTool.toDouble("1.23", 1.0));
		assertEquals(123.0, NumberTool.toDouble("123", 1.0));
		assertEquals(-123.0, NumberTool.toDouble("-123", 1.0));
	}

}
