package mypack;

import java.io.Serializable;

import javax.persistence.*;
@Entity
@Table(name="account")
public class Account 
{
	private int id;
	private double balance;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="accid")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="balance")
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
}
