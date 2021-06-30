package com.project.coursework.Services;

import com.project.coursework.Models.FootballClub;
import com.project.coursework.Models.FootballMatch;
import com.project.coursework.Models.SportsClub;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.*;

@Component
public class PremierLeagueManager implements LeagueManager, Serializable {
    private static final long serialVersionUID = 6L;
    private static PremierLeagueManager leagueManagerInstance;
    private static final int NO_OF_CLUBS_IN_LEAGUE = 20;
    private static final Scanner input = new Scanner(System.in);
    private String season;
    private final List<FootballClub> footballClubs; //store details of football clubs in the Premier League
    private final List<FootballMatch> footballMatches; //store details of football matches related to the Premier League

    private PremierLeagueManager() {  //constructor
        this.season = "";
        this.footballClubs = new ArrayList<>(NO_OF_CLUBS_IN_LEAGUE);
        this.footballMatches = new ArrayList<>();
    }

    public static long getSerialVersionUID() {  //return serialVersionUID
        return serialVersionUID;
    }

    public static PremierLeagueManager getLeagueManagerInstance() { //return leagueManagerInstance
        if (leagueManagerInstance == null) {
            synchronized (PremierLeagueManager.class) {
                if (leagueManagerInstance == null) {
                    System.out.println("Please enter the Premier League Season (YYYY-YYYY) : ");
                    String season = input.nextLine();
                    boolean match = false;
                    try {
                        match = season.matches("^\\d{4}-\\d{4}"); //check whether the user input matches the pattern
                    } catch (Exception exception) {
                        System.out.println("An exception has occurred while trying to validate the user input for Premier League season");
                    }
                    while (!match) {
                        System.out.println("You have entered the Premier League season in an incorrect format." +
                                "\nPlease re-enter the Premier League Season (YYYY-YYYY) : ");
                        season = input.nextLine();
                        try {
                            match = season.matches("^\\d{4}-\\d{4}");
                        } catch (Exception exception) {
                            System.out.println("An exception has occurred while trying to validate the user input for Premier League season");
                        }
                    }
                    leagueManagerInstance = new PremierLeagueManager(); //create new PremierLeagueManager object
                    leagueManagerInstance.setSeason(season);
                }
            }
        }
        return leagueManagerInstance;
    }

    public static int getNoOfClubsInLeague() { //return NO_OF_CLUBS_IN_LEAGUE
        return NO_OF_CLUBS_IN_LEAGUE;
    }

    public String getSeason() { //return season
        return season;
    }

    public void setSeason(String season) { //set a value for season
        this.season = season;
    }

    public List<FootballClub> getFootballClubs() { //return footballClubs
        return footballClubs;
    }

    public List<FootballMatch> getFootballMatches() { //return footballMatches
        return footballMatches;
    }

    @Override
    public void addFootballClub() { //add a football club into the Premier League
        if (footballClubs.size() < NO_OF_CLUBS_IN_LEAGUE) { //check whether there are more football clubs to be added into the Premier League
            System.out.println("Please enter the following details of the football club that you want to add in Premier League");
            System.out.println("Name of the club : ");
            String clubName = input.nextLine().toLowerCase();
            System.out.println("Club location : ");
            String clubLocation = input.nextLine().toLowerCase();

            boolean alreadyAdded = false;
            for (FootballClub footballClub : footballClubs) { //check whether the entered football club is already playing in the Premier League
                if (footballClub.getClubName().equals(clubName) && footballClub.getClubLocation().equals(clubLocation)) {
                    System.out.println("The football club you are trying to add, has already been added to the Premier League");
                    alreadyAdded = true;
                    break;
                }
            }

            if (!alreadyAdded) {
                System.out.println("Here are the details you have provided related to the football club\n");
                System.out.println("\tClub Name : " + clubName + "\n\tClub Location : " + clubLocation);
                System.out.println("\nDo you want to make any changes into these details ? (Y/N) : ");
                String changeDetails = input.nextLine().toUpperCase();
                while (!changeDetails.equals("Y") && !changeDetails.equals("N")) {
                    System.out.println("You have to enter either \"Y\" or \"N\" as the input. Please try again");
                    changeDetails = input.nextLine().toUpperCase();
                }
                if (changeDetails.equals("Y")) {
                    addFootballClub(); //let user to change the provided details of the football club
                } else {
                    SportsClub footballClub = new FootballClub(clubName, clubLocation); //create a new FootballClub object
                    footballClubs.add((FootballClub) footballClub);
                    updateClubPositionInCLI(footballClubs);  //update club positions after adding a new football club
                    System.out.println("Following football club has been successfully added into the Premier League\n");
                    System.out.println(footballClub.toString());
                }
            } else {
                System.out.println("\nDo you want to try again adding a football club into the Premier League ? (Y/N) : ");
                String tryAgain = input.nextLine().toUpperCase();
                while (!tryAgain.equals("Y") && !tryAgain.equals("N")) {
                    System.out.println("You have to enter either \"Y\" or \"N\" as the input. Please try again");
                    tryAgain = input.nextLine().toUpperCase();
                }
                if (tryAgain.equals("Y")) {
                    addFootballClub(); //let user to try adding another football club into the Premier League
                }
            }
        } else {
            System.out.println("You have already added all " + NO_OF_CLUBS_IN_LEAGUE + " football clubs into the Premier League");
        }
    }

