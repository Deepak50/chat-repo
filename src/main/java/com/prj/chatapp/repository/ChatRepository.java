package com.prj.chatapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prj.chatapp.entity.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {
//	public ResponseDto saveChat(ChatDto chatDto);
	@Query(value="SELECT c.to_user_id, u.user_name, u.profile_pic FROM chat c JOIN user u ON u.user_id = c.to_user_id WHERE c.from_user_id = :userId", nativeQuery = true)
	public List<Object[]> getChatList(String userId);
	
	@Query(value="SELECT c from Chat c where c.fromUser.userId = :fromUserId or c.fromUser.userId = :toUserId order by sentTime desc")
	public List<Chat> getChat(String fromUserId, String toUserId);
	
	
}
