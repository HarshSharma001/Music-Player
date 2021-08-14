package service_layer;

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

/**
 * 
 * @author Harsh Sharma 
 * 		
 *         A super class to declare and instantiate GUI components
 *         which are available to all sub classes directly
 */
public class Declaration {

	protected JFrame f1 = new JFrame("Music Player");

//	protected JButton stream_Music = new JButton();
	protected JButton playlist = new JButton();
	protected JButton browse = new JButton();
	protected JButton favourite = new JButton();
	protected JButton skip_Backward = new JButton();
	protected JButton play = new JButton();
	protected JButton skip_Forward = new JButton();
	protected JButton stop = new JButton();
	protected JButton volume = new JButton();

	protected JSlider volume_Slider = new JSlider();

	protected JFileChooser fc;

	protected MP3Player mp3 = new MP3Player();

	protected String filepath;

	JTextField tf1 = new JTextField("");

	protected int music_is_Playing;

	protected ImageIcon ic1 = new ImageIcon("src//pictures//512.png");

	protected ImageIcon ic2 = new ImageIcon("src//pictures//playlist.png");
	protected ImageIcon ic3 = new ImageIcon("src//pictures//browse.png");
	protected ImageIcon ic4 = new ImageIcon("src//pictures//Favourite.png");
	protected ImageIcon ic5 = new ImageIcon("src//pictures//Skip Backward.png");

	protected ImageIcon ic6 = new ImageIcon("src//pictures//Play.png");
	protected ImageIcon ic7 = new ImageIcon("src//pictures//Skip Forward.png");
	protected ImageIcon ic8 = new ImageIcon("src//pictures//stop.png");
	protected ImageIcon ic9 = new ImageIcon("src//pictures//volume-adjustment.png");
	protected ImageIcon ic10 = new ImageIcon("src//pictures//Favourite_Added.png");
	protected ImageIcon ic11 = new ImageIcon("src//pictures//Pause.png");
	protected ImageIcon ic13 = new ImageIcon("src//pictures//shuffle.png");
	protected ImageIcon ic14 = new ImageIcon("src//pictures//volume-adjustment-off.png");

	protected Font font1 = new Font("Century", Font.BOLD, 12);

	protected Color LIGHT_YELLOW = new Color(255, 255, 153);

	protected Color cyan_Code = new Color(0, 102, 102);

	protected Color cyan_Code1 = new Color(153, 204, 255);

	protected int volume_Value, play_button_is_pressed;

	// Playlist Frame and its Components//
	protected JFrame f2 = new JFrame("Playlist");

	protected JButton add_new_playlist = new JButton("Add New Playlist");

	protected JButton delete_playlist = new JButton("Delete Playlist");

	protected JButton favourites_Playlist = new JButton("Favourites");

	protected JButton open_Playlist = new JButton("Open Playlist");

	protected JButton cancel = new JButton("Cancel");

	protected JButton add_To_Playlist = new JButton("Add Tracks To Playlist");

	protected boolean favourite_added, mute;

	// Add New-Playlist Frame and its Components//
	protected JFrame f3 = new JFrame("Add New Playlist");

	protected JTextField tf2 = new JTextField(20);

	protected JButton create = new JButton("Create");

	protected JButton cancel_Creation = new JButton("Cancel");

	// Add New-Playlist Frame and its Components//
	protected JFrame f4 = new JFrame("Select Playlist");

	protected JButton select = new JButton("Select Playlist");

	protected JButton cancel_Selection = new JButton("Cancel");

	protected String selected_Playlist;

	// Add Server-Connection Estabilishing Frame and it's components//
	protected JFrame f5 = new JFrame("Connect To Server");

	protected JLabel lb1 = new JLabel("Estabilishing connection with the server...!");

	protected JProgressBar pb = new JProgressBar(0, 100);

	protected JButton shuffle = new JButton();

	JTextField tf3 = new JTextField(60);

	protected int progress_value = 0, a;

	Random rm = new Random();

	protected Font font2 = new Font("Century", Font.BOLD, 15);

	String data;

	Timer t = new Timer(100, new ActionListener() {

		public void actionPerformed(ActionEvent ae) {
			play_Mp3_Files_from_server();
		}
	});

	void rand() {
		a = 1 + rm.nextInt(20);
	}

	void play_Mp3_Files_from_server() // Method to fetch mp3 files from Server//
	{
		if (progress_value == 100) {
			try {
				// Registering Driver//
				Class.forName("oracle.jdbc.driver.OracleDriver");

				// Connecting to Database//
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-N6R0FO5:1521:XE", "system",
						"angiosperm");

				rand(); // Generating Random Number Here //

				PreparedStatement ps = con.prepareStatement("select Music from harsh where id = ?");

				ps.setInt(1, a);

				ResultSet rs;
				rs = ps.executeQuery();

				rs.next();

				data = rs.getString("Music");

				mp3 = new MP3Player(new File(data));

				mp3.play();

				tf3.setText("Now Playing : " + data.substring(13));

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
