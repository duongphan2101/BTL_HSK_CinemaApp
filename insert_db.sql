INSERT INTO EmployeeType (position) VALUES 
(N'Quản lý'),
(N'Nhân viên bán vé'),
(N'Kỹ thuật viên');

INSERT INTO Employee (name, type_id, birth_day, phone, email) VALUES 
(N'Nguyễn Văn A', 1, N'2003-01-10', N'0901234567', N'a.nguyen@example.com'),
(N'Tran Thị B', 2, N'2003-03-20', N'0902345678', N'b.tran@example.com'),
(N'Lê Văn C', 3, N'2003-06-15', N'0903456789', N'c.le@example.com');

-- Thêm trạng thái vào bảng MovieStatus
INSERT INTO MovieStatus (status)
VALUES (N'Đang chiếu'), (N'Ngừng chiếu');

INSERT INTO Movie (title, genre, duration, release_date, director, status_id, Img) VALUES
('Inception', N'Khoa học viễn tưởng', 148, '2010-07-16', 'Christopher Nolan', 1, N'inception.jfif'),  -- 1 là 'Đang chiếu'
('The Dark Knight', N'Hành động', 152, '2008-07-18', 'Christopher Nolan', 2, N'thedarknight.jfif'),     -- 2 là 'Không chiếu'
('Titanic', N'Lãng mạn', 195, '1997-12-19', 'James Cameron', 1, N'titanic.jfif'),
('Avatar', N'Khoa học viễn tưởng', 162, '2009-12-18', 'James Cameron', 1, N'avatar.jfif'),
('Parasite', N'Kinh dị', 132, '2019-05-30', 'Bong Joon-ho', 2, N'parasite.jfif'),
('Avengers: Endgame', N'Hành động', 181, '2019-04-26', 'Anthony Russo, Joe Russo', 1,  N'endgame.jfif'),
('The Matrix', N'Khoa học viễn tưởng', 136, '1999-03-31', 'Lana Wachowski, Lilly Wachowski', 2,  N'matrix.jfif'),
('Forrest Gump', N'Tâm lý', 142, '1994-07-06', 'Robert Zemeckis', 1,  N'gump.jfif'),
('Interstellar', N'Khoa học viễn tưởng', 169, '2014-11-07', 'Christopher Nolan', 1,  N'interstellar.jfif'),
('The Godfather', N'Tội phạm', 175, '1972-03-24', 'Francis Ford Coppola', 2,  N'godfather.jfif');


INSERT INTO Theater (name, location, capacity) VALUES 
(N'Phòng 1', N'Tầng 1', 30),
(N'Phòng 2', N'Tầng 2', 30),
(N'Phòng VIP', N'Tầng 3', 30);


-- Chèn các trạng thái ghế vào bảng SeatStatus với tiếng Việt
INSERT INTO SeatStatus (status_id, status_name)
VALUES (1, N'Trống'),  -- Trạng thái trống
       (2, N'Có khách');  -- Trạng thái có khách


INSERT INTO Account (username, password, employee_id) VALUES
('admin', '123', 1),   -- Tài khoản quản trị với username 'admin', mật khẩu 'adminPass123', liên kết với nhân viên có ID 1
('tranthib', '123', 2), -- Tài khoản quản lý với username 'manager', mật khẩu 'managerPass456', liên kết với nhân viên có ID 2
('levanc', '123', 3);    -- Tài khoản nhân viên với username 'staff1', mật khẩu 'staffPass789', liên kết với nhân viên có ID 3


--INSERT INTO Showtime (movie_id, theater_id, show_date, start_time) VALUES 
--(1, 1, N'2024-09-10', N'14:00'), -- The Matrix
--(2, 2, N'2024-09-11', N'16:30'), -- Inception
--(3, 3, N'2024-09-12', N'18:00'); -- Titanic

INSERT INTO Showtime (movie_id, theater_id, show_date, start_time) VALUES
(1, 1, '2024-09-11', '18:00'),  -- Phim với ID 1, Rạp với ID 1, Ngày chiếu 10/09/2024, Thời gian bắt đầu 18:00
(1, 2, '2024-09-11', '21:00'),  -- Phim với ID 1, Rạp với ID 2, Ngày chiếu 10/09/2024, Thời gian bắt đầu 21:00
(2, 1, '2024-09-11', '19:00'),  -- Phim với ID 2, Rạp với ID 1, Ngày chiếu 11/09/2024, Thời gian bắt đầu 19:00
(3, 3, '2024-09-12', '20:00'),  -- Phim với ID 3, Rạp với ID 3, Ngày chiếu 12/09/2024, Thời gian bắt đầu 20:00
(4, 2, '2024-09-13', '17:00');  -- Phim với ID 4, Rạp với ID 2, Ngày chiếu 13/09/2024, Thời gian bắt đầu 17:00


INSERT INTO Seat (theater_id, seat_number, status_id)
VALUES
	(1, 'A1', 1),
    (1, 'A2', 1),
    (1, 'A3', 1),
    (1, 'A4', 1),
    (1, 'A5', 1),
    (1, 'A6', 1),
    (1, 'A7', 1),
    (1, 'A8', 1),
    (1, 'A9', 1),
    (1, 'A10', 1),

	(2, 'A1', 1),
    (2, 'A2', 1),
    (2, 'A3', 1),
    (2, 'A4', 1),
    (2, 'A5', 1),
    (2, 'A6', 1),
    (2, 'A7', 1),
    (2, 'A8', 1),
    (2, 'A9', 1),
    (2, 'A10', 1),

    (3, 'A1', 1),
    (3, 'A2', 1),
    (3, 'A3', 1),
    (3, 'A4', 1),
    (3, 'A5', 1),
    (3, 'A6', 1),
    (3, 'A7', 1),
    (3, 'A8', 1),
    (3, 'A9', 1),
    (3, 'A10', 1),
    (3, 'A11', 1),
    (3, 'A12', 1),
    (3, 'A13', 1),
    (3, 'A14', 1),
    (3, 'A15', 1),
    (3, 'A16', 1),
    (3, 'A17', 1),
    (3, 'A18', 1),
    (3, 'A19', 1),
    (3, 'A20', 1),
    (3, 'A21', 1),
    (3, 'A22', 1),
    (3, 'A23', 1),
    (3, 'A24', 1),
    (3, 'A25', 1),
    (3, 'A26', 1),
    (3, 'A27', 1),
    (3, 'A28', 1),
    (3, 'A29', 1),
    (3, 'A30', 1);


