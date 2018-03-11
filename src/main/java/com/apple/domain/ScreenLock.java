package com.apple.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * ScreenLock
 */
@Entity
public class ScreenLock {

    @Id
    @GeneratedValue
    private Integer id;
    private String phone;
    private String slock;

    public ScreenLock(){

    }
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the slock
	 */
	public String getSlock() {
		return slock;
	}
	/**
	 * @param slock the slock to set
	 */
	public void setSlock(String slock) {
		this.slock = slock;
	}

}