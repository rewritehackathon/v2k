package database;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Home extends Main{
	
	JPanel logoPanel;
	JPanel buttonPanel;
	JPanel logOutPanel;
	JButton AddPolicy;
	JButton AddClaim;
	JButton AddClient;
	JButton AddUW;
	JButton EditPolicy;
	JButton EditClaim;
	JButton EditClient;
	JButton EditUW;
	JButton LogOut;
	JLabel welcomeLabel;
	JFrame f; // frame will be passed through to next screen
	
	//Constructor for Home Login Page
	Home(JFrame f){
		this.f =f;
		
		//Set Logo Panel
		logoPanel = new JPanel();
		logoPanel.setPreferredSize(new Dimension(1000, 50));
		logoPanel.setBackground(Color.white);	
		welcomeLabel = new JLabel();
		welcomeLabel.setText("Welcome to the Policy database.");
		welcomeLabel.setFont(new Font("Century Gothic", Font.BOLD, 36));
		logoPanel.add(welcomeLabel);

		
		//Set Button Panel
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(700, 500));
		buttonPanel.setBackground(Color.white);
			//Initialize
			AddPolicy = new JButton("Add Policy");
			AddClaim = new JButton("Add Claim");
			AddClient = new JButton("Add Client");
			AddUW = new JButton("Add Underwriter");
			EditPolicy = new JButton("Edit Policy");
			EditClaim = new JButton("Edit Claim");
			EditClient = new JButton("Edit Client");
			EditUW = new JButton("Edit Underwriter");
			//Dimensions and colors
			AddPolicy.setPreferredSize(new Dimension(150,150));
			AddClaim.setPreferredSize(new Dimension(150,150));
			AddClient.setPreferredSize(new Dimension(150,150));
			AddUW.setPreferredSize(new Dimension(150,150));
			EditPolicy.setPreferredSize(new Dimension(150,150));
			EditClaim.setPreferredSize(new Dimension(150,150));
			EditClient.setPreferredSize(new Dimension(150,150));
			EditUW.setPreferredSize(new Dimension(150,150));
			
			AddPolicy.setBackground(Color.black);
			AddClaim.setBackground(Color.black);
			AddClient.setBackground(Color.black);
			AddUW.setBackground(Color.black);
			EditPolicy.setBackground(Color.black);
			EditClaim.setBackground(Color.black);
			EditClient.setBackground(Color.black);
			EditUW.setBackground(Color.black);
			
			AddPolicy.setForeground(Color.white);
			AddClaim.setForeground(Color.white);
			AddClient.setForeground(Color.white);
			AddUW.setForeground(Color.white);
			EditPolicy.setForeground(Color.white);
			EditClaim.setForeground(Color.white);
			EditClient.setForeground(Color.white);
			EditUW.setForeground(Color.white);
			
			//Listeners
			ScreenListener AddPolicyListener = new ScreenListener();
			ScreenListener AddClaimListener = new ScreenListener();
			ScreenListener AddClientListener = new ScreenListener();
			ScreenListener AddUWListener = new ScreenListener();
			ScreenListener EditPolicyListener = new ScreenListener();
			ScreenListener EditClaimListener = new ScreenListener();
			ScreenListener EditClientListener = new ScreenListener();
			ScreenListener EditUWListener = new ScreenListener();
			AddPolicy.addActionListener(AddPolicyListener);
			AddClaim.addActionListener(AddClaimListener);
			AddClient.addActionListener(AddClientListener);
			AddUW.addActionListener(AddUWListener);
			EditPolicy.addActionListener(EditPolicyListener);
			EditClaim.addActionListener(EditClaimListener);
			EditClient.addActionListener(EditClientListener);
			EditUW.addActionListener(EditUWListener);
			
		buttonPanel.add(AddPolicy);
		buttonPanel.add(AddClaim);
		buttonPanel.add(AddClient);
		buttonPanel.add(AddUW);
		buttonPanel.add(EditPolicy);
		buttonPanel.add(EditClaim);
		buttonPanel.add(EditClient);
		buttonPanel.add(EditUW);

		//Set Logout Panel
		logOutPanel = new JPanel();
		logOutPanel.setPreferredSize(new Dimension(1000, 50));
		logOutPanel.setBackground(Color.black);
		LogOut = new JButton("Log Out");
		LogOut.setBackground(Color.DARK_GRAY);
		LogOut.setForeground(Color.white);
		LogOut.setPreferredSize(new Dimension(100,40));
		logOutPanel.add(LogOut);
		
		//Set Back Panel
		setBackground(Color.white);
		add(logoPanel);
		add(buttonPanel);
		add(logOutPanel);
	}
	
	public class ScreenListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == AddPolicy) {
				//go to add policy screen
				AddPolicy panel = new AddPolicy(f);
				f.setContentPane(panel); 
				f.setVisible(true);
			}
			if(event.getSource() == AddClaim) {
				//go to add claim
				AddClaim panel = new AddClaim(f);
				f.setContentPane(panel); 
				f.setVisible(true);
			}
			if(event.getSource() == AddClient) {
				//go to add client
				AddClient panel = new AddClient(f);
				f.setContentPane(panel); 
				f.setVisible(true);
			}
			if(event.getSource() == AddUW) {
				//go to add uw 
				AddUW panel = new AddUW(f);
				f.setContentPane(panel); 
				f.setVisible(true);
			}
			if(event.getSource() == AddPolicy) {
				//go to add policy screen
				AddPolicy panel = new AddPolicy(f);
				f.setContentPane(panel); 
				f.setVisible(true);
			}
			if(event.getSource() == EditClaim) {
				//go to add claim
				EditClaim panel = new EditClaim(f);
				f.setContentPane(panel); 
				f.setVisible(true);
			}
			if(event.getSource() == EditClient) {
				//go to add client
				AddClient panel = new AddClient(f);
				f.setContentPane(panel); 
				f.setVisible(true);
			}
			if(event.getSource() == EditUW) {
				//go to add uw 
				AddUW panel = new AddUW(f);
				f.setContentPane(panel); 
				f.setVisible(true);
			}
	
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Home Screen"); //What it says at top of window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Home panel = new Home(frame); 
		frame.getContentPane().add(panel);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(false);
		frame.pack(); frame.setVisible(true);
	}

}

