package com.anonim.tree_builder.JsonHandler;

import javafx.stage.FileChooser.ExtensionFilter;

import java.security.cert.Extension;
import java.util.ArrayList;
import java.util.List;

public class TreeNodeExtensionFilter {

    public static final String extension = "(*.tnse)";
    private static ExtensionFilter filter = new ExtensionFilter("Extensions of Tree Nodes", extension);

    public static ExtensionFilter getFilter() {
        return filter;
    }
}
