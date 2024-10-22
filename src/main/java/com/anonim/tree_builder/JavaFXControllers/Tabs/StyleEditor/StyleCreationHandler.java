package com.anonim.tree_builder.JavaFXControllers.Tabs.StyleEditor;

import com.anonim.tree_builder.Main;
import com.anonim.tree_builder.DisplayClasses;
import com.anonim.tree_builder.JavaFXControllers.Control.UnselectStyleButtonController;
import com.anonim.tree_builder.TreeNodes.TreeNodeClass;
import com.anonim.tree_builder.TreeNodes.TreeNodeLinkClass;
import javafx.scene.paint.Color;

public class StyleCreationHandler {

    public static void createNewNodeStyle() {
        Color tileColor = Main.controller.styleEditorBackgroundColorPicker.getValue();
        Color textColor = Main.controller.styleEditorForegroundColorPicker.getValue();
        String displayName = Main.controller.styleEditorNameField.getText();

        if (displayName != null) {
            TreeNodeClass style = new TreeNodeClass(displayName,tileColor,textColor);
            DisplayClasses.insertNewClass(style);
            DisplayClasses.setSelected(style);
            ListController.update();
        }
        UnselectStyleButtonController.update();
    }

    public static void createNewNodeLinkStyle() {
        Color tileColor = Main.controller.styleEditorBackgroundColorPicker.getValue();
        Color textColor = Main.controller.styleEditorForegroundColorPicker.getValue();
        String displayName = Main.controller.styleEditorNameField.getText();

        if (displayName != null) {
            TreeNodeLinkClass style = new TreeNodeLinkClass(displayName,tileColor,textColor);
            DisplayClasses.insertNewLinkClass(style);
            DisplayClasses.setSelected(style);
            ListController.update();
        }
        UnselectStyleButtonController.update();
    }

}
