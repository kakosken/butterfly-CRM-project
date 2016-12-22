package CRM;

/* Terhi Järvenpää */
import java.io.Serializable;

import javax.persistence.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean
@RequestScoped
@Entity
@NamedQuery(name = "searchAllOrders", query = "SELECT order from Order order") 
public class Order {
//  private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private enum state{
    	NEW, DELIVERED
    }; 
    private String salesPerson;
	public Order(Long id, String salesPerson) {
		super();
		this.id = id;
		this.salesPerson = salesPerson;
	}
    
    public Order() {
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

	@Override
	public String toString() {
		return "Order [id=" + id + ", salesPerson=" + salesPerson + "]";
	}
    
    
    
    

}
