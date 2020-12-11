import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.io.Console;
//import java.io.Thread;

class WriteThread extends Thread
{
	private File file;
			WriteThread(File file){
				this.file = file;
			}

			//private File file;
			@Override
			public void run()	//Этот метод будет выполнен в побочном потоке
			{
				//System.out.println(file.getAbsolutePath() + "\n");
				System.out.println(this.file.getAbsolutePath());	
	}
}
 
/*public*/ class Test {
 
	public static void main(String[] args) throws Exception {
		Console console = System.console();
		
		String rootPath = console.readLine("Input rootPath: ");
			
		Integer depth = Integer.parseInt(console.readLine("Input depth: "));  
				
		String mask = console.readLine("Input mask: ");
		
		rootPath.replaceAll(Character.toString ((char)47) + Character.toString ((char)47),Character.toString ((char)47));
		
		try {	
		
			Main.listPath(rootPath, depth, mask);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
 
class Main {
	static WriteThread writeThread2;
 
	public static void listPath(String pathname, int depth, String mask) throws IOException {
		
		File path = new File(pathname).getAbsoluteFile();
		if (!path.exists()) {
			
			throw new FileNotFoundException(
					"Cannot access " + path.getPath()
					+ ": No such file");
		}
		
		if (!path.isDirectory()) {
			
			throw new IllegalArgumentException(
					"Cannot list content of " + path.getPath() +
					": Not a directory");
		}
		
		if (depth < 0) {
			
			throw new IllegalArgumentException(
					"List depth cannot be less than zero");
		}
 
		Queue<Entry> queue = new ArrayDeque<Entry>();
		Entry e = new Entry(0, path);
		do {
 
			int nextDepth = e.depth + 1;
			path = e.file;
			if (path.isDirectory()) {
				
				File[] files = path.listFiles();
				if (files != null) for (File item : files) {
					
					if (nextDepth <= depth && item.isDirectory()) {
						
						queue.offer(new Entry(nextDepth, item));
					}
					
					if (mask == null || item.getName().contains(mask)){
						writeThread2 = new WriteThread(item);	//Создание потока
						writeThread2.start();
					}
						
				}
			}
			
			e = queue.poll();
			
		} while (e != null);
	}
	
	private static class Entry {
		
		private int depth;
		private File file;
		
		public Entry(int depth, File file) {
			
			this.depth = depth;
			this.file = file;
		}
	}
}
