package group.zealot.study.algorithm.search.tree.impl;

import group.zealot.study.algorithm.search.tree.AbsTreeSearch;
import group.zealot.study.algorithm.search.tree.AbsTree;

/**
 * 红黑树
 *
 * @author zealotTL
 * @date 2019-12-29 08:50
 */
public class RedBlackTreeSearch extends AbsTreeSearch {
    protected static boolean RED = true;
    protected static boolean BLACK = !RED;

    @Override
    protected AbsTree transformToTree() {
        RedBlackTree root = new RedBlackTree(null, 0);
        for (int i = 1; i < length; i++) {
            treeAddValue(root, i);
        }
        return root;
    }

    private void treeAddValue(RedBlackTree tree, int index) {
        int value = numbers[index];
        if (compare(tree.getValue(), value) == 0) {
            tree.addIndex(index);
        } else if (compare(tree.getValue(), value) == 1) {
            if (tree.left == null) {
                tree.left = new RedBlackTree(tree, index);
                adjust(tree.left);
            } else {
                treeAddValue(tree.left, index);
            }
        } else {
            if (tree.right == null) {
                tree.right = new RedBlackTree(tree, index);
                adjust(tree.right);
            } else {
                treeAddValue(tree.right, index);
            }
        }
    }

    /**
     * 检测触发节点是否需要调整
     * 1、触发节点为 根节点
     * 2、触发节点的父节点为红色
     * <p>
     * -           Gb
     * -          / \
     * -         P  Fr
     * -           / \
     * -          A  Xr
     *
     * @param xTree 触发节点
     */
    private void adjust(RedBlackTree xTree) {
        if (xTree.father == null) {//触发节点为根节点
            xTree.color = BLACK;//直接变黑，路径增高1
        } else if (xTree.father.color == RED) {
            //触发节点非根且需要调整，则此节点 必然存在F、G节点
            //判断平衡打破的类型，并相应调整（向上调整：后续各个调整类型前提都是当前节点的颜色都不变）
            RedBlackTree fTree = xTree.father;
            RedBlackTree gTree = fTree.father;
            RedBlackTree pTree = gTree.right;
            if (pTree == null) {
                adjustPNull(xTree, fTree, gTree);
            } else {
                adjustPNotNull(fTree, pTree, gTree);
            }
        }
    }

    /**
     * Xr红色（触发节点），Fr红色，Gb黑色（既可以是根，也可以非根）,P不存在
     * 推测：A也不存在
     * =》调整结构和颜色
     * <p> LL
     * -           Gb          Fb
     * -          /           / \
     * -         Fr     =>   Xr Gr
     * -        /
     * -       Xr
     * <p> LR
     * -           Gb          Xb
     * -          /           / \
     * -         Fr     =>   Fr Gr
     * -         \
     * -         Xr
     * <p> RL
     * -           Gb            Xb
     * -            \           / \
     * -            Fr  =>     Gr Fr
     * -            /
     * -           Xr
     * <p> RR
     * -           Gb            Fb
     * -            \           / \
     * -            Fr  =>     Gr Xr
     * -             \
     * -             Xr
     * <p>
     * 此树的叶节点到根节点的黑色节点个数不变
     *
     * @param xTree Xr节点（颜色不能变动）
     * @param fTree Fr节点
     * @param gTree Gb节点
     */
    private void adjustPNull(RedBlackTree xTree, RedBlackTree fTree, RedBlackTree gTree) {
        gTree.color = RED;
        if (gTree.left == fTree) {
            if (fTree.left == xTree) {//LL
                fTree.color = BLACK;
                //替换父子关系
                changeSon(fTree, gTree);
                fTree.right = gTree;
                gTree.father = fTree;
            } else {//LR
                xTree.color = BLACK;
                // x <---> f 双向父子关系异位（左子节点）
                fTree.right = null;
                fTree.father = xTree;
                xTree.left = fTree;
                //替换父子关系
                changeSon(xTree, gTree);
                xTree.right = gTree;
                gTree.father = xTree;
            }
        } else {
            if (fTree.left == xTree) {//RL
                xTree.color = BLACK;
                // x <---> f 双向父子关系异位（右子节点）
                fTree.left = null;
                fTree.father = xTree;
                xTree.right = fTree;
                //替换父子关系
                changeSon(xTree, gTree);
                xTree.right = gTree;
                gTree.father = xTree;
            } else {//RR
                fTree.color = BLACK;
                //替换父子关系
                changeSon(fTree, gTree);
                fTree.left = gTree;
                gTree.father = fTree;
            }
        }
    }

