package com.anonim.tree_builder;

import com.anonim.tree_builder.Enums.StandardStyle;
import com.anonim.tree_builder.TreeNodes.TreeInstance;
import com.anonim.tree_builder.TreeNodes.TreeNodeClass;
import com.anonim.tree_builder.TreeNodes.TreeNodeLink;
import com.anonim.tree_builder.TreeNodes.TreeNodeLinkClass;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DisplayClasses {

    private static final TreeNodeClass STANDARD_STYLE = StandardStyle.STANDARD_STYLE;
    private static final TreeNodeLinkClass STANDARD_LINK_STYLE = StandardStyle.STANDARD_LINK_STYLE;

    private static final UUID StandardStyleIdentifier = StandardStyle.STANDARD_NODE_STYLE_IDENTIFIER;
    private static final UUID StandardLinkStyleIdentifier = StandardStyle.STANDARD_LINK_STYLE_IDENTIFIER;

    public static Map<UUID, TreeNodeClass> styles = new HashMap<UUID, TreeNodeClass>();
    public static Map<UUID, TreeNodeLinkClass> linkStyles = new HashMap<UUID, TreeNodeLinkClass>();

    public static TreeNodeClass selected;

    public static void insertNewClass(TreeNodeClass displayClass) {
        if (!styles.containsKey(displayClass.getIdentifier())) {
            styles.put(displayClass.getIdentifier(), displayClass);
        }
    }

    public static void removeDisplayClass(UUID identifier) {
        System.out.println("Received style to delete with ind "+selected.getIdentifier());
        if (styles.containsKey(identifier)) {
            System.out.println("Removing");
            styles.remove(identifier);
        }
    }

    public static Map<UUID, TreeNodeClass> getStylesList() {
        return styles;
    }

    public static TreeNodeClass getStyle(UUID identifier) {
        if (identifier == StandardStyleIdentifier) {
            return STANDARD_STYLE;
        }
        if (styles.containsKey(identifier)) {
            return styles.get(identifier);
        }
        return null;
    }

    public static void insertNewLinkClass(TreeNodeLinkClass displayClass) {

        if (!linkStyles.containsKey(displayClass.getIdentifier())) {
            linkStyles.put(displayClass.getIdentifier(), displayClass);
        }
    }

    public static void removeLinkDisplayClass(UUID identifier) {
        if (linkStyles.containsKey(identifier)) {
            linkStyles.remove(identifier);
        }
    }

    public static Map<UUID, TreeNodeLinkClass> getLinkStylesList() {
        return linkStyles;
    }

    public static TreeNodeLinkClass getLinkStyle(UUID identifier) {
        if (identifier == StandardLinkStyleIdentifier) {
            return STANDARD_LINK_STYLE;
        }
        if (linkStyles.containsKey(identifier)) {
            return linkStyles.get(identifier);
        }
        return null;
    }

    public static void setSelected(TreeNodeClass displayClass) {
        selected = displayClass;
    }

    public static TreeNodeClass getSelected() {
        return selected;
    }

    public static void unselect() {
        selected = null;
    }

    public static boolean selectedLinkDisplayClass() {
        return selected instanceof TreeNodeLinkClass;
    }

    public static void loadInstance(TreeInstance instance) {
        styles.clear();
        linkStyles.clear();

        styles = instance.getStyles();
        linkStyles = instance.getLinkStyles();
    }

}
