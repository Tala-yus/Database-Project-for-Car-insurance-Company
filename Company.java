package application;

public class Company {
/*A Company has a: company_id (Primary key), name, address_id (foreign key), email.*/
	public String company_name;
	public int address_id;
	public String email;
	
	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Company(String company_name, int address_id, String email) {
		super();
		this.company_name = company_name;
		this.address_id = address_id;
		this.email = email;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public int getAddress_id() {
		return address_id;
	}
	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
}
