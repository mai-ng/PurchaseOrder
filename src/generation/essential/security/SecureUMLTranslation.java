package generation.essential.security;

import java.sql.Connection;
import java.sql.SQLException;

public class SecureUMLTranslation {
	
	//private static PreparedStatement stmtSelectPermission;
	
	/**
	 * Check the permission of the connecting user when he wants to perform a store procedure in the database
	 * @author Mai Nguyen
	 *
	 */
	public SecureUMLTranslation(Connection conn) throws SQLException {
		/*stmtSelectPermission = conn
				.prepareStatement("SELECT COUNT(*) FROM sys.database_permissions "
						+ "WHERE USER_NAME(grantee_principal_id) = (?) "
						+ "AND OBJECT_NAME(major_id) = (?) "
						+ "AND permission_name = 'EXECUTE';");*/
	}

	
	/**
	 * check the permission for the connecting user on executing a given method (stored procedure).
	 * @param op is the method to be checked.
	 * @param usr is the user to be checked, This user should be the connecting user.
	 * @return true if the current role of the logged in user is authorized on the given stored procedure in the database. 
	 * Otherwise, @return false.
	 * @throws SQLException
	 */
	public boolean checkUserPermission(String op, String usr)
			throws SQLException {
		boolean access = false;
		String conUser = Environment.getConnectingUser();
		if (usr.equals(conUser)) {
			boolean isPermitted = Environment.isPermittedRole(op);
			if (isPermitted)
				access = true;
			else
				access = false;
		}
		return access;
	}
/*	
	
	*//**
	 * check the existence of a permission for the current role on a given stored procedure.
	 * @param op is the name of the stored procedure to be checked.
	 * @return
	 * @throws SQLException
	 *//*
	private static boolean isPermittedRole(String op) throws SQLException {
		boolean access = false;
		try {
			stmtSelectPermission.setString(1, Environment.getCurrentRole());
			stmtSelectPermission.setString(2, op);
			ResultSet resSet = stmtSelectPermission.executeQuery();
			if (resSet.next()) {
				if (resSet.getInt(1) > 0)
					access = true;
			}
			if (!access) {
				System.err.println("[STATIC_SECURITY] FAILED: The current role " +Environment.getCurrentRole() + " of the user "
						+ Environment.getConnectingUser()
						+ " does not have right to perform the method: "
						+ op);
			}
			resSet.close();
		} catch (SQLException e) {
			System.err.println("[STATIC_SECURITY] FAILED: SQLException "
					+ e.getMessage());
			e.printStackTrace();
			access = false;
		}
		return access;
	}*/
}
