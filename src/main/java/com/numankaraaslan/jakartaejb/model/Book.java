package com.numankaraaslan.jakartaejb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// all packages are jakarta.persistence not javax.persistance
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
// this is a hibernate managed entity
// \"JakartaEE\" is the schema name and \"Book\" is the table name
// if you are using postgresql, you need the queries like create table "jakartaee"."obs" with double quotes
// that's why i have to use \" on schema and table names
@Table(schema = "\"JakartaEE\"", name = "\"Book\"")
//Remember to create "JakartaEE" schema in posgtresql database
//CREATE SCHEMA JakartaEE AUTHORIZATION postgres; GRANT ALL ON SCHEMA JakartaEE TO postgres;
public class Book
{
	@Id
	// Identity works nice with postgresql and every table will have their own sequence
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	public Book(String name, int year, String author)
	{
		this.name = name;
		this.year = year;
		this.author = author;
	}

	@Column
	private String name;

	@Column
	private int year;

	@Column
	private String author;
}
