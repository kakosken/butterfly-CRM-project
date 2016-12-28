package CRM;

/* Terhi Järvenpää */
import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean
@RequestScoped
@Entity
@NamedQueries({
	@NamedQuery(name = "searchAllOrders", query = "SELECT order from MyOrder order"),
	@NamedQuery(name = "searchOrdersByCompany", query = "SELECT order FROM MyOrder order WHERE order.company.id=:yritysId")
})
public class MyOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
	private Company company;
    
    private enum state{
    	NEW, DELIVERED
    }; 

    private String salesPerson;
    private Date deliveryDate;
    private String deliveryPlace;

	public MyOrder() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public String getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(String salesPerson) {
		this.salesPerson = salesPerson;
	}
	
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "MyOrder [id=" + id + ", company=" + company + ", salesPerson="
				+ salesPerson + "]";
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryPlace() {
		return deliveryPlace;
	}

	public void setDeliveryPlace(String deliveryPlace) {
		this.deliveryPlace = deliveryPlace;
	}
	
	


}
