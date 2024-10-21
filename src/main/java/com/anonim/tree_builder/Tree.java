package com.anonim.tree_builder;

import com.anonim.tree_builder.JavaFXControllers.Control.CreateLinkButtonController;
import com.anonim.tree_builder.JavaFXControllers.Control.DeleteNodeButtonController;
import com.anonim.tree_builder.JavaFXControllers.LeftStatusController;
import com.anonim.tree_builder.JavaFXControllers.RightStatusController;
import com.anonim.tree_builder.JavaFXControllers.Tabs.NodeEditor.NodeEditorController;
import com.anonim.tree_builder.TreeNodes.TreeNode;
import com.anonim.tree_builder.TreeNodes.TreeNodeJoint;
import com.anonim.tree_builder.TreeNodes.TreeNodeLink;
import com.anonim.tree_builder.TreeNodes.TreeNodeRoot;

import java.util.HashMap;
import java.util.UUID;

public class Tree {

    public static HashMap<UUID, TreeNode> TreeNodes = new HashMap<UUID, TreeNode>();
    public static HashMap<UUID, TreeNodeJoint> TreeJoints = new HashMap<UUID, TreeNodeJoint>();
    public static HashMap<UUID, TreeNodeLink> TreeNodesLinks = new HashMap<UUID, TreeNodeLink>();

    static TreeNodeRoot selectedNode = null;

    public static void insertNewNode(TreeNode node) {
        if (!TreeNodes.containsKey(node.getIdentifier())) {
            TreeNodes.put(node.getIdentifier(),node);
        }
    }

    public static void removeNode(UUID identifier) {
        if (TreeNodes.containsKey(identifier)) { TreeNodes.remove(identifier); }
    }

    public static void insertNewNodeLink(TreeNodeLink node) {
        if (!TreeNodesLinks.containsKey(node.getIdentifier())) {
            TreeNodesLinks.put(node.getIdentifier(),node);
        }
    }

    public static void removeNodeLink(TreeNodeLink node) {
        if (TreeJoints.containsKey(node.getIdentifier())) {
            TreeJoints.remove(node.getIdentifier());
        }
    }

    public static void insertNewNodeJoint(TreeNodeJoint node) {
        if (!TreeJoints.containsKey(node.getIdentifier())) {
            TreeJoints.put(node.getIdentifier(),node);
        }
    }

    public static void removeNodeJoint(TreeNodeJoint node) {
        if (TreeJoints.containsKey(node.getIdentifier())) {
            TreeJoints.remove(node.getIdentifier());
        }
    }

    public static HashMap<UUID, TreeNode> getTreeNodes() {
        return TreeNodes;
    }

    public static HashMap<UUID, TreeNodeLink> getTreeNodesLinks() {
        return TreeNodesLinks;
    }

    public static HashMap<UUID, TreeNodeJoint> getTreeNodesJoints() {
        return TreeJoints;
    }

    public static TreeNodeRoot getSelectedNode() {
        return selectedNode;
    }

    public static void setSelectedNode(TreeNodeRoot selectedNodeS) {
        selectedNode = selectedNodeS;

        RightStatusController.updateRightStatusSelectedNode();
        LeftStatusController.updateLeftStatusSelectedNode();

        CreateLinkButtonController.update();
        DeleteNodeButtonController.update();
        NodeEditorController.update();
    }

    public static void unselectNode() {
        selectedNode = null;

        RightStatusController.updateRightStatusSelectedNode();
        LeftStatusController.updateLeftStatusSelectedNode();

        CreateLinkButtonController.update();
        DeleteNodeButtonController.update();
        NodeEditorController.update();
    }

}