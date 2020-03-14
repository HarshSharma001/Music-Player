import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

import javax.swing.JOptionPane;

class Methods extends More_Methods implements ActionListener
{
	
	
	//Program Control is getting started from here //
	
	

	 
    public void proceed() 
    {

    stream_Music.setIcon(ic12);  						//Setting Icon To stream_Music Button//
    stream_Music.addActionListener(this);
    stream_Music.setBackground(cyan_Code1);    
    stream_Music.setToolTipText("Stream music from server");
        
    playlist.setIcon(ic2);                                                      //Setting Icon To Playlist Button//
    playlist.addActionListener(this);
    playlist.setBackground(cyan_Code1);
    playlist.setToolTipText("Playlist");

    browse.setIcon(ic3);							//Setting Icon To Browse Button//
    browse.addActionListener(this);
    browse.setBackground(cyan_Code1);
    browse.setToolTipText("Browse your mp3 files");

    favourite.setIcon(ic4);							//Setting Icon To Favorite Button//
    favourite.addActionListener(this);
    favourite.setBackground(cyan_Code1);

    skip_Backward.setIcon(ic5);                                                 //Setting Icon To Skip Backward Button//
    skip_Backward.addActionListener(this);
    skip_Backward.setBackground(cyan_Code1);

    play.setIcon(ic6);								//Setting Icon To Skip Backward Button//
    play.addActionListener(this);
    play.setBackground(cyan_Code1);

    skip_Forward.setIcon(ic7);                                                  //Setting Icon To Skip Forward Button//
    skip_Forward.addActionListener(this);
    skip_Forward.setBackground(cyan_Code1);

    stop.setIcon(ic8);								//Setting Icon To Stop Button//
    stop.addActionListener(this);
    stop.setBackground(cyan_Code1);

    volume.setIcon(ic9);							//Setting Icon To Volume Button//
    volume.addActionListener(this);
    volume.setBackground(cyan_Code1);

    volume_Slider.setToolTipText("Volume");
    volume_Slider.setBackground(cyan_Code1);
    volume_Slider.addMouseListener(new MouseAdapter() {   		

        public void mousePressed(MouseEvent me) {
        	
        	
        	//Setting Up MouseListener To the Volume Slider//
        	
            try
            {
                volume_Value = volume_Slider.getValue();
                mp3.setVolume(volume_Value);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(f1, e);
            }
        }
    });




    
    
   //Setting Main Frame Components and Adding Them as Well//
    


    tf1.setFont(font1);
    tf1.setBackground(LIGHT_YELLOW);
    tf1.setEditable(false);
    tf1.setVisible(false);


    f1.add(stream_Music);
    f1.add(playlist);
    f1.add(browse);
    f1.add(favourite);
    f1.add(tf1);
    f1.add(skip_Backward);
    f1.add(play);
    f1.add(skip_Forward);
    f1.add(stop);
    f1.add(volume);
    f1.add(volume_Slider);

   
    
    

    f1.setIconImage(ic1.getImage());
    f1.setLocation(10, 50);
    f1.setVisible(true);
    f1.setLayout(new FlowLayout());
    f1.setSize(1350, 150);
    f1.setDefaultCloseOperation(3);
    f1.setResizable(true);
    f1.getContentPane().setBackground(cyan_Code);
}





//Playlist Frame Settings and Adding Components//


void playList_Form_Settings()
{
    cancel.addActionListener(this);
    cancel.setBackground(cyan_Code1);
    open_Playlist.addActionListener(this);
    open_Playlist.setBackground(cyan_Code1);
    add_To_Playlist.addActionListener(this);
    add_To_Playlist.setBackground(cyan_Code1);
    add_new_playlist.addActionListener(this);
    add_new_playlist.setBackground(cyan_Code1);
    delete_playlist.addActionListener(this);
    delete_playlist.setBackground(cyan_Code1);
    favourites_Playlist.addActionListener(this);
    favourites_Playlist.setBackground(cyan_Code1);

    f2.add(open_Playlist);
    f2.add(add_To_Playlist);
    f2.add(add_new_playlist);
    f2.add(delete_playlist);
    f2.add(favourites_Playlist);
    f2.add(cancel);


    f2.setVisible(true);
    f2.setSize(400,400);
    f2.setLocation(100,50);
    f2.setLayout(new FlowLayout());
    f2.getContentPane().setBackground(cyan_Code);
}


//Playlist Frame Settings ending here//







//Add-New-Playlist Frame Settings and Adding Components//


void add_New_PlayList_Form_Settings()
{
	cancel_Creation.addActionListener(this);
        cancel_Creation.setBackground(cyan_Code1);
	create.addActionListener(this);
        create.setBackground(cyan_Code1);

	
	tf2.setText("Enter playlist name here !");
	tf2.addMouseListener(new MouseAdapter() {   		
		 
        public void mousePressed(MouseEvent me) {
        	
        	
        	//Setting Up MouseListener To the TextField of create playlist form//
        	
            try
            {
                tf2.setText("");
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(f1, e);
            }
        }
    });
	
    f3.add(tf2);
    f3.add(create);
    f3.add(cancel_Creation);


    f3.setVisible(true);
    f3.setSize(300,100);
    f3.setLocation(300,200);
    f3.setLayout(new FlowLayout());
    f3.getContentPane().setBackground(cyan_Code);
}


//Add-New-Playlist Frame Settings ending here//








//Select any playlist frame and components settings are defined here //

void select_Any_Playlist()
{
	select.addActionListener(this);
        select.setBackground(cyan_Code1);
	cancel_Selection.addActionListener(this);
        cancel_Selection.setBackground(cyan_Code1);
	
	
	f4.add(select);
    f4.add(cancel_Selection);


    f4.setVisible(true);
    f4.setSize(300,100);
    f4.setLocation(300,100);
    f4.setLayout(new FlowLayout());
    f4.getContentPane().setBackground(cyan_Code);
	
	
}






//Select any playlist frame and components settings are defined here //

void connect_To_Server_Frame_Settings()
{
    lb1.setFont(font2);
    lb1.setForeground(Color.white);
    pb.setStringPainted(true);
    shuffle.setIcon(ic13);
    shuffle.setBackground(cyan_Code1);
    shuffle.addActionListener(this);
    
    tf3.setText("Accessing playlist from server....!!!");
    tf3.setFont(font1);
    tf3.setBackground(Color.yellow);
    
    f5.add(lb1);
    f5.add(pb);
    f5.add(shuffle);
    f5.add(tf3);



    f5.setVisible(true);
    f5.setSize(800,150);
    f5.setLocation(300,150);
    f5.setLayout(new FlowLayout());
    f5.getContentPane().setBackground(cyan_Code);
    f5.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



}







//ActionPerformed Method is defined here and Starts from here //


public void actionPerformed(ActionEvent ae)
{
    
    
    if(ae.getSource() == playlist)   			//Playlist button of Main Frame //
    {
        playList_Form_Settings();
    }



    if(ae.getSource() == browse)				//Browse button of Main Frame //
    {
        try
        {
            eventForBrowseLabel();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(f1, e);
        }
    }




    if(ae.getSource() == favourite)				//Favorite button of Main Frame //
    {
    	eventForFavouriteLabel();
    }




    if(ae.getSource() == skip_Backward )		//Skip Backward button of Main Frame //
    {
        try
        {
            mp3.skipBackward();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(f1, e);
        }
    }




    if(ae.getSource() == play)					//Play button of Main Frame //
    {
        try
        {
            play_button_is_pressed ++;

            if(play_button_is_pressed % 2 != 0)
            {
            	eventForPlayLabel();
                play.setIcon(ic11);
            }

            else
            {
            	eventForPauseLabel();
                play.setIcon(ic6);
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(f1, e);
        }
    }




    if(ae.getSource() == skip_Forward)					//Skip Forward button of Main Frame //
    {
        try
        {
            mp3.skipForward();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(f1, e);
        }
    }



    if(ae.getSource() == stop)						//Stop button of Main Frame //
    {
    	try
    	{
    		eventForStopLabel();	
    	}
    	catch(Exception e)
    	{
    		JOptionPane.showMessageDialog(f1, e);
    	}
        
    }
    
    
    
    
    if(ae.getSource() == volume )					//Volume button of Main Frame to Mute the volume //
    {

    	int value = volume_Slider.getValue();
    	
    	if(mute == true)
    	{
    		mp3.setVolume(value);
    	}
    	
    	else
    	{
        	mp3.setVolume(0);
        	mute=true;        		
    	}
    }






    //PlayList Frame Buttons settings are started from here//

   
    
    if(ae.getSource() == cancel)					//Cancel button of 2nd Frame //
    {
        f2.dispose();
    }

    
    

    if(ae.getSource() == open_Playlist)						//Open-Playlist button of 2nd Frame //
    {
    	eventForOpenPlayList();
    }
    
    
    
    
    if (ae.getSource() == add_To_Playlist) 						//Add-To-Playlist button of 2nd Frame //
    {
        select_Any_Playlist();
    }
    
    
    
    if (ae.getSource() == add_new_playlist ) 						//Add-To-Playlist button of 2nd Frame //
    {
    	add_New_PlayList_Form_Settings();
    }
    
    
    
    
    if(ae.getSource() == delete_playlist)					//Delete-Playlist button of 2nd Frame //
    {
    	eventForDeletePlayList();
    }
    
    
    
    
    if(ae.getSource() == favourites_Playlist )				//Favourite-Playlist button of 2nd Frame To Play it //
    {
    	eventForFavouritePlayList();
    }

    
    
    
    
    
    // Create New Playlist Form Components are starting from here //
    
    
    
    if(ae.getSource() == cancel_Creation )
    {
    	f3.dispose();
    }
    
    
    
    if(ae.getSource() == create )
    {
    	eventForCreation_Of_New_Playlist();
    }
    
    
    
    
    
    
    //Select any playlist components events are started from here //
    
    
    if(ae.getSource() == select)
    {
    	event_for_selecting_Playlist();
    }
    
    
    
    if(ae.getSource() == cancel_Selection)
    {
    	f4.dispose();
    }
    

    //Select any playlist frame and components ettings are defined here //
    
    
    
    
    
    //Connect To Server frame and components ettings are defined here //
    
    
    if(ae.getSource() == stream_Music)
    {
        connect_To_Server_Frame_Settings();
        t.start();
    }
    
    
    
    if(ae.getSource()==shuffle)
    {
        if(mp3.isStopped())
            {
                play_Mp3_Files_from_server();
            }
            else
            {
                JOptionPane.showMessageDialog(f5, "**Server Error** A Mp3 file is already running !");
            }
                
    }
    
    
    //Connect To Server frame and components ettings are ending here //
    



}			// Action-Performed method ending here //










}