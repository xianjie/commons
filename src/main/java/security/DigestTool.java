package security;

import exception.SystemException;
import org.apache.commons.lang3.Validate;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * 支持SHA-1/MD5消息摘要的工具类
 * 返回ByteSource，可进一步被编码为Hex, Base64或UrlSafeBase64
 */
public class DigestTool {

    public static final String SHA1 = "SHA-1";
    public static final String MD5 = "MD5";

    private static final SecureRandom random = new SecureRandom();

    /**
     * 对输入字符串字节数组进行sha1散列.
     *
     * @param input 字符串字节数组
     * @return 进行sha1散列后的字节数组
     */
    public static byte[] sha1(byte[] input) {
        return digest(input, SHA1, null, 1);
    }

    /**
     * 对输入字符串字节数组进行sha1散列.
     *
     * @param input 字符串字节数组
     * @param salt  加盐值字节数组
     * @return 进行sha1散列后的字节数组
     */
    public static byte[] sha1(byte[] input, byte[] salt) {
        return digest(input, SHA1, salt, 1);
    }

    /**
     * 对输入字符串字节数组进行sha1散列.
     *
     * @param input      字符串字节数组
     * @param salt       加盐值字节数组
     * @param iterations 迭代次数
     * @return 进行sha1散列后的字节数组
     */
    public static byte[] sha1(byte[] input, byte[] salt, int iterations) {
        return digest(input, SHA1, salt, iterations);
    }

    /**
     * 对字符串进行散列, 支持md5与sha1算法.
     *
     * @param input      字符串字节数组
     * @param algorithm  算法名称
     * @param salt       加盐值字节数组
     * @param iterations 迭代次数
     * @return 进行散列后的字节数组
     */
    public static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);

            if (salt != null) {
                digest.update(salt);
            }

            byte[] result = digest.digest(input);

            for (int i = 1; i < iterations; i++) {
                digest.reset();
                result = digest.digest(result);
            }
            return result;
        } catch (GeneralSecurityException e) {
            throw new SystemException(e);
        }
    }

    /**
     * 生成随机的Byte[]作为salt.
     *
     * @param numBytes byte数组的大小
     * @return 加盐值字节数组
     */
    public static byte[] generateSalt(int numBytes) {
        Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", numBytes);

        byte[] bytes = new byte[numBytes];
        random.nextBytes(bytes);
        return bytes;
    }

    /**
     * 对文件进行md5散列.
     *
     * @param input 文件输入流
     * @return 散列后的文件字节数组
     */
    public static byte[] md5(InputStream input) {
        try {
            return digest(input, MD5);
        } catch (IOException e) {
            throw new SystemException(e);
        }
    }

    /**
     * 对文件进行sha1散列.
     *
     * @param input 文件输入流
     * @return 散列后的文件字节数组
     */
    public static byte[] sha1(InputStream input) {
        try {
            return digest(input, SHA1);
        } catch (IOException e) {
            throw new SystemException(e);
        }
    }

    private static byte[] digest(InputStream input, String algorithm) throws IOException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            int bufferLength = 8 * 1024;
            byte[] buffer = new byte[bufferLength];
            int read = input.read(buffer, 0, bufferLength);

            while (read > -1) {
                messageDigest.update(buffer, 0, read);
                read = input.read(buffer, 0, bufferLength);
            }

            return messageDigest.digest();
        } catch (GeneralSecurityException e) {
            throw new SystemException(e);
        }
    }

}
