import java.util.*; 

class DriverTTT{    
   public static void main(String argv[]){
      Scanner input = new Scanner(System.in);
      TTTModel tttg = new TTTModel();
      //tttg.displayDebug(); 
      
      while ( !tttg.gameOverStatus()){
         do{
            System.out.println("What position, player " + tttg.getSym() + " ?");
            int c1 = input.nextInt(); 
            System.out.println("What position?");
            int c2 = input.nextInt(); 
            tttg.takeTurn(c1,c2); 
            tttg.display();
         }while(!tttg.isTurnEnding());

         System.out.println("There are  " + (9- tttg.getNumAttempts()) + " attempts left"); 
      }
   }

} 
