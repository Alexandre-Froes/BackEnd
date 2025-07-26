create user 'AlexandreFroes'@'localhost' identified by '12345';

grant insert, select, delete on db_ecommerce.* to 'AlexandreFroes'@'localhost';

revoke select, delete on db_ecommerce.* from 'AlexandreFroes'@'localhost';
show grants for 'AlexandreFroes'@'localhost';

create user 'adm_interdisciplinar'@'localhost' identified by '123';

grant all on db_ecommerce.* to 'adm_interdisciplinar'@'localhost';
grant grant option on db_ecommerce.* to 'adm_interdisciplinar'@'localhost';
show grants for 'adm_interdisciplinar'@'localhost';


create user 'desenvolvedor'@'localhost' identified by '123';
grant select on db_ecommerce.* to 'desenvolvedor'@'localhost';

show grants for 'desenvolvedor'@'localhost';
