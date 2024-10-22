package com.anonim.tree_builder.Canvas;

import com.anonim.tree_builder.Enums.StandardColors;
import com.anonim.tree_builder.Tree;
import com.anonim.tree_builder.TreeNodes.TreeNode;
import com.anonim.tree_builder.TreeNodes.TreeNodeJoint;
import com.anonim.tree_builder.TreeNodes.TreeNodeLink;
import com.anonim.tree_builder.TreeNodes.TreeNodeRoot;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static com.anonim.tree_builder.Canvas.BaseCanvas.PADDING;
import static com.anonim.tree_builder.Canvas.BaseCanvas.STANDARD_FONT_FAMILY;

public class TreeCanvas {

    final static int MAX_SIZE = 2000;
    final static float MIN_ZOOM = 0.25f;
    final static float MAX_ZOOM = 5.0f;

    public static double displayX = 0;
    public static double displayY = 0;

    public static float displayZoom = 1.0f;
    private static boolean currentlyDraggingTile = false;
    private static boolean dragging = false;
    private static TreeNodeRoot draggingTile = null;

    public static boolean currentlyConnecting = false;
    public static TreeNodeRoot connectingParent;

    private static boolean inBoundaries(double posX, double posY, double boundX1, double boundY1, double boundX2, double boundY2) {
        return ((posX >= boundX1 && posX <= boundX2) && (posY >= boundY1 && posY <= boundY2));
    }

    public static TreeNodeRoot overlapsNode(double targetPosX, double targetPosY) {

        HashMap<UUID, TreeNode> TreeNodes = Tree.getTreeNodes();
        HashMap<UUID, TreeNodeLink> TreeNodesLinks = Tree.getTreeNodesLinks();
        HashMap<UUID, TreeNodeJoint> TreeNodesJoints = Tree.getTreeNodesJoints();

        List<TreeNodeRoot> Nodes = new ArrayList<>(TreeNodes.values());
        Nodes.addAll(TreeNodesLinks.values());

        for (TreeNodeRoot node : Nodes) {

            // Getting Size
            double textSize = node.getDisplayClass().getTextSize() * displayZoom;
            Font textFont = new Font(STANDARD_FONT_FAMILY, textSize);

            Text content = new Text(node.getContent());
            if (content.getText().isEmpty()) {content.setText("(Empty)");}
            content.setFont(textFont);

            double contentWidth = content.getBoundsInLocal().getWidth();
            double contentHeight = content.getBoundsInLocal().getHeight();

            // Declaring
            double nodePosX = (node.getNodeX()+displayX) * displayZoom-2;
            double nodePosY = (node.getNodeY()+displayY) * displayZoom-2;

            double nodeSizeX = contentWidth+PADDING*2;
            double nodeSizeY = contentHeight+PADDING*2;
            if (inBoundaries(targetPosX,targetPosY,nodePosX,nodePosY,nodePosX+nodeSizeX,nodePosY+nodeSizeY)) {
                return node;
            }

        }

        for (TreeNodeJoint item : TreeNodesJoints.values()) {
            // Getting Size
            double textSize = item.getDisplayClass().getTextSize() * displayZoom;
            Font textFont = new Font(STANDARD_FONT_FAMILY, textSize);

            Text content = new Text("JO");
            content.setFont(textFont);

            double contentWidth = content.getBoundsInLocal().getWidth();

            // Declaring
            double nodePosX = ((item.getNodeX()-contentWidth/2)+displayX) * displayZoom-2;
            double nodePosY = ((item.getNodeY()-contentWidth/2)+displayY) * displayZoom-2;

            double nodeSizeX = contentWidth+PADDING;
            double nodeSizeY = contentWidth+PADDING;
            if (inBoundaries(targetPosX,targetPosY,nodePosX,nodePosY,nodePosX+nodeSizeX,nodePosY+nodeSizeY)) {
                return (TreeNodeRoot) item;
            }
        };

        return null;

    }

