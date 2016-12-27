package CRM;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;




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
		em.persist(testiOrder);
		
		MyOrder testiOrder2 = new MyOrder();
		testiOrder2.setSalesPerson("Matti Myyntimies");
		testiOrder2.setCompany(testiCompany);
		em.persist(testiOrder2);
		 
		
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
	
	public void deleteAccount(Account account){
		//käyttäjätilin poistaminen
		try{
			em.remove(account);
			System.out.println("Account deleted: "+account);
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
	
	@SuppressWarnings("unchecked")
	public List<MyOrder> getOrders() {
		List<MyOrder> orders = null; 
		// get all orders from the database
		orders = em.createNamedQuery("searchAllOrders").getResultList();
		return orders;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<OrderObject> getOrdersObjects() {
		List<OrderObject> orderObjects = null; 
		// get all orders from the database
		orderObjects = em.createNamedQuery("searchAllOrderObjects").getResultList();
		return orderObjects;

	}
	
	//tallentaa yrityksen tiedot
	public void saveCompany(Company company) {
		try {
			em.persist(company);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Company> getCompanies() {
		List<Company> companies = null; 
		// get all companie from the database
		companies = em.createNamedQuery("searchAllCompanies").getResultList();
		return companies;

	}

}
