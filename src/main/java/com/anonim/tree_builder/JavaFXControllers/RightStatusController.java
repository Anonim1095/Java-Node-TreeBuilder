package com.anonim.tree_builder.JavaFXControllers;

import com.anonim.tree_builder.Main;
import com.anonim.tree_builder.Tree;
import com.anonim.tree_builder.TreeNodes.TreeNodeRoot;

public class RightStatusController {

    private static final String STANDARD_TEXT = "";

    public static void updateRightStatusSelectedNode() {
        TreeNodeRoot node = Tree.getSelectedNode();
        if (node != null) {
            Main.controller.updateRightStatusText(node.getIdentifier().toString());
        } else {
            Main.controller.updateRightStatusText(STANDARD_TEXT);
        }
    }

}
