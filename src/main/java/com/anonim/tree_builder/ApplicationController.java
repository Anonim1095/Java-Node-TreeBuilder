package com.anonim.tree_builder;

import com.anonim.tree_builder.Canvas.TreeCanvas;
import com.anonim.tree_builder.JavaFXControllers.*;
import com.anonim.tree_builder.JavaFXControllers.Control.*;
import com.anonim.tree_builder.JavaFXControllers.Tabs.NodeEditor.NodeEditorController;
import com.anonim.tree_builder.JavaFXControllers.Tabs.StyleEditor.StyleCreationHandler;
import com.anonim.tree_builder.JavaFXControllers.Tabs.StyleEditor.StyleEditorController;
import com.anonim.tree_builder.JsonHandler.NewFileAction;
import com.anonim.tree_builder.JsonHandler.OpenFileAction;
import com.anonim.tree_builder.JsonHandler.SaveFileAction;
import com.anonim.tree_builder.TreeNodes.TreeInstance;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ApplicationController {

    // TREE CANVAS
    @FXML
    public Canvas treeCanvas;
    public Label rightStatus;
    public Label leftStatus;
    public Label mode;
    public Button connectNodesButton;
    public Button createLinkButton;
    public Button deleteNodeButton;
    public Button newJointButton;
    // NODE EDITOR
    public AnchorPane treeNodePane;
    public AnchorPane parentList;
    public AnchorPane childList;

    public VBox parentListBox;
    public VBox childListBox;

    public TextField contentInputField;
    public TextField identifierFieldReadonly;
    public SplitMenuButton displayClassSelector;
    // STYLE EDITOR
    public AnchorPane styleEditorList;
    public VBox styleEditorListBox;

    public GridPane styleEditorPropertiesPane;
    public TextField styleEditorNameField;

    public ColorPicker styleEditorBackgroundColorPicker;
    public ColorPicker styleEditorForegroundColorPicker;

    public Button styleEditorCreateNewStyleButton;
    public Button styleEditorCreateNewLinkStyleButton;
    public Button styleEditorCreateDeleteStyleButton;
    public Button styleEditorUnselectStyleButton;

    private double startX;
    private double startY;

    @FXML
    private void initialize() {
        System.out.println("Initializtion!");

    }

    @FXML
    protected void onTreeCanvasMousePressed(MouseEvent event) {
        startX = event.getX();
        startY = event.getY();
        TreeCanvasController.mousePressed(treeCanvas, event);
        System.out.println("onTreeCanvasMousePressed");
    }

    @FXML
    protected void onTreeCanvasContextMenu() {
        TreeCanvasController.contextMenu(treeCanvas);
        System.out.println("onTreeCanvasContextMenu");
    }

    @FXML
    protected void onTreeCanvasMouseDragged(MouseEvent event) {
        double currentX = event.getX();
        double currentY = event.getY();

        double deltaX = startX - currentX;
        double deltaY = startY - currentY;


        TreeCanvasController.mouseDragged(treeCanvas, deltaX, deltaY, event);

        startX = currentX;
        startY = currentY;

        System.out.println("Mouse dragged by: X = " + deltaX + ", Y = " + deltaY);
        System.out.println("onTreeCanvasMouseDragged");
    }

    @FXML
    protected void onTreeCanvasScroll(ScrollEvent event) {
        double deltaX = event.getDeltaX();
        double deltaY = event.getDeltaY();

        TreeCanvasController.scroll(treeCanvas, deltaX, deltaY, event);

        System.out.println("onTreeCanvasScroll");
    }

    @FXML
    protected void onTreeCanvasMouseReleased(MouseEvent event) {
        TreeCanvas.releaseMouse(treeCanvas, event);
        System.out.println("onTreeCanvasMouseReleased");
    }

    // NEW NODE BUTTON
    @FXML
    private Button newNodeButton;

    @FXML
    protected void onNewNodeButtonAction(ActionEvent event) {
        NewNodeButtonController.action(treeCanvas);
        System.out.println("onNewNodeButtonAction");
    }

    @FXML
    protected void newJointButtonAction(ActionEvent event) {
        CreateJointButtonController.action(treeCanvas);
        System.out.println("newJointButtonAction");
    }

    // RIGHT STATUS
    public void updateRightStatusText(String text) {
        rightStatus.setText(text);
    }

    public static void updateRightStatus(ApplicationController controller, String text) {
        controller.updateRightStatusText(text);
    }

    // LEFT STATUS
    public void updateLeftStatusText(String text) {
        leftStatus.setText(text);
    }

    public static void updateLeftStatus(ApplicationController controller, String text) {
        controller.updateRightStatusText(text);
    }

    // MODE STATUS
    public void updateModeStatusText(String text) {
        mode.setText(text);
    }

    public static void updateModeStatus(ApplicationController controller, String text) {
        controller.updateRightStatusText(text);
    }

    // CONNECT NODES BUTTON
    @FXML
    protected void connectNodesButtonAction(ActionEvent event) {
        CreateConnectionButtonController.action(treeCanvas, event);
        System.out.println("connectNodesButtonAction");
    }

    // CREATE LINK BUTTON
    @FXML
    protected void createLinkButtonAction(ActionEvent event) {
        CreateLinkButtonController.action(treeCanvas, event);
        System.out.println("createLinkButtonAction");
    }

    public static void changeVisibilityOfCreateLinkButton(boolean value) {
        Application.controller.createLinkButton.setVisible(value);
    }

    // DELETE LINK BUTTON
    @FXML
    protected void deleteNodeButtonAction(ActionEvent event) {
        DeleteNodeButtonController.action(treeCanvas, event);
        System.out.println("deleteNodeButtonAction");
    }

    public static void changeVisibilityOfDeleteNodeButton(boolean value) {
        Application.controller.deleteNodeButton.setVisible(value);
    }

    // NODE EDITOR
    @FXML
    protected void contentInputFieldTextAction(ActionEvent event) {
        NodeEditorController.inputFieldChanged(event);
        System.out.println("contentInputFieldTextChanged");
    }

    // STYLE EDITOR
    @FXML
    protected void styleEditorNameFieldTextAction(ActionEvent event) {
        StyleEditorNameFieldAction.action(event);
        System.out.println("styleEditorNameFieldTextAction");
    }

    @FXML
    protected void styleEditorBackgroundColorPickerAction(ActionEvent event) {
        StyleEditorColorPickerAction.actionBackground(event);
        System.out.println("styleEditorBackgroundColorPickerAction");
    }

    @FXML
    protected void styleEditorForegroundColorPickerAction(ActionEvent event) {
        StyleEditorColorPickerAction.actionForeground(event);
        System.out.println("styleEditorForegroundColorPickerAction");
    }

    @FXML
    protected void styleEditorCreateNewStyleButtonAction(ActionEvent event) {
        StyleCreationHandler.createNewNodeStyle();
        System.out.println("styleEditorCreateNewStyleButtonAction");
    }

    @FXML
    protected void styleEditorCreateNewLinkStyleButtonAction(ActionEvent event) {
        StyleCreationHandler.createNewNodeLinkStyle();
        System.out.println("styleEditorCreateNewLinkStyleButtonAction");
    }

    @FXML
    protected void styleEditorUnselectStyleButtonAction(ActionEvent event) {
        UnselectStyleButtonController.action(event);
        System.out.println("styleEditorUnselectStyleButtonAction");
    }

    @FXML
    protected void styleEditorCreateDeleteStyleButtonAction(ActionEvent event) {
        DeleteStyleButtonController.action(event);
        System.out.println("styleEditorUnselectStyleButtonAction");
    }

    // FILE MENU!!!!

    @FXML
    protected void newFileAction(ActionEvent event) {
        NewFileAction.action(event);
        System.out.println("newFileAction");
    }

    @FXML
    protected void openFileAction(ActionEvent event) {
        OpenFileAction.action(event);
        System.out.println("openFileAction");
    }

    @FXML
    protected void closeFileAction(ActionEvent event) {
        TreeInstance instance = new TreeInstance();
        Tree.loadInstance(instance);
        System.out.println("closeFileAction");
    }

    @FXML
    protected void saveFileAction(ActionEvent event) {
        SaveFileAction.action(event);
        System.out.println("saveFileAction");
    }

    @FXML
    protected void saveAsFileAction(ActionEvent event) {
        SaveFileAction.actionAdvanced(event);
        System.out.println("saveAsFileAction");
    }

}