package com.anonim.tree_builder.JsonHandler;

import com.anonim.tree_builder.Main;
import com.anonim.tree_builder.Tree;
import com.anonim.tree_builder.TreeNodes.TreeInstance;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;

import java.io.*;

public class SaveFileAction {

    private static Gson GSON = new Gson();

    private static void save(File file, JsonObject object) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            Writer writer = new FileWriter(file);
            String content = GSON.toJson(object);
            writer.write(content);
            writer.close();

        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }

    public static void actionAdvanced(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Tree Node");
        fileChooser.setSelectedExtensionFilter(TreeNodeExtensionFilter.getFilter());
        File choosed = fileChooser.showSaveDialog(Main.stage);

        if (choosed != null) {
            TreeInstance instance = Tree.saveInstance();
            save(choosed, instance.Object.get());
            TreeInstancesHandler.setCurrentFile(choosed);
        }
    }

    public static void action(ActionEvent event) {
        if (TreeInstancesHandler.getCurrentFile() == null) {
            actionAdvanced(event);
        } else {
            TreeInstance instance = Tree.saveInstance();
            save(TreeInstancesHandler.getCurrentFile(), instance.Object.get());
        }
    }

}
