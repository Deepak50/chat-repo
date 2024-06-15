package com.prj.chatapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prj.chatapp.entity.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {

	@Query(value="select (to_user_id), from_user_id from chat c where to_user_id = :userId or from_user_id = :userId", nativeQuery = true)
	public List<Object[]> getChatList(String userId);
	
	@Query(value="SELECT * from Chat c where (c.from_user_id = :fromUserId and c.to_user_id=:toUserId) or (c.from_user_id = :toUserId and c.to_user_id=:fromUserId) order by sent_time desc", nativeQuery = true)
	public List<Object[]> getChat(String fromUserId, String toUserId);
	
	@Query(value = "select c from Chat c where (c.fromUser.userId= :userId or c.toUser.userId = :userId)")
	public List<Chat> getLoggedInUserChats(String userId);	
}
