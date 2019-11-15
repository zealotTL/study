package group.zealot.study.algorithm.sort;

import group.zealot.study.algorithm.util.NumberUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 基数排序
 */
public class RadixSort extends AbsSort {
    private static final int bitLength = 10;

    @Override
    public void doSortMinToMax() {
        List<List<Integer>> list = splitNumbers();
        list.forEach(RadixSort::sort);
    }

    private static void sort(List<Integer> item) {

    }

    private List<List<Integer>> splitNumbers() {
        List<List<Integer>> list = new ArrayList<>();
        int x = 0;
        while (x++ < bitLength) {
            list.add(new LinkedList<>());
        }
        for (int i : numbers) {
            List<Integer> item = list.get(NumberUtil.getLength(i));
            item.add(i);
        }
        return list;
    }

    /**
     * 比较i和j在第bit位上数字的大小，返回大元素
     *
     * @param i   i元素
     * @param j   j元素
     * @param bit 比较第bit位
     */
    private int contrastBitReturnMax(int i, int j, int bit) {
        int iBit = NumberUtil.getBitValue(i, bit);
        int jBit = NumberUtil.getBitValue(j, bit);
        contrastNumber++;//比较次数增加
        if (iBit < jBit) {
            return j;
        } else {
            return i;
        }
    }
}