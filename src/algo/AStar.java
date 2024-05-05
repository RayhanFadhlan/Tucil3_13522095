package algo;

import util.Dictionary;

import java.util.ArrayList;

public class AStar extends Search {
    public AStar() {
        super();
    }

    public ArrayList<String> searchAstar(String start, String end, Dictionary dict){
        return search(start,end,dict);
    }

    @Override
    public int calculateCost(Node node1, String endString){
        int ret = 0;
        String nodeString = node1.getWord();
        for(int i = 0;i<nodeString.length();i++){
            if(nodeString.charAt(i)!=endString.charAt(i)){
                ret++;
            }
        }

        // menjadikan heuristic admissible, dengan cara mengunderestimate h(n), dalam hal ini h(n) adalah ret yaitu estimasi jarak node sekaran ke node tujuan
        // dengan cara ini, algoritma A* akan selalu menghasilkan solusi optimal
        // karena nilai h(n) tidak akan pernah melebihi nilai sebenarnya, karena di setiap step, nilai g(n) di increment dengan 5, bukan 1
        return (node1.getCost() + 5) + ret;
    }

}
