package com.anonim.tree_builder.TreeNodes;

import java.util.ArrayList;
import java.util.UUID;

public interface TreeNodeRoot {

    double getNodeX();
    double getNodeY();

    void setNodeX(double nodeX);

    void setNodeY(double nodeY);

    String getContent();

    void setContent(String value);

    boolean hasParent();

    void setDisplayClass(TreeNodeClass displayClass);

    TreeNodeClass getDisplayClass();

    UUID getIdentifier();

    boolean itIsLinkNode();

    boolean itIsDependentLinkNode();

    void addParent(TreeNodeRoot parent);

    void removeParent(TreeNodeRoot node);

    void addChildren(TreeNodeRoot children);

    void removeChildren(TreeNodeRoot node);

    ArrayList<TreeNodeRoot> getChildrenArray();

    ArrayList<TreeNodeRoot> getParentArray();

    boolean hasChildren(TreeNodeRoot children);

}
