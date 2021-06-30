package Assignment;

public class SchoolFootballClub extends FootballClub {   //not a POJO class as the extends keyword is present
    private String schoolName;

    public SchoolFootballClub(String ClubName, String location,String schoolName){
        super(ClubName, location);
        this.schoolName=schoolName;

    }


    public SchoolFootballClub(String ClubName, String location, int wins, int defeats, int draws, int GoalsReceived, int goalsScored, int numMatches,int goalsConceded,String schoolName,int NumPoints) {
        super(ClubName, location,wins, defeats, draws, GoalsReceived, goalsScored, numMatches,goalsConceded,NumPoints);
        this.schoolName=schoolName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

}
