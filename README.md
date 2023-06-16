# Tours_BD


# Script Tablas Oracle

prompt PL/SQL Developer Export Tables for user JROJAS@XE
prompt Created by jitor on jueves, 15 de junio de 2023
set feedback off
set define off

prompt Dropping TBL_EMPRESA...
drop table TBL_EMPRESA cascade constraints;
prompt Dropping TBL_TIPOTOURS...
drop table TBL_TIPOTOURS cascade constraints;
prompt Dropping TBL_TOURS...
drop table TBL_TOURS cascade constraints;
prompt Dropping TBL_CLIENTES...
drop table TBL_CLIENTES cascade constraints;
prompt Dropping TBL_ITINERARIO...
drop table TBL_ITINERARIO cascade constraints;
prompt Dropping TBL_RESERVA...
drop table TBL_RESERVA cascade constraints;
prompt Creating TBL_EMPRESA...
create table TBL_EMPRESA
(
  emp_cedjuridica  NUMBER(15) not null,
  emp_nombre       VARCHAR2(30),
  emp_telefono     VARCHAR2(20),
  emp_correo       VARCHAR2(50),
  emp_fechafund    DATE,
  emp_calificacion VARCHAR2(1)
)
tablespace UNA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table TBL_EMPRESA
  add constraint PK_CEDJURIDICA primary key (EMP_CEDJURIDICA)
  using index 
  tablespace UNA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating TBL_TIPOTOURS...
create table TBL_TIPOTOURS
(
  tpt_codigo   NUMBER(5) not null,
  tpt_tipotour VARCHAR2(1),
  tpt_nombre   VARCHAR2(50),
  tpt_pais     VARCHAR2(50)
)
tablespace UNA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table TBL_TIPOTOURS
  add constraint PK_TIPOTOURS primary key (TPT_CODIGO)
  using index 
  tablespace UNA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating TBL_TOURS...
create table TBL_TOURS
(
  tur_idempresa       NUMBER(15),
  tur_idtour          NUMBER(5) not null,
  tur_nombretour      VARCHAR2(50),
  tur_fechasalida     DATE,
  tur_fecharegreso    DATE,
  tur_costos          NUMBER(10,2),
  tur_codigotipotours NUMBER(5) not null
)
tablespace UNA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table TBL_TOURS
  add constraint PK_IDTOURS primary key (TUR_IDTOUR)
  using index 
  tablespace UNA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table TBL_TOURS
  add constraint FK_EMPRESA foreign key (TUR_IDEMPRESA)
  references TBL_EMPRESA (EMP_CEDJURIDICA);
alter table TBL_TOURS
  add constraint FK_TIPOTOURS foreign key (TUR_CODIGOTIPOTOURS)
  references TBL_TIPOTOURS (TPT_CODIGO);

prompt Creating TBL_CLIENTES...
create table TBL_CLIENTES
(
  cli_idtours       NUMBER(5),
  cli_idcliente     NUMBER(15) not null,
  cli_nombre        VARCHAR2(30),
  cli_papellido     VARCHAR2(30),
  cli_sapellido     VARCHAR2(30),
  cli_telefono      VARCHAR2(20),
  cli_correo        VARCHAR2(50),
  cli_fecnacimiento DATE
)
tablespace UNA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table TBL_CLIENTES
  add constraint PK_CLIENTE primary key (CLI_IDCLIENTE)
  using index 
  tablespace UNA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table TBL_CLIENTES
  add constraint FK_CLIXTOURS foreign key (CLI_IDTOURS)
  references TBL_TOURS (TUR_IDTOUR);

prompt Creating TBL_ITINERARIO...
create table TBL_ITINERARIO
(
  iti_idtours           NUMBER(5) not null,
  iti_iditinerario      NUMBER(5) not null,
  iti_lugar             VARCHAR2(50),
  iti_fecllegada        DATE,
  iti_fecsalida         DATE,
  iti_duracion          VARCHAR2(15),
  iti_descp_actividades VARCHAR2(250),
  iti_horallegada       VARCHAR2(5),
  iti_horasalida        VARCHAR2(5)
)
tablespace UNA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table TBL_ITINERARIO
  add constraint PK_ITINERARIO primary key (ITI_IDITINERARIO)
  using index 
  tablespace UNA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table TBL_ITINERARIO
  add constraint FK_ITIXTOURS foreign key (ITI_IDTOURS)
  references TBL_TOURS (TUR_IDTOUR);

prompt Creating TBL_RESERVA...
create table TBL_RESERVA
(
  res_idreserva      NUMBER(5) not null,
  res_idcliente      NUMBER(15),
  res_idtour         NUMBER(5),
  res_fecreserva     DATE,
  res_cantpersonas   NUMBER(2),
  res_costo          NUMBER(10,2),
  res_montosabonados NUMBER(10,2)
)
tablespace UNA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table TBL_RESERVA
  add constraint PK_RESERVA primary key (RES_IDRESERVA)
  using index 
  tablespace UNA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table TBL_RESERVA
  add constraint FK_RESXCLI foreign key (RES_IDCLIENTE)
  references TBL_CLIENTES (CLI_IDCLIENTE);
