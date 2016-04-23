import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Evgeni Developer on 23.04.2016.
 */
public class Main extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        String fxmlFile = "/fxml/menu/menu.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

        //добавил загрузку стилей т.к. стили для menu.fxml не применились
        root.getStylesheets().add(getClass().getResource("/fxml/menu/menu.css").toExternalForm());
        String image = getClass().getResource("/fxml/background1.jpg").toExternalForm();
        root.setStyle("-fx-background-image: url('" + image + "'); " +
                "    -fx-background-repeat: no-repeat;\n" +
                "    -fx-background-size: cover;");

        stage.setTitle("Molecule Manager");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
