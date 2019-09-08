/*==============================================================*/
/* DBMS name:      MySQL 8.0.16                                    */
/* Created on:     2019/6/14 23:10:29                           */
/*==============================================================*/
set names utf8;
drop table if exists file;
drop table if exists feedback;
drop table if exists apply;
drop table if exists interest;
drop table if exists report;
drop table if exists student;
drop table if exists supervisor;
drop table if exists advertisement;
drop table if exists enterprise;

/*==============================================================*/
/* Table: supervisor                                          */
/*==============================================================*/
create table supervisor
(
   supid                  int not null auto_increment,
   supname                  varchar(30) not null,
   password                 varchar(30) not null,
   suptitle               varchar(20) not null,
   school              varchar(40) not null,
   supmail                varchar(40) not null,
   primary key (supid)
);


/*==============================================================*/
/* Table: student                                          */
/*==============================================================*/
create table student
(
   sid                    int not null auto_increment,
   supname                varchar(30) not null,
   supid                   int,
   sname                  varchar(30) not null,
   password                 varchar(30) not null,
   sage                   int,
   school                 varchar(40) not null,
   smail                  varchar(40) not null,
   primary key (sid)
);






/*==============================================================*/
/* Table: cv                                    */
/*==============================================================*/
create table file
(  fid                   int not null auto_increment,
   fname                  varchar(200) not null,
   sid                    int  not null, 
   fplace                  varchar(1000) not null,
   fdescription                  varchar(1000),
   createdate                   datetime,
   primary key (fid)
);


/*==============================================================*/
/* Table: enterprise                                    */
/*==============================================================*/
create table enterprise
(
   eid                    int not null auto_increment,
   website             varchar(100) not null,
   ename                   varchar(50) not null,
   password                 varchar(30) not null,
   email                   varchar(60) not null,

   primary key (eid)
);


create table advertisement
(
   aid                     int not null auto_increment,
   eid                      int,
   title                     varchar(90) not null,
   content                  varchar(7000) not null,
   companyDescription        varchar(7000) not null,
   salary                    varchar(30) not null,
   workplace                varchar(90) ,
   contact                  varchar(50),
   feature                  varchar(50),
   imgpath                  varchar(200) not null,
   enddate                     date, 
   primary key (aid)
);


/*==============================================================*/
/* Table: interest                                      */
/*==============================================================*/

create table interest(
     interestid                  int not null auto_increment,
     sname              varchar(30) not null,
     interest                    varchar(70) ,
      primary key (interestid)
   );


/*==============================================================*/
/* Table: apply                                     */
/*==============================================================*/

create table apply(
      applyid                 int not null auto_increment,
      aid                     int not null, 
      sname                  varchar(30) not null,
      fid                   int not null,
      applydate                     date, 
      status                varchar(30),
      feedback              varchar(7000),
      primary key (applyid)
   );


/*==============================================================*/
/* Table: report                                       */
/*==============================================================*/
create table report
(
    reportid                   int not null auto_increment,
    sname                  varchar(30) not null,
    supid                        int not null,
    content                 varchar(3000),
    reporttitle              varchar(30) ,
    feedback                varchar(3000),
    reportdate               date,
    primary key (reportid)
);


alter table comment add constraint fk_supervisor_comment foreign key(supid) references supervisor(supid);
alter table comment add constraint fk_student_comment foreign key(sid) references student(sid);
alter table advertisement add constraint fk_enterprise_adv foreign key(eid) references enterprise(eid);




