package com.e7i.safetynetapi.dto;

import com.e7i.safetynetapi.data.UserData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneAlertDto implements Dto {
	
	private String phoneNumber;
	
	public PhoneAlertDto(UserData userData) {
		this.phoneNumber = userData.getPhoneNumber();
	}

}
