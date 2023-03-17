package com.practice9;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@SpringBootTest
class Practice9ApplicationTests {


	@Autowired
	private Environment environment;
	@Test
	void contextLoads() {
		System.out.println(Arrays.toString(environment.getActiveProfiles()));
	}

}
