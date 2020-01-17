package org.cdp.rhasta2.algorithm.sort;

public class InsertSort {
    public static void insertSort1(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insert = arr[i];
            int j = i - 1;
            while(j >= 0 && arr[j] > insert) {
                arr[j + 1] = arr[j];
                j -= 1;
            }
            arr[j + 1] = insert;
        }
    }

    public static void main(String[] args) {
        int[] a = {1,10,2,19,0,-1};
        insertSort1(a);
        for (Integer i: a) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
