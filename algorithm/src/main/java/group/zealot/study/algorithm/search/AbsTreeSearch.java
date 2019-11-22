package group.zealot.study.algorithm.search;

import static group.zealot.study.algorithm.util.Utils.NumberUtil;
import static group.zealot.study.algorithm.util.Utils.NumbersUtil;

public abstract class AbsTreeSearch extends AbsDefultSearch {


    /**
     * 各搜索算法的预处理机制
     * 返回树形结构根节点
     */
    abstract protected Tree preprocessNumbers();

    /**
     * 配合各算法预处理机制
     * key与节点比较，若节点满足条件，则返回此节点，否则返回下一个待比较的节点（若无，则返回null）
     *
     * @param tree 树节点
     */
    abstract protected Tree contrastTree(Tree tree);

    /**
     * 简单树搜索算法
     */
    protected void doSearchKey() {
        Tree father = preprocessNumbers();

        while (father != null) {
            Tree son = contrastTree(father);
            if (father.equals(son)) {
                keyPoints = father.indexs;
                return;
            } else {
                father = son;
            }
        }

    }

    abstract class Tree {
        public static final int D = 10;
        protected int value;//值
        protected int[] indexs;//相同值的原数组下标节点
        private int indexsLength;

        Tree(int value, int index) {
            this.value = value;
            this.indexs = new int[D];
            NumbersUtil.initNumbers(this.indexs);
            addIndex(index);
            indexsLength = 1;
        }

        /**
         * value 相等则返回true
         */
        @Override
        public boolean equals(Object o) {
            if (o != null) {
                if (o instanceof Tree) {
                    contrastNumber++;
                    return NumberUtil.contrast(((Tree) o).value, this.value);
                }
            }
            return false;
        }

        public void addIndex(int index) {
            if (indexsLength == indexs.length) {
                int[] newIndexs = new int[indexsLength + D];
                NumbersUtil.initNumbers(newIndexs);
                NumbersUtil.copy(indexs, newIndexs);
                indexs = newIndexs;
            }
            NumbersUtil.addValue(indexs, index);
            indexsLength++;
        }
    }
}
