package com.capstone.mobileservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.mobileservice.entity.Mobile;

@Repository
public interface MobileRepository extends JpaRepository<Mobile, Long> {

	List<Mobile> findByBrand(String brand);

	List<Mobile> findByModel(String model);

	List<Mobile> findByPriceBetween(double minPrice, double maxPrice);
}
