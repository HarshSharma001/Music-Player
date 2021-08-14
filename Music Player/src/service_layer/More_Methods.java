package service_layer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import jaco.mp3.player.MP3Player;

/**
 * 
 * @author Harsh Sharma
 * 
 *         Class for event based concrete implementations
 *
 *         It has direct access to all the application components via
 *         Declaration class
 */
public class More_Methods extends Declaration {

	/**
	 * method for browsing mp3 files
	 * 
	 */
	public void eventForBrowseLabel() {
		favourite.setIcon(ic4);
		if (music_is_Playing == 1) {
			mp3.stop();
		}

		if (play_button_is_pressed % 2 != 0) {
			play_button_is_pressed--;
			play.setIcon(ic6);
		}

		tf1.setText("Browsing media track...");

		fc = new JFileChooser();
		fc.setDialogTitle("Select a mp3 file");
		fc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter restrict = new FileNameExtensionFilter("MP3 Files (.mp3)", "mp3");
		fc.addChoosableFileFilter(restrict);

		int i = fc.showOpenDialog(null);

		if (i == JFileChooser.APPROVE_OPTION) {

			filepath = fc.getSelectedFile().getPath();

			mp3 = new MP3Player(new File(filepath));

			tf1.setText("Now Playing : " + filepath.substring(filepath.lastIndexOf("/") + 1));
			tf1.setVisible(true);

			if (mp3.isStopped()) {
				mp3.setRepeat(true);
			}
		}
	}

	/**
	 * method for playing a selected music track
	 */
	public void eventForPlayLabel() {
		
		// Base Condition
		if(filepath == null && mp3.getPlayList() == null || filepath == null && mp3.getPlayList().size() == 0) {
			return;
		}

		try {
			File favourite_Playlist = new File("Favourite_Playlist.txt");
			
			if (filepath.contains(".mp3") && favourite_Playlist.exists()) {
				Scanner sc = new Scanner(favourite_Playlist);
				while (sc.hasNextLine() && filepath != null) {
					String data = sc.nextLine();
					if (data.equals(filepath.substring(filepath.lastIndexOf('/')))) {
						favourite.setIcon(ic10); // Already favourite music track indication //
						break;
					} else {
						favourite.setIcon(ic4); // Music track not Added as favourite yet indication //
						break;
					}
				}
			}
			
			tf1.setVisible(true);
			int lastIndex = filepath.lastIndexOf('/');
			tf1.setText("Now Playing : " + filepath.substring(lastIndex + 1));
			
			mp3.play();
			mp3.setShuffle(true);

			music_is_Playing = 1;

			if (mp3.isStopped()) {
				mp3.setRepeat(true);
			}
			

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(f1, "Error occurred while playing selected media track !");
		}
	}

	/**
	 * method for Pause operation
	 */
	public void eventForPauseLabel() {
		if (mp3.isPaused()) {
			tf1.setText("Now Playing : " + filepath.substring(filepath.lastIndexOf("/") + 1));
			mp3.play();

			if (mp3.isStopped()) {
				mp3.setRepeat(true);
			}
		} else {
			tf1.setText("Now Paused : " + filepath.substring(filepath.lastIndexOf("/") + 1));
			mp3.pause();
		}
	}

	/**
	 * method for stopping the current playing media track
	 */
	public void eventForStopLabel() {
		favourite.setIcon(ic4);
		tf1.setVisible(false);
		if (!mp3.isPaused() || mp3.isPaused()) {
			mp3.stop();
		}

		if (play_button_is_pressed % 2 != 0) {
			play_button_is_pressed--;
			play.setIcon(ic6);
		}
	}

