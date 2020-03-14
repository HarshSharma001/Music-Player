import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import jaco.mp3.player.MP3Player;


public class More_Methods extends Declaration
{
	 //Browse button settings are defined here //
    
	 
   public void eventForBrowseLabel() 
    {
    	favourite.setIcon(ic4);
        if (music_is_Playing == 1) {
            mp3.stop();
 
        }
 
 
        if(play_button_is_pressed %2 !=0)
        {
            play_button_is_pressed--;
            play.setIcon(ic6);
        }
 
        tf1.setText("Now Playing : ");
   
        fc = new JFileChooser();
    	fc.setDialogTitle("Select a mp3 file");
    	fc.setAcceptAllFileFilterUsed(false); 
    	FileNameExtensionFilter restrict = new FileNameExtensionFilter("MP3 Files (.mp3)", "mp3"); 
        fc.addChoosableFileFilter(restrict); 
        
        int i = fc.showOpenDialog(null);
 
        if (i == JFileChooser.APPROVE_OPTION) {
 
            filepath = fc.getSelectedFile().getPath();
           
            mp3 = new MP3Player(new File(filepath));
            
            tf1.setText("Now Playing : " + filepath.substring(13));
            tf1.setVisible(true);
 
            if (mp3.isStopped()) {
                mp3.setRepeat(true);
            }
        }
    }
 
 
 
    
    
    //Play button settings are defined here //
    
 
   public void eventForPlayLabel() 
    {
    	
    	try {
            File favourite_Playlist = new File("C:\\Users\\Harsh Sharma\\Documents\\Favourite.txt");
 
            Scanner sc = new Scanner(favourite_Playlist);
           
            ArrayList<String> list = new ArrayList<String>();       			//Adding Favourite Playlist Data Into A New List //
 
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
           
                list.add(data);
               
                    if(list.contains(filepath))
                    {
                        favourite.setIcon(ic10);				// Already favourite song indication //
                    }
                   
                    else
                    {
                        favourite.setIcon(ic4);					// New favourite song Added indication //
                    }
               
            }
 
 
            sc.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(f1, e);
        }
    	
    	
    	
        tf1.setVisible(true);
        tf1.setText("Now Playing : " + filepath.substring(13));
        mp3.play();
        mp3.setShuffle(true);
 
        music_is_Playing = 1;
 
