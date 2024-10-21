package com.anonim.tree_builder.JavaFXControllers.Control;

import com.anonim.tree_builder.ApplicationController;
import com.anonim.tree_builder.Canvas.TreeCanvas;
import com.anonim.tree_builder.JavaFXControllers.Tabs.NodeEditor.NodeEditorController;
import com.anonim.tree_builder.Tree;
import com.anonim.tree_builder.TreeNodes.TreeNode;
import com.anonim.tree_builder.TreeNodes.TreeNodeJoint;
import com.anonim.tree_builder.TreeNodes.TreeNodeLink;
import com.anonim.tree_builder.TreeNodes.TreeNodeRoot;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.UUID;

public class DeleteNodeButtonController {

    public static void update () {
        TreeNodeRoot selected = Tree.getSelectedNode();
        if (selected != null) {
            ApplicationController.changeVisibilityOfDeleteNodeButton(true);
        } else {
            ApplicationController.changeVisibilityOfDeleteNodeButton(false);
        }
    }

    public static void destroyAllLinksNodeLink(TreeNodeLink node) {
        ArrayList<TreeNodeRoot> parent = new ArrayList<>(node.getParentArray());
        ArrayList<TreeNodeRoot> children = new ArrayList<>(node.getChildrenArray());

        node.getDisplayClass().deleteNodeFromUsing(node);

        children.forEach((root) -> {
            root.removeParent(node);
        });
        parent.forEach((root) -> {
            root.removeChildren(node);
        });
    }

    public static void destroyAllLinksNodeJoint(TreeNodeJoint node) {
        ArrayList<TreeNodeRoot> parent = new ArrayList<>(node.getParentArray());
        ArrayList<TreeNodeRoot> children = new ArrayList<>(node.getChildrenArray());

        node.getDisplayClass().deleteNodeFromUsing(node);

        children.forEach((root) -> {
            root.removeParent(node);
        });
        parent.forEach((root) -> {
            root.removeChildren(node);
        });
    }

    public static void destroyAllLinksNode(TreeNode node) {
        ArrayList<TreeNodeRoot> parent = new ArrayList<>(node.getParentArray());
        ArrayList<TreeNodeRoot> children = new ArrayList<>(node.getChildrenArray());

        node.getDisplayClass().deleteNodeFromUsing(node);

        children.forEach((root) -> {
            root.removeParent(node);
        });
        parent.forEach((root) -> {
            root.removeChildren(node);
        });
    }

    public static void action (Canvas canvas, ActionEvent event) {
        TreeNodeRoot selected = Tree.getSelectedNode();
        if (selected != null) {
            TreeNode owner;
            if (!selected.itIsLinkNode()) {
                owner = (TreeNode) selected;

                ArrayList<TreeNodeLink> links = owner.getLinksArray();
                destroyAllLinksNode(owner);

                links.forEach((link) -> {
                    destroyAllLinksNodeLink(link);
                    Tree.removeNodeLink(link);
                });

                Tree.removeNode(owner.getIdentifier());
            } else if (selected.itIsLinkNode() && !selected.itIsDependentLinkNode()){
                TreeNodeJoint jointClass = (TreeNodeJoint) selected;
                destroyAllLinksNodeJoint(jointClass);
                Tree.removeNodeJoint(jointClass);
            } else {
                TreeNodeLink linkClass = (TreeNodeLink) selected;
                destroyAllLinksNodeLink(linkClass);
                Tree.removeNodeLink(linkClass);
            }
            Tree.unselectNode();
            TreeCanvas.display(canvas);
            NodeEditorController.update();
        }
    }

}
