package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class Main extends JPanel{

	public static void main(String[] args) throws Exception {
		//Testing Get Connection function
		getConnection();
	}
	//Creating reused functions in this parent class of all the screens
	
	//Connect to database
	public static Connection getConnection() throws Exception{
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/policies";
			String username = "root";
			String password = "karen";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url,username,password);
			System.out.println("Connected");
			return conn;
		} catch(Exception e){System.out.println(e);}
		System.out.println("Not connected");
		return null;
	}
	
	//Get latest information from database for String combo boxes
	public static ArrayList<String> getS(String attribute, String table) throws Exception{
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT " + attribute + " FROM " + table);
			
			ResultSet result = statement.executeQuery();
			ArrayList<String> array = new ArrayList<String>();
			while(result.next()) {
				System.out.println(result.getString(attribute));
				array.add(result.getString(attribute));
			}
			System.out.println("All " + attribute + " information added");
			return array;
		}catch(Exception e){System.out.println(e);
		return null;
		}
	}
	
	//Get latest information from database for Integer combo boxes
	public static ArrayList<Integer> getI(String attribute, String table) throws Exception{
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT " + attribute + " FROM " + table);
			
			ResultSet result = statement.executeQuery();
			ArrayList<Integer> array = new ArrayList<Integer>();
			while(result.next()) {
				System.out.println(attribute);
				array.add(result);
			}
			System.out.println("All " + attribute + " information added");
			return array;
		}catch(Exception e){System.out.println(e);
		return null;
		}
	}
	
	
	
/*	//Get latest information from database for Integer combo boxes
	public static ArrayList<Integer> getI(String attribute, String table) throws Exception{
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT " + attribute + " FROM " + table);
			
			ResultSet result = statement.executeQuery();
			ArrayList<Integer> array = new ArrayList<Integer>();
			while(result.next()) {
				System.out.println(result.getInt(attribute));
				array.add(result.getInt(attribute));
			}
			System.out.println("All " + attribute + " information added");
			return array;
		}catch(Exception e){System.out.println(e);
		return null;
		}
	}*/
	
	//Add combo box String items
	public void addtocomboboxS(JComboBox<String> box, ArrayList<String> list){
		for(int i = 0; i < list.size();i++) {
			box.addItem(list.get(i));
		}
	}
	
	//Add combo box Integer items
	public void addtocomboboxI(JComboBox<Integer> box, ArrayList<Integer> list){
		for(int i = 0; i < list.size();i++) {
			box.addItem(list.get(i));
		}
	}
}
