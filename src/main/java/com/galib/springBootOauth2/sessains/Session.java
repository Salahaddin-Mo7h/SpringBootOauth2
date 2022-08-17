package com.galib.springBootOauth2.sessains;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.galib.springBootOauth2.permission.Permission;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.security.oauth2.common.OAuth2AccessToken;


@Entity
@Table(name = "session")
public class Session implements Serializable {

	private static final long serialVersionUID = -5751308177922323492L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "accessToken")
    private String accessToken;
    private String userName;
    private Date  loginTime;
    private  Date logoutTime;
    
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "permission_role", joinColumns = {
//            @JoinColumn(name = "role_id", referencedColumnName = "id")}, inverseJoinColumns = {
//            @JoinColumn(name = "permission_id", referencedColumnName = "id")})
//    private List<Permission> permissions;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

	@Override
	public String toString() {
		return "Session [id=" + id + ", accessToken=" + accessToken + ", userName=" + userName
				+ ", loginTime=" + loginTime + ", logoutTime=" + logoutTime + "]";
	}



}
