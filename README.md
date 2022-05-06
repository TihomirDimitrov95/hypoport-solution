# Insutrctions


## Steps to test my code

* Requires [Docker](https://www.docker.com/products/docker-desktop/)

1. clone your repository --> https://github.com/europace/duck-coding-challenge


2. Create the docker image: `docker build -t europace/document-api .`


3. Run the docker image: `docker run --rm -p 8081:8080 europace/document-api`
   1. **Important:** run the Docker image on port 8081 as in the command so you can leave 8080 free for this application


4. Clone my repository --> https://github.com/TihomirDimitrov95/hypoport-solution
   `curl --location --request GET 'http://localhost:8080/v1/documents'`

My project will make HTTP calls to your Documents API, process the data and return information at my endpoints.

5. Run my project in IntelliJ. My project will use port 8080 (while your docker will use 8081)


6. Test commands through Postman
   1. ` GET 'http://localhost:8080/v1/documents/details'`
   2. ` GET 'http://localhost:8080/v1/documents/filter?category=cat_1'` (you can use any category and I search for a full match)
   3. ` GET 'http://localhost:8080/v1/documents/sorted?sortBy=name'` (you can use name, type, size or deleted; if anything else is used there is an error message displayed)

