package com.golike.lookgank;

/**
 * Created by admin on 2018/2/7.
 */

public class SortHelper {

    private SortHelper() {
    }

    private static class SingleInstance {
        private final static SortHelper sortHelper = new SortHelper();
    }

    public static SortHelper getInstance() {
        return SingleInstance.sortHelper;
    }

    String sort(int[] arrary, MainActivity.Sort type) {
        switch (type) {
            case Pop:
                arrary = PopSort(arrary);
                break;
            case Choose:
                arrary = ChooseSort(arrary);
                break;
            default:
                break;
        }

        String st = "";
        for (int x : arrary) {
            st += x + " ";
        }
        return st;
    }

    int[] PopSort(int[] arrary) {
        for (int i = 1; i < arrary.length; i++) {
            for (int j = 0; j < arrary.length - i; j++) {
                if (arrary[j] > arrary[j + 1]) {
                    int temp = arrary[j];
                    arrary[j] = arrary[j + 1];
                    arrary[j + 1] = temp;
                }
            }
        }
        return arrary;
    }

    int[] ChooseSort(int[] arrary) {
        for (int i = 1; i < arrary.length; i++) {
            //设置未排序内容中的第一个数最小值
            int minIndex = i;
            //选择最小值
            for (int j = i; j < arrary.length - 1; j++) {
                if (arrary[minIndex] > arrary[j + 1]) {
                    minIndex = j + 1;
                }
            }
            //交换数据
            int temp = arrary[i];
            arrary[i] = arrary[minIndex];
            arrary[minIndex] = temp;
        }
        return arrary;
    }

}
