# URL Shortener
Simple URL shortener written in Kotlin Spring. Utilizes DynamoDB for storage of the url mappings.

## How to run locally
1. Add execute permissions to the bash file.

```shell
chmod +x ./scripts/create-dynamodb-table.sh
```

2. Run docker environment with DynamoDB image.

```shell
docker compose -f docker-compose.yml up -d
```

3. Create a DynamoDB table.

```shell
./scripts/create-dynamodb-table.sh
```
4. Run application.

```shell
mvn spring-boot:run
```

## How to use locally
To generate a short code for a given url use the following command:
```shell
curl --location 'http://localhost:8080/v1/shortenUrl' \
--header 'Content-Type: application/json' \
--data '{
    "originalUrl": <url/to/shorten>
}'
```

To access the original url via the short code simply type ```http://localhost:8080/v1/<short-code>``` into your browser.

---