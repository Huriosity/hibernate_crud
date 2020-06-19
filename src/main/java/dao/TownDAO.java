package dao;

import model.Town;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class TownDAO {

    public Integer addTown(){
        String name = "";

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите название гоpода ");
        name = scanner.nextLine();

        Session session = Factory.getSessionFactory().openSession();
        Transaction transaction = null;

        Integer townId = null;

        transaction = session.beginTransaction();

        Town town = new Town();
        town.setName(name);

        townId = (Integer) session.save(town);

        transaction.commit();
        session.close();

        return townId;
    }

    public void listTowns() {
        Session session = Factory.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        List<Town> towns = session.createQuery("FROM Town").list();
        for (Town town : towns) {
            System.out.println(town);
            System.out.println("\n================\n");
        }
        session.close();
    }

    public static Town findTownByID(int id) {
        Session session = Factory.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Town WHERE id = :id");
        query.setParameter("id", id);
        Town town = (Town) query.getSingleResult();

        System.out.println("ХОБА");
        System.out.println(town);

        session.close();

        return town;
    }


}
