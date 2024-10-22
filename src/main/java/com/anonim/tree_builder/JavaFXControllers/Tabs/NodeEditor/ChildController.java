package com.anonim.tree_builder.JavaFXControllers.Tabs.NodeEditor;

import com.anonim.tree_builder.Main;
import com.anonim.tree_builder.Enums.StandardColors;
import com.anonim.tree_builder.Tree;
import com.anonim.tree_builder.TreeNodes.TreeNodeRoot;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ChildController {

    private static void updateChild(VBox box, ArrayList<TreeNodeRoot> children) {
        box.getChildren().clear();
        children.forEach((item) -> {
            String content = item.getContent();
            if (content.isEmpty()) { content = item.getIdentifier().toString(); }
            if (item.itIsLinkNode() && !item.itIsDependentLinkNode()) {
                content = "(JOINT LINK)";
            }
            Label itemLabel = new Label("(C)"+content);
            Color color = StandardColors.EMPTY.getColor();
            if (item.itIsLinkNode()) {color = StandardColors.PLACEHOLDER.getColor();}
            itemLabel.setTextFill(color);
            box.getChildren().add(itemLabel);
        });
    }

    public static void update() {
        TreeNodeRoot selected = Tree.getSelectedNode();
        if (selected != null) {
            ArrayList<TreeNodeRoot> children = selected.getChildrenArray();
            VBox box = Main.controller.childListBox;
            updateChild(box,children);
        }
    }

}
