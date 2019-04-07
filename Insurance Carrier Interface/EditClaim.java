package database;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.AddClaim.CListener;

public class EditClaim extends Main {
	
	
JFrame f;
	
	//Panels
	JPanel TopPanel;
	JPanel SidePanel;
		JPanel Insured;
		JPanel Claim;
		JPanel OtherParties;
		JPanel PoliceReport;
		JPanel Receipts;
	JPanel InputPanel;
		JPanel PolicyNumPanel;
		JPanel RankPanel;
		JPanel OpenPanel;
		JPanel AmountPanel;
		JPanel CommentsPanel;
	JPanel SubmitPanel;
	
	//Labels & Buttons
	JLabel TopLabel;
		JLabel PolicyNumLabel;
		JLabel RankLabel;
		JLabel OpenLabel;
		JLabel AmountLabel;
		JLabel CommentsLabel;
	JLabel SideLabel;
		JLabel InsuredLabel;
		JLabel ClaimLabel;
		JLabel OtherPartiesLabel;
		JLabel PoliceReportLabel;
		JLabel ReceiptsLabel;
	JButton Submit;
	JButton Home;
	EListener HomeListener;
	EListener SubmitListener;
	
	//Input fields
	JComboBox<Integer> policynum;
	JComboBox<String> rank;
	JComboBox<String> open;
	JFormattedTextField amount;
	JTextField comments;
	
