package com.anonim.tree_builder.JavaFXControllers;

import com.anonim.tree_builder.Application;
import com.anonim.tree_builder.Canvas.TreeCanvas;
import com.anonim.tree_builder.Tree;
import com.anonim.tree_builder.TreeNodes.TreeNodeRoot;

public class ModeStatusController {

    private static final String STANDARD_TEXT = "";

    private static final String LINK_TEXT = "Currently Linking; ";

    public static void updateModeStatus() {
        if (TreeCanvas.currentlyConnecting) {
            System.out.println("Displaying modeStatus");
            String text = LINK_TEXT;
            text += TreeCanvas.connectingParent.getContent();
            Application.controller.updateModeStatusText(text);
        } else {
            Application.controller.updateModeStatusText(STANDARD_TEXT);
        }
    }

}
