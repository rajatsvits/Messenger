package org.rajat.mahajan.messenger1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.rajat.mahajan.messenger1.database.DatabaseClass;
import org.rajat.mahajan.messenger1.model.Profile;

public class ProfileService {

	private Map<String,Profile> profiles =  DatabaseClass.getProfiles();
	
	public ProfileService() { 
		profiles.put("Rj_mahajan", new Profile(1L,"Rj_mahajan","Rajat","Mahajan"));
		profiles.put("K_mahajan", new Profile(2L,"K_mahajan","Kunjesh","Mahajan"));
	}
	public List<Profile> getAllProfiles(){
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size()+1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
		
	}
	
	public Profile updateProfile(Profile profile) {
		if(profile.getProfileName().isEmpty())
			return null;
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile DeleteProfile(String profileName) {
		return profiles.remove(profileName);
	}
}
