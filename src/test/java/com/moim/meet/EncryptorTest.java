package com.moim.meet;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * EncryptorTest.java
 * 
 * @author cdssw
 * @since 2020. 6. 6.
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * 2020. 6. 6.    cdssw            최초 생성
 * </pre>
 */
@Slf4j
public class EncryptorTest {

	@Test
	public void encryptor() {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setAlgorithm("PBEWithMD5AndDES");
		encryptor.setPassword("PasswordSaltSecret");
		
		String url = "jdbc:mariadb://172.17.0.1:3307/hbuser";
		String username = "{id}";
		String password = "{password}";
		
		log.info(encryptor.encrypt(url));
		log.info(encryptor.encrypt(username));
		log.info(encryptor.encrypt(password));
	}
}
