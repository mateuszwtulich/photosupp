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
    VALUES (6, 'User has possibility to use CRUD operations on INDICATORS.', 'A_CRUD_INDICATORS');
INSERT INTO public.permission(
    id, description, name)
    VALUES (7, 'Standard user with no special permissions.', 'AUTH_USER');

INSERT INTO public.role(
	id, description, name)
	VALUES (0, 'Administrator with all permissions', 'ADMIN');
INSERT INTO public.role(
	id, description, name)
	VALUES (1, 'Manager with all permissions in order management', 'MANAGER');
INSERT INTO public.role(
	id, description, name)
	VALUES (2, 'Standard user with no special permissions', 'USER');

INSERT INTO public.role_permissions(
	permission_id, role_id)
	VALUES (0, 0);
INSERT INTO public.role_permissions(
	permission_id, role_id)
	VALUES (1, 0);
INSERT INTO public.role_permissions(
	permission_id, role_id)
	VALUES (2, 0);
INSERT INTO public.role_permissions(
	permission_id, role_id)
	VALUES (3, 0);
INSERT INTO public.role_permissions(
	permission_id, role_id)
	VALUES (4, 0);
INSERT INTO public.role_permissions(
	permission_id, role_id)
	VALUES (5, 0);
INSERT INTO public.role_permissions(
	permission_id, role_id)
	VALUES (6, 0);
	INSERT INTO public.role_permissions(
	permission_id, role_id)
	VALUES (7, 0);

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
	VALUES (7, 1);

INSERT INTO public.role_permissions(
	permission_id, role_id)
	VALUES (7, 2);

INSERT INTO public.account(
    id, username, password, email, is_activated)
    VALUES (1000000, 'user1', '$2y$10$EqGHhmN8QZRvdLHaUv5wgeqezToyPK8gd1qxRHgnKUPtCKz2fRjkm', 'user1@test.com', false);

INSERT INTO public.account(
    id, username, password, email, is_activated)
    VALUES (1000001, 'user2', '$2y$10$EqGHhmN8QZRvdLHaUv5wgeqezToyPK8gd1qxRHgnKUPtCKz2fRjkm', 'user2@test.com', true);

INSERT INTO public.VERIFICATION_TOKEN(
    id, token, account_id)
    VALUES (1000000, 'token', 1000000);

INSERT INTO public.photosupp_user(
    id, name, surname, role_id, account_id)
    VALUES (1000000, 'NAME', 'SURNAME', 2, 1000000);

INSERT INTO public.photosupp_user(
    id, name, surname, role_id, account_id)
    VALUES (1000001, 'NAME2', 'SURNAME2', 2, 1000001);

INSERT INTO public.indicator(
    id, name, description, locale, base_amount, DOUBLE_PRICE)
    VALUES (1000000, 'Podroz sluzbowa', 'Paliwo, amortyzacja', 'pl', 10, 20);

INSERT INTO public.service(
    id, name, description, locale, base_price)
    VALUES (1000000, 'Film produktowy', 'Film produktow na bialym tle i odpowiednim oswietleniu', 'pl', 500);

INSERT INTO public.service(
    id, name, description, locale, base_price)
    VALUES (1000001, 'FOTOGRAFIA', 'PRODUKTOWA', 'pl', 1500);

INSERT INTO public.SERVICE_INDICATORS(
    INDICATOR_ID, SERVICE_ID)
    VALUES (1000000,1000000);

INSERT INTO public.SERVICE_INDICATORS(
    INDICATOR_ID, SERVICE_ID)
    VALUES (1000000,1000001);

INSERT INTO public.address(
    id, CITY, STREET, BUILDING_NUMBER, APARTMENT_NUMBER, POSTAL_CODE)
    VALUES (1000000, 'Wroclaw', 'Wroblewskiego', '27', null, '51-627');

INSERT INTO public.booking(
    id, NAME, DESCRIPTION, START_DATE, END_DATE, FINAL_PRICE, MODIFICATION_DATE, IS_CONFIRMED, ADDRESS_ID, USER_ID, SERVICE_ID)
    VALUES (1000000, 'Film dla TestCompany', 'Film produktowy z dojazdem', '2020-04-11', '2020-04-12', 1400, '2020-04-11', false, 1000000, 1000000, 1000000);

INSERT INTO public.price_indicator(
    INDICATOR_ID, BOOKING_ID, PRICE, AMOUNT)
    VALUES (1000000, 1000000, 400, 10);

INSERT INTO public.photosupp_order(
    ORDER_NUMBER, USER_ID, COORDINATOR_ID, STATUS, BOOKING_ID, CREATED_AT, PRICE)
    VALUES ('INVIU_00001', 1000000, 1000001, 'NEW', null, '2020-09-11', 1000);

INSERT INTO public.PHOTOSUPP_COMMENT(
    id, CONTENT, ORDER_ID, USER_ID, CREATED_AT)
    VALUES (1000000, 'Perfect, thanks!', 'INVIU_00001', 1000001, '2020-09-11');

INSERT INTO public.media_content(
    id, TYPE, URL, ORDER_ID)
    VALUES (1000000, 'IMAGE', 'https://sample.com/jpg1', 'INVIU_00001');