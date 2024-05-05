package algo;

import util.Dictionary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;

public abstract class Search {

    public static PriorityQueue<Node> pq;
    public static HashSet<String> visited;
    public static int traversed;

    public Search(){
        pq = new PriorityQueue<>();
        visited = new HashSet<>();
        traversed = 0;
    }

    public ArrayList<String> generatePath(Node endNode){
        ArrayList<String> path = new ArrayList<>();
        Node current = endNode;

        // Backtrack from the end node to the start node
        while (current != null) {
            path.add(current.getWord());
            current = current.getParent();
        }

        // Reverse the path since we started from the end node
        Collections.reverse(path);
        return path;
    }

    public int getTraversed(){
        return traversed;
    }

    public ArrayList<String> search(String start, String end, Dictionary dict){
        pq.clear();
        visited.clear();
        traversed = 0;

        Node first = new Node(start,null,0);

        pq.add(first);
        visited.add(start);
        while(!pq.isEmpty()){
            traversed++;
            Node cur = pq.remove();
            if(cur.getWord().equals(end)){
                return generatePath(cur);
            }

            

            for(int i = 0; i < cur.getWord().length(); i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    if (cur.getWord().charAt(i) == c) {
                        continue;
                    }
                    StringBuilder temp = new StringBuilder(cur.getWord());
                    temp.setCharAt(i, c);
                    String newWord = temp.toString();
                    if(visited.contains(newWord)){
                        continue;
                    }
                    if(dict.contains(newWord) ){
                        pq.add(new Node(newWord,cur, calculateCost(cur,end)));
                        visited.add(newWord);
                    }

                }
            }
        }
        return new ArrayList<>();
    }

    abstract public int calculateCost(Node node1, String endString);


}
