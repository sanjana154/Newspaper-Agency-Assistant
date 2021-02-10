package hawker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import jdbcc.ConnectToDatabase;

public class hawkerviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combo;

    @FXML
    private TextField txtno;

    @FXML
    private ImageView img;

    @FXML
    private DatePicker combodate;

    @FXML
    private TextField txtadd;
    
    @FXML
    private TextField txtsal;
    
    @FXML
    private DatePicker date;
    
    @FXML
    private Label lblarea;
    
    @FXML
    public ListView<String> lstarea;
    
    @FXML
    void doremove(ActionEvent event) 
    {
    	try {
    		pstmt=con.prepareStatement("delete from hawkertable where name=?");
    		pstmt.setString(1,combo.getEditor().getText());
          pstmt.executeUpdate();
    	} 
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void dosave(ActionEvent event) 
    {
    	try {
    		String all="";
			pstmt=con.prepareStatement("insert into hawkertable values(?,?,?,?,?,?,?)");
			pstmt.setString(1,combo.getEditor().getText());
			pstmt.setString(2,txtno.getText());
			pstmt.setString(3,txtadd.getText());
			ObservableList<String> items=lstarea.getSelectionModel().getSelectedItems();
			for (String s : items)
	    	  {
	    		  all=all+s+",";
	    	  }
			pstmt.setString(4,all);
			pstmt.setString(5,path);
			pstmt.setFloat(6,Float.parseFloat(txtsal.getText()));
			LocalDate d=date.getValue();
			java.sql.Date d2= java.sql.Date.valueOf(d);
			pstmt.setDate(7,d2);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
public String[] arr;
    @FXML
    void dosearch(ActionEvent event)
    {
    	try 
    	{
			pstmt=con.prepareStatement("select * from hawkertable where name=?");
			pstmt.setString(1,combo.getEditor().getText());
			ResultSet table=pstmt.executeQuery();
			if(table.next())
			{
				txtno.setText(table.getString("mobile"));
				txtadd.setText(table.getString("address"));
				lstarea.getSelectionModel().clearSelection();
				String s=table.getString("areas");
				String[] arrpaper=s.split(",");
					for (String i : arrpaper)
			    	  {
			    		  lstarea.getSelectionModel().select(i);
			    	  }
				String p=table.getString("aadharpic");
				try {
					FileInputStream fil=new FileInputStream(p);
					Image im=new Image(fil);
					img.setImage(im);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				txtsal.setText(String.valueOf(table.getFloat("salary")));
				java.sql.Date d3=table.getDate("doj");
				LocalDate d4=d3.toLocalDate();
               date.setValue(d4);
               
			}
    	} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    }

    String path;
    @FXML
    void upload(ActionEvent event) {
    	FileChooser fc=new FileChooser();
        File selectedFile=fc.showOpenDialog(null);
        Image image=new Image(selectedFile.toURI().toString());
       if(selectedFile!=null)
       {
       	img.setImage(image);
        path=selectedFile.getAbsolutePath();
       }
    }

    @FXML
    void doupdate(ActionEvent event) 
    {
    	try {
			pstmt=con.prepareStatement("update hawkertable set name=? , mobile=? , address=? ,areas=? ,aadharpic=? ,salary=? ,doj=? where name=?");
			pstmt.setString(1,combo.getEditor().getText());
			pstmt.setString(2,txtno.getText());
			pstmt.setString(3,txtadd.getText());
			String all2="";
			ObservableList<String> items=lstarea.getSelectionModel().getSelectedItems();
			for (String s : items)
	    	  {
	    		  all2=all2+s+",";
	    	  }
			pstmt.setString(4,all2);
			
			pstmt.setString(5,path);
			pstmt.setFloat(6,Float.parseFloat(txtsal.getText()));
			LocalDate d2=date.getValue();
			pstmt.setString(7,d2.toString());
			pstmt.setString(8,combo.getEditor().getText());
			pstmt.executeUpdate();
		} 
    	catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    PreparedStatement pstmt;    
    Connection con;
    public ArrayList<String>lst=new ArrayList<String>(Arrays.asList("Model Town Phase 1","Model Town Phase 2","Model Town Phase 3","Ajit Road","PowerHouse Road","Kamla Nehru","Bharat Nagar","Ganesha Basati"));;
    @FXML
    void initialize() {
    	con=ConnectToDatabase.getConnection();
		lstarea.getItems().addAll(lst);
		lstarea.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
    }
}

