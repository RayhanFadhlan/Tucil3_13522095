package gui;
import algo.AStar;
import algo.GreedyBFS;
import algo.UCS;
import util.Dictionary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class WordLadderGUI extends JFrame implements ActionListener {

    private JTextField startWordField;
    private JTextField endWordField;
    private JTextArea pathArea;
    private JButton searchButton;
    private JButton resetButton;
    private JLabel statusLabel;
    private JComboBox<String> algorithmComboBox;
    private Dictionary dict;
    private UCS ucs;
    private GreedyBFS gbfs;
    private AStar astar;
    public WordLadderGUI() throws IOException {
        super("Word Ladder");
        ucs = new UCS();
        gbfs = new GreedyBFS();
        astar = new AStar();
        dict = new Dictionary();
        // Set the look and feel to Nimbus
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        pathArea = new JTextArea(20,40);
        pathArea.setEditable(false);


        // Create UI components
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        JLabel startWordLabel = new JLabel("Start Word:");
        startWordField = new JTextField(10);
        JLabel endWordLabel = new JLabel("End Word:");
        endWordField = new JTextField(10);
        inputPanel.add(startWordLabel);
        inputPanel.add(startWordField);
        inputPanel.add(endWordLabel);
        inputPanel.add(endWordField);

        JPanel algorithmPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel algorithmLabel = new JLabel("Select Algorithm:");
        String[] algorithms = {"UCS", "A*", "Greedy BFS"};
        algorithmComboBox = new JComboBox<>(algorithms);
        algorithmPanel.add(algorithmLabel);
        algorithmPanel.add(algorithmComboBox);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        buttonPanel.add(searchButton);
        buttonPanel.add(resetButton);



        JScrollPane scrollPane = new JScrollPane(pathArea);


        statusLabel = new JLabel(" ");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Layout components
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(algorithmPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);
        mainPanel.add(buttonPanel, BorderLayout.EAST);
        mainPanel.add(statusLabel, BorderLayout.WEST);

        // Add main panel to frame and display
        getContentPane().add(mainPanel);
        pack();
        setLocationRelativeTo(null); // Center the window on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
         
            String startWord = startWordField.getText().toLowerCase();
            String endWord = endWordField.getText().toLowerCase();
           

            if (startWord.length() != endWord.length()) {
           
                pathArea.setText("Start word and end word must have the same length.");
                statusLabel.setText(""); 
                return; 
            }
            if (!dict.contains(startWord)) {
                
                pathArea.setText("Start word not found in the dictionary.");
                statusLabel.setText("");
                return; 
            }

            if (!dict.contains(endWord)) {
               
                pathArea.setText("End word not found in the dictionary.");
                statusLabel.setText("");
                return; 
            }
            String selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();
            ArrayList<String> path;
            long startTime = 0;
            long endTime = 0;
            int totalTraversed = 0;
            switch (selectedAlgorithm) {
                case "UCS":
                    startTime = System.currentTimeMillis();
                    path = ucs.searchUCS(startWord, endWord,dict);
                    totalTraversed = ucs.getTraversed();
                    endTime = System.currentTimeMillis();
                    break;
                case "A*":
                    startTime = System.currentTimeMillis();
                    path = astar.searchAstar(startWord, endWord,dict);
                    totalTraversed = ucs.getTraversed();
                    endTime = System.currentTimeMillis();
                    break;
                case "Greedy BFS":
                    startTime = System.currentTimeMillis();
                    path = gbfs.searchGreedyBFS(startWord, endWord,dict);
                    totalTraversed = ucs.getTraversed();
                    endTime = System.currentTimeMillis();
                    break;
                default:
                    path = new ArrayList<>();
                    break;
            }


            
            long elapsedTime = endTime - startTime;

            
            updatePathArea(path, elapsedTime, totalTraversed);

            statusLabel.setText(""); 
        } else if (e.getSource() == resetButton) {
            // Clear all fields
            startWordField.setText("");
            endWordField.setText("");
            pathArea.setText("");
            statusLabel.setText("");
        }
    }

    private void updatePathArea(ArrayList<String> path, long elapsedTime, int totalTraversed) {
        if (path.isEmpty()) {
            pathArea.setText("No path found");
        } else {
            StringBuilder pathText = new StringBuilder();
            int count = 1;
            for (String word : path) {
                pathText.append(count).append(". ").append(word).append("\n");
                count++;
            }
            pathText.append("\nElapsed Time: ").append(elapsedTime).append(" ms\n");
            pathText.append("Total Nodes Traversed: ").append(totalTraversed);
            pathArea.setText(pathText.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new WordLadderGUI();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