	EListener policyListener;
	EListener rankListener;
	EListener openListener;
	EListener amountListener;
	EListener commentsListener;
	
	
	EditClaim(JFrame f){
		this.f =f;
		
		//Initialize the panels
		TopPanel  = new JPanel();
		TopPanel.setPreferredSize(new Dimension(1000, 100));
		TopPanel.setBackground(Color.white);
		TopLabel = new JLabel();
		Home = new JButton("Home Page");
		Home.setPreferredSize(new Dimension(100,70));
		Home.setBackground(Color.gray);
		HomeListener = new EListener();
		Home.addActionListener(HomeListener);
		TopLabel.setText("      View claim information below");
		TopLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
		TopLabel.setPreferredSize(new Dimension(800,100));
		TopPanel.add(Home);
		TopPanel.add(TopLabel);
		
		
		InputPanel = new JPanel();
		InputPanel.setPreferredSize(new Dimension(500, 300));
		InputPanel.setBackground(Color.white);
		
		//******Can add in underwriter name and department here from database login
			//setting up all form parts
/*			PolicyNumPanel = new JPanel();
			PolicyNumPanel.setPreferredSize(new Dimension(800, 40));
			PolicyNumPanel.setBackground(Color.white);
			PolicyNumLabel = new JLabel();
			PolicyNumLabel.setText("Policy #:");
			PolicyNumLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
			PolicyNumLabel.setPreferredSize(new Dimension(150,30));
			policynum = new JComboBox<Integer>();
			policynum.setPreferredSize(new Dimension(400,30));
			policyListener = new CListener();
			policynum.addActionListener(policyListener);
			PolicyNumPanel.add(PolicyNumLabel);
			PolicyNumPanel.add(policynum);*/
			
/*			RankPanel = new JPanel();
			RankPanel.setPreferredSize(new Dimension(800, 40));
			RankPanel.setBackground(Color.white);
			RankLabel = new JLabel();
			RankLabel.setText("Rank:");
			RankLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
			RankLabel.setPreferredSize(new Dimension(150,30));
			rank = new JComboBox<String>();
			rank.setPreferredSize(new Dimension(400,30));
			rankListener = new CListener();
			rank.addActionListener(rankListener);
			RankPanel.add(RankLabel);
			RankPanel.add(rank);
			*/
			OpenPanel = new JPanel();
			OpenPanel.setPreferredSize(new Dimension(400, 40));
			OpenPanel.setBackground(Color.white);
			OpenLabel = new JLabel();
			OpenLabel.setText("Update Status:");
			OpenLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
			OpenLabel.setPreferredSize(new Dimension(150,30));
			open = new JComboBox<String>();
			open.setPreferredSize(new Dimension(200,30));
			openListener = new EListener();
			open.addActionListener(openListener);
			OpenPanel.add(OpenLabel);
			OpenPanel.add(open);
			
			AmountPanel = new JPanel();
			AmountPanel.setPreferredSize(new Dimension(400, 40));
			AmountPanel.setBackground(Color.white);
			AmountLabel = new JLabel();
			AmountLabel.setText("Amount:");
			AmountLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
			AmountLabel.setPreferredSize(new Dimension(150,30));
			NumberFormat amountFormat = NumberFormat.getNumberInstance();
			amount = new JFormattedTextField(amountFormat);
			amount.setForeground(Color.red);
			amount.setPreferredSize(new Dimension(200,30));
			amountListener = new EListener();
			amount.addActionListener(amountListener);
			AmountPanel.add(AmountLabel);
			AmountPanel.add(amount);
			
			CommentsPanel = new JPanel();
			CommentsPanel.setPreferredSize(new Dimension(400, 100));
			CommentsPanel.setBackground(Color.white);
			CommentsLabel = new JLabel();
			//CommentsLabel.setText("Comments for the Insured:");
			CommentsLabel.setText("<html>"+ "Comments for the Insured:" +"</html>");
			CommentsLabel.setFont(new Font("Century Gothic", Font.BOLD, 12));
			CommentsLabel.setPreferredSize(new Dimension(150,30));
			comments = new JFormattedTextField();
			comments.setPreferredSize(new Dimension(200,85));
			commentsListener = new EListener();
			comments.addActionListener(commentsListener);
			CommentsPanel.add(CommentsLabel);
			CommentsPanel.add(comments);
			
		//InputPanel.add(PolicyNumPanel);
		//InputPanel.add(RankPanel);
		InputPanel.add(OpenPanel);
		InputPanel.add(AmountPanel);
		InputPanel.add(CommentsPanel);
		
		//Setting Submit Panel
		SubmitPanel = new JPanel();
		SubmitPanel.setPreferredSize(new Dimension(600, 500));
		SubmitPanel.setBackground(Color.white);
		Submit = new JButton("Submit changes");
		Submit.setFont(new Font("Arial", Font.BOLD, 15));
		Submit.setForeground(Color.white);
		Submit.setPreferredSize(new Dimension(200,100));
		Submit.setBackground(Color.blue);
		SubmitListener = new EListener();
		Submit.addActionListener(SubmitListener);
		SubmitPanel.add(Submit);
		
		SidePanel = new JPanel();
		SidePanel.setPreferredSize(new Dimension(500, 400));
		SidePanel.setBackground(Color.white);
	

/*			//setting up all claim info
			Insured = new JPanel();
			Insured.setPreferredSize(new Dimension(300, 40));
			Insured.setBackground(Color.white);
			InsuredLabel = new JLabel();
			InsuredLabel.setText("<html>" + "Insured information: <br> Helen Bread Policy #: 0650" + "<html>");
			InsuredLabel.setFont(new Font("Century Gothic", Font.BOLD, 10));
			InsuredLabel.setPreferredSize(new Dimension(300,30));
			Insured.add(InsuredLabel);*/
			
			//setting up all claim info
			ClaimLabel = new JLabel();
			ClaimLabel.setText("<html>"+ "Insured information: <br> First Name: Helen <br> Last Name: Bread <br> Policy #: 123 <br> <br> Claim info: <br> Date: January 1, 2019 <br> Location: 728 Brock St. New York, NY 10001 <br> Type: Auto <br> Description: Hit from behind. Bumper damage. <br> <br> Other Party info: <br> First Name: Matt <br> Last Name: White <br> Carrier: Farm State <br> Policy #: 321 <br> Description: Ran over mailbox. Crashed into Helen." + "</html>");
			ClaimLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
			ClaimLabel.setPreferredSize(new Dimension(400,400));
			//Claim.add(ClaimLabel);
		
		
		//SidePanel.add(Insured);
		SidePanel.add(ClaimLabel);
		//Adding all Panels to screen
		setBackground(Color.white);
		add(TopPanel);
	
		add(InputPanel);
		add(SidePanel);
		add(SubmitPanel);
		
		//Adding ranks to combo box
/*		rank.addItem("Low Severity");
		rank.addItem("Moderate Severity");
		rank.addItem("High Severity");*/
		
		//Adding open status options to combo box
		open.addItem("Open");
		open.addItem("Closed");
		
/*		//Get current policy numbers from database
		ArrayList<Integer> tempP = new ArrayList<Integer>();
		try {
			tempP = getI("policy_id","policy");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addtocomboboxI(policynum, tempP);*/
	}
	
	
	//Inserting Claim information into the database
	public static void insertClaim(Integer pid, String r, Integer o, Integer a, String com) throws Exception{
		try {
			Connection con = getConnection();
			PreparedStatement inserted = con.prepareStatement("INSERT INTO claims VALUES (null, '"+pid+"', '"+r+"', '"+o+"', '"+a+"', '"+com+"')");
			inserted.executeUpdate();
		} catch(Exception e) {System.out.println(e);}
		finally {
			System.out.println("Insert into claims completed");
		}
	}
	
	

	//Add listeners for submit and home button	
		public class EListener implements ActionListener{
			public void actionPerformed(ActionEvent event){
				if(event.getSource() == Home) {
					//go to home screen
					Home panel = new Home(f);
					f.setContentPane(panel); 
					f.setVisible(true);
				}
				if(event.getSource() == Submit) {
					//submit information and go to home screen
/*					Integer tempp = (Integer) policynum.getSelectedItem();
					String tempr = (String) rank.getSelectedItem();
					String tempo = (String) open.getSelectedItem();
					Integer tempa = Integer.parseInt(amount.getText().replaceAll(",", ""));
					String tempcom = comments.getText();
					
					System.out.println(tempa);
					//Check status convert to boolean
					Integer tempo2 =0;
					if(tempo=="Open") {
						tempo2 = 1;
					}
					
					try {
						insertClaim(tempp,tempr,tempo2,tempa,tempcom);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					Home panel = new Home(f);
					f.setContentPane(panel); 
					f.setVisible(true);
				}
			}
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("View a Claim");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		EditClaim panel = new EditClaim(frame); 
		frame.getContentPane().add(panel);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(false); //to take out the top bar
		frame.pack(); 
		frame.setVisible(true);

	}

}
