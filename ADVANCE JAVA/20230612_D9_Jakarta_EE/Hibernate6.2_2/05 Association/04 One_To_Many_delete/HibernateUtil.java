package mypack;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static final SessionFactory sessionFactory; 
   static { 
        try { 
        	 // Create registry
        	 ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        	
        	 // Create MetadataSources
             MetadataSources sources = new MetadataSources(registry);
             // Create Metadata
             Metadata metadata = sources.getMetadataBuilder().build();
             // Create SessionFactory
             sessionFactory = metadata.getSessionFactoryBuilder().build();
        } catch (Throwable ex) { 
            System.err.println("Initial SessionFactory creation failed." + ex); 
            throw new ExceptionInInitializerError(ex); 
        } 
    } 
  
    public static SessionFactory getSessionFactory() { 
        return sessionFactory; 
    } 
}











