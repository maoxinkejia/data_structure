package com.zmx.study.datastructure.sparsearray;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 稀疏数组
 */


public class SparseArray {
    private static final String FILE_NAME = "D:\\sparsearray.txt";

    public static void main(String[] args) {
        // 创建一个原始的二维数组 11 * 11
        // 0:表示没有旗子， 1:表示黑子， 2:表示蓝子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;

        // 输出原有二位数组
        outArray(chessArr1);

        // 将二维数组  转  稀疏数组的思路
        // 1.先遍历二维数组  得到非0数据的个数
        int sum = 0;
        for (int[] row : chessArr1) {
            for (int col : row) {
                if (col != 0) {
                    sum++;
                }
            }
        }


        // 2.创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        // 第一行记录二维数组有几行，几列，几个有效数字
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        sparseArr[0][2] = sum;

        // 3.遍历二维数组，将非0数据存放到稀疏数组中
        int count = 0; // 用来递增记录第几个非0数据
        for (int i = 0; i < sparseArr[0][0]; i++) {
            for (int j = 0; j < sparseArr[0][1]; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        // 遍历稀疏数组，看效果
        for (int[] arr : sparseArr) {
            System.out.printf("%d\t%d\t%d\t\n", arr[0], arr[1], arr[2]);
        }

        // 存储稀疏数组到磁盘
        write2File(sparseArr);

        // 读取磁盘的稀疏数组到内存
        int[][] fileArray = readFromFile();

        // 将稀疏数组转换成二维数组
        int[][] chessArr2 = new int[fileArray[0][0]][fileArray[0][1]];
        for (int i = 1; i < fileArray.length; i++) {
            chessArr2[fileArray[i][0]][fileArray[i][1]] = fileArray[i][2];
        }

        outArray(chessArr2);
    }

    private static int[][] readFromFile() {
        int[][] sparseArray = null;
        try {
            BufferedReader bf = new BufferedReader(new FileReader(FILE_NAME));

            List<String> list = new ArrayList<>();
            String str;
            while ((str = bf.readLine()) != null) {
                list.add(str);
            }

            sparseArray = new int[list.size()][3];
            for (int i = 0; i < sparseArray.length; i++) {
                String[] split = list.get(i).split("\\t");
                for (int j = 0; j < sparseArray[i].length; j++) {
                    sparseArray[i][j] = Integer.parseInt(split[j]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sparseArray;
    }

    private static void write2File(int[][] sparseArr) {
        try {
            FileOutputStream fos = new FileOutputStream(FILE_NAME);
            for (int[] rows : sparseArr) {
                for (int col : rows) {
                    String s = col + "\t";
                    fos.write(s.getBytes("UTF-8"));
                }
                fos.write("\r\n".getBytes("UTF-8"));
            }

            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void outArray(int[][] array) {
        for (int[] row : array) {
            for (int col : row) {
                System.out.printf("%d\t", col);
            }
            System.out.println();
        }
    }
}
