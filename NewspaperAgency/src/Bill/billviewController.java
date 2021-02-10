package Bill;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
//import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import jdbcc.ConnectToDatabase;

public class billviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combo;

    @FXML
    private ListView<String> lstpaper;

    @FXML
    private ListView<String> lstprice;

    @FXML
    private TextField txtbill;
    LocalDate d5=java.time.LocalDate.now();
    long days;
    @FXML
    void dobill(ActionEvent event) 
    {
    	try {
    		pstmt=con.prepareStatement("select * from customer where mobile=?");
    		pstmt.setString(1, combo.getSelectionModel().getSelectedItem());
    		ResultSet table=pstmt.executeQuery();
           if(table.next())
           {
        	   java.sql.Date d3=table.getDate("doj");
				LocalDate d4=d3.toLocalDate();
				days = ChronoUnit.DAYS.between(d4,d5);
				String s=table.getString("price");
				String[] arrprice=s.split(",");
				float sum=0;
				for(String i:arrprice)
				{
					float f=Float.parseFloat(i);
					sum=sum+days*f;
				}
				if(sum<0)
					txtbill.setText("0");
				else
				txtbill.setText(String.valueOf(sum));
           }
    	} 
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void docombo(ActionEvent event) {

    }

    @FXML
    void dofetch(ActionEvent event)
    {
    	try
    	{
    		lstpaper.getItems().clear();
    		lstprice.getItems().clear();
			pstmt=con.prepareStatement("select * from customer where mobile=?");
			String item=combo.getSelectionModel().getSelectedItem();
			pstmt.setString(1, item);
			ResultSet table=pstmt.executeQuery();
			if(table.next())
			{
				String s=table.getString("paper");
				String[] arrpaper=s.split(",");
				for(String i:arrpaper)
					lstpaper.getItems().add(i);
				String s2=table.getString("price");
				String[] arrprice=s2.split(",");
				for(String i:arrprice)
					lstprice.getItems().add(i);
			}
		} 
    	catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void dosave(ActionEvent event)
    {
    	try {
			pstmt=con.prepareStatement("update customer set doj=? where mobile=?");
			java.sql.Date d= java.sql.Date.valueOf(d5.plusDays(1));
			pstmt.setDate(1,d);
			pstmt.setString(2, combo.getSelectionModel().getSelectedItem());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	try {
			pstmt=con.prepareStatement("insert into bill(mobile,days,dob,amount) values(?,?,?,?)");
			pstmt.setString(1,combo.getSelectionModel().getSelectedItem());
			pstmt.setInt(2,(int)days);
			pstmt.setDate(3, java.sql.Date.valueOf(d5));
			pstmt.setFloat(4, Float.parseFloat(txtbill.getText()));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    PreparedStatement pstmt;    
    Connection con;
    @FXML
    void initialize() 
    {
    	con=ConnectToDatabase.getConnection();
    	try
    	{
			pstmt=con.prepareStatement("select * from customer");
			ResultSet table=pstmt.executeQuery();
			while(table.next())
			{
				combo.getItems().add(table.getString("mobile"));
			}
		} 
    	catch (SQLException e) {
			e.printStackTrace();
		}
     }
}

