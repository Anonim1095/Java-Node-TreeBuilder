package com.anonim.tree_builder.JsonHandler;

import java.io.File;

public class TreeInstancesHandler {

    public static File currentFile;

    public static File getCurrentFile() {
        return currentFile;
    }

    public static void setCurrentFile(File currentFile) {
        TreeInstancesHandler.currentFile = currentFile;
    }

    public static void unselect() {
        currentFile = null;
    }
}
