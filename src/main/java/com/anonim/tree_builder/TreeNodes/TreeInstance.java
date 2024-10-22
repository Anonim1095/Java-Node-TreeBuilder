package com.anonim.tree_builder.TreeNodes;

import com.anonim.tree_builder.Main;
import com.anonim.tree_builder.Enums.StandardStyle;
import com.anonim.tree_builder.JavaFXControllers.ColorTransformer;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

public class TreeInstance {

    private HashMap<UUID, TreeNode> TreeNodes = new HashMap<UUID, TreeNode>();
    private HashMap<UUID, TreeNodeJoint> TreeJoints = new HashMap<UUID, TreeNodeJoint>();
    private HashMap<UUID, TreeNodeLink> TreeLinks = new HashMap<UUID, TreeNodeLink>();

    private Map<UUID, TreeNodeClass> Styles = new HashMap<UUID, TreeNodeClass>();
    private Map<UUID, TreeNodeLinkClass> LinkStyles = new HashMap<UUID, TreeNodeLinkClass>();

    public Supplier<JsonObject> Object = () -> {
        JsonObject ROOT = new JsonObject();

        JsonArray STYLES = new JsonArray();
        JsonArray NODES = new JsonArray();
        JsonArray JOINTS = new JsonArray();
        JsonArray LINKS = new JsonArray();

        Styles.forEach((uuid, style) -> {
            JsonObject element = new JsonObject();
            element.addProperty("displayName", style.getDisplayName());
            element.addProperty("styleUUID", style.getIdentifier().toString());

            element.addProperty("backgroundColor", ColorTransformer.toHexString(style.getBackgroundColor()));
            element.addProperty("foregroundColor", ColorTransformer.toHexString(style.getForegroundColor()));

            element.addProperty("link",style.isLinkClass());
            element.addProperty("textSize",style.getTextSize());
            STYLES.add(element);
        });

        TreeNodes.forEach((uuid, node) -> {
            JsonObject element = new JsonObject();
            element.addProperty("content", node.getContent());
            element.addProperty("uuid", node.getIdentifier().toString());

            element.addProperty("displayClass", node.getDisplayClass().getIdentifier().toString());

            element.addProperty("nodeX",node.getNodeX());
            element.addProperty("nodeY",node.getNodeY());

            JsonArray parents = new JsonArray();
            node.getParentArray().forEach((item) -> {
                parents.add(item.getIdentifier().toString());
            });
            JsonArray children = new JsonArray();
            node.getChildrenArray().forEach((item) -> {
                children.add(item.getIdentifier().toString());
            });
            element.add("parents", parents);
            element.add("children", children);
            NODES.add(element);
        });

        TreeLinks.forEach((uuid, node) -> {
            JsonObject element = new JsonObject();
            element.addProperty("uuid", node.getIdentifier().toString());
            element.addProperty("owner",node.getOwner().getIdentifier().toString());

            element.addProperty("displayClass", node.getDisplayClass().getIdentifier().toString());

            element.addProperty("nodeX",node.getNodeX());
            element.addProperty("nodeY",node.getNodeY());

            JsonArray parents = new JsonArray();
            node.getParentArray().forEach((item) -> {
                parents.add(item.getIdentifier().toString());
            });
            JsonArray children = new JsonArray();
            node.getChildrenArray().forEach((item) -> {
                children.add(item.getIdentifier().toString());
            });
            element.add("parents", parents);
            element.add("children", children);
            LINKS.add(element);
        });

        TreeJoints.forEach((uuid, node) -> {
            JsonObject element = new JsonObject();
            element.addProperty("uuid", node.getIdentifier().toString());
            element.addProperty("displayClass", node.getDisplayClass().getIdentifier().toString());

            element.addProperty("nodeX",node.getNodeX());
            element.addProperty("nodeY",node.getNodeY());

            JsonArray parents = new JsonArray();
            node.getParentArray().forEach((item) -> {
                parents.add(item.getIdentifier().toString());
            });
            JsonArray children = new JsonArray();
            node.getChildrenArray().forEach((item) -> {
                children.add(item.getIdentifier().toString());
            });
            element.add("parents", parents);
            element.add("children", children);
            JOINTS.add(element);
        });

        ROOT.add("Styles",STYLES);
        ROOT.add("Nodes",NODES);
        ROOT.add("Joints",JOINTS);
        ROOT.add("Links",LINKS);
        ROOT.addProperty("VERSION", Main.TREE_VERSION);
        return ROOT;
    };

