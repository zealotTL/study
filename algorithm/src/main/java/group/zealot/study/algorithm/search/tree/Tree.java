package group.zealot.study.algorithm.search.tree;

import lombok.Getter;

import static group.zealot.study.algorithm.util.Utils.NumbersUtil;

/**
 * @author zealotTL
 * @date 2019-12-29 11:39
 */
public abstract class Tree {
    @Getter
    private int value;//值
    @Getter
    private int[] indexs;//相同值的原数组下标节点

    public Tree(int value, int index) {
        this.value = value;
        this.indexs = new int[10];
        NumbersUtil.initNumbers(this.indexs);
        addIndex(index);
    }

    public void addIndex(int index) {
        indexs = NumbersUtil.addValue(indexs, index);
    }
}
