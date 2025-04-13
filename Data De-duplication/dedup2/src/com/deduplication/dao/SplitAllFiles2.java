package com.deduplication.dao;

import net.codejava.crypto.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.deduplication.db.ConnectionFactory;

public class SplitAllFiles2
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
        File folder = new File("C:/Deduplication/BlockWise/");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) 
        {
        	int nChunks = 0;
            if (file.isFile()) 
            {
            	File encryptPath=new File("C:/Deduplication/BlockWise/"+file.getName());
            	File decryptPath=new File("C:/Deduplication/Cache/"+file.getName());
            	//System.out.println(file.getName());
            	
            	Connection con=ConnectionFactory.getInstance().getConnection();
            	Statement st=con.createStatement();
            	ResultSet rs=st.executeQuery("select hash from blockindex where filepath='"+"C:/Deduplication/BlockWise/"+file.getName()+"'");
            	String key=null;
            	
            	while(rs.next())
            	{
            		key=(String) rs.getString(1).subSequence(0, 16);
            		
            	}
            	        	
            	
                CryptoUtils.decrypt(key, encryptPath, decryptPath);
                       		
        		
            }
        }
		
	}
	public static void main(String args[]) throws CryptoException, SQLException
	{
		
		SplitAllFiles.splitAllFiles();
		System.out.println("Done....");
	}

}
