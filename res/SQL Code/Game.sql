connect / as sysdba;

CREATE User RDBMS_project identified by 1138;
GRANT all privilege TO RDBMS_project;

disc;

conn RDBMS_project/1138;

drop table object_status;
drop table player;
drop table save_game;

CREATE TABLE Save_game
(
id int primary key,
name varchar2(30),
last_save varchar2(30),
game_time int,
location varchar2(30)
);

insert into Save_game values(0, 'INTO THE VILLAGE',to_char(sysdate, 'MONTH DD, YYYY HH:MI PM') , 0, 'Village');

CREATE TABLE Player
(
player_id int,
boyX int,
boyY int,
girlX int,
girlY int,
boyCurrentHealth int,
girlCurrentHealth int,
save_game_id int unique not null
);

delete from player;
insert into Player values(0,2200,1100, 2100,1100,100,100,0);

CREATE TABLE object_status
(
object_id int,
object_status int,
save_game_id int
);

commit;