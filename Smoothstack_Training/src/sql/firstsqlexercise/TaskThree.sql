SELECT name FROM tbl_borrower
WHERE cardNo NOT IN (
	SELECT b.cardNo FROM tbl_borrower b
    JOIN tbl_book_loans bl ON b.cardNo = bl.cardNo
    WHERE (bl.dateIn IS NULL OR bl.dateIn > CURTIME()) AND bl.dateOut <= CURTIME()
);