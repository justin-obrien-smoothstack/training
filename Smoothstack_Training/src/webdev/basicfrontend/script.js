const books = [], numBooks = 20, numAuthors = 5, baseTitle = "Book #",
    baseAuthorName = "Author #", booksPerPage = 6, baseDivId = "result",
    numPages = Math.ceil(numBooks / booksPerPage),
    nav = document.getElementById("nav"),
    body = document.getElementById("body"),
    pageNum = document.getElementById("page-number");
var i, currentPage = 1, div;

function makeBookText(book) {
    if (book === undefined) return "\n\n\n";
    return "ID number: " + book.id + "\nTitle: " + book.title +
        "\nAuthor: " + book.author;
}

function previousPage() {
    if (currentPage === 1) return;
    var startingIndex = (currentPage - 2) * booksPerPage;
    for (i = 0; i < booksPerPage; i++) {
        document.getElementById(baseDivId + i).innerText =
            makeBookText(books[startingIndex + i]);
    }
    pageNum.innerText = "Page " + --currentPage + " of " + numPages;
}

function nextPage() {
    if (currentPage === numPages) return;
    var startingIndex = currentPage * booksPerPage;
    for (i = 0; i < booksPerPage; i++) {
        document.getElementById(baseDivId + i).innerText =
            makeBookText(books[startingIndex + i]);
    }
    pageNum.innerText = "Page " + ++currentPage + " of " + numPages;
}

for (i = 1; i <= numBooks; i++) {
    books.push({
        id: i, title: baseTitle + i, author: baseAuthorName +
            Math.ceil(numAuthors * Math.random())
    });
}
pageNum.innerText = "Page " + currentPage + " of " + numPages;
body.insertBefore(document.createElement("hr"), nav)
for (i = 0; i < booksPerPage; i++) {
    div = document.createElement("div");
    div.innerText = makeBookText(books[i]);
    div.id = baseDivId + i;
    body.insertBefore(div, nav);
    body.insertBefore(document.createElement("hr"), nav);
}