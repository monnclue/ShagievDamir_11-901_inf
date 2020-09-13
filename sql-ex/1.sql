#task 1

SELECT model, speed, hd from PC 
WHERE price < 500

#task 2

SELECT DISTINCT maker from Product 
WHERE type LIKE '%Printer%'

#task 3

SELECT model, ram, screen from Laptop 
WHERE Price > 1000 

#task 4

SELECT * FROM Printer 
WHERE color = 'y'

#task 5

SELECT model, speed, hd FROM PC 
WHERE price < 600 AND 
(cd = '12x' OR cd = '24x')

#task 6

SELECT DISTINCT maker, speed FROM Product 
JOIN Laptop ON Product.model = Laptop.model 
WHERE hd >= 10

#task 7

SELECT p.model, pc.price FROM product as p
JOIN pc as pc ON p.model=pc.model
WHERE p.maker = 'B'
union
SELECT p.model, l.price FROM product as p
JOIN laptop as l ON p.model=l.model
WHERE p.maker = 'B'
union
SELECT p.model, pr.price FROM product as p
JOIN printer as pr ON p.model=pr.model
WHERE p.maker = 'B'

#task 8

SELECT DISTINCT maker
FROM product
WHERE type = 'PC'
EXCEPT
SELECT DISTINCT maker
FROM product
WHERE type= 'Laptop';

#task 9

select distinct maker from Product 
join PC on Product.model = PC.model 
where speed >= 450

#task 10

select model, price from Printer 
where price = (select MAX(price) 
from Printer)

#task 11

select AVG(speed) from PC 

#task 12

select AVG(speed) from Laptop 
where price > 1000

#task 13

select AVG(speed) from Product 
join PC on Product.model = PC.model 
where maker = 'A'

#task 14

select Classes.class, name, country 
from Classes 
join Ships on Classes.class = Ships.class 
where numGuns >= 10

#task 15

select hd from PC 
group by hd 
having COUNT(hd) > 1

#task 16

select distinct big.model, low.model, 
big.speed, big.ram from PC as big 
join PC as low on (
big.model > low.model and big.ram = low.ram
and big.speed = low.speed)

#task 17

select distinct type, Product.model, 
Laptop.speed from Product 
join Laptop on Product.model = Laptop.model 
where Laptop.speed < ALL (
	select speed from PC)

#task 18

select distinct maker, price from Product 
join Printer on Product.model = Printer.model
where Printer.price = (select MIN(price) 
from Printer where color = 'y') 
and color = 'y'

#task 19

select maker, AVG(screen) from Product 
join Laptop on Product.model = Laptop.model
group by maker

#task 20

Select maker, count(model) from product
where type = 'PC'
group by maker
having count(model) >= 3

#task 21

select maker, MAX(price) from Product 
right join PC on Product.model = PC.model
group by maker

#task 22

select speed, AVG(price) from PC
where speed > 600
group by speed

#task 23

select maker from Product 
join PC on Product.model = PC.model
where speed >= 750
INTERSECT 
select maker from Product 
join Laptop on Product.model = Laptop.model
where speed >= 750

#task 24

with Res (model, price) as 
(
	select model, price from PC 
	where price = (select MAX(price) 
		from PC)
	union
	select model, price from Laptop 
	where price = (select MAX(price) 
		from Laptop)
	union
	select model, price from Printer
	where price = (select MAX(price) 
		from Printer)
)
select model from Res
where price = (select MAX(price) from Res)

#task 25

select distinct maker from Product 
join PC on Product.model = PC.model
where speed = (select MAX(speed) from PC
where ram = (select MIN(ram) from PC))
and ram in (select MIN(ram) from PC)
INTERSECT
select maker from Product 
where type = 'printer'
 
#task 26

with Res as (
	select price
	from PC 
	join Product 
	on Product.model = PC.model
	where maker = 'A'
	union all
	select price 
	from Laptop
	join Product
	on Product.model = Laptop.model
	where maker = 'A'
)
select AVG(price) from Res

#task 27

select maker, AVG(hd) from PC
join Product on Product.model = PC.model
where maker in (
	select maker from Product 
	where type = 'Printer')
group by maker

#task 28

select COUNT(maker) from Product
where maker in (
	select maker from Product 
	group by maker
	having COUNT(model) = 1
)

#task 29

select income_o.point, income_o.date,
 inc, out from income_o 
left join outcome_o on
income_o.point = outcome_o.point 
and income_o.date = outcome_o.date
union
select outcome_o.point, outcome_o.date, 
inc, out from income_o
right join outcome_o on
income_o.point = outcome_o.point 
and income_o.date = outcome_o.date

#task 30

with resIn as (
	select point, date, sum(inc) as inc
	from Income
	group by point, date
), 
resOut as (
	select point, date, sum(out) as out
	from Outcome
	group by point, date
)
 
select resIn.point, resIn.date, resOut.out, resIn.inc
from resIn left join resOut on
resIn.point = resOut.point
and resIn.date = resOut.date 

union
 
select resOut.point, resOut.date, resOut.out, resIn.inc
from resIn right join resOut on
resIn.point = resOut.point
and resIn.date = resOut.date 



