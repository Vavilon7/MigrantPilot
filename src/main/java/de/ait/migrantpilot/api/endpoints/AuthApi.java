package de.ait.migrantpilot.api.endpoints;

import de.ait.migrantpilot.dto.AuthRequestDto;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static de.ait.migrantpilot.config.AppConfigApi.getProperty;
import static de.ait.migrantpilot.api.objects.ObjectsData.requestDto;
import static io.restassured.RestAssured.given;

//the class stores different methods for requests
public class AuthApi {

    public static ValidatableResponse getRegistrationResponse(AuthRequestDto requestDto) {
        return given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .post(getProperty("register.path"))
                .then();
    }

    public static ValidatableResponse getConfirmResponse(String confirmCode) {
        return given()
                .get(getProperty("confirm.path") + confirmCode)
                .then();
    }

    public static ValidatableResponse getAuthResponse() {
        return given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .post(getProperty("login.path"))
                .then();
    }
}
