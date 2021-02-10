package billtable;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import hawkertable.UserBean2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import jdbcc.ConnectToDatabase;

public class btblviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton r1;

    @FXML
    private ToggleGroup btnpaid;

    @FXML
    private RadioButton r2;

    @FXML
    private Button dofetch;

    @FXML
    private TextField txtno;

    @FXML
    private Button dofetchno;

    @FXML
    private TableView<UserBean3> tbl;

    @FXML
    private TextField txtamt;

    @FXML
    void dofetch(ActionEvent event) 
    {
    	String path="carry.mp3";
    	Media media = new Media(new File(path).toURI().toString());  
    	 MediaPlayer mediaPlayer = new MediaPlayer(media);   
         mediaPlayer.setAutoPlay(true);  

    	TableColumn<UserBean3, String> mobile=new TableColumn<UserBean3, String>("Mobile");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mobile.setMinWidth(100);
    	
    	TableColumn<UserBean3, String> days=new TableColumn<UserBean3, String>("Days");
    	days.setCellValueFactory(new PropertyValueFactory<>("days"));
    	days.setMinWidth(100);
    	
    	TableColumn<UserBean3, String> dob=new TableColumn<UserBean3, String>("DateOfBilling");
    	dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
    	dob.setMinWidth(100);
    	
    	TableColumn<UserBean3, String> amount=new TableColumn<UserBean3, String>("Amount");
    	amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    	amount.setMinWidth(100);

    	tbl.getColumns().clear();
    	tbl.getColumns().addAll(mobile,days,dob,amount);
    	ObservableList<UserBean3> ary3=getallrecords();
    	
    	tbl.setItems(null);
    	tbl.setItems(ary3);
    }

    ObservableList<UserBean3> getallrecords() 
    {
    	ObservableList<UserBean3> ary=FXCollections.observableArrayList();
    	ObservableList<UserBean3> ary2=FXCollections.observableArrayList();
    	if(r1.isSelected())
    	{
    	try
    	{
    	pstmt=con.prepareStatement("select * from bill where status=?");
    	pstmt.setInt(1, 1);
    	ResultSet table=pstmt.executeQuery();
    	while(table.next())
    		{
    			String mobile=table.getString("mobile");
    			String days=String.valueOf(table.getInt("days"));
    			java.sql.Date d3=table.getDate("dob");
    			String dob=d3.toString();
    			String amount=String.valueOf(table.getFloat("amount"));
    			UserBean3 ref=new UserBean3(mobile,days,dob,amount);
    			ary.add(ref);
    		}
    	}
    	catch(Exception exp)
    	{}
    	return ary;
    	}
    	else
    	{
    		try
        	{
        	pstmt=con.prepareStatement("select * from bill where status=?");
        	pstmt.setInt(1, 0);
        	ResultSet table=pstmt.executeQuery();
        	while(table.next())
        		{
        			String mobile=table.getString("mobile");
        			String days=String.valueOf(table.getInt("days"));
        			java.sql.Date d3=table.getDate("dob");
        			String dob=d3.toString();
        			String amount=String.valueOf(table.getFloat("amount"));
        			UserBean3 ref=new UserBean3(mobile,days,dob,amount);
        			ary2.add(ref);
        		}
        	}
        	catch(Exception exp)
        	{}
    	return ary2;
    	}
    }
    
    @FXML
    void dofetchno(ActionEvent event) 
    {
    	String path="carry.mp3";
    	Media media = new Media(new File(path).toURI().toString());  
    	 MediaPlayer mediaPlayer = new MediaPlayer(media);   
         mediaPlayer.setAutoPlay(true);  

    	TableColumn<UserBean3, String> status=new TableColumn<UserBean3, String>("Status");
    	status.setCellValueFactory(new PropertyValueFactory<>("status"));
    	status.setMinWidth(100);
    	
    	TableColumn<UserBean3, String> days=new TableColumn<UserBean3, String>("Days");
    	days.setCellValueFactory(new PropertyValueFactory<>("days"));
    	days.setMinWidth(100);
    	
    	TableColumn<UserBean3, String> dob=new TableColumn<UserBean3, String>("DateOfBilling");
    	dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
    	dob.setMinWidth(100);
    	
    	TableColumn<UserBean3, String> amount=new TableColumn<UserBean3, String>("Amount");
    	amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    	amount.setMinWidth(100);

    	tbl.getColumns().clear();
    	tbl.getColumns().addAll(status,days,dob,amount);
    	ObservableList<UserBean3> ary3=records();
    	
    	tbl.setItems(null);
    	tbl.setItems(ary3);
    }
    
    ObservableList<UserBean3> records() 
    {
    	ObservableList<UserBean3> ary=FXCollections.observableArrayList();
    	try
    	{
    	pstmt=con.prepareStatement("select * from bill where mobile=?");
    	pstmt.setString(1, txtno.getText());
    	ResultSet table=pstmt.executeQuery();
    	while(table.next())
    		{
    			String status=String.valueOf(table.getInt("status"));
    			String days=String.valueOf(table.getInt("days"));
    			java.sql.Date d3=table.getDate("dob");
    			String dob=d3.toString();
    			String amount=String.valueOf(table.getFloat("amount"));
    			UserBean3 ref=new UserBean3(status,days,dob,amount);
    			ary.add(ref);
    		}
    	}
    	catch(Exception exp)
    	{}
    	return ary;
    }
    
    PreparedStatement pstmt;    
    Connection con;
    @FXML
    void initialize() 
    {
    	con=ConnectToDatabase.getConnection();
    }
}

