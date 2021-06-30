package com.project.coursework.Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class FootballMatch implements Serializable {
    private static final long serialVersionUID = 5L;
    private FootballClub homeTeam;
    private FootballClub awayTeam;
    private LocalDate matchDate;
    private int homeTeamGoalsScored;
    private int awayTeamGoalsScored;

    public FootballMatch(FootballClub homeTeam, FootballClub awayTeam, LocalDate matchDate, int homeTeamGoalsScored, int awayTeamGoalsScored) { //parameterized constructor
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchDate = matchDate;
        this.homeTeamGoalsScored = homeTeamGoalsScored;
        this.awayTeamGoalsScored = awayTeamGoalsScored;
    }

    public static long getSerialVersionUID() {  //return serialVersionUID
        return serialVersionUID;
    }

    public FootballClub getHomeTeam() { //return homeTeam
        return homeTeam;
    }

    public void setHomeTeam(FootballClub homeTeam) { //set a value for homeTeam
        this.homeTeam = homeTeam;
    }

    public FootballClub getAwayTeam() { //return awayTeam
        return awayTeam;
    }

    public void setAwayTeam(FootballClub awayTeam) { //set a value for awayTeam
        this.awayTeam = awayTeam;
    }

    public LocalDate getMatchDate() { //return matchDate
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) { //set a value for matchDate
        this.matchDate = matchDate;
    }

    public int getHomeTeamGoalsScored() { //return homeTeamGoalsScored
        return homeTeamGoalsScored;
    }

    public void setHomeTeamGoalsScored(int homeTeamGoalsScored) { //set a value for homeTeamGoalsScored
        this.homeTeamGoalsScored = homeTeamGoalsScored;
    }

    public int getAwayTeamGoalsScored() { //return awayTeamGoalsScored
        return awayTeamGoalsScored;
    }

    public void setAwayTeamGoalsScored(int awayTeamGoalsScored) { //set a value for awayTeamGoalsScored
        this.awayTeamGoalsScored = awayTeamGoalsScored;
    }

    public void updateStatistics(FootballClub team1, FootballClub team2) { //update corresponding statistics of football teams according to match result
        if (team1 != null && team2 != null) {
            team1.setNoOfPlayedMatches(team1.getNoOfPlayedMatches() + 1);
            team1.setGoalsScored(team1.getGoalsScored() + homeTeamGoalsScored);
            team1.setGoalsReceived(team1.getGoalsReceived() + awayTeamGoalsScored);

            team2.setNoOfPlayedMatches(team2.getNoOfPlayedMatches() + 1);
            team2.setGoalsScored(team2.getGoalsScored() + awayTeamGoalsScored);
            team2.setGoalsReceived(team2.getGoalsReceived() + homeTeamGoalsScored);

            if (homeTeamGoalsScored > awayTeamGoalsScored) {
                team1.setNoOfWins(team1.getNoOfWins() + 1);
                team2.setNoOfDefeats(team2.getNoOfDefeats() + 1);
                team1.setNoOfPoints(team1.getNoOfPoints() + 3);
            } else if (homeTeamGoalsScored < awayTeamGoalsScored) {
                team2.setNoOfWins(team2.getNoOfWins() + 1);
                team1.setNoOfDefeats(team1.getNoOfDefeats() + 1);
                team2.setNoOfPoints(team2.getNoOfPoints() + 3);
            } else {
                team1.setNoOfDraws(team1.getNoOfDraws() + 1);
                team2.setNoOfDraws(team2.getNoOfDraws() + 1);
                team1.setNoOfPoints(team1.getNoOfPoints() + 1);
                team2.setNoOfPoints(team2.getNoOfPoints() + 1);
            }
        }
    }

    @Override
    public String toString() {
        return "\tHome Team = " + this.homeTeam.getClubName() + ", " + this.homeTeam.getClubLocation() + "\n\tAway Team = " + this.awayTeam.getClubName() + ", " +
                this.awayTeam.getClubLocation() + "\n\tMatch Date = " + this.matchDate + "\n\tHome Team Goals Scored in match = " +
                this.homeTeamGoalsScored + "\n\tAway Team Goals Scored in match = " + this.awayTeamGoalsScored;
    }

    @Override
    public boolean equals(Object object) { //check equality between two objects
        if (this == object) {
            return true;
        }
        if (!(object instanceof FootballMatch)) {
            return false;
        }
        FootballMatch footballMatch = (FootballMatch) object;
        return getHomeTeamGoalsScored() == footballMatch.getHomeTeamGoalsScored() &&
                getAwayTeamGoalsScored() == footballMatch.getAwayTeamGoalsScored() &&
                Objects.equals(getHomeTeam(), footballMatch.getHomeTeam()) &&
                Objects.equals(getAwayTeam(), footballMatch.getAwayTeam()) &&
                Objects.equals(getMatchDate(), footballMatch.getMatchDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHomeTeam(), getAwayTeam(), getMatchDate(), getHomeTeamGoalsScored(), getAwayTeamGoalsScored());
    }

}
