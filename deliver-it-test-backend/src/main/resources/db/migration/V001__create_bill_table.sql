CREATE TABLE bill
(
    id             BIGINT PRIMARY KEY AUTO_INCREMENT,
    name           VARCHAR(50)    NOT NULL,
    original_value DECIMAL(10, 2) NOT NULL,
    adjusted_value DECIMAL(10, 2) NOT NULL,
    due_date       DATE           NOT NULL,
    payment_date   DATE           NOT NULL,
    due_days       INT            NOT NULL,
    rule           VARCHAR(20)    NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
