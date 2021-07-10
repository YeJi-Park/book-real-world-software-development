package com.iteratrlearning.shu_book.chapter_06;

public class User {
	private final String id;
	private final byte[] password;
	private final byte[] salt;
	
	public User(String id, byte[] password, byte[] salt) {
		this.id = id;
		this.password = password;
		this.salt = salt;
	}

	public String getId() {
		return id;
	}

	public byte[] getPassword() {
		return password;
	}
	
	public byte[] getSalt() {
		return salt;
	}
}
