package leetcode_contest.contest122;

/**
 * We have an array A of integers, and an array queries of queries.
 * <p>
 * For the i-th query val = queries[i][0], index = queries[i][1], we add val to A[index].  Then, the answer to the i-th query is the sum of the even values of A.
 * <p>
 * (Here, the given index = queries[i][1] is a 0-based index, and each query permanently modifies the array A.)
 * <p>
 * Return the answer to all queries.  Your answer array should have answer[i] as the answer to the i-th query.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: A = [1,2,3,4], queries = [[1,0],[-3,1],[-4,0],[2,3]]
 * Output: [8,6,2,4]
 * Explanation:
 * At the beginning, the array is [1,2,3,4].
 * After adding 1 to A[0], the array is [2,2,3,4], and the sum of even values is 2 + 2 + 4 = 8.
 * After adding -3 to A[1], the array is [2,-1,3,4], and the sum of even values is 2 + 4 = 6.
 * After adding -4 to A[0], the array is [-2,-1,3,4], and the sum of even values is -2 + 4 = 2.
 * After adding 2 to A[3], the array is [-2,-1,3,6], and the sum of even values is -2 + 6 = 4.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * 1 <= queries.length <= 10000
 * -10000 <= queries[i][0] <= 10000
 * 0 <= queries[i][1] < A.length
 */
public class _985 {
    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        if (A == null || queries == null) {
            return null;
        }

        int sum = 0;
        for (int val : A) {
            if (val % 2 == 0) {
                sum += val;
            }
        }

        int[] res = new int[queries.length];

        int idx = 0;
        for (int[] q : queries) {
            int val = q[0];
            int index = q[1];

            if (A[index] % 2 == 0) {
                sum -= A[index];
            }

            A[index] += val;
            if (A[index] % 2 == 0) {
                sum += A[index];
            }

            res[idx++] = sum;
        }

        return res;
    }
}
