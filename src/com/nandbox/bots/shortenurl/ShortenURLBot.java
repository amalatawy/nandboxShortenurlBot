package com.nandbox.bots.shortenurl;

import static com.nandbox.bots.shortenurl.util.Utility.getTokenFromPropFile;
import static com.nandbox.bots.shortenurl.util.Utility.isNotEmpty;

import com.nandbox.bots.api.Nandbox;
import com.nandbox.bots.api.NandboxClient;
import com.nandbox.bots.api.data.Chat;
import com.nandbox.bots.api.data.User;
import com.nandbox.bots.api.inmessages.ChatAdministrators;
import com.nandbox.bots.api.inmessages.ChatMember;
import com.nandbox.bots.api.inmessages.ChatMenuCallback;
import com.nandbox.bots.api.inmessages.IncomingMessage;
import com.nandbox.bots.api.inmessages.InlineMessageCallback;
import com.nandbox.bots.api.inmessages.InlineSearch;
import com.nandbox.bots.api.inmessages.MessageAck;
import com.nandbox.bots.api.inmessages.PermanentUrl;

import net.minidev.json.JSONObject;

public class ShortenURLBot {

	public static void main(String[] args) throws Exception {

		String token = getTokenFromPropFile();

		NandboxClient client = NandboxClient.get();
		client.connect(token, new Nandbox.Callback() {
			Nandbox.Api api = null;

			@Override
			public void onConnect(Nandbox.Api api) {
				this.api = api;
			}

			@Override
			public void onClose() {
				System.out.println("ONCLOSE");
			}

			@Override
			public void onError() {
				System.out.println("ONERROR");
			}

			@Override
			public void onReceive(JSONObject obj) {

			}

			@Override
			public void onReceive(IncomingMessage incomingMsg) {

				String chatId = incomingMsg.getChat().getId();
				String replyMsg;
				System.out.println("incomingMsg.getType() : " + incomingMsg.getType());
				if (incomingMsg.isTextMsg()) {

					ShortenURL googleShortenURL = new ShortenURL();
					String shortURLReply = googleShortenURL.getShortURL(incomingMsg.getText());

					if (isNotEmpty(shortURLReply)) {

						replyMsg = shortURLReply;

						api.sendText(chatId, replyMsg);

					}
				} else {
					replyMsg = ("This bot supports text only, please send URL link");
					api.sendText(chatId, replyMsg);
				}

			}

			@Override
			public void onChatAdministrators(ChatAdministrators chatAdministrators) {

			}

			@Override
			public void onChatMember(ChatMember chatMember) {

			}

			@Override
			public void onChatMenuCallBack(ChatMenuCallback chatMenuCallback) {

			}

			@Override
			public void onInlineMessageCallback(InlineMessageCallback inlineMsgCallback) {

			}

			@Override
			public void onChatDetails(Chat chat) {

			}

			@Override
			public void onInlineSearh(InlineSearch inlineSearch) {

			}

			@Override
			public void onMessagAckCallback(MessageAck msgAck) {

			}

			@Override
			public void onMyProfile(User user) {

			}

			@Override
			public void onUserDetails(User user) {

			}

			@Override
			public void onUserJoinedBot(User user) {

			}

			@Override
			public void permanentUrl(PermanentUrl permenantUrl) {

			}

			@Override
			public void userLeftBot(User user) {

			}

			@Override
			public void userStartedBot(User user) {

			}

			@Override
			public void userStoppedBot(User user) {

			}

		});

	}

}
