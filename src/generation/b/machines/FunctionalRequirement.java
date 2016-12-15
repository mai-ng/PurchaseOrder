/**
 * 
 */
package generation.b.machines;

import java.sql.CallableStatement;
import java.sql.SQLException;

import generation.essential.security.Environment;

/**
 * @author Mai Nguyen
 * The methods call the corresponding stored procedures in the database.
 */
public class FunctionalRequirement {
	
	/**
	 *  Call the stored procedure 'purchase_order_create' in the database, 
	 * which execute the statement of inserting a record with a list of given items on the table 'purchase_order'
	 * @param _items
	 * @return true if the stored procedure is executed (the statement returns the row count), 
	 * otherwise, return false (the statement returns nothing)
	 */
	public boolean PurchaseOrder_create(int po_id){
		boolean ret = false;
		CallableStatement statem;
		try {
			statem = Environment.getDBConnection().prepareCall("{call PurchaseOrder_create(?)}");
			statem.setInt("po_id", po_id);
			int result = statem.executeUpdate();
			if(result > 0){
				System.out.println("[INFO] Order " + po_id + " is created!");
				ret = true;
//				res2.close();
			}
			statem.close();
//			return ret;
		} catch (SQLException e) {
//			e.printStackTrace();
			System.err.println("[SQL] ERROR: " + e.getErrorCode() +": " + e.getMessage());
//			return ret;
		}
		return ret;
	}
	
	/** 
	 * Call the stored procedure 'purchase_order_approve' in the database, 
	 * which execute the statement of modifying the value of the column 'isApproved' to 'TRUE' on the table 'purchase_order'
	 * of a given order.
	 * @param po_id the identification of the order
	 * @return true if the stored procedure is executed (the statement returns the row count), 
	 * otherwise, return false (the statement returns nothing)
	 */
	public boolean PurchaseOrder_approve(int po_id){
		boolean ret = false;
		CallableStatement statem;
		try {
			statem = Environment.getDBConnection().prepareCall("{call PurchaseOrder_approve(?)}");
			statem.setInt("po_id", po_id);
			int result = statem.executeUpdate();
			if(result > 0){
				System.out.println("[INFO] Order " + po_id + " is approved!");
				ret = true;
			}
			statem.close();
//			return ret;
		} catch (SQLException e) {
//			e.printStackTrace();
			System.err.println("[SQL] ERROR: " + e.getErrorCode() +": " + e.getMessage());
//			return false;
		}
		return ret;
	}
	
	/**
	 * Call the stored procedure 'purchase_order_receiveGoods' in the database, 
	 * which execute the statement of modifying the value of the column 'isReveivedGoods' to 'TRUE' on the table 'purchase_order'
	 * of a given order.
	 * @param po_id the identification of the order
	 * @return true if the stored procedure is executed (the statement returns the row count), 
	 * otherwise, return false (the statement returns nothing)
	 */
	public boolean PurchaseOrder_receive(int po_id){
		boolean res = false;
		CallableStatement statem;
		try {
			statem = Environment.getDBConnection().prepareCall("{call PurchaseOrder_receive(?)}");
			
			statem.setInt("po_id", po_id);
			
			
			int result = statem.executeUpdate();
			if(result > 0){
				System.out.println("[INFO] Order " + po_id + " is completed!");
				res = true;
			}
			statem.close();
		} catch (SQLException e) {
			System.err.println("[SQL] ERROR: " + e.getErrorCode() +": " + e.getMessage());
		}
		return res;
	}
	
/*	*//**
	 * Call the stored procedure 'purchase_order_modify' in the database, 
	 * which execute the statement of modifying the value of the column 'items' on the table 'purchase_order'
	 * of a given order.
	 * @param _poId the identification of the order
	 * @return true if the stored procedure is executed (the statement returns the row count), 
	 * otherwise, return false (the statement returns nothing)
	 *//*
	public boolean purchase_order_modify(int _poId, String _items){
		boolean ret = false;
		CallableStatement statem;
		try {
			statem = Environment.getDBConnection().prepareCall("{call purchase_order_modify(?, ?)}");
			statem.setInt("oId", _poId);
			statem.setString("items", _items);
			int result = statem.executeUpdate();
			if(result == 1){
				System.out.println("[INFO] Order " + _poId + " is modified!");
				ret = true;
			}
			statem.close();
			return ret;
		} catch (SQLException e) {
//			e.printStackTrace();
			System.err.println("[SQL] ERROR: " + e.getErrorCode() +": " + e.getMessage());
			return false;
		}
	}*/
}
