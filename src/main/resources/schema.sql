CREATE TABLE Drug1 (
                      drug_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255),
                      description VARCHAR(255),
                      manufacturer VARCHAR(255),
                      category VARCHAR(255),
                      quantity INT,
                      price DOUBLE,
                      manufacture_date DATE,
                      expiry_date DATE,
                      batch_number VARCHAR(255)
);
