/**
 * 
 */
package edu.ncsu.csc.itrust.action;

import java.util.List;
import edu.ncsu.csc.itrust.beans.ReferralBean;
import edu.ncsu.csc.itrust.beans.VerboseReferralBean;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.dao.mysql.PatientDAO;
import edu.ncsu.csc.itrust.dao.mysql.PersonnelDAO;
import edu.ncsu.csc.itrust.dao.mysql.ReferralDAO;
import edu.ncsu.csc.itrust.enums.SortDirection;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.iTrustException;

/**
 * Class for viewing the referrals sent by a particular HCP.
 * @author student
 */
public class ViewSentReferralsAction {
	
	private ReferralDAO referralDAO;
	private PatientDAO patientDAO;
	private PersonnelDAO personnelDAO;
	private long hcpid;
	
	public ViewSentReferralsAction(DAOFactory factory, long hcpid) 
		throws iTrustException {
		this.hcpid = hcpid;
		referralDAO = factory.getReferralDAO();
	    patientDAO = factory.getPatientDAO();
	    personnelDAO = factory.getPersonnelDAO();
	}
	
	/**
	 * Get all sent referrals sorted by the time they were created.
	 * @return
	 * @throws DBException
	 */
	/*public List<VerboseReferralBean> getReferrals() throws DBException {
		return referralDAO.getSenderQuery(hcpid).query("timestamp", SortDirection.DESCENDING);
	}*/
	
	/**
	 * Get all sent referrals sorted by the given field and in the given 
	 * direction.
	 * @param field The name of the pseudo-field to sort by.
	 * @param dir The direction of the sort.
	 * @return
	 * @throws DBException
	 */
	public List<VerboseReferralBean> getReferrals(String field, SortDirection dir) throws DBException {
		return referralDAO.getSenderQuery(hcpid).query(field, dir);
	}
	
	/**
	 * Get a specific referral.
	 * @param id
	 * @return
	 * @throws DBException
	 */
	public ReferralBean getReferral(long id) throws DBException {
		return referralDAO.getReferral(id);
	}
	
	/**
	 * Edit an existing referral.
	 * @param bean
	 * @throws iTrustException
	 */
	/*public void editReferral(ReferralBean bean) throws iTrustException {
		referralDAO.editReferral(bean);
	}*/
	
	/**
	 * Delete an existing referral.
	 * @param bean
	 * @throws iTrustException
	 */
	/*public void deleteReferral(ReferralBean bean) throws iTrustException {
		referralDAO.removeReferral(bean.getId());
	}*/

	/**
	 * Get the patient name associated with the given referral.
	 * @param bean
	 * @return The patient's name as a String.
	 * @throws iTrustException
	 */
	public String getPatientName(ReferralBean bean) throws iTrustException {
		return patientDAO.getName(bean.getPatientID());
	}
	
	/**
	 * Get the name of the receiving HCP associated with the given referral.
	 * @param bean
	 * @return The HCP's name as a String.
	 * @throws iTrustException
	 */
	public String getReceivingHCPName(ReferralBean bean) throws iTrustException {
		return personnelDAO.getName(bean.getReceiverID());
	}
	
	
}
