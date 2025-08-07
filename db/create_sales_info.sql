USE custome_rise;

DROP TABLE IF EXISTS sales_info;

CREATE TABLE sales_info
(
  proceeds_id INT NOT NULL AUTO_INCREMENT,
  person varchar(20) NOT NULL,
  pcs varchar(3) NOT NULL,
  goods_id varchar(4) NOT NULL,
  sales_date DATE NOT NULL,
  customer_id int NOT NULL,
  delete_at varchar(2) NOT NULL,
  PRIMARY KEY (proceeds_id),
  FOREIGN KEY (customer_id) REFERENCES customer_mst (customer_id),
  FOREIGN KEY (goods_id) REFERENCES goods_mst (goods_id)
);

INSERT INTO sales_info
(person, pcs, goods_id, sales_date, customer_id, delete_at)
VALUES
('京都太郎',  '23','GD01','2021-06-23',1,''),
('東京華子',  '14','GD02','2021-06-04',2,'');