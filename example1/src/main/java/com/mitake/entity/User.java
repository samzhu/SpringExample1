package com.mitake.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user", catalog = "testdb")
public class User implements java.io.Serializable {

	// Fields

	private Long uid;
	private String acct;
	private Timestamp timecreate;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String acct, Timestamp timecreate) {
		this.acct = acct;
		this.timecreate = timecreate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "uid", unique = true, nullable = false)
	public Long getUid() {
		return this.uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	@Column(name = "acct", nullable = false, length = 20)
	public String getAcct() {
		return this.acct;
	}

	public void setAcct(String acct) {
		this.acct = acct;
	}

	@Column(name = "timecreate", nullable = false, length = 19)
	public Timestamp getTimecreate() {
		return this.timecreate;
	}

	public void setTimecreate(Timestamp timecreate) {
		this.timecreate = timecreate;
	}

}