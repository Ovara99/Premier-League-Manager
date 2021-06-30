package com.project.coursework.Controllers;

import com.project.coursework.Models.FootballClub;
import com.project.coursework.Models.FootballMatch;
import com.project.coursework.Services.PremierLeagueManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins ="http://localhost:4200")
@RequestMapping("/api")

public class GUIController {
    private final List<FootballClub> footballClubs = PremierLeagueManager.getLeagueManagerInstance().getFootballClubs();
    private final List<FootballMatch> footballMatches = PremierLeagueManager.getLeagueManagerInstance().getFootballMatches();
    private final String season = PremierLeagueManager.getLeagueManagerInstance().getSeason();

    @GetMapping("/sortLeagueTable")
    public List<FootballClub> displayLeagueTableInGUI(String sortOption) { //display Premier League Table in GUI
        if (!footballClubs.isEmpty()) { //check whether there are any football clubs currently playing in the Premier League
            List<FootballClub> tableData = new ArrayList<>(footballClubs);
            switch (sortOption) {
                case "Points" :
                    tableData.sort((FootballClub object1, FootballClub object2) -> { //sort the ArrayList according to descending order of the each football club`s points
                        if (object1 != null && object2 != null) {
                            return object2.getNoOfPoints() - object1.getNoOfPoints();
                        }
                        return 0;
                    });
                    break;
                case "Goals Scored" :
                    tableData.sort((FootballClub object1, FootballClub object2) -> { //sort the ArrayList according to descending order of the each football club`s goals scored
                        if (object1 != null && object2 != null) {
                            return object2.getGoalsScored() - object1.getGoalsScored();
                        }
                        return 0;
                    });
                    break;
                case "No of wins":
                    tableData.sort((FootballClub object1, FootballClub object2) -> { //sort the ArrayList according to descending order of the each football club`s no of wins
                        if (object1 != null && object2 != null) {
                            return object2.getNoOfWins() - object1.getNoOfWins();
                        }
                        return 0;
                    });
                    break;
            }
            for (FootballClub footballClub : tableData) { //update the club position of each football club
                int index = tableData.indexOf(footballClub);
                if (index != 0) {
                    boolean condition = false;
                    switch (sortOption) {
                        case "Points" :
                            condition = ((tableData.get(index - 1).getNoOfPoints()) - footballClub.getNoOfPoints() != 0);
                            break;
                        case "Goals Scored" :
                            condition = ((tableData.get(index - 1).getGoalsScored()) - footballClub.getGoalsScored() != 0);
                            break;
                        case "No of wins":
                            condition = ((tableData.get(index - 1).getNoOfWins()) - footballClub.getNoOfWins() != 0);
                            break;
                    }
                    if (condition) {
                        footballClub.setClubPosition(index + 1);
                    } else {
                        footballClub.setClubPosition(tableData.get(index - 1).getClubPosition());
                    }
                } else {
                    footballClub.setClubPosition(index + 1);
                }
            }
            return tableData;
        }
        return null;
    }

