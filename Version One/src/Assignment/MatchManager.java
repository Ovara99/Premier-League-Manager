package Assignment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MatchManager {
    private List<FootballGame> listOfMatches;

    public MatchManager() {
        this.listOfMatches = new ArrayList<>();
    }

    public MatchManager(List<FootballGame> listOfMatches) {
        this.listOfMatches = listOfMatches;
    }

    public List<FootballGame> getListOfMatches() {
        return listOfMatches;
    }


    public int addMatch(FootballGame p){
        this.listOfMatches.add(p);
        return count();
    }

    public FootballGame getMatch(int index) {
        if (index < 0 || index >= count()) {
            return null;
        }
        return this.listOfMatches.get(index);
    }
    public void removeAll(FootballGame p){

    }

    public int count(){
        return this.listOfMatches.size();
    }
    public FootballGame deleteMatch(int index) {
        if (index < 0 || index >= count()) {
            return null;
        }
        return this.listOfMatches.remove(index);
    };

    }