    @Override
    public void relegateFootballClub() { //relegate a football club from the Premier League
        if (!footballClubs.isEmpty()) { //check whether there are football clubs currently playing in the Premier League
            System.out.println("Name of the football club to relegate from the Premier League : ");
            String clubName = input.nextLine().toLowerCase();

            boolean containsClub = false;
            List<FootballClub> matchingClubs = new ArrayList<>();
            for (FootballClub footballClub : footballClubs) { //check whether the entered football club is currently playing in the Premier League
                if (footballClub.getClubName().equals(clubName)) {
                    containsClub = true;
                    matchingClubs.add(footballClub);
                }
            }

            if (containsClub) {
                FootballClub clubToRelegate;
                if (matchingClubs.size() > 1) { //check whether there are multiple football clubs playing in the Premier League with the provided name
                    System.out.println("There are several football clubs currently playing in the Premier League with the provided club name");
                    int optionNo = 1;
                    for (FootballClub footballClub : matchingClubs) { //provide an option to select one football club among the football clubs with the same name
                        System.out.println("\nOption Number : " + optionNo + "\n\tClub Name : " + footballClub.getClubName() + "\n\tClub Location : "
                                + footballClub.getClubLocation());
                        optionNo ++;
                    }
                    System.out.println("Please enter the relevant option number of the football club that you want to relegate from the Premier League : ");
                    while (true) {
                        try {
                            Scanner option = new Scanner(System.in);
                            int selectedOption = option.nextInt();
                            if (selectedOption > 0 && selectedOption <= matchingClubs.size()) {
                                clubToRelegate = matchingClubs.get(selectedOption - 1);
                                break;
                            } else {
                                System.out.println("You have to enter the relevant integer option number of the football club that you want to relegate from the Premier League."
                                        + "Please try again");
                            }
                        } catch (InputMismatchException inputMismatchException) {
                            System.out.println("You have to enter the relevant integer option number of the football club that you want to relegate from the Premier League." +
                                    "Please try again");
                        }
                    }
                } else {
                    clubToRelegate = matchingClubs.get(0);
                }
                System.out.println("Here are the details related to the football club that you are going to relegate from the Premier League\n");
                System.out.println(clubToRelegate.toString());
                System.out.println("\nDo you really want to relegate this football club from the Premier League ? (Y/N) : ");
                String relegate = input.nextLine().toUpperCase();
                while (!relegate.equals("Y") && !relegate.equals("N")) {
                    System.out.println("You have to enter either \"Y\" or \"N\" as the input. Please try again");
                    relegate = input.nextLine().toUpperCase();
                }
                if (relegate.equals("Y")) {
                    footballClubs.remove(clubToRelegate); //relegate the selected football club from the Premier League
                    System.out.println("The following football club has been successfully relegated from the Premier League\n");
                    System.out.println(clubToRelegate.toString());
                    updateClubPositionInCLI(footballClubs); //update club positions after relegating the selected football club
                }
            } else {
                System.out.println("There's not any football club with the name \"" + clubName + "\" in the Premier League at the moment.");
                System.out.println("Do you want to try again relegating a football club from the Premier League ? (Y/N) : ");
                String tryAgain = input.nextLine().toUpperCase();
                while (!tryAgain.equals("Y") && !tryAgain.equals("N")) {
                    System.out.println("You have to enter either \"Y\" or \"N\" as the input. Please try again");
                    tryAgain = input.nextLine().toUpperCase();
                }
                if (tryAgain.equals("Y")) {
                    relegateFootballClub(); //let user to try relegating another football club from the Premier League
                }
            }
        } else {
            System.out.println("There aren't any football clubs in the Premier League at the moment. Therefore, there are no clubs to relegate");
        }
    }

