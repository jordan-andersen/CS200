import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
     public void start(Stage primaryStage) {
         try {
             FXMLLoader loader = new FXMLLoader(Main.class.getResource("/layout.fxml"));
             AnchorPane root = loader.load();
             Scene scene = new Scene(root);

             primaryStage.setScene(scene);
             primaryStage.show();
         } catch ( Exception e ) {
             System.out.println(e.getMessage());
         }

     }
}
