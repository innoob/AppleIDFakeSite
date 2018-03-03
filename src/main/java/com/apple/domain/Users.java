package com.apple.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Users
 */

@Entity
public class Users {

	@Id
	@GeneratedValue
	private Integer id;
	private String uuid;
    private String username;
	private String password;
	private String phone;

    public Users(){

    }

    public Users(String username,String password){
        this.username = username;
        this.password = password;
    }

    public Users(String username,String password,String phone,String uuid){
        this.username = username;
        this.password = password;
		this.phone = phone;
		this.uuid = uuid;
    }

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public boolean isReal(){
		return null!=username&&!"".equals(username)&&
				null!=password&&!"".equals(password);
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
    
    
}