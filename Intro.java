import java.lang.System;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;


public class Intro{
	public static void main(String[] args) {
		Console console = System.console();
		
		String rootPath = console.readLine("Input rootPath: ");
		console.printf("Input rootPath: %s", rootPath);
		
        Integer depth = Integer.parseInt(console.readLine("\nInput depth: "));  
		System.out.println( "depth = " + depth );
		
		String mask = console.readLine("Input mask: ");
		console.printf("Input mask: %s", mask);
		
		File path = new File(rootPath).getAbsoluteFile();
		System.out.println( "\npath = " + path );
		if (!path.exists()) {
			console.printf("No search this path");
		} else{
			console.printf("");
		}
		
	}
}