    public static void display(Canvas canvas) {
        GraphicsContext context = canvas.getGraphicsContext2D();

        HashMap<UUID, TreeNode> TreeNodes = Tree.getTreeNodes();
        HashMap<UUID, TreeNodeLink> TreeNodesLinks = Tree.getTreeNodesLinks();
        HashMap<UUID, TreeNodeJoint> TreeNodesJoints = Tree.getTreeNodesJoints();

        context.setFill(StandardColors.BACKGROUND.getColor());
        context.fillRect(0,0, canvas.getWidth()*5, canvas.getHeight()*5);

        TreeNodeRoot selectedNode = Tree.getSelectedNode();

        // Displaying nodes connections!
        TreeNodes.forEach((uuid,node) -> {

            if (node.hasParent()) {
                ArrayList<TreeNodeRoot> parents = node.getParentArray();

                for (TreeNodeRoot element : parents) {
                    BaseCanvas.drawConnection(context, element, node, displayZoom, displayX, displayY);
                    System.out.println("Drawing connection");
                }
            }
        });

        TreeNodesLinks.forEach((uuid, node) -> {

            if (node.hasParent()) {
                ArrayList<TreeNodeRoot> parents = node.getParentArray();

                for (TreeNodeRoot element : parents) {
                    BaseCanvas.drawConnection(context, element, node, displayZoom, displayX, displayY);
                    System.out.println("Drawing connection");
                }
            }
        });

        TreeNodesJoints.forEach((uuid, node) -> {

            if (node.hasParent()) {
                ArrayList<TreeNodeRoot> parents = node.getParentArray();

                for (TreeNodeRoot element : parents) {
                    BaseCanvas.drawConnection(context, element, node, displayZoom, displayX, displayY);
                    System.out.println("Drawing connection");
                }
            }
        });

        // Displaying nodes!
        TreeNodes.forEach((uuid,node) -> {
            boolean selected = false;
            if (selectedNode != null) {
                selected = (node.getIdentifier().equals(selectedNode.getIdentifier()));
            }
            BaseCanvas.drawNode(context, node, displayZoom, displayX, displayY, selected);
        });

        // Displaying nodes links!
        TreeNodesLinks.forEach((uuid,link) -> {
            boolean selected = false;
            if (selectedNode != null) {
                selected = (link.getIdentifier().equals(selectedNode.getIdentifier()));
            }
            BaseCanvas.drawNodeLink(context, link, displayZoom, displayX, displayY, selected);
        });

        // Displaying nodes joints!
        TreeNodesJoints.forEach((uuid,link) -> {
            boolean selected = false;
            if (selectedNode != null) {
                selected = (link.getIdentifier().equals(selectedNode.getIdentifier()));
            }
            BaseCanvas.drawNodeJoint(context, link, displayZoom, displayX, displayY, selected);
        });

        System.out.println("X: "+displayX+"; Y: "+displayY+"; Zoom: "+displayZoom);
    }

    private static void dragTile(Canvas canvas, float deltaX, float deltaY, MouseEvent event) {
        double baseX = draggingTile.getNodeX();
        double baseY = draggingTile.getNodeY();

        draggingTile.setNodeX(baseX - deltaX/displayZoom);
        draggingTile.setNodeY(baseY - deltaY/displayZoom);
    }

    private static void dragScreen(Canvas canvas, float deltaX, float deltaY, MouseEvent event) {
        double changeX = displayX - deltaX / displayZoom;
        double changeY = displayY - deltaY / displayZoom;

        double maxX = MAX_SIZE / displayZoom;
        double maxY = MAX_SIZE / displayZoom;

        if (changeX < -MAX_SIZE) { changeX = -MAX_SIZE; }
        if (changeY < -MAX_SIZE) { changeY = -MAX_SIZE; }

        if (changeX > maxX) { changeX = maxX; }
        if (changeY > maxY) { changeY = maxY; }

        displayX = changeX;
        displayY = changeY;
    }

    public static void drag(Canvas canvas, float deltaX, float deltaY, MouseEvent event) {
        if (currentlyDraggingTile) {
            dragTile(canvas, deltaX, deltaY, event);
        } else if (!dragging) {
            TreeNodeRoot overlaps = overlapsNode(event.getX(), event.getY());
            if (overlaps != null) {
                currentlyDraggingTile = true;
                draggingTile = overlaps;
                dragTile(canvas, deltaX, deltaY, event);
            } else {
                dragScreen(canvas, deltaX, deltaY, event);
            }
        } else {
            dragScreen(canvas, deltaX, deltaY, event);
        }

        dragging = true;
        display(canvas);
    }

    public static void releaseMouse(Canvas canvas, MouseEvent event) {
        currentlyDraggingTile = false;
        dragging = false;
    }

    public static void zoom(Canvas canvas, boolean zoomDirection) {
        double zoomFactor = 1.1;

        if (zoomDirection) {
            displayZoom *= zoomFactor;
        } else {
            displayZoom /= zoomFactor;
        }

        if (displayZoom > MAX_ZOOM) {
            displayZoom = MAX_ZOOM;
        }
        if (displayZoom < MIN_ZOOM) {
            displayZoom = MIN_ZOOM;
        }

        display(canvas);
    }

    public static void connect(Canvas canvas, TreeNodeRoot parent, TreeNodeRoot child) {
        child.addParent(parent);
        TreeCanvas.display(canvas);
    }

}
