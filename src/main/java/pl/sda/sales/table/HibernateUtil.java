package pl.sda.sales.table;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    public final static HibernateUtil INSTANCE = new HibernateUtil();
    private final SessionFactory sessionFactor;

    private HibernateUtil() {
        this.sessionFactor = loadConfigFile();
    }

    public SessionFactory loadConfigFile() {

        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml") // <-- jesli nie podamy nazwy - ustawi sie domyslna
                .build();

        Metadata metadata = new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();

        return metadata.getSessionFactoryBuilder().build();
    }
    public SessionFactory getSessionFactory() {
        return sessionFactor;
    }
}
