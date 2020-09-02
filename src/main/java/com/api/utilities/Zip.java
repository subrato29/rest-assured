package com.api.utilities;  
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.net.SyslogAppender;

public class Zip extends Utility{
		 static String screenshotPath;
		private static String zipFile = "Results\\Reports.zip";
//		private static String[] srcFiles = { "Results\\git.PNG", "Results\\git2.PNG"};

		//private static String[] srcFiles = { Utility.htmlReportPathGenerated(), Utility.pathScreenshotCapturedFailedSteps};
		private static String[] srcFiles = {htmlReportPathGenerated()};
		public static void zipFile() {
			Utility.fnLogging(htmlReportPathGenerated());
			System.out.println("Zipping the files");
		try {
			
			// create byte buffer
			byte[] buffer = new byte[1024];
			
			FileOutputStream fos = new FileOutputStream(zipFile);

			ZipOutputStream zos = new ZipOutputStream(fos);
			
			for (int i=0; i < srcFiles.length; i++) {
				
				File srcFile = new File(srcFiles[i]);

				FileInputStream fis = new FileInputStream(srcFile);

				// begin writing a new ZIP entry, positions the stream to the start of the entry data
				zos.putNextEntry(new ZipEntry(srcFile.getName()));
				
				int length;

				while ((length = fis.read(buffer)) > 0) {
					zos.write(buffer, 0, length);
				}

				zos.closeEntry();

				// close the InputStream
				fis.close();
				
			}

			// close the ZipOutputStream
			zos.close();
			
		}
		catch (IOException ioe) {
			System.out.println("Error creating zip file: " + ioe);
		}
		
	}

}