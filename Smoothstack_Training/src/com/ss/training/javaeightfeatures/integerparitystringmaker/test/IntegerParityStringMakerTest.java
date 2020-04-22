package com.ss.training.javaeightfeatures.integerparitystringmaker.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ss.training.javaeightfeatures.integerparitystringmaker.IntegerParityStringMaker;

/**
 * @author Justin O'Brien
 *
 */
public class IntegerParityStringMakerTest {
	@Test
	public void makeStringTest() {
		List<Integer> integers = new ArrayList<Integer>();
		String expected = "o25,e44,e108,o97,o3,o-45,e0,e44,o-39,e-174";
		integers.add(25);
		integers.add(44);
		integers.add(108);
		integers.add(97);
		integers.add(3);
		integers.add(-45);
		integers.add(0);
		integers.add(44);
		integers.add(-39);
		integers.add(-174);
		assertEquals(expected, IntegerParityStringMaker.makeString(integers));
	}
}
