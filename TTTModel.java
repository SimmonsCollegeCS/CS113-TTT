import java.util.*;
import java.io.*; 
   
public class TTTModel extends GameModel{
// attributes for TTT Game
   public final int NSQUARES = 9;
   private String [ ] shown = new String[9]; 
   final int SIZE = 3;  // 3 x 3 board
   public int numAttempts; // how many attempts have been played.
   boolean turnOver; // the next action ends a turn.
   public int choice; // index of the chosen square in this turn 
   String [] symbols = {"O","X"}; // start with O unless set otherwise
   String empty = " "; 
   // Sets the state that should be at the start of a new game.
   public void resetGame() {
   // initialize everything 
      turnOver = false; // at the start of the game, the first action starts a turn.
      choice = -1;      // bogus value since no turn.
      numAttempts = 0; // No attempts at the start
     
      for(int i=0;i<NSQUARES;i++)
         shown[i] = empty; 
   }
   
   void display(){
      for(int i = 0;i<NSQUARES;i++){
         if(i%SIZE!=2)
            System.out.print(shown[i] + "|" ); 
         else
            System.out.print(shown[i]); 
         if( (i+1)%SIZE ==0)
            if( i<NSQUARES-1)
               System.out.println("\n-+-+-"); 
            else
               System.out.println(); 
      }
   } 
      
      
   public boolean isTurnEnding() {
      return turnOver;
   }
   
   TTTModel(){//constructor
      resetGame(); // sets the state such that we start a new game
   }   
   
 /*  public void setSym(String s){
      sym = s; 
   }
   */ 
   public String getSym(){
      return symbols[numAttempts%2];}
  
   
   public String getCell(int i){
      return(shown[i]);
   } 
   
   public void takeTurn(int row, int col){
      choice = row*SIZE + col; 
      if(shown[choice].equals("X") ||  shown[choice].equals("O")){
         System.out.println("Illegal move. Space already taken"); 
         turnOver = false;
      }
      else {
         shown[choice] = symbols[numAttempts%2];  
         numAttempts ++; 
         turnOver = true; 
      }      
   }//take turn     
   
         
   boolean gameOverStatus(){
      if (numAttempts==9){
         return(true);}
      else{
         return(false);}
   }//game over
   
   public int get(int r, int c){
      return(r*3+c);
   }//gets shown string
         
   int getRows(){
      return(SIZE);
   }
      
   int getCols(){
      return(SIZE);
   }
            
     
   public int getNumAttempts() {
      return numAttempts;
   }
   
   String reportWinner(){
   // return O, X, None(still going), Tie
      boolean win = false;
      // win by rows, try rows 0-2
      for(int r=0;r<3;r++)
         if(!shown[r*3+0].equals(empty) && shown[r*3+0].equals(shown[r*3+1]) && shown[r*3+1].equals(shown[r*3+2]))
            return(shown[r*3+0]);
       // win by columns (0,3,6)/(1,4,7)/(2,5,8)  
      for(int c=0;c<3;c++)
         if(!shown[c+0].equals(empty) && shown[c+0].equals(shown[c+3]) && shown[c+3].equals(shown[c+6]))
            return(shown[c+0]);
               
   // win by diags
      if(!shown[0].equals(empty) && shown[0].equals(shown[4]) && shown[4].equals(shown[8]))
         return(shown[0]);
      else
         if(!shown[2].equals(empty) && shown[2].equals(shown[4]) && shown[4].equals(shown[6]))
            return(shown[2]);
   
      //no winner      
      boolean foundBlank = false; 
      for(int i=0;i<shown.length&&!foundBlank;i++)
         if(shown[i].equals(empty))
            foundBlank = true;
      if(foundBlank)
         return("None");
      else
         return("Tie"); 
         
   }//winner

}//class