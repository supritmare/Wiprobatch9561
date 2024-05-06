package com.capstone.mobileservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.mobileservice.entity.Mobile;
import com.capstone.mobileservice.service.MobileService;

@RestController
@RequestMapping("/mobile")
public class MobileController {

	@Autowired
	private MobileService mobileService;

	@GetMapping("/all")
	public List<Mobile> getAllMobiles() {
		return mobileService.getAllMobiles();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Mobile> getMobileById(@PathVariable Long id) {
		Optional<Mobile> mobile = mobileService.getMobileById(id);
		return mobile.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("/add")
	public ResponseEntity<Mobile> saveMobile(@RequestBody Mobile mobile) {
		Mobile createdMobile = mobileService.saveMobile(mobile);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdMobile);
	}

	@PutMapping("{id}")
	public ResponseEntity<Mobile> updateMobile(@PathVariable Long id, @RequestBody Mobile mobile) {
		Mobile updatedMobile = mobileService.updateMobile(id, mobile);
		return ResponseEntity.ok().body(updatedMobile);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMobile(@PathVariable Long id) {
		mobileService.deleteMobile(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/search/brand")
	public List<Mobile> searchMobilesByBrand(@RequestParam String brand) {
		return mobileService.searchMobilesByBrand(brand);
	}

	@GetMapping("/search/model")
	public List<Mobile> searchMobilesByModel(@RequestParam String model) {
		return mobileService.searchMobilesByModel(model);
	}

	@GetMapping("/search/price")
	public List<Mobile> searchMobilesByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
		return mobileService.searchMobilesByPriceRange(minPrice, maxPrice);
	}
}
