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
//            if(nodeString.charAt(i)=='a' || nodeString.charAt(i)=='e' || nodeString.charAt(i)=='i' || nodeString.charAt(i)=='o' || nodeString.charAt(i)=='u'){
//                ret++;
//            }
        }

//            return (node1.getCost() +1 - ret) ;

//        return ret;
        return (node1.getCost() + 5) + ret;
//        return (node1.getCost() + 1) + ret/(nodeString.length()-1);
    }

}
