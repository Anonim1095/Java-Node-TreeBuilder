package com.anonim.tree_builder.JavaFXControllers.Control;

import com.anonim.tree_builder.Canvas.TreeCanvas;
import com.anonim.tree_builder.DisplayClasses;
import com.anonim.tree_builder.Enums.StandardStyle;
import com.anonim.tree_builder.JavaFXControllers.Tabs.NodeEditor.NodeEditorController;
import com.anonim.tree_builder.Tree;
import com.anonim.tree_builder.TreeNodes.TreeNode;
import com.anonim.tree_builder.TreeNodes.TreeNodeClass;
import javafx.scene.canvas.Canvas;

public class NewNodeButtonController {

    public static void action(Canvas canvas) {

        double posX = TreeCanvas.displayX;
        double posY = TreeCanvas.displayY;

        TreeNodeClass selected = DisplayClasses.getSelected();
        if (selected == null) {
            selected = StandardStyle.STANDARD_STYLE;
        }
        if (selected.isLinkClass()) {
            selected = StandardStyle.STANDARD_STYLE;
        }

        TreeNode node = new TreeNode(
                "",
                selected
        );

        node.setNodeX(-posX + 20);
        node.setNodeY(-posY + 20);

        Tree.insertNewNode(node);
        TreeCanvas.display(canvas);

        NodeEditorController.update();

    }

}
