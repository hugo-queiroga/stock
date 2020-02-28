insert into beverage_type (id,name,max_volume) values 
	(NEXT VALUE FOR beverage_type_seq ,'Alcoholic beverage', 500),
	(NEXT VALUE FOR beverage_type_seq ,'Non-alcoholic beverage', 400);

insert into section (id,name, space_used) values (NEXT VALUE FOR section_seq ,'Section 1', 0),
	(NEXT VALUE FOR section_seq ,'Section 2', 0),
	(NEXT VALUE FOR section_seq ,'Section 3', 0),
	(NEXT VALUE FOR section_seq ,'Section 4', 0),
	(NEXT VALUE FOR section_seq ,'Section 5', 0);