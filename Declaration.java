
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import jaco.mp3.player.MP3Player;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import javax.swing.JProgressBar;

class Declaration {

    public JFrame f1 = new JFrame("Music Player");

    public JButton stream_Music = new JButton();
    public JButton playlist = new JButton();
    public JButton browse = new JButton();
    public JButton favourite = new JButton();
    public JButton skip_Backward = new JButton();
    public JButton play = new JButton();
    public JButton skip_Forward = new JButton();
    public JButton stop = new JButton();
    public JButton volume = new JButton();

    public JSlider volume_Slider = new JSlider();

    public JFileChooser fc;

    public MP3Player mp3 = new MP3Player();

    public String filepath;

    JTextField tf1 = new JTextField("");

    public int music_is_Playing;

    public ImageIcon ic1 = new ImageIcon("E:\\Java Projects\\Demo\\ImageIcons\\Image Icons\\512.png");

    public ImageIcon ic2 = new ImageIcon("E:\\Java Projects\\Demo\\ImageIcons\\playlist.png");
    public ImageIcon ic3 = new ImageIcon("E:\\Java Projects\\Demo\\ImageIcons\\browse.png");
    public ImageIcon ic4 = new ImageIcon("E:\\Java Projects\\Demo\\ImageIcons\\Favourite.png");
    public ImageIcon ic5 = new ImageIcon("E:\\Java Projects\\Demo\\ImageIcons\\Skip Backward.png");

    public ImageIcon ic6 = new ImageIcon("E:\\Java Projects\\Demo\\ImageIcons\\Play.png");
    public ImageIcon ic7 = new ImageIcon("E:\\Java Projects\\Demo\\ImageIcons\\Skip Forward.png");
    public ImageIcon ic8 = new ImageIcon("E:\\Java Projects\\Demo\\ImageIcons\\stop.png");
    public ImageIcon ic9 = new ImageIcon("E:\\Java Projects\\Demo\\ImageIcons\\volume-adjustment.png");
    public ImageIcon ic10 = new ImageIcon("E:\\Java Projects\\Demo\\ImageIcons\\Favourite_Added.png");
    public ImageIcon ic11 = new ImageIcon("E:\\Java Projects\\Demo\\ImageIcons\\Pause.png");
    public ImageIcon ic12 = new ImageIcon("E:\\Java Projects\\Demo\\ImageIcons\\live_Streaming.png");
    public ImageIcon ic13 = new ImageIcon("E:\\Java Projects\\Demo\\ImageIcons\\shuffle.png");

   
    
    public Font font1 = new Font("Century", Font.BOLD, 12);

    public Color LIGHT_YELLOW = new Color(255, 255, 153);

    public Color cyan_Code = new Color(0, 102, 102);

    public Color cyan_Code1 = new Color(153, 204, 255);

    public int volume_Value, play_button_is_pressed;

    

    //Playlist Frame and its Components//
    public JFrame f2 = new JFrame("Playlist");

    public JButton add_new_playlist = new JButton("Add New Playlist");

    public JButton delete_playlist = new JButton("Delete Playlist");

    public JButton favourites_Playlist = new JButton("Favourites");

    public JButton open_Playlist = new JButton("Open Playlist");

    public JButton cancel = new JButton("Cancel");

    public JButton add_To_Playlist = new JButton("Add Tracks To Playlist");

    public boolean favourite_added, mute;
    
    


    //Add New-Playlist Frame and its Components//
    public JFrame f3 = new JFrame("Add New Playlist");

    public JTextField tf2 = new JTextField(20);

    public JButton create = new JButton("Create");

    public JButton cancel_Creation = new JButton("Cancel");
    
    


    //Add New-Playlist Frame and its Components//
    public JFrame f4 = new JFrame("Select Playlist");

    public JButton select = new JButton("Select Playlist");

    public JButton cancel_Selection = new JButton("Cancel");

    public String selected_Playlist;
    




    //Add Server-Connection Estabilishing Frame and it's components// 
    public JFrame f5 = new JFrame("Connect To Server");

    public JLabel lb1 = new JLabel("Estabilishing connection with the server...!");

    public JProgressBar pb = new JProgressBar(0, 100);
    
    public JButton shuffle = new JButton();
    
    JTextField tf3 = new JTextField(60);

    public int progress_value = 0, a;

    Random rm = new Random();

    public Font font2 = new Font("Century", Font.BOLD, 15);

    String data;

    
    
    
    
    Timer t = new Timer(100, new ActionListener() {

        public void actionPerformed(ActionEvent ae) {
            play_Mp3_Files_from_server();
        }
    });

    
    
    
    
    void rand() {
        a = 1 + rm.nextInt(20);
    }

    
    
    
    
    
    void play_Mp3_Files_from_server() //Method to fetch mp3 files from Server//
    {
        if (progress_value == 100) {
            try {
                //Registering Driver//
                Class.forName("oracle.jdbc.driver.OracleDriver");

                //Connecting to Database//
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-N6R0FO5:1521:XE", "system", "angiosperm");

                rand();                                          //Generating Random Number Here //

                PreparedStatement ps = con.prepareStatement("select Music from harsh where id = ?");

                ps.setInt(1, a);

                ResultSet rs;
                rs = ps.executeQuery();

                rs.next();

                data = rs.getString("Music");
                
                
                mp3 = new MP3Player(new File(data));

                mp3.play();
                
                tf3.setText("Now Playing : " +data.substring(13));
                

            } catch (Exception e) {
                JOptionPane.showMessageDialog(f5, e);
            }

            t.stop();

        } 
        
        else {
            progress_value++;

            if (progress_value < 100) {
                pb.setString("Connecting.." + progress_value + "%");
            } else {
                pb.setString("Connected Successfully ");
                lb1.setText("Connection estabilished successfully with server !");
            }

            pb.setValue(progress_value);
        }

    }
}
