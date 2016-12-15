package generation.b.machines;

import generation.essential.security.Environment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LogFile_receive {

	private PreparedStatement stmtselect_receive;

	public LogFile_receive(Connection conn) throws SQLException {
		stmtselect_receive = conn
				.prepareStatement("SELECT executed_moment, executed_user "
						+ "FROM LogTable_receive " + "WHERE PURCHASEORDER_ID = ?; ");
	}

	public ResultSet readLogTable_receive(int po_id) throws SQLException {
		stmtselect_receive.setInt(1, po_id);
		ResultSet res = stmtselect_receive.executeQuery();
		return res;
	}

	public boolean isExecuted_receive(int po_id) {
		try {
			ResultSet res = readLogTable_receive(po_id);
			if (!res.next()) {
				System.err
						.print("[LogFile_receive] ERROR: The purchase order ( "
								+ po_id + " ) has not been received yet!");
				return false;
			} else
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public String executedUser_receive(int po_id) {
		try {
			ResultSet res = readLogTable_receive(po_id);
			if (res.next()) {
				// get the user received the given purchase order
				String executedUser = res.getString("executed_user");
				return executedUser;
			} else {
				System.err
						.print("\n[LogFile_receive] ERROR: The purchase order ( "
								+ po_id + " ) has not been received yet!");
				return "";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
	}

	public String executedTime_receive(int po_id) {
		try {
			ResultSet res = readLogTable_receive(po_id);
			if (res.next()) {
				// get the user received the given purchase order
				String executedUser = res.getString("executed_moment");
				return executedUser;
			} else {
				System.err
						.print("\n[LogFile_receive] ERROR: The purchase order ( "
								+ po_id + " ) has not been received yet!");
				return "";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
	}

	public void saveExecution_receive(int po_id, String usr) {
		try {
			Statement stm = Environment.getDBConnection().createStatement();

			String log = "INSERT INTO "
					+ "LogTable_receive ( PURCHASEORDER_ID ,executed_user) "
					+ "VALUES(" + po_id + ",'" + usr + "');";

			int res = stm.executeUpdate(log);

			if (res == 1) {
				System.out
						.println("[LogFile_receive]: The method 'PurchaseOrder_receive' has been logged successfully  on the purchase order "
								+ po_id + " executed by " + usr);
			} else {
				System.err
						.println("[LogFile_receive] ERROR: The method 'PurchaseOrder_receive' CANNOT be logged successfully  on the purchase order "
								+ po_id + " executed by " + usr);
			}
			stm.close();
		} catch (SQLException e) {
			System.err
					.println("\n [LogFile_receive] ERROR: Cannot access to the database: "
							+ e.getErrorCode() + ": " + e.getMessage());
		}
	}
}
