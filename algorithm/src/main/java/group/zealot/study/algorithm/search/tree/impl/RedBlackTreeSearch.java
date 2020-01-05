package group.zealot.study.algorithm.search.tree.impl;

import group.zealot.study.algorithm.search.tree.AbsTreeSearch;
import group.zealot.study.algorithm.search.tree.AbsTree;
import org.springframework.stereotype.Component;

/**
 * 红黑树：
 * 【1】、根节点黑色
 * 【2】、节点颜色要么是黑，要么是红
 * 【3】、相邻接点颜色不能都是红
 * 【4】、叶节点到根节点路径上，黑节点个数都一样
 * <p>
 * 备注:
 * 1、叶节点高度指 叶节点到根节点路径上黑节点个数
 * 2、默认插入的节点是红色（不增加树高，仅可能与【1】【3】冲突，后续调整方式好处理）
 * 3、X表示刚插入的节点（触发节点），G表示祖父节点，F表示父节点，P表示G的另一个子节点（叔节点），A表示F的另一个子节点
 * 4、Xr中r表示red 红色节点，Xb中b表示black黑色节点
 * 5、left节点的value 小于 父节点的value，right节点value 大于 父节点的value
 * 6、边缘树，指left、right节点均是null（叶节点）
 * 7、校验【4】时，对所有边缘树进行树高比对，若一致，则【4】通过
 *
 * @author zealotTL
 * @date 2019-12-29 08:50
 */
@Component
public class RedBlackTreeSearch extends AbsTreeSearch {
    private int high = -1;//红黑树的高度，-1表示未定义

    @Override
    protected AbsTree transformToTree() {
        RedBlackTree root = new RedBlackTree(null, 0);
        root.color = ColorType.BLACK;//根节点，直接变成黑色
        for (int i = 1; i < length; i++) {
            root = treeAddValue(root, i);
        }
        return root;
    }

    /**
     * 将点numbers[index]插入树tree中
     * 返回根节点
     *
     * @param tree  触发树（非null）
     * @param index 插入点的下标
     */
    private RedBlackTree treeAddValue(RedBlackTree tree, int index) {
        RedBlackTree root;
        int value = numbers[index];

        if (compare(tree.getValue(), value) == 0) {
            tree.addIndex(index);
            return tree;
        } else if (compare(tree.getValue(), value) == 1) {
            if (tree.left == null) {
                tree.left = new RedBlackTree(tree, index);
                root = adjust(tree.left);
            } else {
                root = treeAddValue(tree.left, index);
            }
        } else {
            if (tree.right == null) {
                tree.right = new RedBlackTree(tree, index);
                root = adjust(tree.right);
            } else {
                root = treeAddValue(tree.right, index);
            }
        }
        if (root.father != null) {
            root = tree;
        }
        return root;
    }

    /**
     * 检测触发节点是否需要调整
     * 1、触发节点为 根节点
     * 2、触发节点的父节点为红色
     * <p>LL LR RR RL四种情况，此处列举一种作样例
     * -           Gb
     * -          / \
     * -         P  Fr
     * -           / \
     * -          A  Xr
     *
     * @param xTree 触发节点（红色非null）
     * @return 返回当前树结构的根节点
     */
    private RedBlackTree adjust(RedBlackTree xTree) {
        if (xTree.father == null) {//触发节点为根节点
            xTree.color = ColorType.BLACK;//直接变黑，路径增高1
        } else if (xTree.father.color == ColorType.RED) {
            //触发节点非根且需要调整，则此节点 必然存在F、G节点
            //判断平衡打破的类型，并相应调整
            RedBlackTree fTree = xTree.father;
            RedBlackTree gTree = fTree.father;
            RedBlackTree pTree;
            if (fTree == gTree.left) {
                pTree = gTree.right;
            } else {
                pTree = gTree.left;
            }
            if (pTree == null || pTree.color == ColorType.BLACK) {
                return adjustPb(xTree, fTree, gTree);
            } else {
                return adjustPr(fTree, pTree, gTree);
            }
        }
        return xTree;
    }

