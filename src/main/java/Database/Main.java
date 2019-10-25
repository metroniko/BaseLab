package Database;

import java.util.Date;
import java.util.Scanner;

/**
 *  starting point of the program
 */

public class Main {

    static String FIO;
    static Integer year;
    static Integer month;
    static Integer day;
    static Integer endWord = 666;

    public static void main(String[] args) {
        Base base = new Base();
        Person person = new Person();
        Scanner in = new Scanner(System.in);
        /**
         * cycle with output condition
         */
        while (endWord != 0) {

            System.out.println("Input FIO: ");
            FIO = in.nextLine();
            System.out.println("name ="+ FIO);

            System.out.println("Input year of birth: ");
            year = (Integer) in.nextInt();
            System.out.println("year ="+ year);

            System.out.println("Input month of birth: ");
            month = (Integer) in.nextInt();
            System.out.println("month ="+ month);

            System.out.println("Input day of birth: ");
            day = (Integer) in.nextInt();
            System.out.println("day ="+ day);

            person.setValues(FIO,day,month,year);
            base.addNewPerson(person);
            System.out.println("if you want to exit type "+"0"+" else any key");
            endWord = in.nextInt();
            System.out.println(in.nextLine());
        }


        for (int i = 0; i < 10; i++) {
            person.setValues(("Kolya"+i), 12,  7,  1999);
            base.addNewPerson(person);
        }

        person.setValues("Nikolay", 12,  7,  1999);
        base.addNewPerson(person);
        base.deletePersonbyFIO("Kolya2");

        base.showAllDatabase();
        base.getPersonbyFIO("Nikolay").getLifetime();

    }
}
