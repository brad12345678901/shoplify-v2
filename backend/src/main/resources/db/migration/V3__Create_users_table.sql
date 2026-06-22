CREATE TABLE users (
    id BIGINT IDENTITY(1,1) NOT NULL,
    first_name VARCHAR (255) NOT NULL,
    middle_name VARCHAR (255) NULL,
    last_name VARCHAR (255) NOT NULL,
    email VARCHAR (255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    is_active BIT NOT NULL,
);