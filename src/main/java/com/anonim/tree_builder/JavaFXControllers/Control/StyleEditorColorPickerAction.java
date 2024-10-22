package com.anonim.tree_builder.JavaFXControllers.Control;

import com.anonim.tree_builder.Main;
import com.anonim.tree_builder.DisplayClasses;
import com.anonim.tree_builder.TreeNodes.TreeNodeClass;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;

public class StyleEditorColorPickerAction {

    public static void actionBackground (ActionEvent event) {
        Color newValue = Main.controller.styleEditorBackgroundColorPicker.getValue();
        TreeNodeClass style = DisplayClasses.getSelected();

        if (style != null) {
            style.setBackgroundColor(newValue);
            Main.update();
        }
    }

    public static void actionForeground (ActionEvent event) {
        Color newValue = Main.controller.styleEditorForegroundColorPicker.getValue();
        TreeNodeClass style = DisplayClasses.getSelected();

        if (style != null) {
            style.setForegroundColor(newValue);
            Main.update();
        }
    }

}
