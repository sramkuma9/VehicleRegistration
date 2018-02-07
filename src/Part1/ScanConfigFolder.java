package Part1;

/*Title - scan a directory for csv/xls files.
Author - Srini Ramkumar
Date - 28/01/2018*/

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import javax.activation.MimetypesFileTypeMap;

public class ScanConfigFolder{
	
	public static ArrayList<String> configScan(){
		// Path to Configure directory.
	    File configuredDir = new File("/home/ajay/Srini/libraryFIlesSelenium/Srini/files");
	    ArrayList<String> configureXlsCsv = null;
	    
	    // check if the config dir is present and call the method to get CSV & XLS files.
	    if (configuredDir.exists()){
	    	configureXlsCsv = getCsvXlsFiles(configuredDir);
	    }
	    else {
	    	System.out.println("The configured directory cannot be found in specified location, exiting.");	
	    }
	    return configureXlsCsv;
	}
	
	public static ArrayList<String> getCsvXlsFiles(File configuredDir){
		String extension = "";
		ArrayList<String> configureXlsCsv = new ArrayList<>();
		String fileName = "";
		try{
			// count no: of files in the config dir.
			File[] filesList = configuredDir.listFiles();
		    if (filesList.length>9){
		    	System.out.println("The configured directory has more than 9 files.Below is the lists with details");
			    for(File f : filesList){
		            if(f.isFile()){
				            System.out.println(f.getName() + " Size = " + f.length() + "Bytes");
				            // to print the mimi type in the console.
				            MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
				            String mimeType = mimeTypesMap.getContentType(f);
				            System.out.println("MimiType = " + mimeType);
				            fileName = f.getName();
				            // find the extension of a file.
				            int i = fileName.lastIndexOf('.');
				            if (i > 0) {
				                extension = fileName.substring(i+1);
				            }
				            // if the extension is xls or csv, add the file in an array.
				            if (extension.equalsIgnoreCase("xls") || extension.equalsIgnoreCase("csv")){
				            	configureXlsCsv.add(configuredDir + "/" + fileName);
				            }
		            }
			    }			    
			    // message is there is no xls or csv file.
			    if (configureXlsCsv.isEmpty()) {
			    	System.out.println("There are no .xls and .csv files in the configured folder.");
			    }
			    else {
			    	System.out.println("Below are the .xls and .csv files from the configured folder.");
			    }
			    int i=0;			    
			    // print all the csv & xls files.
			    for (Iterator<String> it = configureXlsCsv.iterator(); it.hasNext(); i++){
					String xlsCSvFiles = it.next();
			    	System.out.println(xlsCSvFiles);
			    }
		    }
		    else
		    {
		    	System.out.println("The configured directory does not have more than 9 files.");
		    }
		} catch (NullPointerException e) {	
			System.out.println("The configured directory does not have any files.");
		}
		return configureXlsCsv;
	}	
}