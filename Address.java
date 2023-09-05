package application;

public class Address {
	/*An Address has an: address_id, city, street, zip*/
	public int address_id;
	public String city;
	public String street;
	public String zip;
	public Address(int address_id, String city, String street, String zip) {
		super();
		this.address_id = address_id;
		this.city = city;
		this.street = street;
		this.zip = zip;
	}
	public int getAddress_id() {
		return address_id;
	}
	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
}
