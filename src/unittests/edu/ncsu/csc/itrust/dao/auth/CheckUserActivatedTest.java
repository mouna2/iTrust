package edu.ncsu.csc.itrust.dao.auth;

import junit.framework.TestCase;
import edu.ncsu.csc.itrust.dao.mysql.AuthDAO;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.enums.Role;
import edu.ncsu.csc.itrust.exception.iTrustException;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;

public class CheckUserActivatedTest extends TestCase {
	AuthDAO authDAO = TestDAOFactory.getTestInstance().getAuthDAO();
	private TestDataGenerator gen = new TestDataGenerator();

	@Override
	protected void setUp() throws Exception {
		gen.clearAllTables();
		gen.standardData();
		gen.patientDeactivate();
	}

	public void testActivatedUser() throws Exception {
		try {
			assertFalse(authDAO.getDeactivated(1));
		} catch (iTrustException e) {
			fail("Exception should not be thrown.");
		}
	}

	public void testDeactivatedUser() throws Exception {
		try {
			assertTrue(authDAO.getDeactivated(314159));
		} catch (iTrustException e) {
			fail("Exception should not be thrown.");
		}
	}
}
