insert into t_user(id,name,login_name,password,status,register_date,salt) values(1,'admin','admin','691b14d79bf0fa2215f155235df5e670b64394cc',0,NOW(),'7efbd59d9741d34f');

insert into t_role(id,name,permissions) values(1,'超级管理员','admin,mng:user,mng:role');

insert into t_user_role(id,user_id,role_id) values(1,1,1);

insert into t_auth(id,name,permission,update_date) values(1,'超级管理员','admin',now());
insert into t_auth(id,name,permission,update_date) values(2,'用户管理','mng:user',now());
insert into t_auth(id,name,permission,update_date) values(3,'角色管理','mng:role',now());
