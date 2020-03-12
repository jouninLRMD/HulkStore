/*
Created: 03/10/2020
Modified: 03/11/2020
Model: MySQL 8.0
Database: MySQL 8.0
*/

/* REPORTS */

CREATE VIEW
VI_Store AS
SELECT S.store_Id, S.store_Name, S.address, S.state
FROM STORE S
WHERE S.state = 1;

CREATE VIEW
VI_Product AS
SELECT P.product_Id, P.product_Name, U.unity_Description
FROM PRODUCT AS P
INNER JOIN UNITY AS U
ON P.unity_Id = U.unity_Id
WHERE P.state = 1;

CREATE VIEW
VI_Kardex AS
SELECT K.product_Id, P.product_Name, U.unity_Description, K.store_Id, S.store_Name, K.quantity, K.unity_Value
FROM INVENTORY AS K
INNER JOIN PRODUCT AS P
ON P.product_Id = K.product_Id
INNER JOIN STORE AS S
ON S.store_Id = K.store_Id
INNER JOIN UNITY AS U
ON U.unity_Id = P.unity_Id
WHERE K.state = 1;

CREATE VIEW
VI_KardexDetail AS
SELECT KD.detailId, KD.product_Id, KD.store_Id, KD.inventory_DetailYear, KD.inventory_DetailMonth, KD.inventory_Detailday, D.document_Id, KD.documentNumber, KD.operation, KD.quantity, KD.unity_Value, KD.total_Value, KD.observations
FROM INVENTORY_DETAIL AS KD
INNER JOIN DOCUMENT AS D
ON KD.document_Id = D.document_Id
WHERE KD.state = 1;