SELECT b.title, bc.noOfCopies
FROM tbl_book b
JOIN tbl_book_copies bc ON b.bookId = bc.bookId
JOIN tbl_book_authors ba ON b.bookId = ba.bookId
JOIN tbl_author a ON ba.authorId = a.authorId
JOIN tbl_library_branch lb ON bc.branchId = lb.branchId
WHERE a.authorName = 'Stephen King' AND lb.branchName = 'Central';