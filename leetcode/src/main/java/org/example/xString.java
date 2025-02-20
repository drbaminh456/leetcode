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

}
