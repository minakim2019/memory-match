import javax.swing.JFrame;
public class DriverMM
{
      public static void main(String[] args)
      {
         JFrame frame = new JFrame("Memory Match");
         frame.setSize(900, 900);
         frame.setLocation(200, 100);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setContentPane(new MemoryMatch());
         frame.setVisible(true);
      }
  }

