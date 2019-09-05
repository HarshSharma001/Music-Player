import jaco.mp3.player.MP3Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;


class Demo implements ActionListener
{
    JFrame f1 = new JFrame("Music Player");  
    
    JButton b1 = new JButton("Open");
    JButton b2 = new JButton("Play");
    JButton b3 = new JButton("Pause");
    JButton b4 = new JButton("Stop");
    
    JButton b5 = new JButton("");
    
    ImageIcon ic = new ImageIcon("C:\\Users\\Harsh Sharma\\Downloads\\musicplayer_ui_kit.png");
    
    ImageIcon ic1 = new ImageIcon("C:\\Users\\Harsh Sharma\\Downloads\\images.jfif");
    
    JLabel lb = new JLabel(ic);
    
    JLabel lb1 = new JLabel();

    
    JFileChooser fc;
   
    MP3Player mp3;
   
    String filepath;

    
    void proceed()
    {
        
        b1.setPreferredSize(new Dimension(90,30));
        b1.setBounds(140,30,100,30);
        b1.setBackground(Color.white);
        b1.addActionListener(this);
        
        b2.setPreferredSize(new Dimension(90,30));
        b2.setBounds(140,80,100,30);
        b2.setBackground(Color.white);
        b2.addActionListener(this);
        
        b3.setPreferredSize(new Dimension(90,30));
        b3.setBounds(140,130,100,30);
        b3.setBackground(Color.white);
        b3.addActionListener(this);
        
        b4.setPreferredSize(new Dimension(90,30));
        b4.setBounds(140,180,100,30);
        b4.setBackground(Color.white);
        b4.addActionListener(this);
        
        
        lb.setBounds(00,00,400,270);
  
        
        
        
        f1.add(b1);
        f1.add(b2);
        f1.add(b3);
        f1.add(b4);
        f1.add(b5);
        f1.add(lb);
        f1.add(lb1);
    
       
        f1.setIconImage(ic1.getImage());
        f1.setLocation(350,100);
        f1.setVisible(true);
        f1.setLayout(null);
        f1.setSize(400, 300);
        f1.setDefaultCloseOperation(3);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        try
        {
       if(ae.getSource()==b1)
       {
        fc = new JFileChooser();    
        int i = fc.showOpenDialog(null);    
        
        if(i==JFileChooser.APPROVE_OPTION)
        {    
        
           filepath = fc.getSelectedFile().getPath();
            
           mp3 = new MP3Player(new File(filepath));
           
           
        }
        
       }
    
        
         if(ae.getSource()==b2)
       {
           mp3.play();
       }
       
       if(ae.getSource()==b3)
       {
           if(mp3.isPaused())
           {
               mp3.play();
           }
           else
           {    
           mp3.pause();
           }
       }
       
       if(ae.getSource()==b4)
       {
           mp3.stop();
       }
       
        }
        catch(Exception e)
        {
           JOptionPane.showMessageDialog(f1, "" +e);
        }
        
        if(mp3.isStopped())
        {
            mp3.setRepeat(true);
        }
        
        
    }
    
    
    
    public static void main(String args[])
    {
     new Demo().proceed();   
    }
}