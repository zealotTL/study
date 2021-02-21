package group.zealot.study.algorithm.sort.impl;

import group.zealot.study.algorithm.sort.AbsSort;
import org.springframework.stereotype.Service;

/**
 * 归并排序
 */
@Service
public class MergeSort extends AbsSort {
    @Override
    public void doSortMinToMax() {
        mergeSortInternally(numbers,0,length - 1);
    }

    /**
     * \
     * 递归分组排序
     *
     * @param arr
     * @param start
     * @param end
     */
    private void mergeSortInternally(int[] arr,int start,int end) {
        if (end <= start) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSortInternally(arr,start,mid);
        mergeSortInternally(arr,mid + 1,end);
        merge(arr,start,mid,end);
    }

    /**
     * 合并
     *
     * @param arr
     * @param start
     * @param mid
     * @param end
     */
    private void merge(int[] arr,int start,int mid,int end) {
        int newN = end - start + 1;
        int[] temp = new int[newN];
        int i = start, j = mid + 1;
        int index = 0;
        // 将两边数据拷贝进临时数组
        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                temp[index++] = arr[i++];
            } else {
                temp[index++] = arr[j++];
            }
        }
        if (i <= mid) {
            while (i <= mid) {
                temp[index++] = arr[i++];
            }
        }
        if (j <= end) {
            while (j <= end) {
                temp[index++] = arr[j++];
            }
        }
        // 将数据拷贝进原数组
        for (int k = 0; k < newN; k++) {
            arr[start + k] = temp[k];
        }
    }
}
