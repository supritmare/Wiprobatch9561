package com.capstone.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capstone.userservice.entity.Admin;
import com.capstone.userservice.exception.ResourceNotFoundException;
import com.capstone.userservice.service.AdminService;
import java.util.List;

@RestController
@RequestMapping("admins")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@GetMapping("/all")
	public ResponseEntity<List<Admin>> getAllAdmins() {
		List<Admin> admins = adminService.getAllAdmins();
		return new ResponseEntity<>(admins, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
		Admin admin = adminService.getAdminById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Admin with id " + id + " not found"));
		return new ResponseEntity<>(admin, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin) {
		Admin createdAdmin = adminService.addAdmin(admin);
		return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody Admin adminDetails) {
		Admin updatedAdmin = adminService.updateAdmin(id, adminDetails);
		return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
		adminService.deleteAdmin(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}