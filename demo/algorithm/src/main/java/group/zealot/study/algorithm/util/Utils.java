package group.zealot.study.algorithm.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Utils {
    public static NumbersUtil NumbersUtil;
    public static NumberUtil NumberUtil;
    public static SearchUtil SearchUtil;
    public static SortUtil SortUtil;

    @Autowired
    public void setNumbersUtil(NumbersUtil numbersUtil) {
        NumbersUtil = numbersUtil;
    }

    @Autowired
    public void setNumberUtil(NumberUtil numberUtil) {
        NumberUtil = numberUtil;
    }

    @Autowired
    public void setSearchUtil(SearchUtil searchUtil) {
        SearchUtil = searchUtil;
    }

    @Autowired
    public void setSortUtil(SortUtil sortUtil) {
        SortUtil = sortUtil;
    }
}
