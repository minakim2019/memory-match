import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays; 

//MemoryMatch class
/***********************************************************************
Sets up the board with all cards face down
Runs the game
Lets player select difficulty
Displays winner or loser
/**********************************************************************/

public class MemoryMatch extends JPanel
{
   public JButton[] cards;
   public String[] temp, country;
   public Card card, c;
   public Timer timer;
   public JLabel label, countlabel; 

   //private ImageIcon pic, icon;
   private JButton reset, exit, first, second;
   //private String guess1, guess2;
   private int tries, count, difficulty;
 
   public MemoryMatch()
   {
      count = 0; // number of matches found
      setLayout(new BorderLayout());
  
      // initialize JPanel
      JPanel cardpanel = new JPanel();
      cardpanel.setLayout(new GridLayout(6, 8, 4, 4));
      add(cardpanel, BorderLayout.CENTER);      

      cards = new JButton[48]; // JButton array (board)
      temp = new String[48]; // temporary array used when shuffling filename
  
      card = new Card(); // Card object
  
      // Initialize string array of flag image filenames
      country = new String[]{"AUSTRALIA.png","BRAZIL.png","CANADA.png","CHINA.png","GREATBRITAIN.png",
         "GREECE.png", "JAMAICA.png","JAPAN.png","SWEDEN.png","AMERICA.png", "GERMANY.png",
        "ARGENTINA.png", "GHANA.png", "INDONESIA.png", "INDIA.png", "ITALY.png",
         "NORWAY.png", "PAKISTAN.png", "PHILIPPINES.png","RUSSIA.png","SAFRICA.png",
         "SKOREA.png", "SPAIN.png", "TAIWAN.png"};
  
      for(int x = 0; x < 48; x++) // initial setup of board
      {
         cards[x] = new JButton();  // create array of buttons
         cards[x].addActionListener(new MMListener());   // link array of buttons to MMListener 
         cards[x].setHorizontalTextPosition(JLabel.CENTER); 
         cards[x].setPreferredSize(new Dimension(75, 95));
         cards[x].setFont(new Font("Arial", Font.PLAIN, 0));
      }
  
      card.setBack(cards); // show the back of the card deck picture on every button

      for(int a = 0; a < 48; a++)   // add all 48 JButtons
         cardpanel.add(cards[a]);
    
      // Create a south panel
      JPanel southpanel = new JPanel();
      southpanel.setLayout(new FlowLayout()); 
      add(southpanel, BorderLayout.SOUTH);

      tries = 24; // allow for 24 tries to match the pairs
      
      // Add labels and buttons to south panel
      label = new JLabel("You have " + tries + " tries to match all pairs."); 
      southpanel.add(label);  // add label to south panel
      //countlabel = new JLabel("Count: ");

      reset = new JButton("Reset");
      reset.setEnabled(false);
      reset.addActionListener(new Reset());
      southpanel.add(reset);  // add reset button to south panel

      exit = new JButton("Exit");
      exit.addActionListener(new Exit());
      southpanel.add(exit);   // add exit button to south panel
  
      shuffle();  // call shuffle method
  
      // Show message, ask for difficulty: 
      String message = " Welcome to MemoryMatch!";
      message += " MemoryMatch is a simple yet intriguing game that requires keen observation, concentration, and memory. \n";
      message += " The object of the game is to match all of 24 pairs in 24 tries. \n";
      message += " Every time you match an incorrect pair, 1 is deducted from your number of tries. \n";
      message += " We have four difficulty levels: easy, medium, hard, and extreme. \n";
      message += " These levels are distinguised by amount of time a pair is shown to you after making your pick. \n";
      message += " Pick your level by entering in the corresponding number, and have fun!";                
      message = message + "\n1. Easy";
      message = message + "\n2. Medium";
      message = message + "\n3. Hard";
      message = message + "\n4. Extreme";
      message = message + "\n5. Quit.";

      difficulty = Integer.parseInt(JOptionPane.showInputDialog(message));
  
      if(difficulty == 5)  // if user input = 5, exit out of program
         System.exit(0);
  
      second = null; // make second button null
   }
        
   int click;  // define click
 
