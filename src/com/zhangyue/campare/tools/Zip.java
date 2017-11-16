package com.zhangyue.campare.tools;
import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public final class Zip {
	public static final int BUF_SIZE	= 8192;
	
	public ArrayList<String> mZipFiles;
	
	public Zip() {
		mZipFiles = new ArrayList<>();
	}
	


	/** 解压某个压缩文件到目标文件夹
	 * @param zipFile
	 * @param targetBaseDir
	 * @param isRemove 如果文件存在是否删除掉
	 * @return
	 */
	public boolean unzip(String zipFile, String targetBaseDir , boolean isRemove) {
		boolean ret 	= true;
		boolean find	= false;
		byte[] buffer 	= new byte[BUF_SIZE];
		mZipFiles.clear();
		InputStream in = null;
		ZipInputStream  zipIn = null;
				
		try {
			File file 				= new File(zipFile);
			in	= new FileInputStream(file);
			zipIn 	= new ZipInputStream(in);
			ZipEntry entry 			= null;
			
			while (null != (entry = zipIn.getNextEntry())) {
				String zipName = entry.getName();
				
				try {
					File unzipFile = new File(targetBaseDir+File.separator+zipName);
					if (entry.isDirectory()) {
						if (!unzipFile.exists())
							if (!unzipFile.mkdirs()) {
								System.out.print("unzipFile mkDirs Fail");
							}
					} else {
						boolean exist = unzipFile.exists();
						if(isRemove && exist) {
							unzipFile.delete();
							unzipFile.createNewFile();
						}
						if (!exist) {
							File parentDir = unzipFile.getParentFile();
							parentDir.mkdirs();
							unzipFile.createNewFile();
						} 
						
						
						FileOutputStream out = new FileOutputStream(unzipFile);
						int bytes;
						
						while ((bytes = zipIn.read(buffer, 0, BUF_SIZE)) != -1) {
							out.write(buffer, 0, bytes);
						}
						
						out.close();
						mZipFiles.add(unzipFile.getAbsolutePath());
					}
					find = true;
				} catch (IOException e) {
					e.printStackTrace();
					ret = false;
					break;
				}
			}
			
			zipIn.close();
			in.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			ret = false;
		}
		
		return ret && find;
	}


}
