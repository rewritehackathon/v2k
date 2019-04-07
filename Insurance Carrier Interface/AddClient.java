package database;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddClient extends Main{
	JFrame f;
	
	JPanel TopPanel;
	JPanel InputPanel;
		JPanel ClientPanel;
		JPanel EmailPanel;
		JPanel PhonePanel;     
	JPanel SubmitPanel;
	JButton Submit;
	JButton Home;
	JLabel TopLabel;
		JLabel ClientLabel;
		JLabel EmailLabel;
		JLabel PhoneLabel;
		JTextField client;
		JTextField email;
		JTextField phone;
	CListener homeListener;
		CListener clientListener;
		CListener emailListener;
		CListener phoneListener;
		CListener submitListener;
	
	//Constuctor
	AddClient(JFrame f){
		this.f = f;
		
		//Setting top panel
			TopPanel  = new JPanel();
			TopPanel.setPreferredSize(new Dimension(1000, 200));
			TopPanel.setBackground(Color.white);
			TopLabel = new JLabel();
			Home = new JButton("Home Page");
			Home.setPreferredSize(new Dimension(100,70));
			Home.setBackground(Color.gray);
			homeListener = new CListener();
			Home.addActionListener(homeListener);
			TopLabel.setText("      Add the client information below");
			TopLabel.setFont(new Font("Century Gothic", Font.BOLD, 36));
			TopLabel.setPreferredSize(new Dimension(800,175));
			TopPanel.add(Home);
			TopPanel.add(TopLabel);
			
			//Setting Input Panel
			InputPanel = new JPanel();
			InputPanel.setPreferredSize(new Dimension(800, 300));
			InputPanel.setBackground(Color.white);
			
			//******Can add in underwriter name and department here from database login
				//setting up all form parts
				ClientPanel = new JPanel();
				ClientPanel.setPreferredSize(new Dimension(800, 40));
				ClientPanel.setBackground(Color.white);
				ClientLabel = new JLabel();
				ClientLabel.setText("Client Name:");
				ClientLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
				ClientLabel.setPreferredSize(new Dimension(150,30));
				client = new JTextField();
				client.setPreferredSize(new Dimension(400,30));
				clientListener = new CListener();
				client.addActionListener(clientListener);
				ClientPanel.add(ClientLabel);
				ClientPanel.add(client);
				
				EmailPanel = new JPanel();
				EmailPanel.setPreferredSize(new Dimension(800, 40));
				EmailPanel.setBackground(Color.white);
				EmailLabel = new JLabel();
				EmailLabel.setText("Email:");
				EmailLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
				EmailLabel.setPreferredSize(new Dimension(150,30));
				email = new JTextField();
				email.setPreferredSize(new Dimension(400,30));
				EmailPanel.add(EmailLabel);
				EmailPanel.add(email);
				
				PhonePanel = new JPanel();
				PhonePanel.setPreferredSize(new Dimension(800, 40));
				PhonePanel.setBackground(Color.white);
				PhoneLabel = new JLabel();
				PhoneLabel.setText("Phone #:");
				PhoneLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
				PhoneLabel.setPreferredSize(new Dimension(150,30));
				phone = new JFormattedTextField();
				phone.setPreferredSize(new Dimension(400,30));
				PhonePanel.add(PhoneLabel);
				PhonePanel.add(phone);
				
				//adding all the individual parts to form
				InputPanel.add(ClientPanel);
				InputPanel.add(EmailPanel);
				InputPanel.add(PhonePanel);

			//Setting Submit Panel
			SubmitPanel = new JPanel();
			SubmitPanel.setPreferredSize(new Dimension(600, 500));
			SubmitPanel.setBackground(Color.white);
			Submit = new JButton("Submit");
			Submit.setFont(new Font("Arial", Font.BOLD, 30));
			Submit.setForeground(Color.white);
			Submit.setPreferredSize(new Dimension(400,100));
			Submit.setBackground(Color.blue);
			submitListener = new CListener();
			Submit.addActionListener(submitListener);
			SubmitPanel.add(Submit);
			
			
			//Adding all Panels
			setBackground(Color.white);
			add(TopPanel);
			add(InputPanel);
			add(SubmitPanel);	
		
	}
	

	
	//Inserting Client information into the database
	public static void insertClient(String cn, String em, String p) throws Exception{
		try {
			Connection con = getConnection();
			PreparedStatement inserted = con.prepareStatement("INSERT INTO client (company_name, email, number) VALUES ('"+cn+"', '"+em+"', '"+p+"')");
			inserted.executeUpdate();
		} catch(Exception e) {System.out.println(e);}
		finally {
			System.out.println("Insert into Client Completed");
		}
	}
	
	//Listeners
	public class CListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == Home) {
				//go to home screen
				Home panel = new Home(f);
				f.setContentPane(panel); 
				f.setVisible(true);
			}
			if(event.getSource() == Submit) {
				//submit and go to home screen
				String tempc = client.getText();
				String tempe = email.getText();
				String tempp = phone.getText();
				try {
					insertClient(tempc,tempe,tempp);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Home panel = new Home(f);
				f.setContentPane(panel); 
				f.setVisible(true);
			}
		}
	}
	
	

	public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame("Add a Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AddClient panel = new AddClient(frame); 
		frame.getContentPane().add(panel);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(false); //to take out the top bar
		frame.pack(); 
		frame.setVisible(true);

	}

}
