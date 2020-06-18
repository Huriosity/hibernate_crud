package dao;

import com.github.Huriosity.InputUtils;
import model.Country;
import model.Ruler;
import model.RulerCountryRel;
import model.RullerMainTitleRel;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

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
}
