package com.e7i.safetynetapi.dto;

import com.e7i.safetynetapi.data.UserData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommunityEmail implements Dto {
	
	private String email;
	
	public CommunityEmail(UserData userData) {
		this.email = userData.getEmail();
	}
}
