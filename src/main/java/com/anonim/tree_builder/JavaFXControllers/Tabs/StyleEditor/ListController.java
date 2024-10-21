package com.anonim.tree_builder.JavaFXControllers.Tabs.StyleEditor;

import com.anonim.tree_builder.Application;
import com.anonim.tree_builder.DisplayClasses;
import com.anonim.tree_builder.Enums.StandardColors;
import com.anonim.tree_builder.JavaFXControllers.Control.UnselectStyleButtonController;
import com.anonim.tree_builder.Tree;
import com.anonim.tree_builder.TreeNodes.TreeNodeClass;
import com.anonim.tree_builder.TreeNodes.TreeNodeLinkClass;
import com.anonim.tree_builder.TreeNodes.TreeNodeRoot;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

public class ListController {

    private static void updateList(VBox box, Map<UUID, TreeNodeClass> styles, Map<UUID, TreeNodeLinkClass> linkStyles) {
        styles.forEach((identifier, item) -> {
            if (item.isInitialized()) {
                String content = item.getDisplayName();
                Button itemButton = new Button("(NC) " + content);
                itemButton.setPrefWidth(box.getWidth());
                itemButton.addEventHandler(ActionEvent.ACTION, (event) -> {
                    DisplayClasses.setSelected(item);
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
                    DisplayClasses.setSelected(item);
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
        VBox box = Application.controller.styleEditorListBox;

        box.getChildren().clear();

        updateList(box, styles, linkStyles);
        UnselectStyleButtonController.update();
    }

}
