// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP103 Assignment 2
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.UI;

/** 
 * This class is provided as a bad example.
 * Don't do this at home!
 */
class Order {

    /** the items that are wanted for the order */
    private boolean wantsFish;
    private boolean wantsChips;
    private boolean wantsBurger;

    /** the items that have been added and are ready in the order */
    private boolean hasFish;
    private boolean hasChips;
    private boolean hasBurger;

    Order() {
        wantsFish = Math.random() > 0.5;
        wantsChips = Math.random() > 0.5;
        wantsBurger = Math.random() > 0.5;

        if (!wantsFish && !wantsChips && !wantsBurger) {
            int choice = (int)(Math.random() * 3);
            if (choice==0) wantsFish = true;
            else if (choice==1) wantsChips = true;
            else if (choice==2) wantsBurger = true;
        }
    }

    /** 
     *  The order is ready as long as every item that is
     *  wanted is also ready.
     */
    boolean isReady() {
        return !(wantsFish && !hasFish) && !(wantsChips && !hasChips) && !(wantsBurger && !hasBurger);
    }

    /** 
     *  If the item is wanted but not already in the order,
     *  then put it in the order and return true, to say it was successful.
     *  If the item not wanted, or is already in the order,
     *  then return false to say it failed.
     */
    boolean addItemToOrder(String item){
        switch (item) {
            case "Fish":
                if (wantsFish && !hasFish) {
                    hasFish = true;
                    return true;
                }
                break;
            case "Chips":
                if (wantsChips && !hasChips) {
                    hasChips = true;
                    return true;
                }
                break;
            case "Burger":
                if (wantsBurger && !hasBurger) {
                    hasBurger = true;
                    return true;
                }
                break;
        }
        return false;
    }

    /** 
     *  Computes and returns the price of an order.
     *  [CORE]: Uses constants: 2.50 for fish, 1.50 for chips, 5.00 for burger
     *  to add up the prices of each item
     *  [COMPLETION]: Uses a map of prices to look up prices
     */
    double getPrice() {
        double price = 0;
        if (wantsFish) price += 2.50;
        if (wantsChips) price += 1.50;
        if (wantsBurger) price += 5.00;
        return price;
    }

    double getItemPrice(String item) {
        double price = 0;
        if (item.equals("fish")) price = 2.50;
        if (item.equals("Chips")) price = 1.50;
        if (item.equals("burger")) price = 5.00;
        return price;
    }

    void draw(int y) {
        if (wantsFish) UI.drawImage("Fish-grey.png", 10, y);
        if (wantsChips) UI.drawImage("Chips-grey.png", 50, y);
        if (wantsBurger) UI.drawImage("Burger-grey.png", 90, y);

        if (hasFish) UI.drawImage("Fish.png", 10, y);
        if (hasChips) UI.drawImage("Chips.png", 50, y);
        if (hasBurger) UI.drawImage("Burger.png", 90, y);
    }
}
