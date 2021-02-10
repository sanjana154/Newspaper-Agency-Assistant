package hawkertable;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import jdbcc.ConnectToDatabase;

public class htblviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<UserBean2> tbl;

    @FXML
    void dofetch(ActionEvent event)
    {
    	String path="carry.mp3";
    	Media media = new Media(new File(path).toURI().toString());  
    	 MediaPlayer mediaPlayer = new MediaPlayer(media);   
         mediaPlayer.setAutoPlay(true);  
    	TableColumn<UserBean2, String> name=new TableColumn<UserBean2, String>("Name");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));
    	name.setMinWidth(100);
    	
    	TableColumn<UserBean2, String> mobile=new TableColumn<UserBean2, String>("Mobile");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mobile.setMinWidth(100);
    	
    	TableColumn<UserBean2, String> address=new TableColumn<UserBean2, String>("Address");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));
    	address.setMinWidth(100);
    	
    	TableColumn<UserBean2, String> areas=new TableColumn<UserBean2, String>("Areas");
    	areas.setCellValueFactory(new PropertyValueFactory<>("areas"));
    	areas.setMinWidth(100);
    	
    	TableColumn<UserBean2, String> salary=new TableColumn<UserBean2, String>("Salary");
    	salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
    	salary.setMinWidth(100);
    	
    	TableColumn<UserBean2, String> doj=new TableColumn<UserBean2, String>("DateOfJoining");
    	doj.setCellValueFactory(new PropertyValueFactory<>("doj"));
    	doj.setMinWidth(100);
    	
    	tbl.getColumns().clear();
    	tbl.getColumns().addAll(name,mobile,address,areas,salary,doj);
    	ObservableList<UserBean2> ary=getallrecords();
    	
    	tbl.setItems(null);
    	tbl.setItems(ary);
    } 
    
    @FXML
    ObservableList<UserBean2> getallrecords() 
    {
    	ObservableList<UserBean2> ary=FXCollections.observableArrayList();
    	try
    	{
    	pstmt=con.prepareStatement("select * from hawkertable");
    	ResultSet table=pstmt.executeQuery();
    	while(table.next())
    		{
    			String name=table.getString("name");
    			String mobile=table.getString("mobile");
    			String address=table.getString("address");
    			String areas=table.getString("areas");
    			String salary=String.valueOf(table.getFloat("salary"));
    			java.sql.Date d3=table.getDate("doj");
    			String doj=d3.toString();
    			UserBean2 ref=new UserBean2(name,mobile,address,areas,salary,doj);
    			ary.add(ref);
    		}
    	}
    	catch(Exception exp)
    	{
    		
    	}
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

