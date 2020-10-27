package com.kostapo.bot.controller;

import com.kostapo.bot.Bot;
import com.kostapo.bot.model.Request;
import com.kostapo.bot.repository.RequestRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class WebHookController {
    private final Bot myBot;
    private final RequestRepository requestRepository;

    public WebHookController(Bot myBot, RequestRepository requestRepository) {
        this.myBot = myBot;
        this.requestRepository = requestRepository;
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        Request request = new Request();
        request.setChat_id(Math.toIntExact(update.getMessage().getChatId()));
        request.setUser_name(update.getMessage().getChat().getUserName());
        requestRepository.save(request);
        return myBot.onWebhookUpdateReceived(update);
    }



}




