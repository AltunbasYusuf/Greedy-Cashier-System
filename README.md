# Greedy-Cashier-System
Problem Definition and Scenario:
The program managing a busy supermarket during peak hours. Each cashier is responsible for checking out a sequence of customer transactions (e.g., groceries, electronics, returns, etc.). These transaction types have different complexities.
Each cashier can only serve one customer at a time and switching between transaction types of costs time, especially if the transaction types differ significantly (e.g., groceries to electronics or returns).
The program assign customer transactions to available cashiers to minimize total overhead caused by switching between transaction types.
Aim is minimize the cost using greedy approach.
Example transaction Types (b):
•	Type 1: Groceries
•	Type 2: Electronics
•	Type 3: Customer Returns
•	Type 4: Pharmacy Items
•	Type 5: Bulk Items
•	…


Problem Rules:
1.	Base Switching Cost (c): 
Time it takes a cashier to switch between different transaction types. This will be taken as an argument.
2.	Exhaustion Rule:
If a cashier processes two of the same type consecutively, they get fatigued → next third task of same type costs 1.5× as much. However, if the next item is different than it still costs 1× as much as usual switch cost.
3.	Learning Curve:
If a cashier has handled more complex ones before in any transaction (e.g., electronics (Type 2)), and now calculates cost to simpler transactions (e.g., groceries (Type 1)) the cost reduces to 0.8× of the current cost.
4.	Limited Transaction Diversity (k):
Each cashier can only handle k different types of transactions across the day (e.g., only groceries + pharmacy). This will be taken as an argument.
5.	Cost Inflation Over Time:
Every 5 customers, all switching costs increase by +1 to simulate fatigue or rush hour stress. Cost increases after 5 complete transactions.
6.	Insufficient Cashier:
If there is no possible solution the cost must be returned as -1.
7.	Same Cost:
If one or more cashiers has same transaction cost, program must select first fitting cashier for transaction.
input.txt format
The program read inputs from the “input.txt” file.



Input file may or may not include multiple runs for the problem, program must adjust accordingly to input file dynamically.
* Base cost
* Cashier count
* Max types Per Cashier
* Transactions
*For example:
*3
*2
*2
*Type 1, Type 2, Type 3, Type 1, Type 1, Type 1, Type 2
*4
*2
*1
*Type 2, Type 2, Type 1, Type 1, Type 4, Type 1
