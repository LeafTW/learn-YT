package day27ts;

import java.io.Serializable;

public class Person implements Serializable {
    //序列化類一定要implements Serializable 並 一定要有static final long serialVersionUID = 42L;(這一思是給序列化一個ID避免反序列化時找不到)
    static final long serialVersionUID = 42342432322L;
    private bankId ba;//序列化類一定要implements Serializable
    private String name;
    private int age;
    private char gender;



    public Person(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Person(String name, int age, char gender, bankId ba) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.ba = ba;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", ba=" + ba +
                '}';
    }
}

class bankId implements Serializable{
    static final long serialVersionUID = 42342432322L;
    private int adderss;

    @Override
    public String toString() {
        return "bankId{" +
                "adderss=" + adderss +
                '}';
    }

    public bankId(int adderss) {
        this.adderss = adderss;
    }
}