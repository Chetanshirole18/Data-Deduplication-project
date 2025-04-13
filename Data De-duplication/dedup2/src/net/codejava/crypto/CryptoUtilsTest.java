package net.codejava.crypto;

import java.io.File;



/**
 * A tester for the CryptoUtils class.
 * @author www.codejava.net
 *
 */
public class CryptoUtilsTest {
	public static void main(String[] args) throws HashGenerationException 
	{
		//String key = "1ava has one boy";
		File inputFile = new File("C://Users//projectguru//Desktop//Files/abc.txt");
		File encryptedFile = new File("C://Users//projectguru//Desktop//Files/encryptabc.txt");
		File decryptedFile = new File("C://Users//projectguru//Desktop//Files/decryptabc.txt");
		
		String k = HashGeneratorUtils.generateSHA256(inputFile);
		System.out.println(k);
		String key=(String) k.subSequence(0, 16);
		System.out.println(key);
		
		try 
		{
			CryptoUtils.encrypt(key, inputFile, encryptedFile);
			CryptoUtils.decrypt(key, encryptedFile, decryptedFile);
			System.out.println("Done");
		} catch (CryptoException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
}
