package lintCode_alg._5_dfs;

import common.NodeClass.TreeNode;

/**
 * Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two
 * nodes. The lowest common ancestor is the node with largest depth which is the ancestor of both
 * nodes. Return null if LCA does not exist.
 *
 * <p>node A or node B may not exist in tree.
 *
 * <p>Have you met this question in a real interview? Example For the following binary tree:
 *
 * <p>4 / \ 3 7 / \ 5 6 LCA(3, 5) = 4
 *
 * <p>LCA(5, 6) = 7
 *
 * <p>LCA(6, 7) = 7
 */
public class _578 {
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        return search(root, A, B).lca;
    }

    private RT search(TreeNode root, TreeNode A, TreeNode B) {
        if (root == null) {
            return new RT(false, false, null);
        }

        RT left = search(root.left, A, B);
        RT right = search(root.right, A, B);

        boolean containA = left.containsA || right.containsA || (root == A);
        boolean containB = left.containsB || right.containsB || (root == B);

        // Need to update containA and containB no matter have lca or not. This is for all contions that
        // have lca.
        if (containA && containB) {
            if (left.lca == null && right.lca == null) {
                return new RT(true, true, root);
            } else if (right.lca != null) {
                return right;
            } else {
                return left;
            }
        }

        // update containA, containB.
        return new RT(containA, containB, null);
    }

    class RT {
        boolean containsA;
        boolean containsB;
        TreeNode lca;

        public RT(boolean containsA, boolean containsB, TreeNode lca) {
            this.containsA = containsA;
            this.containsB = containsB;
            this.lca = lca;
        }
    }
}
