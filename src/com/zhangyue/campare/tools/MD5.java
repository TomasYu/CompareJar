package com.zhangyue.campare.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

	public static byte[] md5(File file) {
		FileInputStream fis = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("md5");
			fis = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int readCount = 0;
			while ((readCount = fis.read(buffer)) > -1) {
				md5.update(buffer, 0, readCount);
			}
			return md5.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 计算一个 InputStream 的 md5 值，这是在实践中更常用的一种情况。
	 *
	 * Added by WangXiao on 07/18/2017
	 *
	 * @param is
	 * @return
	 */
	public static byte[] md5(InputStream is) {
		if (is == null) {
			return null;
		}
		try {
			MessageDigest md5 = MessageDigest.getInstance("md5");
			byte[] buffer = new byte[1024];
			int readCount;
			while ((readCount = is.read(buffer)) > -1) {
				md5.update(buffer, 0, readCount);
			}
			return md5.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static byte[] getByte(String b) {
		
		StringBuffer sb = new StringBuffer(b);
		byte by[]=new byte[sb.length()/2];
		for (int i = 0; i < sb.length(); i=i+2) {
			BigInteger big= new BigInteger(sb.substring(i, i+2), 16);
			by[i/2]=big.byteValue();
		}
		return by;
	}

    private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
            'E', 'F' };

    private final static String toHex(byte[] b) { // String to byte
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 0x0f]);
        }
        return sb.toString();
    }

    public final static String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            return toHex(messageDigest).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

	public static String getMd5ByFile(File file) {
		String value = null;
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(byteBuffer);
			BigInteger bi = new BigInteger(1, md5.digest());
			value = bi.toString(16);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}
}
