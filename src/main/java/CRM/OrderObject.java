package CRM;

/* Terhi Järvenpää */

import java.io.Serializable;

import javax.persistence.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean
@RequestScoped
@Entity


@NamedQuery(name = "searchAllOrderObjects", query = "SELECT orderObject from OrderObject orderObject") 


public class OrderObject {
	
//  private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private enum state{
    	INSTORE, DELIVERED
    }; 
    private double price;
    private String name;
	
    public OrderObject(Long id, double price, String name) {
		this.id = id;
		this.price = price;
		this.name = name;
	}
    
    public OrderObject() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "OrderObject [id=" + id + ", price=" + price + ", name=" + name
				+ "]";
	}
    
    
	

}
