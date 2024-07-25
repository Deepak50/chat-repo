package com.prj.chatapp.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prj.chatapp.dto.ChatDto;
import com.prj.chatapp.dto.ChatListDto;
import com.prj.chatapp.dto.ChatWithProfilePicDto;
import com.prj.chatapp.dto.RecentChatDto;
import com.prj.chatapp.dto.RecentChatWithUserName;
import com.prj.chatapp.dto.ResponseDto;
import com.prj.chatapp.dto.UserChatDto;
import com.prj.chatapp.dto.UserChatMapDto;
import com.prj.chatapp.entity.Chat;
import com.prj.chatapp.repository.ChatRepository;
import com.prj.chatapp.repository.FriendRepository;
import com.prj.chatapp.repository.UserRepository;
import com.prj.chatapp.service.ChatService;
import com.prj.chatapp.serviceUtil.DateUtil;
import com.prj.chatapp.serviceUtil.SortChat;
import com.prj.chatapp.serviceUtil.SortUserChatMap;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatRepository chatRepository;

	@Autowired
	private FriendRepository friendRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper m;
	

	@Override
	public ResponseDto saveChat(ChatDto chatDto) {
		Chat chat = chatRepository.save(m.map(chatDto, Chat.class));
		ResponseDto responseDto = new ResponseDto();

		responseDto.setStatusCode(200);
		responseDto.setStatus("Success");
		responseDto.setMessage("Successfully saved chat");
		responseDto.setData(chat);
		return responseDto;
	}

	public ResponseDto getChatList(String userId) {
		List<Object[]> chatList = chatRepository.getChatList(userId);
		System.out.println("Chat list: " + chatList.toString());
		List<ChatListDto> chatListDtos = new ArrayList<ChatListDto>();

		ResponseDto responseDto = new ResponseDto();
		
		Set<Object> distinctEmails = chatList.stream().flatMap(Arrays::stream).map(chat -> (String) chat)
				.filter(user -> (user != userId)).collect(Collectors.toSet());

		responseDto.setStatusCode(200);
		responseDto.setMessage("Successfully fetched");
		responseDto.setStatus("Success");
		responseDto.setData(chatListDtos);
		return responseDto;
	}

	// this is good when there is too much data and you want api call for each click
	// on chat
	public ResponseDto getChat(String fromUserId, String toUserId) {

		ResponseDto responseDto = new ResponseDto();
		List<Object[]> chats = chatRepository.getChat(fromUserId, toUserId);

		List<UserChatDto> userChatDtos = new ArrayList<UserChatDto>();

		chats.forEach(chat -> {
			UserChatDto userChatDto = new UserChatDto();
			userChatDto.setChatId((Long) chat[0]);
			userChatDto.setChatDesc((String) chat[1]);
			userChatDto.setDeliveredTime((Date) chat[2]);
			userChatDto.setSeenTime((Date) chat[3]);
			userChatDto.setSentTime((Date) chat[4]);
			userChatDto.setFromUserId((String) chat[5]);
			userChatDto.setToUserId((String) chat[6]);

			userChatDtos.add(userChatDto);
		});

		responseDto.setStatusCode(200);
		responseDto.setData(userChatDtos);
		return responseDto;
	}

	// this is good when there is too much data and you want api call for each click
	// on chat
	@Override
	public ResponseDto getFriends(String userId) {
		List<Object[]> recentChatObj = friendRepository.getRecentChat(userId);

		List<Object[]> allUsers = userRepository.getAllUsers();
		Map<String, String> userMap = allUsers.stream()
				.collect(Collectors.toMap(o -> (String) o[0], o -> (String) o[1]));

		List<RecentChatDto> recentChats = recentChatObj.stream().map(
				a -> new RecentChatDto(a[0] != null ? (String) a[0] : (String) a[3], DateUtil.recentDate(a[2], a[5])))
				.collect(Collectors.toList());

		final List<RecentChatDto> tmpChats = recentChats;

		List<String> friendsObj = friendRepository.getFriends(userId);
		List<RecentChatDto> friends = friendsObj.stream().map(a -> new RecentChatDto(a, null))
				.collect(Collectors.toList());

		// remove duplicates from friends
		friends = friends.stream().filter(a -> !(tmpChats.stream().map(b -> b.getUserName())
				.collect(Collectors.toList()).contains(a.getUserName()))).collect(Collectors.toList());

		List<RecentChatWithUserName> recentChatsWithUserName = Stream
				.concat(recentChats.stream()
						.map(a -> new RecentChatWithUserName(a.getUserName(), userMap.get(a.getUserName()),
								a.getSentDate())),
						friends.stream().map(a -> new RecentChatWithUserName(a.getUserName(),
								userMap.get(a.getUserName()), a.getSentDate())))
				.collect(Collectors.toList());

		ResponseDto responseDto = new ResponseDto();
		responseDto.setData(recentChatsWithUserName);
		return responseDto;
	}

	/*this is a better approach for a smaller scale app with less chats. Looks
	 impressive from the ui when everything is faster because the chat and the
	 friendlist are sent to the in 1 API call reducing network time between fetching
	 chat for each user*/
	public ResponseDto getEverything(String userId) {

		ResponseDto responseDto = new ResponseDto();

		// fetch the chats for the logged in user. ie. sent from him and sent to him
		List<Chat> chats = chatRepository.getLoggedInUserChats(userId);

		// segragate users and their chats together
		Map<String, List<Chat>> userChatMap = new HashMap<String, List<Chat>>();
		System.out.println("Logged in user: " + userId);
		chats.forEach(chat -> {
			// condition to avoid segregating on the basis of loggedInUser. (ie. to fetch
			// list of all users logInUser has interacted)
			if (!chat.getFromUser().getUserId().equals(userId)) {
				if (userChatMap.containsKey(chat.getFromUser().getUserId())) {
					List<Chat> userChats = userChatMap.get(chat.getFromUser().getUserId());
					
					ChatWithProfilePicDto c = new ChatWithProfilePicDto();
//					c.getUserChats()
					userChats.add(chat);
//					ChatWithProfilePicDto chatWithProfilePicDto = new ChatWithProfilePicDto();
//					chatWithProfilePicDto
					userChatMap.put(chat.getFromUser().getUserId(), userChats);
					
				} else {
					List<Chat> userChats = new ArrayList<Chat>();
					userChats.add(chat);
					userChatMap.put(chat.getFromUser().getUserId(), userChats);
				}
			} else {
//				System.out.println("coming here for : "+chat.getToUser().getUserId());
				if (userChatMap.containsKey(chat.getToUser().getUserId())) {
					List<Chat> userChats = userChatMap.get(chat.getToUser().getUserId());
					userChats.add(chat);
					userChatMap.put(chat.getToUser().getUserId(), userChats);
				} else {
					List<Chat> userChats = new ArrayList<Chat>();
					userChats.add(chat);
					userChatMap.put(chat.getToUser().getUserId(), userChats);
				}
			}
		});

		// utility query for easy mapping of userId and name
		List<Object[]> allUsers = userRepository.getAllUsers();
		Map<String, String> userMap = allUsers.stream()
				.collect(Collectors.toMap(o -> (String) o[0], o -> (String) o[1]));

		List<UserChatMapDto> userChatMapDtos = new ArrayList<UserChatMapDto>();
		// traverse the userChatMap and sort each user's chat in decreasing order of
		// date and time.
		for (Map.Entry<String, List<Chat>> entry : userChatMap.entrySet()) {
			List<Chat> chatsToSort = entry.getValue();

			// sort using comparator class
			SortChat sortChat = new SortChat();
			Collections.sort(chatsToSort, sortChat);
			entry.setValue(chatsToSort);

			// convert maps to dto so its more readable and traversible from the UI
			UserChatMapDto userChatMapDto = new UserChatMapDto();
			if (!chatsToSort.get(0).getFromUser().getUserId().equals(userId)) {
				userChatMapDto.setProfilePic(chatsToSort.get(0).getFromUser().getProfilePic());
			}
			else {
				userChatMapDto.setProfilePic(chatsToSort.get(0).getToUser().getProfilePic());
			}
			userChatMapDto.setUserId(entry.getKey());
			userChatMapDto.setName(userMap.get(entry.getKey()));
			userChatMapDto.setChats(entry.getValue());

			userChatMapDtos.add(userChatMapDto);
		}

		// sort the chatList on the basis of most recent chat (user) at the top
		SortUserChatMap sortUserChatMap = new SortUserChatMap();
		Collections.sort(userChatMapDtos, sortUserChatMap);
		
		
		List<String> friendsObj = friendRepository.getFriends(userId);
		List<UserChatMapDto> friends = friendsObj.stream().map(a -> new UserChatMapDto(a, userMap.get(a),null, null))
				.collect(Collectors.toList());
		
		System.out.println("frineds: "+friends.toString());
		
		List<UserChatMapDto> friendsWithNoChatHistory = friends.stream().filter(a -> !(userChatMapDtos.stream().map(b -> b.getUserId())
				.collect(Collectors.toList()).contains(a.getUserId()))).collect(Collectors.toList());
		
		System.out.println("fwnch: "+ friendsWithNoChatHistory);
//		friend
		
		friendsWithNoChatHistory.forEach((item)->{userChatMapDtos.add(0,item);});
		
		responseDto.setData(userChatMapDtos);
		return responseDto;
	}

}
