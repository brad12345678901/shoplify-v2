ALTER TABLE categories ALTER COLUMN deleted_at datetime NULL;
ALTER TABLE products ALTER COLUMN deleted_at datetime NULL;
ALTER TABLE product_images ALTER COLUMN deleted_at datetime NULL;