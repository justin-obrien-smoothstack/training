SELECT noOfCopies, lb.branchName, b.title FROM tbl_book_copies bc
INNER JOIN tbl_library_branch lb ON bc.branchId = lb.branchId
INNER JOIN tbl_book b ON bc.bookId = b.bookId
WHERE b.title = 'The Lost Tribe' AND lb.branchName = 'Sharpstown';