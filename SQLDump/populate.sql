insert into items (item_id, item_name, item_pricePrKilogram) values ('Onion', 5);
insert into items (item_id, item_name, item_pricePrKilogram) values ('Bacon', 10);
insert into items (item_id, item_name, item_pricePrKilogram) values (35, 'Flour', 2);
insert into items (item_id, item_name, item_pricePrKilogram) values (36, 'Salt', 1);

insert into ingredients (ingredient_id, ingredient_amount, ingredient_item) values (33, 5, 33);
insert into ingredients (ingredient_id, ingredient_amount, ingredient_item) values (34, 1, 36);
insert into ingredients (ingredient_id, ingredient_amount, ingredient_item) values (35, 10, 34);
insert into ingredients (ingredient_id, ingredient_amount, ingredient_item) values (36, 400, 35);


insert into recipes (recipe_directions, recipe_preparationTime) values (23, 'Step 1', 10);
insert into recipes (recipe_directions, recipe_preparationTime) values (24, 'Step 1', 2);

select * from recipes;

insert into recipe_ingredient (recipe_id, ingredient_id) values (23, 33);
insert into recipe_ingredient (recipe_id, ingredient_id) values (23, 34);
insert into recipe_ingredient (recipe_id, ingredient_id) values (24, 34);
insert into recipe_ingredient (recipe_id, ingredient_id) values (23, 35);
insert into recipe_ingredient (recipe_id, ingredient_id) values (24, 35);
insert into recipe_ingredient (recipe_id, ingredient_id) values (23, 36);
