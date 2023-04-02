package com.greedy.matcat.main.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.greedy.matcat.MatcatApplication;
import com.greedy.matcat.main.dto.CategoryDTO;
import com.greedy.matcat.main.dto.ProductDTO;

@SpringBootTest
@ContextConfiguration(classes = {MatcatApplication.class})
public class ProductMapperTests {

	@Autowired
	private ProductMapper menuMapper;
	
	@Test
	public void MapperInterfaceTest() {
		
		assertNotNull(menuMapper);
	
	}
	



	
	
	
	
	
	
	
}
