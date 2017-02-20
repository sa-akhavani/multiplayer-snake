package common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ali on 2/19/17.
 */
public class Board {
    private int size;
    private Point food;
    private ArrayList<Snake> snakes;
    private ArrayList<Point> obstacles;

    public Board() {
        snakes = new ArrayList<Snake>();
        obstacles = new ArrayList<Point>();
    }

    public String getStatics() {
        String output;
        output = "board = {\n'size':" + size + ",\n'obstacles': [";
        for (Point o: obstacles)
            output += o.toString() + ", ";

        output = output.substring(0,output.length()-2);
        output += "]\n}";
        return output;
    }

    public String getMovings() {
        String output;
        output = "{\n'size:': " + size + ",\n'obstacles': [";
        for (Snake s: snakes)
            output += s.toString() + ',';

        output = output.substring(0,output.length()-2);
        output += "]\n}";
        return output;
    }

    public void parseMap(String fileLocation, Point food) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileLocation));

        int lineNo = 0;
        String line;
        while ((line = br.readLine()) != null) {
            String[] newLine = line.split("\\s+");
            for (int i = 0; i < newLine.length; i++) {
                if (newLine[i].equals("1")) {
                    Point p = new Point(lineNo, i);
                    obstacles.add(p);
                }
            }
            lineNo++;
        }

        this.size = lineNo;
        this.food = food;
    }
}