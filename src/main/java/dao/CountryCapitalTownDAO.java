package dao;

import com.github.Huriosity.InputUtils;
import model.Country;
import model.CountryCapitalTownRel;
import model.RulerCountryRel;
import model.Town;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.Scanner;

@Entity
@Table(name = "town")
public class CountryCapitalTownDAO {

    public Integer addCountryCapital(){
        int townID;
        int countryID;

        Integer capital_start_year;
        Integer capital_end_year;

        System.out.println("Введите countryID");
        countryID = InputUtils.getInt();

        System.out.println("Введите townID");
        townID = InputUtils.getInt();

        while (true){
            System.out.println("Введите capital_start_year");
            capital_start_year = InputUtils.getInteger();

            System.out.println("Введите capital_end_year");
            capital_end_year = InputUtils.getInteger();

            if (capital_start_year != null && capital_end_year != null) {
                if (capital_start_year > capital_end_year) {
                    System.out.println("Остоpожнее молодой человек! capital_start_year > capital_end_year. Введите заного");
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        Session session = Factory.getSessionFactory().openSession();
        Transaction transaction = null;

        Integer countryCapitalId = null;

        transaction = session.beginTransaction();

        CountryCapitalTownRel countryCapitalTownRel = new CountryCapitalTownRel();
        countryCapitalTownRel.setCountry_id(countryID);
        // countryCapitalTownRel.setTown_id(townID);
        countryCapitalTownRel.setCapital_start_year(capital_start_year);
        countryCapitalTownRel.setCapital_end_year(capital_end_year);

        Country country = CountryDAO.findCountryByID(countryID);
        country.addCountryTownRel(countryCapitalTownRel);
        countryCapitalTownRel.setCountry(country);
        // tut setCountry

        Town town = TownDAO.findTownByID(townID);
        town.addCountryTownRel(countryCapitalTownRel);
        countryCapitalTownRel.setTown(town);

        countryCapitalId =  (Integer) session.save(countryCapitalTownRel);

        transaction.commit();
        session.close();

        return countryCapitalId;
    }

    public void updateCountryCapital(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter coutnry id");
        int countryID = InputUtils.getInt();

        System.out.println("Enter town id");
        int townID = InputUtils.getInt();

        CountryCapitalTownRel countryCapitalTownRel = CountryCapitalTownDAO.findCountryCapitals(countryID, townID);
        System.out.println("find tis countryCapitalTownRel: ");
        System.out.println(countryCapitalTownRel);

        while (true){
            System.out.println("Would you like to change the capital town? (y/n)");
            String choose = scanner.nextLine();
            if(choose.equals("y")){
                // System.out.println("Old country id: " + rulerCountryRel.getCountry_id());
                System.out.println("Old capital town id: " + countryCapitalTownRel.getTown().getId());
                System.out.println("Enter new town id: ");
                int newTownID = InputUtils.getInt();
                Town town = TownDAO.findTownByID(newTownID);

                System.out.println("find tis town: ");
                System.out.println(town);
                countryCapitalTownRel.setTown(town);
                break;
            } else if(choose.equals("n")){
                break;
            }
        }

        while (true){
            System.out.println("Would you like to change the capital_start_year? (y/n)");
            String choose = scanner.nextLine();
            if(choose.equals("y")){
                System.out.println("Old capital_start_year: " + countryCapitalTownRel.getCapital_start_year());
                System.out.println("Enter new capital_start_year: ");
                countryCapitalTownRel.setCapital_start_year(InputUtils.getInteger());
                break;
            } else if(choose.equals("n")){
                break;
            }
        }

        while (true){
            System.out.println("Would you like to change the capital_end_year? (y/n)");
            String choose = scanner.nextLine();
            if(choose.equals("y")){
                System.out.println("Old capital_end_year: " + countryCapitalTownRel.getCapital_end_year());
                System.out.println("Enter new capital_end_year: ");
                countryCapitalTownRel.setCapital_end_year(InputUtils.getInteger());
                break;
            } else if(choose.equals("n")){
                break;
            }
        }

        Session session = Factory.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        session.update(countryCapitalTownRel);

        transaction.commit();
        session.close();

    }

    public void listCountryCapitals() {
        Session session = Factory.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        List<CountryCapitalTownRel> countryCapitalTowns = session.createQuery("FROM CountryCapitalTownRel").list();
        for (CountryCapitalTownRel countryCapital : countryCapitalTowns) {
            System.out.println(countryCapital);
            System.out.println("\n================\n");
        }
        session.close();
    }

    public static CountryCapitalTownRel findCountryCapitals(int countryID, int townID) {
        Session session = Factory.getSessionFactory().openSession();
        // Query query = session.createQuery("FROM CountryCapitalTownRel WHERE country_id = :id AND town_id = :id2");
        Query query = session.createQuery("FROM CountryCapitalTownRel WHERE country_id = :id");
        query.setParameter("id", countryID);
        //query.setParameter("id2",townID);

        List<CountryCapitalTownRel> countryCapitalTownRels =query.list();
        System.out.println("Список найденых записей:");
        for (CountryCapitalTownRel countryCapitalTownRel : countryCapitalTownRels) {
            System.out.println(countryCapitalTownRel);
            System.out.println("\n================\n");
        }

        System.out.println("выбеpить id нyжной вам записи");

        int recordID = InputUtils.getInt();

        Query query2 = session.createQuery("FROM CountryCapitalTownRel WHERE id = :id3");
        query2.setParameter("id3", recordID);

        CountryCapitalTownRel countryCapitalTownRel = (CountryCapitalTownRel) query2.getSingleResult();

        session.close();

        return countryCapitalTownRel;
    }


}
