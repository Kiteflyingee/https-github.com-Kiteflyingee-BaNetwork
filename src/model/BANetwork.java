package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

public class BANetwork {

	private int initSize;
	// 保存新加入节点的出度
	private int outdegree;
	// N ,每次加入新节点(Time,N)个新节点
	private float N = 0;
	// 保存这个网络所有节点
	private List<BANode> baNodes;
	// 用来保存网络当前网络扩增过程中的时间
	private int currentTime = 0;
	// 保存网络节点数量
	private int finishTime;

	public Map<Integer, Integer> getTimeNum() {
		return timeNum;
	}

	// 保存网络每个时刻的节点总数
	private Map<Integer, Integer> timeNum;

	public BANetwork(int initSize, int outdegree, float i2) {
		this.initSize = initSize;
		this.N = i2;
		this.outdegree = outdegree;
		this.baNodes = new ArrayList<>();
		this.timeNum = new HashMap<Integer, Integer>();
		// 初始化网络
		for (int i = 0; i < initSize; i++) {
			BANode node = new BANode(i, 0);
			for (int j = 0; j < initSize; j++) {
				if (j != i) {
					node.addAccessNode(j);
					node.addNetNode(j);
				}
			}
			baNodes.add(i, node);
		}
	}

	/**
	 * 根据N和时间快速扩增网络
	 * 
	 * @param size
	 *            网络的大小
	 */
	public void expandNetwork(int size) {
		int nodeNum = getNodeNum();
		// 当前时刻需要加入网络的节点的数量
		int needNum = 0;
		// 当网络节点数小于设定的总数的时候，增长网络
		while (nodeNum < size) {
			// 进入下一个时刻
			timeNum.put(currentTime, nodeNum);
			currentTime++;
			// 指数增长方案
			// needNum = (int) Math.exp(currentTime);
			// 幂率增长方案
			needNum = (int) Math.pow(currentTime, N);
			int restNum = size - nodeNum;
			if (needNum > restNum) {
				needNum = restNum;
			}
			List<Set<Integer>> list = generateNode(needNum);
			Set<BANode> set = new HashSet<>();
			for (int i = 0; i < needNum; i++) {
				BANode node = new BANode(nodeNum + i, currentTime);
				node.addNetNodes(list.get(i));
				set.add(node);
			}
			// 更新到整个网络
			baNodes.addAll(set);
			// 更新临时变量节点数量，其实可以通过baNodes.size()获得
			nodeNum += needNum;
			timeNum.put(currentTime, nodeNum);
		}
		finishTime = currentTime;
	}

	// 在已有网络中选择节点与新加入节点进行连接，遵循度优先原则
	/**
	 * 单纯的挑选节点并不更新网络
	 * 
	 * @param needNum
	 * @return
	 */
	private List<Set<Integer>> generateNode(int needNum) {
		// 保存网络中已有节点的度
		List<Integer> degrees = new ArrayList<>();
		int sumDegree = 0;
		for (int i = 0; i < getNodeNum(); i++) {
			int degree = baNodes.get(i).getDegree();
			degrees.add(degree);
			sumDegree += degree;
		}
		Random random = new Random();
		// List存储neednum个新节点所选择的节点的信息
		List<Set<Integer>> list = new ArrayList<>();
		for (int i = 0; i < needNum; i++) {
			// banNodes保存为特定节点选择的outdegree个连接的节点
			Set<Integer> nodeSet = new HashSet<>();
			while (nodeSet.size() < outdegree) {
				int num = random.nextInt(sumDegree) + 1;
				int tempSum = 0;
				for (int id = 0; id < degrees.size(); id++) {
					tempSum += degrees.get(id);
					// 如果选到点了，需要对于选到的点的入度
					if (tempSum > num) {
						nodeSet.add(id);
						baNodes.get(id).addNetNode(i + baNodes.size());
						;
						baNodes.get(id).addAccessNode(i + baNodes.size());
						;
						break;
					}
				}
			}
			list.add(nodeSet);
		}
		return list;
	}

	// 获得度分布和累积度分布
	public List<Map<Integer, Double>> statistics() {
		List<Map<Integer, Double>> results = new ArrayList<>();
		Map<Integer, Integer> degrees = new TreeMap<>();
		Map<Integer, Double> degreeDistribution = new TreeMap<>();
		Map<Integer, Double> indegreeDistribution = new TreeMap<>();
		Map<Integer, Integer> indegrees = new TreeMap<>();

		// int sumDegree=0,sumIndegree=0;
		for (int i = 0; i < baNodes.size(); i++) {
			int degree = baNodes.get(i).getDegree();
			int indegree = baNodes.get(i).getInDegree();
			// sumDegree += degree;
			// sumIndegree += indegree;
			if (degrees.containsKey(degree))
				degrees.put(degree, degrees.get(degree) + 1);
			else
				degrees.put(degree, 1);
			if (indegree != 0)
				if (indegrees.containsKey(indegree))
					indegrees.put(indegree, indegrees.get(indegree) + 1);
				else
					indegrees.put(indegree, 1);
		}
		int sumnode = 0;
		int total = baNodes.size();
		for (int idx : degrees.keySet()) {

			double percent = (double) (total - sumnode) / total;
			int nodeCount = degrees.get(idx);
			sumnode += nodeCount;
			degreeDistribution.put(idx, percent);
		}
		sumnode = 0;
		for (int idx : indegrees.keySet()) {
			double percent = (double) (total - sumnode) / total;
			int nodeCount = indegrees.get(idx);
			sumnode += nodeCount;
			indegreeDistribution.put(idx, percent);
		}
		results.add(degreeDistribution);
		results.add(indegreeDistribution);
		return results;
	}

	/**
	 * 根据时间计算每个时间间隔加入网络节点的平均度 信息
	 * 
	 * @return
	 */
	public List<Double> computeAvgdegreeByTime() {
		int tempIdx = 0; // 用来记录暂时遍历到网络的第几个节点
		BANode node = null;
		List<Double> averageDegrees = new ArrayList<>();
		node = baNodes.get(tempIdx);
		for (int t = 0; t <= finishTime; t++) {
			int nodeNum = 0;
			double degreeSum = 0.0;
			// 因为0时刻有initsize个初始节点，所以这里用了do while循环
			// 获得tempIdx位置的节点，判断该节点是否是该时间加入网络的
			while (node.getAddTime() == t) {
				nodeNum++;
				degreeSum += node.getDegree();
				tempIdx++;
				if (10000 > tempIdx)
					node = baNodes.get(tempIdx);
				else
					break;
			}
			double averageDegree = degreeSum / nodeNum;
			averageDegrees.add(averageDegree);
		}
		return averageDegrees;
	}

	/**
	 * 求网络最大度
	 */
	public int computeMaxDegree() {
		// 直接遍历整个网络，求出节点的最大度
		int maxDegree = 0;
		for (BANode node : baNodes) {
			int degree = node.getDegree();
			if (maxDegree < degree) {
				maxDegree = degree;
			}
		}
		return maxDegree;
	}

	public List<BANode> getBaNodes() {
		return baNodes;
	}

	public int getNodeNum() {
		return baNodes.size();
	}

	public int getInitSize() {
		return initSize;
	}

	public int getFinishTime() {
		return finishTime;
	}
}
