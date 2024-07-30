insert into roles(role_name) values ('Администратор');

insert into users(name, surname, birthday, role_id)
	values('Сергей', 'Шапоренко', '1987-12-27', 1);

insert into rules(rule_name) values ('Полные права');

insert into roles_rules(role_id, rule_id) values (1, 1);

insert into categories(category_name) values ('Категория');

insert into states(state_name) values ('Активный пользователь');

insert into items(item_name, user_id, category_id, state_id) values ('Элемент', 1, 1, 1);

insert into comments(comment_name, item_id) values ('Комментарий', 1);

insert into attachs(attach_name, item_id) values ('Присоединенный файл', 1);