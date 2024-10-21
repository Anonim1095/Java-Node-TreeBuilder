package com.anonim.tree_builder.JavaFXControllers.Control;

import com.anonim.tree_builder.ApplicationController;
import com.anonim.tree_builder.Canvas.TreeCanvas;
import com.anonim.tree_builder.DisplayClasses;
import com.anonim.tree_builder.Enums.StandardStyle;
import com.anonim.tree_builder.JavaFXControllers.Tabs.NodeEditor.NodeEditorController;
import com.anonim.tree_builder.Tree;
import com.anonim.tree_builder.TreeNodes.*;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;

public class CreateLinkButtonController {

    public static void update () {
        TreeNodeRoot selected = Tree.getSelectedNode();
        if (selected != null) {
            ApplicationController.changeVisibilityOfCreateLinkButton(true);
        } else {
            ApplicationController.changeVisibilityOfCreateLinkButton(false);
        }
    }

    public static void action (Canvas canvas, ActionEvent event) {
        TreeNodeRoot selected = Tree.getSelectedNode();
        if (selected != null) {
            TreeNode owner;
            if (selected instanceof TreeNode) {
                owner = (TreeNode) selected;
            } else if (selected instanceof TreeNodeLink) {
                TreeNodeLink linkClass = (TreeNodeLink) selected;
                owner = linkClass.getOwner();
            } else {
                return;
            }

            TreeNodeClass selectedStyle = DisplayClasses.getSelected();
            TreeNodeLinkClass displayClass = StandardStyle.STANDARD_LINK_STYLE;
            if (selectedStyle != null) {
                if (selectedStyle.isLinkClass()) {
                    displayClass = (TreeNodeLinkClass) selectedStyle;
                }
            }

            TreeNodeLink link = new TreeNodeLink(owner, displayClass);
            owner.addLink(link);
            Tree.insertNewNodeLink(link);
            Tree.setSelectedNode(link);
            TreeCanvas.display(canvas);
            NodeEditorController.update();
        }
    }

}
