package org.oguz.orm.data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.oguz.orm.data.entities.Account;
import org.oguz.orm.data.entities.Transaction;

public class HqlApplication {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		SessionFactory factory = null;
		Session session = null;
		org.hibernate.Transaction tx = null;
		try {
			factory = HibernateUtil.getSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();

			/*Query query = session
					.createQuery("select t from Transaction t"
							+ " where t.amount > :amount and transactionType= 'Withdrawl'");
			System.out.println("Please specify an amount"); 
			query.setParameter("amount", new BigDecimal(scanner.next()));*/
			// query.setParameter(0, new BigDecimal(scanner.next()));
			//List<Transaction> transactions = query.list();
			Query query = session.createQuery("select distinct t.account from Transaction t "
					+ " where t.amount > 400 and lower(t.transactionType) ='deposit'");
			
			List<Account> accounts = query.list();

			for (Account a : accounts) {
				System.out.println(a.getName());
			}

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
			factory.close();
		}
	}

}
