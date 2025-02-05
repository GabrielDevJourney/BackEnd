package com.demoTest.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@RequestMapping(name = "Clients")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;

	@Column(name = "Nif")
	private int nif;

	@Column(name = "Name")
	private String name;

	@Column(name = "Address")
	private String address;

	@Column(name = "Email")
	private String email;


}
