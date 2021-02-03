INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

INSERT INTO users (username, email, password) VALUES
('adminek', 'adminek@gmail.com', 'pass1'),
('firstUser', 'firstuser@gmail.com', 'pass2'),
('secondUser', 'seconduser@gmail.com', 'pass3');

INSERT INTO users_roles (user_id, roles_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, roles_id) VALUES (1, 2);
INSERT INTO users_roles (user_id, roles_id) VALUES (2, 2);
INSERT INTO users_roles (user_id, roles_id) VALUES (3, 2);

INSERT  INTO galleries (id, galleryName) VALUES
(1, 'gallery1'), (2, 'gallery2');

INSERT INTO users_galleries(user_id, gallery_id) VALUES
(2,1), (3,2);

INSERT INTO galleries_photos(gallery_id, photo_id) VALUES
(1,1), (1,2), (1,3), (2,4), (2,5), (2,6);

INSERT INTO photos (id, photoName, imgUrl) VALUES
(1, 'photo1', '/img/photo1.jpg'),
(2, 'photo2', '/img/photo2.jpg'),
(3, 'photo3', '/img/photo3.jpg'),
(4, 'photo4', '/img/photo4.jpg'),
(5, 'photo5', '/img/photo5.jpg'),
(6, 'photo6', '/img/photo6.jpg');




