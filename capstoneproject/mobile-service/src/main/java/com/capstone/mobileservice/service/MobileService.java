package com.capstone.mobileservice.service;

import java.util.List;
import java.util.Optional;

import com.capstone.mobileservice.entity.Mobile;

public interface MobileService {

	List<Mobile> getAllMobiles();

	Optional<Mobile> getMobileById(Long id);

	Mobile saveMobile(Mobile mobile);

	Mobile updateMobile(Long id, Mobile updatedMobile);

	void deleteMobile(Long id);

	List<Mobile> searchMobilesByBrand(String brand);

	List<Mobile> searchMobilesByModel(String model);

	List<Mobile> searchMobilesByPriceRange(double minPrice, double maxPrice);

}
