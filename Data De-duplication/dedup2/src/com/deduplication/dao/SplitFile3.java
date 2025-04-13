package com.deduplication.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class SplitFile3 
{
	
	private static int PART_SIZE = 450;
	public static void splitFile(String path) 
	{
		String FILE_NAME = path;
		        
		ArrayList<File> partlist=new ArrayList<File>();
		File inputFile = new File(FILE_NAME);
		FileInputStream inputStream;
		String newFileName;
		FileOutputStream filePart;
		int fileSize = (int) inputFile.length();
		int nChunks = 0, read = 0, readLength = PART_SIZE;
		byte[] byteChunkPart;
		try {
			inputStream = new FileInputStream(inputFile);
			while (fileSize > 0) {
				if (fileSize <= 5) {
					readLength = fileSize;
				}
				byteChunkPart = new byte[readLength];
				read = inputStream.read(byteChunkPart, 0, readLength);
				fileSize -= read;
				assert (read == byteChunkPart.length);
				nChunks++;
				newFileName = "C:/Deduplication/TMP/TMP2/"+inputFile.getName() + ".part"+ Integer.toString(nChunks - 1)+"_0.5KB";
				filePart = new FileOutputStream(new File(newFileName));
				filePart.write(byteChunkPart);
				File part=new File(newFileName);
				filePart.flush();
				filePart.close();
				byteChunkPart = null;
				filePart = null;
				//File deleteFile=new File(newFileName);
				//deleteFile.delete();
			}
			inputStream.close();
		} catch (IOException exception) 
		{
			exception.printStackTrace();
		}
		
	}
}