    public TreeInstance() {};

    public TreeInstance(HashMap<UUID, TreeNode> treeNodes, HashMap<UUID, TreeNodeJoint> treeJoints, HashMap<UUID, TreeNodeLink> treeLinks, Map<UUID, TreeNodeClass> styles, Map<UUID, TreeNodeLinkClass> linkStyles) {
        TreeNodes = treeNodes;
        TreeJoints = treeJoints;
        TreeLinks = treeLinks;

        Styles = styles;
        LinkStyles = linkStyles;
    }

    public TreeInstance(JsonObject object) {
        if (object != null) {
            boolean properObject = object.has("Styles") && object.has("Nodes") && object.has("Links") && object.has("Joints");

            if (properObject) {
                Styles.put(StandardStyle.STANDARD_NODE_STYLE_IDENTIFIER, StandardStyle.STANDARD_STYLE);
                LinkStyles.put(StandardStyle.STANDARD_LINK_STYLE_IDENTIFIER, StandardStyle.STANDARD_LINK_STYLE);

                JsonArray styles = object.getAsJsonArray("Styles");

                JsonArray nodes = object.getAsJsonArray("Nodes");
                JsonArray joints = object.getAsJsonArray("Joints");
                JsonArray links = object.getAsJsonArray("Links");

                // STYLES, WEEEE
                styles.forEach((element) -> {
                    JsonObject translated = element.getAsJsonObject();
                    String displayName = translated.get("displayName").getAsString();
                    UUID identifier = UUID.fromString(translated.get("styleUUID").getAsString());

                    String backgroundHex = translated.get("backgroundColor").getAsString();
                    String foregroundHex = translated.get("foregroundColor").getAsString();
                    boolean linkStyle = translated.get("link").getAsBoolean();
                    double textSize = translated.get("textSize").getAsDouble();
                    if (linkStyle) {
                        TreeNodeLinkClass item = new TreeNodeLinkClass(displayName, identifier, Color.web(backgroundHex), Color.web(foregroundHex), textSize);
                        LinkStyles.put(identifier, item);

                    } else {
                        TreeNodeClass item = new TreeNodeClass(displayName, identifier, Color.web(backgroundHex), Color.web(foregroundHex), textSize);
                        Styles.put(identifier, item);
                    }
                });

                HashMap<TreeNodeRoot, UUID> unresolvedParentList = new HashMap<>();
                HashMap<TreeNodeRoot, UUID> unresolvedChildrenList = new HashMap<>();

                // NODES, WEEEE
                nodes.forEach((element) -> {
                    JsonObject translated = element.getAsJsonObject();
                    String content = translated.get("content").getAsString();
                    UUID identifier = UUID.fromString(translated.get("uuid").getAsString());

                    UUID styleIdentifier = UUID.fromString(translated.get("displayClass").getAsString());

                    double elementX = translated.get("nodeX").getAsDouble();
                    double elementY = translated.get("nodeY").getAsDouble();

                    TreeNode node = new TreeNode(content, Styles.get(styleIdentifier), identifier);

                    JsonArray parents = translated.getAsJsonArray("parents");
                    JsonArray children = translated.getAsJsonArray("children");

                    parents.forEach((item) -> {
                        UUID parentIdentifier = UUID.fromString(item.getAsString());
                        unresolvedParentList.put(node, parentIdentifier);
                    });

                    children.forEach((item) -> {
                        UUID childrenIdentifier = UUID.fromString(item.getAsString());
                        unresolvedChildrenList.put(node, childrenIdentifier);
                    });

                    node.setNodeX(elementX);
                    node.setNodeY(elementY);
                    TreeNodes.put(identifier, node);
                });

                // NODES LINKS, WEEEE
                links.forEach((element) -> {
                    JsonObject translated = element.getAsJsonObject();
                    UUID identifier = UUID.fromString(translated.get("uuid").getAsString());

                    UUID styleIdentifier = UUID.fromString(translated.get("displayClass").getAsString());
                    UUID ownerIdentifier = UUID.fromString(translated.get("owner").getAsString());

                    double elementX = translated.get("nodeX").getAsDouble();
                    double elementY = translated.get("nodeY").getAsDouble();

                    TreeNodeLink link = new TreeNodeLink(TreeNodes.get(ownerIdentifier),LinkStyles.get(styleIdentifier),identifier);

                    JsonArray parents = translated.getAsJsonArray("parents");
                    JsonArray children = translated.getAsJsonArray("children");

                    parents.forEach((item) -> {
                        UUID parentIdentifier = UUID.fromString(item.getAsString());
                        unresolvedParentList.put(link, parentIdentifier);
                    });

                    children.forEach((item) -> {
                        UUID childrenIdentifier = UUID.fromString(item.getAsString());
                        unresolvedChildrenList.put(link, childrenIdentifier);
                    });

                    link.setNodeX(elementX);
                    link.setNodeY(elementY);
                    TreeLinks.put(identifier, link);
                });

                // NODES JOINTS, WEEEE
                joints.forEach((element) -> {
                    JsonObject translated = element.getAsJsonObject();
                    UUID identifier = UUID.fromString(translated.get("uuid").getAsString());

                    UUID styleIdentifier = UUID.fromString(translated.get("displayClass").getAsString());

                    double elementX = translated.get("nodeX").getAsDouble();
                    double elementY = translated.get("nodeY").getAsDouble();

                    JsonArray parents = translated.getAsJsonArray("parents");
                    JsonArray children = translated.getAsJsonArray("children");

                    TreeNodeJoint joint = new TreeNodeJoint(LinkStyles.get(styleIdentifier));

                    parents.forEach((item) -> {
                        UUID parentIdentifier = UUID.fromString(item.getAsString());
                        unresolvedParentList.put(joint, parentIdentifier);
                    });

                    children.forEach((item) -> {
                        UUID childrenIdentifier = UUID.fromString(item.getAsString());
                        unresolvedChildrenList.put(joint, childrenIdentifier);
                    });

                    joint.setNodeX(elementX);
                    joint.setNodeY(elementY);
                    TreeJoints.put(identifier, joint);
                });

                // Unresolving Parents, WEEEE

                unresolvedParentList.forEach((node, identifier) -> {
                    if (TreeNodes.containsKey(identifier)) {
                        TreeNodes.get(identifier).addChildren(node);
                        node.addParent(TreeNodes.get(identifier));
                    } else if (TreeLinks.containsKey(identifier)) {
                        TreeLinks.get(identifier).addChildren(node);
                        node.addParent(TreeLinks.get(identifier));
                    } else if (TreeJoints.containsKey(identifier)) {
                        TreeJoints.get(identifier).addChildren(node);
                        node.addParent(TreeJoints.get(identifier));
                    }
                });

                // Unresolving Chilren, WEEEE

                unresolvedChildrenList.forEach((node, identifier) -> {
                    if (TreeNodes.containsKey(identifier)) {
                        TreeNodes.get(identifier).addParent(node);
                        node.addChildren(TreeNodes.get(identifier));
                    } else if (TreeLinks.containsKey(identifier)) {
                        TreeLinks.get(identifier).addParent(node);
                        node.addChildren(TreeLinks.get(identifier));
                    } else if (TreeJoints.containsKey(identifier)) {
                        TreeJoints.get(identifier).addParent(node);
                        node.addChildren(TreeJoints.get(identifier));
                    }
                });

            }

        }
    }

    public HashMap<UUID, TreeNode> getTreeNodes() {
        return TreeNodes;
    }

    public HashMap<UUID, TreeNodeJoint> getTreeJoints() {
        return TreeJoints;
    }

    public HashMap<UUID, TreeNodeLink> getTreeLinks() {
        return TreeLinks;
    }

    public Map<UUID, TreeNodeClass> getStyles() {
        return Styles;
    }

    public Map<UUID, TreeNodeLinkClass> getLinkStyles() {
        return LinkStyles;
    }

}
