package com.anonim.tree_builder.TreeNodes;

import java.util.ArrayList;
import java.util.UUID;

public class TreeNodeJoint implements TreeNodeRoot {

    public ArrayList<TreeNodeRoot> parent = new ArrayList<>();
    public ArrayList<TreeNodeRoot> children = new ArrayList<>();

    public TreeNodeLinkClass displayClass;
    public UUID identifier;

    public double nodeX;
    public double nodeY;

    public boolean smallDisplay;

    public TreeNodeJoint(TreeNodeLinkClass displayClass) {
        this.displayClass = displayClass;

        this.identifier = UUID.randomUUID();

        displayClass.addNodeUsesThisStyle(this);
    }

    public TreeNodeJoint(TreeNodeLinkClass displayClass, boolean smallDisplay) {
        this.displayClass = displayClass;
        this.smallDisplay = smallDisplay;

        this.identifier = UUID.randomUUID();

        displayClass.addNodeUsesThisStyle(this);
    }

    public boolean hasParent() {
        return !parent.isEmpty();
    }

    public UUID getIdentifier() { return this.identifier; }

    // GETTERS & SETTERS

    public String getContent() { return ""; }

    public void setContent(String value) { return; }

    public ArrayList<TreeNodeRoot> getParentArray() {
        return parent;
    }

    public void addParent (TreeNodeRoot node) {
        UUID uuid = node.getIdentifier();
        for (TreeNodeRoot element : parent) {
            if (element.getIdentifier() == uuid) {
                return;
            }
        }
        parent.add(node);
        node.addChildren(this);
    }

    public void removeParent (TreeNodeRoot node) {
        UUID uuid = node.getIdentifier();
        parent.removeIf((TreeNodeRoot root) -> root.getIdentifier() == uuid);
    }
    public void addChildren (TreeNodeRoot node) {
        UUID uuid = node.getIdentifier();
        for (int index = 0; index < children.size(); index++)
        {
            TreeNodeRoot element = children.get(index);
            if (element.getIdentifier() == uuid) {
                return;
            }
        }
        children.add(node);
        node.addParent(this);
    }

    public void removeChildren (TreeNodeRoot node) {
        UUID uuid = node.getIdentifier();
        children.removeIf((TreeNodeRoot root) -> root.getIdentifier() == uuid);
    }

    public ArrayList<TreeNodeRoot> getChildrenArray () {
        return children;
    }

    public boolean hasChildren (TreeNodeRoot node) {
        UUID uuid = node.getIdentifier();
        for (TreeNodeRoot element : children) {
            if (element.getIdentifier() == uuid) {
                return true;
            }
        }
        return false;
    }

    public TreeNode getOwner() { return null; }

    public TreeNodeLinkClass getDisplayClass() {
        return displayClass;
    }

    public void setDisplayClass(TreeNodeClass displayClass) {
        if (displayClass.isLinkClass()) {
            this.displayClass = (TreeNodeLinkClass) displayClass;
        }
    }

    public boolean isSmallDisplay() {
        return smallDisplay;
    }

    public void setSmallDisplay(boolean smallDisplay) {
        this.smallDisplay = smallDisplay;
    }

    public double getNodeX() {
        return nodeX;
    }

    public void setNodeX(double nodeX) {
        this.nodeX = nodeX;
    }

    public double getNodeY() {
        return nodeY;
    }

    public void setNodeY(double nodeY) {
        this.nodeY = nodeY;
    }

    public boolean itIsLinkNode() {return true;}

    public boolean itIsDependentLinkNode() {return false;};

}
