package algo;

import java.util.*;

import util.Dictionary;

public class UCS extends Search {

    public UCS() {
        super();
    }

    public ArrayList<String> searchUCS(String start, String end, Dictionary dict) {
        return search(start, end, dict);
    }
    // self explanatory, the cost of the node is incremented by 1
    @Override
    public int calculateCost(Node node1, String endString) {
        return node1.getCost() + 1;
    }

}