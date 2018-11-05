package com.hackdocs.model;

import com.hackdocs.model.response.FollowupEventInput;
import com.hackdocs.model.response.Payload;
import com.hackdocs.model.response.payload.Google;
import com.hackdocs.model.response.payload.Telegram;
import com.hackdocs.model.response.payload.google.RichResponse;
import com.hackdocs.model.response.payload.google.richResponse.ItemSimpleResponse;
import com.hackdocs.model.response.payload.google.richResponse.item.SimpleResponse;
import lombok.Data;

@Data
public class Response {

    Payload payload;

    //    String fulfillmentText;
//    List<FulfillmentMessage> fulfillmentMessages;
//    String source;
//    List<OutputContext> outputContexts;
    FollowupEventInput followupEventInput;


    private Response(Payload payload, FollowupEventInput followupEventInput) {
        this.payload = payload;
//        this.followupEventInput = followupEventInput;
    }


    public static Response buildGoogleResponse(String text) {
        SimpleResponse simpleResponse = new SimpleResponse(text);
        ItemSimpleResponse itemSimpleResponse = new ItemSimpleResponse(simpleResponse);
        RichResponse richResponse = new RichResponse(itemSimpleResponse);
        Google google = new Google(true, richResponse);
        Payload payload = new Payload(google);
        FollowupEventInput followupEventInput = new FollowupEventInput();
        followupEventInput.name = "Cell phone";

        Response response = new Response(payload, followupEventInput);
        return response;
    }

    public static Response buildTelegramResponse(String text) {
        Telegram telegram = new Telegram(text);
        Payload payload = new Payload(telegram);
        FollowupEventInput followupEventInput = new FollowupEventInput();
        followupEventInput.name = "Cell phone";
        return new Response(payload, followupEventInput);
    }

}
