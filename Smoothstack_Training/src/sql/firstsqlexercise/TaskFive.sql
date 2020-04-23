SELECT lb.branchName, count(*) 'Books currently loaned out'
FROM tbl_library_branch lb
JOIN tbl_book_loans bl ON lb.branchId = bl.branchId
WHERE (bl.dateIn IS NULL OR bl.dateIn > CURDATE()) AND bl.dateOut <= CURDATE()
GROUP BY lb.branchId