package com.project.coursework.Models;

public class SchoolFootballClub extends FootballClub {
    private static final long serialVersionUID = 4L;
    private String schoolName;

    public SchoolFootballClub(String clubName, String clubLocation, String schoolName) { //parameterized constructor
        super(clubName, clubLocation);
        this.schoolName = schoolName;
    }

    public static long getSerialVersionUID() {  //return serialVersionUID
        return serialVersionUID;
    }

    public String getSchoolName() { //return schoolName
        return schoolName;
    }

    public void setSchoolName(String schoolName) { //set a value for schoolName
        this.schoolName = schoolName;
    }

    @Override
    public String toString() {
        return super.toString() + "\n\tSchool Name = " + this.schoolName;
    }

}
