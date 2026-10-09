class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int openCount = 0;
        int n = s.length();
        
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                openCount++;
            } else {
                if (i + 1 < n && s.charAt(i + 1) == ')') {
                    i++;
                } else {
                    insertions++;
                }
                
                if (openCount > 0) {
                    openCount--;
                } else {
                    insertions++;
                }
            }
        }
        
        return insertions + openCount * 2;
    }
}