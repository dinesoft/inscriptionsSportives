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
@Table(name = "Inscrire")
public class Appartenir
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_appartenir")
	private int id_appartenir;

	@Column(name = "id_equipe")
	private int id_equipe;
	
	@Column(name = "id_personne")
	private int id_personne;
	
	Appartenir(){}

	public Appartenir(int id_equipe, int id_personne)
	{
		this.id_equipe = id_equipe;
		this.id_personne = id_personne;
	}
	
	public static Inscrire setInscrire(int id_candidat, int id_competition)
	{
		return new Inscrire(id_candidat, id_competition);

	}
	
	@SuppressWarnings("rawtypes")
	public static java.util.List getInscrire()
	{
		try
		{
			Session s = hibernates.BDD.getSession();
			Query requete = s.createQuery("from Appartenir");
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
