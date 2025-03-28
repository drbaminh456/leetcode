package org.example;

public class xString {
    public static void main(java.lang.String[] args) {
    }
    public static String countAndSay(int n) {
        if (n == 1) return "1";
        String prev = countAndSay(n-1);
        StringBuilder res = new StringBuilder();
        int count = 1;
        for (int i = 0; i < prev.length() - 1; i++) {
            if (prev.charAt(i) == prev.charAt(i + 1)) {
                count++;
            } else {
                res.append(count).append(prev.charAt(i));
                count = 1;
            }
        }
        res.append(count).append(prev.charAt(prev.length() - 1));
        return res.toString();
    }

    public static String longestPalindrome(String s) {
        int[] res = {0,0};
        for (int i = 0; i < s.length(); i++) {
            int oddLength = expand(s, i, i);
            if (oddLength > res[1] - res[0] +1 ){
                int dist = oddLength / 2;
                res[0] = i - oddLength / 2;
                res[1] = i + oddLength / 2;
            }
            int evenLength = expand(s, i, i+1);
            if (evenLength > res[1] - res[0] +1 ){
                int dist = evenLength / 2;
                res[0] = i - dist ;
                res[1] = i + evenLength / 2 + 1;
            }
        }
        return s.substring(s.charAt(0), s.charAt(1));
    }

    private static int expand(String s, int left, int right) {
        while (left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        return right - left -1;
    }
}
