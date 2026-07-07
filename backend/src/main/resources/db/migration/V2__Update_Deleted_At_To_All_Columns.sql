ALTER TABLE categories MODIFY COLUMN deleted_at datetime NULL;
ALTER TABLE products MODIFY COLUMN deleted_at datetime NULL;
ALTER TABLE product_images MODIFY COLUMN deleted_at datetime NULL;