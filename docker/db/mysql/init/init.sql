SELECT DATABASE();
select user();

CREATE TABLE MB_MEMBER(
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