   // Memory Match Listener
   private class MMListener implements ActionListener
   {
     public void actionPerformed(ActionEvent e)
      {   
         first = (JButton) e.getSource();  // get the title of the image clicked by user
         card.faceUp(first);  // show the first card
     
         if(click == 0)   // if click is 0
         {
            second = first;   // put a string value into the second button so it is no longer null
            click = 1;
         }
         else
         {
            // if first button is equal to the second, user has clicked the same button twice -- do nothing
            if(first == second) return;
            else  
            {
               if(card.compare(cards, first, second) == true)  // Case 1: first and second cards are a pair
               {
                  card.faceUp(first); // flip both cards face up
                  card.faceUp(second); 

                  first.setEnabled(false);
                  second.setEnabled(false);  // disable user from clicking either card; this pair is done
                  click = 0;  
                  count++; // increment number of pairs found
              
                 if(count == 24) // if count is 24, user wins
                     displayWinner(); 
              
                  if(tries == 0 && count!= 24)  // if user ran out of tries and count is not 24, user loses
                     displayLoser();
               }
               else  // Case 2: both cards are different
               {
                  delay();             
                  click = 0;  // set click to 0    
                  if(count == 24)   // if count is 24, user wins
                     displayWinner(); 
              
                  if(tries == 0 && count!= 24)  //if user ran out of tries and count is not 24, user loses
                     displayLoser();
               }
           
            }
        
         }
             
      }
   }
   private class Reset implements ActionListener   // reset button
   {
      public void actionPerformed(ActionEvent e)
     {   
         reset(); 
      }
   }
  private class Exit implements ActionListener  // exit button
   {
      public void actionPerformed(ActionEvent e)
      {
         System.exit(0); 
      }
   }
 
   // Reset method
   public void reset()
   {
      card.setBack(cards); // flip all cards face-down
      label.setText("You have 24 tries remaining.");  
      tries = 24; // reset number of tries to 24
   
      for(int x = 0; x < 48; x++)   // enable the user to press all 48 buttons
      {
         cards[x].setEnabled(true);
      }
  
  }
  // Shuffle method
   public void shuffle()   
   {
      //String filenames:
      List<String> filenames = new ArrayList<>(Arrays.asList("AUSTRALIA.png","BRAZIL.png","CANADA.png","CHINA.png","GREATBRITAIN.png",
         "GREECE.png", "JAMAICA.png","JAPAN.png","SWEDEN.png","AMERICA.png", "GERMANY.png",
         "ARGENTINA.png", "GHANA.png", "INDONESIA.png", "INDIA.png", "ITALY.png",
         "NORWAY.png", "PAKISTAN.png", "PHILIPPINES.png","RUSSIA.png","SAFRICA.png",
        "SKOREA.png", "SPAIN.png", "TAIWAN.png", "AUSTRALIA.png","BRAZIL.png","CANADA.png","CHINA.png","GREATBRITAIN.png",
         "GREECE.png", "JAMAICA.png","JAPAN.png","SWEDEN.png","AMERICA.png", "GERMANY.png",
         "ARGENTINA.png", "GHANA.png", "INDONESIA.png", "INDIA.png", "ITALY.png",
         "NORWAY.png", "PAKISTAN.png", "PHILIPPINES.png","RUSSIA.png","SAFRICA.png",
         "SKOREA.png", "SPAIN.png", "TAIWAN.png"));
      Random r = new Random();
      for(JButton b : cards)
      {
         // get a random element
         int index = r.nextInt(filenames.size()); // index is between 0 and list size exclusive
         String name = filenames.remove(index); // remove the element which returns it as well
         b.setText(name);
      }
  
   }
   // Delay method
   private void delay()
   {
      // Instantiate new ActionListener
      ActionListener taskPerformer = new ActionListener(){
            public void actionPerformed(ActionEvent evt)
            {
               card.faceDown(first);
               card.faceDown(second);  
           
            }
         };

      switch(difficulty){
         case 1: // easy
            javax.swing.Timer t = new javax.swing.Timer(1500, taskPerformer); // cards stay up for 1.5 seconds
            break;
         case 2: // medium
            javax.swing.Timer t = new javax.swing.Timer(1000, taskPerformer); // cards stay up for 1 second
            break;
         case 3: // hard
            javax.swing.Timer t = new javax.swing.Timer(250, taskPerformer);  //cards stay up for 1/4 second
            break;
         default: // extreme
            javax.swing.Timer t = new javax.swing.Timer(100, taskPerformer);  //cards stay up for 1/10 second
            break;

         t.setRepeats(false);
         t.start();
       
         tries--;
         label.setText("You have " + tries + " tries remaining.");

      }
    
   }
 
 // Displays congratulatory message if game is won, as well as an option to play the game again or exit out of the game
   public void displayWinner()
   {
      label.setText("You are a winner! You matched all 24 pairs in " + tries + " tries!");
      reset.setEnabled(true);
      exit.setEnabled(true);
   }
// Displays message that prompts user to play the game again or exit out
   public void displayLoser()
   {
      label.setText("Sorry, you lose. Try again next time!");
      reset.setEnabled(true);
      exit.setEnabled(true);
      for(int x = 0; x < 48; x++)
      {
         cards[x].setEnabled(false);
      }
   }

}
















