package com.deduplication.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.deduplication.db.ConnectionFactory;

import net.codejava.crypto.*;

public class SplitAllFiles
{
	
	public static void splitAllFiles() throws CryptoException, SQLException 
	{
		String FILE_NAME = "C:/Deduplication/Cache/";
		int PART_SIZE = 900;
		File fileSplitDir=new File(FILE_NAME);
        if(!fileSplitDir.exists())
        {
        	fileSplitDir.mkdir();
        }
        File folder = new File("C:/Deduplication/EncryptFile/");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) 
        {
        	int nChunks = 0;
            if (file.isFile()) 
            {
            	File encryptPath=new File("C:/Deduplication/EncryptFile/"+file.getName());
            	File decryptPath=new File("C:/Deduplication/File/"+file.getName());
            	//System.out.println(file.getName());
            	Connection con=ConnectionFactory.getInstance().getConnection();
            	Statement st=con.createStatement();
            	ResultSet rs=st.executeQuery("select encryptkey from filestorage where filepath='"+"C:/Deduplication/EncryptFile/"+file.getName()+"'");
            	String key=null;
            	while(rs.next())
            	{
            		key=rs.getString(1);
            	}
            	      	
            	CryptoUtils.decrypt(key, encryptPath, decryptPath);
                File inputFile = new File("C:/Deduplication/File/"+file.getName());
                String FILE_NAME1="C:/Deduplication/Cache/"+file.getName();
        		FileInputStream inputStream;
        		String newFileName;
        		FileOutputStream filePart;
        		int fileSize = (int) inputFile.length();
        		int read = 0, readLength = PART_SIZE;
        		byte[] byteChunkPart;
        		try {
        			inputStream = new FileInputStream(inputFile);
        			while (fileSize > 0) 
        			{
        				if (fileSize <= 5) 
        				{
        					readLength = fileSize;
        				}
        				byteChunkPart = new byte[readLength];
        				read = inputStream.read(byteChunkPart, 0, readLength);
        				fileSize -= read;
        				assert (read == byteChunkPart.length);
        				nChunks++;
        				newFileName = FILE_NAME1 + ""+ Integer.toString(nChunks - 1)+"_1KB";
        				if(FILE_NAME1.contains(".part"))
        				{
        					newFileName .substring(0,newFileName .length()-1);
        				}
        				filePart = new FileOutputStream(new File(newFileName));
        				filePart.write(byteChunkPart);
        				filePart.flush();
        				filePart.close();
        				byteChunkPart = null;
        				filePart = null;
        			}
        			inputStream.close();
        			
        		} catch (IOException exception) 
        		{
        			exception.printStackTrace();
        		}
        		
            }
        }
		
	}
	public static void main(String args[]) throws CryptoException, SQLException
	{
		
		SplitAllFiles.splitAllFiles();
		System.out.println("Done....");
	}

}
