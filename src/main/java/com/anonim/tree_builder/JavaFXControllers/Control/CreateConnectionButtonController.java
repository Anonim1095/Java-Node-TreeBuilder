package com.anonim.tree_builder.JavaFXControllers.Control;

import com.anonim.tree_builder.Application;
import com.anonim.tree_builder.Canvas.TreeCanvas;
import com.anonim.tree_builder.Enums.StandardColors;
import com.anonim.tree_builder.JavaFXControllers.ModeStatusController;
import com.anonim.tree_builder.Tree;
import com.anonim.tree_builder.TreeNodes.TreeNodeRoot;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class CreateConnectionButtonController {

    private static final Color DISABLED_COLOR = Color.BLACK;
    private static final Color ENABLED_COLOR = StandardColors.SELECTED.getColor();

    public static void updateStyle(boolean status) {
        if (status) {
            Application.controller.connectNodesButton.setTextFill(ENABLED_COLOR);
        } else {
            Application.controller.connectNodesButton.setTextFill(DISABLED_COLOR);
        }
    }

    public static void action (Canvas canvas, ActionEvent event) {
        TreeNodeRoot selected = Tree.getSelectedNode();
        boolean status = TreeCanvas.currentlyConnecting;
        if (status) {
            System.out.println("Status is on");
            TreeCanvas.currentlyConnecting = false;
            TreeCanvas.connectingParent = null;
            ModeStatusController.updateModeStatus();
            updateStyle(TreeCanvas.currentlyConnecting);
            return;
        }

        if (selected != null) {
            System.out.println("Status is onnn");
            TreeCanvas.currentlyConnecting = true;
            TreeCanvas.connectingParent = selected;
            ModeStatusController.updateModeStatus();
            updateStyle(TreeCanvas.currentlyConnecting);
        }
    }

}
