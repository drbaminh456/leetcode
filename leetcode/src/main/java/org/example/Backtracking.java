import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Backtracking {
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generateParenthesisBacktracking(res, "", 0, 0, n);
        return res;
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
