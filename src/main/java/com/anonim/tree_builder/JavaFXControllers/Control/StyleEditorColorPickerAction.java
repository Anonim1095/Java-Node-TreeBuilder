package com.anonim.tree_builder.JavaFXControllers.Control;

import com.anonim.tree_builder.Application;
import com.anonim.tree_builder.DisplayClasses;
import com.anonim.tree_builder.TreeNodes.TreeNodeClass;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;

import java.util.List;

public class StyleEditorColorPickerAction {

    public static void actionBackground (ActionEvent event) {
        Color newValue = Application.controller.styleEditorBackgroundColorPicker.getValue();
        TreeNodeClass style = DisplayClasses.getSelected();

        if (style != null) {
            style.setBackgroundColor(newValue);
            Application.update();
        }
    }

    public static void actionForeground (ActionEvent event) {
        Color newValue = Application.controller.styleEditorForegroundColorPicker.getValue();
        TreeNodeClass style = DisplayClasses.getSelected();

        if (style != null) {
            style.setForegroundColor(newValue);
            Application.update();
        }
    }

}
