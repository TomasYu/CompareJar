package com.zhangyue.campare.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by zy1 on 16/11/2017.
 */
public class Utils {
    /**
     * 描述：判断一个字符串是否为null或空值.
     *
     * @param str 指定的字符串
     * @return true or false
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0 ;
    }

    public static String getCurPath(){
        File   file=new File(".");
        String path=file.getAbsolutePath();
        return path;
    }

    /**
     * 强制删除文件/文件夹(含不为空的文件夹)<br>
     * @param dir
     * @throws IOException
     * @see Files#deleteIfExists(Path)
     * @see Files#walkFileTree(Path, java.nio.file.FileVisitor)
     */
    public static void deleteIfExists(Path dir) throws IOException {
        try {
            Files.deleteIfExists(dir);
        } catch (DirectoryNotEmptyException e) {
            Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return super.postVisitDirectory(dir, exc);
                }
            });
        }
    }

    public static void write2File(String filePath,String content) {
        FileOutputStream fos = null;
        try {

            fos = new FileOutputStream(new File(filePath));
            FileChannel channel = fos.getChannel();
            ByteBuffer src = Charset.forName("utf8").encode(content);
            // 字节缓冲的容量和limit会随着数据长度变化，不是固定不变的
//            System.out.println("初始化容量和limit：" + src.capacity() + ","
//                    + src.limit());
            int length = 0;

            while ((length = channel.write(src)) != 0) {
                /*
                 * 注意，这里不需要clear，将缓冲中的数据写入到通道中后 第二次接着上一次的顺序往下读
                 */
//                System.out.println("写入长度:" + length);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
