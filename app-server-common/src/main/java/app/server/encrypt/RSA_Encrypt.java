package app.server.encrypt;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

/**
 * 生成RSA公私钥文件
 * @author 陈捷
 *
 * 2015-9-6上午9:34:09
 */
public class RSA_Encrypt {
	
	
	/** 指定加密算法为DESede */
	private static String ALGORITHM = "RSA";
	/** 指定key的大小 */
	private static int KEYSIZE = 1024;
	/** 指定公钥存放文件 */
	private static String PUBLIC_KEY_FILE = "D:/public.cer";
	/** 指定私钥存放文件 */
	private static String PRIVATE_KEY_FILE = "D:/private.key";

	/**
	* 生成密钥对
	*/
	private static void generateKeyPair() throws Exception{
		
		/** RSA算法要求有一个可信任的随机数源 */
	   SecureRandom sr = new SecureRandom();
	   /** 为RSA算法创建一个KeyPairGenerator对象 */
	   KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM);
	  /** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
	   kpg.initialize(KEYSIZE, sr);
	   /** 生成密匙对 */
	   KeyPair kp = kpg.generateKeyPair();
	   /** 得到公钥 */
	   Key publicKey = kp.getPublic();
	   /** 得到私钥 */
	   Key privateKey = kp.getPrivate();
	   /** 用对象流将生成的密钥写入文件 */
	   ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(PUBLIC_KEY_FILE));
	   ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(PRIVATE_KEY_FILE));
	   oos1.writeObject(publicKey);
	   oos2.writeObject(privateKey);
	   
	  /* oos1.writeObject(publicKey.getEncoded());
	   oos2.writeObject(privateKey.getEncoded());*/
	   /** 清空缓存，关闭文件输出流 */
	   oos1.close();
	   oos2.close();
	}

	/**
	* 加密方法
	* source： 源数据
	*/
	public static String encrypt(String source) throws Exception{
	   /** 将文件中的公钥对象读出 */
	   ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
	   Key key = (Key) ois.readObject();
	   ois.close();
	   /** 得到Cipher对象来实现对源数据的RSA加密 */
	   Cipher cipher = Cipher.getInstance(ALGORITHM);
	   cipher.init(Cipher.ENCRYPT_MODE, key);
	   byte[] b = source.getBytes();
	   /** 执行加密操作 */
	   byte[] b1 = cipher.doFinal(b);
	   BASE64Encoder encoder = new BASE64Encoder();
	   return encoder.encode(b1);
	}
	
	 
	 
	/**
	* 解密算法
	* cryptograph:密文
	*/
	public static String decrypt(String cryptograph) throws Exception{
	   /** 将文件中的私钥对象读出 */
	   ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
	   Key key = (Key) ois.readObject();
	   /** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
	   Cipher cipher = Cipher.getInstance(ALGORITHM);
	   cipher.init(Cipher.DECRYPT_MODE, key);
	   BASE64Decoder decoder = new BASE64Decoder();
	   byte[] b1 = decoder.decodeBuffer(cryptograph);
	   /** 执行解密操作 */
	   byte[] b = cipher.doFinal(b1);
	   return new String(b);
	}
	
	
	
	public static void main(String[] args) throws Exception {
		   
		//generateKeyPair();
		String source = "Hello World!";//要加密的字符串
	    String cryptograph = encrypt(source);//生成的密文
	    System.out.println(cryptograph);
	  
	    String target = decrypt(cryptograph);//解密密文
	    System.out.println(target);
		//String s = "s/iUg1UIbj1r21PeCP1JM5Y8im8WwE4nCt1QDtlZqRTWOVKVVyMOfR0IwQgWHoDT1NcYqwl+KzI58PwiO0b5gkCHdGsBJYy8BmS6P4+phxhfNx9EFVgK5mHpC4dN4mxnBiw5q6rVgTk3ryMYJL4mAqePfsGc3DHxFoBcQiaL+Xoac0+sV9At1OR37zJREKwkpuIV/Hx1Psk8uQgUvaZ08U3Y5BrXs38qGYCQGSH2P6S8oW9bwhc96azsI/E2lq4Tqs5M3bzsPkcjJkzobU2z3Fi1yEYsjnsxwBb3yuOKlzrv83vBWyjMPrfgyDtfk44fneWKvzVpvGDpWRxeD3YhnVf8ipPljYqh7gFUSnDHy/l0AxwpEnkc5RrDzDH44olIa12sVfFLLFY0XGEYN7LraEjEke7hX2glR5GE3z/UJojKRaSNxcs3CsHSH+Po+fyFZw5Zue22HzpIxJHu4V9oJXWRPA9qR8gtunfJNmc3Ee9nDlm57bYfOvqErQzgJlWvbKMNS1I+fZuKrFRjWAQt87tTqACMeReoQTN6V6f2mxw9BGAR7uknCbuM0dFgqxMGdp29Z8TKg3EH+cZhwoEZ5cBDnHKHawq2J75f9oX20FSOyILDVKbcW5l2ZlA8cJGN4V5iVsgyb/YboJg3p7eEp1v2gDoO9994M1Y/m0gGmR189UzogjRNczHdYshWOzbdZKuabPN/BcG34Mg7X5OOHzKz3uJULBPPjLIkaoPjfuox3WLIVjs23eDrkfOekwApKrGI6bqkDCfe+RI8fLAZgJSrdzX/WGHJ4YV7q8Mu0r+BSviDOWgB/Jmj5sxnU+3mEWRpDROHbH2rKr1bED7g3B1og0nlPg6Os6ZQF1VectMdCMEIFh6A04G+H8Lu0PnlWN0pXffeps67vWJqZJLfVt72tNIwUKH0lkciscNKX8DxZur8YVJgiFC7SngbFPHlyBgp2h6GcnY=";
		//byte[] data = Base64.decodeBase64(s.getBytes("UTF-8"));

	}
		   
}