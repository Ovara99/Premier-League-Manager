package Assignment;

public class SportsClub {     //super class and a POJO class
    private String ClubName;
    private String location;
    private String statistics;

    public SportsClub(String ClubName){
        this.ClubName = ClubName;
    }


    public SportsClub(String ClubName, String location) {
        this.ClubName = ClubName;
        this.location = location;

    }


    // public method to retrieve the ClubName
    public String getClubName(){
        return ClubName;
    }

    public String getLocation(){
        return location;
    }


    public void setClubName(String ClubName){
        this.ClubName=ClubName;
    }
    public void setLocation(String location){
        this.location=location;
    }


    public String getStatistics() {
        return statistics;
    }

    public void setStatistics(String statistics) {
        this.statistics = statistics;
    }

    @Override
    public String toString() {
        return "ClubName='" + ClubName + '\'' + ", location='" + location + '\'' + ", statistics=" + statistics;
    }

}