    @Override
    public void displayStatistics() { //display statistics of a selected football club
        if (!footballClubs.isEmpty()) { //check whether there are football clubs currently playing in the Premier League
            System.out.println("Name of the football club to display the statistics : ");
            String clubName = input.nextLine().toLowerCase();
            boolean containsClub = false;
            List<FootballClub> matchingClubs = new ArrayList<>();
            for (FootballClub footballClub : footballClubs) { //check whether the entered football club is currently playing in the Premier League
                if (footballClub.getClubName().equals(clubName)) {
                    containsClub = true;
                    matchingClubs.add(footballClub);
                }
            }

            if (containsClub) {
                FootballClub clubToDisplayStats;
                if (matchingClubs.size() > 1) { //check whether there are multiple football clubs playing in the Premier League with the provided club name
                    System.out.println("There are several football clubs currently playing in the Premier League with the provided club name");
                    int optionNo = 1;
                    for (FootballClub footballClub : matchingClubs) { //provide an option to select one football club among the football clubs with the same name
                        System.out.println("\nOption Number : " + optionNo + "\n\tClub Name : " + footballClub.getClubName() + "\n\tClub Location : "
                                + footballClub.getClubLocation());
                        optionNo ++;
                    }
                    System.out.println("Please enter the relevant option number of the football club that you want to display statistics : ");
                    while (true) {
                        try {
                            Scanner option = new Scanner(System.in);
                            int selectedOption = option.nextInt();
                            if (selectedOption > 0 && selectedOption <= matchingClubs.size()) {
                                clubToDisplayStats = matchingClubs.get(selectedOption - 1);
                                break;
                            } else {
                                System.out.println("You have to enter the relevant integer option number of the football club that you want to display statistics." +
                                        "Please try again");
                            }
                        } catch (InputMismatchException inputMismatchException) {
                            System.out.println("You have to enter the relevant integer option number of the football club that you want to display statistics." +
                                    "Please try again");
                        }
                    }
                } else {
                    clubToDisplayStats = matchingClubs.get(0);
                }
                System.out.println("Here are the statistics related to the football club you selected\n");
                updateClubPositionInCLI(footballClubs);
                System.out.println(clubToDisplayStats.toString()); //display statistics of the selected football club
            } else {
                System.out.println("There's not any football club with the name \"" + clubName + "\" in the Premier League at the moment.");
                System.out.println("Do you want to try again displaying statistics of a football club in the Premier League ? (Y/N) : ");
                String tryAgain = input.nextLine().toUpperCase();
                while (!tryAgain.equals("Y") && !tryAgain.equals("N")) {
                    System.out.println("You have to enter either \"Y\" or \"N\" as the input. Please try again");
                    tryAgain = input.nextLine().toUpperCase();
                }
                if (tryAgain.equals("Y")) {
                    displayStatistics(); //let user to try displaying statistics of another football club
                }
            }
        } else {
            System.out.println("There aren't any football clubs in the Premier League at the moment. Therefore, there are no statistics to show about any football club");
        }
    }

