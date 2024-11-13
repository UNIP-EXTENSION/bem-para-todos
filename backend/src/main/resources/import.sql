------ PARAMETERIZATION ------

-- insert into tb_country
INSERT INTO tb_country (id, name, name_portuguese, code) VALUES (1, 'Brazil', 'Brasil', 'BR');

-- Insert into tb_event_theme
INSERT INTO tb_event_theme (id, theme) VALUES ('17b63b68-26b3-4b4b-b153-afb9fcf37989', 'Playstation');
INSERT INTO tb_event_theme (id, theme) VALUES ('76d1203b-9923-4865-9172-8208c4e20e9a', 'Tech Conference');

-- Insert into tb_dress_code
INSERT INTO tb_dress_code (id, description) VALUES ('3aec1409-cc84-4f5d-ab11-0788d15f04c0', 'Casual');
INSERT INTO tb_dress_code (id, description) VALUES ('a63f67c8-0aed-4ea4-a86d-8a03ead3f6e4', 'Formal');

------ USER ------

-- insert into tb_user
INSERT INTO tb_user (id,first_name, last_name, email, password) VALUES ('c73b9a9f-1518-44e3-bb55-2e56eab6717a', 'Alex', 'Brown', 'alex@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (id,first_name, last_name, email, password) VALUES ('28732672-985c-415b-a94c-b1254bd11261','Maria', 'Green', 'maria@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

-- insert into tb_role
INSERT INTO tb_role (id, authority) VALUES ('96960b12-9eeb-43b0-95fd-3bf27680122b', 'ROLE_OPERATOR');
INSERT INTO tb_role (id, authority) VALUES ('407a8fcc-bf19-4bf8-8d26-9782f3d9bc7e', 'ROLE_ADMIN');

-- insert into tb_user_role
INSERT INTO tb_user_role (user_id, role_id) VALUES ('c73b9a9f-1518-44e3-bb55-2e56eab6717a', '96960b12-9eeb-43b0-95fd-3bf27680122b');
INSERT INTO tb_user_role (user_id, role_id) VALUES ('28732672-985c-415b-a94c-b1254bd11261', '96960b12-9eeb-43b0-95fd-3bf27680122b');
INSERT INTO tb_user_role (user_id, role_id) VALUES ('28732672-985c-415b-a94c-b1254bd11261', '407a8fcc-bf19-4bf8-8d26-9782f3d9bc7e');

------ ADDRESS ------

-- insert into tb_address
INSERT INTO tb_address(id, line1, line2, line3, city, region, postal_code, country_id) VALUES('7942f7d1-1263-4fab-819e-399055861d33', 'Alameda Grajaú, 248', 'Apto 63', 'Alphaville', 'Barueri', 'SP', '06453-016', 1);

------ EVENT ------

-- insert into tb_event
-- Insert1
INSERT INTO tb_event (id, user_id, name, description, start_date, end_date)VALUES ('a2776f8c-a58e-4569-b715-27c20bddaa9d', 'c73b9a9f-1518-44e3-bb55-2e56eab6717a', 'Oscar','Para comemorar, venha participar de novo evento de Oscar para ONGs.','2024-03-09T06:00:00Z', '2024-03-09T18:00:00Z');

-- Insert2
INSERT INTO tb_event (id, user_id, name, description, start_date, end_date)VALUES ('5dccd439-4320-4951-bc54-8f3e246cc2a9', '28732672-985c-415b-a94c-b1254bd11261', 'Dias das Crianças','Feliz dia das crianças, para comemorar venha participar de nosso evento!','2024-03-10T06:00:00Z', '2024-03-10T18:00:00Z');

-- insert into tb_event_detail
-- Insert1
INSERT INTO tb_event_detail (id, event_id, dress_code_id, event_theme_id, required_item) VALUES ('a59c3fe7-d0c4-49f6-a49c-003ae6e2e65c', 'a2776f8c-a58e-4569-b715-27c20bddaa9d', '3aec1409-cc84-4f5d-ab11-0788d15f04c0', '17b63b68-26b3-4b4b-b153-afb9fcf37989', 'Required to bring PS5 controller to test available games');

-- Insert2
INSERT INTO tb_event_detail (id, event_id,dress_code_id, event_theme_id) VALUES ('714fb9fa-1c07-4a99-99f4-294811ae3944', '5dccd439-4320-4951-bc54-8f3e246cc2a9', 'a63f67c8-0aed-4ea4-a86d-8a03ead3f6e4', '76d1203b-9923-4865-9172-8208c4e20e9a');

-- insert into tb_event_address
-- Insert1
INSERT INTO tb_event_address (event_id, address_id)VALUES ('a2776f8c-a58e-4569-b715-27c20bddaa9d', '7942f7d1-1263-4fab-819e-399055861d33');

-- Insert2
INSERT INTO tb_event_address (event_id, address_id)VALUES ('5dccd439-4320-4951-bc54-8f3e246cc2a9', '7942f7d1-1263-4fab-819e-399055861d33');

------ FILE ------

INSERT INTO tb_file (id,file_name,file_type,file_size,description,url,upload_date) VALUES('123e4567-e89b-12d3-a456-426614174000','example.png','application/image',1024,'An example Image file','https://res.cloudinary.com/dripsiabv/image/upload/v1731455883/Rectangle_15_m9sly0.png',CURRENT_TIMESTAMP());

INSERT INTO tb_file (id,file_name,file_type,file_size,description,url,upload_date) VALUES('78c9e50f-9fe6-4ded-9c8c-fb4721b1433a','example.png','application/image',1024,'An example Image file','https://res.cloudinary.com/dripsiabv/image/upload/v1731455887/Rectangle_16_v3city.png',CURRENT_TIMESTAMP());

-- insert into tb_event_file
-- Insert1
INSERT INTO tb_event_file (event_id, file_id) VALUES ('a2776f8c-a58e-4569-b715-27c20bddaa9d', '123e4567-e89b-12d3-a456-426614174000');

-- Insert2
INSERT INTO tb_event_file (event_id, file_id) VALUES ('5dccd439-4320-4951-bc54-8f3e246cc2a9', '78c9e50f-9fe6-4ded-9c8c-fb4721b1433a');
