package group.zealot.study.algorithm.search.tree;

import group.zealot.study.algorithm.search.AbsDefultSearch;

public abstract class AbsTreeSearch extends AbsDefultSearch {

    /**
     * 预处理，整理numbers数组成tree树形结构，来达到快速查询
     *
     * @return 返回处理后的树根节点
     */
    abstract protected Tree transformToTree();

    /**
     * 搜索树，并返回多棵树
     * 存着key的节点，必须在返回的数组[树]当中，可以是树节点或子节点
     *
     * @param tree 树节点
     * @return trees 数组[树]
     */
    abstract protected Tree[] doSearchTree(Tree tree);

    /**
     * 开始搜索树结构（默认从根节点开始）
     */
    protected void doSearchKey() {
        logger.debug("transformToTree 开始");
        Tree root = transformToTree();
        logger.debug("transformToTree 完成");
        logTree(root);
        searchTree(root);
    }

    /**
     * 从指定树（father）结构搜索
     *
     * @param father 树节点
     */
    private void searchTree(Tree father) {
        Tree[] sons = doSearchTree(father);
        if (sons == null) {
            return;
        }
        for (Tree son : sons) {
            if (compareValue(son.getValue(), key)) {
                addKeyPointTree(son);
            } else {
                searchTree(son);
            }
        }
    }

    protected void addKeyPointTree(Tree tree) {
        for (int index : tree.getIndexs()) {
            addKeyPoint(index);
        }
    }

    protected void logTree(Tree tree) {

    }

}
