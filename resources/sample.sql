SET SQL_SAFE_UPDATES = 0;
SET SQL_SAFE_UPDATES = 1;

select * from customers;

select * from orders;


select * from customers 
cross join orders
on (customers.customerid != orders.customerid && customers.customerid = 4) or customers.customerid = orders.customerid
order by customers.customerid;
