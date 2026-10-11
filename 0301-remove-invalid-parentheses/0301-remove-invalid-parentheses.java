
import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();

        int left = 0, right = 0;

        // Count minimum removals needed
        for (char c : s.toCharArray()) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left == 0) {
                    right++;
                } else {
                    left--;
                }
            }
        }

        backtrack(s, 0, left, right, result);

        return result;
    }

    private void backtrack(String s, int start, int left,
                           int right, List<String> result) {
        if (left == 0 && right == 0) {
            if (isValid(s)) {
                result.add(s);
            }
            return;
        }

        for (int i = start; i < s.length(); i++) {
            // Skip duplicate parentheses
            if (i > start && s.charAt(i) == s.charAt(i - 1)) {
                continue;
            }

            char c = s.charAt(i);

            if (c == '(' && left > 0) {
                backtrack(
                    s.substring(0, i) + s.substring(i + 1),
                    i, left - 1, right, result
                );
            } else if (c == ')' && right > 0) {
                backtrack(
                    s.substring(0, i) + s.substring(i + 1),
                    i, left, right - 1, result
                );
            }
        }
    }

    private boolean isValid(String s) {
        int balance = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                balance++;
            } else if (c == ')') {
                balance--;
            }

            if (balance < 0) {
                return false;
            }
        }

        return balance == 0;
    }
}
