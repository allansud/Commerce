package br.com.commerce.util;

import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import br.com.commerce.categoria.Categoria;
import br.com.commerce.comentario.Comentario;
import br.com.commerce.endereco.Endereco;
import br.com.commerce.fabricante.Fabricante;
import br.com.commerce.itemvenda.ItemVenda;
import br.com.commerce.permissao.Permissao;
import br.com.commerce.produto.Produto;
import br.com.commerce.quote.Quote;
import br.com.commerce.usuario.Usuario;
import br.com.commerce.venda.Venda;
import br.com.commerce.vendedor.Vendedor;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory = buildSessionFactory(); 
			
	private static SessionFactory buildSessionFactory(){
		try {
			
			Configuration cfg = new Configuration();
			
			Properties p = new Properties();
			
			p.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
			p.setProperty("hibernate.connection.driver_class","com.mysql.jdbc.Driver");
			//p.setProperty("hibernate.connection.username", "root");
			//p.setProperty("hibernate.connection.password", "");
			p.setProperty("hibernate.connection.datasource", "java:/comp/env/jdbc/CommerceDB");
			//p.setProperty("hibernate.hbm2ddl.auto", "create");
			p.setProperty("hibernate.current_session_context_class", "thread");			
			
			cfg.addAnnotatedClass(Endereco.class);
			cfg.addAnnotatedClass(Usuario.class);
			cfg.addAnnotatedClass(Permissao.class);
			cfg.addAnnotatedClass(Quote.class);
			cfg.addAnnotatedClass(Categoria.class);
			cfg.addAnnotatedClass(Fabricante.class);
			cfg.addAnnotatedClass(Produto.class);
			cfg.addAnnotatedClass(Comentario.class);
			cfg.addAnnotatedClass(ItemVenda.class);
			cfg.addAnnotatedClass(Venda.class);
			cfg.addAnnotatedClass(Vendedor.class);
			
			cfg.setProperties(p);
			
			ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
			sessionFactory = cfg.buildSessionFactory(sr);
			
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return sessionFactory;
	}
	
	public static SessionFactory getSessionFactory(){
		if(sessionFactory == null){
			sessionFactory = buildSessionFactory();
			return sessionFactory;
		}else{
			return sessionFactory;
		}
	}
}
