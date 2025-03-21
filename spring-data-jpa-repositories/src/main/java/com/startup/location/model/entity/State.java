package com.startup.location.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class State implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, unique = true)
	private String name;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Type type;
	private String region;
	private String capital;
	private int porpulation;

	public State(String name, Type type, String region, String capital, int porpulation) {
		super();
		this.name = name;
		this.type = type;
		this.region = region;
		this.capital = capital;
		this.porpulation = porpulation;
	}

	public enum Type {
		State("State"), Region("Region"), Union("Union Territory");

		private String value;

		private Type(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

}
