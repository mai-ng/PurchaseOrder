package generation.b.machines;

import generation.essential.security.Environment;
import generation.essential.security.SecureUMLTranslation;

import java.sql.SQLException;

public aspect AspectJForSecurity {

	private ActionsHistory log;
	private ADTranslation dynamicConstraints;
	private SecureUMLTranslation staticConstraints;

	pointcut pc_PurchaseOrder_create():call(public boolean FunctionalRequirement.PurchaseOrder_create(int));

	pointcut pc_PurchaseOrder_approve():call(public boolean FunctionalRequirement.PurchaseOrder_approve(int));

	pointcut pc_PurchaseOrder_receive():call(public boolean FunctionalRequirement.PurchaseOrder_receive(int));

	private void initSecureFilter() {
		try {
			staticConstraints = new SecureUMLTranslation(
					Environment.getDBConnection());
			dynamicConstraints = new ADTranslation(
					Environment.getDBConnection());
			log = new ActionsHistory(Environment.getDBConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	boolean around(int po_id):args(po_id) && pc_PurchaseOrder_create(){
		initSecureFilter();
		boolean result = false;
		String usr = Environment.getConnectingUser();
		try {
			// check static security rules
			boolean staticRight = staticConstraints.checkUserPermission(
					thisJoinPoint.getSignature().getName(), usr);
			if (staticRight) {
				System.out.println("[STATIC_SECURITY] PASSED! ");
				// check dynamic security rules
				boolean dynamicRight = dynamicConstraints
						.ADPurchaseOrder_create(po_id, usr);
				if (dynamicRight) {
					System.out.println("[DYNAMIC_SECURITY] PASSED!");
					// proceed the method execution
					result = proceed(po_id);
					if (result == true) {
						System.out.println("[ASPECTJ] "
								+ thisJoinPoint.getSignature().getName()
								+ " is succeeded. Going to write method-log ");
						log.LogPurchaseOrder_create(po_id, usr);
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("[ASPECTJ] SQLException: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	boolean around(int po_id):args(po_id) && pc_PurchaseOrder_approve(){
		initSecureFilter();
		boolean result = false;
		String usr = Environment.getConnectingUser();

		try {
			// check static security rules
			boolean staticRight = staticConstraints.checkUserPermission(
					thisJoinPoint.getSignature().getName(), usr);
			if (staticRight) {
				System.out.println("[STATIC_SECURITY] PASSED! ");
				// check dynamic security rules
				boolean dynamicRight = dynamicConstraints
						.ADPurchaseOrder_approve(po_id, usr);
				if (dynamicRight) {
					System.out.println("[DYNAMIC_SECURITY] PASSED!");
					// proceed the method execution
					result = proceed(po_id);
					if (result == true) {
						System.out.println("[ASPECTJ] "
								+ thisJoinPoint.getSignature().getName()
								+ " is succeeded. Going to write method-log ");
						log.LogPurchaseOrder_approve(po_id, usr);
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("[ASPECTJ] SQLException: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	boolean around(int po_id):args(po_id) && pc_PurchaseOrder_receive(){
		initSecureFilter();
		boolean result = false;
		String usr = Environment.getConnectingUser();

		try {
			// check static security rules
			boolean staticRight = staticConstraints.checkUserPermission(
					thisJoinPoint.getSignature().getName(), usr);
			if (staticRight) {
				System.out.println("[STATIC_SECURITY] PASSED! ");
				// check dynamic security rules
				boolean dynamicRight = dynamicConstraints
						.ADPurchaseOrder_receive(po_id, usr);
				if (dynamicRight) {
					System.out.println("[DYNAMIC_SECURITY] PASSED!");
					// proceed the method execution
					result = proceed(po_id);
					if (result == true) {
						System.out.println("[ASPECTJ] "
								+ thisJoinPoint.getSignature().getName()
								+ " is succeeded. Going to write method-log ");
						log.LogPurchaseOrder_receive(po_id, usr);
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("[ASPECTJ] SQLException: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

}
