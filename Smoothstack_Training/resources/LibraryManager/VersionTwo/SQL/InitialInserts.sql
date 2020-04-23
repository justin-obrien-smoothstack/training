INSERT INTO tbl_author (authorName)
	VALUES
		('Stephen King'), ('Author2'), ('Author3'), ('Author4'), ('Author5'), ('Author6'), ('Author7'), ('Author8'),
        ('Author9'), ('Author10'), ('Author11'), ('Author12'), ('Author13'), ('Author14'), ('Author15'), ('Author16'),
        ('Author17'), ('Author18'), ('Author19'), ('Author20');

INSERT INTO tbl_book (title)
	VALUES
		('The Lost Tribe'), ('Book2'), ('Book3'), ('Book4'), ('Book5'), ('Book6'), ('Book7'),
        ('Book8'), ('Book9'), ('Book10'), ('Book11'), ('Book12'), ('Book13'), ('Book14'),
        ('Book15'), ('Book16'), ('Book17'), ('Book18'), ('Book19'), ('Book20');

INSERT INTO tbl_book_authors (bookId, authorId)
	VALUES
		(1,2), (2,1), (3,3), (4,4), (5,5), (6,6), (7,7), (8,8), (9,9), (10,10), 
        (11,11), (12,12), (13,13), (14,14), (15,15), (16,16), (17,17), (18,18), (19,19), (20,20);

INSERT INTO tbl_borrower () VALUES ();

INSERT INTO tbl_genre () VALUES ();

INSERT INTO tbl_library_branch (branchName) VALUES ('Sharpstown'), ('Central');

INSERT INTO tbl_library_branch () VALUES ();

INSERT INTO tbl_publisher (publisherName) VALUES
	('Publisher1'), ('Publisher2'), ('Publisher3'), ('Publisher4'), ('Publisher5'),
    ('Publisher6'), ('Publisher7'), ('Publisher8'), ('Publisher9'), ('Publisher10'),
    ('Publisher11'), ('Publisher12'), ('Publisher13'), ('Publisher14'), ('Publisher15'),
    ('Publisher16'), ('Publisher17'), ('Publisher18'), ('Publisher19'), ('Publisher20');

INSERT INTO tbl_book_copies (bookId, branchId) VALUES
	(1,1), (20,20), (2,2), (3,3), (4,4), (5,5), (6,6), (7,7), (8,8), (9,9),
    (10,10), (11,11), (12,12), (13,13), (14,14), (15,15), (16,16), (17,17), (18,18), (19,19);

INSERT INTO tbl_book_genres (bookId, genre_id) VALUES
	(1,1), (2,2), (3,3), (4,4), (5,5), (6,6), (7,7), (8,8), (9,9), (10,10),
	(11,11), (12,12), (13,13), (14,14), (15,15), (16,16), (17,17), (18,18), (19,19), (20,20);

INSERT INTO tbl_book_loans (bookId, branchId, cardNo) VALUES
	(1,1,1), (2,2,2), (3,3,3), (4,4,4), (5,5,5), (6,6,6), (7,7,7), (8,8,8), (9,9,9), (10,10,10),
	(11,11,11), (12,12,12), (13,13,13), (14,14,14), (15,15,15), (16,16,16), (17,17,17),
    (18,18,18), (19,19,19), (20,20,20);