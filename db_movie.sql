--create database db_movie 

--use db_movie



-- Bảng quản lý loại nhân viên (vị trí làm việc)
CREATE TABLE EmployeeType (
    type_id INT PRIMARY KEY IDENTITY(1,1),
    position NVARCHAR(100) NOT NULL -- Vị trí làm việc (vd: Quản lý, Nhân viên bán vé, Kỹ thuật viên)
);

-- Bảng quản lý nhân viên
CREATE TABLE Employee (
    employee_id INT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(255) NOT NULL,
    type_id INT FOREIGN KEY REFERENCES EmployeeType(type_id), -- Liên kết với bảng EmployeeType
    birth_day NVARCHAR(10), -- Ngày sinh, định dạng chuỗi (vd: 'yyyy-MM-dd')
    phone NVARCHAR(20),
    email NVARCHAR(255),
);

CREATE TABLE MovieStatus (
    status_id INT PRIMARY KEY IDENTITY(1,1),
    status NVARCHAR(20) CHECK (status IN (N'Đang chiếu', N'Ngừng chiếu')) NOT NULL
);

CREATE TABLE Movie (
    movie_id INT PRIMARY KEY IDENTITY(1,1),
    title NVARCHAR(255) NOT NULL,
    genre NVARCHAR(100),
    duration INT,  -- Thời lượng phim tính bằng phút
    release_date NVARCHAR(10), -- Ngày phát hành dưới dạng chuỗi (vd: 'yyyy-MM-dd')
    director NVARCHAR(255),
    status_id INT, -- Thêm cột status_id làm khóa ngoại
    FOREIGN KEY (status_id) REFERENCES MovieStatus(status_id) -- Khóa ngoại liên kết với MovieStatus
);

ALTER TABLE Movie
ADD Img NVARCHAR(255);


-- Bảng quản lý phòng chiếu
CREATE TABLE Theater (
    theater_id INT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(100) NOT NULL,
    location NVARCHAR(255),
    capacity INT -- Số lượng ghế
);

-- Bảng quản lý suất chiếu
CREATE TABLE Showtime (
    showtime_id INT PRIMARY KEY IDENTITY(1,1),
    movie_id INT FOREIGN KEY REFERENCES Movie(movie_id),
    theater_id INT FOREIGN KEY REFERENCES Theater(theater_id),
    show_date NVARCHAR(10), -- Ngày chiếu dưới dạng chuỗi (vd: 'yyyy-MM-dd')
    start_time NVARCHAR(5) -- Thời gian bắt đầu dưới dạng chuỗi (vd: 'HH:mm')
);

-- Bảng quản lý vé
CREATE TABLE Ticket (
    ticket_id INT PRIMARY KEY IDENTITY(1,1),
    showtime_id INT FOREIGN KEY REFERENCES Showtime(showtime_id),
    seat_number NVARCHAR(10),
    price FLOAT, -- Giá vé
    purchase_time NVARCHAR(255) -- Thời gian mua vé dưới dạng chuỗi
);

ALTER TABLE Ticket
ADD theater_id INT;
ALTER TABLE Ticket
ADD CONSTRAINT FK_Ticket_Theater
FOREIGN KEY (theater_id) REFERENCES Theater(theater_id);



-- Bảng quản lý hóa đơn
CREATE TABLE Invoice (
    invoice_id INT PRIMARY KEY IDENTITY(1,1),
    total_amount FLOAT, -- Tổng tiền thanh toán
    purchase_time NVARCHAR(19) DEFAULT CONVERT(NVARCHAR, GETDATE(), 120), -- Thời gian thanh toán
    employee_id INT FOREIGN KEY REFERENCES Employee(employee_id), -- Nhân viên thực hiện giao dịch
);


CREATE TABLE InvoiceDetail (
    invoice_id INT, -- Liên kết với bảng hóa đơn
    ticket_id INT, -- Liên kết với bảng vé
    quantity INT, -- Số lượng vé
    price_per_ticket FLOAT, -- Giá vé đơn lẻ
    PRIMARY KEY (invoice_id, ticket_id), -- Khóa chính là cặp invoice_id và ticket_id
    FOREIGN KEY (invoice_id) REFERENCES Invoice(invoice_id),
    FOREIGN KEY (ticket_id) REFERENCES Ticket(ticket_id)
);

-- Tạo bảng SeatStatus (Trạng thái ghế)
CREATE TABLE SeatStatus (
    status_id INT PRIMARY KEY,
    status_name VARCHAR(20)
);

-- Tạo bảng Seat (Ghế)
CREATE TABLE Seat (
    seat_id INT IDENTITY(1,1) PRIMARY KEY,
    theater_id INT,
    seat_number VARCHAR(10),
    status_id INT,
    FOREIGN KEY (theater_id) REFERENCES Theater(theater_id),
    FOREIGN KEY (status_id) REFERENCES SeatStatus(status_id)
);

CREATE TABLE Account (
    account_id INT PRIMARY KEY IDENTITY(1,1),
    username NVARCHAR(50) NOT NULL UNIQUE, -- Tên đăng nhập
    password NVARCHAR(255) NOT NULL, -- Mật khẩu (cần mã hóa trong thực tế)
    employee_id INT, -- Khóa ngoại liên kết với bảng Employee
    FOREIGN KEY (employee_id) REFERENCES Employee(employee_id) -- Khóa ngoại
);




--drop database db_movie
