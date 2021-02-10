package papermaster;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import jdbcc.ConnectToDatabase;

public class pmviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combo;

    @FXML
    private TextField txtprice;

    @FXML
    private ImageView img;

    @FXML
    void dofetch(ActionEvent event) 
    {
    	try 
    	{
			pstmt=con.prepareStatement("select * from pm where paper=?");
			pstmt.setString(1,combo.getEditor().getText());
			ResultSet table=pstmt.executeQuery();
			if(table.next())
			{
				float rs= table.getFloat("price");
				String p=table.getString("picpath");
				try {
					FileInputStream fil=new FileInputStream(p);
					Image im=new Image(fil);
					img.setImage(im);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				txtprice.setText(String.valueOf(rs));
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
		pstmt=con.prepareStatement("delete from pm where paper=?");
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
			pstmt=con.prepareStatement("insert into pm values(?,?,?)");
			pstmt.setString(1,combo.getEditor().getText());
			pstmt.setFloat(2, Float.parseFloat(txtprice.getText()));
			pstmt.setString(3,path);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void doupdate(ActionEvent event) 
    {
    	try {
			pstmt=con.prepareStatement("update pm set paper=? , price=? , picpath=? where paper=?");
			pstmt.setString(1,combo.getEditor().getText());
			pstmt.setFloat(2, Float.parseFloat(txtprice.getText()));
			pstmt.setString(4,combo.getEditor().getText());
			pstmt.setString(3,path);
	     pstmt.executeUpdate();//to fire query on table-to save
		} 
    	catch (SQLException e) {
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

    PreparedStatement pstmt;    
    Connection con;
    @FXML
    void initialize() {
    	con=ConnectToDatabase.getConnection();
    }
}
