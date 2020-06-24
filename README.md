# myRetail RESTful service

myRetail provides a java implementation for the myRetail RESTful service Target technical case study. It is based upon the document provided, titled Java BE - Redsky Updated 5_1_2020-edits.docx.
In specific, it implements:
Responds to an HTTP GET request at /products/{id} and delivers product data as JSON (where {id} will be a number. 
An example response is: {"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}
The name is retrieved from  http://redsky.target.com/v2/pdp/tcin/{id}?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics

Reads pricing information from a NoSQL data store and combines it with the product id and name from the HTTP request into a single response.  
Accepts an HTTP PUT request at the same path (/products/{id}), containing a JSON request body similar to the GET response, and updates the product’s price in the data store.  
An example curl put request is: 
curl -X PUT -H "Content-Type: application/json" -d '{"value":"19", "currency_code":"USD"}' "http://localhost:8080/products/13860428"

## Technolgies

Java 1.8
Spring Boot 2.3.1
Maven 3.6.3 
mongodb 

## Installation

You will need Java 1.8 and maven 3.6 or later installed upon your system. 
Maven can be downloaded from: https://maven.apache.org/download.cgi
Follow the instructions at: https://docs.mongodb.com/manual/administration/install-community/ to install mongodb to your system and then run with mongod

clone or copy this repository to your system ()


## Usage

```python
import foobar

foobar.pluralize('word') # returns 'words'
foobar.pluralize('goose') # returns 'geese'
foobar.singularize('phenomena') # returns 'phenomenon'
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.