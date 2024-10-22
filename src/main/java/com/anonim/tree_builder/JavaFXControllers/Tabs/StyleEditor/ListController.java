package com.anonim.tree_builder.JavaFXControllers.Tabs.StyleEditor;

import com.anonim.tree_builder.Main;
import com.anonim.tree_builder.ApplicationController;
import com.anonim.tree_builder.DisplayClasses;
import com.anonim.tree_builder.Enums.StandardColors;
import com.anonim.tree_builder.JavaFXControllers.Control.UnselectStyleButtonController;
import com.anonim.tree_builder.TreeNodes.TreeNodeClass;
import com.anonim.tree_builder.TreeNodes.TreeNodeLinkClass;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.Map;
import java.util.UUID;

public class ListController {

    public static void updateStyleDashboard() {
        TreeNodeClass selected = DisplayClasses.getSelected();
        if (selected != null) {
            if (selected.isInitialized()) {
                ApplicationController controller = Main.controller;

                controller.styleEditorNameField.setText(selected.getDisplayName());
                controller.styleEditorForegroundColorPicker.setValue(selected.getForegroundColor());
                controller.styleEditorBackgroundColorPicker.setValue(selected.getBackgroundColor());
            }
        }
    }

    public static void selectStyle(TreeNodeClass style) {
        DisplayClasses.setSelected(style);
        UnselectStyleButtonController.update();
        updateStyleDashboard();
    }

    public static void selectStyleLink(TreeNodeLinkClass style) {
        DisplayClasses.setSelected(style);
        UnselectStyleButtonController.update();
        updateStyleDashboard();
    }

    private static void updateList(VBox box, Map<UUID, TreeNodeClass> styles, Map<UUID, TreeNodeLinkClass> linkStyles) {
        styles.forEach((identifier, item) -> {
            if (item.isInitialized()) {
                String content = item.getDisplayName();
                Button itemButton = new Button("(NC) " + content);
                itemButton.setPrefWidth(box.getWidth());
                itemButton.addEventHandler(ActionEvent.ACTION, (event) -> {
                    selectStyle(item);
                    UnselectStyleButtonController.update();
                });
                Color color = StandardColors.EMPTY.getColor();
                itemButton.setTextFill(color);
                box.getChildren().add(itemButton);
            }
        });
        Separator separator = new Separator(Orientation.HORIZONTAL);
        box.getChildren().add(separator);
        linkStyles.forEach((identifier, item) -> {
            if (item.isInitialized()) {
                String content = item.getDisplayName();
                Button itemButton = new Button("(LC) " + content);
                itemButton.setPrefWidth(box.getWidth());
                itemButton.addEventHandler(ActionEvent.ACTION, (event) -> {
                    selectStyleLink(item);
                    UnselectStyleButtonController.update();
                });
                Color color = StandardColors.EMPTY.getColor();
                itemButton.setTextFill(color);
                box.getChildren().add(itemButton);
            }
        });
    }

    public static void update() {
        Map<UUID, TreeNodeClass> styles = DisplayClasses.getStylesList();
        Map<UUID, TreeNodeLinkClass> linkStyles = DisplayClasses.getLinkStylesList();
        VBox box = Main.controller.styleEditorListBox;

        box.getChildren().clear();

        updateList(box, styles, linkStyles);
        UnselectStyleButtonController.update();
    }

}
