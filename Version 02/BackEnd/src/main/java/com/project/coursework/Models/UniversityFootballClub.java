package com.project.coursework.Models;

public class UniversityFootballClub extends FootballClub {
    private static final long serialVersionUID = 3L;
    private String universityName;

    public UniversityFootballClub(String clubName, String clubLocation, String universityName) { //parameterized constructor
        super(clubName, clubLocation);
        this.universityName = universityName;
    }

    public static long getSerialVersionUID() {  //return serialVersionUID
        return serialVersionUID;
    }

    public String getUniversityName() { //return universityName
        return universityName;
    }

    public void setUniversityName(String universityName) { //set a value for universityName
        this.universityName = universityName;
    }

    @Override
    public String toString() {
        return super.toString() + "\n\tUniversity Name = " + this.universityName;
    }

}
