package com.prj.chatapp.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.prj.chatapp.entity.Chat;
import com.prj.chatapp.entity.Grp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private String userId;
    private String email;
    private String password;
    private boolean active;
    private String roles;
    private String profilePic;
    private Set<Chat> chats = new HashSet<>();
    private Set<Grp> grps = new HashSet<>();
    private Date joinedDate;
}