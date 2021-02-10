package customertable;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import jdbcc.ConnectToDatabase;

public class tableviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboarea;

    @FXML
    private ComboBox<String> combopaper;

    @FXML
    private TableView<UserBean> tbl;

    @FXML
    void doshowall(ActionEvent event)
    {
    	String path="carry.mp3";
    	Media media = new Media(new File(path).toURI().toString());  
    	 MediaPlayer mediaPlayer = new MediaPlayer(media);   
         mediaPlayer.setAutoPlay(true);  
    	TableColumn<UserBean, String> name=new TableColumn<UserBean, String>("Name");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));
    	name.setMinWidth(100);
    	
    	TableColumn<UserBean, String> mobile=new TableColumn<UserBean, String>("Mobile");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mobile.setMinWidth(100);
    	
    	TableColumn<UserBean, String> add=new TableColumn<UserBean, String>("Address");
    	add.setCellValueFactory(new PropertyValueFactory<>("address"));
    	add.setMinWidth(100);
    	
    	TableColumn<UserBean, String> paper=new TableColumn<UserBean, String>("Paper");
    	paper.setCellValueFactory(new PropertyValueFactory<>("paper"));
    	paper.setMinWidth(100);
    	
    	TableColumn<UserBean, String> hawker=new TableColumn<UserBean, String>("Hawker");
    	hawker.setCellValueFactory(new PropertyValueFactory<>("hawker"));
    	hawker.setMinWidth(100);
    	
    	tbl.getColumns().clear();
    	tbl.getColumns().addAll(name,mobile,add,paper,hawker);
    	ObservableList<UserBean> ary=getallrecords();
    	
    	tbl.setItems(null);
    	tbl.setItems(ary);
    } 
    
    @FXML
    ObservableList<UserBean> getallrecords() 
    {
    	ObservableList<UserBean> ary=FXCollections.observableArrayList();
    	try
    	{
    	pstmt=con.prepareStatement("select * from customer");
    	ResultSet table=pstmt.executeQuery();
    	while(table.next())
    		{
    			String name=table.getString("name");
    			String mob=table.getString("mobile");
    			String add=table.getString("address");
    			String paper=table.getString("paper");
    			String hawker=table.getString("hawker");
    			UserBean ref=new UserBean(name,mob,add,paper,hawker);
    			ary.add(ref);
    		}
    	}
    	catch(Exception exp)
    	{
    		
    	}
    	return ary;
    }
    ObservableList<UserBean> getallarea() 
    {
    	ObservableList<UserBean> ary2=FXCollections.observableArrayList();
    	String item=comboarea.getSelectionModel().getSelectedItem();
    	try
    	{
    	pstmt=con.prepareStatement("select * from customer where area=?");
    	pstmt.setString(1, item);
    	ResultSet table=pstmt.executeQuery();
    	while(table.next())
    		{
    			String name=table.getString("name");
    			String mob=table.getString("mobile");
    			String add=table.getString("address");
    			String paper=table.getString("paper");
    			String hawker=table.getString("hawker");
    			UserBean ref2=new UserBean(name,mob,add,paper,hawker);
    			ary2.add(ref2);
    		}
    	}
    	catch(Exception exp)
    	{
    		
    	}
    	return ary2;
    }

    @FXML
    void fetcharea(ActionEvent event) 
    {
    	TableColumn<UserBean, String> name=new TableColumn<UserBean, String>("Name");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));
    	name.setMinWidth(100);
    	
    	TableColumn<UserBean, String> mobile=new TableColumn<UserBean, String>("Mobile");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mobile.setMinWidth(100);
    	
    	TableColumn<UserBean, String> add=new TableColumn<UserBean, String>("Address");
    	add.setCellValueFactory(new PropertyValueFactory<>("address"));
    	add.setMinWidth(100);
    	
    	TableColumn<UserBean, String> paper=new TableColumn<UserBean, String>("Paper");
    	paper.setCellValueFactory(new PropertyValueFactory<>("paper"));
    	paper.setMinWidth(100);
    	
    	TableColumn<UserBean, String> hawker=new TableColumn<UserBean, String>("Hawker");
    	hawker.setCellValueFactory(new PropertyValueFactory<>("hawker"));
    	hawker.setMinWidth(100);
    	
    	tbl.getColumns().clear();
    	tbl.getColumns().addAll(name,mobile,add,paper,hawker);
    	ObservableList<UserBean> ary2=getallarea();
    	
    	tbl.setItems(null);
    	tbl.setItems(ary2);
    }
    ObservableList<UserBean> getallpaper() 
    {
    	ObservableList<UserBean> ary3=FXCollections.observableArrayList();
    	String item2=combopaper.getSelectionModel().getSelectedItem();
    	try
    	{
    	pstmt=con.prepareStatement("select * from customer");
    	ResultSet table=pstmt.executeQuery();
    	while(table.next())
    		{
    			String s=table.getString("paper");
				String[] as=s.split(",");
				for(String i:as)
				{
					if(i.equals(item2))
					{
						String name=table.getString("name");
		    		    String mob=table.getString("mobile");
		    			String add=table.getString("address");
		    			String paper=table.getString("paper");
		    			String hawker=table.getString("hawker");
		    			UserBean ref3=new UserBean(name,mob,add,paper,hawker);
		    			ary3.add(ref3);
					}
				}
    		}
    	}
    	catch(Exception exp)
    	{
    		
    	}
    	return ary3;
    }
    @FXML
    void fetchpaper(ActionEvent event) 
    {
    	TableColumn<UserBean, String> name=new TableColumn<UserBean, String>("Name");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));
    	name.setMinWidth(100);
    	
    	TableColumn<UserBean, String> mobile=new TableColumn<UserBean, String>("Mobile");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mobile.setMinWidth(100);
    	
    	TableColumn<UserBean, String> add=new TableColumn<UserBean, String>("Address");
    	add.setCellValueFactory(new PropertyValueFactory<>("address"));
    	add.setMinWidth(100);
    	
    	TableColumn<UserBean, String> paper=new TableColumn<UserBean, String>("Paper");
    	paper.setCellValueFactory(new PropertyValueFactory<>("paper"));
    	paper.setMinWidth(100);
    	
    	TableColumn<UserBean, String> hawker=new TableColumn<UserBean, String>("Hawker");
    	hawker.setCellValueFactory(new PropertyValueFactory<>("hawker"));
    	hawker.setMinWidth(100);
    	
    	tbl.getColumns().clear();
    	tbl.getColumns().addAll(name,mobile,add,paper,hawker);
    	ObservableList<UserBean> ary3=getallpaper();
    	
    	tbl.setItems(null);
    	tbl.setItems(ary3);
    }
    PreparedStatement pstmt;    
    Connection con;
    @FXML
    void initialize() 
    {
    	con=ConnectToDatabase.getConnection();
    	try 
    	{
			pstmt=con.prepareStatement("select * from hawkertable");
			ResultSet table=pstmt.executeQuery();
			while(table.next())
			{
				String s=table.getString("areas");
				String[] as=s.split(",");
				for(String i:as)
				{
					comboarea.getItems().add(i);
				}
			}
    	} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    	try
    	{
			pstmt=con.prepareStatement("select * from pm");
			ResultSet table=pstmt.executeQuery();
			while(table.next())
			{
			combopaper.getItems().add(table.getString("paper"));
			}
		} 
    	catch (SQLException e) {
			e.printStackTrace();
		}
    }
}

