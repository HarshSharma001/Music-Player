
import jaco.mp3.player.MP3Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;


class Demo implements ActionListener {

    JFrame f1 = new JFrame("Music Player");

    JButton b1 = new JButton();
    JButton b2 = new JButton();
    JButton b3 = new JButton();
    JButton b4 = new JButton();

    JButton b5 = new JButton("");

    JFileChooser fc;

    MP3Player mp3;

    String filepath;
    
    JTextField tf1 = new JTextField("");
    
    private int music_is_Playing;
    
    ImageIcon ic = new ImageIcon("E:\\Java Projects\\Music Player2\\src\\Image Icons\\BG.jpg");
    
    ImageIcon ic1 = new ImageIcon("E:\\Java Projects\\Music Player2\\src\\Image Icons\\512.png");
    
    ImageIcon ic2 = new ImageIcon("E:\\Java Projects\\Music Player2\\src\\Image Icons\\b1.png");
    ImageIcon ic3 = new ImageIcon("E:\\Java Projects\\Music Player2\\src\\Image Icons\\b2.png");
    ImageIcon ic4 = new ImageIcon("E:\\Java Projects\\Music Player2\\src\\Image Icons\\b3.png");
    ImageIcon ic5 = new ImageIcon("E:\\Java Projects\\Music Player2\\src\\Image Icons\\b4.png");
    
    JLabel lb = new JLabel(ic);
    
    JLabel lb1 = new JLabel();
    
    Font font1 = new Font("Century", Font.BOLD,12);
    
    Color LIGHT_YELLOW = new Color(255, 255, 153);
    
    
    void proceed() {

        b1.setPreferredSize(new Dimension(90, 30));
        b1.setBounds(360, 30, 120, 40);
        b1.setIcon(ic2);
        b1.addActionListener(this);
        
  
        b2.setPreferredSize(new Dimension(90, 30));
        b2.setBounds(360, 90, 120, 40);
        b2.setIcon(ic3);
        b2.addActionListener(this);

        b3.setPreferredSize(new Dimension(90, 30));
        b3.setBounds(360, 150, 120, 40);
        b3.setIcon(ic4);
        b3.addActionListener(this);

        b4.setPreferredSize(new Dimension(90, 30));
        b4.setBounds(360, 210, 120, 40);
        b4.setIcon(ic5);
        b4.addActionListener(this);
        
        tf1.setBounds(05, 270, 835, 30);
        tf1.setFont(font1);
        tf1.setBackground(LIGHT_YELLOW);
        tf1.setEditable(false);
        tf1.setVisible(false);
        
        lb.setBounds(00,00,850,400);

       

        f1.add(b1);
        f1.add(b2);
        f1.add(b3);
        f1.add(b4);
        f1.add(b5);
       
        
        f1.add(tf1);
        
        f1.add(lb);
        f1.add(lb1);
      
        f1.setIconImage(ic1.getImage());
        f1.setLocation(300, 200);
        f1.setVisible(true);
        f1.setLayout(null);
        f1.setSize(850, 400);
        f1.setDefaultCloseOperation(3);
        f1.setResizable(false);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == b1) {
                
                if(music_is_Playing==1)
                {
                    mp3.stop();
                }
                
                tf1.setText("Now Playing : ");
                fc = new JFileChooser();
                int i = fc.showOpenDialog(null);

                if (i == JFileChooser.APPROVE_OPTION) {

                    filepath = fc.getSelectedFile().getPath();

                    mp3 = new MP3Player(new File(filepath));
                    
                    tf1.setText("Now Playing : "  +filepath);
                    tf1.setVisible(true);
                    
                }

            }

            if (ae.getSource() == b2) {
                tf1.setVisible(true);
                tf1.setText("Now Playing : "  +filepath);
                mp3.play();
                
                music_is_Playing=1;
            }

            if (ae.getSource() == b3) {
                if (mp3.isPaused()) {
                    tf1.setText("Now Playing : "  +filepath);
                    mp3.play();
                } else {
                    tf1.setText("Now Paused : "  +filepath);
                    mp3.pause();
                }
            }

            if (ae.getSource() == b4) {
                tf1.setVisible(false);
                mp3.stop();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(f1, "Please select a Music File !");
        }

        if (mp3.isStopped()) {
            mp3.setRepeat(true);
        }

    }

    public static void main(String args[]) {
        new Demo().proceed();
    }
}
