package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.GardenKit.src.Nature.Garden;
import sample.GardenKit.src.Nature.GardenCalendar.GardenTime;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Garden");
        primaryStage.setScene(new Scene(root,960, 540));
        primaryStage.show();

        Garden garden = Garden.getInstance();
        garden.bootGarden();

        primaryStage.setOnCloseRequest(e -> Platform.exit());

    }


    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void stop() throws Exception {
        super.stop();

            Garden garden = Garden.getInstance();
            garden.stopGarden();


        Platform.exit();
        System.exit(0);
    }
}
