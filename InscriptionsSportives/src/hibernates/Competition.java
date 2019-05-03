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
@Table(name = "Competition")
public class Competition
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_competition")
	private int id_competition;

	@Column(name = "nom_competition")
	private String nom_competition;
	
	@Column(name = "date_cloture")
	private LocalDate date_cloture;
	
	@Column(name = "type")
	private boolean type;
	
	Competition(){}

	public Competition(String nom_competition, LocalDate date_cloture, boolean type)
	{
		this.nom_competition = nom_competition;
		this.date_cloture = date_cloture;
		this.type = type;
	}
	
	public static Competition setCompetition(String nom_competition, LocalDate date_cloture, boolean type) {
		
		return new Competition(nom_competition,date_cloture,type);
	}
	
	@SuppressWarnings("rawtypes")
	public static java.util.List getCompetition() {
		try
		{
			Session s = hibernates.BDD.getSession();
			Query requete = s.createQuery("select * from Competition");
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

