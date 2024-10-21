package com.anonim.tree_builder;

import com.anonim.tree_builder.Canvas.TreeCanvas;
import com.anonim.tree_builder.JavaFXControllers.Control.UnselectStyleButtonController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    public static ApplicationController controller;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 917, 603);
        stage.setTitle("Tree Builder");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        controller = fxmlLoader.getController();
        System.out.println("Controller: " + controller);

        TreeCanvas.display(controller.treeCanvas);
        UnselectStyleButtonController.update();
    }

    public static void main(String[] args) {
        launch();
    }
}