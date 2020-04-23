SELECT *
FROM (SELECT b.name, b.address, COUNT(*) booksCurrentlyCheckedOut
	FROM tbl_borrower b
	JOIN (
		SELECT cardNo FROM tbl_book_loans
		WHERE (dateIn IS NULL OR dateIn > CURTIME()) AND dateOut <= CURTIME()
	) bl ON b.cardNo = bl.cardNo
    GROUP BY b.cardNo
) c
WHERE booksCurrentlyCheckedOut > 5;