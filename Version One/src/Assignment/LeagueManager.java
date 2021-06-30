package Assignment;

import java.util.List;

public interface LeagueManager {
    int addClub(FootballClub p);
    int deleteClub(FootballClub p);
    FootballClub getClub(int i);
    void showStatistics();

    int count();

    List<FootballClub> getClubList();

    void showTable();
    //void setTable();
    //void addMatch();

}

