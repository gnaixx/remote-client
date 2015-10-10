package com.mlegeb.remote.common;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 名称: FileUtil
 * 描述: 文件读写
 *
 * @author xiangqing.xue
 * @date 15/10/10
 */
public class FileUtil {

    //根目录
    public static final String BASE_PATH = Environment.getExternalStorageDirectory().getPath()
            + File.separator
            + "ibaozhang"
            + File.separator;

    /**
     * 判断存储状态
     *
     * @return
     */
    public static boolean isExternalStorageMounted() {
        return android.os.Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 创建文件夹
     *
     * @param path
     * @return
     */
    public static boolean createFolder(String path) {
        File file = new File(path);
        return (file.exists() && file.isDirectory()) || file.mkdirs();
    }

    /**
     * 写文件
     * @param msg
     */
    public static void writeFile(String path, String msg) {
        File file = new File(path);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file, true);
            fos.write(msg.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    LogUtil.e("");
                }
            }
        }
    }
}
