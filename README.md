# omnicuris---ecommerce-assignment
omnicuris  ecommerce assignment

JAVA Spring Boot, Maven ,Hibernate, JPA
DataBase-MYSQL
1 ---> Pull code 
2 ---> Import in eclipse , Maven Update
3 ---> change db details in application.properties file (currently username->root, Pass-srivastav, Schema - ecomm)
4 ---> create a schema in mysql ( can use Mysql Workbench) with name ecomm
5 ---> no need to create tables, it will be created automatically with models , 
6 ---> default some products are added in products table using CommandLineRunner (in Main)


APIS
1 ---->  GET All products in DB
GET :http://localhost:8085/products/all
resp : 
```json

[
    {
        "id": 1,
        "name": "product 1",
        "quantity": 500
    },
    {
        "id": 2,
        "name": "product 2",
        "quantity": 500
    },
    {
        "id": 3,
        "name": "product 3",
        "quantity": 75
    },
    {
        "id": 4,
        "name": "product 4",
        "quantity": 0
    },
    {
        "id": 5,
        "name": "product 5",
        "quantity": 5
    }
]

```
===============================================================================================================================
2 ----> Order a Product 
POST http://localhost:8085/createorder?username=hello
POST BODy : 
```json
	[ 
		{
			"productid":4565,
			"quantity":2
		},
		{
			"productid":3,
			"quantity":99999999
		},
		{
			"productid":3,
			"quantity":5
		}
	]
  resp:
  {
    "failOrders": [
        {
            "4565": "product not found with this ID"
        },
        {
            "3": "Expected Quantity is not available"
        }
    ],
    "sucessOrders": {
        "id": 1,
        "created": "2019-05-21T13:59:51.915+0000",
        "user": "hello",
        "orderProducts": [
            {
                "id": 1,
                "quantity": 5,
                "order_id": null,
                "product_id": 3
            }
        ]
    }
}
```
===============================================================================================================================
3 ----> get successfully odered products on user wise
GET : http://localhost:8085/getallorders?username=hello
[
    {
        "id": 1,
        "created": "2019-05-21T13:59:52.000+0000",
        "user": "hello",
        "orderProducts": [
            {
                "id": 1,
                "quantity": 5,
                "order_id": 1,
                "product_id": 3
            }
        ]
    }
]
===============================================================================================================================
4 -----> Add new Products
POST : http://localhost:8085/product/add
BODY :
[
	{
		"name"     :"new1",
		"quantity" :100
	}
]
resp :
[
    {
        "id": 6,
        "name": "new1",
        "quantity": 100
    }
]
===============================================================================================================================
5 -----> Update a product already in db (Quantity will be added to currently available quantity & Name will be updated with new)
POST http://localhost:8085/product/update/1
BODY :
 {
        "name": "new1",
        "quantity": 100
 }
resp :
{
    "id": 1,
    "name": "new1",
    "quantity": 600
}
===============================================================================================================================
 6 --> Delete a product in product table
 DELETE http://localhost:8085/product/delete/1
 resp :
{
    "stat":"ok",
    "msg":"deleted SucessFully"
}
{
    "msg": "Product not found to delete",
    "stat": "fail"
}
=================================================================================================================================
7 ----> Get all Orders of all Users (each order separetly)
GET http://localhost:8085/orders/all
resp
[
    {
        "id": 1,
        "created": "2019-05-21T14:25:09.000+0000",
        "user": "hello",
        "orderProducts": [
            {
                "id": 1,
                "quantity": 5,
                "order_id": 1,
                "product_id": 3
            }
        ]
    },
    {
        "id": 2,
        "created": "2019-05-21T14:26:19.000+0000",
        "user": "hello",
        "orderProducts": [
            {
                "id": 2,
                "quantity": 5,
                "order_id": 2,
                "product_id": 3
            }
        ]
    },
    {
        "id": 3,
        "created": "2019-05-21T14:26:21.000+0000",
        "user": "hello",
        "orderProducts": [
            {
                "id": 3,
                "quantity": 5,
                "order_id": 3,
                "product_id": 3
            }
        ]
    },
    {
        "id": 4,
        "created": "2019-05-21T14:26:22.000+0000",
        "user": "hello",
        "orderProducts": [
            {
                "id": 4,
                "quantity": 5,
                "order_id": 4,
                "product_id": 3
            }
        ]
    },
    {
        "id": 5,
        "created": "2019-05-21T14:26:23.000+0000",
        "user": "hello",
        "orderProducts": [
            {
                "id": 5,
                "quantity": 5,
                "order_id": 5,
                "product_id": 3
            }
        ]
    }
]
===========================================================================================================
