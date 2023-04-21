package com.e7i.safetynetapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Firestation {

	String address;
	@JsonProperty("station")
	int stationNumber;
	
	@Override
	public String toString() {
		return "Adress : " + address + 
				"\nNumber of Station : " + stationNumber;
	}
}
