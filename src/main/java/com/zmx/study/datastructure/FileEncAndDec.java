package com.zmx.study.datastructure;

import java.io.*;


public class FileEncAndDec {
    private static final int numOfEncAndDec = 0x99; //加密解密秘钥
    private static int dataOfFile = 0; //文件字节内容

    public static void main(String[] args) {
        int option = 1;

        String path = "C:\\Users\\zhangmaoxin\\Desktop";
        File file = new File(path);
        System.out.println(file.exists());

        String srcFileName = path + File.separator + "1111.xls";
        File srcFile = new File(srcFileName);

        String encFileName = path + File.separator + "encFile";
        File encFile = new File(encFileName);

        String decFileName = path + File.separator + "decFile";
        File decFile = new File(decFileName);

        try {
            if (option == 0) {
                EncFile(srcFile, encFile); //加密操作
            } else {
                DecFile(encFile, decFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void EncFile(File srcFile, File encFile) throws Exception {
        if (!srcFile.exists()) {
            System.out.println("source file not exixt");
            return;
        }

        if (!encFile.exists()) {
            System.out.println("encrypt file created");
            encFile.createNewFile();
        }
        InputStream fis = new FileInputStream(srcFile);
        OutputStream fos = new FileOutputStream(encFile);

        while ((dataOfFile = fis.read()) > -1) {
            fos.write(dataOfFile ^ numOfEncAndDec);
        }

        fis.close();
        fos.flush();
        fos.close();
    }

    private static void DecFile(File encFile, File decFile) throws Exception {
        if (!encFile.exists()) {
            System.out.println("encrypt file not exixt");
            return;
        }

        if (!decFile.exists()) {
            System.out.println("decrypt file created");
            decFile.createNewFile();
        }

        InputStream fis = new FileInputStream(encFile);
        OutputStream fos = new FileOutputStream(decFile);

        while ((dataOfFile = fis.read()) > -1) {
            fos.write(dataOfFile ^ numOfEncAndDec);
        }

        fis.close();
        fos.flush();
        fos.close();
    }


}