    @GetMapping("/randomMatch")
    public FootballMatch generateRandomMatch() { //generate a random footballMatch
        if (footballClubs.size() >= 2) { //check whether there are at least two football clubs currently playing in the Premier League
            boolean uniqueMatch = false;
            FootballMatch footballMatch = null;
            while (!uniqueMatch) {
                Random randomNumber = new Random();
                int homeTeamIndex = randomNumber.nextInt(footballClubs.size());
                FootballClub homeTeam = footballClubs.get(homeTeamIndex); // get a random footballClub for homeTeam
                int awayTeamIndex = randomNumber.nextInt(footballClubs.size());
                while (awayTeamIndex == homeTeamIndex) { //check whether the awayTeam is the same as the homeTeam
                    awayTeamIndex = randomNumber.nextInt(footballClubs.size());
                }
                FootballClub awayTeam = footballClubs.get(awayTeamIndex);  // get a random footballClub for awayTeam
                String[] splitSeasonYears = season.split("-"); //store relevant years of the season into a String Array
                List<Integer> seasonYears = new ArrayList<>();
                try {
                    int seasonFirstYear = Integer.parseInt(splitSeasonYears[0].trim());
                    int seasonSecondYear = Integer.parseInt(splitSeasonYears[1].trim());
                    seasonYears.add(seasonFirstYear);
                    seasonYears.add(seasonSecondYear);
                } catch (NumberFormatException numberFormatException) {
                    System.out.println("An exception has occurred due to the provision of an invalid input format for the Premier League season");
                    break;
                }
                int year = seasonYears.get(randomNumber.nextInt(seasonYears.size())); //get a random year from valid years
                int month = randomNumber.nextInt(12) + 1; //generate a random number from 1 to 12 for month
                int daysInMonth = Month.of(month).length(Year.isLeap(year)); //check number of available dates in the selected month
                int date = randomNumber.nextInt(daysInMonth) + 1; //generate a random number from 1 to maximum days in the selected month, for date
                LocalDate matchDate = LocalDate.of(year, month, date);
                while (matchDate.compareTo(LocalDate.now()) > 0) { //check whether the generated match date is a future date
                    year = seasonYears.get(randomNumber.nextInt(seasonYears.size()));
                    month = randomNumber.nextInt(12) + 1;
                    daysInMonth = Month.of(month).length(Year.isLeap(year));
                    date = randomNumber.nextInt(daysInMonth) + 1;
                    matchDate = LocalDate.of(year, month, date);
                }
                int homeTeamScore = randomNumber.nextInt(11); //generate a random number from 0 to 10 for homeTeamScore
                int awayTeamScore = randomNumber.nextInt(11); //generate a random number from 0 to 10 for awayTeamScore

                boolean duplicateMatch = false;
                for (FootballMatch match : footballMatches) { //check whether the same footballMatch details has been added or generated before
                    if (match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam) && match.getMatchDate().equals(matchDate) &&
                            match.getHomeTeamGoalsScored() == homeTeamScore && match.getAwayTeamGoalsScored() == awayTeamScore) {
                        duplicateMatch = true;
                        break;
                    }
                }
                if (!duplicateMatch) {
                    uniqueMatch = true;
                    footballMatch = new FootballMatch(homeTeam,awayTeam,matchDate,homeTeamScore,awayTeamScore); // create footballMatch object
                    footballMatches.add(footballMatch);
                    footballMatch.updateStatistics(homeTeam, awayTeam); //update statistics of the two football clubs
                }
            }
            return footballMatch;
        }
        return null;
    }

    @GetMapping("/sortMatches")
    public List<FootballMatch> displayMatchesSortedByDate() {
        if (!footballMatches.isEmpty()) { //check whether there are any football matches which have been played in the Premier League
            List<FootballMatch> tableData = new ArrayList<>(footballMatches);  //create an ArrayList to store footballMatch details

            tableData.sort((FootballMatch object1, FootballMatch object2) -> { //sort the ArrayList according to ascending order of the each football match's played date
                if (object1 != null && object2 != null) {
                    return object1.getMatchDate().compareTo(object2.getMatchDate());
                }
                return 0;
            });
            return tableData;
        }
        return null;
    }

    @GetMapping("/searchMatches")
    public List<FootballMatch> searchMatchesByDate(String searchDate) { //search footballMatch details using the played date
        if (!footballMatches.isEmpty()) { //check whether there are any football matches which have been played in the Premier League
            searchDate = searchDate.trim();
            try {
                if (!searchDate.isEmpty()) {
                    String[] splitDate = searchDate.split("-"); //split user input
                    int year = Integer.parseInt(splitDate[0]);
                    int month = Integer.parseInt(splitDate[1]);
                    int date = Integer.parseInt(splitDate[2]);
                    if (year > 0 && month >= 1 && month <= 12 && date >= 1 && date <= Month.of(month).length(Year.isLeap(year))) {
                        LocalDate matchDateToSearch = LocalDate.of(year, month, date); //create a LocalDate object of provided date
                        List<FootballMatch> filteredMatches = footballMatches.stream().filter(Objects::nonNull) //get a list of matches which have played on the given date
                                .filter(footballMatch -> footballMatch.getMatchDate().equals(matchDateToSearch)).collect(Collectors.toList());
                        if (!filteredMatches.isEmpty()) {
                            return new ArrayList<>(filteredMatches);
                        } else { //alert if there are no matches played on the given date
                            System.out.println("There aren't any matches played on " + matchDateToSearch + " in the Premier League");
                        }
                    } else { //alert if the given date is invalid
                        System.out.println("Please enter a valid date in the given format (YYYY-MM-DD) into the search bar");
                    }
                } else { //alert if the search item is invalid
                    System.out.println("Please enter a valid date in the given format (YYYY-MM-DD) into the search bar");
                }
            } catch (Exception exception) {
                System.out.println("Please enter a valid date in the given format (YYYY-MM-DD) into the search bar");
            }
        } else {
            System.out.println("There aren't any football matches played in the Premier League yet.");
        }
        return null;
    }

}
