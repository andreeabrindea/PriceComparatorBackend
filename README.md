# PriceComparatorBackend

#### A silly backend for Accesa's Coding Challange.

## Main features: 
- Daily Shopping Basket Monitoring:
  it helps users to split their products from the basket into several shopping list per store to reduce costs. 
- Best Discounts:
  it helps users to descover the biggest existing discounts
- New Discounts:
  it helps users to descover the biggest existing discounts
- Dynamic Price History Graphs:
  it provides data points that would allow a frontend to calculate and display price
trends over time for individual products. Products can be filtered by store, product category and brand.
- Product Substitutes & Recommendations:
  it helps users to see similar products with best prices per unit
- Custom Price Alert:
  helps a user to set alerts in order to monitor when a product has a desirable price.

## Code Architecture
To maintain organization and follow RESTful principles, the project is structured into:
- models should represent input data type or requests.
- repositories reads and holds and filters lists of data
- services handles business logic and data manipulation.
- controllers define API endpoints and handle incoming requests

## How to run the application
- Clone this repository.
- Ensure you have Java installed.
- Open the project in your preferred IDE.
- Run the application.

## How to use it
- Daily Shopping Basket Monitoring is accesible at the endpoint ```http://localhost:8080/api/products``` where you shoud add a request body containing a list of desirable products names such as: 
```[
  {
    "productName": "ouă"
  },
  {
    "productName": "vin alb demisec"
  },
  {
    "productName": "pâine albă"
  },
   {
    "productName": "roșii"
  }
]
```
- Best Discounts is accesible at the endpoint ```http://localhost:8080/api/best-discounts``` through a simple get request.
- New Discounts is accesible at the endpoint ```http://localhost:8080/api/latest-discounts``` through a simple get request.
- Dynamic Price History Graphs is accesible at the endpoint ```http://localhost:8080/api/price-history/product?store=kaufland&brand=heidi```, where ```store=kaufland&brand=heidi``` are some filters added as request params.
- Product Substitutes & Recommendations is accesible at the endpoint ```http://localhost:8080/api/product?name=vin```, where ?name=vin is a request param.
- Custom Price Alert lets you create & delete a price alert.
