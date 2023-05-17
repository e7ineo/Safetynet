package com.e7i.safetynetapi.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.e7i.safetynetapi.data.UserData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FloodDto implements Dto{
	
	private Map<String,List<FireDto>> floodDtoMap = new HashMap<>();
	
	public FloodDto(List<UserData> listUserData) {
		for(UserData ud : listUserData) {
			String key = ud.getAddress();
			FireDto value = new FireDto(ud);
			if(!floodDtoMap.containsKey(key)) {
				floodDtoMap.put(key, new ArrayList<FireDto>());
			}
			floodDtoMap.get(key).add(value);
		}
	}
}
