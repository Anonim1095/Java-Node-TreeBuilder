package com.anonim.tree_builder.JavaFXControllers.Control;

import com.anonim.tree_builder.Application;
import com.anonim.tree_builder.Canvas.TreeCanvas;
import com.anonim.tree_builder.DisplayClasses;
import com.anonim.tree_builder.Enums.StandardStyle;
import com.anonim.tree_builder.JavaFXControllers.Tabs.StyleEditor.StyleEditorController;
import com.anonim.tree_builder.TreeNodes.TreeNodeClass;
import javafx.event.ActionEvent;

public class UnselectStyleButtonController {

    public static void update() {
        TreeNodeClass selected = DisplayClasses.getSelected();
        if (selected != null) {
            Application.controller.styleEditorUnselectStyleButton.setVisible(true);
        } else {
            Application.controller.styleEditorUnselectStyleButton.setVisible(false);
        }
        TreeCanvas.display(Application.controller.treeCanvas);
    }

    public static void action(ActionEvent event) {
        DisplayClasses.unselect();
        update();
    }

}
