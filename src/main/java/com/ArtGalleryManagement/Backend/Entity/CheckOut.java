package com.ArtGalleryManagement.Backend.Entity;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name="checkout")
@Data
public class CheckOut {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int checkout_id;
	
	@Column(name="user_email")
	private String userEmail;
	
	@Column	
	private String checkout_date;
	
	@Column(name="product_id")
	private int productId;
	
	@Column
	private String return_date;
	
	public CheckOut() {	}

	public CheckOut(String userEmail, String checkout_date,String return_date, int productId) {
		this.userEmail = userEmail;
		this.checkout_date = checkout_date;
		this.productId = productId;
		this.return_date=return_date;
	}

	public int getCheckout_id() {
		return checkout_id;
	}

	public void setCheckout_id(int checkout_id) {
		this.checkout_id = checkout_id;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getCheckout_date() {
		return checkout_date;
	}

	public void setCheckout_date(String checkout_date) {
		this.checkout_date = checkout_date;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getReturn_date() {
		return return_date;
	}

	public void setReturn_date(String return_date) {
		this.return_date = return_date;
	}
	
	
	
	
	
	

	
}
