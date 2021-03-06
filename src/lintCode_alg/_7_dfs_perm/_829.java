package lintCode_alg._7_dfs_perm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *Given a pattern and a string str, find if str follows the same pattern.
 *
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.(i.e if a corresponds to s, then b cannot correspond to s. For example, given pattern = "ab", str = "ss", return false.)
 *
 * Example
 * Given pattern = "abab", str = "redblueredblue", return true.
 * Given pattern = "aaaa", str = "asdasdasdasd", return true.
 * Given pattern = "aabb", str = "xyzabcxzyabc", return false.
 *
 * Notice
 * You may assume both pattern and str contains only lowercase letters.
 */
public class _829 {
    public boolean wordPatternMatch(String s, String t) {
        if (s == null || t == null) {
            return false;
        }

        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        return match(s, t, map, set);
    }

    private boolean match(String s, String t, Map<Character, String> map, Set<String> set) {
        int slen = s.length();
        int tlen = t.length();

        if (slen == 0) {
            return tlen == 0;
        }

        if (slen != 0 && tlen == 0) {
            return false;
        }

        //recursive.
        char c = s.charAt(0);
        String snext = s.substring(1);
        // if already mapped.

        if (map.containsKey(c)) {
            String mapStr = map.get(c);
            // startswith is very import. Contains the method.
            if (!t.startsWith(mapStr)) {
                return false;
            } else {
                return match(snext, t.substring(mapStr.length()), map, set);
            }
        } else {
            for (int i = 1; i <= tlen; i++) {
                String mapStr = t.substring(0, i);
                if (set.contains(mapStr)) {
                    continue;
                }
                map.put(c, mapStr);
                set.add(mapStr);
                if (match(snext, t.substring(i), map, set)) {
                    return true;
                }
                map.remove(c);
                set.remove(mapStr);
            }
            return false;
        }
    }
}
