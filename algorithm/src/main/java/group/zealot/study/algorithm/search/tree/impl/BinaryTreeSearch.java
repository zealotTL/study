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
        BinaryTree root = new BinaryTree(numbers[0], 0);
        for (int i = 1; i < numbers.length; i++) {
            treeAddValue(root, i, numbers[i]);
        }
        return root;
    }

    private void treeAddValue(BinaryTree tree, int index, int value) {
        if (compareValue(tree.getValue(), value)) {
            tree.addIndex(index);
        } else if (compareValue(tree.getValue(), value)) {
            if (tree.left == null) {
                tree.left = new BinaryTree(value, index);
            } else {
                treeAddValue(tree.left, index, value);
            }
        } else {
            if (tree.right == null) {
                tree.right = new BinaryTree(value, index);
            } else {
                treeAddValue(tree.right, index, value);
            }
        }
    }


    @Override
    protected AbsTree[] doSearchTree(AbsTree tree) {
        if (compareValue(tree.getValue(), key)) {
            return returnTree(tree);
        } else if (compareValue(tree.getValue(), key)) {
            return returnTree(((BinaryTree) tree).left);
        } else {
            return returnTree(((BinaryTree) tree).right);
        }
    }

    private AbsTree[] returnTree(AbsTree tree) {
        AbsTree[] trees = new AbsTree[1];
        trees[0] = tree;
        return trees;
    }

    class BinaryTree extends AbsTree {
        BinaryTree left;//小于 value
        BinaryTree right;//大于 value

        BinaryTree(int value, int index) {
            super(value, index);
        }

        @Override
        public void print() {
            //todo 尚未完成打印树形结构功能
        }
    }
}