    @Override
    public void displayLeagueTableInCLI() { //display Premier League Table in CLI
        if (!footballClubs.isEmpty()) { //check whether there are football clubs currently playing in the Premier League
            System.out.println("This is the current Premier League Table for season " + season);
            updateClubPositionInCLI(footballClubs); //update club positions of the football clubs
            System.out.printf("%n%-10s%-35s%-10s%-10s%-10s%-10s%-20s%-20s%-20s%-20s",
                    "Position", "Club", "Played", "Won", "Drown", "Lost", "Goals Scored", "Goals Received", "Goal Difference", "Points");
            for (FootballClub footballClub : footballClubs) {
                if (footballClub != null) { //display the Premier League Table according to the current statistics
                    System.out.printf("%n%-10d%-35s%-10d%-10d%-10d%-10d%-20d%-20d%-+20d%-20d", footballClub.getClubPosition(), footballClub.getClubName() + ", "
                                    + footballClub.getClubLocation(), footballClub.getNoOfPlayedMatches(), footballClub.getNoOfWins(), footballClub.getNoOfDraws(),
                            footballClub.getNoOfDefeats(), footballClub.getGoalsScored(), footballClub.getGoalsReceived(), footballClub.getGoalDifference(),
                            footballClub.getNoOfPoints());
                }
            }
            System.out.println();
        } else {
            System.out.println("There aren't any football clubs in the Premier League at the moment. Therefore, there are no statistics to show in the Premier League Table");
        }
    }

