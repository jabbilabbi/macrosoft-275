import java.awt.*;

import javax.swing.*;


public class Frame {
	static AddScreenUI addScreenUI = new AddScreenUI();
	static BrowseUI browseUI = new BrowseUI();
	static CreateAccountUI createAccountUI = new CreateAccountUI();
	static LoginUI loginUI = new LoginUI();
	static MainScreenUI mainScreenUI = new MainScreenUI();

	private Dimension pane_size;
	private Frame frame = new Frame();
	
	public void setPaneSize(int x, int y){
		frame.pane_size = new Dimension(x, y);
	}
	
	public Dimension getPaneSize(){
		return frame.pane_size;
	}
	
	
	public static void main(String[] args) {
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
		
		JPanel addScreenUI = new JPanel();
		JPanel browseUI= new JPanel();
		JPanel createAccountUI = new JPanel();
		JPanel loginUI = new JPanel();
		JPanel mainScreenUI = new JPanel();
		
		
		JPanel primary = new JPanel();
		
		JFrame frame = new JFrame("Macrosoft Media Works");
	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(primary);
		frame.pack();


		// Size and display the window.
		frame.setVisible(true);
		
	            }
	        });
	}
	

}

