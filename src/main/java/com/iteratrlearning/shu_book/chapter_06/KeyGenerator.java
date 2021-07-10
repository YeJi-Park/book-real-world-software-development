package com.iteratrlearning.shu_book.chapter_06;

import java.security.SecureRandom;

import org.bouncycastle.crypto.generators.SCrypt;

import static java.nio.charset.StandardCharsets.UTF_16;


public class KeyGenerator {
	private static final int SCRYPT_COST = 16384;
	private static final int SCRYPT_BLOCK_SIZE = 8;
	private static final int SCRYPT_PARALLELISM = 1;
	private static final int KEY_LENGTH = 20;
	private static final int SALT_LENGTH = 16;
	 
	private static final SecureRandom secRandom = new SecureRandom();
	
	static byte[] hash(final String password, final byte[] salt) {
		final byte[] passwordBytes = password.getBytes(UTF_16);
		
		return SCrypt.generate(passwordBytes, salt, 
				SCRYPT_COST, SCRYPT_BLOCK_SIZE, 
				SCRYPT_PARALLELISM, KEY_LENGTH);
	}
	
	static byte[] newSalt() {
		final byte[] salt = new byte[SALT_LENGTH];
		secRandom.nextBytes(salt);
		return salt;
	}
}