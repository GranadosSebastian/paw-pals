use paw_pals_test;
set sql_safe_updates = 0;
call set_known_good_state();
SELECT * FROM paw_pals_test.animal;