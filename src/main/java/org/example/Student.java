package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

//this makes the class managed by hibernate
@Entity
class Student {

    @Id//primary key of table
    private int id;
    private String fullName;
    private int age;

    public int getId() {
        return this.id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public int getAge() {
        return this.age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                '}';
    }
}
