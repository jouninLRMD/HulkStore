/* TRIGGERS OF DATA VALIDATION */

/*
Created: 03/10/2020
Modified: 03/11/2020
Model: MySQL 8.0
Database: MySQL 8.0
*/

/* USERS user_Name, identification, realName, surname */
DELIMITER $$
CREATE TRIGGER TR_RegisterNewUser
	BEFORE INSERT ON USERS FOR EACH ROW
	BEGIN
		IF LENGTH(NEW.user_Name) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Dato invalido para identificador';
		ELSEIF LENGTH(NEW.identification) <> 8 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Dato invalido para numero de identificacion';
		ELSEIF LENGTH(NEW.realName) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Dato invalido para nombres';
		ELSEIF LENGTH(NEW.surname) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Dato invalido para apellidos';
		END IF;
	END;
$$

/* USERS user_Name, identification, realName, surname, state, userProfile */
DELIMITER $$
CREATE TRIGGER TR_ModifyUser
	BEFORE UPDATE ON USERS FOR EACH ROW
	BEGIN
		IF LENGTH(NEW.user_Name) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Dato para identificador invalido';
		ELSEIF LENGTH(NEW.identification) <> 8 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Dato para identificacion invalido';
		ELSEIF LENGTH(NEW.realName) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Dato para nombre invalido';
		ELSEIF LENGTH(NEW.surname) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Dato para apellidos invalido';
		ELSEIF (SELECT COUNT(*) FROM USERS WHERE state = 1 AND userProfile = 1) = 1 AND OLD.userProfile = 1 AND NEW.userProfile = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Debe haber al menos un administrador';
		END IF;
	END;
$$

/* UNITY unity_Description */
DELIMITER $$
CREATE TRIGGER TR_RegisterNewUnity
	BEFORE INSERT ON UNITY FOR EACH ROW
	BEGIN
		IF LENGTH(NEW.unity_Description) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Descripcion de unidad invalida';
		END IF;
	END;
$$

/* UNITY unity_Description */
DELIMITER $$
CREATE TRIGGER TR_ModifyUnity
	BEFORE UPDATE ON UNITY FOR EACH ROW
	BEGIN
		IF LENGTH(NEW.unity_Description) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Descripcion de unidad invalida';
		END IF;
	END;
$$

/* STORE store_Name, address */
DELIMITER $$
CREATE TRIGGER TR_RegisterNewStore
	BEFORE INSERT ON STORE FOR EACH ROW
	BEGIN
		IF LENGTH(NEW.store_Name) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Nombre de almacen invalido';
		ELSEIF LENGTH(NEW.address) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Direccion de almacen invalido';
		END IF;
END;
$$

/* STORE store_Name, address */
DELIMITER $$
CREATE TRIGGER TR_ModifyStore
	BEFORE UPDATE ON STORE FOR EACH ROW
	BEGIN
		IF LENGTH(NEW.store_Name) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Nombre de almacen invalido';
		ELSEIF LENGTH(NEW.address) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Direccion de almacen invalido';
		END IF;
END;
$$

/* DOCUMENT documentDescription */
DELIMITER $$
CREATE TRIGGER TR_RegisterNewDocument
	BEFORE INSERT ON DOCUMENT FOR EACH ROW
	BEGIN
		IF LENGTH(NEW.documentDescription) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Descripcion de documento invalido';
		END IF;
  END;
$$

/* DOCUMENT documentDescription */
DELIMITER $$
CREATE TRIGGER TR_ModifyDocument
	BEFORE UPDATE ON DOCUMENT FOR EACH ROW
	BEGIN
		IF LENGTH(NEW.documentDescription) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Descripcion de documento invalido';
		END IF;
	END;
$$

/*PRODUCT product_Name */
DELIMITER $$
CREATE TRIGGER TR_RegisterNewProduct
	BEFORE INSERT ON PRODUCT FOR EACH ROW
	BEGIN
		IF LENGTH(NEW.product_Name) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Nombre de producto invalido';
		END IF;
  END;
