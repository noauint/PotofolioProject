USE custome_rise;

DROP TABLE IF EXISTS user_mst;

CREATE TABLE user_mst
(
  user_id char(6) NOT NULL,
  user_password varchar(254) NOT NULL,
  user_name varchar(30) NOT NULL,
  mail_address varchar(254) NOT NULL,
  PRIMARY KEY (user_id)
) ;



/** テストデータ **/
INSERT INTO user_mst
( user_id, user_password, user_name, mail_address)
VALUES
( '000001', 'P@ss0001', '京都太郎', 'cust1@example.com'),
( '000010', 'P@ss0002', '東京華子', 'cust2@example.com'),
( '000100', 'P@ss0003', '大阪次郎', 'cust3@example.com');