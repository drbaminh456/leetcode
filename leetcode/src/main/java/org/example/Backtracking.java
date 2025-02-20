package org.example;

import java.util.*;

public class Backtracking {
    public static void main(java.lang.String[] args) {
        int[] x = new int[]{10,1,2,7,6,1,5};
        System.out.println(combinationSum(x, 8));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSumHelper(res, new ArrayList<>(), 0, candidates, target);
        return res;
    }

    public static void combinationSumHelper(List<List<Integer>> res, List<Integer> current, int start, int[] candidates, int target) {
        if (target == 0) {
            res.add(new ArrayList<>(current));
            return;
        }
        if (target < 0) return;
        for (int i = start; i < candidates.length; i++) {
            current.add(candidates[i]);
            combinationSumHelper(res, current, i+1, candidates, target - candidates[i]);
            current.remove(current.size() - 1);
        }
    }

    public static void generateParenthesisBacktracking(List<String> res, String current, int open, int close, int n) {
        if (current.length() == 2 * n) {
            res.add(current.toString());
            return;
        }
        if (open < n) {
            generateParenthesisBacktracking(res, current + '(', open + 1, close, n);
        }
        if (close < open) {
            generateParenthesisBacktracking(res, current + ')', open, close + 1, n);
        }
    }
    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        recursive(digits, res, new StringBuilder(), 0);
        return res;
    }

    public static void recursive(String digits, List<String> res, StringBuilder current, int index) {
        Map<Character, String> DIGIT_TO_LETTERS = new HashMap<>();
        DIGIT_TO_LETTERS.put('2', "abc");
        DIGIT_TO_LETTERS.put('3', "def");
        DIGIT_TO_LETTERS.put('4', "ghi");
        DIGIT_TO_LETTERS.put('5', "jkl");
        DIGIT_TO_LETTERS.put('6', "mno");
        DIGIT_TO_LETTERS.put('7', "pqrs");
        DIGIT_TO_LETTERS.put('8', "tuv");
        DIGIT_TO_LETTERS.put('9', "wxyz");
        if (index == digits.length()) {
            res.add(current.toString());
            return;
        }
        char digit = digits.charAt(index);
        String choices = DIGIT_TO_LETTERS.get(digit);
        for (char choice : choices.toCharArray()) {
            current.append(choice);
            recursive(digits, res, current, index + 1);
            current.deleteCharAt(current.length() - 1);
        }
    }
}
