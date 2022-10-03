package resources;

import java.util.ArrayList;
import java.util.List;

import POJO.Location;
import POJO.addPlace;

public class TestDataBuild {
	
	public addPlace addPlacePayLoad(String name, String language, String address) {
		
		addPlace p = new addPlace();
		p.setAddress(address);
		p.setLanguage(language);
		p.setPhone_number("267-516-2734");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName(name);
		p.setAccuracy(50);
		
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		
		Location l= new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		return p;
	}
	
	public String deletePlacePayload(String placeId) {
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}\r\n";
	}

}
