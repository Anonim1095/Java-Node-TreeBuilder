package com.anonim.tree_builder.Enums;

import com.anonim.tree_builder.TreeNodes.TreeNodeClass;
import com.anonim.tree_builder.TreeNodes.TreeNodeLinkClass;

import java.util.UUID;

public record StandardStyle() {

    public static final UUID STANDARD_NODE_STYLE_IDENTIFIER = UUID.fromString("166efbe2-2a48-4fd0-87d5-cba522ea323b");
    public static final UUID STANDARD_LINK_STYLE_IDENTIFIER = UUID.fromString("f7d88005-320c-4e96-82e1-96aa54519fbc");

    public final static TreeNodeClass STANDARD_STYLE = new TreeNodeClass(
            "DEFAULT",
            STANDARD_NODE_STYLE_IDENTIFIER,
            StandardColors.TILE_BASE.getColor(),
            StandardColors.EMPTY.getColor(),
            16
    );

    public final static TreeNodeLinkClass STANDARD_LINK_STYLE = new TreeNodeLinkClass(
            "DEFAULT",
            STANDARD_LINK_STYLE_IDENTIFIER,
            StandardColors.LINK_TILE_BASE.getColor(),
            StandardColors.EMPTY.getColor(),
            12
    );

}
