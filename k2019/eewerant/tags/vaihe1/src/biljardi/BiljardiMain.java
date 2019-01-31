package biljardi;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author eewerant
 * @version 31.1.2019
 *
 */
public class BiljardiMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("BiljardiGUIView.fxml"));
            final Pane root = ldr.load();
            //final BiljardiGUIController biljardiCtrl = (BiljardiGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("biljardi.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Biljardi");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei k�yt�ss�
     */
    public static void main(String[] args) {
        launch(args);
    }
}