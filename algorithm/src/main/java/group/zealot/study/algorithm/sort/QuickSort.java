package group.zealot.study.algorithm.sort;

import org.springframework.stereotype.Component;

/**
 * 快速排序
 */
@Component
public class QuickSort extends AbsSort {
    private int num = 0;

    @Override
    public void doSort(int[] numbers) {
        quickSort(numbers, 0, numbers.length - 1);
    }

    /**
     * 对数组numbers的部分（numbers[start]~numbers[end]）进行排序
     *
     * @param numbers 数组
     * @param start   起始元素下标
     * @param end     结束元素下标
     */
    private void quickSort(int[] numbers, int start, int end) {
        int q = start;//默认以第一个元素为基准元素，所以先从右往左找第一个比基准元素小的

        int left = start + 1;
        int right = end;
        while (left <= right) {
            right = getRight(numbers, left, right, q);
            if (right == -1) {
                break;
            } else {
                exchange(numbers, q, right);
                q = right;
                right = right - 1;

                left = getLeft(numbers, left, right, q);

                if (left == -1) {
                    break;
                } else {
                    exchange(numbers, q, left);
                    q = left;
                    left = left + 1;
                }
            }
        }

        logNumbers(numbers, ++num);
        if (start < q - 1) {
            quickSort(numbers, start, q - 1);
        }
        if (q + 1 < end) {
            quickSort(numbers, q + 1, end);
        }
    }


    /**
     * 对数组numbers的部分（numbers[start]~numbers[end]），从左边搜索，找到第一个大于 基准元素(numbers[q])的元素，返回其下标，否则返回-1
     *
     * @param numbers 数组
     * @param start   起始元素下标
     * @param end     结束元素下标
     * @param q       基准元素下标
     */
    private int getLeft(int[] numbers, int start, int end, int q) {
        for (int i = start; i <= end; i++) {
            int left = contrast(numbers, q, i);
            if (left != q) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 对数组numbers的部分（numbers[start]~numbers[end]），从右边搜索，找到第一个小于 基准元素(numbers[q])的元素，返回其下标，否则返回-1
     *
     * @param numbers 数组
     * @param start   起始元素下标
     * @param end     结束元素下标
     * @param q       基准元素下标
     */
    private int getRight(int[] numbers, int start, int end, int q) {
        for (int i = end; i >= start; i--) {
            int right = contrast(numbers, q, i);
            if (right == q) {
                return i;
            }
        }
        return -1;
    }
}
