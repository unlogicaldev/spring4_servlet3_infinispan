package com.spring4.servlet3.sample.web.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebsocketController {

	@RequestMapping("/websocket")
	public String socketTest() throws Exception {
		return "websocket/index";
	}
	    
	@MessageMapping("/websocket/hello")
    @SendTo("/websocket/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(3000); // simulated delay
        System.out.println("Hello, " + message.getName() + "!");
        return new Greeting("Hello, " + message.getName() + "!");
    }
}
