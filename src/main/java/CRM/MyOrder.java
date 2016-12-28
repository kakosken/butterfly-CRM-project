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


}
