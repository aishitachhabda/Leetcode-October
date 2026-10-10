import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();

        if (words.length == 0) return ans;

        int n = s.length();
        int len = words[0].length();
        int total = len * words.length;

        if (n < total) return ans;

        Map<String, Integer> map = new HashMap<>();

        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        for (int i = 0; i < len; i++) {
            int left = i, count = 0;
            Map<String, Integer> seen = new HashMap<>();

            for (int right = i; right + len <= n; right += len) {
                String word = s.substring(right, right + len);

                if (map.containsKey(word)) {
                    seen.put(word, seen.getOrDefault(word, 0) + 1);
                    count++;

                    while (seen.get(word) > map.get(word)) {
                        String leftWord = s.substring(left, left + len);
                        seen.put(leftWord, seen.get(leftWord) - 1);
                        left += len;
                        count--;
                    }

                    if (count == words.length) {
                        ans.add(left);
                        String leftWord = s.substring(left, left + len);
                        seen.put(leftWord, seen.get(leftWord) - 1);
                        left += len;
                        count--;
                    }
                } else {
                    seen.clear();
                    count = 0;
                    left = right + len;
                }
            }
        }

        return ans;
    }
}