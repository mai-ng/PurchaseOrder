package generation.b.machines;

import generation.essential.security.Environment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LogFile_approve {

	private PreparedStatement stmtselect_approve;

	public LogFile_approve(Connection conn) throws SQLException {
		stmtselect_approve = conn
				.prepareStatement("SELECT executed_moment, executed_user "
						+ "FROM LogTable_approve " + "WHERE PURCHASEORDER_ID = ?; ");
	}

	public ResultSet readLogTable_approve(int po_id) throws SQLException {
		stmtselect_approve.setInt(1, po_id);
		ResultSet res = stmtselect_approve.executeQuery();
		return res;
	}

	public boolean isExecuted_approve(int po_id) {
		try {
			ResultSet res = readLogTable_approve(po_id);
			if (!res.next()) {
				System.err
						.print("[LogFile_approve] ERROR: The purchase order ( "
								+ po_id + " ) has not been approved yet!");
				return false;
			} else
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public String executedUser_approve(int po_id) {
		try {
			ResultSet res = readLogTable_approve(po_id);
			if (res.next()) {
				String executedUser = res.getString("executed_user");
				return executedUser;
			} else {
				System.err
						.print("\n[LogFile_approve] ERROR: The purchase order ( "
								+ po_id + " ) has not been approved yet!");
				return "";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
	}

	public String executedTime_approve(int po_id) {
		try {
			ResultSet res = readLogTable_approve(po_id);
			if (res.next()) {
				String executedUser = res.getString("executed_moment");
				return executedUser;
			} else {
				System.err
						.print("\n[LogFile_approve] ERROR: The purchase order ( "
								+ po_id + " ) has not been approved yet!");
				return "";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
	}

	public void saveExecution_approve(int po_id, String usr) {
		try {
			Statement stm = Environment.getDBConnection().createStatement();

			String log = "INSERT INTO "
					+ "LogTable_approve ( PURCHASEORDER_ID ,executed_user) "
					+ "VALUES(" + po_id + ",'" + usr + "');";

			int res = stm.executeUpdate(log);

			if (res == 1) {
				System.out
						.println("[LogFile_approve]: The method 'PurchaseOrder_approve' has been logged successfully  on the purchase order "
								+ po_id + " executed by " + usr);
			} else {
				System.err.println("[LogFile_approve] ERROR: The method 'PurchaseOrder_approve' CANNOT be logged successfully  on the purchase order "
								+ po_id + " executed by " + usr);
			}
			stm.close();
		} catch (SQLException e) {
			System.err
					.println("\n [LogFile_approve] ERROR: Cannot access to the database: "
							+ e.getErrorCode() + ": " + e.getMessage());
		}
	}
}
