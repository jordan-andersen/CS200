import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
     public void start(Stage primaryStage) {
         try {
             FXMLLoader loader = new FXMLLoader(Main.class.getResource("/layout.fxml"));
             Parent root = loader.load();
             Scene scene = new Scene(root);

             Controller controller = loader.getController();
             controller.setStage(primaryStage);

             primaryStage.setScene(scene);
             primaryStage.show();
         } catch ( Exception e ) {
             System.out.println(e.getMessage());
         }

     }
}
