package dao;

import com.github.Huriosity.InputUtils;
import model.Ruler;
import model.RulerCountryRel;
import model.RullerMainTitleRel;
import model.Title;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class RulerTitleDAO {
    public void start() {
        System.out.println((System.getProperty("user.dir")));
        SessionFactory sessionFactory = Factory.getSessionFactory();

        RulerTitleDAO rulerTitleDAO = new RulerTitleDAO();

        System.out.println("Test  case");

        rulerTitleDAO.addRulerMainTitle();

        System.out.println("List of RullerTitles:");
        rulerTitleDAO.listRullersMainTitle();
    }

    public Integer addRulerMainTitle(){

        int rulerID;
        int titleID;
        Integer got_the_title_in;
        Integer lost_the_title_in;

        System.out.println("Введите rulerID");
        rulerID = InputUtils.getInt();

        System.out.println("Введите titleID");
        titleID = InputUtils.getInt();

        while (true){
            System.out.println("Введите got_the_title_in");
            got_the_title_in = InputUtils.getInteger();

            System.out.println("Введите lost_the_title_in");
            lost_the_title_in = InputUtils.getInteger();

            if (got_the_title_in != null && lost_the_title_in != null) {
                if (got_the_title_in > lost_the_title_in) {
                    System.out.println("Остоpожнее молодой человек! got_the_title_in > lost_the_title_in. Введите заного");
                } else {
                    break;
                }
            } else {
                break;
            }

        }


        Session session = Factory.getSessionFactory().openSession();
        Transaction transaction = null;

        Integer rullerMainTitleId = null;

        transaction = session.beginTransaction();

        RullerMainTitleRel rullerMainTitle = new RullerMainTitleRel();
        rullerMainTitle.setRuller_id(rulerID);
        rullerMainTitle.setTitle_id(titleID);
        rullerMainTitle.setGot_the_title_in(got_the_title_in);
        rullerMainTitle.setLost_the_title_in(lost_the_title_in);

        Title rulerTitle = TitleDAO.findTitleByID(titleID);
        rulerTitle.addRullerMainTitleRels(rullerMainTitle);

        //setRuler
        Ruler ruler = RulerDAO.findRulerByID(rulerID);
        rullerMainTitle.setRuler(ruler);


        rullerMainTitleId = (Integer) session.save(rullerMainTitle);

        transaction.commit();
        session.close();

        return rullerMainTitleId;
    }

    public void updateRulerMainTitle(){

    }

    public void listRullersMainTitle() {
        Session session = Factory.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        List<RullerMainTitleRel> rullersMainTitle = session.createQuery("FROM RullerMainTitleRel").list();
        for (RullerMainTitleRel rulerTitle : rullersMainTitle) {
            System.out.println(rulerTitle);
            System.out.println("\n================\n");
        }
        session.close();
    }

    public static RullerMainTitleRel findRulerMainTitleRelBy2ID(int rulerID, int titleID) {
        Session session = Factory.getSessionFactory().openSession();
        Query query = session.createQuery("FROM RullerMainTitleRel WHERE ruller_id = :id AND title_id = :id2");
        query.setParameter("ruller_id", rulerID);
        query.setParameter("title_id",titleID);
        RullerMainTitleRel rullerMainTitleRel = (RullerMainTitleRel) query.getSingleResult();

        System.out.println("ХОБА");
        System.out.println(rullerMainTitleRel);

        session.close();

        return rullerMainTitleRel;
    }
}
