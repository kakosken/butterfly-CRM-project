package CRM;

/* Terhi Järvenpää */

import java.util.ArrayList;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;


@ManagedBean
public class OrderController {
	
	@EJB
	private CRMejb crmEjb;

	@ManagedProperty(value = "#{order}")
	private Order order;
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	public String saveOrder() {
		String viesti = "Order has been successfully saved" ;

		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		Order ord = (Order) facesContext.getExternalContext().getRequestMap().get("order");
		// save order and set facesMessage if error

		
		FacesMessage facesMessage = new FacesMessage(viesti);
		facesContext.addMessage(viesti, facesMessage);
		
		crmEjb.saveOrder(ord);

		return "index";
	}
	
	
	
	
	public  ArrayList<Order> listOrders() {
		return (ArrayList<Order>) crmEjb.getOrders();
	} 
	
	

	public String initializeOrder() {
		crmEjb.init();
		return null;
	}

}
