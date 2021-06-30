package Assignment;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Menu extends Application {     //To extend the javafx application
    private LeagueManager lm;
    private LeagueIO io;
    private MatchManager mm;
    private final Scanner scanner;
    private final ArrayList<FootballGame> matches;


    public Menu() {
        this.io = new LeagueIO();
        this.lm = new PremierLeagueManager(io.load()); //retrieves the data previously stored
        this.mm=new MatchManager(io.Matchload());//retrieves the match dates previously stored
        scanner = new Scanner(System.in);

        matches = new ArrayList<>();
    }
    public List<FootballGame> getMatchList() {
        return matches;
    }


    //-----------MAIN METHOD------------------------------

    public static void main(String[] args) {
        Menu pc = new Menu();
        pc.displayConsole();
    }


    //------------------------------------METHODS FOR CONSOLE-----------------------------------------------------

    private void addClub() {

        if (this.lm.count() == 20) {
            System.out.println("Can't add more clubs to league");
            return;
        } else {
            Scanner input = new Scanner(System.in);
            System.out.println("Insert club name: ");
            String clubName = input.nextLine().toLowerCase();
            for (int i = 0; i < this.lm.count(); i++) {
                FootballClub p = this.lm.getClub(i);
                if (p.getClubName().equals(clubName)) {
                    System.out.println("This club is already in the league");
                    return;
                }
            }
            System.out.println("Insert Club Location: ");
            String location = input.nextLine().toLowerCase();
            FootballClub newclub = new FootballClub(clubName, location);
            newclub.setClubName(clubName);
            newclub.setLocation(location);
            this.lm.addClub(newclub);
            this.io.save(lm.getClubList());

        }
    }


    private void deleteClub() {
        if (lm.getClubList().isEmpty()) {
            System.out.println("The League is empty");
            return;
        }

        Scanner input = new Scanner(System.in);
        System.out.println("Insert club name: ");
        String clubName = input.nextLine().toLowerCase();
        for (int i = 0; i < this.lm.count(); i++) {
            FootballClub p = this.lm.getClub(i);
            if (p.getClubName().equals(clubName)) {
                this.lm.deleteClub(p);
                System.out.println("This club was removed.");
                this.io.save(lm.getClubList());
                if(lm.getClubList().isEmpty()){
                    for (int n = 0; n < this.mm.count(); n++) {
                        FootballGame f= this.mm.deleteMatch(n);
                        this.io.saveMatch(mm.getListOfMatches());
                        return;
                    }
                }return;

            }
            else{System.out.println("No such club in league");}
            }


        }

    //-------------------------------------------------------CONSOLE----------------------------------------------------
    public void displayConsole() {

        label58:
        while (true) {
            try {
                System.out.println("------------Menu------------");
                System.out.println("Press 1 - Add a Football Club to the League ");
                System.out.println("Press 2 - Delete an existing club");
                System.out.println("Press 3 - Display Statistics for a specific team");
                System.out.println("Press 4 - Display the Premier League Table");
                System.out.println("Press 5 - Add a Played Match ");
                System.out.println("Press 6 - Open the GUI");
                System.out.println("Press 7 - Quit program");
                System.out.println("\nPlease enter the relevant number of the option you want to select : ");
                Scanner input = new Scanner(System.in);
                int option = input.nextInt();
                switch (option) {
                    case 1:
                        this.addClub();
                        break;
                    case 2:
                        this.deleteClub();
                        break;
                    case 3:
                        this.lm.showStatistics();
                        break;
                    case 4:
                        this.lm.showTable();
                        break;
                    case 5:
                        this.addPlayedGame();
                        this.io.save(lm.getClubList());
                        break;
                    case 6:
                        this.launch();

                    case 7:
                        System.out.println("You just closed the program.");
                        return;
                    default:
                        System.out.println("You have to enter the given number relevant to the option you want to select. Please try again");
                }
            } catch (Exception var20) {
                System.out.println("You have to enter the given number relevant to the option you want to select. Please try again");
            }
        }
    }


    //----------------------------------------- ADDITION OF A PLAYED MATCH-----------------------------------
    public void addPlayedGame() {
        if (lm.getClubList().isEmpty()) {
            System.out.println("The League is empty");
            return;
        }
        if (this.lm.count() == 1) {
            System.out.println("Only one club in the league. Please add another club in order to add a Played Game.");
            return;
        }

        LocalDate date;
        String line;
        while (true) {

            Scanner input = new Scanner(System.in);
            System.out.println("Enter date (format dd-MM-yyyy): ");
            line = input.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu");

            try {
                date = LocalDate.parse(line,formatter);
                break;
            } catch (Exception ex) {
                System.out.println("You have to enter date in format mm-dd-yyyy");

            }
        }
        FootballClub TeamA = null;

        String TeamOne;

        while (true) {
            System.out.println("Enter First Team: ");
            TeamOne = scanner.nextLine();


            for (int i = 0; i < this.lm.count(); i++) {
                FootballClub p = this.lm.getClub(i);
                if (p.getClubName().equals(TeamOne)) {
                    TeamA = p;
                    break;
                }
            }

            if (TeamA == null) {
                System.out.println("No such club in league");
                continue;

            }
            break;


        }
        FootballClub TeamB = null;
        String TeamTwo;
        while (true) {
            System.out.println("Enter Second Team: ");
            TeamTwo = scanner.nextLine();
            for (int i = 0; i < this.lm.count(); i++) {
                FootballClub p = this.lm.getClub(i);
                if (p.getClubName().equals(TeamTwo)) {
                    TeamB = p;
                    break;
                }
            }

            if (TeamB == null) {
                System.out.println("No such club in league");
                continue;

            }
            break;

        }

        int TeamAGoals;
        while (true) {
            try {
                System.out.println("Enter Goals Scored by Team One: ");
                TeamAGoals = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("You have to enter a number!");
            }
        }
        int TeamBGoals;

        while (true) {
            try {
                System.out.println("Enter Goals Scored by the Second team : ");
                TeamBGoals = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("You have to enter number of goals");
            }
        }

        FootballGame game = new FootballGame(TeamA,TeamB,TeamAGoals,TeamBGoals,date);
        FootballGame p=new FootballGame(TeamOne,TeamTwo,date,TeamAGoals,TeamBGoals);
        p.setTeam1(TeamOne);
        p.setTeam2(TeamTwo);
        p.setMatchDate(date);
        p.setTeamOneScore(TeamAGoals);
        p.setTeamTwoScore(TeamBGoals);
        this.mm.addMatch(p);
        this.io.saveMatch(mm.getListOfMatches());

        game.setMatchDate(date);
        game.setTeamOne(TeamA);
        game.setTeamTwo(TeamB);
        game.setTeamOneScore(TeamAGoals);
        game.setTeamTwoScore(TeamBGoals);
        matches.add(game);


        TeamA.setGoalsScored(TeamA.getGoalsScored() + TeamAGoals);
        TeamB.setGoalsScored(TeamB.getGoalsScored() + TeamBGoals);
        TeamA.setGoalsReceived(TeamA.getGoalsReceived() + TeamAGoals);
        TeamB.setGoalsReceived(TeamB.getGoalsReceived() + TeamBGoals);
        TeamA.setNumMatches(TeamA.getNumMatches() + 1);
        TeamB.setNumMatches(TeamB.getNumMatches() + 1);

        if (TeamAGoals > TeamBGoals) {
            TeamA.setWins(TeamA.getWins() + 1);
            TeamB.setDefeats(TeamB.getDefeats() + 1);
            TeamA.setNumPoints(TeamA.getNumPoints());
        } else if (TeamAGoals < TeamBGoals) {
            TeamB.setWins(TeamB.getWins() + 1);
            TeamA.setDefeats(TeamA.getDefeats() + 1);
            TeamB.setNumPoints(TeamB.getNumPoints());
        } else {
            TeamA.setDraws(TeamA.getDraws() + 1);
            TeamB.setDraws(TeamB.getDraws() + 1);
            TeamA.setNumPoints(TeamA.getNumPoints());
            TeamB.setNumPoints(TeamA.getNumPoints());
        }


    }

    //--------------------------------------------------GUI APPLICATION-------------------------------------------------------------
    @Override
    public void start(Stage primaryStage) {
        Stage scene1 = new Stage();


        //MainPage

        Button button1 = new Button("View Match Table");
        button1.setLayoutX(300);
        button1.setLayoutY(100);
        button1.setPrefSize(250, 20);
        button1.setStyle("-fx-background-color: #3baee5;");
        button1.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 16));


        Button button3 = new Button("Generate Match");
        button3.setLayoutX(300);
        button3.setLayoutY(175);
        button3.setPrefSize(250, 20);
        button3.setStyle("-fx-background-color: #3baee5;");
        button3.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 16));

        Button button4 = new Button("View Table with Dates");
        button4.setLayoutX(300);
        button4.setLayoutY(250);
        button4.setPrefSize(250, 20);
        button4.setStyle("-fx-background-color: #3baee5;");
        button4.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 16));

        scene1.setOnCloseRequest(event -> {event.consume();
        scene1.hide();
        displayConsole();});

        Pane root = new Pane();
        root.setStyle("-fx-background-color: #73947d");
        root.getChildren().add(button1);
        root.getChildren().add(button3);
        root.getChildren().add(button4);
        Scene scene = new Scene(root, 800, 400);
        scene1.setScene(scene);
        scene1.show();
        scene1.setTitle("Football Premier League");

        button1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                getTableView();

            }
        });

        button3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                RandomMatch();

            }
        });

        button4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                GetMatchDateTable();

            }
        });



    }
    //------------- TABLE WITHOUT DATE--------------------------------------------------------------------------------

    public void getTableView() {
        Stage scene2 = new Stage();
        TableView table = new TableView();


        TableColumn clubCol = new TableColumn("Name of Club");
        clubCol.setCellValueFactory(new PropertyValueFactory<FootballClub, String>("ClubName"));
        TableColumn GSCol = new TableColumn("goalsScored");
        GSCol.setCellValueFactory(new PropertyValueFactory<FootballClub, Integer>("goalsScored"));
        TableColumn GRCol = new TableColumn("GoalsReceived");
        GRCol.setCellValueFactory(new PropertyValueFactory<FootballClub, Integer>("GoalsReceived"));
        TableColumn numMatchCol = new TableColumn("numMatches");
        numMatchCol.setCellValueFactory(new PropertyValueFactory<FootballClub, Integer>("numMatches"));
        TableColumn winsCol = new TableColumn("wins");
        winsCol.setCellValueFactory(new PropertyValueFactory<FootballClub, Integer>("wins"));
        TableColumn defeatsCol = new TableColumn("defeats");
        defeatsCol.setCellValueFactory(new PropertyValueFactory<FootballClub, Integer>("defeats"));
        TableColumn drawsCol = new TableColumn("draws");
        drawsCol.setCellValueFactory(new PropertyValueFactory<FootballClub, Integer>("draws"));
        TableColumn pointsCol = new TableColumn("NumPoints");
        pointsCol.setCellValueFactory(new PropertyValueFactory<FootballClub, Integer>("NumPoints"));
        pointsCol.setSortType(TableColumn.SortType.DESCENDING);



        table.getColumns().setAll(clubCol, GRCol, GSCol, winsCol, defeatsCol, drawsCol, pointsCol, numMatchCol);
        table.setPrefWidth(600);
        table.setPrefHeight(450);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setItems(getInitialTableData());
        table.getSortOrder().setAll(pointsCol);

        Button btnWin = new Button("Sort according to Wins");
        btnWin.setLayoutX(100);
        btnWin.setLayoutY(650);
        btnWin.setPrefSize(250, 20);
        btnWin.setStyle("-fx-background-color: #3baee5;");
        btnWin.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 16));

        Button btnGS = new Button("Sort according to Goals Scored");
        btnGS.setLayoutX(400);
        btnGS.setLayoutY(650);
        btnGS.setPrefSize(300, 20);
        btnGS.setStyle("-fx-background-color: #3baee5;");
        btnGS.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 16));


        btnGS.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                GSCol.setSortType(TableColumn.SortType.DESCENDING);
                table.getSortOrder().setAll(GSCol);

            }
        });
        btnWin.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                winsCol.setSortType(TableColumn.SortType.DESCENDING);
                table.getSortOrder().setAll(winsCol);

            }
        });


        // Vbox
        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(25, 25, 25, 25));
        ;
        vbox.getChildren().addAll(table, btnGS, btnWin);

        // Scene
        Scene scene = new Scene(vbox, 850, 600); // w x h
        scene2.setScene(scene);
        scene2.show();
    }

    private ObservableList getInitialTableData() {


        ObservableList data = FXCollections.observableList(this.lm.getClubList());
        return data;

    }

    //---------------------------------------GENERATE Random Match----------------------------------------

    public void RandomMatch() {
        Stage scene5 = new Stage();
        GridPane gridmort = new GridPane();
        gridmort.setPadding(new Insets(10, 10, 10, 10));
        gridmort.setVgap(8);
        gridmort.setHgap(10);
        Label title1 = new Label("--------Random Match----- ");
        title1.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 16));

        Label teamA = new Label("    Team One:   ");
        Label teamB = new Label("    Team Two:   ");
        teamA.setFont(Font.font("Calibri Body", FontWeight.MEDIUM, FontPosture.REGULAR, 14));
        teamB.setFont(Font.font("Calibri Body", FontWeight.MEDIUM, FontPosture.REGULAR, 14));

        gridmort.setConstraints(title1, 2, 0);
        gridmort.setConstraints(teamA, 0, 1);
        gridmort.setConstraints(teamB, 0, 2);
        gridmort.getChildren().addAll(title1, teamA, teamB);
        scene5 = new Stage();


        while (true) {
            if (lm.getClubList().isEmpty()) {
                Alert alert1= new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("ERROR");
                alert1.setHeaderText("The League is empty!");
                alert1.showAndWait();
                return;
            }
            if (this.lm.count() == 1) {
                Alert alert2= new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("ERROR");
                alert2.setHeaderText("Only one club in the league. Please add another club in order to Generate a Match!");
                alert2.showAndWait();
                return;
            }
            Random rand = new Random();
            int index1 = rand.nextInt(lm.count());
            int index2 = rand.nextInt(lm.count());

            if (index1 == index2) {
                continue;
            } else {
                FootballClub TeamA = lm.getClub(index1);
                FootballClub TeamB = lm.getClub(index2);
                String team1=TeamA.getClubName();
                String team2=TeamB.getClubName();


                int day=(1+rand.nextInt(30)); //to not get a zero


                int month=(1+rand.nextInt(12));

                int year=2000+rand.nextInt(20);//takes years from 2000 to 2020
                String line=String.format("%02d",day)+"-"+String.format("%02d",month)+"-"+year;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu");
                LocalDate date =LocalDate.parse(line,formatter);


                int TeamAGoals = rand.nextInt(21);
                int TeamBGoals = rand.nextInt(21);
                Label lb1 = new Label(TeamA.getClubName());
                Label lb2 = new Label(TeamB.getClubName());
                Label lb3 = new Label(" Team One Goals :   " + TeamAGoals);
                Label lb4 = new Label(" Team Two Goals :   " + TeamBGoals);
                Label lb5 = new Label(" Date of Match :   " + line);
                gridmort.setConstraints(lb1, 4, 1);
                gridmort.setConstraints(lb2, 4, 2);
                gridmort.setConstraints(lb3, 4, 3);
                gridmort.setConstraints(lb4, 4, 4);
                gridmort.setConstraints(lb5, 4, 5);


                gridmort.getChildren().addAll(lb1, lb2, lb3, lb4,lb5);

                FootballGame p=new FootballGame(team1,team2,date,TeamAGoals,TeamBGoals);
                p.setTeam1(team1);
                p.setTeam2(team2);
                p.setMatchDate(date);
                p.setTeamOneScore(TeamAGoals);
                p.setTeamTwoScore(TeamBGoals);


                TeamA.setGoalsScored(TeamA.getGoalsScored() + TeamAGoals);
                TeamB.setGoalsScored(TeamB.getGoalsScored() + TeamBGoals);
                TeamA.setGoalsReceived(TeamA.getGoalsReceived() + TeamAGoals);
                TeamB.setGoalsReceived(TeamB.getGoalsReceived() + TeamBGoals);
                TeamA.setNumMatches(TeamA.getNumMatches() + 1);
                TeamB.setNumMatches(TeamB.getNumMatches() + 1);

                if (TeamAGoals > TeamBGoals) {
                    TeamA.setWins(TeamA.getWins() + 1);
                    TeamB.setDefeats(TeamB.getDefeats() + 1);
                    TeamA.setNumPoints(TeamA.getNumPoints());
                } else if (TeamAGoals < TeamBGoals) {
                    TeamB.setWins(TeamB.getWins() + 1);
                    TeamA.setDefeats(TeamA.getDefeats() + 1);
                    TeamB.setNumPoints(TeamB.getNumPoints());
                } else {
                    TeamA.setDraws(TeamA.getDraws() + 1);
                    TeamB.setDraws(TeamB.getDraws() + 1);
                    TeamA.setNumPoints(TeamA.getNumPoints());
                    TeamB.setNumPoints(TeamA.getNumPoints());
                }
                this.io.save(lm.getClubList());
                this.mm.addMatch(p);
                this.io.saveMatch(mm.getListOfMatches());
                break;

            }

        }
        gridmort.setStyle("-fx-background-color: #e1e7c3;");
        scene5.setScene(new Scene(gridmort, 900, 400));
        scene5.show();
        scene5.setTitle("Random Match Generator");


    }
    //----------------------------------------MATCH DATE TABLE--------------------------------------------------------------------

    public void GetMatchDateTable() {
        Stage scene2 = new Stage();
        TableView table = new TableView();


        TableColumn team1Col = new TableColumn("Name of Team One");
        team1Col.setCellValueFactory(new PropertyValueFactory<FootballGame, String>("team1"));
        TableColumn team2Col = new TableColumn("Name of Team Two");
        team2Col.setCellValueFactory(new PropertyValueFactory<FootballGame,String>("team2"));
        TableColumn score1Col = new TableColumn("Number of Goals Team One Scored");
        score1Col.setCellValueFactory(new PropertyValueFactory<FootballGame, Integer>("teamOneScore"));
        TableColumn score2Col = new TableColumn("Number of Goals Team Two Scored");
        score2Col.setCellValueFactory(new PropertyValueFactory<FootballGame, Integer>("teamTwoScore"));
        TableColumn dateCol = new TableColumn("Date of Match");
        dateCol.setCellValueFactory(new PropertyValueFactory<FootballGame, Date>("matchDate"));
        dateCol.setSortType(TableColumn.SortType.ASCENDING);


        table.getColumns().setAll(team1Col,team2Col,score1Col,score2Col,dateCol);
        table.setPrefWidth(600);
        table.setPrefHeight(450);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setItems(getDateTableData());
        table.getSortOrder().setAll(dateCol);

        TextField Sdate = new TextField();
        Sdate.setPromptText("Input date in uuuu-MM-dd format");
        Sdate.setMinWidth(200);


        Button btnSearch = new Button("Search Match");
        btnSearch.setPrefSize(200, 20);
        btnSearch.setStyle("-fx-background-color: #3baee5;");
        btnSearch.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 16));

        btnSearch.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (Sdate.getText().isEmpty()) {
                    Alert alert1= new Alert(Alert.AlertType.ERROR);
                    alert1.setTitle("ERROR");
                    alert1.setHeaderText("Text field is empty!");
                    alert1.showAndWait();
                    return;

                }
                for (int i = 0; i < mm.count(); i++) {  //search button feature is not complete yet. Will keep trying.
                    FootballGame p = mm.getMatch(i);
                    if (dateCol.toString() == Sdate.getText()) {
                        FilteredList filteredlist=new FilteredList(getDateTableData());
                        filteredlist.add(p);
                        ObservableList data = FXCollections.observableList(filteredlist);
                        table.setItems(data);

                    }else{
                        Alert alert1= new Alert(Alert.AlertType.ERROR);
                        alert1.setTitle("ERROR");
                        alert1.setHeaderText("Invalid date. Please enter in uuuu-MM-dd format");
                        alert1.showAndWait();
                        return;

                    }

                }



            }
        });

        HBox hbox= new HBox();
        hbox.setPadding(new Insets(10,10,10,10));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(Sdate,btnSearch);





        // Vbox
        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(25, 25, 25, 25));
        ;
        vbox.getChildren().addAll(table,hbox);

        // Scene
        Scene scene = new Scene(vbox, 850, 600); // w x h
        scene2.setScene(scene);
        scene2.show();

    }
    public void searchbuttonClicked(){

    }

    private ObservableList getDateTableData() {


        ObservableList data = FXCollections.observableList(this.mm.getListOfMatches());
        return data;

    }









}






















