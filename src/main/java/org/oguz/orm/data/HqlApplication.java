package org.oguz.orm.data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.oguz.orm.entities.Account;
import org.oguz.orm.entities.Transaction;

public class HqlApplication {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		SessionFactory factory = null;
		Session session = null;
		org.hibernate.Transaction tx = null;
		int pageNumber = 3;
		int pageSize = 2;
		try {
			factory = HibernateUtil.getSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();

			// Query query = session
			// .createQuery("select t from Transaction t"
			// + " where t.amount > :amount and transactionType= 'Withdrawl'");
			// System.out.println("Please specify an amount");
			// query.setParameter("amount", new BigDecimal(scanner.next()));
			// query.setParameter(0, new BigDecimal(scanner.next()));
			// List<Transaction> transactions = query.list();
			// Query query =
			// session.createQuery("select distinct t.account from Transaction t "
			// +
			// " where t.amount > 400 and lower(t.transactionType) ='deposit'");

			Query query = session.getNamedQuery("Account.largeDeposits");
			List<Account> accounts = query.list();

			System.out.println("Query has been executed.");
			for (Account a : accounts) {
				System.out.println(a.getName());
				System.out.println(a.getBank().getName());
			}
			Criterion criterion1 = Restrictions.le("amount", new BigDecimal(
					"20.00"));
			Criterion criterion2 = Restrictions.eqOrIsNull("transactionType",
					"Withdrawl");

			Criteria criteria = session.createCriteria(Transaction.class)
					.add(Restrictions.and(criterion1, criterion2))
					.addOrder(Order.desc("title"));
			// criteria.setFirstResult((pageNumber-1)*pageSize);
			// criteria.setMaxResults(pageSize);

			List<Transaction> transactions = criteria.list();

			for (Transaction t : transactions) {
				System.out.println(t.getTitle());
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
