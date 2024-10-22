package com.anonim.tree_builder.JavaFXControllers.Control;

import com.anonim.tree_builder.Main;
import com.anonim.tree_builder.DisplayClasses;
import com.anonim.tree_builder.TreeNodes.TreeNodeClass;
import javafx.event.ActionEvent;

public class StyleEditorNameFieldAction {

    public static void action (ActionEvent event) {
        String newContent = Main.controller.styleEditorNameField.getText();
        TreeNodeClass style = DisplayClasses.getSelected();

        if (style != null) {
            style.setDisplayName(newContent);
            Main.update();
        }
    }

}
