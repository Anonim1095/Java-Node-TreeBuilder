package com.anonim.tree_builder;

import com.anonim.tree_builder.Canvas.TreeCanvas;
import com.anonim.tree_builder.JavaFXControllers.Control.CreateLinkButtonController;
import com.anonim.tree_builder.JavaFXControllers.Control.DeleteNodeButtonController;
import com.anonim.tree_builder.JavaFXControllers.Control.UnselectStyleButtonController;
import com.anonim.tree_builder.JavaFXControllers.LeftStatusController;
import com.anonim.tree_builder.JavaFXControllers.ModeStatusController;
import com.anonim.tree_builder.JavaFXControllers.RightStatusController;
import com.anonim.tree_builder.JavaFXControllers.Tabs.NodeEditor.ChildController;
import com.anonim.tree_builder.JavaFXControllers.Tabs.NodeEditor.NodeEditorController;
import com.anonim.tree_builder.JavaFXControllers.Tabs.NodeEditor.ParentController;
import com.anonim.tree_builder.JavaFXControllers.Tabs.StyleEditor.ListController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends javafx.application.Application {

    public static ApplicationController controller;
    public static Stage stage;
    public static final int TREE_VERSION = 1;

    public static void update() {
        ListController.update();
        UnselectStyleButtonController.update();
        LeftStatusController.updateLeftStatusSelectedNode();
        ModeStatusController.updateModeStatus();
        RightStatusController.updateRightStatusSelectedNode();
        ParentController.update();
        NodeEditorController.update();
        ChildController.update();
        CreateLinkButtonController.update();
        DeleteNodeButtonController.update();
        UnselectStyleButtonController.update();
        TreeCanvas.display(controller.treeCanvas);
    }

    @Override
    public void start(Stage applicationStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1054, 603);

        applicationStage.setTitle("Tree Builder");
        applicationStage.setScene(scene);
        applicationStage.setResizable(false);
        applicationStage.show();

        controller = fxmlLoader.getController();
        stage = applicationStage;
        System.out.println("Controller: " + controller);

        TreeCanvas.display(controller.treeCanvas);
        UnselectStyleButtonController.update();
    }

    public static void main(String[] args) {
        launch();
    }
}