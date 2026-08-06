USE mydb1;

CREATE TABLE IF NOT EXISTS users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(200),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

INSERT INTO users (name, email)
VALUES
    ('홍길동', 'hong@example.com'),
    ('이순신', 'lee@example.com'),
    ('유관순', 'yoo@example.com');