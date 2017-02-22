package board;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ali on 2/19/17.
 */
public class Board {
    private int size;
    private Point food;
    private ArrayList<Snake> snakes;
    private ArrayList<Point> obstacles;
    private ArrayList<Point> removes;
    Random rand = new Random();

    public Board() {
        snakes = new ArrayList<Snake>();
        obstacles = new ArrayList<Point>();
        removes = new ArrayList<Point>();
    }

    public String getStatics() {
        String output;
        output = "{\n\"size\":" + size + ",\n\"obstacles\": [";
        for (Point o: obstacles)
            output += o.toString() + ", ";

        output = output.substring(0, output.length()-2);
        output += "]\n}";
        return output;
    }

    public String getMovings() {
        String output;
        output = "{\n\"snakes\": [";
        for (Snake s :
                snakes) {
            output += s.toString() + ", ";
        }
        output = output.substring(0, output.length()-2);
        output += "],\n\"food\":" + food.toString();
        output += ",\n\"remove\": [";
        if (this.removes.size() > 0) {
            for (Point p :
                    this.removes) {
                output += p.toString() + ", ";
            }
            output = output.substring(0, output.length()-2);
        }
        output += "]";
        output += "\n}";
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

    public void randomSnakeGenerator() {
        for(int i = 0; i < snakes.size(); i++) {
            snakes.get(i).randomGenerate();
        }
    }


    public void addSnake(Snake s) {
        snakes.add(s);
    }

    public void addRemoving(Point p) {
        removes.add(p);
    }

    public void addObstacles(Point p) {
        obstacles.add(p);
    }

    public Board rotate(int num) {
        Board temp = new Board();
        for (Snake s: snakes) {
            temp.addSnake(s.rotate(num, size));
        }

        temp.setFood(food.rotate(num, size));

        for (Point p: removes) {
            temp.addRemoving(p.rotate(num, size));
        }

        return temp;
    }

    public Board rotateStatics(int num) {
        Board temp = new Board();
        temp.setSize(size);
        for (Point p: obstacles) {
            temp.addObstacles(p.rotate(num, size));
        }

        return temp;
    }

    public void generateNewFood() {
        int  x, y;
        boolean success;

        while (true){
            success = true;
            x = rand.nextInt(10);
            y = rand.nextInt(10);
            Point randFood = new Point(x, y);

            for (Snake s:snakes) {
                if(s.contains(randFood)){
                    success = false;
                    break;
                }
            }

            for (Point p:obstacles) {
                if(p.equals(randFood))
                    success = false;
                    break;
            }
            if(!success)
                continue;

            if (food.equals(randFood))
                continue;

            food = randFood;
            return;
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Point getFood() {
        return food;
    }

    public void setFood(Point food) {
        this.food = food;
    }

    public ArrayList<Snake> getSnakes() {
        return snakes;
    }

    public void setSnakes(ArrayList<Snake> snakes) {
        this.snakes = snakes;
    }

    public ArrayList<Point> getObstacles() {
        return obstacles;
    }

    public void setObstacles(ArrayList<Point> obstacles) {
        this.obstacles = obstacles;
    }

    public ArrayList<Point> getRemoves() {
        return removes;
    }

    public void setRemoves(ArrayList<Point> removes) {
        this.removes = removes;
    }
}