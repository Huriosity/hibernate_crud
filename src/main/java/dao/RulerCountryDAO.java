package dao;

import com.github.Huriosity.InputUtils;
import model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class RulerCountryDAO {

    public Integer addRulerCountry(){
        int rulerID;
        int countryID;

        Integer year_of_reign;
        Integer year_end_of_reign;

        System.out.println("Введите rulerID");
        rulerID = InputUtils.getInt();

        System.out.println("Введите countryID");
        countryID = InputUtils.getInt();

        while (true){
            System.out.println("Введите year_of_reign");
            year_of_reign = InputUtils.getInteger();

            System.out.println("Введите year_end_of_reign");
            year_end_of_reign = InputUtils.getInteger();

            if (year_of_reign != null && year_end_of_reign != null) {
                if (year_of_reign > year_end_of_reign) {
                    System.out.println("Остоpожнее молодой человек! year_of_reign > year_end_of_reign. Введите заного");
                } else {
                    break;
                }
            } else {
                break;
            }

        }

        Session session = Factory.getSessionFactory().openSession();
        Transaction transaction = null;

        Integer rulerCountryId = null;

        transaction = session.beginTransaction();

        RulerCountryRel rulerCountryRel = new RulerCountryRel();
        rulerCountryRel.setRuler_id(rulerID);
        rulerCountryRel.setCountry_id(countryID);
        rulerCountryRel.setYear_of_reign(year_of_reign);
        rulerCountryRel.setYear_end_of_reign(year_end_of_reign);

        Ruler ruler = RulerDAO.findRulerByID(rulerID);
        rulerCountryRel.setRuler(ruler);

        Country country = CountryDAO.findCountryByID(countryID);
        country.addRulerCountryRels(rulerCountryRel);

        rulerCountryId = (Integer) session.save(rulerCountryRel);

        transaction.commit();
        session.close();

        return rulerCountryId;
    }

    public void updateRulerCountry(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Ruler id");
        int rulerID = InputUtils.getInt();

        System.out.println("Enter coutnry id");
        int countryID = InputUtils.getInt();

        RulerCountryRel rulerCountryRel = RulerCountryDAO.findRulerCountryRelBy2ID(rulerID,countryID);

        while (true){
            System.out.println("Would you like to change the country? (y/n)");
            String choose = scanner.nextLine();
            if(choose.equals("y")){
                System.out.println("Old country id: " + rulerCountryRel.getCountry_id());
                System.out.println("Enter new country id: ");
                int newCountryID = InputUtils.getInt();
                Country country = CountryDAO.findCountryByID(newCountryID);
                rulerCountryRel.setCountry(country);
                rulerCountryRel.setCountry_id(countryID);
                break;
            } else if(choose.equals("n")){
                break;
            }
        }

        while (true){
            System.out.println("Would you like to change the year_of_reign? (y/n)");
            String choose = scanner.nextLine();
            if(choose.equals("y")){
                System.out.println("Old year_of_reign: " + rulerCountryRel.getYear_of_reign());
                System.out.println("Enter new year_of_reign: ");
                rulerCountryRel.setYear_of_reign(InputUtils.getInteger());
                break;
            } else if(choose.equals("n")){
                break;
            }
        }

        while (true){
            System.out.println("Would you like to change the year_end_of_reign? (y/n)");
            String choose = scanner.nextLine();
            if(choose.equals("y")){
                System.out.println("Old year_end_of_reign: " + rulerCountryRel.getYear_end_of_reign());
                System.out.println("Enter new year_end_of_reign: ");
                rulerCountryRel.setYear_end_of_reign(InputUtils.getInteger());
                break;
            } else if(choose.equals("n")){
                break;
            }
        }

        Session session = Factory.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        session.update(rulerCountryRel);

        transaction.commit();
        session.close();

    }

    public void listRullersCountrys() {
        Session session = Factory.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        List<RulerCountryRel> rulerCountrys = session.createQuery("FROM RulerCountryRel").list();
        for (RulerCountryRel rulerCountry : rulerCountrys) {
            System.out.println(rulerCountry);
            System.out.println("\n================\n");
        }
        session.close();
    }

    public static RulerCountryRel findRulerCountryRelBy2ID(int rulerID, int countryID) {
        Session session = Factory.getSessionFactory().openSession();
        Query query = session.createQuery("FROM RulerCountryRel WHERE ruler_id = :id AND country_id = :id2");
        query.setParameter("id", rulerID);
        query.setParameter("id2",countryID);

        List<RulerCountryRel> rulerCountrys =query.list();
        System.out.println("Список найденых записей:");
        for (RulerCountryRel rulerCountry : rulerCountrys) {
            System.out.println(rulerCountry);
            System.out.println("\n================\n");
        }

        System.out.println("выбеpить id нyжной вам записи");

        int recordID = InputUtils.getInt();

        // RulerCountryRel rulerCountryRel = (RulerCountryRel) query.getSingleResult();

        session.close();

        return findRulerCountryRelByID(recordID);
    }

    public static RulerCountryRel findRulerCountryRelByID(int id){
        Session session = Factory.getSessionFactory().openSession();
        Query query = session.createQuery("FROM RulerCountryRel WHERE id = :id");
        query.setParameter("id", id);

        RulerCountryRel rulerCountryRel = (RulerCountryRel) query.getSingleResult();

        System.out.println("ХОБА");
        System.out.println(rulerCountryRel);

        session.close();

        return rulerCountryRel;

    }
}
