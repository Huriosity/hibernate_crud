package dao;

import com.github.Huriosity.InputUtils;
import model.Ruler;
import model.Title;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class TitleDAO {
    public void start() {
        System.out.println((System.getProperty("user.dir")));
        SessionFactory sessionFactory = Factory.getSessionFactory(); ////////////// &&&&&&&&&&&&&&&&&&&

        TitleDAO titleDAO = new TitleDAO();

        System.out.println("Test  case");

        titleDAO.addTitle();

        System.out.println("List of Title:");
        titleDAO.listTitles();
    }

    // public Integer addTitle(String name){
    public Integer addTitle(){
        String name = "";

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите имя ");
        name = scanner.nextLine();

        Session session = Factory.getSessionFactory().openSession();
        Transaction transaction = null;

        Integer titleId = null;

        transaction = session.beginTransaction();

        Title title = new Title();
        title.setName(name);

        titleId = (Integer) session.save(title);

        transaction.commit();
        session.close();

        return titleId;
    }

    public void updateTitle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Title id");
        int id = InputUtils.getInt();

        Title title = TitleDAO.findTitleByID(id);

        while (true){
            System.out.println("Would you like to change the title name? (y/n)");
            String choose = scanner.nextLine();
            if(choose.equals("y")){
                System.out.println("Old name: " + title.getName());
                System.out.println("Enter new name: ");
                title.setName(scanner.nextLine());
                break;
            } else if(choose.equals("n")){
                break;
            }
        }

        Session session = Factory.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        session.update(title);

        transaction.commit();
        session.close();
    }

    public void listTitles() {
        Session session = Factory.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        List<Title> titles = session.createQuery("FROM Title").list();
        for (Title title : titles) {
            System.out.println(title);
            System.out.println("\n================\n");
        }
        session.close();
    }

    public static Title findTitleByID(int id) {
        Session session = Factory.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Title WHERE id = :id");
        query.setParameter("id", id);
        Title title = (Title) query.getSingleResult();

        System.out.println("ХОБА");
        System.out.println(title);

        session.close();

        return title;
    }
}
