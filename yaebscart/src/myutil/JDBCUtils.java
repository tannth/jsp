package myutil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import oracle.jdbc.pool.OracleConnectionCacheManager;
import oracle.jdbc.pool.OracleDataSource;

public class JDBCUtils {
	private final static String CACHE_NAME = "MYCACHE";
	private static OracleDataSource ods = null;

	private JDBCUtils() {

	}

	static {
		System.out.println("OracleDataSource Initialization");
		try {
			ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
			ods.setUser("hr");
			ods.setPassword("hr");
			// caching parms
			ods.setConnectionCachingEnabled(true);
			ods.setConnectionCacheName(CACHE_NAME);
			Properties cacheProps = new Properties();
			cacheProps.setProperty("MinLimit", "1");
			cacheProps.setProperty("MaxLimit", "4");
			cacheProps.setProperty("InitialLimit", "1");
			cacheProps.setProperty("ConnectionWaitTimeout", "5");
			cacheProps.setProperty("ValidateConnection", "true");

			ods.setConnectionCacheProperties(cacheProps);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return getConnection("env. unspecified");
	}

	public static Connection getConnection(String env) throws SQLException {
		System.out.println("Request connection for " + env);
		if (ods == null) {
			throw new SQLException("OracleDataSource is null.");
		}
		return ods.getConnection();
	}

	public static void closePooledConnections() throws SQLException {
		if (ods != null) {
			ods.close();
		}
	}

	public static void listCacheInfos() throws SQLException {
		OracleConnectionCacheManager occm = OracleConnectionCacheManager
				.getConnectionCacheManagerInstance();
		System.out.println(occm.getNumberOfAvailableConnections(CACHE_NAME)
				+ " connections are available in cache " + CACHE_NAME);
		System.out.println(occm.getNumberOfActiveConnections(CACHE_NAME)
				+ " connections are active");

	}
}
