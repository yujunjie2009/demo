package com.newtiming.core;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * Druid加密
 * @author yujunjie
 *
 */
public class DecryptDruid {
    /**
     * 对文字进行解密
     * @throws Exception
     */
    public  void testDecrypt() throws Exception {
        //解密
        String word="kJ1skM6AVS4sgv0yFcTc9qRiFSHazh5gao+U+Wf4zjE2hJWh2S4q8G/CQNId+/DTiWBxeykZynZFTUvjU9LU0g==";
        String decryptword = ConfigTools.decrypt(word);
        System.out.println(decryptword);
    }
    /**
     * 文字进行加密
     * @throws Exception 
     */
    public static void testEncrypt() throws Exception {
        //加密
        String password ="xdmfinance123$";
        String encryptword = ConfigTools.encrypt(password);
        System.out.println(encryptword);
        
    }
    
    public static void main(String[] args) throws Exception{
    	testEncrypt();
	}
}