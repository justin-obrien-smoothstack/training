SELECT bk.title, br.name, br.address
FROM tbl_book bk
JOIN tbl_book_loans bl ON bl.bookId = bk.bookId
JOIN tbl_borrower br ON bl.cardNo = br.cardNo
JOIN tbl_library_branch lb ON bl.branchId = lb.branchId
WHERE lb.branchName = 'Sharpstown' AND bl.dueDate = CURDATE();