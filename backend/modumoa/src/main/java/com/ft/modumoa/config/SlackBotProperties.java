package com.ft.modumoa.config;

import org.springframework.beans.factory.annotation.Value;

public interface SlackBotProperties {

    String CHANNEL = "channel";
    String TEXT = "text";
    String EMAIL_PATH_KEY = "?email=";
    String EMAIL = "@student.42seoul.kr";
    String KEY_USER = "user";
    String KEY_ID = "id";
}
