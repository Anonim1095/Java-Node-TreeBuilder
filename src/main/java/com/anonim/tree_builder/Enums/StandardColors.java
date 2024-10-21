package com.anonim.tree_builder.Enums;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public enum StandardColors {
    BACKGROUND(Color.LIGHTGREY),
    LINK_TILE_BASE(Color.GREY),
    TILE_BASE(Color.WHITE),
    INFORMATION(Color.GOLD),
    PLACEHOLDER(Color.LIGHTGREY),
    SELECTED(Color.LIGHTCYAN),
    EMPTY(Color.BLACK)
    ;

    private final Color color;
    
    StandardColors(Color color) {
        this.color = color;
    }

    public Color getColor() { return this.color; }
}
