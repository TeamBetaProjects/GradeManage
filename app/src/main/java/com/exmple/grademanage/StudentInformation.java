package com.exmple.grademanage;

public class StudentInformation {
    private int id;
    private String FirstName;
    private String LastName;
    private String Course;
    private int Credits;
    private int marks;


    public StudentInformation(int id, String firstName, String lastName, String course, int credits, int marks) {
        this.id = id;
        FirstName = firstName;
        LastName = lastName;
        Course = course;
        Credits = credits;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String course) {
        Course = course;
    }

    public int getCredits() {
        return Credits;
    }

    public void setCredits(int credits) {
        Credits = credits;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}
