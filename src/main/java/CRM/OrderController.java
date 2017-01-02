package CRM;

/* Terhi Järvenpää */

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;


@ManagedBean
public class OrderController {
	
	@EJB
	private CRMejb crmEjb;
	
	private OrderObjectController orderObjectc;

	@ManagedProperty(value = "#{myorder}")
	private MyOrder order;
	
	private List<MyOrder> hakutulokset= new ArrayList<MyOrder>(); 
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
	
	public List<MyOrder> getHakutulokset(){ 
		return hakutulokset; 
	}
	
	
	//kaikki tilaukset kannasta
	public  List<MyOrder> listOrders() {
		hakutulokset = (List<MyOrder>) crmEjb.getOrders();
		return null;
	} 
	
	//yrityksen tilaukset kannasta
	public  List<MyOrder> listOrdersByCompany(Long companyId) {
		
		hakutulokset= (List<MyOrder>) crmEjb.getOrdersByCompany(companyId);
		return null;
	}
	
	
	//tilaukset tilauspäivän mukaan
	public  List<MyOrder> listOrdersByDate(String date) {
		hakutulokset = (List<MyOrder>) crmEjb.getOrdersByDate(date);
		return null;
	}
	
	//tilaukset toimituspaikan mukaan
	public  List<MyOrder> listOrdersByPlace(String place) {
		hakutulokset = (List<MyOrder>) crmEjb.getOrdersByPlace(place);
		return null;
	}
	
/*	public String getOrdersByDate(String day,String month, String year){
		public  String searchByOrderDate(String day, String month, String year) {
			int d = 0;
			int m = 0;
			int y = 0;
			try{
				d = Integer.parseInt(day);
				m = Integer.parseInt(month);
				y = Integer.parseInt(year);
				
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("Päivämäärän annettu väärässä muodossa");
			}
			//Date deliveryDate = (Date) new GregorianCalendar(y, m, d).getTime();
			//Date deliveryDate = Calendar.getInstance().set(y, m - 1, d, 0, 0);
			//return crmEjb.getOrderObjectsByOrderDate(deliveryDate).toString();
		}
		
	}
	
	*/

	

	public void initializeMyOrder() {
		crmEjb.init();
	}
	
	

}
