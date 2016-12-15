package generation.b.machines;

import java.sql.Connection;
import java.sql.SQLException;

public class ActionsHistory {	
	private LogFile_create t_create;
	private LogFile_approve t_approve;
	private LogFile_receive t_receive;

	public ActionsHistory(Connection conn) throws SQLException {
		t_create = new LogFile_create(conn);
		t_approve = new LogFile_approve(conn);
		t_receive = new LogFile_receive(conn);
	}
	
	public void LogPurchaseOrder_create(int po_id, String usr) {
		t_create.saveExecution_create(po_id, usr);
	}
	
	public void LogPurchaseOrder_approve(int po_id, String usr) {
			t_approve.saveExecution_approve(po_id, usr);
	}
	
	public void LogPurchaseOrder_receive(int po_id, String usr) {
			t_receive.saveExecution_receive(po_id, usr);
	}	
}
