import com.github.Huriosity.InputUtils;
import dao.*;
import model.*;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class UI {

    public static void startMenu(){

        Scanner scanner = new Scanner(System.in);
        String tmp = "";
        while (tmp != null) {
            System.out.println("Выбеpите таблицy: " +
                    "\n1 Ruler " +
                    "\n2 Title" +
                    "\n3 Country" +
                    "\n4 Town" +
                    "\n5 Ruler Title" +
                    "\n6 Ruler Country" +
                    "\n7 Country Town" +
                    "\n12 все связи в конкетном title " +
                    "\n0 exit");
            tmp = scanner.nextLine();
            switch (tmp){
                case "1": {
                    RulerDAO rulerDAO = new RulerDAO();
                    while (tmp != "end"){
                        actionWithTable();
                        tmp = scanner.nextLine();
                        switch (tmp){
                            case "1": {
                                rulerDAO.listRullers();
                                break;
                            }
                            case "2":{
                                rulerDAO.addRuler();
                                /// titl
                                break;
                            }
                            case "3": {
                                System.out.println("Not impl..");
                                break;
                            }
                            case "4": {
                                rulerDAO.updateRuler();
                                break;
                            }
                            case "5": {
                                rulerDAO.deleteRuler();
                                break;
                            }
                            default: {
                                tmp = "end";
                                break;
                            }
                        }
                    }
                    break;
                }
                case "2": {
                    TitleDAO titleDAO = new TitleDAO();

                    while (tmp != "end"){
                        actionWithTable();
                        tmp = scanner.nextLine();
                        switch (tmp){
                            case "1": {
                                titleDAO.listTitles();
                                break;
                            }
                            case "2":{
                                titleDAO.addTitle();
                                break;
                            }
                            case "3": {
                                System.out.println("Введите id искомого элемента");
                                int id = InputUtils.getInt();
                                titleDAO.findTitleByID(id);
                                break;
                            }
                            case "4": {
                                titleDAO.updateTitle();
                                break;
                            }
                            case "5": {
                                titleDAO.deleteTitle();
                                break;
                            }
                            default: {
                                tmp = "end";
                                break;
                            }
                        }
                    }



                    // titleDAO.start();
                    break;
                }
                case "3":{
                    CountryDAO countryDAO = new CountryDAO();
                    while (tmp != "end"){
                        actionWithTable();
                        tmp = scanner.nextLine();
                        switch (tmp){
                            case "1": {
                                countryDAO.listCountrys();
                                break;
                            }
                            case "2":{
                                countryDAO.addCountry();
                                break;
                            }
                            case "3": {
                                System.out.println("Введите id искомого элемента");
                                int id = InputUtils.getInt();
                                countryDAO.findCountryByID(id);
                                break;
                            }
                            case "4": {
                                countryDAO.updateCountry();
                                break;
                            }
                            case "5": {
                                countryDAO.deleteCountry();
                                break;
                            }
                            default: {
                                tmp = "end";
                                break;
                            }
                        }
                    }

                    break;
                }

                case "4": {
                    TownDAO townDAO = new TownDAO();
                    while (tmp != "end"){
                        actionWithTable();
                        tmp = scanner.nextLine();
                        switch (tmp){
                            case "1": {
                                townDAO.listTowns();
                                break;
                            }
                            case "2":{
                                townDAO.addTown();
                                break;
                            }
                            case "3": {
                                System.out.println("Введите id искомого элемента");
                                int id = InputUtils.getInt();
                                townDAO.findTownByID(id);
                                break;
                            }
                            case "4": {
                                townDAO.updateTown();
                                break;
                            }
                            case "5": {
                                townDAO.deleteTown();
                                break;
                            }
                            default: {
                                tmp = "end";
                                break;
                            }
                        }
                    }

                    break;
                }
                case "5": {
                    RulerTitleDAO rulerTitleDAO = new RulerTitleDAO();

                    while (tmp != "end"){
                        actionWithTable();
                        tmp = scanner.nextLine();
                        switch (tmp){
                            case "1": {
                                rulerTitleDAO.listRullersMainTitle();
                                break;
                            }
                            case "2":{
                                rulerTitleDAO.addRulerMainTitle();
                                break;
                            }
                            case "3": {
                                System.out.println("Not impl..");
                                /*System.out.println("Введите id искомого элемента");
                                int id = InputUtils.getInt();
                                titleDAO.findTitleByID(id);*/
                                break;
                            }
                            case "4": {
                                rulerTitleDAO.updateRulerMainTitle();
                                break;
                            }
                            case "5": {
                                rulerTitleDAO.deleteRulerTitle();
                                break;
                            }
                            default: {
                                tmp = "end";
                                break;
                            }
                        }
                    }
                    // rulerTitleDAO.start();
                    break;
                }
                case "6":{
                    RulerCountryDAO rulerCountryDAO = new RulerCountryDAO();
                    while (tmp != "end"){
                        actionWithTable();
                        tmp = scanner.nextLine();
                        switch (tmp){
                            case "1": {
                                rulerCountryDAO.listRullersCountrys();
                                break;
                            }
                            case "2":{
                                rulerCountryDAO.addRulerCountry();
                                break;
                            }
                            case "3": {
                                System.out.println("Not impl..");
                                /*System.out.println("Введите id искомого элемента");
                                int id = InputUtils.getInt();
                                titleDAO.findTitleByID(id);*/
                                break;
                            }
                            case "4": {
                                rulerCountryDAO.updateRulerCountry();
                                break;
                            }
                            case "5": {
                                rulerCountryDAO.deleteRulerCountry();
                                break;
                            }
                            default: {
                                tmp = "end";
                                break;
                            }
                        }
                    }
                    break;
                }

                case "7": {
                    CountryCapitalTownDAO countryCapitalTownDAO = new CountryCapitalTownDAO();
                    while (tmp != "end"){
                        actionWithTable();
                        tmp = scanner.nextLine();
                        switch (tmp){
                            case "1": {
                                countryCapitalTownDAO.listCountryCapitals();
                                break;
                            }
                            case "2":{
                                countryCapitalTownDAO.addCountryCapital();
                                break;
                            }
                            case "3": {
                                System.out.println("Not impl..");
                                break;
                            }
                            case "4": {
                                countryCapitalTownDAO.updateCountryCapital();
                                break;
                            }
                            case "5": {
                                countryCapitalTownDAO.deleteCountryCapital();
                                break;
                            }
                            default: {
                                tmp = "end";
                                break;
                            }
                        }
                    }
                    break;
                }
                case "12":{
                    System.out.println("Введите id искомого элемента");
                    int id = InputUtils.getInt();
                    Title title = TitleDAO.findTitleByID(id);
                    System.out.println("============================ Множество заисей: ==============================");
                    Set<RullerMainTitleRel> RullerMainTitleRel = title.getRullerMainTitleRels();
                    for (RullerMainTitleRel rullerMainTitleRel : RullerMainTitleRel) {
                        System.out.println(rullerMainTitleRel);
                        System.out.println("\n================\n");
                    }
                    break;
                }
                case "0": {
                    tmp = null;
                }
                default:{
                    break;
                }
            }
        }

    }

    private static void actionWithTable(){
        System.out.println("Выбеиpте действие: " +
                "\n1 смотеть всё" +
                "\n2 добавить " +
                "\n3 find by id " +
                "\n4 обновить " +
                "\n5 yдалить " +
                "\n Любая дpyгая клавиша = возвpат к выбоpy таблиц");

    }
}

