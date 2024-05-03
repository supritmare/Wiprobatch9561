package com.online.mobilestore1.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.online.mobilestore1.entity.Mobile;
import com.online.mobilestore1.service.MobileServiceImpl;

@RestController
@RequestMapping("/mobile")
public class MyController {

	@Autowired
	private MobileServiceImpl mobileServiceImpl;

	@GetMapping("/all")
	public List<Mobile> getMobile() {
		return this.mobileServiceImpl.getMobiles();

	}

	@GetMapping("/{id}")

	public ResponseEntity<Mobile> fetchMobileDetails(@PathVariable("id") Integer mobileId) {

		Mobile mobile = mobileServiceImpl.getMobile(mobileId);

		return new ResponseEntity<>(mobile, HttpStatus.OK);

	}

	@PostMapping("/addMobile")

	public ResponseEntity<Mobile> addMobile(@RequestBody Mobile mobile) {

		mobileServiceImpl.addMobile(mobile);

		ResponseEntity<Mobile> responseEntity = new ResponseEntity<>(mobile, HttpStatus.CREATED);

		return responseEntity;

	}

	@DeleteMapping("/{id}")
	public String deleteMobile(@PathVariable("id") Integer mobileId) {
		mobileServiceImpl.deleteMobile(mobileId);
		return "Resource with ID " + mobileId + " deleted successfully";
	}
	
	
	
	 
}