    /**
     * b节点替换成a节点
     * =》 F <---> a 建立双向父子关系，F <--- b 依旧是单向父子关系（仅有b的父节点是F）
     * <p> 无父节点
     * -      a  b     => a  b
     * <p> L
     * -           F           F
     * -          /           /
     * -      a  b     => b  a
     * -
     * <p> R
     * -         F           F
     * -         \           \
     * -       a  b     => b  a
     *
     * @param aTree 替换后的节点
     * @param bTree 替换前的节点
     */
    private void changeSon(RedBlackTree aTree, RedBlackTree bTree) {
        aTree.father = bTree.father;
        if (bTree.father != null) {
            if (bTree.father.left == bTree) {
                bTree.father.left = aTree;
            } else {
                bTree.father.right = aTree;
            }
        }
    }

    /**
     * Xr红色（触发节点），Fr红色，Gb黑色（既可以是根，也可以非根）
     * 1：P为黑色
     * 推测：A存在，为黑色
     * =》仅调整颜色：Fr->Fb  Gb->Gr
     * <p> LL
     * -           Gb            Gr
     * -          / \           / \
     * -         Fr Pb   =>    Fb Pb
     * -        / \           / \
     * -       Xr Ab         Xr Ab
     * <p> RL
     * -           Gb           Gr
     * -          / \          / \
     * -         Fr Pb    =>  Pb Fb
     * -        / \             / \
     * -       Xr Ab           Xr Ab
     * <p> RR
     * -           Gb           Gr
     * -          / \          / \
     * -         Pb Fr  =>    Pb Fb
     * -           / \          / \
     * -          Ab Xr        Ab Xr
     * <p> LR
     * -           Gb            Gr
     * -          / \           / \
     * -         Fr Pb   =>    Fb Pb
     * -        / \           / \
     * -       Ab Xr         Ab Xr
     * <p>
     * <p>
     * 2：P为红色
     * =》仅调整颜色：Fr->Fb  Gb->Gr  Pr->Pb
     * <p> LL
     * -           Gb            Gr
     * -          / \           / \
     * -         Fr Pr   =>    Fb Pb
     * -        / \           / \
     * -       Xr A          Xr A
     * <p> RL
     * -           Gb           Gr
     * -          / \          / \
     * -         Fr Pr    =>  Pb Fb
     * -        / \             / \
     * -       Xr A            Xr A
     * <p> RR
     * -           Gb           Gr
     * -          / \          / \
     * -         Pr Fr  =>    Pb Fb
     * -           / \          / \
     * -          A  Xr        A  Xr
     * <p> LR
     * -           Gb            Gr
     * -          / \           / \
     * -         Fr Pr   =>    Fb Pb
     * -        / \           / \
     * -       A  Xr         A  Xr
     * 此树的叶节点，到根节点的黑色节点个数不变，但G变色了，以下情况需要进一步调整
     * 1：G的父节点为红色，则G作为触发节点，再做调整 adjust(G)
     * 2：G为根节点，则调整 adjust(G)
     *
     * @param fTree Fr节点
     * @param pTree P节点
     * @param gTree Gb节点
     */
    private void adjustPNotNull(RedBlackTree fTree, RedBlackTree pTree, RedBlackTree gTree) {
        //Fr->Fb  Gb->Gr
        fTree.color = BLACK;
        gTree.color = RED;
        //Pr->Pb
        pTree.color = BLACK;
        adjust(gTree);
    }

    @Override
    protected AbsTree[] doSearchTree(AbsTree tree) {
        return null;
    }

    @Override
    protected boolean checkTree(AbsTree tree) {
        if (tree instanceof RedBlackTree) {
            RedBlackTree root = (RedBlackTree) tree;
            if (root.color == BLACK) {
                return checkTree((RedBlackTree) tree);
            } else {
                logger.warn("根节点为红色");
                return false;
            }

        }
        return false;
    }

    private boolean checkTree(RedBlackTree tree) {
        return true;
    }

    private class RedBlackTree extends AbsTree {
        private boolean color;

        private RedBlackTree father;
        private RedBlackTree left;
        private RedBlackTree right;

        RedBlackTree(RedBlackTree father, int index) {
            super(numbers[index], index);
            this.father = father;
            color = RED;//默认插入节点为红【目的是尽量不打破平衡，以及调整类型减少】
        }

        @Override
        public void print() {

        }
    }
}
