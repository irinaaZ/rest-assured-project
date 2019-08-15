import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class RestTest {
    Gson gsonBuilder = new GsonBuilder().create();

    public final class EndPoints {
        public static final String users = "/api/v1/users";
        public static final String users_id = "/api/v1/users/{id}";
        public static final String basePathUsers = "/api/v1/users/";
    }

    @BeforeTest
    public void setUpUri() {
        baseURI = "https://5d50601381cbae00149a0bbf.mockapi.io";
    }

    @Test
    public void getAllUsersFromList() {
        List<User> listUsers = given()
                .baseUri(baseURI)
                .when()
                .get(EndPoints.users)
                .then()
                .extract().body().jsonPath().getList(".", User.class);
        listUsers.forEach(System.out::println);
    }

    @Test
    public void getAllUsersAge() {
        List<Integer> listUsersAge = given()
                .baseUri(baseURI)
                .basePath(EndPoints.users)
                .when().get().then().extract().response().jsonPath().getList("age");
        listUsersAge.forEach(System.out::println);
    }

    @Test
    public void getAllUsersNames() throws JSONException {
        JSONArray usersInJson = new JSONArray(
                given().baseUri(baseURI)
                        .basePath(EndPoints.users)
                        .when().get().then().extract().response().jsonPath().prettify());

        List<JSONObject> listOfUsersInJson = new ArrayList<>();

        for (int index = 0; index < usersInJson.length(); index++) {
            JSONObject jsonObject = (JSONObject) usersInJson.get(index);
            System.out.println(jsonObject.getString("FirstName") + " " + jsonObject.getString("LastName"));
            listOfUsersInJson.add(jsonObject);
        }
    }

    @Test
    public void searchUserById() {
        String value = given().
                when()
                .get(EndPoints.users_id, 4)
                .then()
                .assertThat().statusCode(200)
                .extract().body().jsonPath().prettyPrint();
    }

    @Test
    public void createUser() {
        User user = new User(30, "email 222", "Name", "Surname", 222);

        String jsonFomUser = gsonBuilder.toJson(user);
        given().baseUri(baseURI)
                .basePath(EndPoints.users)
                .body(jsonFomUser)
                .post().then().statusCode(201);
    }

    @Test
    public void deleteUser() {
        given().baseUri(baseURI)
                .basePath(EndPoints.basePathUsers + "2")
                .delete().then().statusCode(200);
    }

    @Test
    public void updateUser() {
        User user = new User(48, "email 1", "LastName 1", "FirstName 1", 1);

        String jsonFomUser = gsonBuilder.toJson(user);
        given().baseUri(baseURI)
                .basePath(EndPoints.basePathUsers + "1")
                .put().then().statusCode(200);
    }
}