INSERT INTO books (title, name_author, date_release_book, price, isbn, cover)
VALUES ('7Navikov', 'S.Kovy', '1991-12-25', 32.00, '978-3-16-148410-0', 'SOFT'),
    ('GrafMonte', 'A.Dyma', '1896-10-18', 23.00, '978-3-16-148410-1', 'SOFT'),
    ('GunsSteelAndGerms', 'D.Diamond', '2003-06-17', 44.00, '978-3-16-148410-2', 'SOFT'),
    ('TheSubtleArtOfNotGivingAF', 'M.Mancon', '2016-09-13', 65.00, '978-3-16-148410-3', 'SOFT'),
    ('SmertIvanaIlicha', 'L.Tolstoy', '1886-11-05', 35.00, '978-3-16-148410-4', 'SOFT'),
    ('KamoGryadeshi', 'G.Synkevich', '1896-05-14', 73.00, '978-3-16-148410-5', 'SOFT'),
    ('HoreOtUma', 'A.Griboedov', '1824-08-24', 59.00, '978-3-16-148410-6', 'EXCLUSIVE'),
    ('Neirofitnes', 'R.Djandial', '2019-02-23', 31.00, '978-3-16-148411-0', 'EXCLUSIVE'),
    ('JiznBezGranic', 'N.Vuitich', '2010-04-20', 77.00, '978-3-16-148411-1', 'EXCLUSIVE'),
    ('1984', 'D.Oruall', '1949-05-28', 11.00, '978-3-16-148411-2', 'SOFT'),
    ('AnimalFarm', 'D.Oruall', '1945-09-17', 73.00, '978-3-16-148411-3', 'HARD'),
    ('ThreeMeninABoat(ToSayNothingOfTheDog)', 'D.Dgerom', '1889-07-13', 90.00, '978-3-16-148411-4', 'HARD'),
    ('Uliss', 'I.Achlabustin', '2015-08-26', 36.00, '978-3-16-148411-5', 'HARD'),
    ('Do Androids Dream of Electric Sheep?', 'P.Dick', '1968-06-03', 9.80, '978-3-16-148411-6', 'HARD'),
    ('Pineapple water for beautiful ladies', 'V.Pelevin', '2010-11-25', 12.20, '978-3-16-148412-0', 'HARD'),
    ('An Occurrence at Owl Creek Bridge', 'A.Birs', '1890-06-05', 24.30, '978-3-16-148412-1', 'HARD'),
    ('To Kill a Mockingbird', 'H.Li', '1960-06-11', 110.80, '978-3-16-148412-2', 'HARD'),
    ('What Doesn’t Kill Us', 'S.Carney', '2011-11-20', 26.00, '978-3-16-148412-3', 'HARD'),
    ('Sleeping Beauties', 'S.King', '2017-09-01', 45.70, '978-3-16-148412-4', 'HARD'),
    ('In search of the lost orpheus', 'A.Lurie', '1912-04-04', 84.30, '978-3-16-148412-5', 'HARD');

