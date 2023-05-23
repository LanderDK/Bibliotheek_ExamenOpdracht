INSERT INTO users(username,password,enabled, max_aantal_favs)
VALUES('Baba', '$2a$10$dhIbfqE5r5p0G2v2.mJlh.93rpCsJRHhDlbZj78ixfdojKWdhhYJq', true, 7),('Lander', '$2a$10$PVJgEDbszumX2z1glP6PI.jWd5LuLcijjS5rZggicBoL/gqNHPg8C', true, 5);

INSERT INTO authorities(username,authority) 
VALUES ('Baba', 'ROLE_USER'),('Lander', 'ROLE_ADMIN');