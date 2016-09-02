package com.chapter1.servlet;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class SystemTest {

	@Test
	public void test() {
		System.out.println(System.getProperty("user.dir"));
		System.out.println(System.getProperty("java.version"));
		System.out.println(System.getProperty("java.vender"));
		System.out.println(System.getProperty("java.home"));
	}

}
