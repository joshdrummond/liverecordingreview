-- Sample DDL script for base database schema using MySQL
-- to create tables in your database
create table artists (artist_id int(11) not null auto_increment primary key, description varchar(50) not null)
create table categories (category_id int(11) not null auto_increment primary key, artist_id int(11) not null, description varchar(50) not null)
create table recordings (recording_id int(11) not null auto_increment primary key, category_id int(11) not null, type char(1) not null, description varchar(100) not null, source varchar(50) not null, info text not null, avg_perf_rating float not null, avg_rec_rating float not null, total_reviews int(11) not null, date_created datetime not null)
create table reviews (review_id int(11) not null auto_increment primary key, recording_id int(11) not null, user_id varchar(50) not null, performance_rating int not null, recording_rating int not null, notes text not null, date_created datetime not null)
-- insert dummy data, administrator may insert artists and categories of their choice, the rest is GUI driven
insert into artists values (null, 'Garbage')
insert into categories values (null, 1, '1995-1996')
insert into categories values (null, 1, '1998-1999')
insert into categories values (null, 1, '2001-2002')
insert into categories values (null, 1, '2005')
insert into artists values (null, 'Beatles')
insert into categories values (null, 2, '1961-1966')
insert into categories values (null, 2, '1967-1970')