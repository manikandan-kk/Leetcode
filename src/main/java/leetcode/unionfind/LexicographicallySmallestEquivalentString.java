package leetcode.unionfind;

public class LexicographicallySmallestEquivalentString {

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        int[] representations = new int[26];
        for (int i = 0; i < 26; i++) {
            representations[i] = i;
        }
        int slen = s1.length(), bl = baseStr.length();
        char[] s1ch = s1.toCharArray();
        char[] s2ch = s2.toCharArray();
        for (int i = 0; i < slen; i++) {
            int r1 = representations[s1ch[i] - 97], r2 = representations[s2ch[i] - 97];
            if (r1 == r2) {
                continue;
            } else { //merge disjoint sets
                if (r1 < r2) {
                    for (int j = 0; j < 26; j++) {
                        if (representations[j] == r2) {
                            representations[j] = r1;
                        }
                    }
                } else {
                    for (int j = 0; j < 26; j++) {
                        if (representations[j] == r1) {
                            representations[j] = r2;
                        }
                    }
                }
            }
        }

        char[] bch = baseStr.toCharArray();
        char[] ans = new char[bl];
        for (int i = 0; i < bl; i++) {
            ans[i] = (char)(97 + representations[bch[i]-97]);
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        String res = new LexicographicallySmallestEquivalentString()
                .smallestEquivalentString("leetcode", "programs", "sourcecode");
        System.out.println(res);
    }

}
