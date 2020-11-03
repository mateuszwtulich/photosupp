INSERT INTO public.permission(
	id, description, name)
	VALUES (0, 'User has possibility to use CRUD operations one every functionality.', 'A_CRUD_SUPER');
INSERT INTO public.permission(
	id, description, name)
	VALUES (1, 'User has possibility to use CRUD operations on USERS.', 'A_CRUD_USERS');
INSERT INTO public.permission(
	id, description, name)
	VALUES (2, 'User has possibility to use CRUD operations on ROLES.', 'A_CRUD_ROLES');
INSERT INTO public.permission(
	id, description, name)
	VALUES (3, 'User has possibility to use CRUD operations on SERVICES and INDICATORS.', 'A_CRUD_SERVICES');
INSERT INTO public.permission(
    id, description, name)
    VALUES (4, 'User has possibility to use CRUD operations on BOOKINGS.', 'A_CRUD_BOOKINGS');
INSERT INTO public.permission(
    id, description, name)
    VALUES (5, 'User has possibility to use CRUD operations on ORDERS.', 'A_CRUD_ORDERS');
INSERT INTO public.permission(
    id, description, name)
    VALUES (6, 'Standard user with no special permissions.', 'AUTH_USER');

-- INSERT INTO public.role(
-- 	id, description, name)
-- 	VALUES (0, 'Administrator with all permissions', 'ADMIN');
INSERT INTO public.role(
	id, description, name)
	VALUES (1, 'Manager with all permissions in order management', 'MANAGER');
INSERT INTO public.role(
	id, description, name)
	VALUES (2, 'Standard user with no special permissions', 'USER');

-- INSERT INTO public.role_permissions(
-- 	permission_id, role_id)
-- 	VALUES (0, 0);
-- INSERT INTO public.role_permissions(
-- 	permission_id, role_id)
-- 	VALUES (1, 0);
-- INSERT INTO public.role_permissions(
-- 	permission_id, role_id)
-- 	VALUES (2, 0);
-- INSERT INTO public.role_permissions(
-- 	permission_id, role_id)
-- 	VALUES (3, 0);
-- INSERT INTO public.role_permissions(
-- 	permission_id, role_id)
-- 	VALUES (4, 0);
-- INSERT INTO public.role_permissions(
-- 	permission_id, role_id)
-- 	VALUES (5, 0);
-- INSERT INTO public.role_permissions(
-- 	permission_id, role_id)
-- 	VALUES (6, 0);
--
INSERT INTO public.role_permissions(
	permission_id, role_id)
	VALUES (1, 1);
INSERT INTO public.role_permissions(
	permission_id, role_id)
	VALUES (3, 1);
INSERT INTO public.role_permissions(
	permission_id, role_id)
	VALUES (4, 1);
INSERT INTO public.role_permissions(
	permission_id, role_id)
	VALUES (5, 1);
INSERT INTO public.role_permissions(
	permission_id, role_id)
	VALUES (6, 1);

INSERT INTO public.role_permissions(
	permission_id, role_id)
	VALUES (6, 2);

INSERT INTO public.account(
    id, username, password, email, is_activated)
    VALUES (1, 'user1', 'passw0rd', 'user1@test.com', false);

INSERT INTO public.account(
    id, username, password, email, is_activated)
    VALUES (2, 'user2', 'passw0rd', 'user2@test.com', true);

INSERT INTO public.VERIFICATION_TOKEN(
    id, token, account_id)
    VALUES (1, 'token', 1);

INSERT INTO public.photosupp_user(
    id, name, surname, role_id, account_id)
    VALUES (1, 'NAME', 'SURNAME', 2, 1);

INSERT INTO public.photosupp_user(
    id, name, surname, role_id, account_id)
    VALUES (2, 'NAME2', 'SURNAME2', 2, 2);

INSERT INTO public.indicator(
    id, name, description, base_amount)
    VALUES (1, 'PODROZ SLUZBOWA', '100KM', 50);

INSERT INTO public.service(
    id, name, description, base_price)
    VALUES (1, 'FILM', 'PRODUKTOWY', 1000);

INSERT INTO public.service(
    id, name, description, base_price)
    VALUES (2, 'FOTOGRAFIA', 'PRODUKTOWA', 1500);