package com.galib.springBootOauth2.permission;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "permission")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Permission implements Serializable{
	 	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Integer id;
	    @Column(name = "name")
	    private String name;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
}
