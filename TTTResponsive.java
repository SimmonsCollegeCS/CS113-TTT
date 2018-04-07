import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color; 
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

/**
 *  A JavaFX: TTT Game 
 */
public class TTTResponsive extends Application
{

   Font appFont = new Font("Comic Sans MS",24);
   Label myLabel;
   Button [] buttons;  
   GridPane grid;  
   Image [] xoImages; 
   final int SIZE = 3; 
   TTTModel tttg;  
   Image background = new Image("file:star.png"); 


   public static void main(String[] args)
   {
      // Launch the application.
      launch(args);
   }
   
   @Override
   public void start(Stage stage)
   {
      xoImages = new Image[SIZE];
      xoImages[0] = new Image("file:o.png");
      xoImages[1] = new Image("file:x.png"); 
   
      buttons = new Button[SIZE*SIZE];
      tttg = new TTTModel();
      for(int i=0;i<9;i++)
         buttons[i] = makeButton(background);
         
        // Put the Button components in a new GridPane.
      grid = new GridPane( );
      grid.setVgap(10);
      
      for(int i =0;i<SIZE*SIZE;i++){
         grid.add(buttons[i],i%SIZE,i/SIZE);
         // Register the event handler.
         buttons[i].setOnAction(new ButtonClickHandler());
      }
   
     // top and bottom labels
      Label top = makeLabel("Let's play TTT!");    
      myLabel = makeLabel("hello Player 1");
      
       // larger layout: VBox
      VBox vbox = new VBox(top,grid,myLabel);  
   
        // Create a Scene with the GridPane as its root node.
      Scene scene = new Scene(vbox,1000,1000);      
      // Add the Scene to the Stage.
      stage.setScene(scene);    
      // Set the stage title.
      stage.setTitle("Tic Tac Toe ");
      // Show the window.
      stage.show();
   }
   
   Button makeButton(Image img){
       // create a button with an image on it
      // Create an ImageView component to decorate Button
      ImageView iView = new ImageView(img);
      iView.setFitWidth(100); 
      iView.setFitHeight(100);
      
      Button newButton =  new Button("",iView);
      return(newButton); 
   }
    
   Label makeLabel(String s){
      Label label = new Label(s); 
      label.setFont(appFont); 
      return(label); 
   }
  
   /*
    * Event handler class for Buttons
    */
    
   class ButtonClickHandler implements EventHandler<ActionEvent>
   {
      @Override
      public void handle(ActionEvent event)
      {
         for(int i=0;i<(SIZE*SIZE);i++){
            if(event.getSource().equals(buttons[i])){
               int row = i/SIZE; 
               int col = i%SIZE; 
               tttg.takeTurn(row,col);
               Image thisPlayer = xoImages[(tttg.getNumAttempts()+1)%2];
               buttons[i] = makeButton(thisPlayer);
               grid.add(buttons[i],col,row);             
            } 
         }
        
         // update winner info
         String winner = tttg.reportWinner(); 
         if(winner.equals("None"))        
            myLabel.setText("Reader, Player " + tttg.getSym() + "?");
         else
            if(winner.equals("Tie"))
               myLabel.setText("Game over! Tie!");     
            else
               myLabel.setText("Game over! The winner :" + winner);
      }
   }

   
}
