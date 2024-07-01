package com.prj.chatapp.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Chat")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long chatId;
	
	@ManyToOne
	@JoinColumn(name = "from_user_id")
	private Userr fromUser;
	
	@ManyToOne
	@JoinColumn(name = "to_user_id")
	private Userr toUser;
	@Lob
	private String chatDesc;
	@CreationTimestamp
	private Date sentTime;
	private Date deliveredTime;
	private Date seenTime;
}
