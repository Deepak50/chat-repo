INSERT INTO users(username, password, enabled)
	values('user',
		'pass',
		true
	);
	
INSERT INTO users(username, password, enabled)
	values('user2',
		'pass',
		true
	);
	
	
--INSERT INTO users(username, password, enabled)
--	values('admin',
--		'pass',
--		true
--	);
	
INSERT INTO authorities(username, authority)
	values('user',
		'ROLE_USER'
	);
	
INSERT INTO authorities(username, authority)
	values('user2',
		'ROLE_USER'
	);
	
--INSERT INTO authorities(username, authority)
--	values('admin',
--		'ROLE_ADMIN'
--	);