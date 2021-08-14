package mainpackage;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import service_layer.Methods;

/**
 * 
 * @author Harsh Sharma 
 * 		   
 * 		   The main class to execute and run the whole application
 */
class Music_PLayer extends Methods {
	public static void main(String args[]) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Error : " + e.getMessage());
		}
		new Music_PLayer().proceed();
	}

}