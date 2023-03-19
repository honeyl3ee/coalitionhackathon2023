package com.ft.modumoa.service;

import com.ft.modumoa.config.SlackBotProperties;
import com.ft.modumoa.config.jwt.JwtProperties;
import com.ft.modumoa.entity.Party;
import com.ft.modumoa.entity.User;
import com.ft.modumoa.repository.PartyRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.ft.modumoa.config.SlackBotProperties.*;
import static com.ft.modumoa.config.jwt.JwtProperties.TOKEN_PREFIX;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpMethod.GET;

@Service
@RequiredArgsConstructor
public class SlackBotService {

    @Value("${slack-bot.token}")
    private String slackToken;

    @Value("${slack-bot.post-message-uri}")
    private String POST_MESSAGE_URI;

    @Value("${slack-bot.get-user-email-uri}")
    private String GET_USER_EMAIL_URI;

    @Autowired
    private PartyRepository partyRepository;

    public void isFull(Long partyId, User user, String message) {

        Party party = partyRepository.getReferenceById(partyId);

        if (party.getMax() == party.getCurrent()) {
            sendMessageToUser(partyId, message);
        }
    }

    public void sendMessageToUser(Long partyId, String message) {

        Party party = partyRepository.getReferenceById(partyId);
        String writer = party.getWriter().getIntraId();

        HttpHeaders headers = new HttpHeaders();

        headers.add(AUTHORIZATION, TOKEN_PREFIX + slackToken);
        headers.add(CONTENT_TYPE, JwtProperties.CONTENT_TYPE);

        String slackId = getSlackIdByEmail(writer);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(SlackBotProperties.CHANNEL, slackId);
        jsonObject.put(SlackBotProperties.TEXT, message);


        String body = jsonObject.toString();
        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.exchange(POST_MESSAGE_URI, HttpMethod.POST, requestEntity, String.class);
    }

    public String getSlackIdByEmail(String intraId) {

        String email = intraId + EMAIL;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + slackToken);
        headers.add("Content-type", "application/x-www-form-urlencoded");

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                GET_USER_EMAIL_URI + EMAIL_PATH_KEY + email,
                GET,
                requestEntity,
                String.class
        );

        JSONObject profile = new JSONObject(responseEntity.getBody()).getJSONObject(KEY_USER);

        return profile.get(KEY_ID).toString();
    }

    public String getParticipationMessage(Long partyId, User user) {

        Party party = partyRepository.getReferenceById(partyId);
        return "42gether 모집 안내입니다.\n" + user.getIntraId() + "님이 [" + party.getTitle() + "]";
    }

    public String getWhenFullMessage(Long partyId, User user) {

        Party party = partyRepository.getReferenceById(partyId);
        return "42gether 모집 안내입니다.\n[" + party.getTitle() + "]";
    }
}