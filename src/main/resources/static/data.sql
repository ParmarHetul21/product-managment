INSERT INTO users (user_id, username, email, password, status, created_at, updated_at, created_by, updated_by)
VALUES ('123e4567-e89b-12d3-a456-426614174000',
        'admin',
        'admin@yopmail.com',
        '$2a$10$gQXRK6GRXeqIaYbt99Jn9u8xxtPjKdKqGUSeVGA4B2Wz/YqeAMhWK',
        'ACTIVE',
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        '123e4567-e89b-12d3-a456-426614174000',
        '123e4567-e89b-12d3-a456-426614174000');

INSERT INTO products (product_id, name, description, price, quantity, status, created_at, updated_at, created_by,
                      updated_by)
VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Premium Laptop',
        'High-performance laptop with 16GB RAM, 512GB SSD, and dedicated graphics card', 1299.99, 50, 'ACTIVE',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '123e4567-e89b-12d3-a456-426614174000',
        '123e4567-e89b-12d3-a456-426614174000');

INSERT INTO products (product_id, name, description, price, quantity, status, created_at, updated_at, created_by,
                      updated_by)
VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'Smartphone Pro',
        'Latest smartphone with triple camera setup, 256GB storage and 5G capability', 899.99, 100, 'ACTIVE',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '123e4567-e89b-12d3-a456-426614174000',
        '123e4567-e89b-12d3-a456-426614174000');

INSERT INTO products (product_id, name, description, price, quantity, status, created_at, updated_at, created_by,
                      updated_by)
VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13', 'Wireless Headphones',
        'Noise-cancelling wireless headphones with 30-hour battery life and premium sound quality', 249.99, 75,
        'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '123e4567-e89b-12d3-a456-426614174000',
        '123e4567-e89b-12d3-a456-426614174000');

INSERT INTO products (product_id, name, description, price, quantity, status, created_at, updated_at, created_by,
                      updated_by)
VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a14', 'Fitness Smartwatch',
        'Waterproof smartwatch with heart rate monitor, GPS, and 7-day battery life', 179.99, 60, 'ACTIVE',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '123e4567-e89b-12d3-a456-426614174000',
        '123e4567-e89b-12d3-a456-426614174000');

INSERT INTO products (product_id, name, description, price, quantity, status, created_at, updated_at, created_by,
                      updated_by)
VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a15', 'Ultra Tablet',
        '10.5-inch display tablet with 128GB storage, stylus support, and all-day battery life', 449.99, 40, 'ACTIVE',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '123e4567-e89b-12d3-a456-426614174000',
        '123e4567-e89b-12d3-a456-426614174000');