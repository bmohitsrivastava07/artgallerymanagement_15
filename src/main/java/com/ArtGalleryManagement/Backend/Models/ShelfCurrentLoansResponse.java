package com.ArtGalleryManagement.Backend.Models;

import com.ArtGalleryManagement.Backend.Entity.Product;

import lombok.Data;

@Data
public class ShelfCurrentLoansResponse {

	private int daysLeft;
	private Product product;
	public ShelfCurrentLoansResponse(int daysLeft, Product product) {
		super();
		this.daysLeft = daysLeft;
		this.product = product;
	}
	
	
}
