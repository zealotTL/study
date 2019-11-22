package group.zealot.study.algorithm.search;

import org.springframework.stereotype.Component;

import static group.zealot.study.algorithm.util.Utils.NumberUtil;

/**
 * 二叉树搜索
 */
@Component
public class BinaryTreeSearch extends AbsTreeSearch {

    @Override
    protected BinaryTree preprocessNumbers() {
        BinaryTree root = new BinaryTree(numbers[0], 0);
        for (int i = 1; i < numbers.length; i++) {
            treeAddValue(root, i, numbers[i]);
        }
        return root;
    }

    private void treeAddValue(BinaryTree tree, int index, int value) {
        if (NumberUtil.contrast(tree.value, value)) {
            tree.addIndex(index);
        } else if (NumberUtil.contrastMaxA(tree.value, value)) {
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
    protected Tree contrastTree(Tree tree) {
        if (NumberUtil.contrast(tree.value, key)) {
            return tree;
        } else if (NumberUtil.contrastMaxA(tree.value, key)) {
            return ((BinaryTree) tree).left;
        } else {
            return ((BinaryTree) tree).right;
        }
    }

    class BinaryTree extends Tree {
        BinaryTree left;//小于 value
        BinaryTree right;//大于 value

        BinaryTree(int value, int index) {
            super(value, index);
        }
    }
}
