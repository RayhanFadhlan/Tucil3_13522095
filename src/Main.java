import algo.AStar;
import algo.GreedyBFS;
import algo.UCS;
import gui.WordLadderGUI;
import util.Dictionary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("===============================");
        System.out.println("\nWelcome to Word Ladder Solver\n");
        System.out.println("==============================");
        System.out.println("\n ");
        System.out.println("Mau main via CLI atau GUI bang : ");
        System.out.println("1. GUI");
        System.out.println("2. CLI");
        System.out.print("input : ");
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        // scanner.close();
        if(inputString.equals("1")) {
            runGUI();
        }
        else{
            runCLI();
        }
        scanner.close();
    }

    public static void runGUI() throws IOException {
        new WordLadderGUI();
    }

    public static void runCLI() throws IOException {
        UCS ucs = new UCS();
        GreedyBFS gbfs = new GreedyBFS();
        AStar aStar = new AStar();
        Dictionary dict = new Dictionary();
        while(true){
            Scanner scanner = new Scanner(System.in);
            ArrayList<String> ret;
            
            System.out.println("\nEnter the start word: ");
            String start = scanner.nextLine();
            System.out.println("Enter the end word: ");
            String end = scanner.nextLine();
    
            start =start.toLowerCase();
            end = end.toLowerCase();
            if(start.length()!=end.length()){
                System.out.println("\nstart and end lengths do not match");
                continue;
            }
            if(!dict.contains(start)){
                System.out.println("\n" + start + " not found in dictionary");
                continue;
            }
            if(!dict.contains(end)){
                System.out.println("\n" + end + " not found in dictionary");
                continue;
            }
            System.out.println("\n\nChoose the algorithm: ");
            System.out.println("1. UCS");
            System.out.println("2. Greedy BFS");
            System.out.println("3. A*\n");
            System.out.print("input : ");
            String algoChosen = scanner.nextLine();

            // scanner.close();
            if(algoChosen.equals("1")){
                long startTimeUCS = System.currentTimeMillis();
                ret = ucs.searchUCS(start,end, dict);
                int travUCS = ucs.getTraversed();
                long endTimeUCS = System.currentTimeMillis();
                if(ret.isEmpty()){
                    System.out.println("No solution found");
                }
                else{
                    System.out.println("Found Solution using UCS From " + start + " to " + end + " : ");
                    printPath(ret);
                    System.out.println(endTimeUCS - startTimeUCS + " ms");
                    System.out.println("Total Nodes Traversed : " + travUCS);
                }
            }
            else if(algoChosen.equals("2")){
                long startTimeGBFS = System.currentTimeMillis();
                ret = gbfs.searchGreedyBFS(start,end,dict);
                int travGBFS = gbfs.getTraversed();
                long endTimeGBFS = System.currentTimeMillis();
                if(ret.isEmpty()){
                    System.out.println("No solution found");
                }
                else{
                    System.out.println("Found Solution using Greedy BFS From " + start + " to " + end + " : ");
                    printPath(ret);
                    System.out.println(endTimeGBFS - startTimeGBFS + " ms");
                    System.out.println("Total Nodes Traversed : " + travGBFS);
                }
            }
            else if(algoChosen.equals("3")){
                long startTimeAStar = System.currentTimeMillis();
                ret = aStar.searchAstar(start,end,dict);
                int travAStar = aStar.getTraversed();
                long endTimeAStar = System.currentTimeMillis();
                if(ret.isEmpty()){
                    System.out.println("No solution found");
                }
                else{
                    System.out.println("Found Solution using A* From " + start + " to " + end + " : ");
                    printPath(ret);
                    System.out.println(endTimeAStar - startTimeAStar + " ms");
                    System.out.println("Total Nodes Traversed : " + travAStar);
                }
            }
            else{
                System.out.println("Invalid input");
            }
            System.out.println("Do you want to continue? (y/n)");
            String cont = scanner.nextLine();
            if(cont.equals("n")){
                scanner.close();
                break;
            }
            else{
                continue;
            }
        }

    
    }

    public static void printPath(ArrayList<String> path){
        int count = 1;
        for(String s : path){
            System.out.println(count + ". " + s);
            count++;
        }
    }
    
    // public static void runCLI() throws IOException {
    //     UCS ucs = new UCS();
    //     GreedyBFS gbfs = new GreedyBFS();
    //     AStar aStar = new AStar();
    //     Dictionary dict = new Dictionary();
    //     ArrayList<String> ret;
    //     ArrayList<String> ret2;
    //     ArrayList<String> ret3;

    //     String start = "zwitterionic";
    //     String end = "zwinglianism";

    //     if(start.length()!=end.length()){
    //         System.out.println("start and end lengths do not match");
    //     }
    //     if(!dict.contains(start)){
    //         System.out.println(start + " not found in dictionary");
    //     }
    //     if(!dict.contains(end)){
    //         System.out.println(end + " not found in dictionary");
    //     }

    //     long startTimeUCS = System.currentTimeMillis();
    //     ret = ucs.searchUCS(start,end, dict);
    //     int travUCS = ucs.getTraversed();
    //     long endTimeUCS = System.currentTimeMillis();


    //     long startTimeGBFS = System.currentTimeMillis();
    //     ret2 = gbfs.searchGreedyBFS(start,end,dict);
    //     int travGBFS = gbfs.getTraversed();
    //     long endTimeGBFS = System.currentTimeMillis();

    //     long startTimeAStar = System.currentTimeMillis();
    //     ret3 = aStar.searchAstar(start,end,dict);
    //     int travAStar = aStar.getTraversed();
    //     long endTimeAStar = System.currentTimeMillis();

    //     if(ret.isEmpty()){
    //         System.out.println("No solution found");
    //     }
    //     else{
    //         System.out.println("UCS");
    //         System.out.println(ret);
    //         System.out.println(ret.size());
    //         System.out.println(endTimeUCS - startTimeUCS + " ms");
    //         System.out.println("Total Traversed UCS : " + travUCS);

    //         System.out.println("GBFS");
    //         System.out.println(ret2);
    //         System.out.println(ret2.size());
    //         System.out.println(endTimeGBFS - startTimeGBFS + " ms");;
    //         System.out.println("Total Traversed GBFS : " + travGBFS);

    //         System.out.println("AStar");
    //         System.out.println(ret3);
    //         System.out.println(ret3.size());
    //         System.out.println(endTimeAStar - startTimeAStar + " ms");
    //         System.out.printf("Total Traversed AStar : " + travAStar);

    //     }
    // }
}