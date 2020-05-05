const books = [], numBooks = 20, numAuthors = 5, baseTitle = "Book #",
    baseAuthorName = "Author #", booksPerPage = 6, baseDivId = "result",
    numPages = Math.ceil(numBooks / booksPerPage), pageNumId = "page-number";
var i, currentPage = 1;
for (i = 1; i <= numBooks; i++) {
    books.push({
        id: i, title: baseTitle + i, author: baseAuthorName +
            Math.ceil(numAuthors * Math.random())
    });
}
document.getElementById(pageNumId).innerHTML =
    "Page " + currentPage + " of " + numPages;

function previousPage() {
    if (currentPage === 1) return;
    for (i = 1; i < booksPerPage; i++) {
        
    }
}