package com.unipi.chris;

import java.io.Serializable;

public class Animal implements Serializable {
    private String password;
    private String name;
    private String bioclass;
    private double weight;
    private int age;
    private String gender;

    public Animal() {
    }
    public void showAll(){
        System.out.println("Κωδικός: " + password + "\nΌνομα: " + name + "\nΟμοταξία: " + bioclass+"\nΒάρος: " + weight + "\nΗλικία: " + age + "\nΦύλο: " + gender);
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBioclass() {
        return bioclass;
    }

    public void setBioclass(String bioclass) {
        this.bioclass = bioclass;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if(weight>0 && weight<=172365) {
            this.weight = weight;
        }
        else System.out.println("Δώσατε εσφαλμένο βάρος.\nΠαρακαλώ προσπαθήστε ξανά.");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age>0 && age<=500) {
            this.age = age;
        }
        else System.out.println("Δώσατε εσφαλμένη ηλικία.\nΠαρακαλώ προσπαθήστε ξανά.");
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Animal(String password, String name, String bioclass, double weight, int age, String gender) {
        this.password = password;
        this.name = name;
        this.bioclass = bioclass;
        this.weight = weight;
        this.age = age;
        this.gender = gender;
    }
}
