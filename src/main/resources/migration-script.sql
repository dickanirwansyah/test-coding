--level 1
insert into t_permissions(id, code, deleted, level, name, glyphicon, parent_id, path) values ('02f678c4-8ba9-4230-bd36-5ae2a6d6a3fb', 'LVL101', 0, 1, 'Home', 'glyphicon-home', null, '/');
insert into t_permissions(id, code, deleted, level, name, glyphicon, parent_id, path) values ('02f678c4-8ba9-4230-bd36-5ae2a6d6a3fa', 'LVL102', 0, 1, 'Product', 'glyphicon-product', null, '/product');
insert into t_permissions(id, code, deleted, level, name, glyphicon, parent_id, path) values ('02f678c4-8ba9-4230-bd36-5ae2a6d6a3fc', 'LVL102', 0, 1, 'Accounts', 'glyphicon-accounts', null, '/accounts');
insert into t_permissions(id, code, deleted, level, name, glyphicon, parent_id, path) values ('02f678c4-8ba9-4230-bd36-5ae2a6d6a3fd', 'LVL103', 0, 1, 'Orders', 'glyphicon-orders', null, '/orders');

--level 2
insert into t_permissions(id, code, deleted, level, name, glyphicon, parent_id, path) values ('12f678c4-8ba9-4230-bd36-5ae2a6d6a3fd', 'LVL203', 0, 2, 'Roles', 'glyphicon-roles', '02f678c4-8ba9-4230-bd36-5ae2a6d6a3fc', '/accounts/roles');
insert into t_permissions(id, code, deleted, level, name, glyphicon, parent_id, path) values ('22f678c4-8ba9-4230-bd36-5ae2a6d6a3fd', 'LVL204', 0, 2, 'Permissions', 'glyphicon-permissions', '02f678c4-8ba9-4230-bd36-5ae2a6d6a3fc', '/accounts/permissions');
insert into t_permissions(id, code, deleted, level, name, glyphicon, parent_id, path) values ('32f678c4-8ba9-4230-bd36-5ae2a6d6a3fd', 'LVL205', 0, 2, 'Manage Accounts', 'glyphicon-accounts', '02f678c4-8ba9-4230-bd36-5ae2a6d6a3fc', '/accounts/manage-accounts');
