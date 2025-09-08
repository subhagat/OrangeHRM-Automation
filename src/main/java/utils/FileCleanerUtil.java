package utils;


import java.io.File;

public class FileCleanerUtil {

//    public static void cleanFolder(String folderPath) {
//        File folder = new File(folderPath);
//        if (folder.exists() && folder.isDirectory()) {
//            File[] files = folder.listFiles();
//            if (files != null) {
//                for (File file : files) {
//                    if (file.isFile()) {
//                        file.delete();
//                    }
//                }
//            }
//        }
//    }
	


	    private static boolean cleaned = false;

	    public static void cleanScreenshotsOnce(String folderPath) {
	        if (cleaned) return;

	        File folder = new File(folderPath);
	        if (folder.exists() && folder.isDirectory()) {
	            File[] files = folder.listFiles();
	            if (files != null) {
	                for (File file : files) {
	                    if (file.isFile()) {
	                        file.delete();
	                    }
	                }
	                System.out.println("🧹 Screenshots cleaned for new execution.");
	            }
	        }

	        cleaned = true;
	    }
	}



