package hibernates;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import commandLineMenus.List;

@Entity
@Table(name = "Personne")
public class Personne
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_personne")
	private int id_personne;

	@Column(name = "nom_personne")
	private String nom_personne;

	@Column(name = "prenom")
	private String prenom;
	
	@Column(name = "email")
	private String email;
	
	Personne(){}

	public Personne(String nom, String prenom, String email)
	{
		this.nom_personne = nom;
		this.prenom = prenom;
		this.email = email;
	}
	
	public static Personne setPersonne(String nom, String prenom, String email) {
		
		return new Personne(nom, prenom, email);
	}
	
	@SuppressWarnings("rawtypes")
	public static java.util.List getPersonne() {
		
		try
		{
			Session s = hibernates.BDD.getSession();
			Query requete = s.createQuery("from Personne");
			java.util.List result = requete.list();
			s.close();
			return result;
		}
		catch (HibernateException ex)
		{
			throw new RuntimeException("Probleme de configuration : "
					+ ex.getMessage(), ex);
		}
	}
	
	
}