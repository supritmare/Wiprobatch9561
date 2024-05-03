package com.online.mobilestore1.service;

import java.util.List;

import com.online.mobilestore1.entity.Mobile;

public interface MobileService {
	
	 List<Mobile> getMobiles();
	
     Mobile getMobile(Integer mobileId);
    
     Mobile addMobile(Mobile mobile);
    
     void deleteMobile( Integer mobileId);
    
     Mobile updateMobile(Integer mobileId, Mobile mobile);



	

}
