package com.anonim.tree_builder.Canvas;

import com.anonim.tree_builder.Enums.StandardColors;
import com.anonim.tree_builder.TreeNodes.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BaseCanvas {

    static double BASE_PADDING = 4;
    static double BASE_LINK_PADDING = 2;

    public static double PADDING = 2;
    public static String STANDARD_FONT_FAMILY = "arial";

    public static void drawNode(GraphicsContext context, TreeNode node, float zoom, double offsetX, double offsetY, boolean selected) {
        TreeNodeClass style = node.getDisplayClass();

        double drawX = (node.getNodeX()+offsetX) * zoom;
        double drawY = (node.getNodeY()+offsetY) * zoom;

        double tilePadding = BASE_PADDING * zoom;
        double textSize = style.getTextSize() * zoom;

        Color tileBackgroundColor = node.getDisplayClass().getBackgroundColor();
        Color textColor = node.getDisplayClass().getForegroundColor();

        Font textFont = new Font(STANDARD_FONT_FAMILY, textSize);

        Text content = new Text(node.getContent());
        if (content.getText().isEmpty()) {content.setText("(Empty)"); textColor = StandardColors.PLACEHOLDER.getColor();}
        content.setFont(textFont);

        double contentWidth = content.getBoundsInLocal().getWidth();
        double contentHeight = content.getBoundsInLocal().getHeight();

        // Drawing Tile
        if (selected) {
            context.setStroke(StandardColors.SELECTED.getColor());
            context.strokeRect(drawX - tilePadding - 2*zoom, drawY - tilePadding - 2*zoom, contentWidth + tilePadding * 2 + 4*zoom, contentHeight + tilePadding * 2 + 4*zoom);
        }

        context.setFill(tileBackgroundColor);
        context.setStroke(StandardColors.INFORMATION.getColor());

        context.fillRect(drawX-tilePadding, drawY-tilePadding, contentWidth+tilePadding*2, contentHeight+tilePadding*2);

        // Drawing Text
        context.setFont(textFont);

        context.setFill(textColor);
        context.fillText(content.getText(), drawX, drawY+contentHeight-3);
    }

    public static void drawNodeLink(GraphicsContext context, TreeNodeLink node, float zoom, double offsetX, double offsetY, boolean selected) {
        TreeNodeClass style = node.getDisplayClass();

        double drawX = (node.getNodeX()+offsetX) * zoom;
        double drawY = (node.getNodeY()+offsetY) * zoom;

        double tilePadding = BASE_LINK_PADDING * zoom;
        double textSize = style.getTextSize() * zoom;

        Color tileBackgroundColor = node.getDisplayClass().getBackgroundColor();
        Color textColor = node.getDisplayClass().getForegroundColor();

        Font textFont = new Font(STANDARD_FONT_FAMILY, textSize);

        Text content = new Text(node.getOwner().getContent());
        if (content.getText().isEmpty()) {content.setText("(Empty)"); textColor = StandardColors.PLACEHOLDER.getColor();}
        content.setFont(textFont);

        double contentWidth = content.getBoundsInLocal().getWidth();
        double contentHeight = content.getBoundsInLocal().getHeight();

        // Drawing Tile
        if (selected) {
            context.setStroke(StandardColors.SELECTED.getColor());
            context.setLineWidth(2*zoom);
            context.strokeRect(drawX - tilePadding - 2*zoom, drawY - tilePadding - 2*zoom, contentWidth + tilePadding * 2 + 4*zoom, contentHeight + tilePadding * 2 + 4*zoom);
        }

        context.setFill(tileBackgroundColor);
        context.setStroke(StandardColors.INFORMATION.getColor());

        context.fillRect(drawX-tilePadding, drawY-tilePadding, contentWidth+tilePadding*2, contentHeight+tilePadding*2);

        // Drawing Text
        context.setFont(textFont);

        context.setFill(textColor);
        context.fillText(content.getText(), drawX, drawY+contentHeight-3);
    }

    public static void drawNodeJoint(GraphicsContext context, TreeNodeJoint node, float zoom, double offsetX, double offsetY, boolean selected) {
        TreeNodeClass style = node.getDisplayClass();

        double drawX = (node.getNodeX()+offsetX) * zoom;
        double drawY = (node.getNodeY()+offsetY) * zoom;

        double tilePadding = BASE_LINK_PADDING * zoom;
        double textSize = style.getTextSize() * zoom;

        Color tileBackgroundColor = node.getDisplayClass().getBackgroundColor();

        Font textFont = new Font(STANDARD_FONT_FAMILY, textSize);

        Text content = new Text("JO");
        content.setFont(textFont);

        double contentWidth = content.getBoundsInLocal().getWidth();

        // Drawing Tile
        if (selected) {
            context.setStroke(StandardColors.SELECTED.getColor());
            context.setLineWidth(2*zoom);
            context.save();
            context.translate(drawX, drawY);
            context.rotate(45);
            context.strokeRect(-(tilePadding+contentWidth) / 2, -(tilePadding+contentWidth) / 2, contentWidth+tilePadding, contentWidth+tilePadding);
            context.restore();
        }

        context.setFill(tileBackgroundColor);
        context.setStroke(StandardColors.INFORMATION.getColor());

        context.save();
        context.translate(drawX, drawY);
        context.rotate(45);
        context.fillRect(-(tilePadding+contentWidth) / 2, -(tilePadding+contentWidth) / 2, contentWidth+tilePadding, contentWidth+tilePadding);
        context.restore();
    }

    private static double[] getCenterPos(TreeNodeRoot node, float zoom, double offsetX, double offsetY) {
        boolean itIsJoint = node.itIsLinkNode() && !node.itIsDependentLinkNode();

        double textSize = node.getDisplayClass().getTextSize() * zoom;
        Font textFont = new Font(STANDARD_FONT_FAMILY, textSize);

        Text content;
        if (itIsJoint) {
            content = new Text("JO");
            content.setFont(textFont);
        } else {
            content = new Text(node.getContent());
            if (content.getText().isEmpty()) {content.setText("(Empty)");}
            content.setFont(textFont);
        }

        double contentWidth = content.getBoundsInLocal().getWidth();
        double contentHeight = content.getBoundsInLocal().getHeight();

        double displayXFirst;
        double displayYFirst;
        if (itIsJoint) {
            displayXFirst = ((node.getNodeX() + offsetX) * zoom);
            displayYFirst = ((node.getNodeY() + offsetY) * zoom);
        } else {
            displayXFirst = ((node.getNodeX() + offsetX) * zoom) + contentWidth/2;
            displayYFirst = ((node.getNodeY() + offsetY) * zoom) + contentHeight/2;
        }

        double[] result = {displayXFirst, displayYFirst};
        return result;
    }

    public static void drawConnection(GraphicsContext context, TreeNodeRoot firstNode, TreeNodeRoot secondNode, float zoom, double offsetX, double offsetY) {

        double[] drawFirst = getCenterPos(firstNode, zoom, offsetX, offsetY);
        double[] drawSecond = getCenterPos(secondNode, zoom, offsetX, offsetY);

        double drawXFirst = drawFirst[0];
        double drawYFirst = drawFirst[1];

        double drawXSecond = drawSecond[0];
        double drawYSecond = drawSecond[1];

        // Drawing!
        context.setStroke(Color.BLACK);
        context.setLineWidth(2*zoom);
        context.strokeLine(drawXFirst, drawYFirst, drawXSecond, drawYSecond);
    }

}