$$

/*PRODUCT product_Name */
DELIMITER $$
CREATE TRIGGER TR_ModifyProduct
	BEFORE UPDATE ON PRODUCT FOR EACH ROW
	BEGIN
		IF LENGTH(NEW.product_Name) = 0 THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Nombre de producto invalido';
		END IF;
  END;
$$

/*INVENTORY_DETAIL inventory_DetailYear, inventory_DetailMonth, inventory_DetailDay, quantity, total_Value */
DELIMITER $$
CREATE TRIGGER TR_RegisterNewKardexDetail
	BEFORE INSERT ON INVENTORY_DETAIL FOR EACH ROW
	BEGIN
		SET @numKarDet = (SELECT COUNT(*) FROM INVENTORY_DETAIL WHERE (store_Id = NEW.store_Id AND product_Id = NEW.product_Id));
		IF (NEW.inventory_DetailYear < 1990) THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Año invalido';
		ELSEIF (NEW.inventory_DetailMonth > 12 OR NEW.inventory_DetailMonth < 1) THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Mes invalido';
		ELSEIF (NEW.inventory_DetailDay > 31 OR NEW.inventory_DetailDay < 1) THEN
				SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Dia invalido';
		ELSEIF (@numKarDet >= 1)	THEN
			SET @detailYear =(SELECT inventory_DetailYear FROM INVENTORY_DETAIL WHERE (store_Id = NEW.store_Id AND product_Id = NEW.product_Id) ORDER BY inventory_DetailYear DESC, inventory_DetailMonth DESC,inventory_DetailDay DESC LIMIT 1);
			SET @detailMonth = (SELECT inventory_DetailMonth FROM INVENTORY_DETAIL WHERE (store_Id = NEW.store_Id AND product_Id = NEW.product_Id) ORDER BY inventory_DetailYear DESC, inventory_DetailMonth DESC,inventory_DetailDay DESC LIMIT 1);
			SET @detailDay = (SELECT inventory_DetailDay FROM INVENTORY_DETAIL WHERE (store_Id = NEW.store_Id AND product_Id = NEW.product_Id) ORDER BY inventory_DetailYear DESC, inventory_DetailMonth DESC,inventory_DetailDay DESC LIMIT 1);

			IF (NEW.inventory_DetailYear < @detailYear) THEN
				SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'El año debe de ser mayor al anterior';
			ELSEIF (@detailYear = NEW.inventory_DetailYear AND  NEW.inventory_DetailMonth < @detailMonth ) THEN
				SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'El mes debe de ser mayor al anterior';
			ELSEIF (@detailYear = NEW.inventory_DetailYear AND @detailMonth = NEW.inventory_DetailMonth AND  NEW.inventory_DetailDay < @detailDay) THEN
				SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'El día debe de ser mayor al anterior';
			END IF;
		END IF;

		IF NEW.operation = 1 THEN
			SET @quantity = (SELECT quantity FROM INVENTORY WHERE product_Id = NEW.product_Id AND store_Id = NEW.store_Id) + NEW.quantity ;
            SET @total_Value = (SELECT total_Value FROM INVENTORY WHERE product_Id = NEW.product_Id AND store_Id = NEW.store_Id) + NEW.total_Value
        ELSE
			SET @quantity = (SELECT quantity FROM INVENTORY WHERE product_Id = NEW.product_Id AND store_Id = NEW.store_Id) - NEW.quantity;
            SET @total_Value = (SELECT total_Value FROM INVENTORY WHERE product_Id = NEW.product_Id AND store_Id = NEW.store_Id) - NEW.total_Value;
        END IF;

        SET @unity_Value = @total_Value / @quantity;
		IF (@unity_Value IS null) THEN
			SET @unity_Value = 0;
		END IF;

		IF(@quantity < 0 OR @total_Value < 0) THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Cantidad excedida';
		ELSEIF(@unity_Value < 0) THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Valor unitario excedido';
    	END IF;
	END;
$$

