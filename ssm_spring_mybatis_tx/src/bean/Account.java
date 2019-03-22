package com.lpt.bean;

public class Account {

	private Integer id;
	private String name;
	private double money;
	
	//转账金额
	private double tranferMoney;
	
	public double getTranferMoney() {
		return tranferMoney;
	}
	public void setTranferMoney(double tranferMoney) {
		this.tranferMoney = tranferMoney;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", money=" + money + "]";
	}
	
	
}
