SELECT b.name FROM tbl_borrower b
JOIN tbl_book_loans bl ON b.cardNo = bl.cardNo