	/**
	 * method for adding a track as favourite media track
	 */
	public void eventForFavouriteLabel() {

		try {
			File file = new File("Favourite_Playlist.txt");
			if (file.exists()) {
				if (favourite.getIcon().equals(ic10)) {
					JOptionPane.showMessageDialog(f2, "The current media track is already added to Favourites !");
				}

				else {

					FileWriter fw = new FileWriter(file, true);

					BufferedWriter bw = new BufferedWriter(fw);

					bw.write(filepath);
					bw.newLine();

					JOptionPane.showMessageDialog(f2, "Favourite Added Succesfully");

					favourite_added = true;

					favourite.setIcon(ic10);

					bw.close();
					fw.close();

				}

			} else {

				if (filepath != null) {
					file.createNewFile();

					FileWriter fw = new FileWriter(file, true);

					BufferedWriter bw = new BufferedWriter(fw);

					bw.write(filepath);
					bw.newLine();

					JOptionPane.showMessageDialog(f2, "Favourite Added Succesfully");

					favourite_added = true;

					favourite.setIcon(ic10);

					bw.close();
					fw.close();
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(f1,
					"Issue occurred while adding favourite media track, sorry for the inconvinience!");
		}
	}

	/**
	 * method to - open a particular playlist
	 */
	public void eventForOpenPlayList() // Method defined for Open Playlist Module //
	{
		fc = new JFileChooser();
		fc.setDialogTitle("Select a playlist file");
		fc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter restrict = new FileNameExtensionFilter("Text Files (.txt)", "txt");
		fc.addChoosableFileFilter(restrict);

		int i = fc.showOpenDialog(null);

		if (i == JFileChooser.APPROVE_OPTION) {
			filepath = fc.getSelectedFile().getPath();

			try {
				if(!filepath.contains("_Playlist.txt")) {
					JOptionPane.showMessageDialog(f1, "Kindly select a valid playlist !");
					return; 
				}
				
				File playList_File = new File(filepath);
				mp3.setShuffle(true);
				
				Scanner sc = new Scanner(playList_File);

				while (sc.hasNextLine()) {
					String data = sc.nextLine();
					if(data.contains(".mp3")) {
						File file = new File(data);
						mp3.addToPlayList(file);
					}
				}

				sc.close();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(f1, "Issue occured with opening playlist, sorry for the inconvinience!");
			}

		}
	}

	/**
	 * method for adding a media track to a selected playlist
	 */
	public void eventForAddToPlayList() {
		fc = new JFileChooser();
		fc.setDialogTitle("Select a media track file");
		fc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter restrict = new FileNameExtensionFilter("MP3 files (.mp3)", "mp3");
		fc.addChoosableFileFilter(restrict);

		int i = fc.showOpenDialog(null);

		if (i == JFileChooser.APPROVE_OPTION) {
			filepath = fc.getSelectedFile().getPath();

			try {
				if(!filepath.contains(".mp3")) {
					JOptionPane.showMessageDialog(f1, "Kindly select a valid media track !");
					return; 
				}
				
				if(!selected_Playlist.contains("_Playlist.txt")) {
					JOptionPane.showMessageDialog(f1, "Kindly select a valid playlist !");
					return; 
				}
				
				FileWriter fw = new FileWriter(selected_Playlist, true);
				BufferedWriter bw = new BufferedWriter(fw);

				bw.write(filepath);
				bw.newLine();
				JOptionPane.showMessageDialog(f2, "Music Added Succesfully");

				bw.close();
				fw.close();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(f2,
						"The selected playlist isn't found, try creating a new playlist or choose any other existing playlist!");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(f1, "Issues occurred while adding media track to playliist !");
			}
		}
	}

	/**
	 * method for deleting a playlist
	 */
	public void eventForDeletePlayList() {
		fc = new JFileChooser();
		fc.setDialogTitle("Select a playlist file");
		fc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter restrict = new FileNameExtensionFilter("Text Files (.txt)", "txt");
		fc.addChoosableFileFilter(restrict);

		int i = fc.showOpenDialog(null);

		if (i == JFileChooser.APPROVE_OPTION) {
			filepath = fc.getSelectedFile().getPath();

			File delete_file = new File(filepath);

			try {
				
				if(!filepath.contains("_Playlist.txt")) {
					JOptionPane.showMessageDialog(f1, "Kindly select a valid playlist !");
					return; 
				}
				
				delete_file.delete();
				mp3 = new MP3Player();
				JOptionPane.showMessageDialog(f2, "Playlist Deleted Successfully");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(f2, "Issue occurred while deleting playlist !");

			}

		}
	}

	/**
	 * method to - play favourite playlist media tracks
	 */
	public void eventForFavouritePlayList() {
		try {

			filepath = "Favourite_Playlist.txt";

			File playList_File = new File(filepath);

			if (playList_File.exists()) {
				JOptionPane.showMessageDialog(f2, "The Favourite songs playlist has been enabled !");

				mp3.setShuffle(true);

				Scanner sc = new Scanner(playList_File);

				while (sc.hasNextLine()) {
					String data = sc.nextLine();
					if(data.contains(".mp3")) {
						File file = new File(data);
						mp3.addToPlayList(file);
					}
				}
				sc.close();
			}

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(f1, "Error favourite playlist doesn't exists yet !");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(f1, "Issues occurred while selecting favourite playlist !");
		}
	}

	/**
	 * method to - create a new playlist
	 */
	public void eventForCreation_Of_New_Playlist() {
		try {
			if (tf2.getText().equals("") || tf2.getText().contains(".") 
					|| tf2.getText().endsWith(".doc") || tf2.getText().endsWith(".m3u")) {
				JOptionPane.showMessageDialog(f3, "Please enter valid playlist name !");
			}

			else {
				String operatingSystem = System.getProperty("os.name");
				String location = null;
				if (operatingSystem.equals("Windows")) {
					location = FileSystemView.getFileSystemView()
							.getDefaultDirectory().getPath();
				} else if (operatingSystem.equals("Linux")) {
					location = FileSystemView.getFileSystemView()
							.getDefaultDirectory().getPath() + "/Documents";
				}
				
				File file = new File(location, tf2.getText() + "_Playlist.txt");

				if (file.exists()) {
					JOptionPane.showMessageDialog(f3, "Playlist already exists !");
					tf2.setText("");
				}

				else {
						file.createNewFile();
						JOptionPane.showMessageDialog(f3, "New Playlist Created Successully !");
						tf2.setText("");
				}

			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(f3, "Error while creating a new playlist !");
		}
		
	}

	/**
	 * method to - select any existing playlist
	 */
	public void event_for_selecting_Playlist() {
		try {
			fc = new JFileChooser();
			fc.setDialogTitle("Select a playlist file");
			fc.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter restrict = new FileNameExtensionFilter("Text Files (.txt)", "txt");
			fc.addChoosableFileFilter(restrict);

			int i = fc.showOpenDialog(null);

			if (i == JFileChooser.APPROVE_OPTION) {
				selected_Playlist = fc.getSelectedFile().getPath();
				if(!selected_Playlist.contains("_Playlist.txt")) {
					JOptionPane.showMessageDialog(f1, "Kindly select a valid playlist !");
					return; 
				}
			}

			eventForAddToPlayList();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(f1, "Error occurred while selecting playlist !");
		}

	}

}