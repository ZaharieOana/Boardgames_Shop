INSERT INTO roles(id, role)
VALUES (1, 'ADMIN'),
       (2, 'USER');

INSERT INTO users(username, email_address, first_name, last_name, password)
VALUES ('Admin', 'admin@a.com', 'Pop', 'Vlad', '$2a$10$FVvZNPIIP3taMKdQqwwWsejbiH1DpT/0/PbBS933b73JewnHag6hK');

INSERT INTO users_roles(users_id, roles_id) VALUES (1, 1);