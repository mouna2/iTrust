package edu.ncsu.csc.itrust.dao.hospital;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;
import edu.ncsu.csc.itrust.DBBuilder;
import edu.ncsu.csc.itrust.beans.HospitalBean;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.dao.mysql.HospitalsDAO;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.iTrustException;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;

/**
 * Test Hostipal DAO
 */
public class AddEditHospitalDAOTest extends TestCase {
	private DAOFactory factory = TestDAOFactory.getTestInstance();
	private HospitalsDAO hospitalDAO = factory.getHospitalsDAO();

	@Override
	protected void setUp() throws Exception {
		TestDataGenerator gen = new TestDataGenerator();
		//clearHospitals();
		gen.clearAllTables();
		gen.hospitals();
	}

	private void clearHospitals() throws SQLException {
		new DBBuilder(factory).executeSQL(Arrays.asList("DELETE FROM hospitals;"));
	}

	/**
	 * Test All hospital
	 * test Get HospitalFromEmptyTable
	 * test GetHospital
	 * test AddDupe
	 * testUpdateName
	 * @throws Exception
	 */
	public void testGetAllHospitals() throws Exception {
		List<HospitalBean> hospitals = hospitalDAO.getAllHospitals();
		assertEquals(9, hospitals.size());
		// All hospitals in alphabetical order.
		assertEquals("Facebook Rehab Center", hospitals.get(0).getHospitalName());
		assertEquals("Health Institute Dr. E", hospitals.get(1).getHospitalName());
		assertEquals("Health Institute Mr. Barry", hospitals.get(2).getHospitalName());
		assertEquals("Health Institute Mr. Donghoon", hospitals.get(3).getHospitalName());
		assertEquals("Le Awesome Hospital", hospitals.get(4).getHospitalName());
		assertEquals("Mental Hospital 4 iTrust Devs", hospitals.get(5).getHospitalName());
		assertEquals("Ninja Hospital", hospitals.get(6).getHospitalName());
	}

	public void testGetHospital() throws DBException {
		HospitalBean hosp = hospitalDAO.getHospital("9191919191");
		assertEquals("9191919191", hosp.getHospitalID());
		assertEquals("Test Hospital 9191919191", hosp.getHospitalName());

	}

	public void testGetAllFromEmptyTable() throws SQLException, DBException {
		clearHospitals();
		assertEquals(0, hospitalDAO.getAllHospitals().size());
	}

	public void testGetHospitalFromEmptyTable() throws SQLException, DBException {
		clearHospitals();
		assertEquals(null, hospitalDAO.getHospital("9191919191"));
	}

	public void testAddHospital() throws DBException, iTrustException {
		final String id = "9191919192";
		final String name = "testAddHospital Hospital ";
		genericAdd(id, name);
		List<HospitalBean> allCodes = hospitalDAO.getAllHospitals();
		assertEquals(id, allCodes.get(allCodes.size() - 1).getHospitalID());
		assertEquals(name, allCodes.get(allCodes.size() - 1).getHospitalName());
	}

	public void testAddDupe() throws SQLException, DBException, iTrustException {
		final String id = "0000000000";
		final String name0 = "testAddDupe Hospital";
		HospitalBean hosp = genericAdd(id, name0);
		try {
			hosp.setHospitalName("");
			hospitalDAO.addHospital(hosp);
			fail("CPTCodeTest.testAddDupe failed to catch dupe");
		} catch (iTrustException e) {
			assertEquals("Error: Hospital already exists.", e.getMessage());
			hosp = hospitalDAO.getHospital(id);
			assertEquals(name0, hosp.getHospitalName());
		}
	}

	private HospitalBean genericAdd(String id, String name) throws DBException, iTrustException {
		HospitalBean hosp = new HospitalBean(id, name);
		assertTrue(hospitalDAO.addHospital(hosp));
		assertEquals(name, hospitalDAO.getHospital(id).getHospitalName());
		return hosp;
	}

	public void testUpdateName() throws DBException, iTrustException {
		final String id = "7777777777";
		final String name = "testUpdateName NEW Hospital";
		HospitalBean hosp = genericAdd(id, "");
		hosp.setHospitalName(name);
		assertEquals(1, hospitalDAO.updateHospital(hosp));
		hosp = hospitalDAO.getHospital(id);
		assertEquals(name, hosp.getHospitalName());
	}

	public void testUpdateNonExistent() throws SQLException, DBException {
		clearHospitals();
		final String id = "0000000000";
		HospitalBean hosp = new HospitalBean(id, "");
		assertEquals(0, hospitalDAO.updateHospital(hosp));
		assertEquals(0, hospitalDAO.getAllHospitals().size());
	}
}