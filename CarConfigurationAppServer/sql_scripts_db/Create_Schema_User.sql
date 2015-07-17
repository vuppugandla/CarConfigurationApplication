create schema car_config;

create user 'carconfig_user'@'localhost' IDENTIFIED BY 'carconfig_password';

grant all on car_config.* to 'carconfig_user'@'localhost';