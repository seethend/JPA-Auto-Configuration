package com.seeth.handlers.custom;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.Card;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.seeth.handlers.IntentHandler;
import com.seeth.services.UserService;
import com.seeth.utils.AlexaUtils;

@Component
public class EmailIntentHandler implements IntentHandler {
	
	@Autowired
	UserService userService;
	
	@Override
	public SpeechletResponse handleIntent(Intent intent, IntentRequest request, Session session) {
		
		System.out.println("##################################################");
		System.out.println("Inside EmailIntentHandler");
		System.out.println("##################################################");
		
		String username = intent.getSlot("userdata").getValue();
		String email = "not found";
//		String email = userService.getEmail(username);
		System.out.println("##################################################");
		System.out.println("Email of User " + username + " is " + email);
		System.out.println("##################################################");
		Card card = AlexaUtils.newCard("Users Skill", "Email of User " + username + " is " + email);
		
		PlainTextOutputSpeech speech = AlexaUtils.newSpeech("Email of User " + username + " is " + email, AlexaUtils.inConversationMode(session));
		
		AlexaUtils.setConversationMode(session, true);
		
		return AlexaUtils.newSpeechletResponse( card, speech, session, false);
	}

}
