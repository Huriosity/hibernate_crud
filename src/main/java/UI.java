import com.github.Huriosity.InputUtils;
import dao.RulerDAO;
import dao.RulerTitleDAO;
import dao.TitleDAO;
import model.Ruler;
import model.RullerMainTitleRel;
import model.Title;

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
                    "\n3 Ruler Title" +
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
                            default: {
                                tmp = "end";
                                break;
                            }
                        }
                    }



                    // titleDAO.start();
                    break;
                }
                case "3": {
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
                            default: {
                                tmp = "end";
                                break;
                            }
                        }
                    }
                    // rulerTitleDAO.start();
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
                "\n Любая дpyгая клавиша = возвpат к выбоpy таблиц");

    }
}

