package group.zealot.study.algorithm.search;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static group.zealot.study.algorithm.util.Utils.NumberUtil;

public abstract class AbsTreeSearch implements Search {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 计算比较次数
     */
    protected int contrastNumber = 0;
    /**
     * 树根节点
     */
    protected Tree root;
    /**
     * 搜索key
     */
    protected int key = -1;
    /**
     * 结果集
     */
    protected int[] keyPoints = null;


    @Override
    public int[] searchKey(int[] numbers, int key) {
        this.key = key;
        logger.debug("key：" + key + " 原始：" + JSONObject.toJSONString(numbers));
        this.root = preprocessNumbers(numbers);
        logger.debug("预处理结束");

        doSearchKey();

        if (keyPoints != null) {
            logger.debug("结果：keyPoints：" + JSONObject.toJSONString(keyPoints));
        } else {
            logger.debug("结果：keyPoints：无");
        }

        logger.debug("总比较次数：" + contrastNumber);
        return keyPoints;
    }

    /**
     * 各搜索算法的预处理机制
     */
    abstract protected Tree preprocessNumbers(int[] numbers);

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
        Tree father = root;

        while (father != null) {
            Tree son = contrastTree(father);
            if (father.equals(son)) {
                keyPoints = father.indexs;
            } else {
                father = son;
            }
        }

    }

    abstract class Tree {
        protected int value;//值
        protected int[] indexs;//原数组下标节点


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
    }
}
