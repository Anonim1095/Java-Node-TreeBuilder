package com.anonim.tree_builder.TreeNodes;

import javafx.scene.paint.Color;

import java.util.UUID;

public class TreeNodeLinkClass extends TreeNodeClass {

    public TreeNodeLinkClass(String displayName, UUID identifier) {
        super(displayName, identifier);
    }

    public TreeNodeLinkClass(String displayName, UUID identifier, Color backgroundColor, Color foregroundColor) {
        super(displayName, identifier, backgroundColor, foregroundColor);
    }

    public TreeNodeLinkClass(String displayName, UUID identifier, Color backgroundColor, Color foregroundColor, double textSize) {
        super(displayName, identifier, backgroundColor, foregroundColor, textSize);
    }

    public TreeNodeLinkClass(String displayName, Color backgroundColor, Color foregroundColor) {
        super(displayName, backgroundColor, foregroundColor);
    }

    public TreeNodeLinkClass() {}

    @Override
    public boolean isLinkClass() { return true; }

}
