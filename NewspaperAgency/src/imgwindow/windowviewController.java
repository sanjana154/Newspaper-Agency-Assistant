package imgwindow;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class windowviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void bill(MouseEvent event) 
    {
    	try {
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Bill/billview.fxml")); 
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
	    } 
	catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void billcollector(MouseEvent event) 
    {
    	try {
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("billcollector/collectview.fxml")); 
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
	    } 
	catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void customer(MouseEvent event)
    {
    	try {
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Customer/customerview.fxml")); 
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
	    } 
	catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void hawker(MouseEvent event)
    {
    	try {
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("hawker/hawkerview.fxml")); 
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
	    } 
	catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void ppr(MouseEvent event) 
    {
    	try {
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("papermaster/pmview.fxml")); 
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
	    } 
	catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {

    }
}

