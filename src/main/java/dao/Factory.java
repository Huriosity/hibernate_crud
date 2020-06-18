package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Factory {
    private static SessionFactory sessionFactoryObj = null;

    private static SessionFactory buildSessionFactory() {

        // Creating Hibernate SessionFactory Instance
        sessionFactoryObj = new Configuration().configure().buildSessionFactory();
        return sessionFactoryObj;
    }

    public static SessionFactory getSessionFactory() {
        if ( sessionFactoryObj == null ) {
            return buildSessionFactory();
        }

        return sessionFactoryObj;
    }

    public static void closeSessionFactory() {
        if(sessionFactoryObj == null) {

        } else {
            sessionFactoryObj.close();
        }

    }

}