    @Override
    public void addPlayedMatch() { //add details of a played football match
        if (!footballClubs.isEmpty()) { //check whether there are football clubs currently playing in the Premier League
            System.out.println("Please enter the following details of the football match that you want to add\n");
            System.out.println("Name of the home team : ");
            String homeTeamName = input.nextLine().toLowerCase();
            System.out.println("Name of the away team : ");
            String awayTeamName = input.nextLine().toLowerCase();

            boolean homeTeamInPL = false;
            boolean awayTeamInPL = false;
            List<FootballClub> matchingHomeTeamClubs = new ArrayList<>();
            List<FootballClub> matchingAwayTeamClubs = new ArrayList<>();
            for (FootballClub footballClub : footballClubs) {
                if (footballClub.getClubName().equals(homeTeamName)) { //check whether the provided home team is currently playing in the Premier League
                    homeTeamInPL = true;
                    matchingHomeTeamClubs.add(footballClub);
                } else if (footballClub.getClubName().equals(awayTeamName)) { //check whether the provided away team is currently playing in the Premier League
                    awayTeamInPL = true;
                    matchingAwayTeamClubs.add(footballClub);
                }
            }

            if (!homeTeamInPL || !awayTeamInPL) { //check if any of the home team or away team is not currently playing in the Premier League
                if (!homeTeamInPL && !awayTeamInPL) {
                    System.out.println("\nThe football clubs \"" + homeTeamName + "\" and \"" + awayTeamName + "\" are not currently playing in the Premier League");
                } else {
                    System.out.printf("\nThe football club \"%s\" is not currently playing in the Premier League",!homeTeamInPL ? homeTeamName : awayTeamName);
                }
                System.out.println("\nDo you want to try again adding the details of a football match ? (Y/N) : ");
                String tryAgain = input.nextLine().toUpperCase();
                while (!tryAgain.equals("Y") && !tryAgain.equals("N")) {
                    System.out.println("You have to enter either \"Y\" or \"N\" as the input. Please try again");
                    tryAgain = input.nextLine().toUpperCase();
                }
                if (tryAgain.equals("Y")) {
                    addPlayedMatch(); //let user to try again adding another football match
                }
            } else {
                FootballClub homeTeam;
                FootballClub awayTeam;
                if (matchingHomeTeamClubs.size() > 1) { //check whether there are multiple football clubs currently playing in the Premier League with the name provided for home team
                    System.out.println("There are several football clubs currently playing in the Premier League with the provided club name for home team");
                    int optionNo = 1;
                    for (FootballClub footballClub : matchingHomeTeamClubs) { //give user an option to select one football club among the football clubs as the home team
                        System.out.println("\nOption Number : " + optionNo + "\n\tClub Name : " + footballClub.getClubName() + "\n\tClub Location : "
                                + footballClub.getClubLocation());
                        optionNo ++;
                    }
                    System.out.println("Please enter the relevant option number of the football club that you want to select as the home team : ");
                    while (true) {
                        try {
                            Scanner option = new Scanner(System.in);
                            int selectedOption = option.nextInt();
                            if (selectedOption > 0 && selectedOption <= matchingHomeTeamClubs.size()) {
                                homeTeam = matchingHomeTeamClubs.get(selectedOption - 1);
                                break;
                            } else {
                                System.out.println("You have to enter the relevant integer option number of the football club that you want to select as the home team." +
                                        "Please try again");
                            }
                        } catch (InputMismatchException inputMismatchException) {
                            System.out.println("You have to enter the relevant integer option number of the football club that you want to select as the home team." +
                                    "Please try again");
                        }
                    }
                } else {
                    homeTeam = matchingHomeTeamClubs.get(0);
                }

                if (matchingAwayTeamClubs.size() > 1) { //check whether there are multiple football clubs currently playing in the Premier League with the name provided for away team
                    System.out.println("There are several football clubs currently playing in the Premier League with the provided club name for away team");
                    int optionNo = 1;
                    for (FootballClub footballClub : matchingAwayTeamClubs) { //give user an option to select one football club among the football clubs as the away team
                        System.out.println("\nOption Number : " + optionNo + "\n\tClub Name : " + footballClub.getClubName() + "\n\tClub Location : "
                                + footballClub.getClubLocation());
                        optionNo ++;
                    }
                    System.out.println("Please enter the relevant option number of the football club that you want to select as the away team : ");
                    while (true) {
                        try {
                            Scanner option = new Scanner(System.in);
                            int selectedOption = option.nextInt();
                            if (selectedOption > 0 && selectedOption <= matchingAwayTeamClubs.size()) {
                                awayTeam = matchingAwayTeamClubs.get(selectedOption - 1);
                                break;
                            } else {
                                System.out.println("You have to enter the relevant integer option number of the football club that you want to select as the away team." +
                                        "Please try again");
                            }
                        } catch (InputMismatchException inputMismatchException) {
                            System.out.println("You have to enter the relevant integer option number of the football club that you want to select as the away team." +
                                    "Please try again");
                        }
                    }
                } else {
                    awayTeam = matchingAwayTeamClubs.get(0);
                }

                int year;
                int month;
                int date;
                LocalDate matchDate = null;
                boolean validDate = false;
                int homeTeamGoals;
                int awayTeamGoals;

                loop :
                while (!validDate) {
                    while (true) { //get year of the match from the user
                        try {
                            Scanner yearInput = new Scanner(System.in);
                            String[] splitYears = getSeason().split("-"); //store relevant years of the season into a String Array
                            int seasonFirstYear = Integer.parseInt(splitYears[0].trim());
                            int seasonSecondYear = Integer.parseInt(splitYears[1].trim());
                            System.out.println("Match year (YYYY) : ");
                            year = yearInput.nextInt();
                            if (year == seasonFirstYear || year == seasonSecondYear) {
                                break;
                            } else {
                                System.out.printf("Match year should be either %s according to the Premier League season. Please re-check and enter the match year " +
                                        "relevant to the football match, according to the given format (YYYY)\n", (seasonFirstYear + " or " + seasonSecondYear));
                            }
                        } catch (NumberFormatException numberFormatException) {
                            System.out.println("An exception has occurred due to the provision of an invalid input format for the Premier League season");
                            break loop;
                        }
                        catch (InputMismatchException inputMismatchException) {
                            System.out.println("Please enter the match year relevant to the football match, according to the given format (YYYY)");
                        }
                    }

                    while (true) { //get month of the match from the user
                        try {
                            Scanner monthInput = new Scanner(System.in);
                            System.out.println("Match month (MM) : ");
                            month = monthInput.nextInt();
                            if (month >= 1 && month <= 12) {
                                break;
                            } else {
                                System.out.println("Match month should be between 1 to 12. Please re-check and enter the match month relevant to the football match, " +
                                        "according to the given format (MM)");
                            }
                        } catch (InputMismatchException inputMismatchException) {
                            System.out.println("Please enter the match month relevant to the football match, according to the given format (MM)");
                        }
                    }

                    while (true) { //get date of the match from the user
                        try {
                            Scanner dateInput = new Scanner(System.in);
                            System.out.println("Match date (DD) : ");
                            date = dateInput.nextInt();
                            int daysInMonth = Month.of(month).length(Year.isLeap(year));
                            if (date >= 1 && date <= daysInMonth) {
                                break;
                            } else {
                                System.out.println("Match date should be between 1 to " + daysInMonth + ". Please re-check and enter the match date relevant to the " +
                                        "football match, according to the given format (DD)");
                            }
                        } catch (InputMismatchException inputMismatchException) {
                            System.out.println("Please enter the match date relevant to the football match, according to the given format (DD)");
                        }
                    }

                    matchDate = LocalDate.of(year, month, date);
                    if (matchDate.compareTo(LocalDate.now()) > 0) { //check whether the provided match date is a future date
                        System.out.println("You have provided a future date as the match date. Please re-check and try again adding the date of the football match.");
                    } else {
                        validDate = true;
                    }
                }

                while (true) { //get no of goals scored by the home team, from the user
                    try {
                        Scanner homeTeamGoalsInput = new Scanner(System.in);
                        System.out.println("Home team goals scored : ");
                        homeTeamGoals = homeTeamGoalsInput.nextInt();
                        if (homeTeamGoals >= 0) {
                            break;
                        } else {
                            System.out.println("Goals scored by \"" + homeTeamName + "\" should be greater than or equal to zero. Please re-check and try again");
                        }
                    } catch (InputMismatchException inputMismatchException) {
                        System.out.println("Please enter a valid integer as the number of goals scored by \"" + homeTeamName + "\"");
                    }
                }

                while (true) { //get no of goals scored by the away team, from the user
                    try {
                        Scanner awayTeamGoalsInput = new Scanner(System.in);
                        System.out.println("Away team goals scored : ");
                        awayTeamGoals = awayTeamGoalsInput.nextInt();
                        if (awayTeamGoals >= 0) {
                            break;
                        } else {
                            System.out.println("Goals scored by \"" + awayTeamName + "\" should be greater than or equal to zero. Please re-check and try again");
                        }
                    } catch (InputMismatchException inputMismatchException) {
                        System.out.println("Please enter a valid integer as the number of goals scored by \"" + awayTeamName + "\"");
                    }
                }

                boolean alreadyAdded = false;
                for (FootballMatch footballMatch : footballMatches) { //check whether the provided match details has already been added previously
                    if (footballMatch.getHomeTeam().equals(homeTeam) && footballMatch.getAwayTeam().equals(awayTeam) && footballMatch.getMatchDate().equals(matchDate) &&
                            footballMatch.getHomeTeamGoalsScored() == homeTeamGoals && footballMatch.getAwayTeamGoalsScored() == awayTeamGoals) {
                        System.out.println("The football match details you are trying to add, has already been added");
                        alreadyAdded = true;
                        break;
                    }
                }

                if (!alreadyAdded) {
                    System.out.println("Here are the details you have provided related to the football match\n");
                    System.out.println("\tHome team Name : " + homeTeamName + ", " + homeTeam.getClubLocation() + "\n\tAway team Name : " + awayTeamName + ", " +
                            awayTeam.getClubLocation() + "\n\tMatch Date : " + matchDate + "\n\tHome team Goals Scored : " + homeTeamGoals + "\n\tAway team Goals Scored : " +
                            awayTeamGoals);
                    System.out.println("\nDo you want to make any changes into these details ? (Y/N) : ");
                    String changeDetails = input.nextLine().toUpperCase();
                    while (!changeDetails.equals("Y") && !changeDetails.equals("N")) {
                        System.out.println("You have to enter either \"Y\" or \"N\" as the input. Please try again");
                        changeDetails = input.nextLine().toUpperCase();
                    }
                    if (changeDetails.equals("Y")) { //let user to change the provided details of the football match
                        addPlayedMatch();
                    } else {
                        FootballMatch footballMatch = new FootballMatch(homeTeam, awayTeam, matchDate, homeTeamGoals, awayTeamGoals); //create FootballMatch object
                        footballMatches.add(footballMatch);
                        footballMatch.updateStatistics(homeTeam, awayTeam); //update statistics of the two football clubs
                        updateClubPositionInCLI(footballClubs); //update the positions of the football clubs according to current statistics
                        System.out.println("Following football match details has been successfully recorded\n");
                        System.out.println(footballMatch.toString());
                    }
                } else {
                    System.out.println("\nDo you want to try again adding details related to a football match ? (Y/N) : ");
                    String tryAgain = input.nextLine().toUpperCase();
                    while (!tryAgain.equals("Y") && !tryAgain.equals("N")) {
                        System.out.println("You have to enter either \"Y\" or \"N\" as the input. Please try again");
                        tryAgain = input.nextLine().toUpperCase();
                    }
                    if (tryAgain.equals("Y")) {
                        addPlayedMatch(); //let user to try adding details of another football match
                    }
                }
            }
        } else {
            System.out.println("There aren't any football clubs in the Premier League at the moment.\nPlease add the corresponding football clubs into Premier League" +
                    " in order to add details of the football match");
        }
    }

