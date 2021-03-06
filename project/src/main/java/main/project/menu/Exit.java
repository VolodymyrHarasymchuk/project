package main.project.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Exit implements MenuCommand {
    public final static String NAME = "exitButton";
    private Stage stage;
    private Parent root;
    private Scene scene;
    @FXML
    private AnchorPane scenePane;

    public void exit() {
        stage = (Stage) scenePane.getScene().getWindow();
        stage.close();
    }

    public void returnScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("mainScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public String getSceneStr() {
        return "exitScene.fxml";
    }
}
