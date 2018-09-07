CREATE DATABASE finalStore CHARACTER SET utf8 COLLATE utf8_general_ci;
CREATE USER 'finalStore_admin' IDENTIFIED BY 'p4ssw0rd';
GRANT ALL ON finalStore.* TO 'finalStore_admin';