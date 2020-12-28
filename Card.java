import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Collections;
import java.util.Arrays; 

//Card class
/**********************************************************************
Represents a card that shows its back and face 
setBack() method flips the whole board down
setFace() method labels JButtons with filenames in order to compare icons
compare() method compares the images of two JButtons using filenames
faceUp() method turns a card face up to show its hidden picture
faceDown() method turns a card face down to show the same back as the 
rest of the board
/**********************************************************************/

public class Card extends JLabel
{
   public ImageIcon icon, icon1, icon2, image, icon4;  
   public Image img1, img2, img3;
 
   public Card()
   {
      //Constructor
   }
   // setBack() method flips the whole board down
   public void setBack(JButton[] array)   //show back side of card
   {
      icon = new ImageIcon(this.getClass().getResource("back.png"));
      img1 = (icon.getImage().getScaledInstance(75, 95, java.awt.Image.SCALE_SMOOTH));
      icon1 = new ImageIcon(img1);  // create and scale image icons

      for(int x = 0; x < array.length; x++)
      {
         array[x].setIcon(icon1);  // set the back picture for every button
      }
   }
   // setFace() method labels JButtons with filenames in order to compare icons
   public void setFace(String[] file, JButton[] buttons)
   {
      for(int i = 0; i < 24; i++)   
      {
         // sets filename on the JButton
         buttons[i].setText(file[i]);
         buttons[i+24].setText(file[i]);  
      }
   }
  // compare() method compares the images of two JButtons using filenames
  public boolean compare(JButton[] button, JButton button1, JButton button2)
  {
      if((button1.getText()).equals(button2.getText()))
      {
            button1.setEnabled(false);
            button2.setEnabled(false); 
            return true; 
      }
      return false;
   }
 // faceUp() method flips a card face up to show its hidden picture
   public void faceUp(JButton button)
   {
      icon4 = new ImageIcon(this.getClass().getResource(button.getText()));
      img2 = (icon4.getImage().getScaledInstance(75, 95, java.awt.Image.SCALE_SMOOTH));
      icon2 = new ImageIcon(img2);
      button.setIcon(icon2);  // show picture
  }
  // faceDown() method turns a card face down to show universal back image
   public void faceDown(JButton button) 
   {
      icon3 = new ImageIcon(this.getClass().getResource("back.png"));
      img3 = (icon.getImage().getScaledInstance(75, 95, java.awt.Image.SCALE_SMOOTH));
      icon3 = new ImageIcon(img3);  
    
      button.setIcon(icon3);  // show picture
   }
 }
 








