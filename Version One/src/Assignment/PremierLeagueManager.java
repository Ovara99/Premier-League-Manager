package Assignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PremierLeagueManager implements LeagueManager {

    private List<FootballClub> clubList;

    public PremierLeagueManager() {
        this.clubList = new ArrayList<>();
    }

    public PremierLeagueManager(List<FootballClub> clubList) {
        this.clubList = clubList;
    }


    public List<FootballClub> getClubList() {
        return clubList;
    }


    @Override
    public int addClub(FootballClub p) {
        this.clubList.add(p);
        return count();
    }

    public int count() {
        return this.clubList.size();
    }

    public FootballClub getClub(int index) {
        if (index < 0 || index >= count()) {
            return null;
        }
        return this.clubList.get(index);
    }

    @Override
    public int deleteClub(FootballClub p) {
        this.clubList.remove(p);
        return count();

    }

    @Override
    public void showStatistics() {
        if (clubList.isEmpty()) {
            System.out.println("The League is empty");
        } else {
            Scanner input = new Scanner(System.in);
            System.out.println("Insert club name: ");
            String line = input.nextLine().toLowerCase();
            for (int i = 0; i < this.count(); i++) {
                FootballClub p = this.getClub(i);
                if (p.getClubName().equals(line)) {
                    System.out.println("Club " + p.getClubName() + " matches won: " + p.getWins());
                    System.out.println("Club " + p.getClubName() + " matches lost: " + p.getDefeats());
                    System.out.println("Club " + p.getClubName() + " matches draw: " + p.getDraws());
                    System.out.println("Club " + p.getClubName() + " scored goals: " + p.getGoalsScored());
                    System.out.println("Club " + p.getClubName() + " recieved goals: " + p.getGoalsReceived());
                    System.out.println("Club " + p.getClubName() + " points: " + p.getNumPoints());
                    System.out.println("Club " + p.getClubName() + " matches played: " + p.getNumMatches());
                    return;
                }

            }
            System.out.println("No such club in league");
        }
    }

    public void showTable() {
        if (clubList.isEmpty()) {
            System.out.println("The League is empty");
        } else {
            System.out.println("-------PREMIER LEAGUE TABLE------");
            System.out.println("Club Name\t\t\t\tPoints\t\t\tGoal Difference");

            for (int i = 0; i < this.count(); i++) {
                Collections.sort(clubList);
                FootballClub p = this.getClub(i);

                System.out.println("Club: " + p.getClubName() + " Points: " + p.getNumPoints() + "  Goal difference: " + p.getGoalDifference());
            }
        }
    }

}