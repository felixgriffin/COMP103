// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 103, Assignment 7
 * Name:
 * Usercode:
 * ID:
 */

import java.awt.Color;

import ecs100.*;

import java.util.*;

/**
 * GeneralTreeNode represents a simple node in a tree. The node stores references
 * to children and a parent (the latter is null in the case of the root node).
 * <p>
 * For the purposes of one technique for drawing on the screen, there is a location field as well.
 * This is purely for drawing purposes, and is not directly related to the concept of the tree structure.
 * NOTE: There are other techniques for drawing the tree that don't require the location field, so you
 * may decide not to use the location field/methods at all.
 *
 * @author Thomas Kuehne
 *         <p>
 *         Based on code written by Stuart Marshall and Monique Damitio
 */

class GeneralTreeNode {
    // The name that is stored at the node.
    private String name;

    // A reference to the parent node of this node.  
    private GeneralTreeNode parent;

    // Since this is a general tree, we don't have a limit on the number of children a node may have, so we use a set.
    private Set<GeneralTreeNode> children = new HashSet<>();

    // This field is only needed for one particular technique for drawing the tree on the screen.
    private Location location;  //location of the center of the node on the screen

    /**
     * Creates a new node
     *
     * @param newName the name of the new node
     */
    GeneralTreeNode(String newName) {
        name = newName;
    }

    // getter and setter methods

    String getName() {
        return name;
    }

    GeneralTreeNode getParent() {
        return parent;
    }

    private void setParent(GeneralTreeNode newParent) {
        parent = newParent;
    }

    Set<GeneralTreeNode> getChildren() {
        return children;
    }

    void setLocation(Location newLocation) {
        location = newLocation;
    }

    private Location getLocation() {
        return location;
    }

    /**
     * Adds a child to the receiver node
     *
     * @param newChild the node/subtree to be added as a new child
     */
    void addChild(GeneralTreeNode newChild) {
        // add to set of children
        children.add(newChild);

        // set new parent reference
        newChild.setParent(this);
    }

    /**
     * Finds the node whose name is equal to the target string
     * <p>
     * CORE.
     * <p>
     * If the target string appears multiple times, then just return the first one encountered.
     * The 'addNode' method from class GeneralTree should guarantee that duplicate strings aren't added anyway.
     * <p>
     * HINT: The most natural implementation of this method is recursive.
     *
     * @param targetName the name of the node we are looking for
     * @return the node that contains the target name, or null if no such node exists.
     */
    GeneralTreeNode findNode(String targetName) {
        if (Objects.equals(name, targetName)) return this;
        if (targetName == null || children.isEmpty()) return null;
        GeneralTreeNode cur;
        for (GeneralTreeNode node : children) {
            cur = node.findNode(targetName);
            if (cur != null) {
                return cur;
            }
        }
        // no node containing the string could be found
        return null;
    }

    /**
     * Removes the receiver node from the list of children of its parent
     * <p>
     * CORE.
     */
    void remove() {
        parent.children.remove(this);
    }

    /**
     * Adds children from another donor node to this node
     * <p>
     * CORE.
     * <p>
     * This method is used to implement method 'moveSubtree' in class General Tree.
     *
     * @param donorNode the node that has the children to be added
     */
    void addChildrenFromNode(GeneralTreeNode donorNode) {
        children.addAll(donorNode.children);
    }

    /**
     * Prints the strings of all the nodes under the given target node
     * (including the target node itself)
     * <p>
     * CORE.
     * <p>
     * HINT: The most natural version of this method is recursive.
     */
    void printSubtree() {
        UI.println(name);
        children.forEach(GeneralTreeNode::printSubtree);
    }

    /**
     * Returns true if the subtree whose root starts with the receiver contains the node of the parameter
     * <p>
     * COMPLETION.
     * <p>
     * This method is used by moveSubtree(...), to ensure that we aren't trying to move a node
     * (and hence the subtree rooted at that node) in a way that makes it become a child of one
     * of it's existing descendants.
     * <p>
     * HINT: The most natural version of this method is recursive.
     *
     * @param node the node to check for
     * @return true if the node is in the subtree, and false otherwise
     */
    boolean contains(GeneralTreeNode node) {
        if (node == this) return true;
        if (node == null || children.isEmpty()) return false;
        for (GeneralTreeNode curNode : children) {
            if (curNode.contains(node)) {
                return true;
            }
        }
        // the node could be found
        return false;
    }

    /**
     * Prints the names of all the nodes in the path from the target node to the root of the entire tree
     * <p>
     * COMPLETION.
     */

    void printPathToRoot() {
        if (parent != null) parent.printPathToRoot();
        UI.println(name);
    }

    /**
     * Prints all the names of all the nodes at the given depth
     * <p>
     * COMPLETION.
     * <p>
     * Prints nothing if there are no nodes at the specified depth.
     * <p>
     * HINT: The most natural version of this method is recursive.
     *
     * @param targetDepth the depth of the tree whores nodes are to be listed. The root is at depth 0.
     */

    void printAllAtDepth(int targetDepth, int currentDepth) {
        if (currentDepth == targetDepth) {
            UI.println(name);
            return;
        }
        for (GeneralTreeNode node : children) {
            node.printAllAtDepth(targetDepth, currentDepth + 1);
        }

    }

    /**
     * Draws all the nodes in the subtree that has the receiver as the root on the Graphics pane
     * <p>
     * CORE.
     * <p>
     * The provided code just draws the tree node; you need to make it draw all the nodes.
     * Make sure that parents and their children are connected by lines .
     * <p>
     * HINT: The most natural version of this method is recursive.
     * <p>
     * HINT: Use UI.drawLine(...) to draw the connecting lines and pay
     * attention in what order you 'paint' on the canvas in order to get a good looking result.
     */
    void redrawSubtree() {
        double x = this.location.getX(), y = this.location.getY(), x2, y2;
        for (GeneralTreeNode node : children) {
            x2 = node.location.getX();
            y2 = node.location.getY();
            UI.drawLine(x, y, x2, y2);
            node.redrawSubtree();
        }
        this.redrawNode();
    }

    /**
     * Draws a node at the location stored in that node. Drawing the node consists of drawing an oval, and writing the name string
     * out "in" that oval. Note that no consideration is given to the length of the string, so this could look ugly.
     */
    private void redrawNode() {
        double x = this.getLocation().getX();
        double y = this.getLocation().getY();

        UI.setColor(Color.white);
        UI.fillOval(x - 2 * GeneralTree.nodeRad, y - GeneralTree.nodeRad, GeneralTree.nodeRad * 4 - 1, GeneralTree.nodeRad * 2 - 1);

        UI.setColor(Color.black);
        UI.drawOval(x - 2 * GeneralTree.nodeRad, y - GeneralTree.nodeRad, GeneralTree.nodeRad * 4 - 1, GeneralTree.nodeRad * 2 - 1);

        UI.drawString(name, x - GeneralTree.nodeRad - 4, y + 5);
    }
}
