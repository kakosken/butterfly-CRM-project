package CRM;

import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;





@Stateless
public class CRMejb {
	
	@PersistenceContext(unitName = "jpa_CRM") // Check persistence.xml
	private EntityManager em;
	
	public CRMejb(){
		
	}
	
	
	public void init(){
		Account testi = new Account();
		testi.setName("TestiNimi");
		testi.setPassword("testisalasana");
		testi.setUserName("TestiUserName");
		em.persist(testi);
		
		Company testiCompany = new Company();
		testiCompany.setBusinessId("1234567-1");
		testiCompany.setName("Testi Yritys Oy");
		testiCompany.setWebPage("wwww.testiyriys.com");
		em.persist(testiCompany);
		
		
		Customer testiCustomer = new Customer();
		testiCustomer.setName("Arttu Asiakas");
		testiCustomer.setEmail("arttu.asiakas@testiyritys.com");
		testiCustomer.setCompany(testiCompany);
		em.persist(testiCustomer);
		
		Customer testiCustomer2 = new Customer();
		testiCustomer2.setName("Testi Asiakas");
		testiCustomer2.setPhone("12345566");
		testiCustomer2.setCompany(testiCompany);
		em.persist(testiCustomer2);
		
		MyOrder testiOrder = new MyOrder();
		testiOrder.setSalesPerson("Minttu Myyjä");
		testiOrder.setCompany(testiCompany);
		testiOrder.setCustomer(testiCustomer);
		testiOrder.setDeliveryPlace("Helsinki");
		testiOrder.setDeliveryDate("1.12.2016");
		em.persist(testiOrder);
		
		MyOrder testiOrder2 = new MyOrder();
		testiOrder2.setSalesPerson("Matti Myyntimies");
		testiOrder2.setCompany(testiCompany);
		testiOrder2.setDeliveryPlace("Muumilaakso");
		testiOrder2.setCustomer(testiCustomer2);
		testiOrder2.setDeliveryDate("24.12.2016");
		em.persist(testiOrder2);
		
		OrderObject testituote = new OrderObject();
		testituote.setName("testituote");
		testituote.setOrder(testiOrder);
		testituote.setPrice(50.0);
		em.persist(testituote);
		
		OrderObject testituote2 = new OrderObject();
		testituote2.setName("testituote2");
		testituote2.setOrder(testiOrder2);
		testituote2.setPrice(66.0);
		em.persist(testituote2);
 
	}
	

	
	//Tallentaa uuden tilin tietokantaan
	public void addAccount(Account account){
		try{
			em.persist(account);
			System.out.println("Save new account "+ account);
		} catch (Exception e){
			e.printStackTrace();
			System.out.println("Uuden käyttäjätilin tallentaminen ei onnistunut");
		}
		
	}
	
	public void deleteAccount(long id){
		//käyttäjätilin poistaminen
		try{
			Account poistettava = em.find(Account.class, id);
			em.remove(poistettava);
			System.out.println("Account deleted: "+poistettava);
		}catch (Exception e){
			System.out.println("Käyttäjätilin poistaminen ei onnistunut");
			e.printStackTrace();
		}
	}
	
	public List<Account> getAccounts(){
		
		List<Account> accounts = null; 
		accounts = em.createNamedQuery("searchAllAccounts").getResultList();
		System.out.println("*********** search all ********** => " + accounts);
		return accounts;
	}

	//Uuden asiakkaan lisääminen
	public void addCustomer(Customer customer) {
		try{
			em.persist(customer);
			System.out.println("Uusi asiakas tallennettu: "+customer);
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Uuden asiakkaan lisääminen ei onnistunut! "+customer);
		}
		
	}
	
	//Jos asiakkaan nimi löytyy jo tietokannasta, palautetaan true, jos ei löydy false
	public boolean customerExist(String name){
		
		List<Customer> c = getCustomers();
		for (int i=0; i < c.size(); i++){
			if (c.get(i).getName().equals(name)) return true;
		}
		return false;
	}
	

	public List<Customer> getCustomers(){
		
		List<Customer> customers = null; 
		customers = em.createNamedQuery("searchAllCustomers").getResultList();
		System.out.println("*********** search all ********** => " + customers);
		
		return customers;

	}

