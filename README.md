Request:

```
GET http://localhost:8080/api/books?pageNumber=0&pageSize=10&search="discovering"
```

Response:

```
{
  "page": 0,
  "size": 10,
  "totalPages": 1,
  "totalElements": 1,
  "data": [
    {
      "id": 4,
      "title": "Harry Potter and the Sorcerer,s Stone",
      "author": "J.K. Rowling",
      "description": "The first book in the beloved series about a young wizard who discovers his magical heritage.",
      "price": 9.99
    }
  ]
}
```
