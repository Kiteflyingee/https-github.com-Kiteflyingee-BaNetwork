package taskplot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import model.BANetwork;

public class Main3 {

	public static void main(String[] args) throws IOException {
		FileWriter fw = new FileWriter(new File("D:\\result.txt"));
		for (float i = 0; i <= 4; i += 0.5) {
			BANetwork network = new BANetwork(6, 3, i);
			network.expandNetwork(10000);
			List<Double> averageDegrees = network.computeAvgdegreeByTime();
			String x = "x=[";
			String y = "y=[";
			for (int t = 0; t <= network.getFinishTime(); t++) {
				x += t + " ";
				y += averageDegrees.get(t) + " ";
			}
			x += "];";
			y += "];";
			fw.write(x+"\n");
			fw.write(y+"\n");
			fw.write("loglog(x,y);\n");
			fw.write("hold on;\n");
			System.out.println(x);
			System.out.println(y);
			System.out.println("loglog(x,y);");
			System.out.println("hold on;");
		}
		fw.close();
	}
}
