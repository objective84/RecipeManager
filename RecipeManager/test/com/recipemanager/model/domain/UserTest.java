/**
 * 
 */
package com.recipemanager.model.domain;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.recipemanager.model.domain.User;


/**
 * @author Peter's Desktop
 *
 */
public class UserTest {

	private User testUser;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testUser = new User(1, "Test", "User", "TestUsername", "TestPassword");
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEqual() {
		User user = new User(1, "Test", "User", "TestUsername", "TestPassword");
		Assert.assertTrue(testUser.equals(user));
	}
	
	@Test
	public void testNotEqual() {
		User user = new User(1, "User", "Test", "TestUsername", "TestPassword");
		Assert.assertFalse(testUser.equals(user));
	}
	
	@Test
	public void testValidatePass(){
		Assert.assertTrue(testUser.validate());
	}

	@Test
	public void testValidateFail(){
		testUser.setUsername(null);
		Assert.assertFalse(testUser.validate());
	}
	

}
