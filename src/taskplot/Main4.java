package taskplot;

import java.io.IOException;

import model.BANetwork;

public class Main4 {

	public static void main(String[] args) throws IOException {
		String x = "x=[0 0.5 1 1.5 2 2.5 3 3.5 4]";
		String y = "y1=[";
		String y4 = "y2=[";
		String y6 = "y3=[";
		for (float i = 0; i <= 4; i += 0.5) {
			
			// m=2的情况
			BANetwork network = new BANetwork(6, 2, i);
			network.expandNetwork(10000);
			int degree = network.computeMaxDegree();
			y += degree;
			y += " ";

			// m=4的情况
			BANetwork network4 = new BANetwork(6, 4, i);
			network4.expandNetwork(10000);
			int degree4 = network4.computeMaxDegree();
			y4 += degree4;
			y4 += " ";

			// m=6的情况
			BANetwork network6 = new BANetwork(6, 6, i);
			network6.expandNetwork(10000);
			int degree6 = network6.computeMaxDegree();
			y6 += degree6;
			y6 += " ";
		}
		y += "];";
		y4 += "];";
		y6 += "];";
		System.out.println(x);
		System.out.println(y);
		System.out.println("plot(x,y1);");
		System.out.println("hold on;");
		
		System.out.println(x);
		System.out.println(y4);
		System.out.println("plot(x,y2);");
		System.out.println("hold on;");

		System.out.println(x);
		System.out.println(y6);
		System.out.println("plot(x,y3);");
		System.out.println("hold on;");

	}

}
