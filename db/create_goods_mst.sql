USE custome_rise;

DROP TABLE IF EXISTS goods_mst;

CREATE TABLE goods_mst
(
  goods_id varchar(4) NOT NULL,
  goods_name varchar(20) NOT NULL,
  goods_price int(9) NOT NULL,
  goods_cost int(9) NOT NULL,
  delete_at varchar(2),
  PRIMARY KEY (goods_id)
) ;

INSERT INTO goods_mst VALUES
('GD01','Macbook Air',230000,200000,""),
('GD02','Windows11 Pro PC',180000,140000,"");