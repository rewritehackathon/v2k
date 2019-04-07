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
import javax.swing.JTextField;

public class AddUW extends Main{
	JFrame f;
	
	JPanel TopPanel;
	JPanel InputPanel;
		JPanel FNamePanel;
		JPanel LNamePanel;  
		JPanel DeptPanel;
		JPanel UsernamePanel;
		JPanel PasswordPanel;
		JPanel CPasswordPanel;
	JPanel SubmitPanel;
	JButton Submit;
	JButton Home;
	JLabel TopLabel;
		JLabel FNameLabel;
		JLabel LNameLabel;
		JLabel DeptLabel;
		JLabel UsernameLabel;
		JLabel PasswordLabel;
		JLabel CPasswordLabel;
	JTextField fName;
	JTextField lName;
	JComboBox<String> dept;
	JTextField username;
	JTextField password;
	JTextField cpassword;  //confirm password
	
	UListener fnameListener;
	UListener lnameListener;
	UListener deptListener;
	UListener unListener;
	UListener pListener;
	UListener homeListener;
	UListener submitListener;
	
	
	
	AddUW(JFrame f){
		this.f = f;
		
		//Setting top panel
			TopPanel  = new JPanel();
			TopPanel.setPreferredSize(new Dimension(1000, 200));
			TopPanel.setBackground(Color.white);
			TopLabel = new JLabel();
			Home = new JButton("Home Page");
			Home.setPreferredSize(new Dimension(100,70));
			Home.setBackground(Color.gray);
			homeListener = new UListener();
			Home.addActionListener(homeListener);
			TopLabel.setText("      Add the underwriter information below");
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
				FNamePanel = new JPanel();
				FNamePanel.setPreferredSize(new Dimension(800, 40));
				FNamePanel.setBackground(Color.white);
				FNameLabel = new JLabel();
				FNameLabel.setText("First Name:");
				FNameLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
				FNameLabel.setPreferredSize(new Dimension(150,30));
				fName = new JTextField();
				fName.setPreferredSize(new Dimension(400,30));
				fnameListener = new UListener();
				fName.addActionListener(fnameListener);
				FNamePanel.add(FNameLabel);
				FNamePanel.add(fName);
				
				LNamePanel = new JPanel();
				LNamePanel.setPreferredSize(new Dimension(800, 40));
				LNamePanel.setBackground(Color.white);
				LNameLabel = new JLabel();
				LNameLabel.setText("Last Name:");
				LNameLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
				LNameLabel.setPreferredSize(new Dimension(150,30));
				lName = new JTextField();
				lName.setPreferredSize(new Dimension(400,30));
				lnameListener = new UListener();
				lName.addActionListener(lnameListener);
				LNamePanel.add(LNameLabel);
				LNamePanel.add(lName);
				
				DeptPanel = new JPanel();
				DeptPanel.setPreferredSize(new Dimension(800, 40));
				DeptPanel.setBackground(Color.white);
				DeptLabel = new JLabel();
				DeptLabel.setText("Department:");
				DeptLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
				DeptLabel.setPreferredSize(new Dimension(150,30));
				dept = new JComboBox<String>();
				dept.setPreferredSize(new Dimension(400,30));
				deptListener = new UListener();
				dept.addActionListener(deptListener);
				DeptPanel.add(DeptLabel);
				DeptPanel.add(dept);
				
				UsernamePanel = new JPanel();
				UsernamePanel.setPreferredSize(new Dimension(800, 40));
				UsernamePanel.setBackground(Color.white);
				UsernameLabel = new JLabel();
				UsernameLabel.setText("Username:");
				UsernameLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
				UsernameLabel.setPreferredSize(new Dimension(150,30));
				username = new JTextField();
				username.setPreferredSize(new Dimension(400,30));
				unListener = new UListener();
				username.addActionListener(unListener);
				UsernamePanel.add(UsernameLabel);
				UsernamePanel.add(username);
				
				PasswordPanel = new JPanel();
				PasswordPanel.setPreferredSize(new Dimension(800, 40));
				PasswordPanel.setBackground(Color.white);
				PasswordLabel = new JLabel();
				PasswordLabel.setText("Password:");
				PasswordLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
				PasswordLabel.setPreferredSize(new Dimension(150,30));
				password = new JTextField();
				password.setPreferredSize(new Dimension(400,30));
				pListener = new UListener();
				password.addActionListener(pListener);
				PasswordPanel.add(PasswordLabel);
				PasswordPanel.add(password);
				
				CPasswordPanel = new JPanel();
				CPasswordPanel.setPreferredSize(new Dimension(800, 40));
				CPasswordPanel.setBackground(Color.white);
				CPasswordLabel = new JLabel();
				CPasswordLabel.setText("Confirm:");
				CPasswordLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
				CPasswordLabel.setPreferredSize(new Dimension(150,30));
				cpassword = new JTextField();
				cpassword.setPreferredSize(new Dimension(400,30));
				cpassword.addActionListener(pListener);
				CPasswordPanel.add(CPasswordLabel);
				CPasswordPanel.add(cpassword);
				
				
				//adding all the individual parts to form
				InputPanel.add(FNamePanel);
				InputPanel.add(LNamePanel);
				InputPanel.add(DeptPanel);
				InputPanel.add(UsernamePanel);
				InputPanel.add(PasswordPanel);
				InputPanel.add(CPasswordPanel);

			//Setting Submit Panel
			SubmitPanel = new JPanel();
			SubmitPanel.setPreferredSize(new Dimension(600, 500));
			SubmitPanel.setBackground(Color.white);
			Submit = new JButton("Submit");
			Submit.setFont(new Font("Arial", Font.BOLD, 30));
			Submit.setForeground(Color.white);
			Submit.setPreferredSize(new Dimension(400,100));
			Submit.setBackground(Color.blue);
			submitListener = new UListener();
			Submit.addActionListener(submitListener);
			SubmitPanel.add(Submit);
			
			//Adding all Panels
			setBackground(Color.white);
			add(TopPanel);
			add(InputPanel);
			add(SubmitPanel);
			
			
			//Add latest dept names to combobox
			ArrayList<String> temp = new ArrayList<String>();
			try {
				temp = getS("dept_name","dept");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			addtocomboboxS(dept, temp);
	}
	
	
	//Inserting UW information into the database
	public static void insertUW(String fn, String ln, Integer dID, String un, String pd, String cpd) throws Exception{
		try {
			Connection con = getConnection();
			PreparedStatement inserted = con.prepareStatement("INSERT INTO underwriter (first_name, last_name, dept_id, username, password) VALUES ('"+fn+"', '"+ln+"', '"+dID+"', '"+un+"', '"+pd+"')");
			inserted.executeUpdate();
		} catch(Exception e) {System.out.println(e);}
		finally {
			System.out.println("Insert into UW Completed");
		}
	}
	
	//Add listeners for submit and home button
	public class UListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == Home) {
				//go to home screen
				Home panel = new Home(f);
				f.setContentPane(panel); 
				f.setVisible(true);
			}
			if(event.getSource() == Submit) {
				//submit and go to home screen
				String tempf = fName.getText();
				String templ = lName.getText();
				String tempd = (String) dept.getSelectedItem();
				String tempu = username.getText();
				String tempp = password.getText();
				String tempcp = cpassword.getText();
				
				ArrayList<Integer> tempdlist = null;
				try {
					tempdlist = getI("dept_id","dept WHERE dept_name = '" + tempd + "'");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Integer tempd2 = tempdlist.get(0);
				
				try {
					insertUW(tempf,templ,tempd2,tempu,tempp,tempcp);
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
	
	//Get dept names for checkbox
		public static ArrayList<String> get() throws Exception{
			try {
				Connection con = getConnection();
				PreparedStatement statement = con.prepareStatement("SELECT dept_name FROM dept");
				
				ResultSet result = statement.executeQuery();
				ArrayList<String> array = new ArrayList<String>();
				while(result.next()) {
					System.out.println(result.getString("company_name"));
					array.add(result.getString("company_name"));
				}
				System.out.println("All Clients added");
				return array;
			}catch(Exception e){System.out.println(e);
			return null;
			}
		}
	

	public static void main(String[] args) {
		JFrame frame = new JFrame("Add an Underwriter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AddUW panel = new AddUW(frame); 
		frame.getContentPane().add(panel);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(false); //to take out the top bar
		frame.pack(); 
		frame.setVisible(true);

	}

}
