package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
		int j = 0;
		String str = "legend(";
		for (float i = 0; i <= 4; i += 0.5) {
			BANetwork network = new BANetwork(6, 3, i);
			network.expandNetwork(10000);
			List<Map<Integer, Double>> result = network.statistics();
			System.out.print("x" + j + "=[");
			for (int key : result.get(0).keySet()) {
				System.out.print(key + ",");
			}
			System.out.println("]");
			System.out.print("y" + j + "=[");
			for (double value : result.get(0).values()) {
				System.out.print(value + ",");
			}
			System.out.println("]");
			System.out.println("loglog(x" + j + ",y" + j + ");");
			System.out.println("hold on;");
			j++;
			str += "'" + i + "',";
			// System.out.print("x=[");
			// for (int key : result.get(1).keySet()) {
			// System.out.print(key + ",");
			// }
			// System.out.println("]");
			// System.out.print("y=[");
			// for (double value : result.get(1).values()) {
			// System.out.print(value + ",");
			// }
			// System.out.println("]");
			// System.out.println("title('N=" + i + ",indegree');");
			// System.out.println("loglog(x,y);");
			// System.out.println("hold on;");
		}
		str = str.substring(0, str.length()-1);
		str += ")";
		System.out.println(str);

		// 线性增长BA网络
		// System.out.println("`````````````线性增长···········");
		// BANetwork network = new BANetwork(6, 3, 1);
		// network.expandNetwork(10000);
		// // 获得每个时刻的节点数
		// Map<Integer, Integer> map = network.getTimeNum();
		// String x = "x=[";
		// String y = "y=[";
		// for (int time : map.keySet()) {
		// x += time;
		// y += map.get(time);
		// x += ",";
		// y += ",";
		// }
		// x+="]";
		// y+="]";
		// System.out.println(x+"\n"+y);
		// System.out.println("···············指数增长···········");
		// BANetwork network = new BANetwork(6, 3, 1);
		// network.expandNetwork(10000);
		// // 获得每个时刻的节点数
		// Map<Integer, Integer> map = network.getTimeNum();
		// String x = "x=[";
		// String y = "y=[";
		// for (int time : map.keySet()) {
		// x += time;
		// y += map.get(time);
		// x += ",";
		// y += ",";
		// }
		// x+="]";
		// y+="]";
		// System.out.println(x+"\n"+y);

		// System.out.println("···············幂率增长···········");
		// BANetwork network = new BANetwork(6, 3, 2);
		// network.expandNetwork(10000);
		// // 获得每个时刻的节点数
		// Map<Integer, Integer> map = network.getTimeNum();
		// String x = "x=[";
		// String y = "y=[";
		// for (int time : map.keySet()) {
		// x += time;
		// y += map.get(time);
		// x += ",";
		// y += ",";
		// }
		// x+="]";
		// y+="]";
		// System.out.println(x+"\n"+y);
	}

}
