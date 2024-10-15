/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementsystem;


import java.sql.Date;


/**
 *
 * @author TUF GAMING
 */
public class studentData {
    private Integer studentNum;
    private String year;
    private String course;
    private String firstName;
    private String lastName;
    private String gender;
     private Date birth;
     private String status ;
     private String image ;
     private double firstsem;
     private double secondsem;
     private double finals;

    public studentData(Integer studentNum, String year, String course, String firstName, String lastName, String gender, Date birth, String status, String image) {
        this.studentNum = studentNum;
        this.year = year;
        this.course = course;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birth = birth;
        this.status = status;
        this.image = image;
    }

    public studentData(Integer studentNum, String year, String course, double firstsem, double secondsem, double finals) {
        this.studentNum = studentNum;
        this.year = year;
        this.course = course;
        this.firstsem = firstsem;
        this.secondsem = secondsem;
        this.finals = finals;
    }
    
    

    public Integer getStudentNum() {
        return studentNum;
    }

    public String getYear() {
        return year;
    }

    public String getCourse() {
        return course;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public Date getBirth() {
        return birth;
    }

    public String getStatus() {
        return status;
    }

    public String getImage() {
        return image;
    }

    public double getFirstsem() {
        return firstsem;
    }

    public double getSecondsem() {
        return secondsem;
    }

    public double getFinals() {
        return finals;
    }
    
     
     
    
    
}
