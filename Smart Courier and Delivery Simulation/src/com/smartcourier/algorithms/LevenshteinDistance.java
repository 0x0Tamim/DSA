package com.smartcourier.algorithms;

public class LevenshteinDistance {
    /**
     * Calculates the Levenshtein distance between two strings.
     * It uses a dynamic programming approach to find the minimum edits.
     * @param s1 The first string.
     * @param s2 The second string.
     * @return The edit distance.
     */
    public static int calculate(String s1, String s2) {
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }

        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j; // Cost of deleting all characters of s2
                } else if (j == 0) {
                    dp[i][j] = i; // Cost of deleting all characters of s1
                } else {
                    int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + cost, // Substitution
                            Math.min(dp[i - 1][j] + 1,    // Deletion
                                    dp[i][j - 1] + 1));   // Insertion
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
}