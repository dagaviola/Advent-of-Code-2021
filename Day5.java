//day5.java
import java.io.File;
import java.util.Scanner;


public class Day5{

    public static class Line{
        int x1;
        int y1;
        int x2;
        int y2;

        public Line(String line){
            String[] split = line.split(" -> ");
            String[] split1 = split[0].split(",");
            String[] split2 = split[1].split(",");

            x1 = Integer.valueOf(split1[0]);
            y1 = Integer.valueOf(split1[1]);
            x2 = Integer.valueOf(split2[0]);
            y2 = Integer.valueOf(split2[1]);

            //System.out.println(x1 + "," + y1 + "," + x2 + "," + y2);

        }
    }
    public static int[][] grid = new int[991][991];
    
    //Checks if the line is a horizontal or vertical line, if not don't process it.
    //public static boolean isValid(int x1, int y1, int x2, int y2){
    //    if(x1 == x2 || y1 == y2){
    //        return true;
    //    }else{
    //        return false;
    //    }
    //}
    public static void processLine(Line line){
        int x1 = line.x1;
        int y1 = line.y1;
        int x2 = line.x2;
        int y2 = line.y2;

        if(x1 == x2){
            if(y1 < y2){
                for(int i = y1; i <= y2; i++){
                    grid[x1][i]++;
                }
            }else{
                for(int i = y2; i <= y1; i++){
                    grid[x1][i]++;
                }
            }
            
        }else if(y1 == y2){
            if(x1 < x2){
                for(int i = x1; i <= x2; i++ ){
                    grid[i][y1]++;
                }
            }else{
                for(int i = x2; i <= x1; i++ ){
                    grid[i][y1]++;
                }
            }
        }else{
            if(x1 < x2 && y1 < y2){
                for(int i = x1, j = y1; i <= x2 && j <= y2; i++, j++){
                    grid[i][j]++;
                }
            }else if(x2 < x1 && y2 < y1){
                for(int i = x2, j = y2; i <= x1 && j <= y1; i++, j++){
                    grid[i][j]++;
                }
            }else if(x2 < x1 && y1 < y2){
                for(int i = x2, j = y1; i <= x1 && j <= y2; i++, j++){
                    grid[i][j]++;
                }                
            }else if(x1 < x2 && y2 < y1){
                for(int i = x1, j = y2; i <= x2 && j <= y1; i++, j++){
                    grid[i][j]++;
                }                
            }
        }
    }

    public static int processGrid(){
        int count = 0;
        for(int i = 0; i < 991; i++){
            for(int j = 0; j < 991; j++){
                if(grid[i][j] >= 2){
                    count++;
                }
            }
        }
        return count;
    }

    public static void printGrid(){
        for(int i = 0; i < 991; i++){
            for(int j = 0; j < 991; j++){
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {

        //initialize grid to all zeros, size based of max input for x or y of 990
        for(int i = 0; i < 991; i++){
            for(int j = 0; j < 991; j++){
                grid[i][j] = 0;
            }
        }



        //Process file line by line
        File input = new File("Day5Input.txt");
        Scanner sc = new Scanner(input);

        while(sc.hasNext()){
            Line curr = new Line(sc.nextLine());
            processLine(curr);
        }
        sc.close();
        //printGrid();
        int numOverlaps = processGrid();

        System.out.println("Number of Overlaps in the Grid: " + numOverlaps);
    }

}