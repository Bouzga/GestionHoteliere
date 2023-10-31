package com.example.gestionhoteliere.payload.response;



import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserInfoResponse {
	private String token;
	private String type = "Bearer";
	private String id;
	private String username;
	private String firstName;
	private String lastName;

	private int age;

	private String phone;
	private String email;
	private List<String> roles;

	public UserInfoResponse(String accessToken, String id, String username, String email,String firstName,String lastName,int age,String phone, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.firstName = firstName;
		this.lastName=lastName;
		this.age=age;
		this.phone=phone;
	}
	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}
}