/*INVENTORY_DETAIL inventory_DetailYear, inventory_DetailMonth, inventory_DetailDay, quantity, total_Value */
DELIMITER $$
CREATE TRIGGER TR_ModifyKardexDetail
	BEFORE UPDATE ON INVENTORY_DETAIL FOR EACH ROW
	BEGIN
		IF (NEW.inventory_DetailYear < 1990) THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Año invalido';
		ELSEIF (NEW.inventory_DetailMonth > 12 OR NEW.inventory_DetailMonth < 1) THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Mes invalido';
		ELSEIF (NEW.inventory_DetailDay > 31 OR NEW.inventory_DetailDay < 1) THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Dia invalido';
		ELSEIF (OLD.inventory_DetailYear > NEW.inventory_DetailYear) THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'El año debe de ser mayor al anterior detalle ';
		ELSEIF (OLD.inventory_DetailYear = NEW.inventory_DetailYear AND OLD.inventory_DetailMonth > NEW.inventory_DetailMonth) THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'El mes debe de ser mayor al anterior detalle ';
		ELSEIF (OLD.inventory_DetailYear = NEW.inventory_DetailYear AND OLD.inventory_DetailMonth = NEW.inventory_DetailMonth AND OLD.inventory_DetailDay > NEW.inventory_DetailDay) THEN
			SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'El día debe de ser mayor al anterior detalle ';
		END IF;

		IF NEW.state = 1 THEN
			IF OLD.operation = 1 THEN
				SET @quantity = (SELECT quantity FROM INVENTORY WHERE product_Id = NEW.product_Id AND store_Id = NEW.store_Id) - OLD.quantity;
				SET @total_Value = (SELECT total_Value FROM INVENTORY WHERE product_Id = NEW.product_Id AND store_Id = NEW.store_Id) - OLD.total_Value;
			ELSE
				SET @quantity = (SELECT quantity FROM INVENTORY WHERE product_Id = NEW.product_Id AND store_Id = NEW.store_Id) + OLD.quantity ;
				SET @total_Value = (SELECT total_Value FROM INVENTORY WHERE product_Id = NEW.product_Id AND store_Id = NEW.store_Id) + OLD.total_Value;
			END IF;

            IF NEW.operation = 1 THEN
				SET @quantity = @quantity + NEW.quantity;
                SET @total_Value = @total_Value + NEW.total_Value;
			ELSE
				SET @quantity = @quantity - NEW.quantity;
                SET @total_Value = @total_Value - NEW.total_Value;
            END IF;

            SET @unity_Value = @total_Value / @quantity;
			IF (@unity_Value IS null) THEN
				SET @unity_Value = 0;
			END IF;

			IF(@quantity < 0 OR @total_Value < 0 OR @unity_Value < 0) THEN
				SIGNAL SQLSTATE '45000' set  MESSAGE_TEXT = 'Cantidad excedida';
			END IF;
    	END IF;
	END;
$$

/* INVENTORY TRIGGER UPDATE  */

DELIMITER $$
CREATE TRIGGER TR_UpdateKardex
	AFTER INSERT ON INVENTORY_DETAIL FOR EACH ROW
	BEGIN
		SET @quantity = 0;
		SET @unity_Value = 0;
		SET @total_Value = 0;
		IF NEW.operation = 1 THEN
			SET @quantity = (SELECT quantity FROM INVENTORY WHERE product_Id = New.product_Id AND store_Id = NEW.store_Id) + NEW.quantity;
			SET @total_Value = (SELECT total_Value FROM INVENTORY WHERE product_Id = New.product_Id AND store_Id = NEW.store_Id) + NEW.total_Value;	
			IF (@total_Value IS null) THEN
				SET @total_Value = NEW.total_Value;
			END IF;
		ELSEIF NEW.operation = 0 THEN
			SET @quantity = (SELECT quantity FROM INVENTORY WHERE product_Id = New.product_Id AND store_Id = NEW.store_Id) - NEW.quantity;
			SET @total_Value = (SELECT total_Value FROM INVENTORY WHERE product_Id = New.product_Id AND store_Id = NEW.store_Id) - NEW.total_Value;
		END IF;
		SET @unity_Value = @total_Value/@quantity;
		IF (@unity_Value IS null) THEN
			SET @unity_Value = 0;
		END IF;
		UPDATE INVENTORY SET quantity = @quantity, unity_Value = @unity_Value, total_Value = @total_Value WHERE product_Id = New.product_Id AND store_Id = NEW.store_Id;
	END;
