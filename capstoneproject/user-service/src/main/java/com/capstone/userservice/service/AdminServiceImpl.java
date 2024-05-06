package com.capstone.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.userservice.entity.Admin;
import com.capstone.userservice.exception.ResourceNotFoundException;
import com.capstone.userservice.repository.AdminRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public List<Admin> getAllAdmins() {
		return adminRepository.findAll();
	}

	@Override
	public Optional<Admin> getAdminById(Long id) {
		Optional<Admin> admin = adminRepository.findById(id);
		if (admin.isEmpty()) {
			throw new ResourceNotFoundException("Admin with id " + id + " not found");
		}
		return admin;
	}

	@Override
	public Admin addAdmin(Admin admin) {
		return adminRepository.save(admin);
	}

	@Override
	public Admin updateAdmin(Long id, Admin admin) {
		Admin existingAdmin = adminRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Admin with id " + id + " not found"));

		existingAdmin.setUsername(admin.getUsername());
		existingAdmin.setPassword(admin.getPassword());

		return adminRepository.save(existingAdmin);
	}

	@Override
	public void deleteAdmin(Long id) {
		if (!adminRepository.existsById(id)) {
			throw new ResourceNotFoundException("Admin with id " + id + " not found");
		}
		adminRepository.deleteById(id);
	}
}
