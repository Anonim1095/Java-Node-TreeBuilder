package com.anonim.tree_builder.JavaFXControllers.Tabs.NodeEditor;

import com.anonim.tree_builder.Application;
import com.anonim.tree_builder.Canvas.TreeCanvas;
import com.anonim.tree_builder.DisplayClasses;
import com.anonim.tree_builder.Enums.StandardStyle;
import com.anonim.tree_builder.Tree;
import com.anonim.tree_builder.TreeNodes.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class NodeEditorController {

    private static final AnchorPane NODE_EDITOR_PANE = Application.controller.treeNodePane;

    // FIELDS
    private static final TextField CONTENT_FIELD = Application.controller.contentInputField;
    private static final TextField IDENTIFIER_FIELD = Application.controller.identifierFieldReadonly;
    private static final SplitMenuButton DISPLAY_CLASSES_SPLIT = Application.controller.displayClassSelector;

    private static final VBox PARENT_PANE = Application.controller.parentListBox;
    private static final VBox CHILD_PANE = Application.controller.childListBox;

    public static void update() {
        TreeNodeRoot selected = Tree.getSelectedNode();
        if (selected != null) {
            NODE_EDITOR_PANE.setVisible(true);
            display(selected);
            ChildController.update();
            ParentController.update();
            TreeCanvas.display(Application.controller.treeCanvas);
        } else {
            NODE_EDITOR_PANE.setVisible(false);
        }
    }

    private static void display(TreeNodeRoot node) {
        boolean isLinkNode = node.itIsLinkNode();

        IDENTIFIER_FIELD.setText(node.getIdentifier().toString());
        if (node.itIsLinkNode() && !node.itIsDependentLinkNode()) {
            CONTENT_FIELD.setText("JOINT; NO CONTENT FIELD.");
            CONTENT_FIELD.setDisable(true);
        } else {
            CONTENT_FIELD.setText(node.getContent());
            CONTENT_FIELD.setDisable(false);
        }

        // Clearing Menu list
        DISPLAY_CLASSES_SPLIT.getItems().clear();
        if (!isLinkNode) {
            Map<UUID, TreeNodeClass> displayClasses = new HashMap<>();
            displayClasses.put(StandardStyle.STANDARD_NODE_STYLE_IDENTIFIER,StandardStyle.STANDARD_STYLE);
            displayClasses.putAll(DisplayClasses.getStylesList());
            displayClasses.forEach((identifier, item) -> {
                if (item.isInitialized()) {
                    MenuItem option = new MenuItem(item.getDisplayName());
                    DISPLAY_CLASSES_SPLIT.getItems().add(option);
                    option.addEventHandler(ActionEvent.ACTION, (event) -> {
                        node.getDisplayClass().deleteNodeFromUsing(node);
                        node.setDisplayClass(item);
                        item.addNodeUsesThisStyle(node);
                    });
                }
            });
        } else {
            Map<UUID, TreeNodeLinkClass> displayClasses = new HashMap<>();
            displayClasses.put(StandardStyle.STANDARD_LINK_STYLE_IDENTIFIER,StandardStyle.STANDARD_LINK_STYLE);
            displayClasses.putAll(DisplayClasses.getLinkStylesList());
            displayClasses.forEach((identifier, item) -> {
                if (item.isInitialized()) {
                    MenuItem option = new MenuItem(item.getDisplayName());
                    DISPLAY_CLASSES_SPLIT.getItems().add(option);
                    option.addEventHandler(ActionEvent.ACTION, (event) -> {
                        node.getDisplayClass().deleteNodeFromUsing(node);
                        node.setDisplayClass(item);
                        item.addNodeUsesThisStyle(node);
                    });
                }
            });
        }
    }

    public static void inputFieldChanged(ActionEvent event) {
        String newContent = CONTENT_FIELD.getText();
        TreeNodeRoot selected = Tree.getSelectedNode();
        if (selected != null) {
            selected.setContent(newContent);
            TreeCanvas.display(Application.controller.treeCanvas);
        } else {
            update();
        }
    }

}