    /**
     * Xr红色（触发节点），Fr红色，Gb黑色（既可以是根，也可以非根）
     * 1、P不存在
     * =》A不存在，XL、XR也不存在
     * 2、P为黑色
     * =》A为黑色
     * <p> LL
     * -           Gb            Fb
     * -          / \           / \
     * -         Fr Pb   =>    Xr Gr
     * -        / \              / \
     * -       Xr Ab            Ab Pb
     * <p> RR
     * -           Gb           Fb
     * -          / \          / \
     * -         Pb Fr  =>    Gr Xr
     * -           / \       / \
     * -          Ab Xr     Pb Ab
     *
     * <p> LR
     * -           Gb             Xb
     * -          / \           /   \
     * -         Fr Pb   =>    Fr   Gr
     * -        / \           / \   / \
     * -       Ab Xr         Ab XL XR Pb
     * -         / \
     * -        XL XR
     * <p> RL
     * -           Gb           Xb
     * -          / \         /   \
     * -         Pb Fr    => Gr   Fr
     * -           / \      / \   / \
     * -          Xr Ab    Pb XL XR Ab
     * -         / \
     * -        XL XR
     * <p>
     * 此树的叶节点高度保持不变
     *
     * @param xTree Xr节点（不为null）
     * @param fTree Fr节点（不为null）
     * @param gTree Gb节点（不为null）
     * @return 返回当前树结构的根节点
     */
    private RedBlackTree adjustPb(RedBlackTree xTree, RedBlackTree fTree, RedBlackTree gTree) {
        RedBlackTree rTree = gTree.father;//G的父节点，可能为空
        SonType fgSonType = getSonType(fTree, gTree);
        SonType xfSonType = getSonType(xTree, fTree);

        if (fgSonType == xfSonType) {// LL或RR
            //颜色：Gb-->Gr Fr-->Fb
            gTree.color = ColorType.RED;
            fTree.color = ColorType.BLACK;
            //结构
            RedBlackTree aTree = xfSonType == SonType.LEFT ? fTree.right : fTree.left;
            if (rTree != null) {
                link(fTree, rTree, getSonType(gTree, rTree));
            } else {
                fTree.father = null;
            }
            link(gTree, fTree, getOtherSonType(xfSonType));
            link(aTree, gTree, fgSonType);
            return fTree;
        } else {
            //颜色：Gb-->Gr Xr-->Xb
            gTree.color = ColorType.RED;
            xTree.color = ColorType.BLACK;
            //结构
            RedBlackTree xlTree = xTree.left;
            RedBlackTree xrTree = xTree.right;
            if (rTree != null) {
                link(xTree, rTree, getSonType(gTree, rTree));
            } else {
                xTree.father = null;
            }
            link(fTree, xTree, fgSonType);
            link(gTree, xTree, xfSonType);
            if (fgSonType == SonType.LEFT) {//LR
                link(xlTree, fTree, xfSonType);
                link(xrTree, gTree, fgSonType);
            } else {//RL
                link(xrTree, fTree, xfSonType);
                link(xlTree, gTree, fgSonType);
            }
            return xTree;
        }
    }

    /**
     * 建立父子关系
     *
     * @param son          子节点（可为null）
     * @param father       父节点（不为null）
     * @param relationship SonType，left/right
     */
    private void link(RedBlackTree son, RedBlackTree father, SonType relationship) {
        if (son != null) {
            son.father = father;
        }
        if (relationship == SonType.LEFT) {
            father.left = son;
        } else {
            father.right = son;
        }
    }

    /**
     * 返回父子关系SonType，left/right
     *
     * @param son    子节点（不为null）
     * @param father 父节点（不为null）
     */
    private SonType getSonType(RedBlackTree son, RedBlackTree father) {
        if (father.left == son) {
            return SonType.LEFT;
        } else {
            return SonType.RIGHT;
        }
    }

