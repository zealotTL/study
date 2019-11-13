package group.zealot.study.algorithm.sort;

import org.springframework.stereotype.Component;

/**
 * 选择排序：
 *      例如有长度为10的数组{10,9,8,7,6,5,4,3,2,1}
 *      将下标为第一位的元素暂时设定为最小值（numbers[index]）,其后的9位元素依次与首位元素比较
 *      第一次排序将最小值放置在首位，而后numbers[index]与实际最小值numbers[i]元素交换，结果：{1,9,8,7,6,5,4,3,2,10}
 *      第二次排序时，由于第一次排序已经把最小值放置首位，故剩9位数，此时9位数的首位（numbers[i+1]）与后8位比较，
 *      结果：{1,2,8,7,6,5,4,3,9,10}
 *      第三次依次类推……
 *      第9次排序时，前8位已排序完毕，此时只剩下最后两位，故比较一次就结束。
 *      至此可看出，长度为n的数组，则需要n-1轮排序，第一轮确定最小值放置首位，第二轮确定第二小值放在第二位……第九轮结束。
 */
@Component
public class SelectionSort extends AbsSort {
    @Override
    public void doSortMinToMax() {
        int num = 1;
        /**
         * 第一次for循环代表选择排序的次数，每次与第一位比较大小，
         * 一轮后将最小的数字筛选出来放置第一位，故数组长度为n时则需排n-1次。
         */
        for (int i = 0; i < length - 1; i++) {
            contrastAndExchange(i);
            logNumbers(num++);
        }
    }


    /**
     * 选择排序的核心逻辑
     * @param i 排序的第i轮
     */
    private void contrastAndExchange(int i) {
        int index = i;//看做数组的首位下标
        int j;//看做数组的下标
        for (j = i + 1; j < length; j++) {
            if (iGreaterJ(index, j)) {//该方法是比较首位元素与后面所有元素的大小，如果首位元素大于比较的元素，则交换下标
                index = j;//将比较后的小值下标赋值为首位元素下标，后面for循环剩下的值挨个与首位比较，统计交换次数
            }
        }
        if (index != i) {//当该轮排序后面的元素与首位元素比较完了之后，判断首位元素下标是否改变，如果首位元素下标改变，
            exchange(index, i);//则交换首位元素（numbers[i]）和最小值元素(numbers[index])
        }
    }

}
