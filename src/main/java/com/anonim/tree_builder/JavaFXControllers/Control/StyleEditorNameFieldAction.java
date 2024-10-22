package com.anonim.tree_builder.JavaFXControllers.Control;

import com.anonim.tree_builder.Application;
import com.anonim.tree_builder.DisplayClasses;
import com.anonim.tree_builder.TreeNodes.TreeNodeClass;
import javafx.event.ActionEvent;

public class StyleEditorNameFieldAction {

    public static void action (ActionEvent event) {
        String newContent = Application.controller.styleEditorNameField.getText();
        TreeNodeClass style = DisplayClasses.getSelected();

        if (style != null) {
            style.setDisplayName(newContent);
            Application.update();
        }
    }

}
