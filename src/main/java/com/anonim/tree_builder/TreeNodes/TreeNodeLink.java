package com.anonim.tree_builder.TreeNodes;

import com.anonim.tree_builder.Enums.StandardStyle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

public class TreeNodeLink implements TreeNodeRoot {

    public ArrayList<TreeNodeRoot> parent = new ArrayList<>();
    public ArrayList<TreeNodeRoot> children = new ArrayList<>();

    public TreeNode owner;
    public TreeNodeLinkClass displayClass;
    public UUID identifier;

    public double nodeX;
    public double nodeY;

    public boolean smallDisplay;

    public TreeNodeLink(TreeNode owner, TreeNodeLinkClass displayClass) {
        this.owner = owner;
        this.displayClass = displayClass;

        this.identifier = UUID.randomUUID();

        displayClass.addNodeUsesThisStyle(this);
    }

    public TreeNodeLink(TreeNode owner, TreeNodeLinkClass displayClass, boolean smallDisplay) {
        this.owner = owner;
        this.displayClass = displayClass;
        this.smallDisplay = smallDisplay;

        this.identifier = UUID.randomUUID();

        displayClass.addNodeUsesThisStyle(this);
    }

    public TreeNodeLink(TreeNode owner, TreeNodeLinkClass displayClass, UUID identifier) {
        this.owner = owner;
        this.displayClass = displayClass;

        this.identifier = identifier;

        displayClass.addNodeUsesThisStyle(this);
    }

    public boolean hasParent() {
        return !parent.isEmpty();
    }

    public UUID getIdentifier() { return this.identifier; }

    // GETTERS & SETTERS

    public String getContent() { return owner.getContent(); }

    public void setContent(String value) { owner.setContent(value); }

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

    public TreeNode getOwner() { return owner; }

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

    public boolean itIsDependentLinkNode() {return true;};

}
