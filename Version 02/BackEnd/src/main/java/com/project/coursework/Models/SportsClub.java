package com.project.coursework.Models;

import java.io.Serializable;
import java.util.Objects;

public abstract class SportsClub implements Serializable {
    private static final long serialVersionUID = 1L;
    private String clubName;
    private String clubLocation;

    public SportsClub(String clubName, String clubLocation) { //parameterized constructor
        this.clubName = clubName;
        this.clubLocation = clubLocation;
    }

    public static long getSerialVersionUID() { //return serialVersionUID
        return serialVersionUID;
    }

    public String getClubName() { //return clubName
        return clubName;
    }

    public void setClubName(String clubName) { //set a value for clubName
        this.clubName = clubName;
    }

    public String getClubLocation() { //return clubLocation
        return clubLocation;
    }

    public void setClubLocation(String clubLocation) { //set a value for clubLocation
        this.clubLocation = clubLocation;
    }

    @Override
    public String toString() {
        return "\tClub Name = " + this.clubName + "\n\tClub Location = " + this.clubLocation;
    }

    @Override
    public boolean equals(Object object) { //check equality between two objects
        if (this == object) {
            return true;
        }
        if (!(object instanceof SportsClub)) {
            return false;
        }
        SportsClub sportsClub = (SportsClub) object;
        return Objects.equals(getClubName(), sportsClub.getClubName()) &&
                Objects.equals(getClubLocation(), sportsClub.getClubLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClubName(), getClubLocation());
    }

}
