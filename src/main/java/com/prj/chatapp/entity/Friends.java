package com.prj.chatapp.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Friends")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Friends {
	@EmbeddedId
	private FriendsCk friendsCk;
}
