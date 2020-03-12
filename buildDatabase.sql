DROP DATABASE IF EXISTS BD_HULKSTORE;
CREATE DATABASE BD_HULKSTORE;
USE BD_HULKSTORE;
/*
Created: 03/10/2020
Modified: 03/11/2020
Model: MySQL 8.0
Database: MySQL 8.0
*/

-- Create tables section -------------------------------------------------

-- Table PRODUCTO

CREATE TABLE PRODUCT
(
  product_Id Int(6) ZEROFILL NOT NULL AUTO_INCREMENT,
  product_Name Varchar(50) NOT NULL,
  unity_Id Int(3) ZEROFILL,
  state Tinyint(1) NOT NULL DEFAULT 1,
 PRIMARY KEY (product_Id)
)
;

CREATE INDEX idx_unity_Id ON PRODUCT (unity_Id)
;

-- Table INVENTORY

CREATE TABLE INVENTORY
(
  product_Id Int(6) ZEROFILL NOT NULL,
  store_Id Int(6) ZEROFILL NOT NULL,
  quantity Double(9,2) NOT NULL,
  unity_Value Double(9,2) NOT NULL,
  total_Value Double(9,2),
  state Tinyint(1) NOT NULL DEFAULT 1
)
;

ALTER TABLE INVENTORY ADD PRIMARY KEY (product_Id,store_Id)
;

-- Table INVENTORY_DETAIL

CREATE TABLE INVENTORY_DETAIL
(
  detailId Int(6) ZEROFILL NOT NULL AUTO_INCREMENT,
  product_Id Int(6) ZEROFILL NOT NULL,
  store_Id Int(6) ZEROFILL NOT NULL,
  inventory_DetailYear Int(2) ZEROFILL NOT NULL,
  inventory_DetailMonth Int(2) ZEROFILL NOT NULL,
  inventory_Detailday Int(2) ZEROFILL NOT NULL,
  userId Int(6) ZEROFILL,
  document_Id Int(6) ZEROFILL NOT NULL,
  documentNumber Int(11) NOT NULL,
  operation Bool NOT NULL,
  quantity Double(9,2) NOT NULL,
  unity_Value Double(9,2) NOT NULL,
  total_Value Double(9,2) NOT NULL,
  observations Varchar(100),
  state Tinyint(1) NOT NULL DEFAULT 1,
 PRIMARY KEY (detailId,product_Id,store_Id)
)
;

CREATE INDEX idx_userId ON INVENTORY_DETAIL (userId)
;

CREATE INDEX idx_document_Id ON INVENTORY_DETAIL (document_Id)
;

-- Table STORE

CREATE TABLE STORE
(
  store_Id Int(6) ZEROFILL NOT NULL AUTO_INCREMENT,
  store_Name Varchar(20) NOT NULL,
  address Varchar(40) NOT NULL,
  state Tinyint(1) NOT NULL DEFAULT 1,
 PRIMARY KEY (store_Id)
)
;

-- Table UNITY

CREATE TABLE UNITY
(
  unity_Id Int(3) ZEROFILL NOT NULL AUTO_INCREMENT,
  unity_Description Varchar(20) NOT NULL,
  state Tinyint(1) NOT NULL DEFAULT 1,
 PRIMARY KEY (unity_Id)
)
;

-- Table DOCUMENT

CREATE TABLE DOCUMENT
(
  document_Id Int(6) ZEROFILL NOT NULL AUTO_INCREMENT,
  documentDescription Text NOT NULL,
  state Tinyint(1) NOT NULL DEFAULT 1,
PRIMARY KEY (document_Id)
)
;

-- Table USER

CREATE TABLE USERS
(
  userId Int(6) ZEROFILL NOT NULL AUTO_INCREMENT,
  userName Varchar(32) NOT NULL,
  userPass Char(32) NOT NULL,
  identification Char(8) NOT NULL,
  realName Varchar(40) NOT NULL,
  surname Varchar(40) NOT NULL,
  userProfile Bool NOT NULL DEFAULT 0,
  state Tinyint(1) NOT NULL,
 PRIMARY KEY (userId)
)
;

ALTER TABLE USERS ADD UNIQUE userName (userName)
;

-- Create relationships section ------------------------------------------------- 

ALTER TABLE PRODUCT ADD CONSTRAINT fk_product__unity_ FOREIGN KEY (unity_Id) REFERENCES UNITY (unity_Id) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE INVENTORY ADD CONSTRAINT fk_inventory__product_ FOREIGN KEY (product_Id) REFERENCES PRODUCT (product_Id) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE INVENTORY ADD CONSTRAINT fk_inventory__store_ FOREIGN KEY (store_Id) REFERENCES STORE (store_Id) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE INVENTORY_DETAIL ADD CONSTRAINT fk_inventory_Detail_inventory_ FOREIGN KEY (product_Id, store_Id) REFERENCES INVENTORY (product_Id, store_Id) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE INVENTORY_DETAIL ADD CONSTRAINT fk_inventory_Detail_document FOREIGN KEY (document_Id) REFERENCES DOCUMENT (document_Id) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE INVENTORY_DETAIL ADD CONSTRAINT fk_inventory_Detail_users FOREIGN KEY (userId) REFERENCES USERS (userId) ON DELETE NO ACTION ON UPDATE NO ACTION
;