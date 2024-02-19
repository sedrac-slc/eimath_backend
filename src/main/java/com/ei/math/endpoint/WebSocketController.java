package com.ei.math.endpoint;

import com.ei.math.entity.Message;
import com.ei.math.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {
    
    @Autowired
    private MessageService messageService;
    
    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/{roomId}")
    public Message chat(@DestinationVariable String roomId, Message message){
        return messageService.save(message);
    }
    
}
