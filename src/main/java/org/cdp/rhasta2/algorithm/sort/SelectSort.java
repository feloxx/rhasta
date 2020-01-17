package org.cdp.rhasta2.algorithm.sort;

public class SelectSort {
    public static void selectSort1(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i + 1;
            int select = Integer.MAX_VALUE;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < select) {
                    index = j;
                    select = arr[j];
                }
            }
            int tmp = arr[i];
            arr[i] = arr[index];
            arr[index] = tmp;
        }
    }

    public static void selectSort2(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;
            int select = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < select) {
                    index = j;
                    select = arr[j];
                }
            }
            if (index != i) {
                arr[index] = arr[i];
                arr[i] = select;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 10, 2, 19, 0, -1};
        selectSort1(a);
        for (int i : a) {
            System.out.print(i + " ");
        }
        System.out.println();

        int[] b = {1, 10, 2, 19, 0, -1};
        selectSort2(b);
        for (int j : b) {
            System.out.print(j + " ");
        }
        System.out.println();
    }
}
