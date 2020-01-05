package group.zealot.study.algorithm.search.tree.impl;

import group.zealot.study.algorithm.search.tree.AbsTreeSearch;
import group.zealot.study.algorithm.search.tree.AbsTree;
import org.springframework.stereotype.Component;


/**
 * 二叉树搜索
 */
@Component
public class BinaryTreeSearch extends AbsTreeSearch {

    @Override
    protected BinaryTree transformToTree() {
        BinaryTree root = new BinaryTree(0);
        for (int i = 1; i < length; i++) {
            treeAddValue(root, i);
        }
        return root;
    }

    @Override
    protected boolean checkTree(AbsTree tree) {
        if (tree instanceof BinaryTree) {
            return checkTree((BinaryTree) tree);
        }
        return false;
    }

    private boolean checkTree(BinaryTree tree) {
        if (tree.left != null) {
            if (compare(tree.getValue(), tree.left.getValue()) != 1) {
                logger.warn("树结构检测不通过");
                logger.warn("tree {} ,index {}", tree.getValue(), tree.getIndexs());
                logger.warn("tree.left {} index {}", tree.left.getValue(), tree.left.getIndexs());
                return false;
            }
            if (!checkTree(tree.left)) {
                return false;
            }
        }
        if (tree.right != null) {
            if (compare(tree.getValue(), tree.right.getValue()) != -1) {
                logger.warn("树结构检测不通过");
                logger.warn("tree {} index {}", tree.getValue(), tree.getIndexs());
                logger.warn("tree.right {} index {}", tree.right.getValue(), tree.right.getIndexs());
                return false;
            }
            if (!checkTree(tree.right)) {
                return false;
            }
        }
        return true;
    }

    private void treeAddValue(BinaryTree tree, int index) {
        int value = numbers[index];
        if (compare(tree.getValue(), value) == 0) {
            tree.addIndex(index);
        } else if (compare(tree.getValue(), value) == 1) {
            if (tree.left == null) {
                tree.left = new BinaryTree(index);
            } else {
                treeAddValue(tree.left, index);
            }
        } else {
            if (tree.right == null) {
                tree.right = new BinaryTree(index);
            } else {
                treeAddValue(tree.right, index);
            }
        }
    }


    @Override
    protected AbsTree[] doSearchTree(AbsTree tree) {
        if (compare(tree.getValue(), key) == 0) {
            return returnTree(tree);
        } else if (compare(tree.getValue(), key) == 1) {
            return returnTree(((BinaryTree) tree).left);
        } else {
            return returnTree(((BinaryTree) tree).right);
        }
    }

    private AbsTree[] returnTree(AbsTree tree) {
        if (tree == null) {
            return null;
        } else {
            AbsTree[] trees = new AbsTree[1];
            trees[0] = tree;
            return trees;
        }
    }

    private class BinaryTree extends AbsTree {
        private BinaryTree left;//小于 value
        private BinaryTree right;//大于 value

        BinaryTree(int index) {
            super(numbers[index], index);
        }

        @Override
        public void print() {
            //todo 尚未完成打印树形结构功能
        }
    }
}
