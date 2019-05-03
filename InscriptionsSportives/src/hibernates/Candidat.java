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
@Table(name = "Candidat")
public class Candidat
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_candidat")
	private int id_candidat;
	
	@Column(name = "nom_candidat")
	private String nom_candidat;
	
	Candidat(){}

	public Candidat(int num_candidat, String nom_candidat)
	{
		this.nom_candidat = nom_candidat;
	}
	
	public static Candidat setCandidat(int num_candidat, String nom_candidat)
	{
		return new Candidat(num_candidat, nom_candidat);
	}
	
	public static java.util.List getCandidat()
	{
		try
		{
			Session s = hibernates.BDD.getSession();
			Query requete = s.createQuery("from Candidat");
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

