CREATE TABLE categories
(
    deleted_at bit NOT NULL,
    id         bigint IDENTITY (1, 1) NOT NULL,
    created_at datetime,
    updated_at datetime,
    name       varchar(255),
    CONSTRAINT pk_categories PRIMARY KEY (id)
)
    GO

CREATE TABLE product_images
(
    deleted_at bit    NOT NULL,
    id         bigint IDENTITY (1, 1) NOT NULL,
    created_at datetime,
    updated_at datetime,
    filename   varchar(255),
    filetype   varchar(255),
    filesize   bigint NOT NULL,
    filepath   varchar(255),
    product_id bigint NOT NULL,
    CONSTRAINT pk_product_images PRIMARY KEY (id)
)
    GO

CREATE TABLE products
(
    deleted_at  bit          NOT NULL,
    id          bigint IDENTITY (1, 1) NOT NULL,
    created_at  datetime,
    updated_at  datetime,
    name        varchar(255) NOT NULL,
    type        varchar(255) NOT NULL,
    description varchar(255) NOT NULL,
    price       float(53)    NOT NULL,
    stock       int          NOT NULL,
    category_id bigint       NOT NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
)
    GO

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES categories (id)
    GO

ALTER TABLE product_images
    ADD CONSTRAINT FK_PRODUCT_IMAGES_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id)
    GO