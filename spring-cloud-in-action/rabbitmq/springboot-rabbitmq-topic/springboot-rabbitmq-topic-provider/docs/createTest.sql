CREATE TABLE t_order (
	id VARCHAR (128) not null PRIMARY KEY, 	--订单id
	name VARCHAR (128),					--订单名称
	message_id VARCHAR (128) not null --消息唯一id
)

CREATE TABLE broker_message_log (
	message_id varchar(128) not null primary key, 
	message varchar(4000) not null,
	try_count INT DEFAULT '0',
	status VARCHAR(10) DEFAULT '', --消息投递状态，0-投递中 1-投递成功 2-投递失败
	next_retry timestamp not null,
	create_time timestamp not null,
	update_time timestamp not null
)