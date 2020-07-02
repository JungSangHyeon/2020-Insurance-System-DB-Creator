package component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

public class MySQL {
	
	// Static Attribute
	private final static String URL = "jdbc:mysql://localhost/ssinarack?serverTimezone=UTC&useSSL=false";
	private final static int TABLE_ALREADY_EXIST = 1050;
	private final static int DUPLICATE_COLUMN_NAME = 1060;
	public enum FKAdditional {
		OnDeleteSetNull("on delete set null"),
		OnDeleteCascade("on delete cascade"),
		OnDeleteNoAction("on delete no action"),		
		OnUpdateCascade("on update cascade");
			
		private String s;
		private FKAdditional(String s) {this.s=s;}
		public String getString() {return this.s;}
	}
	
	// Attribute
	private java.sql.Statement statement;
	
	// Component
	private Vector<String> history;
	
	// Initialize Time Use
	public void login(String user, String pw) {
		try { // Set Attribute
			Connection connect = DriverManager.getConnection(MySQL.URL, user, pw);
			this.statement = connect.createStatement();
		} catch (SQLException e) {e.printStackTrace();}
		
		// Create Component
		 this.history = new Vector<String>();
	}
	
	// Any Time Use
	public void useDB(String dbName) {try {this.statement.executeQuery("use "+dbName+";");} catch (SQLException e) {e.printStackTrace();}}
	public void createTable(TableInfo tableInfo) {
		String instruction = tableInfo.getCreateInfo();
		try {this.history.add(instruction); this.statement.executeUpdate(instruction);}
		catch(SQLException e) {if(e.getErrorCode()==TABLE_ALREADY_EXIST) {
			System.err.println(e.getLocalizedMessage()+". Create Table Ignored.");}else{e.printStackTrace();}
		}
	}
	public void addColumn(TableInfo tableInfo, AttributeInfo attributeInfo) {
		String instruction = "Alter table "+ tableInfo.getName()+" add column "+attributeInfo.getCreateInfo()+";";
		try {this.history.add(instruction); this.statement.executeUpdate(instruction);}
		catch(SQLException e) {
			if(e.getErrorCode()==DUPLICATE_COLUMN_NAME) {System.err.println(e.getLocalizedMessage()+". Add Colum Ignored.");}
			else{e.printStackTrace();}
		}
	}
	public void addForeignKey(TableInfo fkTable, AttributeInfo fkAttr, TableInfo pkTable, AttributeInfo pkAttr, FKAdditional...additionals) {
		String instruction = "Alter table "+fkTable.getName()+" ADD foreign key ("+fkAttr.getName()+") references "+pkTable.getName()+"("+pkAttr.getName()+")";
		for(FKAdditional additional : additionals) {instruction += (" "+additional.getString());}
		instruction+=";";
		try {this.history.add(instruction); this.statement.executeUpdate(instruction);}
		catch(SQLException e) {e.printStackTrace();}
	}
	public void saveHistoryAsFile() {
		try {
			SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd-HH-mm-ss");
			String nowTime = format.format(Calendar.getInstance().getTime());
			FileWriter attendanceResultWriter = new FileWriter(new File("history/"+nowTime+" History.txt"));
			for(String instruction : this.history) {attendanceResultWriter.write(instruction + "\r\n");}
			attendanceResultWriter.flush(); attendanceResultWriter.close();
		} catch (IOException e) {e.printStackTrace();}
	}
}
