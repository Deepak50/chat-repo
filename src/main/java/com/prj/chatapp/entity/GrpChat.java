package com.prj.chatapp.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "GrpChat")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GrpChat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long grpChatId;
	
	@ManyToOne
	@JoinColumn(name = "from_user_id")
	private Userr fromUser;
	
	@ManyToOne
	@JoinColumn(name = "grp_id")
	private Grp grp;
	
	private String chatDesc;
	private Date sentTime;
	private Date deliveredTime;
	private Date seenTime;
}
