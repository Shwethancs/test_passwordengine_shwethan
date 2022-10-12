package com.example.passwordengine;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PasswordVerificationControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	HttpHeaders headers = new HttpHeaders();

	private static final String URI_PASSWORDVERIFICATION = "/password/verify";

	@Test
	public void testEmptyPassword() throws Exception {
		headers.add("Content-Type", "application/json");
		PasswordResource passwordResource = new PasswordResource();
		passwordResource.setPassword("");
		ResponseEntity<String> responseEntity = testRestTemplate.exchange(URI_PASSWORDVERIFICATION, HttpMethod.POST,
				new HttpEntity<>(passwordResource, headers), String.class);
		Assert.assertEquals(400, responseEntity.getStatusCodeValue());
		Assert.assertEquals("Password is null or empty.", responseEntity.getBody());

	}

	@Test
	public void testNullPassword() throws Exception {
		headers.add("Content-Type", "application/json");
		PasswordResource passwordResource = new PasswordResource();
		passwordResource.setPassword(null);
		ResponseEntity<String> responseEntity = testRestTemplate.exchange(URI_PASSWORDVERIFICATION, HttpMethod.POST,
				new HttpEntity<>(passwordResource, headers), String.class);
		Assert.assertEquals(400, responseEntity.getStatusCodeValue());
		Assert.assertEquals("Password is null or empty.", responseEntity.getBody());

	}

	@Test
	public void testInvalidPasswordOneLowercaseLetter() throws Exception {
		headers.add("Content-Type", "application/json");
		PasswordResource passwordResource = new PasswordResource();
		passwordResource.setPassword("OLDPASSWORD");
		ResponseEntity<String> responseEntity = testRestTemplate.exchange(URI_PASSWORDVERIFICATION, HttpMethod.POST,
				new HttpEntity<>(passwordResource, headers), String.class);
		Assert.assertEquals(400, responseEntity.getStatusCodeValue());
		Assert.assertEquals("Password should have atleast one lowercase character.", responseEntity.getBody());

	}

	@Test
	public void testLengthLowercaseCombination() throws Exception {
		headers.add("Content-Type", "application/json");
		PasswordResource passwordResource = new PasswordResource();
		passwordResource.setPassword("oldpassword");
		ResponseEntity<String> responseEntity = testRestTemplate.exchange(URI_PASSWORDVERIFICATION, HttpMethod.POST,
				new HttpEntity<>(passwordResource, headers), String.class);
		Assert.assertEquals(400, responseEntity.getStatusCodeValue());
		Assert.assertEquals(
				"Password did not meet the requirements combination of length, uppercase, lowercase, digit in it.",
				responseEntity.getBody());

	}

	@Test
	public void testLowerUppercaseCombination() throws Exception {
		headers.add("Content-Type", "application/json");
		PasswordResource passwordResource = new PasswordResource();
		passwordResource.setPassword("pWORD");
		ResponseEntity<String> responseEntity = testRestTemplate.exchange(URI_PASSWORDVERIFICATION, HttpMethod.POST,
				new HttpEntity<>(passwordResource, headers), String.class);
		Assert.assertEquals(400, responseEntity.getStatusCodeValue());
		Assert.assertEquals(
				"Password did not meet the requirements combination of length, uppercase, lowercase, digit in it.",
				responseEntity.getBody());

	}

	@Test
	public void testLowerUpperLengthcaseCombination() throws Exception {
		headers.add("Content-Type", "application/json");
		PasswordResource passwordResource = new PasswordResource();
		passwordResource.setPassword("passsWORD");
		ResponseEntity<String> responseEntity = testRestTemplate.exchange(URI_PASSWORDVERIFICATION, HttpMethod.POST,
				new HttpEntity<>(passwordResource, headers), String.class);
		Assert.assertEquals(200, responseEntity.getStatusCodeValue());
		Assert.assertEquals("Valid Password", responseEntity.getBody());

	}

	@Test
	public void testDigitUpperLowerCombination() throws Exception {
		headers.add("Content-Type", "application/json");
		PasswordResource passwordResource = new PasswordResource();
		passwordResource.setPassword("12PpD");
		ResponseEntity<String> responseEntity = testRestTemplate.exchange(URI_PASSWORDVERIFICATION, HttpMethod.POST,
				new HttpEntity<>(passwordResource, headers), String.class);
		Assert.assertEquals(200, responseEntity.getStatusCodeValue());
		Assert.assertEquals("Valid Password", responseEntity.getBody());

	}

	@Test
	public void testDigitLengthLowerCombination() throws Exception {
		headers.add("Content-Type", "application/json");
		PasswordResource passwordResource = new PasswordResource();
		passwordResource.setPassword("12apdabpdc");
		ResponseEntity<String> responseEntity = testRestTemplate.exchange(URI_PASSWORDVERIFICATION, HttpMethod.POST,
				new HttpEntity<>(passwordResource, headers), String.class);
		Assert.assertEquals(200, responseEntity.getStatusCodeValue());
		Assert.assertEquals("Valid Password", responseEntity.getBody());

	}

}
