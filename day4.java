import java.util.*;

public class MyClass {
    public static class BingoBoard{
        int[][] board = new int[5][5];
        
        public BingoBoard(int[] list){
            int index = 0;
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    board[i][j] = list[index];
                    index++;
                }
            }
        }
        
        public void printBoard(){
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
        }
        
        public boolean callNumber(int n){
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    if(board[i][j] == n){
                        board[i][j] = -1;
                        if(checkBoard()){
                            calculateWinner(n);
                            return true;
                        }
                    }
                }
            }
            return false;
            
        }

        public boolean checkBoard(){
            //printBoard();
            for(int i = 0; i < 5; i++){
                boolean isBingo = true;
                for(int j = 0; j < 5; j++){
                    if(board[i][j] != -1){
                        isBingo = false;
                    }
                }
                if(isBingo){
                    return true;
                }
            }
            for(int j = 0; j < 5; j++){
                Boolean isBingo = true;
                for(int i = 0; i < 5; i++){
                    if(board[i][j] != -1){
                        isBingo = false;
                    }
                    
                }
                if(isBingo){
                    return true;
                }
            }
            return false;
        }
        
        public void calculateWinner(int n){
            int sum = 0;
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    if(board[i][j] >= 0){
                        sum += board[i][j];
                    }
                }
            }
            //printBoard();
            System.out.println("Final Score: " + n*sum);
        }
    }
    public static int[] callingNumbers(String s){
        String[] arr = s.split(",");
        int[] arrInts = new int[arr.length];
        for(int i = 0; i < arr.length; i++){
            arrInts[i] = Integer.valueOf(arr[i]);
        }
        return arrInts;
    }
    public static int[] argsToIntArray(String[] args){
        int index = 0;
        int[] argsInIntArray = new int[args.length-1];
        for(int i = 1; i < args.length; i++){
            argsInIntArray[index] = Integer.valueOf(args[i]);
            index++;
        }
        return argsInIntArray;
    }
    public static void main(String args[]) {
      int[] callingNums = callingNumbers(args[0]);
      int[] argsInIntArray = argsToIntArray(args);
      List<BingoBoard> boards = new ArrayList<>();
      for(int i = 0; i < argsInIntArray.length; i+=25){
          int[] currList = Arrays.copyOfRange(argsInIntArray, i, Math.min(i + 25, argsInIntArray.length));
          BingoBoard b = new BingoBoard(currList);
          //b.printBoard();
          boards.add(b);
      }
      
      //call numbers one by one until I find a match
      for(int num : callingNums){
          List<BingoBoard> toRemove = new ArrayList<>();
          for(BingoBoard b: boards){
              if(b.callNumber(num)){
                  toRemove.add(b);
              }
          }
          boards.removeAll(toRemove);
      }
    }
}