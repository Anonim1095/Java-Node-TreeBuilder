package com.anonim.tree_builder.JavaFXControllers.Control;

import com.anonim.tree_builder.DisplayClasses;
import com.anonim.tree_builder.Enums.StandardStyle;
import com.anonim.tree_builder.JavaFXControllers.Tabs.StyleEditor.ListController;
import com.anonim.tree_builder.JavaFXControllers.Tabs.StyleEditor.StyleEditorController;
import com.anonim.tree_builder.TreeNodes.TreeNodeClass;
import com.anonim.tree_builder.TreeNodes.TreeNodeRoot;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.List;

public class DeleteStyleButtonController {

    public static void action(ActionEvent event) {
        TreeNodeClass selected = DisplayClasses.getSelected();
        List<TreeNodeRoot> toDelete = new ArrayList<>();

        selected.getUsedIn().forEach((uuid, item) -> {
            if (item.itIsLinkNode()) {
                item.setDisplayClass(StandardStyle.STANDARD_LINK_STYLE);
            } else {
                item.setDisplayClass(StandardStyle.STANDARD_STYLE);
            }
            toDelete.add(item);
        });

        toDelete.forEach(selected::deleteNodeFromUsing);

        if (selected.isLinkClass()) {
            DisplayClasses.removeLinkDisplayClass(selected.getIdentifier());
        } else {
            System.out.println("Attempting to remove style with ind " + selected.getIdentifier());
            DisplayClasses.removeDisplayClass(selected.getIdentifier());
        }
        DisplayClasses.unselect();
        UnselectStyleButtonController.update();
        ListController.update();
    }

}
