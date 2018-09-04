package com.seeth.handlers.builtins;

import org.springframework.stereotype.Component;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.Card;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.seeth.handlers.IntentHandler;
import com.seeth.utils.AlexaUtils;

@Component
public class HelpIntentHandler implements IntentHandler {

	@Override
	public SpeechletResponse handleIntent(Intent intent, IntentRequest request, Session session) {
		
		Card card = AlexaUtils.newCard("Users Skill", "Ask from following questions \n 1. {userName} email \n 2. get email id of {userName} \n 3. email of {userName}");
		
		PlainTextOutputSpeech speech = AlexaUtils.newSpeech("Ask from following questions {userName} email or get email id of {userName} or email of {userName}", AlexaUtils.inConversationMode(session));
		
		AlexaUtils.setConversationMode(session, true);
		
		return AlexaUtils.newSpeechletResponse( card, speech, session, false);
	}

}
