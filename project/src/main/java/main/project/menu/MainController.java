package main.project.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private Map<String, MenuCommand> menuItems;

    public MainController() {
        menuItems = new LinkedHashMap<>();

        menuItems.put(Create.NAME, new Create());
        menuItems.put(Delete.NAME, new Delete());
        menuItems.put(Sort.NAME, new Sort());
        menuItems.put(Print.NAME, new Print());
        menuItems.put(Save.NAME, new Save());
        menuItems.put(Load.NAME, new Load());
        menuItems.put(Exit.NAME, new Exit());
        menuItems.put(Start.NAME, new Start());
    }

    public void execute(ActionEvent event) throws IOException {
        Node node = (Node)event.getTarget();
        String id = node.getId();
        MenuCommand menuItem = menuItems.get(id);

        root = FXMLLoader.load(getClass().getResource(menuItem.getSceneStr()));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}