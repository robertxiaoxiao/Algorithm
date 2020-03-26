package LeetCode.DP;

public class q1225 {

    public int maxScoreWords(String[] words, char[] letters, int[] score) {

        int[] chars = new int[26];
        for (char ch : letters) {
            chars[ch - 'a']++;
        }

        int[] wscore = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            int sum = 0;
            for (char ch : words[i].toCharArray())
                sum += score[ch - 'a'];
            wscore[i] = sum;
        }

        return helper(words, chars, wscore, 0);
    }

    public int helper(String[] words, int[] letters, int[] score, int i) {

        if (i == words.length)
            return 0;

        int nousing = helper(words, letters, score, i + 1);
        int[] rest = letters.clone();
        for (char ch : words[i].toCharArray()) {
            if (rest[ch - 'a'] > 0)
                rest[ch - 'a']--;
            else
                return nousing;
        }
        int using = helper(words, rest, score, i + 1) + score[i];
        return Math.max(using, nousing);
    }

}