    /**
     * 获取相反的父子关系类型
     *
     * @param sonType 父子关系类型
     */
    private SonType getOtherSonType(SonType sonType) {
        return sonType == SonType.LEFT ? SonType.RIGHT : SonType.LEFT;
    }

    /**
     * Xr红色（触发节点），Fr红色，Gb黑色（既可以是根，也可以非根）
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
     * 此树的叶节点的高度不变，但G变色了，以下情况需要进一步调整
     * 1：G的父节点为红色，则G作为触发节点，再做调整 adjust(G)
     * 2：G为根节点，则调整 adjust(G)
     *
     * @param fTree Fr节点（不为null）
     * @param pTree P节点（不为null）
     * @param gTree Gb节点（不为null）
     */
    private RedBlackTree adjustPr(RedBlackTree fTree, RedBlackTree pTree, RedBlackTree gTree) {
        //Fr->Fb  Gb->Gr
        fTree.color = ColorType.BLACK;
        gTree.color = ColorType.RED;
        //Pr->Pb
        pTree.color = ColorType.BLACK;
        return adjust(gTree);
    }

    @Override
    protected AbsTree[] doSearchTree(AbsTree tree) {
        if (compare(tree.getValue(), key) == 0) {
            return returnTree(tree);
        } else if (compare(tree.getValue(), key) == 1) {
            return returnTree(((RedBlackTree) tree).left);
        } else {
            return returnTree(((RedBlackTree) tree).right);
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

    @Override
    protected boolean checkTree(AbsTree tree) {
        if (tree instanceof RedBlackTree) {
            RedBlackTree root = (RedBlackTree) tree;
            //检测规则【1】
            if (root.color == ColorType.BLACK) {
                high = -1;//红黑树的高度，-1表示未定义
                return checkTree((RedBlackTree) tree);
            } else {
                logger.warn("违反规则【1】");
            }
        }
        return false;
    }

    /**
     * 检测【3】、【4】
     * 因算法设定树的颜色只有红（RED）黑（BLACK），【2】必通过
     *
     * @param tree 待检测的树结构
     */
    private boolean checkTree(RedBlackTree tree) {
        if (tree != null) {
            if (tree.left == null && tree.right == null) {
                //边缘树叶节点的父节点
                int treeHigh = getTreeHigh(tree);
                if (high == -1) {
                    high = treeHigh;//定义整个红黑树的树高
                } else if (high != treeHigh) {
                    logger.warn("违反规则【4】");
                    logger.warn("-- tree {} index {}", tree.getValue(), tree.getIndexs());
                    logger.warn("-- high {} getTreeHigh {}", high, treeHigh);
                    return false;
                }
            } else if (tree.color == ColorType.RED && tree.father != null && tree.father.color == ColorType.RED) {
                logger.warn("违反规则【3】");
                logger.warn("-- tree {} index {} color RED", tree.getValue(), tree.getIndexs());
                logger.warn("-- tree.father {} index {} color RED", tree.father.getValue(), tree.father.getIndexs());
                return false;
            } else {
                if (checkTree(tree.left)) {
                    return checkTree(tree.right);
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 返回树的树高
     *
     * @param tree 边缘树，不为null
     */
    private int getTreeHigh(RedBlackTree tree) {
        RedBlackTree father = tree.father;
        int high = tree.color == ColorType.BLACK ? 1 : 0;//本节点是黑色，则加1（0+1）
        while (father != null) {
            if (father.color == ColorType.BLACK) {
                high++;
            }
            father = father.father;
        }
        return high;
    }

    private class RedBlackTree extends AbsTree {
        private ColorType color;

        private RedBlackTree father;
        private RedBlackTree left;
        private RedBlackTree right;

        RedBlackTree(RedBlackTree father, int index) {
            super(numbers[index], index);
            this.father = father;
            color = ColorType.RED;//默认插入节点为红【目的是尽量不打破平衡，以及调整类型减少】
        }

        @Override
        public void print() {

        }
    }

    private enum SonType {
        LEFT,
        RIGHT
    }

    private enum ColorType {
        RED,
        BLACK
    }
}
