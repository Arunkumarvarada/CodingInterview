package Hard;

// Given two words (start and end), and a dictionary, find the length of shortest
// transformation sequence from start to end, such that only one letter can be changed
// at a time and each intermediate word must exist in the dictionary. For example, given:
// INPUT:
// start = "hit"
// end = "cog"
// dict = ["hot","dot","dog","lot","log"]
// OUTPUT: 5
// Explanation: "hit" -> "hot" -> "dot" -> "dog" -> "cog"
// BFS PROBLEM

import java.util.HashSet;
import java.util.LinkedList;

public class WordLadder {

    private static int ladderLength(String start, String end, HashSet<String> dict) {
        if (dict.isEmpty()) {
            return 0;
        }
        dict.add(end);
        LinkedList<String> wordQueue = new LinkedList<>();
        LinkedList<Integer> distanceQueue = new LinkedList<>();
        wordQueue.add(start);
        distanceQueue.add(1);
        //track the shortest path
        int result = Integer.MAX_VALUE;
        while (!wordQueue.isEmpty()) {
            String currWord = wordQueue.pop();
            Integer currDistance = distanceQueue.pop();
            if (currWord.equals(end)) {
                result = Math.min(result, currDistance);
            }
            for (int i = 0; i < currWord.length(); i++) {
                char[] currCharArr = currWord.toCharArray();
                for (char c = 'a'; c <= 'z'; c++) {
                    currCharArr[i] = c;
                    String newWord = new String(currCharArr);
                    if (dict.contains(newWord)) {
                        wordQueue.add(newWord);
                        distanceQueue.add(currDistance + 1);
                        dict.remove(newWord);
                    }
                }
            }
        }
        if (result < Integer.MAX_VALUE) {
            return result;
        } else {
            return 0;
        }
    }
    
    public static void main(String[] args) {
        String start = "hit";
        String end = "cog";
        HashSet<String> dict = new HashSet<>();
        dict.add("hot");
        dict.add("dot");
        dict.add("dog");
        dict.add("lot");
        dict.add("log");
        System.out.println(ladderLength(start,end, dict));
    }
    
}
