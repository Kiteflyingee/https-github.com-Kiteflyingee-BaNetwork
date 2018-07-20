package taskplot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ReadUtils {

	public static Set<Integer> readCiteCSV(String filepath) throws IOException {
		Set<Integer> idSet = new HashSet<>();
		FileReader fr = new FileReader(new File(filepath));
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		while (line != null && line.trim().equals("")) {
			String[] strs = line.split(",");
			idSet.add(Integer.parseInt(strs[0]));
			idSet.add(Integer.parseInt(strs[1]));
		}
		fr.close();
		br.close();
		return idSet;
	}

	public static Map<Integer, Integer> readPaperYear(String filepath) throws IOException {
		Map<Integer, Integer> map = new TreeMap<>();
		FileReader fr = new FileReader(new File(filepath));
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		while (line != null && line.trim().equals("")) {
			String[] strs = line.split(",");
			String year = strs[1].substring(0, 4);
			map.put(Integer.parseInt(strs[0]), Integer.parseInt(year));
		}
		fr.close();
		br.close();
		return map;
	}
}
