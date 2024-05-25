USE mysqldb;

-- Create database
CREATE DATABASE IF NOT EXISTS mysqldb;
-- Switch to the newly created database
USE mysqldb;

SELECT DATABASE();
select user();

CREATE TABLE IF NOT EXISTS mb_member(
                                        member_id INT(11) NOT NULL AUTO_INCREMENT,
                                        member_nm VARCHAR(30) NOT NULL,
                                        cell_no VARCHAR(30) NOT NULL,
                                        skin_type VARCHAR(20),
                                        age varchar(3) NOT NULL,
                                        entry_date DATETIME,
                                        addr varchar(100),
                                        addr_detail varchar(200),
                                        CONSTRAINT MB_MEMBER_PK PRIMARY KEY(member_id)
);

select * from MB_MEMBER;

CREATE TABLE IF NOT EXISTS pv_goods(
                                       goods_no INT NOT NULL AUTO_INCREMENT,
                                       category VARCHAR(30) NOT NULL,
                                       goods_nm VARCHAR(30) NOT NULL,
                                       sale_price INT not null,
                                       market_price INT not null,
                                       supply_price INT not null,
                                       vendor_id INT not null,
                                       stock_qty INT not null,
                                       brand_nm VARCHAR(20) not null,
                                       image varchar(200),
                                       add_image varchar(200),
                                       sale_start_dtime DATETIME default now(),
                                       sale_end_dtime DATETIME default (DATE_ADD(now(), INTERVAL 1 DAY) - INTERVAL 1 SECOND),
                                       insert_dtime DATETIME default now(),
                                       update_dtime DATETIME DEFAULT now(),
                                       CONSTRAINT PV_GOODS_PK PRIMARY KEY(goods_no)
);

select (DATE_ADD(now(), INTERVAL 1 DAY) - INTERVAL 1 SECOND);

CREATE TABLE IF NOT EXISTS pv_item(
                                      item_no INT(11) NOT NULL AUTO_INCREMENT,
                                      goods_no INT(11) NOT NULL,
                                      item_nm VARCHAR(30) NOT NULL,
                                      item_qty INT,
                                      insert_dtime DATETIME default now(),
                                      update_dtime DATETIME DEFAULT now(),
                                      CONSTRAINT PV_ITEM_PK PRIMARY KEY(item_no),
                                      foreign key (goods_no) references PV_GOODS(goods_no)
);

commit;

select * from pv_goods;
select * from pv_item;
select * from pv_goods where goods_no =1;


