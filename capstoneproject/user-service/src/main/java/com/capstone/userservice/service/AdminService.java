package com.capstone.userservice.service;

import java.util.List;
import java.util.Optional;

import com.capstone.userservice.entity.Admin;

public interface AdminService {

	List<Admin> getAllAdmins();

	Optional<Admin> getAdminById(Long id);

	Admin addAdmin(Admin admin);

	Admin updateAdmin(Long id, Admin admin);

	void deleteAdmin(Long id);

}