$$

DELIMITER $$
CREATE TRIGGER TR_AfterUpdateKardex
	AFTER UPDATE ON INVENTORY_DETAIL FOR EACH ROW
	BEGIN
		SET @quantity = 0;
		SET @unity_Value = 0;
		SET @total_Value = 0;
		IF NEW.state = 1 THEN
			IF NEW.operation <> OLD.operation THEN
				IF NEW.operation = 1 AND OLD.operation = 0 THEN
					SET @quantity = (SELECT quantity FROM INVENTORY WHERE product_Id = New.product_Id AND store_Id = NEW.store_Id) + OLD.quantity + NEW.quantity;
					SET @total_Value = (SELECT total_Value FROM INVENTORY WHERE product_Id = New.product_Id AND store_Id = NEW.store_Id) + OLD.total_Value + NEW.total_Value;
				ELSEIF NEW.operation = 0 AND OLD.operation = 1 THEN
					SET @quantity = (SELECT quantity FROM INVENTORY WHERE product_Id = New.product_Id AND store_Id = NEW.store_Id) - OLD.quantity - NEW.quantity;
					SET @total_Value = (SELECT total_Value FROM INVENTORY WHERE product_Id = New.product_Id AND store_Id = NEW.store_Id) - OLD.total_Value - NEW.total_Value;
				END IF;
			ELSEIF NEW.operation = NEW.operation THEN
				IF NEW.operation = 1 THEN
					SET @quantity = (SELECT quantity FROM INVENTORY WHERE product_Id = New.product_Id AND store_Id = NEW.store_Id) - OLD.quantity + NEW.quantity;
					SET @total_Value = (SELECT total_Value FROM INVENTORY WHERE product_Id = New.product_Id AND store_Id = NEW.store_Id) - OLD.total_Value + NEW.total_Value;
				ELSEIF NEW.operation = 0 THEN
					SET @quantity = (SELECT quantity FROM INVENTORY WHERE product_Id = New.product_Id AND store_Id = NEW.store_Id) + OLD.quantity - NEW.quantity;
					SET @total_Value = (SELECT total_Value FROM INVENTORY WHERE product_Id = New.product_Id AND store_Id = NEW.store_Id) + OLD.total_Value - NEW.total_Value;
				END IF;
			END IF;
		ELSEIF NEW.state = 3 THEN
			IF OLD.operation = 1 THEN
				SET @quantity = (SELECT quantity FROM INVENTORY WHERE product_Id = NEW.product_Id AND store_Id = NEW.store_Id) - OLD.quantity;
				SET @total_Value = (SELECT total_Value FROM INVENTORY WHERE product_Id = NEW.product_Id AND store_Id = NEW.store_Id) - OLD.total_Value;
			ELSEIF OLD.operation = 0 THEN
				SET @quantity = (SELECT quantity FROM INVENTORY WHERE product_Id = NEW.product_Id AND store_Id = NEW.store_Id) + OLD.quantity;
				SET @total_Value = (SELECT total_Value FROM INVENTORY WHERE product_Id = NEW.product_Id AND store_Id = NEW.store_Id) + OLD.total_Value;
            END IF;
    	END IF;
		SET @unity_Value = @total_Value/@quantity;
		IF (@unity_Value IS null) THEN
			SET @unity_Value = 0;
		END IF;
		UPDATE INVENTORY SET quantity = @quantity, unity_Value = @unity_Value, total_Value = @total_Value WHERE product_Id = New.product_Id AND store_Id = NEW.store_Id;
	END;
$$