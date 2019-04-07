package database;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import database.AddClient.CListener;

public class AddPolicy extends Main {
	JPanel TopPanel;
	JPanel InputPanel;
		JPanel ClientPanel;
		JPanel TypePanel;
		JPanel StartPanel;  //start date
		JPanel EndPanel;    //end date
	JPanel SubmitPanel;
	JButton Submit;
	JButton Home;
	JLabel TopLabel;
	JLabel ClientLabel;
	JLabel TypeLabel;
	JLabel StartLabel;
	JLabel EndLabel;
	JComboBox<String> client;
	JComboBox<String> type;
	JFormattedTextField startDate;
	JFormattedTextField endDate;
	JFrame f;
	PListener homeListener;
	PListener submitListener;
	PListener clientListener;
	PListener typeListener;
	PListener startListener;
	PListener endListener;
	
	//Constructor
	AddPolicy(JFrame f){
		this.f = f;

		
		//Setting top panel
		TopPanel  = new JPanel();
		TopPanel.setPreferredSize(new Dimension(1000, 200));
		TopPanel.setBackground(Color.white);
		TopLabel = new JLabel();
		Home = new JButton("Home Page");
		Home.setPreferredSize(new Dimension(100,70));
		Home.setBackground(Color.gray);
		homeListener = new PListener();
		Home.addActionListener(homeListener);
		TopLabel.setText("      Add the policy information below");
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
			ClientLabel.setText("Client");
			ClientLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
			ClientLabel.setPreferredSize(new Dimension(150,30));
			client = new JComboBox<String>();
			client.setPreferredSize(new Dimension(400,30));
			clientListener = new PListener();
			client.addActionListener(clientListener);
			ClientPanel.add(ClientLabel);
			ClientPanel.add(client);
			
			TypePanel = new JPanel();
			TypePanel.setPreferredSize(new Dimension(800, 40));
			TypePanel.setBackground(Color.white);
			TypeLabel = new JLabel();
			TypeLabel.setText("Policy Type");
			TypeLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
			TypeLabel.setPreferredSize(new Dimension(150,30));
			type = new JComboBox<String>();
			type.setPreferredSize(new Dimension(400,30));
			typeListener = new PListener();
			type.addActionListener(typeListener);;
			TypePanel.add(TypeLabel);
			TypePanel.add(type);
			
			StartPanel = new JPanel();
			StartPanel.setPreferredSize(new Dimension(800, 40));
			StartPanel.setBackground(Color.white);
			StartLabel = new JLabel();
			StartLabel.setText("Start Date");
			StartLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
			StartLabel.setPreferredSize(new Dimension(150,30));
			startDate = new JFormattedTextField();
			startDate.setPreferredSize(new Dimension(400,30));
			startListener = new PListener();
			startDate.addActionListener(startListener);
			StartPanel.add(StartLabel);
			StartPanel.add(startDate);
			
			EndPanel = new JPanel();
			EndPanel.setPreferredSize(new Dimension(800, 40));
			EndPanel.setBackground(Color.white);
			EndLabel = new JLabel();
			EndLabel.setText("End Date");
			EndLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
			EndLabel.setPreferredSize(new Dimension(150,30));
			endDate = new JFormattedTextField();
			endDate.setPreferredSize(new Dimension(400,30));
			endListener = new PListener();
			endDate.addActionListener(endListener);
			EndPanel.add(EndLabel);
			EndPanel.add(endDate);
			
			//adding all the individual parts to form
			InputPanel.add(ClientPanel);
			InputPanel.add(TypePanel);
			InputPanel.add(StartPanel);
			InputPanel.add(EndPanel);

		//Setting Submit Panel
		SubmitPanel = new JPanel();
		SubmitPanel.setPreferredSize(new Dimension(600, 500));
		SubmitPanel.setBackground(Color.white);
		Submit = new JButton("Submit");
		Submit.setFont(new Font("Arial", Font.BOLD, 30));
		Submit.setForeground(Color.white);
		Submit.setPreferredSize(new Dimension(400,100));
		Submit.setBackground(Color.blue);
		submitListener = new PListener();
		Submit.addActionListener(submitListener);
		SubmitPanel.add(Submit);
		
		//Adding all Panels
		setBackground(Color.white);
		add(TopPanel);
		add(InputPanel);
		add(SubmitPanel);
		
		
		//adding clients and types
		type.addItem("Basic");
		type.addItem("Advanced");
		type.addItem("Premium");
		
		//Adding current clients to Client combo box
		ArrayList<String> temp = new ArrayList<String>();
		try {
			temp = getS("company_name","client");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		addtocomboboxS(client, temp);
	}
	
	
	//Inserting the user's Policy information into the database
	public static void insertPolicy(Integer cid, Integer uid, String ptype, String st, String en) throws Exception{
		try {
			Connection con = getConnection();
			PreparedStatement inserted = con.prepareStatement("INSERT INTO policy (client_id, uw_id, policy_type, start_date, end_date) VALUES ('"+cid+"', '"+uid+"', '"+ptype+"', '"+st+"', '"+en+"')");
			inserted.executeUpdate();
		} catch(Exception e) {System.out.println(e);}
		finally {
			System.out.println("Insert into policy completed");
		}
	}
	
	
	//Button Listeners for submit and home button
	public class PListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == Home) {
				//go to home screen
				Home panel = new Home(f);
				f.setContentPane(panel); 
				f.setVisible(true);
			}
			if(event.getSource() == Submit) {
				//submit information and go to home screen
				String tempc = (String) client.getSelectedItem();
				String tempt = (String) type.getSelectedItem();
				String temps = startDate.getText();
				String tempend = endDate.getText();
				try {
					insertPolicy(1,2,tempt,temps,tempend);
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
	
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Add a Policy");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AddPolicy panel = new AddPolicy(frame); 
		frame.getContentPane().add(panel);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(false); //to take out the top bar
		frame.pack(); 
		frame.setVisible(true);

	}

}
