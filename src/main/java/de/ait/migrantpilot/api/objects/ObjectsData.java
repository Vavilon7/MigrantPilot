package de.ait.migrantpilot.api.objects;

import de.ait.migrantpilot.dto.AuthRequestDto;

import static de.ait.migrantpilot.config.AppConfigApi.getProperty;

//the class stores different types of data for different objects
public class ObjectsData {

    public static AuthRequestDto requestDto = AuthRequestDto.builder()
            .email(getProperty("default.email"))
            .password(getProperty("default.password"))
            .build();
}