	//Uuden tilaukse lisääminen
	public void saveOrder(MyOrder order) {
			try{
				em.persist(order);
				System.out.println("Uusi tilaus tallennettu: "+order);
			}catch (Exception e){
				e.printStackTrace();
				System.out.println("Uuden tilauksen lisääminen ei onnistunut! "+order);
			}
	}
	
	
	// get all orders from the database
	@SuppressWarnings("unchecked")
	public List<MyOrder> getOrders() {
		List<MyOrder> orders = null; 
		orders = em.createNamedQuery("searchAllOrders").getResultList();
		return orders;
	}
	



	
	public void saveOrderObject(OrderObject orderObject) {
		try {
			em.persist(orderObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	// yrityksen tilaukset tietokannasta
	@SuppressWarnings("unchecked")
	public List<MyOrder> getOrdersByCompany(Long companyId) {
		List<MyOrder> orders = null; 
		orders = em.createNamedQuery("searchOrdersByCompany").setParameter("yritysId", companyId).getResultList();
		// testing
		System.out.println("***********searchOrdersByCompany  ********** ");
				for (MyOrder o : orders) {
		            System.out.println(o.getId());
		            System.out.println(o.getSalesPerson());
				}
		return orders;
		
	}
	
	//tilauksen päivämäärän mukaan
	public List<MyOrder> getOrdersByDate(String date) {
		List<MyOrder> orders = null; 
		
		orders = em.createNamedQuery("searchOrdersByDate").setParameter("date", date).getResultList();
		// testing
		System.out.println("***********searchOrdersByDate  ********** ");
				for (MyOrder o : orders) {
		            System.out.println(o.getId());
		            System.out.println(o.getSalesPerson());
		            System.out.println(o.getDeliveryDate());
				}
		return orders;
	}
	
	//tilaukset toimituspaikan mukaan
	public List<MyOrder> getOrdersByPlace(String place){
		List<MyOrder> orders = null; 
		
		orders = em.createNamedQuery("searchOrdersByPlace").setParameter("place", place).getResultList();
		// testing
		System.out.println("***********searchOrdersByPlace  ********** ");
				for (MyOrder o : orders) {
		            System.out.println(o.getId());
		            System.out.println(o.getSalesPerson());
		            System.out.println(o.getDeliveryDate());
		            System.out.println(o.getDeliveryPlace());
				}
		return orders;
		
	}
	
	//tilauksen asiakkaan mukaan
	public List<MyOrder> getOrdersByCustomer(long customerId){
		List<MyOrder> orders = null; 
		
		orders = em.createNamedQuery("searchOrdersByCustomer").setParameter("customerId", customerId).getResultList();
		// testing
		System.out.println("***********searchOrdersByCustomer  ********** ");
				for (MyOrder o : orders) {
		            System.out.println(o.getId());
		            System.out.println(o.getSalesPerson());
		            System.out.println(o.getDeliveryDate());
		            System.out.println(o.getDeliveryPlace());
		            System.out.println(o.getCustomer());
				}
		return orders;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<OrderObject> getOrderObjects() {
		List<OrderObject> orderObjects = null; 
		// get all orders from the database
		orderObjects = em.createNamedQuery("searchAllOrderObjects").getResultList();
		return orderObjects;

	}
	
	@SuppressWarnings("unchecked")
	public List<OrderObject> getOrderObjectsByName(String name) {
		//Haetaan tuotteita tuotteen nimen perusteella
		TypedQuery<OrderObject> query = em.createQuery(
		        "SELECT o FROM OrderObject o WHERE o.name LIKE :name", OrderObject.class);
		    List<OrderObject> tuloslista = query.setParameter("name", name).getResultList();

		return tuloslista;
	}
	
	@SuppressWarnings("unchecked")
	public List<OrderObject> getOrderObjectsByState(String state) {
		//Haetaan tuotteita tuotteen tilan perusteella
		TypedQuery<OrderObject> query = em.createQuery(
		        "SELECT o FROM OrderObject o WHERE o.state LIKE :name", OrderObject.class);
		    List<OrderObject> tuloslista = query.setParameter("name", state).getResultList();

		return tuloslista;
	}
	
	@SuppressWarnings("unchecked")
	public List<OrderObject> getOrderObjectsByCompany(long companyId) {
		//Haetaan tuotteita tuotteen yrityksen perusteella
		TypedQuery<OrderObject> query = em.createQuery(
		        "SELECT o FROM OrderObject o WHERE o.order.company.id LIKE :id", OrderObject.class);
		    List<OrderObject> tuloslista = query.setParameter("id", companyId).getResultList();

		return tuloslista;
	}
	
	@SuppressWarnings("unchecked")
	public List<OrderObject> getOrderObjectsByDeliveryPlace(String deliveryPlace) {
		//Haetaan tuotteita tuotteen tilauksen toimituspaikan mukaan
		TypedQuery<OrderObject> query = em.createQuery(
		        "SELECT o FROM OrderObject o WHERE o.order.deliveryPlace LIKE :place", OrderObject.class);
		    List<OrderObject> tuloslista = query.setParameter("place", deliveryPlace).getResultList();

		return tuloslista;
	}
	
	@SuppressWarnings("unchecked")
	public List<OrderObject> getOrderObjectsByDate(String date) {
		//Haetaan tuotteita tuotteen tilauksen toimituspäivämäärän  mukaan
		TypedQuery<OrderObject> query = em.createQuery(
		        "SELECT o FROM OrderObject o WHERE o.order.deliveryDate LIKE :date", OrderObject.class);
		    List<OrderObject> tuloslista = query.setParameter("date", date).getResultList();

		return tuloslista;
	}
	
	@SuppressWarnings("unchecked")
	public List<OrderObject> getOrderObjectsByCustomer(long customerId) {
		//Haetaan tuotteita tuotteen tilauksen asiakkaan mukaan
		
		TypedQuery<OrderObject> query = em.createQuery(
		        "SELECT o FROM OrderObject o WHERE o.order.customer.id LIKE :id", OrderObject.class);
		    List<OrderObject> tuloslista = query.setParameter("id", customerId).getResultList();

		return tuloslista;
	}

	
	//tallentaa yrityksen tiedot
	public void saveCompany(Company company) throws SQLException {
		/*int result = 0;
		ResultSet rs;
		rs = (ResultSet) em.createQuery("SELECT COUNT(company.businessId) AS result FROM Company company WHERE company.businessId=:businessId");
		
		rs.getInt(result);
		
		if (result == 0)
		{*/
			try {
				em.persist(company);
			} catch (Exception e) {
				e.printStackTrace();
			}
		/*}
		else
			
			{
			String viesti = "Yritys on jo olemassa";
			FacesMessage facesMessage = new FacesMessage(viesti);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, facesMessage);
			}*/
		
		}
	
	@SuppressWarnings("unchecked")
	public List<Company> getCompanies() {
		List<Company> companies = null; 
		// get all companie from the database
		companies = em.createNamedQuery("searchAllCompanies").getResultList();
		return companies;

	}


}
