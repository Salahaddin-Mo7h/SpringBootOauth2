CREATE TABLE oauth_access_token(
	token_id varchar(256) NULL,
	token varbinary(16383) NULL,
	authentication_id varchar(256) NULL,
	user_name varchar(256) NULL,
	client_id varchar(256) NULL,
	authentication varbinary(16383) NULL,
	refresh_token varbinary(16383) NULL
)

CREATE TABLE oauth_client_details(
	client_id varchar(256) NOT NULL,
	resource_ids varchar(256) NULL,
	client_secret varchar(256) NULL,
	scope varchar(256) NULL,
	authorized_grant_types varchar(256) NULL,
	web_server_redirect_uri varchar(256) NULL,
	authorities varchar(256) NULL,
	access_token_validity int NULL,
	refresh_token_validity int NULL,
	additional_information varchar(4096) NULL,
	autoapprove varchar(256) NULL
)

CREATE TABLE oauth_refresh_token(
	token_id varchar(256) NULL,
	token varbinary(16383) NULL,
	authentication varbinary(16383) NULL
)

INSERT INTO users 
          (id
           ,account_non_expired
           ,account_non_locked
           ,credentials_non_expired
           ,email
           ,enabled
           ,password
           ,username)
     VALUES
           ( 1, 1,1, 1,'shohag.jivita@gmail.com', 1, '{bcrypt}$2a$10$MPNhwVdaRdep2pEarh0zg.dE3JBhYPnX8ppinP69MKhMv1FvPq9ui', 'dmcadmin')

INSERT INTO permission
           (id
           ,name)
     VALUES
           (1,'create_user')
           
INSERT INTO role
           (id
           ,name)
     VALUES
           (1 ,'ROLE_ADMIN')
           
INSERT INTO permission_role
           (role_id
           ,permission_id)
     VALUES
           (1 ,1)
           
INSERT INTO role_user
           (user_id
           ,role_id)
     VALUES
           (1 ,1)
           
INSERT INTO oauth_client_details
           (client_id
           ,resource_ids
           ,client_secret
           ,scope
           ,authorized_grant_types
           ,web_server_redirect_uri
           ,authorities
           ,access_token_validity
           ,refresh_token_validity
           ,additional_information
           ,autoapprove)
     VALUES
           ('mobile'
           ,'inventory,payment'
           ,'{bcrypt}$2a$10$gPhlXZfms0EpNHX0.HHptOhoFD1AoxSr/yUIdTqA8vtjeP4zi0DDu'
           ,'READ,WRITE'
           ,'authorization_code,password,refresh_token,implicit'
           ,'http://localhost:8080/code'
           ,null
           ,3600
           ,10000
           ,'{}'
           ,null)
