package pl.condigitall.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.condigitall.demo.inection.Client;

@SpringBootApplication
public class SpringDemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringDemoApplication.class, args);
//
//		Configuration cfg = new Configuration();
//		cfg.configure("hibernate.cfg.xml");
//
//		SessionFactory factory = cfg.buildSessionFactory();
//		Session session = factory.openSession();
//		Client client = new Client();
//
//		client.setConactID(2);
//		client.setName("Hibernate");
//		Transaction tx = session.beginTransaction();
//		session.save(client);
//		System.out.println("Object saved successfully.....!!");
//		tx.commit();
//		session.close();
//		factory.close();
	}

}
