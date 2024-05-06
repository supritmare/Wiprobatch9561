package com.capstone.mobileservice;

import com.capstone.mobileservice.entity.Mobile;
import com.capstone.mobileservice.repository.MobileRepository;
import com.capstone.mobileservice.service.MobileService;
import com.capstone.mobileservice.service.MobileServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MobileServiceTest {

	@Mock
	private MobileRepository mobileRepository;

	@InjectMocks
	private MobileService mobileService = new MobileServiceImpl();

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAllMobiles() {
		List<Mobile> mobileList = new ArrayList<>();
		mobileList.add(new Mobile(1L, "iPhone", "Apple", 70000));
		mobileList.add(new Mobile(2L, "Galaxy", "Samsung", 40000));

		when(mobileRepository.findAll()).thenReturn(mobileList);

		List<Mobile> result = mobileService.getAllMobiles();

		assertEquals(2, result.size());
	}

	@Test
	public void testGetMobileById() {
		Mobile mobile = new Mobile(1L, "iPhone", "Apple", 70000);

		when(mobileRepository.findById(1L)).thenReturn(Optional.of(mobile));

		Optional<Mobile> result = mobileService.getMobileById(1L);

		assertEquals(mobile, result.get());
	}

	@Test
	public void testSaveMobile() {
		Mobile mobile = new Mobile(1L, "iPhone", "Apple", 70000);

		when(mobileRepository.save(any(Mobile.class))).thenReturn(mobile);

		Mobile result = mobileService.saveMobile(mobile);

		assertEquals(mobile, result);
	}

	@Test
	public void testUpdateMobile() {
		Mobile mobile = new Mobile(1L, "iPhone", "Apple", 70000);

		when(mobileRepository.existsById(1L)).thenReturn(true);
		when(mobileRepository.save(any(Mobile.class))).thenReturn(mobile);

		Mobile result = mobileService.updateMobile(1L, mobile);

		assertEquals(mobile, result);
	}

	@Test
	public void testDeleteMobile() {
		doNothing().when(mobileRepository).deleteById(1L);

		mobileService.deleteMobile(1L);

		verify(mobileRepository, times(1)).deleteById(1L);
	}

	@Test
	public void testSearchMobilesByBrand() {
		List<Mobile> mobileList = new ArrayList<>();
		mobileList.add(new Mobile(1L, "iPhone", "Apple", 70000));
		mobileList.add(new Mobile(2L, "Galaxy", "Samsung", 40000));

		when(mobileRepository.findByBrand(anyString())).thenReturn(mobileList);

		List<Mobile> result = mobileService.searchMobilesByBrand("Apple");

		assertEquals(2, result.size());
		assertEquals("Apple", result.get(0).getBrand());
	}

	@Test
	public void testSearchMobilesByModel() {
		List<Mobile> mobileList = new ArrayList<>();
		mobileList.add(new Mobile(1L, "iPhone", "Apple", 70000));

		when(mobileRepository.findByModel(anyString())).thenReturn(mobileList);

		List<Mobile> result = mobileService.searchMobilesByModel("iPhone");

		assertEquals(1, result.size());
		assertEquals("iPhone", result.get(0).getModel());
	}

	@Test
	public void testSearchMobilesByPriceRange() {
		List<Mobile> mobileList = new ArrayList<>();
		mobileList.add(new Mobile(1L, "iPhone", "Apple", 70000));
		mobileList.add(new Mobile(2L, "Galaxy", "Samsung", 40000));

		when(mobileRepository.findByPriceBetween(anyDouble(), anyDouble())).thenReturn(mobileList);

		List<Mobile> result = mobileService.searchMobilesByPriceRange(40000, 100000);

		assertEquals(2, result.size());
		assertTrue(result.stream().allMatch(mobile -> mobile.getPrice() >= 40000 && mobile.getPrice() <= 100000));
	}

}