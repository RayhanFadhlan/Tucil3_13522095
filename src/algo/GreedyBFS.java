package algo;

import util.Dictionary;

import java.util.ArrayList;

public class GreedyBFS extends Search{

    public GreedyBFS(){
        super();
    }
    public ArrayList<String> searchGreedyBFS(String start, String end, Dictionary dict){
        return search(start,end,dict);
    }

    @Override
    public int calculateCost(Node node1, String endString) {
        int ret = 0;
        String nodeString = node1.getWord();
        for(int i = 0;i<nodeString.length();i++){
            if(nodeString.charAt(i)!=endString.charAt(i)){
                ret++;
            }
        }
        return ret;
    }

}
