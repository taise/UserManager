# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user (
  id                        bigint not null,
  name                      varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  created_at                timestamp,
  updated_at                timestamp,
  constraint uq_user_email unique (email),
  constraint pk_user primary key (id))
;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists user_seq;

