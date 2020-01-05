package group.zealot.study.algorithm.search.tree;

import group.zealot.study.algorithm.search.AbsSearch;

public abstract class AbsTreeSearch extends AbsSearch {

    /**
     * 预处理，整理numbers数组成tree树形结构，来达到快速查询
     *
     * @return 返回处理后的树根节点
     */
    abstract protected AbsTree transformToTree();

    /**
     * 检测树结构是否正确
     *
     * @param tree 树根节点
     */
    abstract protected boolean checkTree(AbsTree tree);

    /**
     * 搜索树，并返回多棵树
     * 存着key的节点，必须在返回的数组[树]当中，可以是树节点或子节点
     *
     * @param tree 树节点
     * @return trees 数组[树]（可为null）
     */
    abstract protected AbsTree[] doSearchTree(AbsTree tree);

    /**
     * 开始搜索树结构（默认从根节点开始）
     */
    protected void doSearchKey() {
        logger.debug("transformToTree 开始");
        AbsTree root = transformToTree();
        boolean checkResult = checkTree(root);
        if (checkResult) {
            logger.debug("transformToTree 完成，树结构检测结果：" + checkResult);
        } else {
            logger.error("transformToTree 完成，树结构检测结果：" + checkResult);
        }
        root.print();//打印树形结构
        searchTree(root);
    }

    /**
     * 从指定树（father）结构搜索
     *
     * @param father 树节点
     */
    private void searchTree(AbsTree father) {
        AbsTree[] sons = doSearchTree(father);
        if (sons == null) {
            return;
        }
        for (AbsTree son : sons) {
            if (compare(son.getValue(), key) == 0) {
                addKeyPointTree(son);
            } else {
                searchTree(son);
            }
        }
    }

    protected void addKeyPointTree(AbsTree tree) {
        for (int index : tree.getIndexs()) {
            addKeyPoint(index);
        }
    }

}
