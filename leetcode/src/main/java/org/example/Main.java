package org.example;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        int[] nums = {2,2,2,3};
        System.out.println(removeDuplicates(nums));
    }

    public static void nextPermutation(int[] nums) {
        int i;
        for (i = nums.length - 2; i >= 0; --i) {
            if (nums[i] < nums[i + 1]) {
                break;
            }
        }
        if (i >= 0) {
            for (int j = nums.length - 1; j > i; j--) {
                if (nums[j] > nums[i]) {
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                }
            }
        }
        reverse(nums, i + 1, nums.length - 1);
    }
    private static void reverse(int[] nums, int l, int r) {
        while (l < r)
            swap(nums, l++, r--);
    }
    private static void swap(int[] nums, int i, int j) {
        final int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j <= nums.length - 1; j++) {
            if (nums[i] != nums[j]){
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    public static int removeElement(int[] nums, int val) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 0; j <= nums.length - 1; j++) {
            if (nums[j] != val){
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode first = head;
        ListNode second = head.next;
        first.next = swapPairs(second.next);
        second.next = first;
        return second;
    }
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return mergeSortLinkedList(lists, 0, lists.length - 1);
    }
    public static ListNode mergeSortLinkedList(ListNode[] lists, int left, int right) {
        if (left==right) return lists[left];
        int mid = (left + right) >> 1;
        ListNode l1 = mergeSortLinkedList(lists, left, mid);
        ListNode l2 = mergeSortLinkedList(lists, mid + 1, right);
        return mergeTwoLists(l1, l2);
    }

    //Stack/Queue
    public static boolean isValid(String s) {
        if (s.length() < 2 || !Arrays.asList('{', '(', '[').contains(s.charAt(0)))
            return false;
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            if (!stack.isEmpty()) {
                switch (c) {
                    case ')':
                        if (stack.peek() == '(') {
                            stack.pop();
                        } else {
                            return false;
                        }
                        break;
                    case '}':
                        if (stack.peek() == '{') {
                            stack.pop();
                        } else {
                            return false;
                        }
                        break;
                    case ']':
                        if (stack.peek() == '[') {
                            stack.pop();
                        } else {
                            return false;
                        }
                        break;
                    default:
                        break;
                }
            } else {
                return false;
            }

        }
        return stack.isEmpty();
    }

    //Linked List
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode slow = dummy, fast = dummy;
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length < 4) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            // Skip duplicate values for i
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int l = i + 1; l < nums.length - 2; l++) {
                // Skip duplicate values for l
                if (l > i + 1 && nums[l] == nums[l - 1]) {
                    continue;
                }
                int j = l + 1, k = nums.length - 1;
                while (j < k) {
                    long sum = (long) nums[i] + (long) nums[l] + (long) nums[j] + (long) nums[k];

                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[l], nums[j], nums[k]));

                        // Move past duplicates for j
                        while (j < k && nums[j] == nums[j + 1]) j++;
                        // Move past duplicates for k
                        while (j < k && nums[k] == nums[k - 1]) k--;

                        j++;
                        k--;
                    } else if (sum < target) {
                        j++; // Increase sum
                    } else {
                        k--; // Decrease sum
                    }
                }
            }
        }
        return res;
    }

    

    public static int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int res = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);
        int minDistance = Math.abs(target - res);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum < target) {
                    j++;
                } else if (sum > target) {
                    k--;
                } else {
                    return sum;
                }
                if (minDistance > Math.abs(target - sum)) {
                    minDistance = Math.abs(target - sum);
                    res = sum;
                }
            }
        }
        return res;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }

        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicate values for i
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int j = i + 1, k = nums.length - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];

                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));

                    // Move past duplicates for j
                    while (j < k && nums[j] == nums[j + 1]) j++;
                    // Move past duplicates for k
                    while (j < k && nums[k] == nums[k - 1]) k--;

                    j++;
                    k--;
                } else if (sum < 0) {
                    j++; // Increase sum
                } else {
                    k--; // Decrease sum
                }
            }
        }
        return res;
    }

    public static String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        String first = strs[0];
        String last = strs[strs.length - 1];
        int i = 0;
        while (i < Math.min(first.length(), last.length()) && first.charAt(i) == last.charAt(i)) {
            i++;
        }
        return first.substring(0, i);
    }

    public static int romanToInt(String s) {
        Map<String, Integer> romanMap = new TreeMap<>();
        romanMap.put("I", 1);
        romanMap.put("V", 5);
        romanMap.put("X", 10);
        romanMap.put("L", 50);
        romanMap.put("C", 100);
        romanMap.put("D", 500);
        romanMap.put("M", 1000);
        int res = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (romanMap.get(String.valueOf(s.charAt(i))) < romanMap.get(String.valueOf(s.charAt(i + 1)))) {
                res -= romanMap.get(String.valueOf(s.charAt(i)));
            } else {
                res += romanMap.get(String.valueOf(s.charAt(i)));
            }
        }
        return res + romanMap.get(String.valueOf(s.charAt(s.length() - 1)));
    }

    public static String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            if (num == 0)
                break;
            while (num >= values[i]) {
                res.append(symbols[i]);
                num -= values[i];
            }

        }
        return res.toString();
    }

    public static int maxArea(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        int left = 0, right = height.length - 1, max = 0;
        int currentArea;

        while (left < right) {
            currentArea = Math.min(height[left], height[right]) * (right - left);
            max = Math.max(currentArea, max);
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }

    public static boolean isMatch(String s, String p) {
        return Pattern.matches(p, s);
    }

    public static int myAtoi(String s) {
        int res = 0;
        int sign = 1;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '-') {
                sign = -1;
            }
        }
        return res * sign;
    }

    public static int reverseInt(int x) {
        int y = Math.abs(x);
        int revNum = 0;
        while (y != 0) {
            int lastDigit = y % 10;
            if (revNum > (Integer.MAX_VALUE - lastDigit) / 10) {
                return 0;
            }

            revNum = (revNum * 10) + lastDigit;
            y = y / 10;
        }

        return (x < 0) ? (-revNum) : revNum;
//        long input = (long) x;
//        StringBuilder res = new StringBuilder();
//        if (input == 0)
//            return 0;
//        if (input < 0) {
//            input = input * -1;
//            res.append("-");
//        }
//        long temp = 0;
//        while (input != 0) {
//            temp = input % 10;
//            input = input / 10;
//            res.append(temp);
//        }
//        long reversedNum = Long.parseLong(res.toString());
//
//        if (reversedNum > Integer.MAX_VALUE || reversedNum < Integer.MIN_VALUE) {
//            return 0;
//        }
//        return (int) reversedNum;
    }

    public static String convertZigzag(String s, int numRows) {
        if (numRows == 1 || numRows > s.length())
            return s;
        ArrayList<StringBuilder> rows = new ArrayList();
        int idx = 0, d = 1;
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }
        for (char c : s.toCharArray()) {
            rows.get(idx).append(c);
            if (idx == 0)
                d = 1;
            else if (idx == numRows - 1)
                d = -1;
            idx += d;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows) {
            res.append(row);
        }
        return res.toString();
    }

    public static String longestPalindrome(String s) {
        int[] ans = {0, 0};
        for (int i = 0; i < s.length(); i++) {
            int oddLength = expand(i, i, s);
            if (oddLength > ans[1] - ans[0] + 1) {
                int dist = oddLength / 2;
                ans[0] = i - dist;
                ans[1] = i + dist;
            }

            int evenLength = expand(i, i + 1, s);
            if (evenLength > ans[1] - ans[0] + 1) {
                int dist = (evenLength / 2) - 1;
                ans[0] = i - dist;
                ans[1] = i + 1 + dist;
            }
        }
        return s.substring(ans[0], ans[1] + 1);
    }

    public static int expand(int i, int j, String s) {
        int left = i;
        int right = j;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }


    public static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[low] <= nums[mid]) {
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        return -1;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int sub = target - nums[i];
            if (map.containsKey(sub)) {
                return new int[]{map.get(sub), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;

            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }


            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
        }
        return dummyHead.next;
    }

    public static int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        Set<Character> charSet = new HashSet<>();
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            if (!charSet.contains(s.charAt(right))) {
                charSet.add(s.charAt(right));
                maxLength = Math.max(maxLength, right - left + 1);
            } else {
                while (charSet.contains(s.charAt(right))) {
                    charSet.remove(s.charAt(left));
                    left++;
                }
                charSet.add(s.charAt(right));
            }
        }

        return maxLength;
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> mergeList = new ArrayList<>();
        mergeList.addAll(new ArrayList<>(Arrays.stream(nums1).boxed().collect(Collectors.toList())));
        mergeList.addAll(new ArrayList<>(Arrays.stream(nums2).boxed().collect(Collectors.toList())));
        mergeList = mergeList.stream().sorted().collect(Collectors.toList());
        if (mergeList.size() % 2 == 0) {
            return (mergeList.get((mergeList.size() / 2) - 1) + (double) mergeList.get((mergeList.size() / 2))) / 2;
        } else {
            return mergeList.get(mergeList.size() / 2);
        }
    }
}
