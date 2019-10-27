package com.database;
import java.util.Scanner;

/**
 *  starting point of the program.
 */
public class Main {

    /**
     *
     */
    private static int endWord;

    /**
     * starting point of the program.
     * @param args arguments
     */
    public static void main(String[] args) {
        Base base = new Base();
        Person person = new Person();
        Scanner in = new Scanner(System.in);
        while (endWord != 0) {

            System.out.println("Input FIO: ");
            //привет
            String fio = in.nextLine();
            System.out.println("FIO = " + fio);

            System.out.println("Input gender: ");
            String gender = in.nextLine();
            System.out.println("gender = " + gender);

            System.out.println("Input year of birth: ");
            int year = in.nextInt();
            System.out.println("year = " + year);



            System.out.println("Input month of birth: ");
            int month = in.nextInt();
            System.out.println("month = " + month);

            System.out.println("Input day of birth: ");
            int day = in.nextInt();
            System.out.println("day = " + day);


            person.setValues(fio, gender, day, month, year);
            base.addNewPerson(person);
            System.out.println("if you want to exit type 0 else any key");
            endWord = in.nextInt();
            System.out.println(in.nextLine());
        }

//        for (int i = 0; i < 16; i++) {
//            person.setValues(("Kolya" + i), "male", 12, 7, 1999);
//            base.addNewPerson(person);
//        }

//        person.setValues("Nikolay", "male", 6, 6, 1999);

        base.addNewPerson(person);
        base.deletePersonbyFIO("Kolya2");

        base.showAllDatabase();
        try {
            base.getPersonbyFIO("Nikolay").getLifetime();
        } catch (NullPointerException e) {
            System.out.println("нет такого пользователя " + e);
        }

    }
}
