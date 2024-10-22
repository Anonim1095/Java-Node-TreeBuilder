package com.anonim.tree_builder.JavaFXControllers;

import com.anonim.tree_builder.Main;
import com.anonim.tree_builder.Canvas.TreeCanvas;

public class ModeStatusController {

    private static final String STANDARD_TEXT = "";

    private static final String LINK_TEXT = "Currently Linking; ";

    public static void updateModeStatus() {
        if (TreeCanvas.currentlyConnecting) {
            System.out.println("Displaying modeStatus");
            String text = LINK_TEXT;
            text += TreeCanvas.connectingParent.getContent();
            Main.controller.updateModeStatusText(text);
        } else {
            Main.controller.updateModeStatusText(STANDARD_TEXT);
        }
    }

}
