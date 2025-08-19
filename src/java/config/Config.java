package config;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Classe que contém as constantes de configuração da aplicação
 */
public final class Config {

    public static final String JDBC_DRIVER = "org.postgresql.Driver";
    public static final String JDBC_URL = "jdbc:postgresql://localhost:5432/elivros?useUnicode=true&characterEncoding=UTF-8";
    public static final String JDBC_USUARIO = "postgres";
    public static final String JDBC_SENHA = "1325";

    private Config() {
        
    }

}
