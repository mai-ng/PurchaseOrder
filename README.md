# Warehouse Management System (WarehouseMS)
---
This case study involves two classes PurchaseOrder and Supplier, which are linked by an association BelongTo. Each class is described by a set of attributes and defines some operations to create new instances or delete existing ones. We make the assumption that the first attribute of each class denotes its key. As specified by the multiplicities, each purchase order belongs to one supplier (multiplicity 1), whereas each supplier may have zero to several purchase orders (multiplicity ∗).

Security Rules:
Rule 1. Only Managers are authorized to approve a purchase order (operation approve)
Rule 2. Only Staffs are permitted to make the creation and the reception of a purchase order (operations create and receive)
Rule 3. The creation and the reception of a purchase order should be executed by two different persons.
---
Technologies:
- Java
- AspectJ
- MS SQL
