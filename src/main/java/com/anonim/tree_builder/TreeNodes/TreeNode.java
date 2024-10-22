package com.anonim.tree_builder.TreeNodes;

import com.anonim.tree_builder.Enums.StandardStyle;
import com.anonim.tree_builder.Tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

public class TreeNode implements TreeNodeRoot {

    public String content;
    public UUID identifier;

    public TreeNodeClass displayClass;
    public ArrayList<TreeNodeRoot> parent = new ArrayList<>();
    public ArrayList<TreeNodeRoot> children = new ArrayList<>();
    public ArrayList<TreeNodeLink> links = new ArrayList<>();

    public double nodeX;
    public double nodeY;

    public float complexity;

    public TreeNode(String content) {
        this.content = content;
        this.identifier = UUID.randomUUID();

        StandardStyle.STANDARD_STYLE.addNodeUsesThisStyle(this);
    }

    public TreeNode(String content, TreeNodeClass displayClass) {
        this.content = content;
        this.identifier = UUID.randomUUID();
        this.displayClass = displayClass;

        displayClass.addNodeUsesThisStyle(this);
    }

    public TreeNode(String content, TreeNodeClass displayClass, TreeNodeRoot parent) {
        this.content = content;
        this.identifier = UUID.randomUUID();
        this.displayClass = displayClass;
        this.parent.add(parent);

        displayClass.addNodeUsesThisStyle(this);
    }

    public TreeNode(String content, TreeNodeClass displayClass, UUID identifier) {
        this.content = content;
        this.identifier =identifier;
        this.displayClass = displayClass;

        displayClass.addNodeUsesThisStyle(this);
    }

    public TreeNode() {
        this.identifier = UUID.randomUUID();

        StandardStyle.STANDARD_STYLE.addNodeUsesThisStyle(this);
    }

    public boolean hasParent() {
        return !parent.isEmpty();
    }

    // GETTERS & SETTERS

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public TreeNodeClass getDisplayClass() {
        return displayClass;
    }

    public void setDisplayClass(TreeNodeClass displayClass) {
        this.displayClass = displayClass;
    }

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

    public void addLink (TreeNodeLink node) {
        UUID uuid = node.getIdentifier();
        for (TreeNodeLink element : links) {
            if (element.getIdentifier() == uuid) {
                return;
            }
        }
        links.add(node);
    }

    public void removeLink (TreeNodeLink node) {
        UUID uuid = node.getIdentifier();
        links.removeIf(element -> element.getIdentifier() == uuid);
    }

    public ArrayList<TreeNodeRoot> getChildrenArray () {
        return children;
    }
    public ArrayList<TreeNodeLink> getLinksArray () {
        return links;
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

    public float getComplexity() {
        return complexity;
    }

    public void setComplexity(float complexity) {
        this.complexity = complexity;
    }

    public boolean itIsLinkNode() {return false;}

    public boolean itIsDependentLinkNode() {return false;};

}
