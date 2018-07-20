package taskplot;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main2 {

	public static void main(String[] args) throws IOException {
		String cite_file = "C:\\Users\\devkite\\eclipse-workspace\\ba\\src\\paper\\a.csv";
		String cite_info = "C:\\Users\\devkite\\eclipse-workspace\\ba\\src\\paper\\b.csv";
		Set<Integer> idSet = ReadUtils.readCiteCSV(cite_file);
		Map<Integer, Integer> map = ReadUtils.readPaperYear(cite_info);
		// 用來存儲年份和對應數量的map集合
		Map<Integer, Integer> yearMap = new HashMap<>();
		for (Integer id : idSet) {
			int year = map.get(id);
			// 如果结果集里面存在该年份那么对应的数量加一
			if (yearMap.containsKey(year)) {
				int count = yearMap.get(year);
				yearMap.put(year, count + 1);
			} else {
				yearMap.put(year, 1);
			}
		}
		String x = "x=[";
		String y = "y=[";
		for (Integer year : yearMap.keySet()) {
			x += year;
			y += yearMap.get(year);
			x += ",";
			y += ",";
		}
		x += "]";
		y += "]";
		System.out.println(x);
		System.out.println(y);
	}

//	/**
//	 * 图1
//	 * 
//	 * @throws IOException
//	 */
//	public void task1() throws IOException {
//		String cite_file = "‪‪E:/a.txt";
//		String cite_info = "E:/paper/b.csv";
//		Set<Integer> idSet = ReadUtils.readCiteCSV(cite_file);
//		Map<Integer, Integer> map = ReadUtils.readPaperYear(cite_info);
//		// 用來存儲年份和對應數量的map集合
//		Map<Integer, Integer> yearMap = new HashMap<>();
//		for (Integer id : idSet) {
//			Integer year = map.get(id);
//			// 如果结果集里面存在该年份那么对应的数量加一
//			if (yearMap.containsKey(year)) {
//				int count = yearMap.get(year);
//				yearMap.put(year, count + 1);
//			} else {
//				yearMap.put(year, 1);
//			}
//		}
//		String x = "[";
//		String y = "[";
//		for (Integer year : yearMap.keySet()) {
//			x += year;
//			y += yearMap.get(year);
//			x += ",";
//			y += ",";
//		}
//		x += "]";
//		y += "]";
//		System.out.println(x);
//		System.out.println(y);
//	}
}
