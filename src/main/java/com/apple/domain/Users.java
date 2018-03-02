package com.apple.domain;

/**
 * Users
 */
public class Users {

    private String username;
    private String password;
    private String phone;

    public Users(){

    }

    public Users(String username,String password){
        this.username = username;
        this.password = password;
    }

    public Users(String username,String password,String phone){
        this.username = username;
        this.password = password;
        this.phone = phone;
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

    
    
}