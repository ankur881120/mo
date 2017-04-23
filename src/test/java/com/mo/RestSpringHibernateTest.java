package com.mo;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mo.businessObject.Product;
import com.mo.service.ProductService;
import com.mo.service.UserService;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	    "classpath:**/checkoutCounter-application-context-test.xml"
	})
public class RestSpringHibernateTest {

	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	static Product product=null;
	static List<Product> products=null;
	@BeforeClass
	public static void configureProduct(){
		
		product= new Product();
		product.setCategory("C");
		product.setName("Book");
		product.setDescription("Book");
		product.setUnitPrice(10);
	}
	
	@Test
	public void testProductSave(){
		products=productService.getAllProducts(null);
		productService.saveProduct(product);
		List<Product> productsAfterSave=productService.getAllProducts(null);
		assertEquals(products.size()+1, productsAfterSave.size());
	} 
	
}
