package resources;

//enum is special class in java which has collection of constants or methods
public enum APIResources_enum {
	
	AddPlaceAPI("maps/api/place/add/json"),
	getPlaceAPI("maps/api/place/get/json"),
	deletePlaceAPI("maps/api/place/delete/json");
	
	private String resource;
	
	APIResources_enum(String resource){
		this.resource = resource;
	}
	
	public String getResource() {
		return resource;
	}

}
