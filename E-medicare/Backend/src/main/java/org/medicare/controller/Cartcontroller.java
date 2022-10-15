package org.medicare.controller;

import java.util.List;

import org.medicare.entity.Cart;
import org.medicare.entity.ContactUs;
import org.medicare.entity.Medicines;
import org.medicare.services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.demo.MedicineList.MedicineService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v5")
public class Cartcontroller {
private CartService cartService;
private MedicineService medicineService;

public Cartcontroller(CartService cartService,MedicineService medicineService) {
	super();
	this.cartService = cartService;
	this.medicineService = medicineService;
}

@GetMapping("/cart")
public List<Cart> getCart(){
	List<Cart> Cart = cartService.getCart();
	return Cart;
}

@GetMapping("/cart/{medicineId}")
public Cart buyMed(@PathVariable("medicineId") int medicineId) { 
	Medicines M =medicineService.findById(medicineId);
	Cart Citem = new Cart();
	Citem.setMedicineId(medicineId);
	Citem.setMedicineName(M.getMedicineName());
	Citem.setDescription(M.getDescription());
	Citem.setPrice(M.getPrice());
	cartService.addToCart(Citem);
	return Citem;
}

@DeleteMapping("/cart/{medicineId}")
public ResponseEntity<Object> deleteCart(@PathVariable("medicineId") int medicineId) {
	cartService.deleteCart(medicineId);
	return new ResponseEntity<>("deleted successsfully", HttpStatus.OK);
}
}

