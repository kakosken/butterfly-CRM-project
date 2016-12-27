package CRM;

/* Terhi Järvenpää */

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;


@ManagedBean
public class OrderController {
	
	@EJB
	private CRMejb crmEjb;

	@ManagedProperty(value = "#{myorder}")
	private MyOrder order;
	
	public OrderController() {
		// testidatan alustus?

	}
	
	public MyOrder getOrder() {
		return order;
	}

	public void setOrder(MyOrder order) {
		this.order = order;
	}
	
	public String saveOrder() {
		String viesti = "Order has been successfully saved" ;

		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		crmEjb.saveOrder(order);
		
		FacesMessage facesMessage = new FacesMessage(viesti);
		facesContext.addMessage(viesti, facesMessage);

		return "index";
	}
	
	
	
	
	public  ArrayList<MyOrder> listOrders() {
		return (ArrayList<MyOrder>) crmEjb.getOrders();
	} 
	
	

	public void initializeMyOrder() {
		crmEjb.init();
	}

}
