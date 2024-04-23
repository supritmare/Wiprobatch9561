package com.abc.abcmart.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.abc.abcmart.entity.Product;
import com.abc.abcmart.exceptions.ProductNotFoundException;
import com.abc.abcmart.repository.ProductRepository;

@SpringBootTest
public class ProductServiceTest {
	
	@InjectMocks
	private ProductServiceImpl productService;
	
	@Mock
	private ProductRepository productRepository;
	
	@Test
	public void testGetProductDetails() {
		
		Product product = new Product();
		product.setProductId(111);
		product.setProductName("ABCProduct");
		product.setProductPrice(100);
		product.setMfd(LocalDate.of(2000, 10, 10));
		product.setCategory("mobbi");
		
		when(productRepository.findById(111)).thenReturn(Optional.of(product));
		
		Product actualObj = productService.getProductById(111);
		assertEquals("ABCProduct",actualObj.getProductName());
		
	}
	
	@Test
	public void testGetProductDetailsException() {
		
		when(productRepository.findById(100)).thenThrow(new ProductNotFoundException("Product not existing with id: 100 "));
	    
		assertThrows(ProductNotFoundException.class, ()->productService.getProductById(100));
	}
	
	@Test
	public void testGetAllProducts() {
		
		Product product = new Product();
		product.setProductId(111);
		product.setProductName("ABCPhone");
		product.setProductPrice(100);
		product.setMfd(LocalDate.of(2000, 10, 10));
		product.setCategory("mobbi");
		
		Product product1 = new Product();
		product1.setProductId(222);
		product1.setProductName("ABCPLaptop");
		product1.setProductPrice(1000);
		product1.setMfd(LocalDate.of(2000, 11, 10));
		product1.setCategory("Laptop");
		
		Product product2 = new Product();
		product2.setProductId(333);
		product2.setProductName("ABCTv");
		product2.setProductPrice(10000);
		product2.setMfd(LocalDate.of(2000, 8, 20));
		product2.setCategory("TV");
		
		List<Product> products = new ArrayList<>();
		products.add(product);
		products.add(product1);
		products.add(product2);
		
		when(productRepository.findAll()).thenReturn(products);
		
		List<Product> productList = productService.getAllProducts();
		
		assertEquals(3,productList.size());
	}
	
	@Test
	void testDeleteProduct() {
		
		Product product = new Product();
		product.setProductId(111);
		product.setProductName("ABCProduct");
		product.setProductPrice(100);
		product.setMfd(LocalDate.of(2000, 10, 10));
		product.setCategory("mobbi");
		
		when(productRepository.findById(111)).thenReturn(Optional.of(product));
		
		doNothing().when(productRepository).delete(product);
		
		productService.deleteProduct(111);
		
		verify(productRepository,times(1)).findById(111);
		verify(productRepository,times(1)).delete(product);
	}
	
	@Test
	void testDeleteProductException() {
		
		Product product = new Product();
		product.setProductId(100);
		product.setProductName("ABCProduct");
		product.setProductPrice(100);
		product.setMfd(LocalDate.of(2000, 10, 10));
		product.setCategory("mobbi");
		
        when(productRepository.findById(111)).thenThrow(new ProductNotFoundException("Product not existing with id: 111 ")); 
	 
		assertThrows(ProductNotFoundException.class, ()->productService.getProductById(111));
		
		verify(productRepository,times(0)).delete(product);    
	}
}
