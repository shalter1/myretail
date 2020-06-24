# myRetail RESTful service

myRetail provides a java implementation for the myRetail RESTful service Target technical case study. It is based upon the document provided, titled Java BE - Redsky Updated 5_1_2020-edits.docx.
In specific, it implements:
Responds to an HTTP GET request at /products/{id} and delivers product data as JSON (where {id} will be a number. 
An example response is: {"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}
The name is retrieved from  http://redsky.target.com/v2/pdp/tcin/{id}?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics

Reads pricing information from a NoSQL data store and combines it with the product id and name from the HTTP request into a single response.  
Accepts an HTTP PUT request at the same path (/products/{id}), containing a JSON request body similar to the GET response, and updates the product’s price in the data store.  
An example curl put request is: 
'curl -X PUT -H "Content-Type: application/json" -d '{"value":"19", "currency_code":"USD"}' "http://localhost:8080/products/13860428"''

## Technolgies

* Java 1.8
* Spring Boot 2.3.1
* Maven 3.6.3 
* mongodb 4.2
* github
* junit 4.12

## Installation

You will need Java 1.8 and maven 3.6 or later installed upon your system. 
Maven can be downloaded from: https://maven.apache.org/download.cgi
Follow the instructions at: https://docs.mongodb.com/manual/administration/install-community/ to install mongodb to your system and then run with mongod

clone or copy this repository to your system (git clone https://github.com/shalter1/myretail.git)


## Usage

cd [your repository path]/myretail/myRetail

Start the server by:
./mvnw spring-boot:run

You can now use your tool of choice to send REST requests to the server.
For example:

### GET request
curl -X GET -H "Content-Type: application/json" "http://localhost:8080/products/13860428" 
JSON result:
{"id":13860428,"name":"The Big Lebowski (Blu-ray)","current_price":{"value":13.49,"currency_code":"USD"}}

### PUT request
curl -X PUT -H "Content-Type: application/json" -d '{"value":"19", "currency_code":"USD"}' "http://localhost:8080/products/13860428"

Will change the price of product 13860428 to 19

### GET request on unknown product
curl -X GET -H "Content-Type: application/json" "http://localhost:8080/products/99"

returns
Product 99 not found

### GET request on invalid product id
curl -X GET -H "Content-Type: application/json" "http://localhost:8080/products/99a"

returns
Malformed JSON request: Failed to convert value of type 'java.lang.String' to required type 'java.lang.Integer'; nested exception is java.lang.NumberFormatException: For input string: "99a"

### GET request on product without pricing information
curl -X GET -H "Content-Type: application/json" "http://localhost:8080/products/12954218"

returns

{"id":12954218,"name":"Kraft Macaroni &#38; Cheese Dinner Original - 7.25oz","current_price":{"value":-1.0,"currency_code":""}}

A value of -1 and empty currency code is used to indicate a non-priced product.

### PUT request on non-existent product
curl -X PUT -H "Content-Type: application/json" -d '{"value":"19", "currency_code":"USD"}' "http://localhost:8080/products/99"

Will set the price of product 99 to 19 in the pricing database.

## Some future enhancements for use in a production environment
### Security
* https--The server should be secured via a proper certificate so that it can be contacted via https.
* User Authentication--The capability for users to authenticate themselves to the server should be added
* User Authorization--The capability for differing access for users should be added. For example, not everyone needs to be able to change pricing information.
* Once security is added, a number of penetration tests will need to be performed.

### Performance
* Various mechanisms may be added to cache data, such as memcached
* The application might be distributed via load balancing
* The mongo db (pricing) will need to be secured

### Other
* Add versioning to the API (/v2/products)
* Add in protection versus attacks such as Denial of Service





