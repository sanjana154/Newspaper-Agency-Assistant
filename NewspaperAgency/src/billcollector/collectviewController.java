package billcollector;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import jdbcc.ConnectToDatabase;

public class collectviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combo;

    @FXML
    private ListView<String> lstdate;

    @FXML
    private ListView<String> lstamount;

    @FXML
    private TextField txtamount;

    @FXML
    void dofetch(ActionEvent event) 
    
    {
    	try
    	{
			pstmt=con.prepareStatement("select * from bill where mobile=? and status=?");
			pstmt.setString(1, combo.getSelectionModel().getSelectedItem());
			pstmt.setString(2, "0");
			ResultSet table=pstmt.executeQuery();
			float sum=0;
			while(table.next())
			{
				Float amt=table.getFloat("amount");
				sum=sum+amt;
				lstamount.getItems().add(String.valueOf(amt));
				java.sql.Date d3=table.getDate("dob");
				lstdate.getItems().add(d3.toString());
			}
			txtamount.setText(String.valueOf(sum));
		} 
    	catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void dopay(ActionEvent event) 
    {
    	try
    	{
			pstmt=con.prepareStatement("update bill set status=? where mobile=?");
			pstmt.setString(1, "1");
			pstmt.setString(2, combo.getSelectionModel().getSelectedItem());
			pstmt.executeUpdate();
		} 
    	catch (SQLException e) {
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
