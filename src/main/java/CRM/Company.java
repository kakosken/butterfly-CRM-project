package CRM;




/* Terhi Järvenpää */

import java.util.List;

import javax.persistence.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import jpaesim.Kauppa;

@ManagedBean
@RequestScoped
@Entity

@NamedQuery(name = "searchAllCompanies", query = "SELECT company from Company company") 

public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String businessId;
    private String name; 
    private String phone;
    private String streetAddress;
    private String postcode;
    private String town;
    private String webPage;
    
   // @ManyToOne 
   // private List<Customer> asiakkaat;
    
	// Määrittelee tuote - kauppa suhteen 1 - n 
	//@OneToMany  ( targetEntity=Kauppa.class )
//	private List<Kauppa> kaupat;
    
    public Company (){
		super();
	}
      
    

	public Company(String businessId, String name, String phone,
			String streetAddress, String postcode, String town, String webPage) {
		super();
		this.businessId = businessId;
		this.name = name;
		this.phone = phone;
		this.streetAddress = streetAddress;
		this.postcode = postcode;
		this.town = town;
		this.webPage = webPage;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}


	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getWebPage() {
		return webPage;
	}

	public void setWebPage(String webPage) {
		this.webPage = webPage;
	}

	@Override
	public String toString() {
		return "Company [businessId=" + businessId + ", name=" + name
				+ ", phone=" + phone + ", streetAddress=" + streetAddress
				+ ", postcode=" + postcode + ", town=" + town + ", webPage="
				+ webPage + "]";
	}
    
    
}




