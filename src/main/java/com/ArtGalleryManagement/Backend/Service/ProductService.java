package com.ArtGalleryManagement.Backend.Service;

import java.util.List;

import com.ArtGalleryManagement.Backend.Entity.Product;
import com.ArtGalleryManagement.Backend.Models.ShelfCurrentLoansResponse;
public interface ProductService {

	public Product checkoutProduct(String userEmail,int productId) throws Exception;
	public Boolean checkoutProductByUser(String userEmail,int productId)throws Exception;
	public int currentLoanCount(String userEmail);
	public List<ShelfCurrentLoansResponse> currentLoans(String userEmail) throws Exception;
	public void returnProduct(String userEmail,int productId) throws Exception;
}
