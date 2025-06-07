package leetcode.greedy;

import java.util.Stack;

public class UsingRobotToPrintTheLexicographicallySmallestString {
    private int nextSmallestAvailable(int[] counter) {
        for (int i = 0; i < 26; i++) {
            if (counter[i] > 0) {
                return i;
            }
        }
        return 26;
    }

    public String robotWithString(String s) {
        //a b a d e z l a b a d d d e b a c c b c
        //b d e z l b d d d e b
        //a a a a a
        int slen = s.length();
        char[] sch = s.toCharArray();
        int[] counter = new int[26];
        for (int i = 0; i < slen; i++) {
            counter[sch[i]-97]++;
        }
        Stack<Integer> stack = new Stack<>();
        char[] ans = new char[slen];
        for(int i = 0, p = 0; i < slen; i++) {
            int ascii = sch[i] - 97;
            stack.push(ascii);
            counter[ascii]--;
            int nextSmallest = nextSmallestAvailable(counter);
            while(!stack.isEmpty() && stack.peek() <= nextSmallest) {
                ans[p++] = (char)(97 + stack.pop());
            }
        }

        return new String(ans);
    }
}
