package all_for_create_user;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;

public class ChecksForUser {

    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site/";
    private static final String PATH_REGISTER = "/api/auth/register";
    private static final String PATH_LOGIN = "/api/auth/login";
    private static final String PATH_USER = "api/auth/user/";

    public ChecksForUser() {
        RestAssured.baseURI = BASE_URI;
    }

    @Step(" Регистрация пользователя")
    public ValidatableResponse createUserWithOkStatus(CreateUser user) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .post(PATH_REGISTER)
                .then();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .when()
                .delete(PATH_USER)
                .then().log().all();
    }

    @Step("Ответ успешный для получения токена")
    public String goodResponse(ValidatableResponse response) {
        return response
                .assertThat()
                .statusCode(HTTP_OK)
                .extract()
                .path("accessToken");
    }

    @Step("Авторизовать пользователя")
    public ValidatableResponse autorizationOfUser(DataForLoginUser user) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .post(PATH_LOGIN)
                .then();
    }
}
