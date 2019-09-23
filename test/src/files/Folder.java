package files;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Folder {
	
	private String[] files;
	ArrayList<String> pathsCache = new ArrayList<String>();
	
	public Folder(String path){
		File folder = new File(path);
		
		pathsCache.clear();
		
		String[] filesString = scanFolder(folder);
		
		pathsCache.clear();
		
		this.files = filesString;
		System.out.println("Loaded folder \"" + path + "\"");
	}
	
	public String[] getFiles() {
		return files;
	}
	
	private String[] scanFolder(File path) {
		System.out.println(path.toURI().toString() + " is a directory: " + path.isDirectory());
		if(path.isDirectory() && path.listFiles() != null) {
			for (int i = 0; i < path.listFiles().length; i++) {
				scanFolder(path.listFiles()[i]);
			}
		}
		else {
			pathsCache.add(path.toURI().toString());
		}
		
		String[] pathsArray = new String[pathsCache.size()];
		for(int i = 0; i < pathsCache.size(); i++) {
			pathsArray[i] = pathsCache.get(i);
		}
		
		Arrays.sort(pathsArray);
		
		return pathsArray;
		
	}

}
