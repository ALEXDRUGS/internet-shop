create table ad (ad_id int4 generated by default as identity, description varchar(255), price int4, title varchar(255), avatar_id int4, image_id int4, user_id int4, primary key (ad_id));
create table comment (comment_id int4 generated by default as identity, date_of_creation timestamp, first_name varchar(255), text varchar(255), ad_id_ad_id int4, avatar_id int4, user_id int4, primary key (comment_id));
create table image (id int4 generated by default as identity, image bytea, image_reference varchar(255), name varchar(255), primary key (id));
create table users (id int4 generated by default as identity, first_name varchar(255), last_name varchar(255), password varchar(255), phone varchar(255), role varchar(255), username varchar(255), avatar_id int4, primary key (id));
alter table if exists users add constraint UK_r43af9ap4edm43mmtq01oddj6 unique (username);
alter table if exists ad add constraint FK9rnuflhjiae0qud8yh0qyo95f foreign key (avatar_id) references image;
alter table if exists ad add constraint FKq69hb9wo7ex9p9k27o2wkilk4 foreign key (image_id) references image;
alter table if exists ad add constraint FKtofr7l4mo2aajd4mm1k7n93a6 foreign key (user_id) references users;
alter table if exists comment add constraint FKtll2wftjvrnkcavmsnujbv0q4 foreign key (ad_id_ad_id) references ad;
alter table if exists comment add constraint FKfgpvoicpv07kmah3f04bhb9pd foreign key (avatar_id) references image;
alter table if exists comment add constraint FKqm52p1v3o13hy268he0wcngr5 foreign key (user_id) references users;
alter table if exists users add constraint FK19lflpg5seis4dwrm2lvjlxfv foreign key (avatar_id) references image;
