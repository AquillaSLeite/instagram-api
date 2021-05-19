INSERT INTO public.users(email, gender, name, phone, username) VALUES ('teste1@hotmail.com', 'M', 'Teste 1', '5582999999999', 'teste1.teste');
INSERT INTO public.users(email, gender, name, phone, username) VALUES ('teste2@hotmail.com', 'F', 'Teste 2', '5582999999999', 'teste2.teste');
INSERT INTO public.users(email, gender, name, phone, username) VALUES ('teste3@hotmail.com', 'M', 'Teste 3', '5582999999999', 'teste3.teste');
INSERT INTO public.users(email, gender, name, phone, username) VALUES ('teste4@hotmail.com', 'M', 'Teste 4', '5582999999999', 'teste4.teste');
INSERT INTO public.users(email, gender, name, phone, username) VALUES ('teste5@hotmail.com', 'M', 'Teste 5', '5582999999999', 'teste5.teste');
INSERT INTO public.users(email, gender, name, phone, username) VALUES ('teste6@hotmail.com', 'F', 'Teste 6', '5582999999999', 'teste6.teste');
INSERT INTO public.users(email, gender, name, phone, username) VALUES ('teste7@hotmail.com', 'F', 'Teste 7', '5582999999999', 'teste7.teste');

INSERT INTO public.follow(user_id, following) VALUES (1, 2);
INSERT INTO public.follow(user_id, following) VALUES (1, 3);
INSERT INTO public.follow(user_id, following) VALUES (1, 7);
INSERT INTO public.follow(user_id, following) VALUES (1, 4);

INSERT INTO public.follow(user_id, following) VALUES (2, 1);
INSERT INTO public.follow(user_id, following) VALUES (2, 6);
INSERT INTO public.follow(user_id, following) VALUES (2, 5);
INSERT INTO public.follow(user_id, following) VALUES (2, 3);

INSERT INTO public.follow(user_id, following) VALUES (7, 6);
INSERT INTO public.follow(user_id, following) VALUES (7, 4);