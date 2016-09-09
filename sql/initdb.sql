--删除权限数据
DELETE FROM shiro_user_role;
DELETE FROM shiro_permission;
DELETE FROM shiro_loginlog;
DELETE FROM shiro_user;
DELETE FROM shiro_userinfo;
DELETE FROM shiro_role;
--初始数据
--角色(权限)设定
INSERT INTO shiro_role(id,rolename,remark) VALUES (1,'role_admin','管理员');
--用户信息
INSERT INTO shiro_userinfo(id,realname)VALUES (1, '管理员');
--用户
INSERT INTO shiro_user(id,accountname, enabled, password, userinfo_id, salt)VALUES (1, 'admin', true, '181846944b54ceef5414237e29678a3b', 1, 'b2d09fdf644c4fd987b3b3bff3e20bfe');
--权限分配
INSERT INTO shiro_user_role(role_id, user_id)VALUES (1, 1);