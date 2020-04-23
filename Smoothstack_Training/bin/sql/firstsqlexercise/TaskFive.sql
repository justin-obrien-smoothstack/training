SELECT lb.branchName, count(*) 'Books currently loaned out'
FROM tbl_library_branch lb
JOIN tbl_book_loans bl ON lb.branchId = bl.branchId
WHERE (bl.dateIn IS NULL OR bl.dateIn > CURTIME()) AND bl.dateOut <= CURTIME()
GROUP BY lb.branchId;