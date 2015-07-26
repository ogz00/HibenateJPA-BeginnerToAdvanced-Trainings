package org.oguz.orm.data;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.oguz.orm.data.entities.Account;
import org.oguz.orm.data.entities.AccountType;
import org.oguz.orm.data.entities.Address;
import org.oguz.orm.data.entities.Bank;
import org.oguz.orm.data.entities.Bond;
import org.oguz.orm.data.entities.Budget;
import org.oguz.orm.data.entities.Credential;
import org.oguz.orm.data.entities.Currency;
import org.oguz.orm.data.entities.Investment;
import org.oguz.orm.data.entities.Market;
import org.oguz.orm.data.entities.Portfolio;
import org.oguz.orm.data.entities.Stock;
import org.oguz.orm.data.entities.TimeTest;
import org.oguz.orm.data.entities.User;
import org.oguz.orm.data.entities.Transaction;
import org.oguz.orm.data.entities.UserCredentialView;
import org.oguz.orm.data.entities.ids.CurrencyId;

public class Application {

	public static void main(String[] args) {

		SessionFactory sessionFactory = null;
		Session session = null;
		org.hibernate.Transaction transaction = null;

		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			/*
			 * Bank detachedBank = (Bank)session.get(Bank.class, 1L);
			 * transaction.commit(); session.close(); Session session2 =
			 * HibernateUtil.getSessionFactory().openSession();
			 * org.hibernate.Transaction transaction2 =
			 * session2.beginTransaction(); session2.saveOrUpdate(detachedBank);
			 * detachedBank.setName("Test Bank 2"); transaction2.commit();
			 * session2.close();
			 */

			/*
			 * session.update(bank1); System.out.println("Method executed");
			 * System.out.println(bank1.getName()); bank1 =
			 * (Bank)session.get(Bank.class, 1L); Bank bank1 =
			 * (Bank)session.load(Bank.class, 1L);
			 * bank1.setName("New Hope Bank");
			 * bank1.setLastUpdatedBy("Oğuzhan Karacullu");
			 * bank1.setLastUpdatedDate(new Date());
			 * System.out.println("Method executed");
			 * System.out.println(bank1.getName());
			 * System.out.println(session.contains(bank1));
			 * session.delete(bank1); System.out.println("Method Invoked");
			 * System.out.println(session.contains(bank1));
			 */

			Bank bank = createBank();
			User user = createUser();
			User user2 = createUser();
			Account account = createNewAccount();
			
			session.save(user);

			Credential credential = createCredential(user);
			Credential credential2 = createCredential(user2);

			session.save(credential);
			session.save(credential2);
			//session.flush();

			// TimeTest test = new TimeTest(new Date());
			// session.save(test);			

			
			account.setAccountType(AccountType.SAVINGS);
			//account.setBank(bank);
			Account account2 = createNewAccount();
			account2.setAccountType(AccountType.LOAN);

			account.getUsers().add(user);
			account.getUsers().add(user2);
			account2.getUsers().add(user);
			account2.getUsers().add(user2);

			// user.getAccounts().add(account);
			Transaction trans1 = createNewBeltPurchase(account);
			Transaction trans2 = createShoePurchase(account);
			account.getTransactions().add(trans1);
			account.getTransactions().add(trans2);

			bank.getAccount().add(account);
			bank.getAccount().add(account2);

			// System.out.println(session.contains(account));
			// session.save(account);
			// session.refresh(account);

			session.save(bank);
			Budget budget = createBudget();

			budget.getTransactions().add(account.getTransactions().get(0));
			budget.getTransactions().add(account.getTransactions().get(1));

			 session.save(budget);

			/*Currency curr = new Currency();
			curr.setCountryName("United States");
			curr.setName("Dollar");
			curr.setSymbol("$");
			session.persist(curr);*/

			Currency dbCurr = (Currency) session.load(Currency.class,
					new CurrencyId("Dollar", "United States"));
			System.out.println(dbCurr.getSymbol());
			
			Market market = new Market();
			market.setMarketName("London Stock Exchange");
			market.setCurrency(dbCurr);
			
			session.persist(market);
			
			//Market dbMarket = (Market)session.get(Market.class, market.getMarketId());
			//System.out.println(dbMarket.getCurrency().getCountryName());
			
			
			Portfolio portfolio = new Portfolio();
			portfolio.setName("Second Invetments");
			
			Stock stock = createStock();
			stock.setPortfolio(portfolio);
			Bond bond = createBond();
			bond.setPortfolio(portfolio);
			
			portfolio.getInvestment().add(stock);
			portfolio.getInvestment().add(bond);
			
			session.save(stock);
			session.save(bond);
			
			UserCredentialView view = (UserCredentialView)session.get(UserCredentialView.class,1L);
			System.out.println(view.getFirstName());
			System.out.println(view.getLastName());
			
			transaction.commit();
			
			/*Portfolio dbPortfolio = (Portfolio) session.get(Portfolio.class, portfolio.getPortfolioId());
			session.refresh(dbPortfolio);
			
			for (Investment i:dbPortfolio.getInvestment()){
				System.out.println(i.getName()); 
			}*/

			// Account dbAccount =(Account)session.get(Account.class,
			// account.getAccountId());
			// System.out.println(dbAccount.getUsers().iterator().next().getEmailAddress());

			// session.refresh(bank);
			// System.out.println(bank.toString());

			// session.refresh(user);
			// System.out.println(user.getAge());

		} catch (ObjectNotFoundException e) {
			e.printStackTrace();
			Bank bank1 = (Bank) session.load(Bank.class, 1L);
			System.out.println("Method executed");
			System.out.println(bank1.getName());
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			// session.close();
			HibernateUtil.getSessionFactory().close();
		}
	}
	
	private static Bond createBond() {
		Bond bond = new Bond();
		bond.setInterestRate(new BigDecimal("123.22"));
		bond.setIssuer("JP Morgan Chase");
		bond.setMaturityDate(new Date());
		bond.setPurchaseDate(new Date());
		bond.setName("Long Term Bond Purchases");
		bond.setValue(new BigDecimal("10.22"));
		return bond;
	}

	private static Stock createStock(){
		Stock stock = new Stock();
		stock.setIssuer("Allen Edmonds");
		stock.setName("Private American Stock Purchases");
		stock.setPurchaseDate(new Date());
		stock.setQuantity(new BigDecimal("1922"));
		stock.setSharePrice(new BigDecimal("100.00"));
		return stock;
	}

	private static Budget createBudget() {
		Budget budget = new Budget();
		budget.setGoalAmount(new BigDecimal("10000.00"));
		budget.setName("Emergency Fund");
		budget.setPeriod("Yearly");
		return budget;
	}

	private static Bank createBank() {
		Bank bank = new Bank();
		bank.setName("Federal Trust");
		bank.setAddressLine1("33 Wall Street");
		bank.setAddressLine2("Suite 233");
		bank.setCity("New York");
		bank.setState("NY");
		bank.setZipCode("12345");
		bank.setInternational(false);
		bank.setCreatedBy("Oguz");
		bank.setCreatedDate(new Date());
		bank.setLastUpdatedBy("Oguz");
		bank.setLastUpdatedDate(new Date());
		bank.getContacts().put("MANAGER", "Mary");
		bank.getContacts().put("TELLER", "Joe");
		return bank;
	}

	private static User createUser() {
		User user = new User();
		// Address address = createAddress();
		user.setAddresses(Arrays.asList(new Address[] { createAddress() }));
		user.setBirthday(getMyBirthday());
		user.setCreatedBy("Oguz");
		user.setCreatedDate(new Date());
		user.setEmailAddress("oguzhan.karacullu@gmail.com");
		user.setFirstName("oguz");
		user.setLastName("karacullu");
		user.setLastUpdatedBy("ogz00");
		user.setLastUpdatedDate(new Date());
		return user;
	}

	private static Address createAddress() {
		Address address = new Address();
		address.setAddressLine1("101 Address Line");
		address.setAddressLine2("102 Address Line");
		address.setCity("New York");
		address.setState("PA");
		address.setZipCode("10000");
		address.setAddressType("PRIMARY");
		return address;
	}

	private static Account createNewAccount() {
		Account account = new Account();
		account.setCloseDate(new Date());
		account.setOpenDate(new Date());
		account.setCreatedBy("Oguzhan Karacullu");
		account.setInitialBalance(new BigDecimal("50.00"));
		account.setName("Savings Account");
		account.setCurrentBalance(new BigDecimal("100.00"));
		account.setLastUpdatedBy("Oguzhan Karacullu");
		account.setLastUpdatedDate(new Date());
		account.setCreatedDate(new Date());
		return account;
	}

	private static Credential createCredential(User user) {
		Credential credential = new Credential();
		credential.setUser(user);
		credential.setUsername("test_username");
		credential.setPassword("test_password");
		return credential;
	}

	private static Transaction createShoePurchase(Account account) {
		Transaction shoePurchase = new Transaction();
		shoePurchase.setAccount(account);
		shoePurchase.setTitle("Work Shoes");
		shoePurchase.setAmount(new BigDecimal("100.00"));
		shoePurchase.setClosingBalance(new BigDecimal("0.00"));
		shoePurchase.setCreatedBy("Oguzhan Karacullu");
		shoePurchase.setCreatedDate(new Date());
		shoePurchase.setInitialBalance(new BigDecimal("0.00"));
		shoePurchase.setLastUpdatedBy("Oguzhan Karacullu");
		shoePurchase.setLastUpdatedDate(new Date());
		shoePurchase.setNotes("Nice Pair of Shoes");
		shoePurchase.setTransactionType("Debit");
		return shoePurchase;
	}

	private static Transaction createNewBeltPurchase(Account account) {
		Transaction beltPurchase = new Transaction();
		beltPurchase.setAccount(account);
		beltPurchase.setTitle("Dress Belt");
		beltPurchase.setAmount(new BigDecimal("50.00"));
		beltPurchase.setClosingBalance(new BigDecimal("0.00"));
		beltPurchase.setCreatedBy("Oguzhan Karacullu");
		beltPurchase.setCreatedDate(new Date());
		beltPurchase.setInitialBalance(new BigDecimal("0.00"));
		beltPurchase.setLastUpdatedBy("Oguzhan Karacullu");
		beltPurchase.setLastUpdatedDate(new Date());
		beltPurchase.setNotes("New Dress Belt");
		beltPurchase.setTransactionType("Debit");
		return beltPurchase;
	}

	private static Date getMyBirthday() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 1990);
		calendar.set(Calendar.MONTH, 4);
		calendar.set(Calendar.DATE, 21);
		return calendar.getTime();
	}

}
