package com.anonim.tree_builder.JavaFXControllers.Control;

import com.anonim.tree_builder.Main;
import com.anonim.tree_builder.Canvas.TreeCanvas;
import com.anonim.tree_builder.DisplayClasses;
import com.anonim.tree_builder.TreeNodes.TreeNodeClass;
import javafx.event.ActionEvent;

public class UnselectStyleButtonController {

    public static void update() {
        TreeNodeClass selected = DisplayClasses.getSelected();
        if (selected != null) {
            Main.controller.styleEditorUnselectStyleButton.setVisible(true);
        } else {
            Main.controller.styleEditorUnselectStyleButton.setVisible(false);
        }
        TreeCanvas.display(Main.controller.treeCanvas);
    }

    public static void action(ActionEvent event) {
        DisplayClasses.unselect();
        update();
    }

}
