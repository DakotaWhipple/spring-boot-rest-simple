## Usage
To call api endpoints, add username and password to request. Api uses cookies for authentication.
### Login
curl -i -X POST -d username=admin -d password=admin -c cookies.txt http://localhost:8080/login
### Get employees
curl -i --header "Accept:application/json" -X GET -b cookies.txt http://localhost:8080/employees
###  Add user
curl -i --header "Accept:application/json" -X POST -b cookies.txt http://localhost:8080/employees -H 'Content-Type:application/json' -d '{"lastName":"Milson","firstName":"Him","middleInitial":"M","dateOfBirth":"2/10/90","dateOfEmployment":"1/23/19"}'
