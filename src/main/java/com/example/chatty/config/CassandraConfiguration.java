//package com.example.chatty.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
//import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
//import org.springframework.data.cassandra.config.SchemaAction;
//import org.springframework.data.cassandra.core.CassandraTemplate;
//import org.springframework.data.cassandra.core.convert.CassandraConverter;
//import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
//import org.springframework.data.cassandra.core.mapping.BasicCassandraMappingContext;
//import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
//
////@Configuration
//public class CassandraConfiguration {
//
//	/**
//	 * Constant String for Keyspace
//	 */
//	private static final String KEYSPACE = "spring.cassandra.keyspace";
//	/**
//	 * Constant String for ContactPoints
//	 */
//	private static final String CONTACTPOINTS = "spring.cassandra.contactpoints";
//	/**
//	 * Constant String for Port
//	 */
//	private static final String PORT = "spring.cassandra.port";
//
//	@Autowired
//	private Environment environment;
//
//	private String getKeyspaceName() {
//		return environment.getProperty(KEYSPACE);
//	}
//
//	private String getContactPoints() {
//		return environment.getProperty(CONTACTPOINTS);
//	}
//
//	private int getPortNumber() {
//		return Integer.parseInt(environment.getProperty(PORT));
//	}
//
//	@Bean
//	public CassandraClusterFactoryBean cluster() {
//		CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
//		cluster.setContactPoints(getContactPoints());
//		cluster.setPort(getPortNumber());
//		return cluster;
//	}
//
//	@Bean
//	public CassandraMappingContext mappingContext() {
//		return new BasicCassandraMappingContext();
//	}
//
//	@Bean
//	public CassandraConverter converter() {
//		return new MappingCassandraConverter(mappingContext());
//	}
//
//	@Bean
//	public CassandraSessionFactoryBean session() throws Exception {
//		CassandraSessionFactoryBean cassandraSessionFactoryBean = new CassandraSessionFactoryBean();
//		cassandraSessionFactoryBean.setCluster(cluster().getObject());
//		cassandraSessionFactoryBean.setKeyspaceName(getKeyspaceName());
//		cassandraSessionFactoryBean.setConverter(converter());
//		cassandraSessionFactoryBean.setSchemaAction(SchemaAction.NONE);
//		return cassandraSessionFactoryBean;
//	}
//
//	@Bean
//	public CassandraTemplate cassandraTemplate() throws Exception {
//		return new CassandraTemplate(session().getObject());
//	}
//}
