const books = [], numBooks = 20, numAuthors = 5, baseTitle = "Book #", baseAuthorName = "Author #";
var i;
for (i = 1; i <= numBooks; i++) {
    books.push({ id: i, title: baseTitle + i, author: baseAuthorName + Math.ceil(numAuthors * Math.random()) });
}