package group.zealot.study.algorithm.search.impl;

import group.zealot.study.algorithm.search.AbsSearch;
import org.springframework.stereotype.Component;

/**
 * 二分搜索
 */
@Component
public class BinarySearch extends AbsSearch {

    @Override
    protected void doSearchKey() {
        int start = 0;
        int end = length - 1;

        binarySearch(start, end);
    }

    /**
     * 对区间数组numbers[start,end]分割成两个数组，然后对两个子数组进行搜索
     *
     * @param start 区间数组的起始位置
     * @param end   区间数组的终点位置
     */
    private void binarySearch(int start, int end) {
        if (start == end) {
            doBinarySearch(start, end);
        } else {
            //将区间数组分隔成两部分 number[start,p]、numbers[p+1,end]，然后别人进行搜索
            int p = getBinaryPoint(start, end);
            doBinarySearch(start, p);
            doBinarySearch(p + 1, end);
        }
    }

    /**
     * 分隔numbers[start,end]成两部分，返回中点坐标
     *
     * @param start 区间数组的起始位置
     * @param end   区间数组的终点位置
     */
    private int getBinaryPoint(int start, int end) {
        return (start + end) / 2;//向下取整，方便后续计算 （1+2）/2 返回 1
    }

    /**
     * 区局数组numbers[start,end]
     * 如果只有一个元素，则比较改元素与key的大小，符合条件则纳入结果集数组中
     * 如果区间数组不止一个元素，则继续对数组进行分隔
     *
     * @param start 区间数组的起始位置
     * @param end   区间数组的终点位置
     */
    private void doBinarySearch(int start, int end) {
        if (start == end) {
            if (compareKey(start)) {
                addKeyPoint(start);
            }
        } else {// 此种情况只能是 (start < end)
            binarySearch(start, end);
        }
    }
}
