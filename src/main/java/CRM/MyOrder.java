package CRM;

/* Terhi Järvenpää */
import java.io.Serializable;

import javax.persistence.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean
@RequestScoped
@Entity
@NamedQueries({
	@NamedQuery(name = "searchAllOrders", query = "SELECT order from MyOrder order"),
	@NamedQuery(name = "searchOrdersByCompany", query = "SELECT order FROM MyOrder order WHERE order.company.id=:yritysId"),
	@NamedQuery(name = "searchOrdersByDate", query = "SELECT order FROM MyOrder order WHERE order.deliveryDate=:date"),
	@NamedQuery(name = "searchOrdersByPlace", query = "SELECT order FROM MyOrder order WHERE order.deliveryPlace=:place"),
	@NamedQuery(name = "searchOrdersByCustomer", query = "SELECT order FROM MyOrder order WHERE order.customer.id=:customerId")
})
public class MyOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    private Customer customer;
    
    public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne
	private Company company;
    
    private enum state{
    	NEW, DELIVERED
    }; 

    private String salesPerson;
    private String deliveryDate;
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
		return "MyOrder [id=" + id + ", customer=" + customer + ", company="
				+ company + ", salesPerson=" + salesPerson + ", deliveryDate="
				+ deliveryDate + ", deliveryPlace=" + deliveryPlace + "]";
	}

	public String getDeliveryPlace() {
		return deliveryPlace;
	}

	public void setDeliveryPlace(String deliveryPlace) {
		this.deliveryPlace = deliveryPlace;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	

}