INSERT INTO users(name, last_name, email, password, role) 
VALUES ('Yauheni', 'Hlaholeu', 'jek94@gmail.com', '12qwaszx', 'ADMIN'),
    ('Uladzislau', 'Solovev', 'sol44@yandex.by', 'qazxsw21', 'USER'),
    ('Haliana', 'Sidoric', 'galina_sid@gmail.com', 'sid93LL', 'USER'),
    ('Lana', 'Dimidova', 'dlana@mail.ru', 'vfAz1234', 'MANAGER'),
    ('Andrey', 'Aksenov', 'AKsin@Gmail.com','12345678OOp', 'USER'),
    ('Nazar', 'Vahtongov', 'vagan@mail.ru', '333eeeddfd', 'USER'),
    ('Tatyana', 'Minikova', 'tMin@tut.by', 'trewrg', 'USER'),
    ('Michail', 'Marshalau', 'Mix2020@rambler.by', 'hgnboenoenernv', 'USER'),
    ('Francs', 'Nikiforof', 'rdko@mail.ru', 'roinrv', 'MANAGER'),
    ('Adi', 'Huseinov', 'gitler21@gmail.com', 'tirmid', 'USER'),
    ('Kristafor', 'Djigurda', 'Americo1789@mail.ru', '534rrr', 'USER'),
    ('Haliana', 'Dombrouskaya', 'hali-gali@yahoo.com', 'ddffgg445566', 'USER'),
    ('Katserina', 'Mudalovich', 'gali@moli.ru', 'irjfncv', 'USER'),
    ('Yauheni', 'Jimolost', 'tolick@rambler.ru', 'fkfldmc.', 'USER'),
    ('Marck', 'Shagal', 'Markusik@rambler.ru', 'wertyuhgf', 'MANAGER'),
    ('Ivan', 'Mandelshtamm', 'manid@Gmail.com', '324rewf', 'USER'),
    ('IoganSebostian', 'Bah', 'bahbah-12@mail.ru', '45tfe', 'USER'),
    ('Pavel', 'Krishtofsky', 'pashok98@mail.ru', 'rtyjgf', 'USER'),
    ('Artemiy', 'Potrahunchik', 'temavsem424@Gmail.com', '4567876543', 'USER'),
    ('Vlad', 'Topalov', 'topal34@mail.ru', '345ygf', 'USER'),
    ('Vlad', 'Marshalau', 'rigfd2020@rambler.by', 'srdfhgjthr', 'ADMIN');

INSERT INTO orders (user_id, timestamp, status)
    VALUES ((SELECT id FROM users WHERE name = 'Yauheni' AND last_name ='Hlaholeu'), '2022-08-22', 'IN_PROCESSING'),
	((SELECT id FROM users WHERE name = 'Uladzislau' AND last_name ='Solovev'), '2022-08-21', 'ASSEMBLED'),
	((SELECT id FROM users WHERE name = 'Lana' AND last_name ='Dimidova'), '2022-08-23', 'ASSEMBLED'),
	((SELECT id FROM users WHERE name = 'Andrey' AND last_name ='Aksenov'), '2022-08-24', 'SENT'),
	((SELECT id FROM users WHERE name = 'Haliana' AND last_name ='Sidoric'), '2022-08-22', 'SENT');

INSERT INTO orders_items(orders_id, book_id, quantity, price)
    VALUES ((SELECT id FROM orders WHERE user_id = (SELECT id FROM users WHERE name = 'Yauheni' AND last_name ='Hlaholeu')), (SELECT id FROM books WHERE title = '7Navikov'), 2, (SELECT price FROM books WHERE title = '7Navikov')),
    ((SELECT id FROM orders WHERE user_id = (SELECT id FROM users WHERE name = 'Uladzislau' AND last_name ='Solovev')), (SELECT id FROM books WHERE title = 'GrafMonte'), 1, (SELECT price FROM books WHERE title = 'GrafMonte')),
    ((SELECT id FROM orders WHERE user_id = (SELECT id FROM users WHERE name = 'Lana' AND last_name ='Dimidova')), (SELECT id FROM books WHERE title = 'GunsSteelAndGerms'), 1, (SELECT price FROM books WHERE title = 'GunsSteelAndGerms')),
    ((SELECT id FROM orders WHERE user_id = (SELECT id FROM users WHERE name = 'Andrey' AND last_name ='Aksenov')), (SELECT id FROM books WHERE title = 'TheSubtleArtOfNotGivingAF'), 3, (SELECT price FROM books WHERE title = 'TheSubtleArtOfNotGivingAF')),
    ((SELECT id FROM orders WHERE user_id = (SELECT id FROM users WHERE name = 'Haliana' AND last_name ='Sidoric')), (SELECT id FROM books WHERE title = 'SmertIvanaIlicha'), 5, (SELECT price FROM books WHERE title = 'SmertIvanaIlicha')),
    ((SELECT id FROM orders WHERE user_id = (SELECT id FROM users WHERE name = 'Yauheni' AND last_name ='Hlaholeu')), (SELECT id FROM books WHERE title = '1984'), 3, (SELECT price FROM books WHERE title = '1984'));

