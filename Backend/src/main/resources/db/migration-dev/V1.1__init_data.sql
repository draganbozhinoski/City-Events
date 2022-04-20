insert into city_users(username,email,name,password,phone_number,type) values
('dbozhinoski','dragan@sorsix.com','Dragan','123',071519218,'ROLE_ADMIN'),
('dbjov','dragan@sorsix.com','Jovan','123',071519218,'ROLE_ADMIN'),
('jovdb','urda@sorsix.com','Stojan','123',071519218,'ROLE_GUEST'),
('jovie','jovan@sorsix.com','Bojan','123',071519218,'ROLE_GUEST'),
('dboie','mevlar@sorsix.com','Xhemil','123',071519218,'ROLE_GUEST');
insert into city_locales(logo_url, name, num_tables, type,owner_id) values
('https://drdrew.com/wp-content/uploads/2018/01/opium-2018-1.jpg','Opium',50,'NIGHT_CLUB',1),
('http://m.grish.co.kr/web/product/medium/202104/62eab208de67e6cb9a9e5c2d6ce00e76.jpg','Grish',15,'COFFEE_SHOP',2),
('https://yt3.ggpht.com/ytc/AKedOLSMlpEwhaHspHB8xQmkFbSSWpa5c8mY8BtXtHL1Rg=s900-c-k-c0x00ffffff-no-rj','Simple',25,'COFFEE_SHOP',3),
('https://play-lh.googleusercontent.com/k5eHiOSJKPOYoHITJdF9reVhEQoMKBwk0EYknLlP6WtX40VtRi5nEaONVt5EGJOx9CQ','Vibe bar & food',30,'LUNCH_BAR',4),
('https://assets.entrepreneur.com/content/3x2/2000/20161024211709-GettyImages-138412921.jpeg','Boss',20,'RESTAURANT',5);
insert into city_images(name,type) values
('Test-photo','jpeg');
insert into city_events(adult, city, covid_certificate, date, description, logo_url, name, num_reservations,locale_id,image_id) values
(true,'Tetovo',true,'2022-12-12T20:00:03','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.','https://i.imgur.com/MXIGQH0.jpg','Hurricane',23,1,1),
(false,'Tetovo',true,'2022-12-12T20:00:03','','https://i.imgur.com/m6HBssL.jpg','DJ Smallz hiphop party',50,1,1),
(true,'Tetovo',false,'2022-12-12T20:00:03','Filip Pecovski','https://i.imgur.com/sVWu92A.jpg','Filip Pecovski',23,1,1),
(true,'Tetovo',true,'2022-12-12T20:00:03','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.','https://i.imgur.com/LAnAbkZ.jpg','NuCCi BeBo',35,1,1),
(false,'Tetovo',true,'2022-12-12T20:00:03','Lorem','https://i.imgur.com/ceLtdVG.jpg','Birthday Party',22,1,1),
(false,'Tetovo',false,'2022-12-12T20:00:03','Lorem','https://i.imgur.com/eGHwA23.jpg','Antonio Pejovski',55,1,1);

