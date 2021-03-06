package edu.ncsu.csc.itrust.action;

import junit.framework.TestCase;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.exception.iTrustException;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;

public class GetUserNameActionTest extends TestCase {
	private DAOFactory factory = TestDAOFactory.getTestInstance();
	private TestDataGenerator gen = new TestDataGenerator();

	@Override
	protected void setUp() throws Exception {
		gen.clearAllTables();
	}
	
	public void testCorrectFormat() throws Exception {
		gen.hcp0();
		assertEquals("Kelly Doctor", new GetUserNameAction(factory).getUserName("9000000000"));
	}

	public void testWrongFormat() throws Exception {
		gen.hcp0();
		try {
			new GetUserNameAction(factory).getUserName("90000000aaa01");
			fail("Exception should have been thrown");
		} catch (iTrustException e) {
			assertEquals("MID not in correct form", e.getMessage());
		}
	}
}
