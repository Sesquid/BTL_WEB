INSERT INTO user(create_time, password, role, username)
VALUES (0, '$2a$12$rilc3OwPTPb2dduZ3GM9lulWj0BtgMZIaMcBXhh6.yfQvFXjQ0Oqy', 0, 'user1');
INSERT INTO user(create_time, password, role, username)
VALUES (0, '$2a$12$rilc3OwPTPb2dduZ3GM9lulWj0BtgMZIaMcBXhh6.yfQvFXjQ0Oqy', 0, 'user2');
INSERT INTO user(create_time, password, role, username)
VALUES (0, '$2a$12$rilc3OwPTPb2dduZ3GM9lulWj0BtgMZIaMcBXhh6.yfQvFXjQ0Oqy', 0, 'user3');
INSERT INTO user(create_time, password, role, username)
VALUES (0, '$2a$12$rilc3OwPTPb2dduZ3GM9lulWj0BtgMZIaMcBXhh6.yfQvFXjQ0Oqy', 0, 'user4');
INSERT INTO user(create_time, password, role, username)
VALUES (0, '$2a$12$rilc3OwPTPb2dduZ3GM9lulWj0BtgMZIaMcBXhh6.yfQvFXjQ0Oqy', 0, 'user5');
INSERT INTO user(create_time, password, role, username)
VALUES (0, '$2a$12$rilc3OwPTPb2dduZ3GM9lulWj0BtgMZIaMcBXhh6.yfQvFXjQ0Oqy', 0, 'user6');
INSERT INTO user(create_time, password, role, username)
VALUES (0, '$2a$12$rilc3OwPTPb2dduZ3GM9lulWj0BtgMZIaMcBXhh6.yfQvFXjQ0Oqy', 0, 'user7');
INSERT INTO user(create_time, password, role, username)
VALUES (0, '$2a$12$rilc3OwPTPb2dduZ3GM9lulWj0BtgMZIaMcBXhh6.yfQvFXjQ0Oqy', 0, 'user8');

INSERT INTO company(company_id, company_name, phone_number, tax_code)
VALUES ('1', 'MASIU', '1512673124', '0987 668 886');
INSERT INTO company(company_id, company_name, phone_number, tax_code)
VALUES ('2', 'FPT', '1987465319', '0985 131 444');
INSERT INTO company(company_id, company_name, phone_number, tax_code)
VALUES ('3', 'VIETTEL', '2318765438', '0971 231 421');
INSERT INTO company(company_id, company_name, phone_number, tax_code)
VALUES ('4', 'TOCOTOCO', '9823718649', '0973 875 123');
INSERT INTO company(company_id, company_name, phone_number, tax_code)
VALUES ('5', 'SUNHOUSE', '8472195891', '0912 764 888');
INSERT INTO company(company_id, company_name, phone_number, tax_code)
VALUES ('6', 'VINAPHONE', '7217261385', '0963 871 625');
INSERT INTO company(company_id, company_name, phone_number, tax_code)
VALUES ('7', 'BIOC', '8391771282', '0965 721 468');
INSERT INTO company(company_id, company_name, phone_number, tax_code)
VALUES ('8', 'NIKE', '1221785671', '0986 671 394');

INSERT INTO employees_company(employeeid, employee_name, date_of_birth,  phone_number, company_id)
VALUES('ECID001', 'Nguyen Quang Huy', '2002-08-20', '0978 612 371', 'CP001');
INSERT INTO employees_company(employeeid, employee_name, date_of_birth,  phone_number, company_id)
VALUES('ECID002', 'Dinh Ba Hung', '2002-12-8', '0979 312 876', 'CP001');
INSERT INTO employees_company(employeeid, employee_name, date_of_birth,  phone_number, company_id)
VALUES('ECID003', 'Nguyen Duc Anh', '2002-05-23', '0976 681 186', 'CP001');

INSERT INTO buildings(buildingid, building_name, address, floor_number, contact)
VALUES('BD001', 'Golden Palace', '96 Phùng Khoang - Nam Từ Liêm - Hà Nội', 7, '19002501');
INSERT INTO buildings(buildingid, building_name, address, floor_number, contact)
VALUES('BD002', 'Hồ gươm Plaza', '12 Nguyễn Trãi - Hà Đông - Hà Nội', 5, '19002308');
INSERT INTO buildings(buildingid, building_name, address, floor_number, contact)
VALUES('BD003', 'Grand Plaza', '111 - Trần Duy Hưng - Cầu Giấy - Hà Nội', 3, '19006868');
INSERT INTO buildings(buildingid, building_name, address, floor_number, contact)
VALUES('BD004', 'Lotte Center', '57 - Liễu Giai - Ba Đình - Hà Nội', 6, '19002061');