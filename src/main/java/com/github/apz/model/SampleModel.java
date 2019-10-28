package com.github.apz.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @AllArgsConstructor @ToString
public class SampleModel implements Serializable {

	public static SampleModel of(String name, Address address) {
		return new SampleModel(name, address);
	}

	private String name;
	private Address address;

	@Getter @Setter @ToString @AllArgsConstructor
	public static class Address implements Serializable {

		private String prefecture;
		private String building;
		public static Address of(String prefecture, String building) {
			return new Address(prefecture, building);
		}

//		public static Address of(String prefecture, String building, Long number) {
//			return new Address(prefecture, building, number);
//		}
//
//		private Long number;

	}
}
