package Customer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import jdbcc.ConnectToDatabase;

public class customerviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtname;
    
    @FXML
    private TextField txtaddress;

    @FXML
    private TextField txtnumber;

    @FXML
    private ComboBox<String> customerarea;

    @FXML
    private TextField txthawker;
    
    @FXML
    private ListView<String> lstpaper;

    @FXML
    private ListView<String> lstprice;
    
    @FXML
    private DatePicker date;

    @FXML
    private ImageView img;
    String area="";
    @FXML
    void docustomerarea(ActionEvent event) 
    {
    	 area=customerarea.getSelectionModel().getSelectedItem();
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
					if(i.equals(area))
						txthawker.setText(table.getString("name"));
				}
			}
    	} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    }
    @FXML
    void doremove(ActionEvent event)
    {
    	try {
    		pstmt=con.prepareStatement("delete from customer where mobile=?");
    		pstmt.setString(1,txtnumber.getText());
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
    		String all="",all2="";
			pstmt=con.prepareStatement("insert into customer values(?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1,txtname.getText());
			pstmt.setString(2,txtnumber.getText());
			pstmt.setString(3,txtaddress.getText());
			ObservableList<String> items=lstpaper.getSelectionModel().getSelectedItems();
			for (String s : items)
	    	  {
	    		  all=all+s+",";
	    	  }
			pstmt.setString(4,all);
			pstmt.setString(5,txthawker.getText());
			pstmt.setString(6,path);
			ObservableList<String> sprice=lstprice.getSelectionModel().getSelectedItems();
			for (String s2 : sprice)
	    	  {
	    		  all2=all2+s2+",";
	    	  }
			pstmt.setString(7,all2);
			pstmt.setString(8, area);
			LocalDate d=date.getValue();
			java.sql.Date d2= java.sql.Date.valueOf(d);
			pstmt.setDate(9,d2);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void doupdate(ActionEvent event) 
    {
    	try {
    		String all="",all2="";
			pstmt=con.prepareStatement("update customer set name=? , mobile=? , address=? ,paper=? ,hawker=? ,picture=? , price=? , area=? , doj=? where name=?");
			pstmt.setString(1,txtname.getText());
			pstmt.setString(2,txtnumber.getText());
			pstmt.setString(3,txtaddress.getText());
			ObservableList<String> items=lstpaper.getSelectionModel().getSelectedItems();
			for (String s : items)
	    	  {
	    		  all=all+s+",";
	    	  }
			pstmt.setString(4,all);
			pstmt.setString(5,txthawker.getText());
			pstmt.setString(6,path);
			ObservableList<String> sprice=lstprice.getSelectionModel().getSelectedItems();
			for (String s2 : sprice)
	    	  {
	    		  all2=all2+s2+",";
	    	  }
			pstmt.setString(7,all2);
			pstmt.setString(8, area);
			LocalDate d=date.getValue();
			java.sql.Date d2= java.sql.Date.valueOf(d);
			pstmt.setDate(9,d2);
			pstmt.setString(10, txtname.getText());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void doclear(ActionEvent event) 
    {
      System.exit(0);
    }
 String path="";   
    @FXML
    void doupload(ActionEvent event) 
    {
    	FileChooser fc=new FileChooser();
        File selectedFile=fc.showOpenDialog(null);
        Image image=new Image(selectedFile.toURI().toString());
       if(selectedFile!=null)
       {
       	img.setImage(image);
       	path=selectedFile.getAbsolutePath();
       }
    }
   /* @FXML
    void doprice(MouseEvent event)
    {
    	String items=lstpaper.getSelectionModel().getSelectedItem();
			try
	    	{
				pstmt=con.prepareStatement("select * from pm where paper=?");
				pstmt.setString(1,items);
				ResultSet table=pstmt.executeQuery();
				if(table.next())
				{
				lstprice.getItems().add(table.getString("price"));
				}
			} 
	    	catch (SQLException e) {
				e.printStackTrace();
			}
    }*/
    @FXML
    void fetch(ActionEvent event) 
    {
    	lstpaper.getSelectionModel().clearSelection();
    	lstprice.getSelectionModel().clearSelection();
    	try 
    	{
			pstmt=con.prepareStatement("select * from customer where mobile=?");
			pstmt.setString(1,txtnumber.getText());
			ResultSet table=pstmt.executeQuery();
			if(table.next())
			{
				txtname.setText(table.getString("name"));
				txtaddress.setText(table.getString("address"));
				try {
					String p=table.getString("picture");
					FileInputStream fil=new FileInputStream(p);
					Image im=new Image(fil);
					img.setImage(im);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				txthawker.setText(table.getString("hawker"));
				customerarea.setValue(table.getString("area"));
			String s3=table.getString("paper");
			String[] arrpaper=s3.split(",");
				for (String i : arrpaper)
		    	  {
		    		  lstpaper.getSelectionModel().select(i);
		    	  }
				String s4=table.getString("price");
				String[] arrprice=s4.split(",");
					for (String i : arrprice)
			    	  {
			    		  lstprice.getSelectionModel().select(i);
			    	  }
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
    PreparedStatement pstmt;    
    Connection con;
    @FXML
    void initialize() 
    {
    	con=ConnectToDatabase.getConnection();
    	lstpaper.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	lstprice.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	try
    	{
			pstmt=con.prepareStatement("select * from pm");
			ResultSet table=pstmt.executeQuery();
			while(table.next())
			{
			lstpaper.getItems().add(table.getString("paper"));
			lstprice.getItems().add(table.getString("price"));
			}
		} 
    	catch (SQLException e) {
			e.printStackTrace();
		}
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
					customerarea.getItems().add(i);
				}
			}
    	} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    }
}

