package com.anonim.tree_builder.JsonHandler;

import com.anonim.tree_builder.Main;
import com.anonim.tree_builder.Tree;
import com.anonim.tree_builder.TreeNodes.TreeInstance;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class OpenFileAction {

    private static Gson GSON = new Gson();

    public static void action (ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Tree Node");
        fileChooser.setSelectedExtensionFilter(TreeNodeExtensionFilter.getFilter());
        File choosed = fileChooser.showOpenDialog(Main.stage);
        if (choosed != null) {
            try {
                Reader reader = new FileReader(choosed);
                JsonObject root = GSON.fromJson(reader, JsonObject.class);
                TreeInstance instance = new TreeInstance(root);
                if (instance != null) {
                    Tree.loadInstance(instance);
                    TreeInstancesHandler.setCurrentFile(choosed);
                }
            } catch (IOException e) {
                System.out.println(e.getStackTrace());
            }
        }
    }

}
