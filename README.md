# Jobs Platform Server
This is the backend-end microservices repository for the job's platform server.

It is based on these main technologies
- Java
- Spring Boot
- Docker
- AWS EC2

## Project Architecture
![UML class](https://github.com/ZongruiL/Job/assets/103609494/e2746b29-c596-475d-87aa-6a4a4e3bd385)

## Getting Started

All the services are containered within the docker. So, we can simply start the docker and run this following command:
```bash
docker compose up -d
```
Then, we can access all APIs through API Gateway: http://localhost:8084

Alternatively, since all services are hosted on AWS EC2, we can access all the APIs through http://54.174.244.97

## REST API



Some examples of manipulating API requests: 

`POST /companies`
```
{
    "name": "comapany1",
    "description": "description 1"
}
```
`POST /jobs`
```
{

    "title": "Software Engineer",
    "description": "Test",
    "minSalary": "30000",
    "maxSalary" : "40000",
    "location": "Toronto",
    "companyId": 1
    
}
```

`POST /reviews?companyId=1`
```
{
    "title": "Review 1",
    "description": "description 1",
    "rating": 5.0
}

```

`PUT /companies/{id}`
```
{
    "name": "comapany1",
    "description": "description 2"
}
```

`DELETE /companies/{id}`

`GET /companies/{id}`

`GET /reviews/{id}`

`GET /reviews/averageRating?companyId=1`
