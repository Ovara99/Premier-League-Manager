package com.project.coursework.Models;

import java.util.Objects;

public class FootballClub extends SportsClub implements Comparable<FootballClub> {
    private static final long serialVersionUID = 2L;
    private int clubPosition;
    private int noOfPlayedMatches;
    private int noOfWins;
    private int noOfDraws;
    private int noOfDefeats;
    private int goalsScored;
    private int goalsReceived;
    private int noOfPoints;

    public FootballClub(String clubName, String clubLocation) { //parameterized constructor
        super(clubName, clubLocation);
        this.clubPosition = 0;
        this.noOfPlayedMatches = 0;
        this.noOfWins = 0;
        this.noOfDraws = 0;
        this.noOfDefeats = 0;
        this.goalsScored = 0;
        this.goalsReceived = 0;
        this.noOfPoints = 0;
    }

    public static long getSerialVersionUID() { //return serialVersionUID
        return serialVersionUID;
    }

    public int getClubPosition() { //return clubPosition
        return clubPosition;
    }

    public void setClubPosition(int clubPosition) { //set a value for clubPosition
        this.clubPosition = clubPosition;
    }

    public int getNoOfPlayedMatches() { //return noOfPlayedMatches
        return noOfPlayedMatches;
    }

    public void setNoOfPlayedMatches(int noOfPlayedMatches) { //set a value for noOfPlayedMatches
        this.noOfPlayedMatches = noOfPlayedMatches;
    }

    public int getNoOfWins() { //return noOfWins
        return noOfWins;
    }

    public void setNoOfWins(int noOfWins) { //set a value for noOfWins
        this.noOfWins = noOfWins;
    }

    public int getNoOfDraws() { //return noOfDraws
        return noOfDraws;
    }

    public void setNoOfDraws(int noOfDraws) { //set a value for noOfDraws
        this.noOfDraws = noOfDraws;
    }

    public int getNoOfDefeats() { //return noOfDefeats
        return noOfDefeats;
    }

    public void setNoOfDefeats(int noOfDefeats) { //set a value for noOfDefeats
        this.noOfDefeats = noOfDefeats;
    }

    public int getGoalsScored() { //return goalsScored
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) { //set a value for goalsScored
        this.goalsScored = goalsScored;
    }

    public int getGoalsReceived() { //return goalsReceived
        return goalsReceived;
    }

    public void setGoalsReceived(int goalsReceived) { //set a value for goalsReceived
        this.goalsReceived = goalsReceived;
    }

    public int getNoOfPoints() { //return noOfPoints
        return noOfPoints;
    }

    public void setNoOfPoints(int noOfPoints) { //set a value for noOfPoints
        this.noOfPoints = noOfPoints;
    }

    public int getGoalDifference() { //return goalDifference
        return (this.goalsScored - this.goalsReceived);
    }

    @Override
    public String toString() {
        return super.toString() + "\n\tClub Position = " + this.clubPosition + "\n\tNo of Played Matches = " + this.noOfPlayedMatches + "\n\tNo of Wins = "
                + this.noOfWins + "\n\tNo of Draws = " + this.noOfDraws + "\n\tNo of Defeats = " + this.noOfDefeats + "\n\tGoals Scored = " + this.goalsScored
                + "\n\tGoals Received = " + this.goalsReceived + String.format("\n\tGoal Difference = %+d",this.getGoalDifference()) + "\n\tNo of Points = " + this.noOfPoints;
    }

    @Override
    public int compareTo(FootballClub footballClub) { //compare current object with the specified object for order
        if (footballClub != null) {
            if (footballClub.getNoOfPoints() != this.getNoOfPoints()) {
                return footballClub.getNoOfPoints() - this.getNoOfPoints();
            } else if (footballClub.getGoalDifference() != this.getGoalDifference()) {
                return footballClub.getGoalDifference() - this.getGoalDifference();
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object object) { //check equality between two objects
        if (this == object) {
            return true;
        }
        if (!(object instanceof FootballClub)) {
            return false;
        }
        if (!super.equals(object)) {
            return false;
        }
        FootballClub footballClub = (FootballClub) object;
        return getClubPosition() == footballClub.getClubPosition() &&
                getNoOfPlayedMatches() == footballClub.getNoOfPlayedMatches() &&
                getNoOfWins() == footballClub.getNoOfWins() &&
                getNoOfDraws() == footballClub.getNoOfDraws() &&
                getNoOfDefeats() == footballClub.getNoOfDefeats() &&
                getGoalsScored() == footballClub.getGoalsScored() &&
                getGoalsReceived() == footballClub.getGoalsReceived() &&
                getNoOfPoints() == footballClub.getNoOfPoints() &&
                getGoalDifference() == footballClub.getGoalDifference();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getClubPosition(), getNoOfPlayedMatches(), getNoOfWins(), getNoOfDraws(), getNoOfDefeats(), getGoalsScored(),
                getGoalsReceived(), getNoOfPoints(), getGoalDifference());
    }

}
