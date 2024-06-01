package com.prj.chatapp.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Grp")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Grp {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String grpId;
	private String grpName;
	private String grpDesc;
	private String grpPic;
	
	@ManyToMany
	@JoinTable(name = "user_grp", joinColumns = @JoinColumn(name = "grp_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<Userr> users;
	
	@OneToMany(mappedBy = "grp")
	private Set<GrpChat> grpChats;
}
