package com.google.controller;

import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.dialogflow.v2beta1.model.GoogleCloudDialogflowV2WebhookRequest;
import com.google.api.services.dialogflow.v2beta1.model.GoogleCloudDialogflowV2WebhookResponse;
import com.google.service.GoogleService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class GoogleController {

    private static JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();
    private final GoogleService googleService;
    private final String errorMessage = "Sorry you didn't send enough to process";

    @SneakyThrows
    @PostMapping("/karen/webhook")
    public ResponseEntity<?> googleHandler(@RequestBody String requestStr) {
        GoogleCloudDialogflowV2WebhookResponse response = new GoogleCloudDialogflowV2WebhookResponse();
        GoogleCloudDialogflowV2WebhookRequest request = jacksonFactory.createJsonParser(requestStr).parse(GoogleCloudDialogflowV2WebhookRequest.class);

        Map<String, Object> params = request.getQueryResult().getParameters();

        response.setFulfillmentText(params.size() > 0 ? googleService.parametersHandler(params) : errorMessage);

        return new ResponseEntity<GoogleCloudDialogflowV2WebhookResponse>(response, HttpStatus.OK);
    }
}
