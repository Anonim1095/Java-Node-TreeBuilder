package com.anonim.tree_builder.JsonHandler;

import com.anonim.tree_builder.Tree;
import com.anonim.tree_builder.TreeNodes.TreeInstance;
import javafx.event.ActionEvent;

public class NewFileAction {

    public static void action(ActionEvent event) {
        Tree.loadInstance(new TreeInstance());
        TreeInstancesHandler.unselect();
    }

}
