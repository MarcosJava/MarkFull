package br.com.mrcsfelipe.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name="person",
	   schema="public")
@SequenceGenerator(name=Person.SEQUENCE_NAME, 
				   sequenceName= Person.SEQUENCE_NAME,
				   initialValue = 1, 
				  // allocationSize = 53, a quantidade que vai ficar na memoria
				   schema="public")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person implements Serializable {

	
	public final static String SEQUENCE_NAME = "SEQUENCIA_PERSON";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@XmlElement(name="identify")
	private Integer id;
	
	@XmlElement(name="nome")
	private String nome;
	
	@Temporal(TemporalType.DATE)
	@XmlElement(name="dataNascimento")
	private Date dtNascimento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", nome=" + nome + ", dtNascimento="
				+ dtNascimento + "]";
	}
	
	
	
	
	
}
