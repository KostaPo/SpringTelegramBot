package com.kostapo.bot.controller;

import com.kostapo.bot.Bot;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class WebHookController {
    private final Bot myBot;

    public WebHookController(Bot myBot) {
        this.myBot = myBot;
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return myBot.onWebhookUpdateReceived(update);
    }



}




