package generation.b.machines;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ADTranslation {
	private LogFile_create t_create;
	private LogFile_approve t_approve;
	private LogFile_receive t_receive;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	public ADTranslation(Connection conn) throws SQLException {
		t_create = new LogFile_create(conn);
		t_approve = new LogFile_approve(conn);
		t_receive = new LogFile_receive(conn);
	}

	public boolean ADPurchaseOrder_create(int po_id, String usr) {
		return true;
	}

	public boolean ADPurchaseOrder_approve(int po_id, String usr) {
		return true;
	}

	public boolean ADPurchaseOrder_receive(int po_id, String usr) {
		boolean isApproved = t_approve.isExecuted_approve(po_id);
		boolean isCreated = t_create.isExecuted_create(po_id);
		String createdUser = t_create.executedUser_create(po_id);
		String createdTime = t_create.executedTime_create(po_id);
		String approvedTime = t_approve.executedTime_approve(po_id);
		try {
			if (isApproved == true && isCreated == true
					&& !createdUser.equals(usr)
					&& sdf.parse(createdTime).before(sdf.parse(approvedTime)))
				return true;
			else{
				System.out.println("[DYNAMIC_SECURITY] FAILED!");
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}
}
