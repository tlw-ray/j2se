CREATE TABLE dbtree (
         nodeId int NOT NULL GENERATED ALWAYS AS IDENTITY,
         nodeOrder double default NULL ,
         parentPath varchar (64),
         userObject varchar(32),
		PRIMARY KEY (nodeId)
);

INSERT INTO dbtree (nodeOrder,parentPath,userObject) values (1024.0 , null);
INSERT INTO dbtree (nodeOrder,parentPath,userObject) values (2048.0 , null);
INSERT INTO dbtree (nodeOrder,parentPath,userObject) values (4096.0 , null);
INSERT INTO dbtree (nodeOrder,parentPath,userObject) values (1024.0 , '1,');
INSERT INTO dbtree (nodeOrder,parentPath,userObject) values (2048.0 , '1,');
INSERT INTO dbtree (nodeOrder,parentPath,userObject) values (1024.0 , '1,4,');
INSERT INTO dbtree (nodeOrder,parentPath,userObject) values (1024.0 , '2,');
