INSERT INTO authors ( name) VALUES
( 'J.K. Rowling'),
( 'George R.R. Martin'),
( 'J.R.R. Tolkien'),
( 'Agatha Christie'),
( 'Isaac Asimov');

INSERT INTO publishers ( name) VALUES
( 'Penguin Random House'),
( 'HarperCollins'),
( 'Macmillan Publishers'),
( 'Simon & Schuster'),
( 'Hachette Book Group');

INSERT INTO categories ( name) VALUES
( 'Fantasy'),
( 'Science Fiction'),
( 'Mystery'),
( 'Romance'),
( 'Non-Fiction');

INSERT INTO books ( title, isbn, author_id, publisher_id, category_id, total_copies, available_copies) VALUES
( 'Harry Potter and the Sorcerers Stone', '9780439708180', 1, 1, 1, 10, 8),
( 'A Game of Thrones', '9780553573404', 2, 2, 1, 8, 6),
( 'The Hobbit', '9780547928227', 3, 3, 1, 7, 7),
( 'Murder on the Orient Express', '9780062073501', 4, 4, 3, 5, 4),
( 'Foundation', '9780553293357', 5, 5, 2, 6, 6);

