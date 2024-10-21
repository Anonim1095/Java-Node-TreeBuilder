package com.anonim.tree_builder.JavaFXControllers;

import com.anonim.tree_builder.Application;
import com.anonim.tree_builder.Tree;
import com.anonim.tree_builder.TreeNodes.TreeNodeRoot;

public class LeftStatusController {

    private static final String STANDARD_TEXT = "";

    private static final String LINK_TEXT = "Node Link";
    private static final String JOINT_TEXT = "Joint Node Link";
    private static final String NODE_TEXT = "Node";

    public static void updateLeftStatusSelectedNode() {
        TreeNodeRoot node = Tree.getSelectedNode();
        if (node != null) {
            if (node.itIsLinkNode()) {
                if (node.itIsDependentLinkNode()) {
                    Application.controller.updateLeftStatusText(LINK_TEXT);
                } else {
                    Application.controller.updateLeftStatusText(JOINT_TEXT);
                }
            } else {
                Application.controller.updateLeftStatusText(NODE_TEXT);
            }
        } else {
            Application.controller.updateLeftStatusText(STANDARD_TEXT);
        }
    }

}
