package org.oguz.orm.data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.oguz.orm.data.entities.Account;
import org.oguz.orm.data.entities.Transaction;

public class JpqlApplication {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		EntityManagerFactory factory = null;
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			factory = Persistence.createEntityManagerFactory("oguz-finances");
			em = factory.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			/*TypedQuery<Transaction> query = em.createQuery(
					"from Transaction t "
					+ "where (t.amount between ?1 and ?2)"
					+ "and t.title like '%s'"
					+ " order by t.title", Transaction.class);
			System.out.println("Please provide the first amount");
			query.setParameter(1, new BigDecimal(scanner.next()));
			System.out.println("Please provide the second amount");
			query.setParameter(2, new BigDecimal(scanner.next()));
			List<Transaction> transactions = query.getResultList();

			for (Transaction t : transactions) {
				System.out.println(t.getTitle());
			}
			
			TypedQuery<Account> query1 = em.createQuery("select distinct a from Transaction t"
					+ " join t.account a "
					+ " where t.amount > 400 and "
					+ " t.transactionType = 'Deposit'", Account.class);
					
			List<Account> accounts = query1.getResultList();
			
			
			for(Account a: accounts){
				System.out.println(a.getName());
			}*/
		//	Query query2 = em.createQuery("select distinct t.account.name,"
		//			+ " concat(concat(t.account.bank.name,' '),t.account.bank.address.state) "
		//			+ "from Transaction t"
		//			+ " where t.amount > :amount and "
		//			+ " t.transactionType = 'withdrawl'");
		//			query.setParameter("amount", new BigDecimal("99"));	
			
			Query query2 = em.createNamedQuery("Account.byWithdrawlAmount");
			query2.setParameter("amount", new BigDecimal("99"));
			
			List <Object[]> accounts2 = query2.getResultList();
			
			for(Object[] a: accounts2){
				System.out.println(a[0]);
				System.out.println(a[1]);
			}
			
			

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			factory.close();
		}
	}
}
