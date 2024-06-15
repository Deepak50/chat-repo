package com.prj.chatapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prj.chatapp.entity.Chat;
import com.prj.chatapp.entity.Friends;

public interface FriendRepository extends JpaRepository<Friends, String> {
	
	@Query(value="select u.user_id from friends f inner join user u on u.user_id = f.friend_id where f.user_id = :userId", nativeQuery = true)
	public List<String> getFriends(String userId);
	
	@Query(value = "select c.to_user_id ,max(c.sent_time) from chat c where c.from_user_id = :userId group by c.to_user_id", nativeQuery = true)
	public List<Object[]> getSentRecentChatDate(String userId);
	
	@Query(value = "select c.from_user_id ,max(c.sent_time) from chat c where c.to_user_id = :userId group by c.from_user_id", nativeQuery = true)
	public List<Object[]> getRecievedRecentChatDate(String userId);
	
	@Query(value = "(select * from ( select c.from_user_id , c.to_user_id as to_logged_in_user, max(c.sent_time) from chat c where c.to_user_id = :userId group by c.from_user_id) as tab1 left join ( select c.to_user_id , c.from_user_id as from_logged_in_user, max(c.sent_time) from chat c where c.from_user_id = :userId group by c.to_user_id) as tab2 on tab1.from_user_id = tab2.to_user_id) union (select * from ( select c.from_user_id , c.to_user_id as to_logged_in_user, max(c.sent_time) from chat c where c.to_user_id = :userId group by c.from_user_id) as tab1 right join ( select c.to_user_id , c.from_user_id as from_logged_in_user, max(c.sent_time) from chat c where c.from_user_id = :userId group by c.to_user_id) as tab2 on tab1.from_user_id = tab2.to_user_id)", nativeQuery = true)
	public List<Object[]> getRecentChat(String userId);
}
