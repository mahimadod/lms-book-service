INSERT INTO roles (id, name) VALUES
(1, 'ADMIN'),
(2, 'LIBRARIAN'),
(3, 'MEMBER'),
(4, 'GUEST'),
(5, 'SUPERADMIN');

INSERT INTO users (id, username, password, role_id) VALUES
(1, 'admin', 'adminpass', 1),
(2, 'librarian1', 'libpass1', 2),
(3, 'member1', 'mempass1', 3),
(4, 'guest1', 'guestpass', 4),
(5, 'superadmin', 'superpass', 5);

INSERT INTO authors (id, name) VALUES
(1, 'J.K. Rowling'),
(2, 'George R.R. Martin'),
(3, 'J.R.R. Tolkien'),
(4, 'Agatha Christie'),
(5, 'Isaac Asimov');

INSERT INTO publishers (id, name) VALUES
(1, 'Penguin Random House'),
(2, 'HarperCollins'),
(3, 'Macmillan Publishers'),
(4, 'Simon & Schuster'),
(5, 'Hachette Book Group');

INSERT INTO categories (id, name) VALUES
(1, 'Fantasy'),
(2, 'Science Fiction'),
(3, 'Mystery'),
(4, 'Romance'),
(5, 'Non-Fiction');

INSERT INTO books (id, title, isbn, author_id, publisher_id, category_id, total_copies, available_copies) VALUES
(1, 'Harry Potter and the Sorcerers Stone', '9780439708180', 1, 1, 1, 10, 8),
(2, 'A Game of Thrones', '9780553573404', 2, 2, 1, 8, 6),
(3, 'The Hobbit', '9780547928227', 3, 3, 1, 7, 7),
(4, 'Murder on the Orient Express', '9780062073501', 4, 4, 3, 5, 4),
(5, 'Foundation', '9780553293357', 5, 5, 2, 6, 6);

INSERT INTO members (id, name, email, phone) VALUES
(1, 'Alice Smith', 'alice@example.com', '1234567890'),
(2, 'Bob Johnson', 'bob@example.com', '2345678901'),
(3, 'Carol Williams', 'carol@example.com', '3456789012'),
(4, 'David Brown', 'david@example.com', '4567890123'),
(5, 'Eve Davis', 'eve@example.com', '5678901234');

INSERT INTO loans (id, member_id, loan_date, due_date, return_date) VALUES
(1, 1, '2025-05-10', '2025-05-20', NULL),
(2, 2, '2025-05-11', '2025-05-21', NULL),
(3, 3, '2025-05-12', '2025-05-22', NULL),
(4, 4, '2025-05-13', '2025-05-23', '2025-05-17'),
(5, 5, '2025-05-14', '2025-05-24', '2025-05-18');

INSERT INTO loan_items (id, loan_id, book_id) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 2, 3),
(4, 3, 4),
(5, 4, 5);

INSERT INTO reservations (id, member_id, book_id, reservation_date) VALUES
(1, 1, 3, '2025-05-10'),
(2, 2, 4, '2025-05-11'),
(3, 3, 5, '2025-05-12'),
(4, 4, 1, '2025-05-13'),
(5, 5, 2, '2025-05-14');
