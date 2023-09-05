package application;

import java.util.Date;

public class AccidentReport {
/*An AccidentReport has:
accident_id (Primary key), vehicle_id (foreign key), type, description, location, date*/
	public int accident_id;
	public int vehicle_id; 
	public String description;
	public String location;
	public Date date;
	public AccidentReport(int accident_id, int vehicle_id, String description, String location, Date date) {
		super();
		this.accident_id = accident_id;
		this.vehicle_id = vehicle_id;
		this.description = description;
		this.location = location;
		this.date = date;
	}
	public int getAccident_id() {
		return accident_id;
	}
	public void setAccident_id(int accident_id) {
		this.accident_id = accident_id;
	}
	public int getVehicle_id() {
		return vehicle_id;
	}
	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
