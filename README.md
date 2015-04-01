# payslip

This project is a small project to exercise a test driven development.

The purpose of this project is to produce a payslip for a given tax data, and employee data.

For Example:

Given this table:

Taxable income   Tax on this income
0 - $18,200     Nil
$18,201 - $37,000       19c for each $1 over $18,200 <br/>
$37,001 - $80,000       $3,572 plus 32.5c for each $1 over $37,000 <br/>
$80,001 - $180,000      $17,547 plus 37c for each $1 over $80,000  <br/>
$180,001 and over       $54,547 plus 45c for each $1 over $180,000 <br/>

And an employee whom annual salary is 60,050, super rate is 9%, how much will this employee be paid for the month of March ? 

Input for this program must be a csv file such as this:

Input (first name, last name, annual salary, super rate (%), payment start date):
David,Rudd,60050,9%,01 March – 31 March
Ryan,Chen,120000,10%,01 March – 31 March

And the output will be a csv file such as this:
Output (name, pay period, gross income, income tax, net income, super):
David Rudd,01 March – 31 March,5004,922,4082,450
Ryan Chen,01 March – 31 March,10000,2696,7304,1000

The calculation details will be the following:
•       pay period = per calendar month
•       gross income = annual salary / 12 months
•       income tax = based on the tax table provide below
•       net income = gross income - income tax
•       super = gross income x super rate
