# bottani

## Per aggiungere un nuovo elemento:
```
http://localhost:8080/blogpost/api/v1/new
```
Passare nel body il JSON che si vuole passare. Ad esempio:

```
{
"title": "prova","text": "testo del blogpost", "author": "marco"
}
```

Se si vuole utilizzare il formato 
`[{"key":"Content-Type","value":"application/x-www-form-urlencoded","description":"","disabled":false}]`

Usare il seguente URL
```
http://localhost:8080/blogpost/api/v1/new
```

## Per modificare un elemento
Per modificare un elemento usare il metodo patch come segue
```
http://localhost:8080/blogpost/api/v1/<id del post>
```
Dove `<id del post>` è l'id del blogpost che si vuole modificare.
Passare nel body i campi che si vogliono modificare formato JSON. 

Esempio:
```
{
	"author":"Giancarlo"
}
```
Per ottenere l'id chiamare il metodo `get` descritto in seguito


## Sostituire un elemento
Usare una chiamata PUT 
```
http://localhost:8080/blogpost/api/v1/<id del post>
``` 
Mettendo come `<id del post>` l'id del post che si vuole modificare
.

Esempio:
```
{
"title": "prova","text": "testo del blogpost", "author": "marcho"
}
```
## Ottenere tutti i blogpost inseriti
Usare una chiamata `get` al seguente url:
```
http://localhost:8080/blogpost/api/v1/
```
