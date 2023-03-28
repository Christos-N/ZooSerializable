package com.unipi.chris;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
/*        Animal RhesusMacaque = new Animal("m1","Rhesus macaque","Mammalia",4.9,29,"Male");                        //Αρχικοποίηση
        Animal GiantPanda = new Animal("m2","Giant Panda","Mammalia",105,38,"Female");
        Animal NileCrocodile = new Animal("r3","Nile Crocodile","Reptilia",137.5,117,"Female");
        Animal Lion = new Animal("m4","Lion","Mammalia",174.9,29,"Male");
        Animal Ostrich = new Animal("a5","Ostrich","Aves",120,45,"Male");
        Animal AldabraTortoise = new Animal("r6","Aldabra Tortoise","Reptilia",250,250,"Male");
        Animal GreenlandShark = new Animal("c7","Greenland Shark","Chondrichthyes",400,500,"Male");
        Animal Peafowl = new Animal("a8","Peafowl","Aves",8,50,"Female");
        Animal Salamander = new Animal("a9","Salamander","Amphibia",28,200,"Male");
        Animal GoldenEagle = new Animal("a10","Golden Eagle","Aves",6.7,46,"Female");
        List<Animal> animalList = new ArrayList<>();
        animalList.add(RhesusMacaque);
        animalList.add(GiantPanda);
        animalList.add(NileCrocodile);
        animalList.add(Lion);
        animalList.add(Ostrich);
        animalList.add(AldabraTortoise);
        animalList.add(GreenlandShark);
        animalList.add(Peafowl);
        animalList.add(Salamander);
        animalList.add(GoldenEagle);*/
        ArrayList<Animal> animalList = new ArrayList<>();

        try {                   //deserialization
            FileInputStream fis = new FileInputStream("Animals.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            animalList = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            return;
        }
        catch (ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        showMenu();
        int userInput;
        do {
            try{
                userInput = scanner.nextInt();
                if (userInput == 0) {           //Ελέγχω από την αρχή το 0, διότι σε 10 γραμμές εκχωρώ στο userInput το 0(περίπτωση που ο χρήστης δεν δώσει integer)
                    System.out.println("Δώσατε εσφαλμένα στοιχεία.\n" + "Παρακαλώ προσπαθήστε ξανά.");
                    sleep2show();
                }
            } catch (InputMismatchException e) {
                System.out.println("Δώσατε εσφαλμένα στοιχεία.\n" + "Παρακαλώ προσπαθήστε ξανά.");
                sleep2show();
                scanner.next();
                userInput = 0; //Το κάνω 0 ώστε να μην μπει στο switch
            }
            switch (userInput) {
                case 1:
                    for (Animal a : animalList) System.out.println("Κωδικός:"+a.getPassword()+" Όνομα:"+a.getName()+" Ομοταξία:"+a.getBioclass()+" Βάρος:"+a.getWeight()+" Ηλικία:"+a.getAge()+" Φύλο:"+ a.getGender());
                    TimeUnit.SECONDS.sleep(5);
                    showMenu();
                    break;
                case 2:
                   outer:while (true) {         //Έβαλα το while ώστε να μπορώ να βγω(στην περίπτωση που ο χρήστης δώσει εσφαλμένα στοιχεία ή ήδη υπάρχοντα)
                       System.out.println("Κωδικός:");
                       scanner.nextLine();
                       String pass2 = scanner.nextLine();
                       for (Animal c : animalList) {
                           if (pass2.equals(c.getPassword())) {
                               System.out.println("Υπάρχει ήδη ζώο με τον κωδικό που δώσατε.");
                               sleep2show();
                               break outer;
                           }
                       }
                       System.out.println("Όνομα:");
                       String name2 = scanner.nextLine();
                       for (Animal c : animalList) {
                           if (name2.equals(c.getName())) {
                               System.out.println("Υπάρχει ήδη ζώο με το όνομα που δώσατε.");
                               sleep2show();
                               break outer;
                           }
                       }
                       System.out.println("Ομοταξία:");
                       String bio = scanner.nextLine();
                       System.out.println("Φύλο(Male ή Female):");
                       String gender = scanner.nextLine();
                       if (!(gender.equals("Male") || gender.equals("Female"))) {
                           System.out.println("Δώσατε εσφαλμένα στοιχεία.\nΠαρακαλώ προσπαθήστε ξανά.");
                           sleep2show();
                           break;
                       }
                       try {
                           System.out.println("Ηλικία:");
                           int age = scanner.nextInt();
                           System.out.println("Βάρος(kg):");
                           double weight = scanner.nextDouble();
                           if ((age <= 0 || age > 500) && (weight > 172365 || weight <= 0)) {
                               System.out.println("Δώσατε εσφαλμένη ηλικία και εσφαλμένο βάρος.\nΠαρακαλώ προσπαθήστε ξανά.");
                               sleep2show();
                               break;
                           }
                           else if((age <= 0) || (age > 500)){
                               System.out.println("Δώσατε εσφαλμένη ηλικία.\nΠαρακαλώ προσπαθήστε ξανά.");
                               sleep2show();
                               break;
                           }
                           else if (weight > 172365 || weight <= 0){
                               System.out.println("Δώσατε εσφαλμένο βάρος.\nΠαρακαλώ προσπαθήστε ξανά.");
                               sleep2show();
                               break;
                           }
                           Animal a1 = new Animal(pass2,name2,bio,weight,age,gender);
                           animalList.add(a1);
                           System.out.println("Το ζώο προστέθηκε με επιτυχία.");
                           sleep2show();
                           break;
                       } catch (Exception e) {
                           TimeUnit.SECONDS.sleep((long) 1.7);
                           break;
                       }
                   }
                       break;
                case 3:
                    System.out.println("Πληκτρολογήστε το όνομα του ζώου:");
                    scanner.nextLine();
                    String name3 = scanner.nextLine();
                    System.out.println(name3);
                    boolean flag1 = false;          //Ελέγχω αν βρέθηκε ζώο με το δοσμένο όνομα
                    for (Animal a: animalList) {
                        if (name3.equals(a.getName())) {
                            a.showAll();
                            flag1 = true;
                            TimeUnit.SECONDS.sleep(5);
                            showMenu();
                        }
                    }
                    if (!flag1) {
                        System.out.println("Δεν βρέθηκε ζώο με το όνομα που δώσατε.");
                        sleep2show();
                    }
                    break;
                case 4:
                    System.out.println("Πληκτρολογήστε το κωδικό του ζώου.");
                    scanner.nextLine();
                    String pass3 = scanner.nextLine();
                    boolean flag2 = false;          //Ελέγχω αν βρέθηκε ζώο με το δοσμένο κωδικό
                    for (Animal a: animalList){
                        if (pass3.equals(a.getPassword())) {
                            a.showAll();
                            flag2 = true;
                            TimeUnit.SECONDS.sleep(5);
                            showMenu();
                            break;
                        }
                    }
                    if(!flag2){
                        System.out.println("Δεν βρέθηκε ζώο με τον κωδικό που δώσατε.");
                        sleep2show();
                    }
                    break;
                case 5:
                    System.out.println("Πληκτρολογήστε το κωδικό του ζώου:");
                    scanner.nextLine();
                    String pass5 = scanner.nextLine();
                    boolean flag5 = false;          //Ελέγχω αν βρέθηκε ζώο με το δοσμένο κωδικό
                    label:
                    for (Animal a: animalList){
                        if (pass5.equals(a.getPassword())) {
                            flag5 = true;
                            System.out.println("Τι θέλετε να τροποποιήσετε;\nΟι επιλογές είναι οι εξής: pass, name, bioclass, gender, age, weight.");
                            String input = scanner.nextLine();
                            switch (input) {
                                case "pass":
                                    System.out.println("Πληκτρολογήστε το νέο κωδικό:");
                                    String newpass = scanner.nextLine();
                                    if (!newpass.equals(a.getPassword())) {             //Ελέγχω αν ο χρήστης έδωσε τον ίδιο κωδικό
                                        a.setPassword(newpass);
                                        System.out.println("Ο κωδικός άλλαξε με επιτυχία.");
                                    } else
                                        System.out.println("Ο κωδικός που πληκτρολογήσατε είναι ίδιος με τον υπάρχων.");
                                    sleep2show();
                                    break label;
                                case "name":
                                    System.out.println("Πληκτρολογήστε το νέο όνομα:");
                                    String newname = scanner.nextLine();
                                    if (!newname.equals(a.getName())) {             //Ελέγχω αν ο χρήστης έδωσε το ίδιο όνομα
                                        a.setName(newname);
                                        System.out.println("Το όνομα άλλαξε με επιτυχία.");
                                    } else System.out.println("Το όνομα που πληκτρολογήσατε είναι ίδιο με το υπάρχον.");
                                    sleep2show();
                                    break label;
                                case "bioclass":
                                    System.out.println("Πληκτρολογήστε τη νέα ομοταξία:");
                                    String newbio = scanner.nextLine();
                                    if (!newbio.equals(a.getBioclass())) {       //Ελέγχω αν ο χρήστης έδωσε την ίδια ομοταξία
                                        a.setBioclass(newbio);
                                        System.out.println("Η ομοταξία άλλαξε με επιτυχία.");
                                    } else
                                        System.out.println("Η ομοταξία που πληκτρολογήσατε είναι ίδια με την υπάρχουσα.");
                                    sleep2show();
                                    break label;
                                case "gender":
                                    System.out.println("Το φύλο του ζώου είναι " + a.getGender() + ". Θέλετε να το αλλάξετε στο αντίθετό του; Πληκτρολογήστε ΝΑΙ ή ΟΧΙ.");
                                    String choice = scanner.nextLine();
                                    if (choice.equals("ΝΑΙ") && a.getGender().equals("Male")) {
                                        a.setGender("Female");
                                        System.out.println("Το φύλο άλλαξε σε Female με επιτυχία.");
                                    } else if (choice.equals("ΝΑΙ") && a.getGender().equals("Female")) {
                                        a.setGender("Male");
                                        System.out.println("Το φύλο άλλαξε σε Male με επιτυχία.");
                                    } else if (choice.equals("ΟΧΙ"))
                                        System.out.println("Η αλλαγή φύλου ματαιώθηκε με επιτυχία.");
                                    else System.out.println("Δώσατε εσφαλμένα δεδομένα.\nΠαρακαλώ προσπαθήστε ξανά.");
                                    sleep2show();
                                    break label;
                                case "age":
                                    System.out.println("Πληκτρολογήστε τη νέα ηλικία:");
                                    try {
                                        int newage = scanner.nextInt();
                                        if (!(newage == a.getAge())) a.setAge(newage);              //Ελέγχω αν ο χρήστης έδωσε την ίδια ηλικία
                                        else System.out.println("Η ηλικία που πληκτρολογήσατε είναι ίδια με την υπάρχουσα.");
                                        sleep2show();
                                    } catch (InputMismatchException e) {
                                        //
                                    }
                                    break label;
                                case "weight":
                                    System.out.println("Πληκτρολογήστε το νέο βάρος(kg):");
                                    try {
                                        double newweight = scanner.nextDouble();
                                        if (!(newweight == a.getWeight())) a.setWeight(newweight);      //Ελέγχω αν ο χρήστης έδωσε το ίδιο βάρος
                                        else System.out.println("Το βάρος που πληκτρολογήσατε είναι ίδιο με το υπάρχον.");
                                        sleep2show();
                                    } catch (InputMismatchException e) {
                                        //
                                    }
                                    break label;
                                default:
                                    System.out.println("Δώσατε εσφαλμένα στοιχεία.\n" + "Παρακαλώ προσπαθήστε ξανά.");
                                    sleep2show();
                            }
                        }
                    }
                    if(!flag5){
                        System.out.println("Δεν βρέθηκε ζώο με τον κωδικό που δώσατε.");
                        sleep2show();
                    }

                    break;
                case 6:
                        System.out.println("Κωδικός:");
                        scanner.nextLine();
                        String pass6 = scanner.nextLine();
                        if(animalList.removeIf(b -> pass6.equals(b.getPassword()))) System.out.println("Το ζώο με κωδικό " + pass6 + " διαγράφτηκε με επιτυχία.");//Ελέγχω αν υπάρχει ζώο με το κωδικό που έδωσε ο χρήστης, αν υπάρχει διαγράφω το ζώο
                        else System.out.println("Δεν βρέθηκε ζώο με τον κωδικό που δώσατε\nΠαρακαλώ προσπαθήστε ξανά.");                                           //και εμφανίζω το καταλλήλο μήνυμα
                        sleep2show();
                    break;
                case 7: break;      // Για να πάει στον τερματισμό του do-while
                case 0: break;     // Περίπτωση που ο χρήστης δεν δώσει integer ή δώσει 0
                default:            //Περίπτωση που ο χρήστης δώσει κάτι διαφορετικό από 1,2,3,4,5,6 ή 7
                    System.out.println("Δώσατε εσφαλμένα στοιχεία.\n" + "Παρακαλώ προσπαθήστε ξανά.");
                    sleep2show();
            }
        }while(userInput!=7);{
            System.out.println("Ευχαριστούμε!");
            try {
                FileOutputStream fileOutputStream = new FileOutputStream("Animals.ser");        //Αποθηκεύω στο αρχείο τις όποιες αλλαγές κάνει ο χρήστης
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(animalList);
                objectOutputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    static void sleep2show() throws InterruptedException {      //Αυτές οι 2 γραμμές εμφανίζοταν συχνά στον κώδικα οπότε τις έβαλα σε συνάρτηση
        TimeUnit.SECONDS.sleep(2);
        showMenu();
    }
    static void showMenu(){
        System.out.println("Εφαρμογή Zoo\n" +
                "Παρακαλώ επιλέξτε από το παρακάτω menu επιλογών\n" +
                "--------------------------------------------\n" +
                "1. Προβολή όλων των διαθέσιμων ζώων του ζωολογικού κήπου\n" +
                "2. Προσθήκη νέου ζώου\n" +
                "3. Αναζήτηση ζώου βάσει ονόματος\n" +
                "4. Αναζήτηση ζώου βάσει κωδικού\n" +
                "5. Επεξεργασία ζώου βάσει κωδικού\n" +
                "6. Διαγραφή ζώου βάσει κωδικού\n" +
                "7. Έξοδος από την εφαρμογή");
    }
}
