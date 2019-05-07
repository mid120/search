package com.liw.elasticsearch;

import com.liw.elasticsearch.controller.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchApplicationTests {

	@Autowired
	UserController userController;

	@Test
	public void contextLoads() {
	}

	@Test
	public void test01(){
		userController.add(null);
	}

}
