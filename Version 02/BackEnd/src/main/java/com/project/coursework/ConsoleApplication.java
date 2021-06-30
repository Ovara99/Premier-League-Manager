package com.project.coursework;

import com.project.coursework.Services.LeagueManager;
import com.project.coursework.Services.PremierLeagueManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@RestController

public class ConsoleApplication implements CommandLineRunner {

    @Autowired
    private static final LeagueManager leagueManager = PremierLeagueManager.getLeagueManagerInstance(); //get LeagueManager object

    public static void main(String[] args) {
        SpringApplication.run(ConsoleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner input = new Scanner(System.in);
        leagueManager.loadDataFromFile(); //load all the data from the text file
        loop:
        while (true) { //view menu for the Premier League Manager
            System.out.println("\nWelcome to the Premier League Manager program!\n");
            List<String> menuOptions = new ArrayList<>(Arrays.asList("----------Menu----------", "A - Add a football club to the Premier League",
                    "B - Relegate a football club from the Premier League", "C - Display statistics of a selected football club", "D - Display the Premier League Table in CLI",
                    "E - Add details of a played match", "F - Display Premier League statistics in GUI", "G - Save Premier League data into a file", "Q - Quit Program"));
            for (String i : menuOptions) {
                System.out.println(i);
            }
            Scanner option = new Scanner(System.in);
            System.out.println("\nPlease enter the relevant letter of the option you want to select : ");
            String selectedOption = option.nextLine().toUpperCase(); //let user to select an option from the menu

            switch (selectedOption) {
                case "A" :
                    leagueManager.addFootballClub();
                    break;
                case "B" :
                    leagueManager.relegateFootballClub();
                    break;
                case "C" :
                    leagueManager.displayStatistics();
                    break;
                case "D" :
                    leagueManager.displayLeagueTableInCLI();
                    break;
                case "E" :
                    leagueManager.addPlayedMatch();
                    break;
                case "F" :
                    System.out.println("GUI is starting. Please wait for a moment");
                    String GUIUrl = "http://localhost:4200";
                    System.setProperty("java.awt.headless","false");
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        desktop.browse(new URI(GUIUrl)); //open GUI in the browser
                    } catch (Exception exception) {
                        System.out.println("Exception has occurred while trying to open the GUI");
                    }
                    break;
                case "G" :
                    leagueManager.saveDataToFile();
                    break;
                case "Q" :
                    System.out.println("Before exiting from the program, make sure you have stored all the data related to the Premier League using 'F' option in the menu.");
                    System.out.println("Do you want to quit program (Y/N) ?");
                    String quit = input.nextLine().toUpperCase();
                    while (!quit.equals("Y") && !quit.equals("N")) {
                        System.out.println("Please enter either 'Y' or 'N' as your answer");
                        quit = input.nextLine().toUpperCase();
                    }
                    if(quit.equals("Y")) {
                        System.out.println("Thank you for using Premier League Manager program. Now you are exiting from the program.");
                        break loop;
                    }
                    break;
                default:
                    System.out.println("Sorry! You have entered an invalid input. Please try again");
                    break;
            }
        }
        System.exit(0);
    }
}
