package com.online.mobilestore1.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mobile.ecxeption.ResourceNotFoundException;
import com.online.mobilestore1.entity.Mobile;
import com.online.mobilestore1.repositary.MobileRepository;

@Service
public class MobileServiceImpl implements MobileService {
	
	

	@Autowired
	private MobileRepository mobileRepositary;
	
	

	@Override
	public List<Mobile> getMobiles() {
		
		return mobileRepositary.findAll();
	}

	@Override
	public Mobile getMobile(Integer mobileId) {
	    Optional<Mobile> optional=mobileRepositary.findById(mobileId);
	    if(optional.isEmpty()) {

			throw new ResourceNotFoundException("Mobile not Existing with this id : "+mobileId);

		}
	    Mobile mobile=optional.get();
	    return mobile;
	}

	@Override
	public Mobile addMobile(Mobile mobile) {
		
		return mobileRepositary.save(mobile);
	}

	@Override
	public void deleteMobile(Integer mobileId) {
		
		Optional<Mobile> optionalMobile = mobileRepositary.findById(mobileId);

		if(optionalMobile.isEmpty()) {

			throw new ResourceNotFoundException("Mobile not Existing with this id : "+mobileId);

		}

		Mobile mobile = optionalMobile.get();

		mobileRepositary.delete(mobile);
	}

	@Override
	public Mobile updateMobile(Integer mobileId, Mobile mobile) {
		
		Optional<Mobile> optionalMobile = mobileRepositary.findById(mobile.getMobileId());

		if(optionalMobile.isEmpty()) {

			throw new ResourceNotFoundException("Mobile not existing with this id : "+mobile.getMobileId());

		}

		mobileRepositary.save(mobile);

		return mobile;





	}



	

	





	}
