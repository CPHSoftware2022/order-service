# MOTOGOCPG Order Service

* Requirements
* Running the code


### Requirements
You will need a few requirements in order to run this service:

* Install and run the Kafka project from [here](https://github.com/CPHSoftware2022/KafkaConsumer)

### Running the code
1. Make sure you have the kafka docker running from the Requirements section.
2. After that is running you should be able to run the docker-compose.yml file using the following command
3. ``docker-compose -d docker-compose.yml``
4. The service should be available on localhost:7000

### GraphQL

GraphQL is available on localhost:7000/graphql

I will go through the queries that are possible in the project:

1. [FindOneCustomerOrder](FindOneCustomerOrder)
2. [CreateCustomerOrder](CreateCustomerOrder)
3. [UpdateCustomerOrder](UpdateCustomerOrder)

#### FindOneCustomerOrder
Here is an example of fetching a single customer order from the graphql endpoint.
```graphql
{
    findOneCustomerOrder(id: 1) {
        order_start
        order_end
        total_price
        accepted
        canceled_reason
        delivered
        customer_id
        restaurant_id
        feedback_id
        employee_id
    }
}
```
And the response should be:
```json
{
    "data": {
        "findOneCustomerOrder": {
            "order_start": "2022-10-10 10:10:10.0",
            "order_end": "2022-10-10 10:20:10.0",
            "total_price": 400.0,
            "accepted": true,
            "canceled_reason": null,
            "delivered": true,
            "customer_id": 1,
            "restaurant_id": 1,
            "feedback_id": 1,
            "employee_id": 2
        }
    }
}
```

#### CreateCustomerOrder
Here is an example of creating a customer order from the graphql endpoint.
```graphql
mutation {
    addCustomerOrder(
        order_start : "2022/10/10 10:10:10",
        order_end : "2022/10/10 10:10:10",
        total_price : 9000,
        accepted : true,
        canceled_reason : null,
        delivered : true,
        customer_id : 1,
        restaurant_id : 1,
        feedback_id : null,
        employee_id : 1,
    ) {
        order_start
        order_end
        total_price
        accepted
        canceled_reason
        delivered
        customer_id
        restaurant_id
        feedback_id
        employee_id
    }
}
```
And the response should be:
```json
{
    "data": {
        "addCustomerOrder": {
            "order_start": "Mon Oct 10 00:00:00 CEST 2022",
            "order_end": "Mon Oct 10 00:00:00 CEST 2022",
            "total_price": 9000.0,
            "accepted": true,
            "canceled_reason": null,
            "delivered": true,
            "customer_id": 1,
            "restaurant_id": 1,
            "feedback_id": null,
            "employee_id": 1
        }
    }
}
```

#### UpdateCustomerOrder
Here is an example of updating an existing customer order from the graphql endpoint.
```graphql
mutation {
    updateCustomerOrder(
        id: 2,
        order_start: "2022-10-10 10:10:10",
        order_end: "2022-10-10 10:10:10",
        total_price: 10000,
        accepted: true,
        canceled_reason: null,
        delivered: true,
        customer_id: 1,
        restaurant_id: 1,
        feedback_id: 1,
        employee_id: 1
    ) {
        id
        order_start
        order_end
        total_price
        accepted
        canceled_reason
        delivered
        customer_id
        restaurant_id
        feedback_id
        employee_id
    }
}
```
And the response should be:
```json
{
    "data": {
        "updateCustomerOrder": {
            "id": null,
            "order_start": null,
            "order_end": null,
            "total_price": 0.0,
            "accepted": false,
            "canceled_reason": null,
            "delivered": false,
            "customer_id": null,
            "restaurant_id": null,
            "feedback_id": null,
            "employee_id": null
        }
    }
}
```