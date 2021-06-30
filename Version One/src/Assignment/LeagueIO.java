package Assignment;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LeagueIO {
    public boolean save(List<FootballClub> list) {
        PrintStream ps = null;
        try {
            ps = new PrintStream(new FileOutputStream("dic.txt"));
            for (int i = 0, n = list.size(); i < n; i++) {
                FootballClub p = list.get(i);
                ps.println(p.getClubName() + "," + p.getLocation() + "," + p.getGoalsScored() + "," + p.getGoalsReceived()
                        + "," + p.getNumMatches() + "," + p.getWins() + "," + p.getDefeats() + "," + p.getDraws() + "," + p.getNumPoints() + "," + p.getGoalsConceded());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PremierLeagueManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ps.close();
        }
        return false;
    }

    public List<FootballClub> load() {
        List<FootballClub> list = new ArrayList<>();
        File file = new File("dic.txt");
        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] attributes = line.split(",");
                    int GS = Integer.parseInt(attributes[2]);
                    int GR = Integer.parseInt(attributes[3]);
                    int NumMatch = Integer.parseInt(attributes[4]);
                    int wins = Integer.parseInt(attributes[5]);
                    int loss = Integer.parseInt(attributes[6]);
                    int draws = Integer.parseInt(attributes[7]);
                    int NP = Integer.parseInt(attributes[8]);
                    int GC = Integer.parseInt(attributes[9]);


                    FootballClub p = new FootballClub(attributes[0], attributes[1], GS, GR, NumMatch, wins, loss, draws, NP, GC);
                    list.add(p);
                }
                reader.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(LeagueIO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(LeagueIO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }


    //-----------------------------------------MATCH SAVING-----------------------------------------------
    public boolean saveMatch(List<FootballGame> list2) {
        PrintStream ps = null;
        try {
            ps = new PrintStream(new FileOutputStream("det.txt"));
            for (int i = 0, n = list2.size(); i < n; i++) {
                FootballGame p = list2.get(i);
                ps.println(p.getTeam1() + "," + p.getTeam2() + "," + p.getMatchDate().toString() +","+ p.getTeamOneScore() + "," + p.getTeamTwoScore()
                );
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PremierLeagueManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ps.close();
        }
        return false;
    }



    public List<FootballGame> Matchload() {

        List<FootballGame> list2 = new ArrayList<>();
        File file = new File("det.txt");
        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] attributes = line.split(",");
                    DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    LocalDate dt=LocalDate.parse(attributes[2],formatter);




                    int score1 = Integer.parseInt(attributes[3]);
                    int score2 = Integer.parseInt(attributes[4]);


                    FootballGame p = new FootballGame(attributes[0], attributes[1], dt, score1, score2);
                    list2.add(p);
                }
                reader.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(LeagueIO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(LeagueIO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list2;
    }


}



