package Assignment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FootballGame {  //POJO class

    private FootballClub teamOne;
    private FootballClub teamTwo;
    private int teamOneScore;
    private int teamTwoScore;
    private LocalDate matchDate;
    private String team1;
    private String team2;
    private String date;

    //constructor to take string values
    public FootballGame(String team1,String team2,LocalDate matchDate,int teamOneScore,int teamTwoScore){
        this.team1=team1;
        this.team2=team2;
        this.matchDate=matchDate;
        this.teamOneScore=teamOneScore;
        this.teamTwoScore=teamTwoScore;

    }

    public FootballGame() {

    }


    public void setDate(String date) {
        this.date = date;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getDate() {
        return date;
    }

    public String getTeam2() {
        return team2;
    }

    public String getTeam1() {
        return team1;
    }

    public FootballGame(FootballClub teamOne, FootballClub teamTwo, int teamOneScore, int teamTwoScore, LocalDate matchDate) {
        this.teamOne = teamOne;
        this.teamTwo=teamTwo;
        this.teamOneScore=teamOneScore;
        this.teamTwoScore=teamTwoScore;
        this.matchDate=matchDate;
    }
    public FootballGame(Object o) {

    }

    public FootballClub getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(FootballClub teamOne) {
        this.teamOne = teamOne;
    }

    public FootballClub getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(FootballClub teamTwo) {
        this.teamTwo = teamTwo;
    }

    public int getTeamOneScore() {
        return teamOneScore;
    }

    public void setTeamOneScore(int teamOneScore) {
        this.teamOneScore = teamOneScore;
    }

    public int getTeamTwoScore() {
        return teamTwoScore;
    }

    public void setTeamTwoScore(int teamTwoScore) {
        this.teamTwoScore = teamTwoScore;
    }

    public LocalDate getMatchDate() {

        return matchDate;

    }

    public void setMatchDate(LocalDate dt) {

        this.matchDate = dt;
    }

    @Override
    public String toString() {
        return "match Date=" + matchDate;
    }
}
