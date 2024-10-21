package com.anonim.tree_builder.JavaFXControllers;

import com.anonim.tree_builder.Canvas.TreeCanvas;
import com.anonim.tree_builder.JavaFXControllers.Control.CreateConnectionButtonController;
import com.anonim.tree_builder.Tree;
import com.anonim.tree_builder.TreeNodes.TreeNodeRoot;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class TreeCanvasController {

    public static void contextMenu(Canvas canvas) {

    }

    public static void mousePressed(Canvas canvas, MouseEvent event) {
        boolean selected = false;
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            TreeNodeRoot node = TreeCanvas.overlapsNode(event.getX(), event.getY());
            if (node != null) {
                System.out.println("Node selected");
                Tree.setSelectedNode(node);
                selected = true;
            }
        } else if (event.getButton().equals(MouseButton.SECONDARY)) {
            System.out.println("Node unselected");
            Tree.unselectNode();
        }
        if (!selected) {
            TreeCanvas.currentlyConnecting = false;
            TreeCanvas.connectingParent = null;
            ModeStatusController.updateModeStatus();
            CreateConnectionButtonController.updateStyle(TreeCanvas.currentlyConnecting);
        }
        if (selected && TreeCanvas.currentlyConnecting) {
            if (Tree.getSelectedNode() != TreeCanvas.connectingParent) {
                TreeCanvas.connect(canvas, TreeCanvas.connectingParent, Tree.getSelectedNode());
                TreeCanvas.currentlyConnecting = false;
                TreeCanvas.connectingParent = null;
                ModeStatusController.updateModeStatus();
                CreateConnectionButtonController.updateStyle(TreeCanvas.currentlyConnecting);
            }
        }
        TreeCanvas.display(canvas);
    }

    public static void mouseDragged(Canvas canvas, double deltaX, double deltaY, MouseEvent event) {
        TreeCanvas.drag(canvas, (float) deltaX, (float) deltaY, event);
    }

    public static void scroll(Canvas canvas, double deltaX, double deltaY, ScrollEvent event) {
        if (deltaX != 0.0d) {
            TreeCanvas.drag(canvas, (float) deltaX, (float) deltaY, null);
        } else {
            boolean direction = (deltaY > 0.0d);
            boolean moved = (deltaY != 0.0d);

            if (moved) {
                TreeCanvas.zoom(canvas, direction);
            }

        }

        System.out.println(deltaX);
        System.out.println(deltaY);

    }

}
