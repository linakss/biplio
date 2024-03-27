package com.example.clientv2;

import com.example.clientv2.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class MainApplication extends Application {
    private Stage stage;
    private FXMLLoader fxmlLoader;
    private static MainController mainController;
    private BorderPane rootLayout;
    @Override
    public void start(Stage stage) throws IOException {
        fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Alert alert = new Alert(Alert.AlertType.NONE,  "Возникла очибка",
                ButtonType.CLOSE, ButtonType.OK);

        Button button = new Button("ладно :(");

        button.setOnAction(e -> {
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.show();
        });
        stage.setTitle("Библиотека");
        stage.setResizable(false);
        stage.setScene(scene);
        mainController = fxmlLoader.getController();
        stage.show();
    }

    public static void showDialog(String nameView, String title){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource(nameView));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.setResizable(false);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.show();
        } catch (IOException l){
            Alert alert = new Alert(Alert.AlertType.NONE,  "Возникла очибка",
                    ButtonType.CLOSE, ButtonType.OK);

            Button button = new Button("ладно :(");

            button.setOnAction(e -> {
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.show();
            });
            l.printStackTrace();
        }
    }

    /*public static void showAuthorDialog(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("add-author-view.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Добавить/изменить автора");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void showPublisherDialog(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("add-publisher-view.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Добавить/Изменить издательство");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void showBookDialog(Optional<BookEntity> book){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("add-book-view.fxml"));

            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Работа с книгами");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            AddBookController controller = loader.getController();

            controller.setBook(book);
            controller.start();
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            dialogStage.showAndWait();
            book = controller.getBook();
            System.out.println(book);
            mainController.setBook(book);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
*/

    public static void main(String[] args) {
        launch();
    }
}
