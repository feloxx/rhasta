package org.cdp.rhasta2.algorithm.sort;

import java.util.Arrays;

public class BubbleSort {
    public static <E extends Comparable<? super E>> void bubbleSort1(E[] comparable) {
        boolean changed = false;
        do {
            changed = false;
            for (int i = 0; i < comparable.length - 1; i++) {
                if (comparable[i].compareTo(comparable[i + 1]) > 0) {
                    E tmp = comparable[i];
                    comparable[i] = comparable[i + 1];
                    comparable[i + 1] = tmp;
                    changed = true;
                }
            }
        } while (changed);
    }

    public static void bubbleSort2(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = {1,10,2,19,0,-1};
        bubbleSort1(a);
        for (Integer i: a) {
            System.out.print(i + " ");
        }
        System.out.println();

        int[] b = {1,10,2,19,0,-1};
        bubbleSort2(b);
        for (int i: b) {
            System.out.print(i + " ");
        }
    }
}
