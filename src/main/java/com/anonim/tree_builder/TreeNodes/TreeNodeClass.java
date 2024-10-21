package com.anonim.tree_builder.TreeNodes;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TreeNodeClass {

    public String displayName;
    public UUID identifier;

    public Color backgroundColor;
    public Color foregroundColor;

    public Map<UUID, TreeNodeRoot> usedIn = new HashMap<UUID, TreeNodeRoot>();

    public double textSize = 16;

    public TreeNodeClass(String displayName, UUID identifier) {
        this.displayName = displayName;
        this.identifier = identifier;
    }

    public TreeNodeClass(String displayName, UUID identifier, Color backgroundColor, Color foregroundColor) {
        this.displayName = displayName;
        this.identifier = identifier;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
    }

    public TreeNodeClass(String displayName, Color backgroundColor, Color foregroundColor) {
        this.displayName = displayName;
        this.identifier = UUID.randomUUID();
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
    }

    public TreeNodeClass(String displayName, UUID identifier, Color backgroundColor, Color foregroundColor, double textSize) {
        this.displayName = displayName;
        this.identifier = identifier;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
        this.textSize = textSize;
    }

    public TreeNodeClass() {
        this.displayName = "Empty Class";
        this.identifier = UUID.randomUUID();
    }

    public boolean isInitialized() {
        return (
            this.displayName != null &&
            this.backgroundColor != null &&
            this.foregroundColor != null
        );
    }

    public Map<UUID, TreeNodeRoot> getUsedIn() { return usedIn; }

    // GETTERS

    public String getDisplayName() {
        return displayName;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public double getTextSize() { return textSize; }

    // SETTERS

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public void setTextSize(double textSize) { this.textSize = textSize; }

    public void addNodeUsesThisStyle(TreeNodeRoot node) {
        if (!usedIn.containsKey(node.getIdentifier())) {
            usedIn.put(node.getIdentifier(),node);
        }
    }

    public void deleteNodeFromUsing(TreeNodeRoot node) {
        if (usedIn.containsKey(node.getIdentifier())) {
            usedIn.remove(node.getIdentifier());
        }
    }

    public boolean isLinkClass() { return false; }

}
