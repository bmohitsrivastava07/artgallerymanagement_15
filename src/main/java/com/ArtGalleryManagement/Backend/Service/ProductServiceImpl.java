package com.ArtGalleryManagement.Backend.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ArtGalleryManagement.Backend.Entity.CheckOut;
import com.ArtGalleryManagement.Backend.Entity.Product;
import com.ArtGalleryManagement.Backend.Models.ShelfCurrentLoansResponse;
import com.ArtGalleryManagement.Backend.Repository.CheckOutRepository;
import com.ArtGalleryManagement.Backend.Repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	@Autowired
	CheckOutRepository checkoutRepository;

	public ProductServiceImpl(ProductRepository productRepository, CheckOutRepository checkoutRespository) {
		super();
		this.productRepository = productRepository;
		this.checkoutRepository = checkoutRespository;
	}

	@Override
	public Product checkoutProduct(String userEmail, int productId) throws Exception {
		Optional<Product> product = productRepository.findById(productId);
		CheckOut validatecheckOut = checkoutRepository.findByUserEmailAndProductId(userEmail, productId);
		if (!product.isPresent() || validatecheckOut != null || product.get().getProductQuantityAvailable() <= 0) {
			throw new Exception("Product doesn't exist or already user checkout the product");
		}
		product.get().setProductQuantityAvailable(product.get().getProductQuantityAvailable() - 1);
		productRepository.save(product.get());
		CheckOut checkout = new CheckOut(userEmail, LocalDate.now().toString(), LocalDate.now().plusDays(7).toString(),
				product.get().getProductId());
		checkoutRepository.save(checkout);
		return product.get();
	}

	@Override
	public Boolean checkoutProductByUser(String userEmail, int productId) throws Exception {
		CheckOut validatecheckOut = checkoutRepository.findByUserEmailAndProductId(userEmail, productId);
		if (validatecheckOut == null) {
			return false;
		}
		return true;
	}

	@Override
	public int currentLoanCount(String userEmail) {

		List<CheckOut> count = checkoutRepository.findProductByUserEmail(userEmail);
		return count.size();
	}

	@Override
	public List<ShelfCurrentLoansResponse> currentLoans(String userEmail) throws Exception {
		List<ShelfCurrentLoansResponse> shelfCurrentLoansResponses=new ArrayList<>();
		List<CheckOut> checkoutList=checkoutRepository.findProductByUserEmail(userEmail);
		List<Integer> productIdList= new ArrayList<>();
		for(CheckOut i:checkoutList) {
			productIdList.add(i.getProductId());
		}
		List<Product> products=productRepository.findProductByProductId(productIdList);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		for(Product product:products) {
			Optional<CheckOut> checkout=checkoutList.stream().filter(x->x.getProductId()== product.getProductId()).findFirst();
			if(checkout.isPresent()) {
				Date d1=sdf.parse(checkout.get().getReturn_date());
				Date d2=sdf.parse(LocalDate.now().toString());
				TimeUnit time=TimeUnit.DAYS;
				long difference_In_Time=time.convert(d1.getTime()-d2.getTime(),TimeUnit.MILLISECONDS);
				shelfCurrentLoansResponses.add(new ShelfCurrentLoansResponse((int)difference_In_Time,product));
			}
		}
		return shelfCurrentLoansResponses;
		
	}

	@Override
	public void returnProduct(String userEmail, int productId) throws Exception {
	    Optional<Product> product=productRepository.findById(productId);
	    CheckOut validateCheckout=checkoutRepository.findByUserEmailAndProductId(userEmail, productId);
	    if(!product.isPresent() || validateCheckout==null) {
	    	throw new Exception("Product does not exist or not checked out by user");
	    }
	    product.get().setProductQuantityAvailable(product.get().getProductQuantityAvailable()+1);
	    productRepository.save(product.get());
	    checkoutRepository.deleteById((long)validateCheckout.getProductId());
		
	}

}
