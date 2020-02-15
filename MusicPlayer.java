
import jaco.mp3.player.MP3Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.*;

class Demo implements MouseListener {

    JFrame f1 = new JFrame("Music Player");

    JLabel browse = new JLabel();
    JLabel play = new JLabel();
    JLabel pause = new JLabel();
    JLabel stop = new JLabel();

    JLabel extra_Label_For_Options_In_Main_Frame = new JLabel();

    JFileChooser fc;

    MP3Player mp3;

    String filepath;

    JTextField tf1 = new JTextField("");

    private int music_is_Playing;

    ImageIcon ic = new ImageIcon("E:\\Java Projects\\Music Player2\\src\\Image Icons\\Demo.gif");

    ImageIcon ic1 = new ImageIcon("E:\\Java Projects\\Music Player2\\src\\Image Icons\\512.png");

    ImageIcon ic2 = new ImageIcon("E:\\Java Projects\\Music Player2\\src\\Image Icons 1\\b1.png");
    ImageIcon ic3 = new ImageIcon("E:\\Java Projects\\Music Player2\\src\\Image Icons 1\\b2.png");
    ImageIcon ic4 = new ImageIcon("E:\\Java Projects\\Music Player2\\src\\Image Icons 1\\b3.png");
    ImageIcon ic5 = new ImageIcon("E:\\Java Projects\\Music Player2\\src\\Image Icons 1\\b4.png");

    JLabel lb = new JLabel(ic);  //For Backgroud Icon of the Main Frame//

    JLabel lb1 = new JLabel();         //Extra Label, For Backgroud Icon of the Main Frame//

    Font font1 = new Font("Century", Font.BOLD, 12);

    Color LIGHT_YELLOW = new Color(255, 255, 153);

    void proceed() {

        browse.setPreferredSize(new Dimension(90, 30));  //Browse label Event Starts Here//
        browse.setBounds(350, 30, 153, 50);
        browse.setIcon(ic2);
        browse.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent me) {
                eventForBrowseLabel();
            }
        });

        play.setPreferredSize(new Dimension(90, 30));   //Play label Event Starts Here//
        play.setBounds(353, 90, 152, 50);
        play.setIcon(ic3);
        play.addMouseListener(new MouseAdapter() {
        	
            public void mousePressed(MouseEvent me) {
            	try
            	{
                eventForPlayLabel();
            	}catch(Exception e)
            	{
            	JOptionPane.showMessageDialog(f1, e);	
            	tf1.setVisible(false);
            	}
            }
        });

        pause.setPreferredSize(new Dimension(90, 30));  //Pause label Event Starts Here//
        pause.setBounds(355, 150, 152, 50);
        pause.setIcon(ic4);
        pause.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent me) {
            	try
            	{
                eventForPauseLabel();
            	}catch(Exception e)
            	{
            	JOptionPane.showMessageDialog(f1, e);
            	tf1.setVisible(false);
            	}
            }
        });

        stop.setPreferredSize(new Dimension(90, 30));   //Stop label Event Starts Here//
        stop.setBounds(360, 210, 150, 50);
        stop.setIcon(ic5);
        stop.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent me) {
            	try
            	{
                eventForStopLabel();
            	}catch(Exception e)
            	{
            	JOptionPane.showMessageDialog(f1, e);	
            	}
            }
        });

        tf1.setBounds(05, 290, 835, 30);
        tf1.setFont(font1);
        tf1.setBackground(LIGHT_YELLOW);
        tf1.setEditable(false);
        tf1.setVisible(false);

        lb.setBounds(00, 00, 850, 400);

        f1.add(browse);
        f1.add(play);
        f1.add(pause);
        f1.add(stop);
        f1.add(extra_Label_For_Options_In_Main_Frame);

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

    void eventForBrowseLabel() {
        if (music_is_Playing == 1) {
            mp3.stop();
        }

        tf1.setText("Now Playing : ");
        fc = new JFileChooser();
        int i = fc.showOpenDialog(null);

        if (i == JFileChooser.APPROVE_OPTION) {

            filepath = fc.getSelectedFile().getPath();

            mp3 = new MP3Player(new File(filepath));

            tf1.setText("Now Playing : " + filepath);
            tf1.setVisible(true);

            if (mp3.isStopped()) {
                mp3.setRepeat(true);
            }
        }
    }

    void eventForPlayLabel() {
        tf1.setVisible(true);
        tf1.setText("Now Playing : " + filepath);
        mp3.play();

        music_is_Playing = 1;

        if (mp3.isStopped()) {
            mp3.setRepeat(true);
        }
    }

    void eventForPauseLabel() {
        if (mp3.isPaused()) {
            tf1.setText("Now Playing : " + filepath);
            mp3.play();

            if (mp3.isStopped()) {
                mp3.setRepeat(true);
            }
        } else {
            tf1.setText("Now Paused : " + filepath);
            mp3.pause();
        }
    }

    void eventForStopLabel() {
        tf1.setVisible(false);
        mp3.stop();
    }

    public static void main(String args[]) {
        new Demo().proceed();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
