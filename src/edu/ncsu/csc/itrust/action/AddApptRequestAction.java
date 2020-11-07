package edu.ncsu.csc.itrust.action;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import edu.ncsu.csc.itrust.beans.ApptBean;
import edu.ncsu.csc.itrust.beans.ApptRequestBean;
import edu.ncsu.csc.itrust.beans.ApptTypeBean;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.dao.mysql.ApptDAO;
import edu.ncsu.csc.itrust.dao.mysql.ApptRequestDAO;
import edu.ncsu.csc.itrust.dao.mysql.ApptTypeDAO;

/**
 * 
 *
 */
public class AddApptRequestAction {

	private ApptDAO aDAO;
	private ApptRequestDAO arDAO;
	private ApptTypeDAO atDAO;
	
	public AddApptRequestAction(DAOFactory factory) {
		aDAO = factory.getApptDAO();
		arDAO = factory.getApptRequestDAO();
		atDAO = factory.getApptTypeDAO();
	}
	
	public String addApptRequest(ApptRequestBean bean) throws SQLException {

		List<ApptBean> conflicts = aDAO.getAllHCPConflictsForAppt(bean.getRequestedAppt().getHcp(),
				bean.getRequestedAppt());

		if (conflicts != null && !conflicts.isEmpty()) {
			return "The appointment you requested conflicts with other existing appointments.";
		}

		arDAO.addApptRequest(bean);

		return "Your appointment request has been saved and is pending.";
	}

	public List<ApptBean> getNextAvailableAppts(int num, ApptBean bean) throws SQLException {
		List<ApptBean> appts = new ArrayList<ApptBean>(num);
		for (int i = 0; i < num; i++) {
			ApptBean b = new ApptBean();
			b.setApptType(bean.getApptType());
			b.setHcp(bean.getHcp());
			b.setPatient(bean.getPatient());
			b.setDate(new Timestamp(bean.getDate().getTime()));
			
			List<ApptBean> conflicts = null;
			do {
				conflicts = aDAO.getAllHCPConflictsForAppt(b.getHcp(), b);
				if (conflicts != null && !conflicts.isEmpty()) {
					ApptBean lastConflict = conflicts.get(conflicts.size() - 1);
					Timestamp afterConflict = endTime(lastConflict);
					b.setDate(afterConflict);
				}
			} while (conflicts != null && !conflicts.isEmpty());
			appts.add(b);
			Timestamp nextTime = endTime(b);
			bean.setDate(nextTime);
		}
		return appts;
	}

	private Timestamp endTime(ApptBean bean) throws SQLException {
		Timestamp d = new Timestamp(bean.getDate().getTime());
		ApptTypeBean type = atDAO.getApptType(bean.getApptType());
		d.setTime(d.getTime() + type.getDuration() * 60 * 1000);
		return d;
	}

}
