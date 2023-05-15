package com.ArtGalleryManagement.Backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ArtGalleryManagement.Backend.Entity.Product;
import com.ArtGalleryManagement.Backend.Models.ShelfCurrentLoansResponse;
import com.ArtGalleryManagement.Backend.Service.ProductService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/product")
public class ProductController {
	@Autowired
     ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/secure/currentloans")
	public List<ShelfCurrentLoansResponse> currentLoans() throws Exception{
		String userEmail="mohit@gmail.com";
		return productService.currentLoans(userEmail);
	}
	
	@GetMapping("/secure/ischeckout/byuser")
	public Boolean checkoutProductByUser(@RequestParam int productId) throws Exception{
	    String userEmail="mohit@gamil.com";
	    return productService.checkoutProductByUser(userEmail, productId);
	}
	
	@GetMapping("/secure/currentloans/count")
	public int currentLoansCount() {
		String userEmail="mohit@gmail.com";
		return productService.currentLoanCount(userEmail);
	}
    
	@PutMapping("/secure/checkout")
	public Product checkoutProduct(@RequestParam int productId)throws Exception{
		String userEmail="mohit@gmail.com";
		return productService.checkoutProduct(userEmail, productId);
	}
	
	@PutMapping("/secure/return")
	public void returnProduct(@RequestParam int productId) throws Exception {
		String userEmail="mohit@gmail.com";
		productService.returnProduct(userEmail, productId);
	}
	
    
    
}
