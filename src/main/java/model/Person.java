package model;

public class Person {
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private int LocationId;
    private String zipCode;
    private String telephone;
    private String email;
    private String ssn;
    private Location location;
	
        

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
        public void setLocationId(int LocationId){
                this.LocationId = LocationId;
        }
        public int getLocationId(){
            return LocationId;
        }
	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

        public String getZipCode() {
		return city;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
