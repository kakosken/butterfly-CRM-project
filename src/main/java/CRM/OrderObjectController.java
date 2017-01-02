package CRM;



import java.util.ArrayList;
import java.util.List;










import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

@ManagedBean
public class OrderObjectController {
	@EJB
	private CRMejb crmEjb;

	@ManagedProperty(value = "#{orderObject}")
	private OrderObject orderObject;
	
	private List<OrderObject> hakutulokset = new ArrayList();
	
	public List<OrderObject> getHakutulokset() {
		return hakutulokset;
	}

	public void setHakutulokset(ArrayList<OrderObject> hakutulokset) {
		this.hakutulokset = hakutulokset;
	}

	public OrderObjectController(){
		
	}
	
	public OrderObject getOrderObject() {
		return orderObject;
	}

	public void setOrderObject(OrderObject orderObject) {
		this.orderObject = orderObject;
	}
	
	
	public String saveOrderObject() {
		
		String viesti = "Uuden tuotteen lisääminen onnistui "+ orderObject;
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		FacesMessage facesMessage = new FacesMessage(viesti);
		facesContext.addMessage(null, facesMessage);
		crmEjb.saveOrderObject(orderObject);
		return "index";
		
		
	}
	
	
	
	public void getOrderObjects() {
		hakutulokset = (List<OrderObject>) crmEjb.getOrderObjects();
	}
	
	public  void searchByName(String name) {
		hakutulokset = (List<OrderObject>)crmEjb.getOrderObjectsByName(name);
	}
	
	public  void searchByState(String state) {
		hakutulokset =  (List<OrderObject>)crmEjb.getOrderObjectsByState(state);
	}
	
	public  void searchByCompanyId(String companyId) {
		try{
			long id = Long.parseLong(companyId);
			hakutulokset = (List<OrderObject>) crmEjb.getOrderObjectsByCompany(id);
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void searchByOrderDeliveryPlace(String deliveryPlace){
		hakutulokset = (List<OrderObject>) crmEjb.getOrderObjectsByDeliveryPlace(deliveryPlace);
	}
	
	public void searchByOrderDeliveryDate(String date){
		hakutulokset = (List<OrderObject>)crmEjb.getOrderObjectsByDate(date);
	}
	
	public void searchByOrderCustomer(long customerId){
		hakutulokset = (List<OrderObject>)crmEjb.getOrderObjectsByCustomer(customerId);
	}
	

	public String initialize() {
		crmEjb.init();
		return "";
	}


}
