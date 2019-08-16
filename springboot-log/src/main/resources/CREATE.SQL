create table SYSTEMLOG ( id INT(18) PRIMARY KEY,
	requestip VARCHAR(50),-- 操作IP
	type VARCHAR(3),-- 操作类型 1 操作记录2异常记录
	userid INT(18),-- 操作人ID
	description VARCHAR(4000),-- 操作描述
	actiondate DATE,-- 操作时间
	exceptioncode VARCHAR(50),-- 异常code
	exceptiondetail VARCHAR(4000),-- 异常详情
	actionmethod VARCHAR(500),-- 请求方法
	params BLOB -- 请求参数
)