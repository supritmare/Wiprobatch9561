package com.capstone.mobileservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.mobileservice.entity.Mobile;
import com.capstone.mobileservice.repository.MobileRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MobileServiceImpl implements MobileService {
	@Autowired
	private MobileRepository mobileRepository;

	@Override
	public List<Mobile> getAllMobiles() {
		return mobileRepository.findAll();
	}

	@Override
	public Optional<Mobile> getMobileById(Long id) {
		return mobileRepository.findById(id);
	}

	@Override
	public Mobile saveMobile(Mobile mobile) {
		return mobileRepository.save(mobile);
	}

	@Override
	public Mobile updateMobile(Long id, Mobile updatedMobile) {
		if (mobileRepository.existsById(id)) {
			updatedMobile.setId(id);
			return mobileRepository.save(updatedMobile);
		} else {
			throw new RuntimeException("Mobile with id " + id + " not found");
		}
	}

	@Override
	public void deleteMobile(Long id) {
		mobileRepository.deleteById(id);
	}

	@Override
	public List<Mobile> searchMobilesByBrand(String brand) {
		return mobileRepository.findByBrand(brand);
	}

	@Override
	public List<Mobile> searchMobilesByModel(String model) {
		return mobileRepository.findByModel(model);
	}

	@Override
	public List<Mobile> searchMobilesByPriceRange(double minPrice, double maxPrice) {
		return mobileRepository.findByPriceBetween(minPrice, maxPrice);
	}

}