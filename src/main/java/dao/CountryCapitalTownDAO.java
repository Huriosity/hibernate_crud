package dao;

import com.github.Huriosity.InputUtils;
import model.Country;
import model.CountryCapitalTownRel;
import model.RulerCountryRel;
import model.Town;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

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
        countryCapitalTownRel.setTown_id(townID);
        countryCapitalTownRel.setCapital_start_year(capital_start_year);
        countryCapitalTownRel.setCapital_end_year(capital_end_year);

        Country country = CountryDAO.findCountryByID(countryID);
        country.addCountryTownRel(countryCapitalTownRel);

        Town town = TownDAO.findTownByID(townID);
        town.addCountryTownRel(countryCapitalTownRel);

        countryCapitalId =  (Integer) session.save(countryCapitalTownRel);

        transaction.commit();
        session.close();

        return countryCapitalId;
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

}
