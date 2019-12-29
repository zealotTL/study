package group.zealot.study.algorithm.search.impl;

import group.zealot.study.algorithm.search.AbsDefultSearch;
import org.springframework.stereotype.Component;

/**
 * 二分搜索
 */
@Component
public class BinaryDefultSearch extends AbsDefultSearch {

    @Override
    protected void doSearchKey() {
        int start = 0;
        int end = length - 1;

        binarySearch(start, end);
    }

    private void binarySearch(int start, int end) {
        int p = getBinaryPoint(start, end);
        doBinarySearch(start, p);
        doBinarySearch(p + 1, end);
    }

    private int getBinaryPoint(int start, int end) {
        return (start + end) / 2;
    }

    private void doBinarySearch(int start, int end) {
        if (start > end) {//无需处理
        } else if (start == end) {
            if (compareKey(start)) {
                addKeyPoint(start);
            }
        } else {
            binarySearch(start, end);
        }
    }


}
