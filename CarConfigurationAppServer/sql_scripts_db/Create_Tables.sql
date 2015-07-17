CREATE TABLE IF NOT EXISTS automobiles(
auto_id INT AUTO_INCREMENT,
make VARCHAR(128) NOT NULL,
model VARCHAR(128) NOT NULL,
base_price DOUBLE NOT NULL,
PRIMARY KEY(auto_id)
);

CREATE TABLE IF NOT EXISTS options_set(
options_set_id INT AUTO_INCREMENT,
auto_id INT NOT NULL,
name VARCHAR(128) NOT NULL,
PRIMARY KEY(options_set_id),
FOREIGN KEY(auto_id) REFERENCES automobiles(auto_id)
);

CREATE TABLE IF NOT EXISTS options(
options_id INT AUTO_INCREMENT,
options_set_id INT NOT NULL,
name VARCHAR(128) NOT NULL,
price DOUBLE NOT NULL,
PRIMARY KEY(options_id),
FOREIGN KEY(options_set_id) REFERENCES options_set(options_set_id)
);


