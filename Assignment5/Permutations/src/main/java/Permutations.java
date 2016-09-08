// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP103, Assignment 5
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;

import java.util.*;

/**
 * Prints out all permutations of a string
 * The static method permute constructs all the permutations.
 * The main method gets the string, calls recPermute, and prints the result.
 */
public class Permutations {
    /**
     * @return a List of all the permutations of a String.
     */
    private static List<String> recPermute(String string) {
        ArrayList<String> wordList = new ArrayList<>();
        addList(string, "", wordList);
        return wordList;
    }

    private static void addList(String string, String prefix, List<String> wordList) {
        if (string.isEmpty()) {
            wordList.add(prefix);
            return;
        }
        for (int i = 0; i < string.length(); i++) {
            addList(string.substring(0, i) + string.substring(i + 1),prefix + string.charAt(i), wordList);
        }
    }

    // Main
    public static void main(String[] arguments) {
        UI.initialise();
        UI.setWindowSize(500, 400);
        UI.setDivider(1);
        String string = "";
        while (!string.equals("#")) {
            string = UI.askString("Enter string to permute - # to exit: ");
            if (string.length() < 11) {

                List<String> permutations = recPermute(string);

                permutations.forEach(UI::println);

                UI.println("---------");
            } else UI.println("Give a smaller string.");
        }
        UI.quit();
    }
}
