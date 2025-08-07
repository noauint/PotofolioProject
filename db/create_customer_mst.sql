USE custome_rise;

DROP TABLE IF EXISTS customer_mst;

CREATE TABLE customer_mst
(
  customer_id INT NOT NULL AUTO_INCREMENT,
  customer_name VARCHAR(254) NOT NULL,
  delete_at varchar(2) NOT NULL DEFAULT '',
  PRIMARY KEY (customer_id)
);

/** テストデータ **/
INSERT INTO customer_mst (customer_name) VALUES
('田中商事'),
('株式会社NN'),
('SS有限会社'),
('DD合同会社');
