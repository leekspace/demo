package com.leekli.javase.java8.lambda;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String givenName;
    private String surName;
    private int age;
    private String eMail;
    private String phone;
    private String address;

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static List<Person> createShortList() {
        List<Person> l = new ArrayList<>();
        l.add(new Person.Builder()
                .givenName("a")
                .surName("b")
                .build());
        l.add(new Person.Builder()
                .givenName("aa")
                .surName("bbb")
                .build());
        return l;
    }

    public void printName() {
        System.out.println(givenName);

    }



    public static class Builder {
        private String givenName;
        private String surName;
        private int age;
        private String eMail;
        private String phone;
        private String address;

        public Builder givenName(String givenName) {
            this.givenName = givenName;
            return this;
        }

        public Builder surName(String surName) {
            this.surName = surName;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder eMail(String eMail) {
            this.eMail = eMail;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Person build() {
            Person person = new Person();
            person.givenName = givenName;
            person.surName = surName;
            person.age = age;
            person.eMail = eMail;
            person.phone = phone;
            person.address = address;
            return person;
        }
    }
}
