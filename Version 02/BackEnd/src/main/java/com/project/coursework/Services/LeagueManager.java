package com.project.coursework.Services;

import java.io.IOException;

public interface LeagueManager {
    //public abstract methods in interface
    void addFootballClub();
    void relegateFootballClub();
    void displayStatistics();
    void displayLeagueTableInCLI();
    void addPlayedMatch();
    void saveDataToFile() throws IOException;
    void loadDataFromFile() throws IOException, ClassNotFoundException;

}
