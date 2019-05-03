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

@Entity
@Table(name = "Equipe")
public class Equipe
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_equipe")
	private int id_equipe;

	
	@Column(name = "nom_equipe")
	private String nom_equipe;
	
	Equipe(){}

	public Equipe(String nom_equipe)
	{
		this.nom_equipe = nom_equipe;
	}
	
	public static Equipe setEquipe(String nom_equipe) {
		
		return new Equipe(nom_equipe);
	}
	
	@SuppressWarnings("rawtypes")
	public static java.util.List getEquipe() {
		
		try
		{
			Session s = hibernates.BDD.getSession();
			Query requete = s.createQuery("from Equipe");
			@SuppressWarnings("rawtypes")
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
