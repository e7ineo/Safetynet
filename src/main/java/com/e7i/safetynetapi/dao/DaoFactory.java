package com.e7i.safetynetapi.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import com.e7i.safetynetapi.model.SafetyNetList;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DaoFactory {
	
	ObjectMapper mapper = new ObjectMapper();
	SafetyNetList safetyNetList;
	
	public void createFileFromUrl(String urlString) throws IOException {
		URL url = new URL(urlString);
		ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
		FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/data.json");
		fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
		fileOutputStream.close();
		readableByteChannel.close();
	}  
	
	public boolean createDao(File file) {
		try {
			safetyNetList = mapper.readValue(file,SafetyNetList.class);
			new PersonDao(safetyNetList.getPersons());
			new MedicalRecordDao(safetyNetList.getMedicalRecords());
			new FirestationDao(safetyNetList.getFirestations());
			return true;
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
