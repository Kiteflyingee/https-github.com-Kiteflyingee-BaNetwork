package model;

import java.util.HashSet;
import java.util.Set;

/**
 * @author devkite
 *
 */
public class BANode {
	
	private int id;
//	用来存储该节点所有连接的点，计算度
	private Set<Integer> netNodes = null;
//	用来保存接入点的id，用来统计入度
	private Set<Integer> accessNodes = null;
//	存储加入网络时间
	private Integer addTime;
	
	public BANode(int id, int addTime) {
	super();
	this.id = id;
	this.addTime = addTime;
}
	public Integer getAddTime() {
		return addTime;
	}
	public void setAddTime(Integer addTime) {
		this.addTime = addTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void addNetNodes(Set<Integer> ids) {
		if(this.netNodes == null) {
			this.netNodes = new HashSet<Integer>();
		}
		this.netNodes.addAll(ids);
	}
	public void addNetNode(Integer id) {
		if(this.netNodes == null) {
			this.netNodes = new HashSet<Integer>();
		}
		this.netNodes.add(id);
	}
	
	public void addAccessNode(int id) {
		if(this.accessNodes == null) {
			this.accessNodes = new HashSet<Integer>();
		}
		this.accessNodes.add(id);
	}
	/**
	 * 获得度的工具类
	 * @return
	 */
	public Integer getDegree() {
		return netNodes.size();
	}
	
	public Integer getInDegree() {
		if(this.accessNodes == null) {
			this.accessNodes = new HashSet<Integer>();
		}
		return accessNodes.size();
	}
}
