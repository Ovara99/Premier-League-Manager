package Assignment;

public class FootballClub extends SportsClub implements Comparable<FootballClub> {   //not a POJO class as the extends keyword is present
    //s how many wins,
    //draws and defeats an instance of it has achieved in the season, and the number of
    //goals received and scored. The number of points that a club currently has, and the
    //number of matches played should also be included.

    private int wins;
    private int defeats;
    private int draws;
    private int GoalsReceived;
    private int goalsScored;
    private int NumPoints;
    private int numMatches;
    private int goalsConceded;


    public FootballClub(String ClubName){
        super(ClubName);
    }

    //constructor with default values
    public FootballClub(String ClubName, String location){
        super(ClubName,location);
        goalsScored=0;
        GoalsReceived=0;
        numMatches=0;
        wins=0;
        defeats=0;
        draws=0;
        NumPoints=0;
        goalsConceded= 0;
    }

    public FootballClub(String ClubName, String location,int goalsScored, int GoalsReceived, int numMatches,int wins,int defeats,int draws,int NumPoints, int goalsConceded) {
        super(ClubName, location);
        this.goalsScored=goalsScored;
        this.GoalsReceived=GoalsReceived;
        this.numMatches=numMatches;
        this.wins=wins;
        this.defeats=defeats;
        this.draws=draws;
        this.NumPoints=NumPoints;
        this.goalsConceded= goalsConceded;

    }



    //getters and setters
    public int getWins(){ return wins; }
    public void setWins(int wins){
        this.wins=wins;
    }

    public int getDefeats(){ return defeats; }
    public void setDefeats(int defeats){
        this.defeats=defeats;
    }

    public int getDraws(){ return draws; }
    public void setDraws(int draws){
        this.draws=draws;
    }

    public int getGoalsReceived() {
        return GoalsReceived;
    }

    public void setGoalsReceived(int goalsReceived) {
        GoalsReceived = goalsReceived;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getNumMatches() {
        return numMatches;
    }

    public void setNumMatches(int numMatches) {
        this.numMatches = numMatches;
    }

    public int getGoalsConceded() {
        return goalsConceded;
    }

    public void setGoalsConceded(int goalsConceded) {
        this.goalsConceded = goalsConceded;
    }

    //public method to get the total num of points
    public int getNumPoints() {
        return NumPoints= ((wins*3)+1*draws);
    }

    //public method to get the goal difference
    public int getGoalDifference() {
        return (goalsScored-goalsConceded);
    }


    public void setNumPoints(int totalPoints) {
        NumPoints = totalPoints;
    }



    @Override
    public String toString() {
        return "FootballClub{" +
                "wins=" + wins +
                ", defeats=" + defeats +
                ", draws=" + draws +
                ", GoalsReceived=" + GoalsReceived +
                ", goalsScored=" + goalsScored +
                ", numMatches=" + numMatches +
                ", goalsConceded=" + goalsConceded +
                '}';
    }
    public int compareTo(FootballClub club2) {
        if(this.getNumPoints()>club2.getNumPoints()) {
            return -1;
        }
        else if(this.getNumPoints()==club2.getNumPoints()){
            if (this.getGoalDifference()>club2.getGoalDifference()){
                return -1;
            }
            else {
                return 1;
            }
        }

        else
            return 1;

    }



}