        if (mp3.isStopped()) {
            mp3.setRepeat(true);
        }
    }
 
 
 
    
    
    //Pause Option, settings are defined here //
 
   public void eventForPauseLabel() 
    {
        if (mp3.isPaused()) {
            tf1.setText("Now Playing : " + filepath.substring(13));
            mp3.play();
 
            if (mp3.isStopped()) {
                mp3.setRepeat(true);
            }
        } else {
            tf1.setText("Now Paused : " + filepath);
            mp3.pause();
        }
    }
 
 
 
    
    //Stop button settings are defined here //
 
   public void eventForStopLabel() 
    {
    	favourite.setIcon(ic4);
        tf1.setVisible(false);
        mp3.stop();
 
 
        if(play_button_is_pressed %2 !=0)
        {
            play_button_is_pressed--;
            play.setIcon(ic6);
        }
    }
    
    
    
    
    
    
    
   //Favourite button settings are defined here //
    
    
   
   public void eventForFavouriteLabel() {
        
        File file = new File("C:\\Users\\Harsh Sharma\\Documents\\Favourite.txt");

            if (file.exists()) {
                try {
                	
                	if(favourite.getIcon().equals(ic10))
                	{
                		JOptionPane.showMessageDialog(f2, "The current song is already added to Favourites !");
                	}
                	

                	
                	else
                	{
                	
                	
                    FileWriter fw = new FileWriter(file, true);

                    BufferedWriter bw = new BufferedWriter(fw);


                    bw.write(filepath);
                    bw.newLine();
                    
                    JOptionPane.showMessageDialog(f2, "Favourite Added Succesfully");
                    
                    favourite_added=true;
                    
                    favourite.setIcon(ic10);

                    bw.close();
                    fw.close();
                
                	}
                    
                	} catch (Exception e) {
                    JOptionPane.showMessageDialog(f1, e);
                }
            } else {

                try {
                    file.createNewFile();

                    FileWriter fw = new FileWriter(file, true);

                    BufferedWriter bw = new BufferedWriter(fw);

                    
                    bw.write(filepath);
                    bw.newLine();
                    
                    JOptionPane.showMessageDialog(f2, "Favourite Added Succesfully");
                    
                    favourite_added=true;
                    
                    favourite.setIcon(ic10);

                    bw.close();
                    fw.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(f1, e);
                }
            }
            
        }
 
 
    
    
    
    
    // Playlist Frame Components Methods Are Defined Here //
    
    
    
   public void eventForOpenPlayList()     							// Method defined for Open Playlist Module //
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
                File playList_File = new File(fc.getSelectedFile().getPath());
                mp3.setShuffle(true);

                Scanner sc = new Scanner(playList_File);

                while (sc.hasNextLine()) {
                    String data = sc.nextLine();
                    File file = new File(data);

                    mp3.addToPlayList(file);
                }


                sc.close();
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(f1, e);
            }

        }
    }
    
    
    
    
    
    
   public void eventForAddToPlayList()								// Method defined for Add Songs To Playlist Module //
    {
    	fc = new JFileChooser();
    	fc.setDialogTitle("Select a song file");
    	fc.setAcceptAllFileFilterUsed(false); 
    	FileNameExtensionFilter restrict = new FileNameExtensionFilter("MP3 files (.mp3)", "mp3"); 
        fc.addChoosableFileFilter(restrict); 
    	
        int i = fc.showOpenDialog(null);


        if (i == JFileChooser.APPROVE_OPTION) {
            filepath = fc.getSelectedFile().getPath();

            try 
            {
                FileWriter fw = new FileWriter(selected_Playlist, true);
                BufferedWriter bw = new BufferedWriter(fw);
                
               
                bw.write(filepath);
                bw.newLine();
                JOptionPane.showMessageDialog(f2, "Music Added Succesfully");
                
                bw.close();
                fw.close();
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(f2, e);
            } catch (IOException e) {
				JOptionPane.showMessageDialog(f1, e);
			} 
        }
    }
    
    
  
    
    
    
    
   public void eventForDeletePlayList() 								// Method defined for Delete Playlist Module //
    {
    	fc = new JFileChooser();
    	fc.setDialogTitle("Select a playlist file");
    	fc.setAcceptAllFileFilterUsed(false); 
    	FileNameExtensionFilter restrict = new FileNameExtensionFilter("Text Files (.txt)", "txt"); 
        fc.addChoosableFileFilter(restrict); 
        
        int i = fc.showOpenDialog(null);

        if (i == JFileChooser.APPROVE_OPTION) {
            filepath = fc.getSelectedFile().getPath();
            
            File delete_file = new File(filepath);

            try
            {
                delete_file.delete();
                JOptionPane.showMessageDialog(f2, "Playlist Deleted Successfully");
            }
            catch(Exception e)
            {
           	 JOptionPane.showMessageDialog(f2, e);
           	 
            }

        }
    }
    
    
    
    
    
    
    
   public void eventForFavouritePlayList()							// Method defined for Favourite Playlist Module //
    {
    	try {
    		
    		filepath="C:\\Users\\Harsh Sharma\\Documents\\Favourite.txt";
    		
            File playList_File = new File(filepath);
            
            
            JOptionPane.showMessageDialog(f2, "The Favourite songs playlist has been enabled !");
            
            
            mp3.setShuffle(true);

            Scanner sc = new Scanner(playList_File);

            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                File file = new File(data);

                mp3.addToPlayList(file);
            }


            sc.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(f1, e);
        }
    }
    
    
    
    
    
    
   public void eventForCreation_Of_New_Playlist()								// Method defined for Create New Playlist Module //
    {
    	
    	
    	if(tf2.getText().equals("") || tf2.getText().endsWith(".doc")  || tf2.getText().endsWith(".m3u"))
    	{
    		JOptionPane.showMessageDialog(f3, "Please enter valid playlist name !");
    	}
    	
    	else
    	{
    	
    	
    	File file = new File("C:\\Users\\Harsh Sharma\\Documents", tf2.getText()+".txt");
    
    	
    	if(file.exists())
    	{
    		JOptionPane.showMessageDialog(f3, "Playlist already exists !");
    		tf2.setText("");
    	}
    	
    	
    	else
    	{
    	try 
    	{
			file.createNewFile();
			JOptionPane.showMessageDialog(f3, "New Playlist Created Successully !");
			tf2.setText("");
		} 
    	catch (IOException e) {
			JOptionPane.showMessageDialog(f3, e);
		}
    	
    	}
    	
    	}
    }
    
    
    
    
    
    //Select Any Playlist Frame component Method //
    
    
    
   public void event_for_selecting_Playlist()								// Method defined for Selecting Playlist to add songs in it //
    {
    	fc = new JFileChooser();
    	fc.setDialogTitle("Select a playlist file");
    	fc.setAcceptAllFileFilterUsed(false); 
    	FileNameExtensionFilter restrict = new FileNameExtensionFilter("Text Files (.txt)", "txt"); 
        fc.addChoosableFileFilter(restrict); 
    	
        int i = fc.showOpenDialog(null);


        if (i == JFileChooser.APPROVE_OPTION) 
        {
            selected_Playlist = fc.getSelectedFile().getPath();

        }
        
        eventForAddToPlayList();
    }
   
   
   
   
   
   
   
   
}