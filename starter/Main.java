package starter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Scanner;

public class Main extends JPanel {

    final int BF_WIDTH = 576;
    final int BF_HEIGHT = 576;

    int y = 6;
    int x = 7;


    int score = 0;
    int countSteps = 50;
    String gameStatus = "Play Game :)";

// B - Brick, GG - Gold, P - Player, G - Ground, E - Exit
    String[][] data = {
            {"B", "G", "B", "B", "B", "B", "B", "G", "B", "B", "G"},
            {"GG", "G", "G", "GG", "GG", "GG", "E", "B", "B", "B", "G"},
            {"G", "G", "B", "B", "G", "B", "B", "G", "B", "B", "G"},
            {"G", "G", "B", "B", "M", "B", "B", "M", "B", "B", "G"},
            {"GG", "G", "G", "B", "B", "B", "B", "G", "B", "B", "G"},
            {"G", "M", "B", "B", "B", "B", "B", "G", "B", "B", "G"},
            {"G", "B", "G", "G", "G", "G", "G", "P", "B", "B", "G"},
            {"G", "G", "G", "B", "B", "GG", "G", "GG", "B", "B", "G"},
            {"M", "B", "B", "B", "B", "B", "B", "B", "B", "B", "G"},
            {"M", "B", "G", "G", "G", "G", "G", "G", "B", "B", "G"},
            {"G", "G", "G", "B", "B", "G", "G", "G", "B", "B", "G"},
            {"G", "M", "B", "B", "B", "B", "B", "G", "B", "B", "G"}
    };
    private int direction;


    void moveUp( ) throws Exception {
     //   if (data[y--][x].equals("G") &&  y-1 >= 0);
        data[y][x] = "G";
        y--;
        data[y][x] = "P";

        drawTable();
        long l = 1000;
        Thread.sleep(l);

    }
    void moveDown( ) throws Exception{
     //   if (data[y++][x].equals("G") && y+1 >= 0);
        data[y][x] = "G";
        y++;
        data[y][x] = "P";

        drawTable();
        long l = 1000;
        Thread.sleep(l);
    }
    void moveLeft( ) throws Exception{
     //   if (data[y][x--].equals("G") && x-1 >= 0 );
        data[y][x] = "G";
        x--;
        data[y][x] = "P";

        drawTable();
        long l = 1000;
        Thread.sleep(l);
    }
    void moveRight( ) throws Exception{
      //  if (data[y][x++].equals("G") && x+1 >= 0);
        data[y][x] = "G";
        x++;
        data[y][x] = "P";

        drawTable();
        long l = 1000;
        Thread.sleep(l);
    }


//////////////////////////////////////////////////////////////
    // direction VALUE (1 - UP, 2 - DOWN, 3 - LEFT, 4 - RIGHT
    void move(int direction) throws Exception {
       if (direction == 1 && data[y--][x].equals("G") && data[y--][x].equals("GG") && y >= 0 && y <= 12){
            moveUp();
        } else if (direction == 2 && data[y++][x].equals("G") && data[y++][x].equals("GG") && y >= 0 && y <= 12) {
            moveDown();
        } else if(direction == 3 && data[y][x--].equals("G") && data[y][x--].equals("GG") && x >= 0 && x <= 11){
            moveLeft();
        } else if (direction == 4 && data[y][x++].equals("G") && data[y][x++].equals("GG") && x >= 0 && x <= 12){
            moveRight();
        }
    }
////////////////////////////////////////////////////////////////////////////////

    void runTheGame() throws Exception {

        while (y > 0 && data[y-1][x].equals("G")){
            moveUp();
        }

        while (y < 12 && data[y+1][x].equals("G")){
            moveDown();
        }


        while (x > 0 && data[y][x-1].equals("G")){
            moveLeft();
        }


        while (x < 11 && data[y][x+1].equals("G")){
            moveRight();
        }



        gameStatus = "Game Over";
        drawTable();
        Thread.sleep(1000);
    }


    // Не смотрите код, что написан ниже. Со временем Вы будете понимать этот код.


    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.runTheGame();
    }


    JTable table;
    String[] column = new String[11];
    JLabel labelScore = new JLabel();
    JLabel labelSteps = new JLabel();
    JLabel labelGameStatus = new JLabel();


    public Main() {
        JFrame frame = new JFrame("Goldman");

        table=new JTable();

        table.setTableHeader(null);
        table.setEnabled(false);
        table.setSize(new Dimension(300, 300));
        table.setRowHeight(26);
        table.setRowSelectionAllowed(false);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.setUpdateSelectionOnSort(false);
        table.setVerifyInputWhenFocusTarget(false);


        for (int i = 0; i < column.length; i++) {
            column[i] = "";
        }

        drawTable();

        add(table);
        add(labelScore);
        add(labelSteps);
        add(labelGameStatus);
        frame.setMinimumSize(new Dimension(BF_WIDTH,BF_HEIGHT + 22));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.setVisible(true);
    }

    private void score() {
        labelScore.setText("Score: " + score);
    }

    private void countSteps() {
        labelSteps.setText("Count steps: " + countSteps);
    }

    private void gameStatus() {
        labelGameStatus.setText(gameStatus);
    }

    private void drawTable() {


        table.setModel(new DefaultTableModel(data, column));

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new ImageRenderer_DONT_TOUCH_THIS_FILE());
            TableColumn a = table.getColumnModel().getColumn(i);
            a.setPreferredWidth(26);
        }

        score();
        countSteps();
        gameStatus();

    }

}
