-- This gets run every time the application starts up, but I have no idea how this happens
insert into user values (10001, sysdate(), 'Jarrod', 'password1'); 
insert into user (id, dob, name, password) values (10002, sysdate(), 'Tessa', 'password2');
insert into user (id, dob, name, password) values (10003, sysdate(), 'Ben', 'password3');

insert into post values (11001, 'My first post: UserId: 1', 10001);
insert into post (id, message, user_id) values (11002, 'My second post: UserId: 1', 10001);
insert into post (id, message, user_id) values (11003, 'My first post: UserId: 2', 10002);