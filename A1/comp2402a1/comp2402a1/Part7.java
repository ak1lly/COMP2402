package comp2402a1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Part7 {
	
	/**
	 * Your code goes here - see Part0 for an example
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */
	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
		List<List<String>> l = new ArrayList<>();
		int prevCount=0;
		int count=0;
		List<String> block = new ArrayList<>();
		for (String line = r.readLine(); line != null; line = r.readLine()) {
			if(line.equals("***reset***")){
				if(count==prevCount+1){
					l.add(block);
					block = new ArrayList<>();
					block.add(line);
					l.add(block);
					block = new ArrayList<>();
				}else {
					block.add(line);
					l.add(block);
					block = new ArrayList<>();
				}
				count = 0;
				prevCount = 0;
			}else{
				if (count > prevCount) {
					prevCount=count;
					l.add(block);
					count = 0;
					block = new ArrayList<>();
				}
				block.add(line);
				count++;
			}
		}
		l.add(block);

		for(int i=(l.size())-1; i>-1; i--){
			List<String> b = l.get(i);
			//StringBuilder output= new StringBuilder();
			for(String element : b){
				w.println(element);
			}
		}
	}

	/**
	 * The driver.  Open a BufferedReader and a PrintWriter, either from System.in
	 * and System.out or from filenames specified on the command line, then call doIt.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader r;
			PrintWriter w;
			if (args.length == 0) {
				r = new BufferedReader(new InputStreamReader(System.in));
				w = new PrintWriter(System.out);
			} else if (args.length == 1) {
				r = new BufferedReader(new FileReader(args[0]));
				w = new PrintWriter(System.out);				
			} else {
				r = new BufferedReader(new FileReader(args[0]));
				w = new PrintWriter(new FileWriter(args[1]));
			}
			long start = System.nanoTime();
			doIt(r, w);
			w.flush();
			long stop = System.nanoTime();
			System.out.println("Execution time: " + 1e-9 * (stop-start));
		} catch (IOException e) {
			System.err.println(e);
			System.exit(-1);
		}
	}
}
