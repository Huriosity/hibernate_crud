package dao;

import com.github.Huriosity.InputUtils;
import model.Country;
import model.Ruler;
import model.Title;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class CountryDAO {

    public Integer addCountry(){
        String name = "";

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите название стpаны ");
        name = scanner.nextLine();

        Session session = Factory.getSessionFactory().openSession();
        Transaction transaction = null;

        Integer countryId = null;

        transaction = session.beginTransaction();

        Country country = new Country();
        country.setName(name);

        countryId = (Integer) session.save(country);

        transaction.commit();
        session.close();

        return countryId;
    }

    public void updateCountry() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter country id");
        int id = InputUtils.getInt();

        Country country = CountryDAO.findCountryByID(id);

        while (true){
            System.out.println("Would you like to change the country name? (y/n)");
            String choose = scanner.nextLine();
            if(choose.equals("y")){
                System.out.println("Old name: " + country.getName());
                System.out.println("Enter new name: ");
                country.setName(scanner.nextLine());
                break;
            } else if(choose.equals("n")){
                break;
            }
        }

        Session session = Factory.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        session.update(country);

        transaction.commit();
        session.close();
    }

    public void deleteCountry(){
        //Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Country id");
        int id = InputUtils.getInt();
        Country country = CountryDAO.findCountryByID(id);

        Session session = Factory.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        session.delete(country);

        transaction.commit();
        session.close();
    }

    public void listCountrys() {
        Session session = Factory.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        List<Country> countries = session.createQuery("FROM Country").list();
        for (Country country : countries) {
            System.out.println(country);
            System.out.println("\n================\n");
        }
        session.close();
    }

    public static Country findCountryByID(int id) {
        Session session = Factory.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Country WHERE id = :id");
        query.setParameter("id", id);
        Country country = (Country) query.getSingleResult();

        System.out.println("ХОБА");
        System.out.println(country);

        session.close();

        return country;
    }
}
