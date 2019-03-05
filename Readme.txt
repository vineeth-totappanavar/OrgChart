Features Provided:
~~~~~~~~~~~~~~~~~~

1. Easy to add new Organisation and its Employee (manually and not through REST)
2. Organisation/Employee Navigation through Reference links
3. Self link to show the current Instance detail
4. Employee search by name (case sensitive search)
5. Pagination when viewing all employees of organisation
6. Data directory (containing all the data) can be provided as VM argumensts

Endpoint references:
~~~~~~~~~~~~~~~~~~~~

1. All available Organisations in OrgChart
http://localhost:8080/OrgChart/rest/organisation

2. Specific Organisation detail
http://localhost:8080/OrgChart/rest/organisation/1
Output:
<organisations>
	<organisation>
	<id>1</id>
	<name>Dell EMC</name>
	<address>
		Bhagmane World Technology Center, Mahadevapura, Outer ring road, Bangalore
	</address>
	<links href="/organisation/1">
		<ref-link>/organisation/1/employee</ref-link>
		<ref-link>/organisation/1/employee/search</ref-link>
	</links>
	</organisation>
</organisations>

3. Employees of an Organisation. Here by default first page with 10 employee details is shown. Hierarchy not shown here.
   Pagination Ref-Link will appear only if total Employee count per organisation is more than 10
http://localhost:8080/OrgChart/rest/organisation/1/employee
http://localhost:8080/OrgChart/rest/organisation/1/employee?page=2

4. A specific employee details along with Hierarchy. managedby gets all the manager hierarchy till Chairman/CEO. reportees get all the employee reporting to an employee.
   managedby and reportees remain empty if there are no employees in either of them
http://localhost:8080/OrgChart/rest/organisation/1/employee/7
Output:
<employees>
	<employee>
	<id>7</id>
	<name>Umamaheshwar Hegde</name>
	<designation>Senior Manager, Software Engineering</designation>
	<location>Bangalore, India</location>
	<links href="/organisation/1/employee/7">
		<ref-link>/organisation/1</ref-link>
	</links>
	<managedby>...</managedby>
	<reportees>...</reportees>
	</employee>
</employees>

5. Employee search by name. Here I have assumed search is case sensitive. Output is same as that of Employee detail by Emp ID
http://localhost:8080/OrgChart/rest/organisation/1/employee/search?name=Umamaheshwar%20Hegde