alter table TBL_RESERVA
  add constraint FK_RESXTUR foreign key (RES_IDTOUR)
  references TBL_TOURS (TUR_IDTOUR);

prompt Disabling triggers for TBL_EMPRESA...
alter table TBL_EMPRESA disable all triggers;
prompt Disabling triggers for TBL_TIPOTOURS...
alter table TBL_TIPOTOURS disable all triggers;
prompt Disabling triggers for TBL_TOURS...
alter table TBL_TOURS disable all triggers;
prompt Disabling triggers for TBL_CLIENTES...
alter table TBL_CLIENTES disable all triggers;
prompt Disabling triggers for TBL_ITINERARIO...
alter table TBL_ITINERARIO disable all triggers;
prompt Disabling triggers for TBL_RESERVA...
alter table TBL_RESERVA disable all triggers;
prompt Disabling foreign key constraints for TBL_TOURS...
alter table TBL_TOURS disable constraint FK_EMPRESA;
alter table TBL_TOURS disable constraint FK_TIPOTOURS;
prompt Disabling foreign key constraints for TBL_CLIENTES...
alter table TBL_CLIENTES disable constraint FK_CLIXTOURS;
prompt Disabling foreign key constraints for TBL_ITINERARIO...
alter table TBL_ITINERARIO disable constraint FK_ITIXTOURS;
prompt Disabling foreign key constraints for TBL_RESERVA...
alter table TBL_RESERVA disable constraint FK_RESXCLI;
alter table TBL_RESERVA disable constraint FK_RESXTUR;
prompt Loading TBL_EMPRESA...
insert into TBL_EMPRESA (emp_cedjuridica, emp_nombre, emp_telefono, emp_correo, emp_fechafund, emp_calificacion)
values (78975, 'Reds', '2771-4020', 'reds720s@gmail.com', to_date('27-09-2012', 'dd-mm-yyyy'), '5');
insert into TBL_EMPRESA (emp_cedjuridica, emp_nombre, emp_telefono, emp_correo, emp_fechafund, emp_calificacion)
values (891223, 'Sé Turismo', '2771-9090', 'seturimos97@gmail.com', to_date('21-06-2019', 'dd-mm-yyyy'), '3');
commit;
prompt 2 records loaded
prompt Loading TBL_TIPOTOURS...
insert into TBL_TIPOTOURS (tpt_codigo, tpt_tipotour, tpt_nombre, tpt_pais)
values (7654, 'N', 'Montañoso', 'Costa Rica');
insert into TBL_TIPOTOURS (tpt_codigo, tpt_tipotour, tpt_nombre, tpt_pais)
values (3409, 'I', 'Urbano', 'Japon');
insert into TBL_TIPOTOURS (tpt_codigo, tpt_tipotour, tpt_nombre, tpt_pais)
values (8723, 'N', 'Acuatico', 'Costa Rica');
commit;
prompt 3 records loaded
prompt Loading TBL_TOURS...
insert into TBL_TOURS (tur_idempresa, tur_idtour, tur_nombretour, tur_fechasalida, tur_fecharegreso, tur_costos, tur_codigotipotours)
values (78975, 1435, 'Canopy', to_date('07-06-2023', 'dd-mm-yyyy'), to_date('30-06-2023', 'dd-mm-yyyy'), 25000, 7654);
insert into TBL_TOURS (tur_idempresa, tur_idtour, tur_nombretour, tur_fechasalida, tur_fecharegreso, tur_costos, tur_codigotipotours)
values (891223, 90201, 'Ruta Idea', to_date('22-06-2023', 'dd-mm-yyyy'), to_date('07-07-2023', 'dd-mm-yyyy'), 10000, 3409);
insert into TBL_TOURS (tur_idempresa, tur_idtour, tur_nombretour, tur_fechasalida, tur_fecharegreso, tur_costos, tur_codigotipotours)
values (78975, 98762, 'Kayak', to_date('12-06-2023', 'dd-mm-yyyy'), to_date('30-06-2023', 'dd-mm-yyyy'), 15000, 8723);
commit;
prompt 3 records loaded
prompt Loading TBL_CLIENTES...
insert into TBL_CLIENTES (cli_idtours, cli_idcliente, cli_nombre, cli_papellido, cli_sapellido, cli_telefono, cli_correo, cli_fecnacimiento)
values (null, 115879724, 'Hairol', 'Romero', 'Sandi', '8496-7905', 'hairol.romero@gmail.com', to_date('06-06-1890', 'dd-mm-yyyy'));
insert into TBL_CLIENTES (cli_idtours, cli_idcliente, cli_nombre, cli_papellido, cli_sapellido, cli_telefono, cli_correo, cli_fecnacimiento)
values (null, 117990469, 'Jorge', 'Rojas', 'Mena', '61422797', 'jorge.rojas.mena@est.una.ac.cr', to_date('29-05-2023', 'dd-mm-yyyy'));
insert into TBL_CLIENTES (cli_idtours, cli_idcliente, cli_nombre, cli_papellido, cli_sapellido, cli_telefono, cli_correo, cli_fecnacimiento)
values (null, 604223487, 'Pepito', 'Lopez', 'Mora', '8798-2143', 'pepitolopez@gmail.com', to_date('20-09-2018', 'dd-mm-yyyy'));
commit;
prompt 3 records loaded
prompt Loading TBL_ITINERARIO...
insert into TBL_ITINERARIO (iti_idtours, iti_iditinerario, iti_lugar, iti_fecllegada, iti_fecsalida, iti_duracion, iti_descp_actividades, iti_horallegada, iti_horasalida)
values (1435, 64589, 'Perez Zeledon', to_date('16-06-2023', 'dd-mm-yyyy'), to_date('08-06-2023', 'dd-mm-yyyy'), '8 dias', 'Se ira en bus a la montaña, luego se dara almuerzo y luego se ira al Canopy', '7', '7');
insert into TBL_ITINERARIO (iti_idtours, iti_iditinerario, iti_lugar, iti_fecllegada, iti_fecsalida, iti_duracion, iti_descp_actividades, iti_horallegada, iti_horasalida)
values (98762, 238, 'Santa Rosa', to_date('29-06-2023', 'dd-mm-yyyy'), to_date('07-06-2023', 'dd-mm-yyyy'), '5 horas', 'Se ira a almorzar a una soda jaja, y luego para el rio JAJA', '7', '7');
commit;
prompt 2 records loaded
prompt Loading TBL_RESERVA...
insert into TBL_RESERVA (res_idreserva, res_idcliente, res_idtour, res_fecreserva, res_cantpersonas, res_costo, res_montosabonados)
values (24678, 604223487, 90201, to_date('29-06-2023', 'dd-mm-yyyy'), 1, 8000, 2000);
insert into TBL_RESERVA (res_idreserva, res_idcliente, res_idtour, res_fecreserva, res_cantpersonas, res_costo, res_montosabonados)
values (7890, 117990469, 90201, to_date('08-06-2023', 'dd-mm-yyyy'), 7, 70000, 0);
insert into TBL_RESERVA (res_idreserva, res_idcliente, res_idtour, res_fecreserva, res_cantpersonas, res_costo, res_montosabonados)
values (8712, 115879724, 1435, to_date('25-06-2023', 'dd-mm-yyyy'), 2, 50000, 0);
insert into TBL_RESERVA (res_idreserva, res_idcliente, res_idtour, res_fecreserva, res_cantpersonas, res_costo, res_montosabonados)
values (5673, 115879724, 98762, to_date('07-06-2023', 'dd-mm-yyyy'), 3, 35000, 0);
insert into TBL_RESERVA (res_idreserva, res_idcliente, res_idtour, res_fecreserva, res_cantpersonas, res_costo, res_montosabonados)
values (2034, 115879724, 90201, to_date('14-06-2023', 'dd-mm-yyyy'), 5, 40000, 10000);
commit;
prompt 5 records loaded
prompt Enabling foreign key constraints for TBL_TOURS...
alter table TBL_TOURS enable constraint FK_EMPRESA;
alter table TBL_TOURS enable constraint FK_TIPOTOURS;
prompt Enabling foreign key constraints for TBL_CLIENTES...
alter table TBL_CLIENTES enable constraint FK_CLIXTOURS;
prompt Enabling foreign key constraints for TBL_ITINERARIO...
alter table TBL_ITINERARIO enable constraint FK_ITIXTOURS;
prompt Enabling foreign key constraints for TBL_RESERVA...
alter table TBL_RESERVA enable constraint FK_RESXCLI;
alter table TBL_RESERVA enable constraint FK_RESXTUR;
prompt Enabling triggers for TBL_EMPRESA...
alter table TBL_EMPRESA enable all triggers;
prompt Enabling triggers for TBL_TIPOTOURS...
alter table TBL_TIPOTOURS enable all triggers;
prompt Enabling triggers for TBL_TOURS...
alter table TBL_TOURS enable all triggers;
prompt Enabling triggers for TBL_CLIENTES...
alter table TBL_CLIENTES enable all triggers;
prompt Enabling triggers for TBL_ITINERARIO...
alter table TBL_ITINERARIO enable all triggers;
prompt Enabling triggers for TBL_RESERVA...
alter table TBL_RESERVA enable all triggers;

set feedback on
set define on
prompt Done


