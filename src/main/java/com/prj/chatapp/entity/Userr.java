package com.prj.chatapp.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "User")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Userr {
		@Id
		private String userId;
		private String userName;
	    private String email;
	    private String password;
	    private boolean active;
	    private String roles;
	    private String profilePic;
	    @OneToMany(mappedBy = "toUser")
	    private Set<Chat> chats = new HashSet<>();
	    @ManyToMany(mappedBy = "users")
	    private Set<Grp> grps = new HashSet<>();
	    private Date joinedDate;
}