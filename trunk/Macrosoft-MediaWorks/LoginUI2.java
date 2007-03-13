import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginUI2 extends JFrame implements ActionListener{

	
	private JButton createAccountBtn, loginBtn, forgotPasswordBtn;
	private JTextField usernameTB;
	private JPasswordField passwordTB;
	private JLabel usernameLabel, passwordLabel, loginLabel, dynamicLabel, title;
	private String dynamicText;
	private Dimension dim;

	
	private Component componentSetup() {
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.PAGE_AXIS));
		loginPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		
		loginLabel = new JLabel("Log into your account");
		loginLabel.setFont(new Font("Helvetica", Font.PLAIN, 16));
		loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		// Username/Password border panel
		JPanel usernamePasswordPanel = new JPanel();
		usernamePasswordPanel.setLayout(new BoxLayout(usernamePasswordPanel, BoxLayout.LINE_AXIS));
		usernamePasswordPanel.setBorder(BorderFactory.createEtchedBorder());
		usernamePasswordPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		usernamePasswordPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.PAGE_AXIS));	
		labelPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		labelPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.PAGE_AXIS));	
		fieldPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		fieldPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		
		usernameLabel = new JLabel("Username:");
		usernameTB = new JTextField(20);
		dim = usernameTB.getPreferredSize();
		usernameTB.setMinimumSize(dim);
		usernameTB.setMaximumSize(dim);
		usernameTB.setAlignmentX(Component.LEFT_ALIGNMENT);
		usernameTB.setAlignmentY(Component.TOP_ALIGNMENT);
		
		passwordLabel = new JLabel("Password:");
		passwordTB = new JPasswordField(20);
		dim = usernameTB.getPreferredSize();
		passwordTB.setMinimumSize(dim);
		passwordTB.setMaximumSize(dim);
		passwordTB.setAlignmentX(Component.LEFT_ALIGNMENT);
		passwordTB.setAlignmentY(Component.TOP_ALIGNMENT);
		
		labelPanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelPanel.add(usernameLabel);
		labelPanel.add(Box.createRigidArea(new Dimension(0,15)));
		labelPanel.add(passwordLabel);
		labelPanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		fieldPanel.add(Box.createRigidArea(new Dimension(0,10)));
		fieldPanel.add(usernameTB);
		fieldPanel.add(Box.createRigidArea(new Dimension(0,10)));
		fieldPanel.add(passwordTB);
		fieldPanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		usernamePasswordPanel.add(Box.createRigidArea(new Dimension(25,0)));
		usernamePasswordPanel.add(labelPanel);
		usernamePasswordPanel.add(Box.createRigidArea(new Dimension(10,0)));
		usernamePasswordPanel.add(fieldPanel);
		usernamePasswordPanel.add(Box.createRigidArea(new Dimension(25,0)));
		
		loginBtn = new JButton("Login");
		dim = loginBtn.getPreferredSize();
		loginBtn.setMinimumSize(dim);
		loginBtn.setMaximumSize(dim);
		loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
		loginBtn.setActionCommand("Login");
		loginBtn.addActionListener(this);
		
		createAccountBtn = new JButton("Create an Account");
		dim = createAccountBtn.getPreferredSize();
		createAccountBtn.setMinimumSize(dim);
		createAccountBtn.setMaximumSize(dim);
		createAccountBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		createAccountBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
		createAccountBtn.setActionCommand("Create an Account");
		createAccountBtn.addActionListener(this);
		
		
		JPanel topLogin = new JPanel();
		topLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
		topLogin.setAlignmentY(Component.CENTER_ALIGNMENT);
		topLogin.setLayout(new BoxLayout(topLogin, BoxLayout.PAGE_AXIS));
		topLogin.add(loginLabel);
		topLogin.add(loginPanel);
		
		JPanel bottomLogin = new JPanel();
		bottomLogin.setAlignmentX(Component.RIGHT_ALIGNMENT);
		bottomLogin.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		bottomLogin.setLayout(new BoxLayout(bottomLogin, BoxLayout.LINE_AXIS));
		bottomLogin.add(createAccountBtn);
		bottomLogin.add(loginBtn);
		
		loginPanel.add(Box.createRigidArea(new Dimension(0,15)));
		loginPanel.add(usernamePasswordPanel);
		loginPanel.add(Box.createRigidArea(new Dimension(0,5)));
		loginPanel.add(bottomLogin);
		
		
		title = new JLabel("Macrosoft Personal Library");
		title.setFont(new Font("Helvetica", Font.BOLD, 24));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.PAGE_AXIS));
		titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		titlePanel.setAlignmentY(Component.CENTER_ALIGNMENT);
		titlePanel.add(title);
		titlePanel.add(Box.createRigidArea(new Dimension(0,25)));
		titlePanel.add(topLogin);
		
		dynamicText = "";
		dynamicLabel = new JLabel(dynamicText);
		dynamicLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		dynamicLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		JPanel componentsPanel = new JPanel();
		componentsPanel.setLayout(new BoxLayout(componentsPanel, BoxLayout.PAGE_AXIS));
		componentsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		componentsPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
		componentsPanel.add(titlePanel);
		componentsPanel.add(Box.createRigidArea(new Dimension(0,50)));
		componentsPanel.add(dynamicLabel);
		
		
		JPanel primaryPanel = new JPanel();
		primaryPanel.setLayout(new BoxLayout(primaryPanel, BoxLayout.PAGE_AXIS));
		primaryPanel.add(Box.createRigidArea(new Dimension(0,75)));
		primaryPanel.add(componentsPanel);
		return primaryPanel;
	}
	
	public static void windowLookAndFeel(){
	    try{
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    }catch (Exception e) {
	        System.out.println("Look and Feel error: " + e);
	    }
	}	
	
	public void createAndShowGUI() {
		// Create and set up the window
		windowLookAndFeel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Macrosoft Media Works");
		setResizable(false);
		add(componentSetup());
		pack();
		setSize(720,540);
		setVisible(true);
	}
	
	
	
	public void actionPerformed(ActionEvent e){
		ControllerClass controller = new ControllerClass();
		if (e.getActionCommand().equals("Login")) {
			dispose();
			controller.createAccountFrame();
		
		}
		
		if (e.getActionCommand().equals("Create an Account")) {
			
			
		}
		
		
		
		
	}
	public static void main(String[] args){
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				LoginUI2 loginUI = new LoginUI2();
				loginUI.createAndShowGUI();
			}
		});
	}
}
