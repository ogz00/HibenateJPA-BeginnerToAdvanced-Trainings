package org.oguz.orm.data;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.Session;
import org.oguz.orm.data.entities.Account;
import org.oguz.orm.data.entities.Address;
import org.oguz.orm.data.entities.Bank;
import org.oguz.orm.data.entities.Budget;
import org.oguz.orm.data.entities.Credential;
import org.oguz.orm.data.entities.TimeTest;
import org.oguz.orm.data.entities.User;
import org.oguz.orm.data.entities.Transaction;

public class Application {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		// session.beginTransaction();
		try {
			// session.getTransaction().begin();
			org.hibernate.Transaction transaction = session.beginTransaction();

			User user = createUser();
			User user2 = createUser();
;
			 //session.save(user);

			Credential credential = createCredential(user);
			Credential credential2 = createCredential(user2);

			session.save(credential);
			session.save(credential2);

			// TimeTest test = new TimeTest(new Date());
			// session.save(test);

			Bank bank = createBank();

			Account account = createNewAccount();
			Account account2 = createNewAccount();

			account.getUsers().add(user);
			account.getUsers().add(user2);
			account2.getUsers().add(user);
			account2.getUsers().add(user2);
			
			//user.getAccounts().add(account);

			account.getTransactions().add(createNewBeltPurchase(account));
			account.getTransactions().add(createShoePurchase(account));

			bank.getAccount().add(account);
			bank.getAccount().add(account2);
			
			session.save(bank);
			//session.save(account);
			//session.save(account2);

			// session.refresh(account);

			Budget budget = createBudget();

			budget.getTransactions().add(account.getTransactions().get(0));
			budget.getTransactions().add(account.getTransactions().get(1));

			session.save(budget);

			// session.getTransaction().commit();
			transaction.commit();
			
			//Account dbAccount =(Account)session.get(Account.class, account.getAccountId());
			//System.out.println(dbAccount.getUsers().iterator().next().getEmailAddress());

			// session.refresh(bank);
			// System.out.println(bank.toString());

			// session.refresh(user);
			// System.out.println(user.getAge());
			// session.refresh(test);
			// System.out.println(test.toString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			HibernateUtil.getSessionFactory().close();
		}
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
