-- USERS
INSERT INTO users (username, password_hash, role) VALUES ('admin', 'admin123', 'ADMIN');
INSERT INTO users (username, password_hash, role) VALUES ('john_doe', 'password123', 'USER');

-- CUSTOMERS
INSERT INTO customers (user_id, full_name, email, phone, dob, address, pan_number, aadhaar_number)
VALUES (2, 'John Doe', 'john@example.com', '9876543210', TO_DATE('1990-05-15','YYYY-MM-DD'), '123 Street, City', 'ABCDE1234F', '123456789012');

INSERT INTO customers (user_id, full_name, email, phone, dob, address, pan_number, aadhaar_number)
VALUES (2, 'Jane Smith', 'jane@example.com', '9123456780', TO_DATE('1992-08-20','YYYY-MM-DD'), '456 Avenue, City', 'PQRSX6789L', '987654321098');

-- ACCOUNTS
INSERT INTO accounts (customer_id, account_type, account_status, created_by)
VALUES (1, 'SAVINGS', 'ACTIVE', 1);

INSERT INTO accounts (customer_id, account_type, account_status, created_by)
VALUES (2, 'CURRENT', 'INACTIVE', 1);

-- KYC DOCUMENTS
INSERT INTO kyc_documents (customer_id, aadhaar_image_path, pan_image_path, photo_image_path)
VALUES (1, '/images/aadhaar1.jpg', '/images/pan1.jpg', '/images/photo1.jpg');

INSERT INTO kyc_documents (customer_id, aadhaar_image_path, pan_image_path, photo_image_path)
VALUES (2, '/images/aadhaar2.jpg', '/images/pan2.jpg', '/images/photo2.jpg');

-- KYC STATUS
INSERT INTO kyc_status (customer_id, status, admin_message, verified_by, verified_at)
VALUES (1, 'APPROVED', 'Documents verified successfully', 1, SYSTIMESTAMP);

INSERT INTO kyc_status (customer_id, status, admin_message, verified_by, verified_at)
VALUES (2, 'PENDING', 'Awaiting document verification', 1, NULL);