    @Override
    public void saveDataToFile() { //save currently available Premier League data into a file
        if (footballClubs.size() == 0 && footballMatches.size() == 0) { //check whether there are any available data to save into the file
            System.out.println("There are no data related to the Premier League " + season + " to save into the file");
        } else {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(String.format("src/main/java/com/project/coursework/Services/Premier League %s.ser", season));
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(footballClubs); //write the footballClubs ArrayList object into the file
                objectOutputStream.writeObject(footballMatches); //write the footballMatches ArrayList object into the file
                objectOutputStream.flush();
                fileOutputStream.close();
                objectOutputStream.close();
                System.out.println("All the data related to the Premier League " + season + " have been successfully saved into the file");
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("The file you are trying to use, to save the data, cannot be found");
            } catch (Exception e) {
                System.out.println("A problem has occurred while trying to save the data related to the Premier League " + season + " into the file");
            }
        }
    }

    @Override
    public void loadDataFromFile() { //load the previously saved data related to the Premier League, from the file
        try {
            FileInputStream fileInputStream = new FileInputStream(String.format("src/main/java/com/project/coursework/Services/Premier League %s.ser", season));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            for (;;) {
                try {
                    @SuppressWarnings("unchecked")
                    List<FootballClub> clubs = (ArrayList<FootballClub>) objectInputStream.readObject(); //read the ArrayList object from the file
                    boolean alreadyLoadedClub = false;
                    for (FootballClub club : clubs) { //check whether the each element in the clubs list has already loaded to the footballClubs ArrayList
                        for (FootballClub footballClub : footballClubs) {
                            if (club.equals(footballClub)) {
                                alreadyLoadedClub = true;
                                break;
                            }
                        }
                        if (!alreadyLoadedClub) {
                            footballClubs.add(club); //add the current FootballClub object into the footballClubs ArrayList
                        }
                    }

                    @SuppressWarnings("unchecked")
                    List<FootballMatch> matches = (ArrayList<FootballMatch>) objectInputStream.readObject(); //read the ArrayList object from the file
                    boolean alreadyLoadedMatch = false;
                    for (FootballMatch match : matches) { //check whether the each element in the matches list has already loaded to the footballMatches ArrayList
                        for (FootballMatch footballMatch : footballMatches) {
                            if (match.equals(footballMatch)) {
                                alreadyLoadedMatch = true;
                                break;
                            }
                        }
                        if (!alreadyLoadedMatch) {
                            footballMatches.add(match); //add the current FootballMatch object into the footballMatches ArrayList
                        }
                    }
                } catch (EOFException eofException) {
                    break;
                }
            }
            fileInputStream.close();
            objectInputStream.close();
            System.out.println("All the data related to the Premier League " + season + " have been successfully loaded from the file");
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("The file you are trying to use, to load the data, cannot be found");
        } catch (Exception e) {
            System.out.println("A problem has occurred while trying to load the data related to the Premier League " + season + " from the file");
        }
    }

    public void updateClubPositionInCLI(List<FootballClub> footballClubs) { //update the positions of all football clubs currently playing in the Premier League, to use in CLI
        Collections.sort(footballClubs); //sort the footballClubs ArrayList according to the descending order of the each football club`s points and further according to goal difference
        for (FootballClub footballClub : footballClubs) {
            int index = footballClubs.indexOf(footballClub);
            if (index != 0) {
                if (footballClub.compareTo(footballClubs.get(index - 1)) != 0) {
                    footballClub.setClubPosition(index + 1);
                } else {
                    footballClub.setClubPosition(footballClubs.get(index - 1).getClubPosition());
                }
            } else {
                footballClub.setClubPosition(index + 1);
            }
        }
    }

}
