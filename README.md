# Getting Started

### Reference Documentation

This project describes the REST api about the posts / stories (like status on Facebook).

User can interact to the post with the title and description.
In addition, user can like the specific post, namely:
* Get all the posts.
* Get the specific post.
* Update the specific post.
* Delete the specific post.
* Add the specific post to Database.
* Make the "like / unlike" post. The like of specific is not negative.


### Applied knowledge
* Apply knowledge of Annotation as "Autowired, Bean, MockBean, ..."
* Separate layer Service, Repository, Controller.
* Design the REST api basic CRUD with @RestController / @PathVariable / @RequestBody.
* Connect to real MySql database.
* Use JPA Method.
* Apply the exception handling.
* Use the WebMVCtest and DataJPATest to test all the API route and all the method repository.
* Use wrapper.


### Installing
* Step 1: Open Intellij IDEA.
* Step 2: Import project by: File -> Open -> Select your project directory.
* Step 3: IDE will auto create necessary file to build project structure, wait some minutes.
* Step 4: Sometimes, project is not built automatically, open maven by: View -> Tools Window -> Maven. Run project with command: mvn -Dmaven.test.skip=true -U clean install.
* Step 5: Ok, the project is already to run. Click Run in Run Dashboard. The api will be expose in your port(server.port in application.yml file).




