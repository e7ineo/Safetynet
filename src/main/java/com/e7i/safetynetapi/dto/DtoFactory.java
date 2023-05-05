package com.e7i.safetynetapi.dto;

import java.util.ArrayList;
import java.util.List;

import com.e7i.safetynetapi.data.UserData;
import com.e7i.safetynetapi.data.UserDataFactory;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_DEFAULT)
public class DtoFactory {
	
	@JsonProperty("Results")
	List<Dto> usersDto = new ArrayList<>();
	@JsonProperty("adultsCount")
	int adultsNumber;
	@JsonProperty("childrensCount")
	int childNumber;
	@JsonProperty("familyMembers")
	List<String> familyMemberList = new ArrayList<>();
	String ErrorMessage;
	
	public List<Dto> createFirestationDto(int stationNumber) {		
		FirestationDto firestationDto;
		for(UserData ud : UserDataFactory.getUsersData()) {
			if(stationNumber == ud.getFirestationNumber()) {
				firestationDto = new FirestationDto(ud);
				usersDto.add(firestationDto);
				if(firestationDto.isAdult()) {
					adultsNumber++;
				} else {
					childNumber++;
				}
			}
		}
		return usersDto;
	}
	
	public List<Dto> createChildAlertDto(String address) {
		ChildAlertDto childAlertDto;
		for(UserData ud : UserDataFactory.getUsersData()) {
			if(address.equalsIgnoreCase(ud.getAddress())) {
				if(!ud.isAdult()) {
					childAlertDto = new ChildAlertDto(ud);					
					usersDto.add(childAlertDto);
				} else {
					familyMemberList.add(ud.getFirstName() + " " + ud.getLastName());
				}
			}
		}
		return usersDto;
	}

	public List<Dto> createPhoneNumberDto(int stationNumber) {
		PhoneAlertDto phoneAlertDto;
		for(UserData ud : UserDataFactory.getUsersData()) {
			if(stationNumber == ud.getFirestationNumber()) {
				phoneAlertDto = new PhoneAlertDto(ud);
				usersDto.add(phoneAlertDto);
			}
		}
		return usersDto;
	}
	
	public List<Dto> createFireDto(String address) {
		FireDto fireDto;
		for(UserData ud : UserDataFactory.getUsersData()) {
			if(address.equalsIgnoreCase(ud.getAddress())) {
				fireDto = new FireDto(ud);
				usersDto.add(fireDto);
			}
		}
		return usersDto;
	}

	public List<Dto> createFloodDto(int[] stationNumbers) {
		FloodDto floodDto;
		List<UserData> usersData = new ArrayList<>();
		
		for(int i = 0; i < stationNumbers.length; i++) {
		
			for(UserData ud : UserDataFactory.getUsersData()) {
				if(stationNumbers[i] == ud.getFirestationNumber()) {
					usersData.add(ud);
				}
			}
		}
		floodDto = new FloodDto(usersData);
		usersDto.add(floodDto);
		return usersDto;
	}
	
	public List<Dto> createPersonInfoDto(String firstName, String lastName) {
		PersonInfoDto personInfoDto;
		for(UserData ud : UserDataFactory.getUsersData()) {
			if(firstName.equalsIgnoreCase(ud.getFirstName()) && lastName.equalsIgnoreCase(ud.getLastName())) {
				personInfoDto = new PersonInfoDto(ud);
				usersDto.add(personInfoDto);
			}
		}
		return usersDto;
	}

	public List<Dto> createCommunityEmailDto(String city) {
		CommunityEmail communityEmail;
		for(UserData ud : UserDataFactory.getUsersData()) {
			if(city.equalsIgnoreCase(ud.getCity())) {
				communityEmail = new CommunityEmail(ud);
				usersDto.add(communityEmail);
			}
		}
		return usersDto;
	}
	
}
