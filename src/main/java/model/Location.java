package model;

public class Location {
	private String city;
	private String state;
	private int zipCode;
        private int LocationId;
        
        public int getLocationId(){
            return LocationId;
        }
        public void setLocationId(int LocationId){
            this.LocationId = LocationId;
        }
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
}
