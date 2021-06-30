package Assignment;

public class UniversityFootballClub extends FootballClub {   //not a POJO class as the extends keyword is present
    private String UniName;

    public UniversityFootballClub(String ClubName, String location, String UniName) {
        super(ClubName, location);
        this.UniName = UniName;

    }

    public UniversityFootballClub(String ClubName, String location, int wins, int defeats, int draws, int GoalsReceived, int goalsScored, int numMatches, int goalsConceded, String UniName, int NumPoints) {
        super(ClubName, location, wins, defeats, draws, GoalsReceived, goalsScored, numMatches, goalsConceded,NumPoints);
        this.UniName = UniName;
    }

    public String getUniName() {
        return UniName;
    }


    public void setUniName(String uniName) {
        UniName = uniName;
    }

}
