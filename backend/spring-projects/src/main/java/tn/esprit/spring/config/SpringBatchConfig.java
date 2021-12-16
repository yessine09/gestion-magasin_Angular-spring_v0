package tn.esprit.spring.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.support.DatabaseType;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import tn.esprit.spring.entities.Client;
import tn.esprit.spring.itemproc.ClientItemProcessor;


@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

	@Bean
	@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
	public Client Client() {
		return new Client();
	}

	@Bean
	@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
	public ItemProcessor<Client, Client> itemProcessor() {
		return new ClientItemProcessor();
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/springdb");
		dataSource.setUsername("root");
		dataSource.setPassword("");

		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.addScript(new ClassPathResource("org/springframework/batch/core/schema-drop-mysql.sql"));
		databasePopulator.addScript(new ClassPathResource("org/springframework/batch/core/schema-mysql.sql"));

		DatabasePopulatorUtils.execute(databasePopulator, dataSource);
		return dataSource;
	}

	@Bean
	public ResourcelessTransactionManager txManager() {
		return new ResourcelessTransactionManager();
	}

	@Bean
	public JobRepository jbRepository(DataSource dataSource, ResourcelessTransactionManager transactionManager)
			throws Exception {
		JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
		factory.setDatabaseType(DatabaseType.MYSQL.getProductName());
		factory.setDataSource(dataSource);
		factory.setTransactionManager(transactionManager);
		return factory.getObject();
	}

	@Bean
	public JobLauncher jbLauncher(JobRepository jobRepository) {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository);
		return jobLauncher;
	}

	@Bean
	public BeanWrapperFieldSetMapper<Client> beanWrapperFieldSetMapper() {
		BeanWrapperFieldSetMapper<Client> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setPrototypeBeanName("Client");
		return fieldSetMapper;
	}

	@Bean
	public FlatFileItemReader<Client> fileItemReader(BeanWrapperFieldSetMapper<Client> beanWrapperFieldSetMapper) {
		FlatFileItemReader<Client> fileItemReader = new FlatFileItemReader<>();
		fileItemReader.setResource(new ClassPathResource("Client.csv"));

		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setNames("id_client", "date_naissance", "email", "fidele", "active", "nom", "password", "prenom", "profession","categorie_client");
		

		DefaultLineMapper<Client> defaultLineMapper = new DefaultLineMapper<>();
		defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
		defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

		fileItemReader.setLineMapper(defaultLineMapper);

		return fileItemReader;
	}

	@Bean
	public JdbcBatchItemWriter<Client> jdbcBatchItemWriter(DataSource dataSource,
			BeanPropertyItemSqlParameterSourceProvider<Client> sqlParameterSourceProvider) {
		JdbcBatchItemWriter<Client> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();
		jdbcBatchItemWriter.setDataSource(dataSource);
		jdbcBatchItemWriter.setItemSqlParameterSourceProvider(sqlParameterSourceProvider);
		

		jdbcBatchItemWriter.setSql("insert into Client(id_client,date_naissance,email,fidele,active,nom,password,prenom,profession,categorie_client) values (:id_client,:date_naissance,:email,:fidele,:active,:nom,:password,:prenom,:profession,:categorie_client)");

		return jdbcBatchItemWriter;
	}

	@Bean
	public BeanPropertyItemSqlParameterSourceProvider<Client> beanPropertyItemSqlParameterSourceProvider() {
		return new BeanPropertyItemSqlParameterSourceProvider<>();
	}

	@Bean
	public Job jobCsvMysql(JobBuilderFactory jobBuilderFactory, Step step) {
		return jobBuilderFactory.get("jobCsvMysql").incrementer(new RunIdIncrementer()).flow(step).end().build();
	}

	@Bean
	public Step step1(StepBuilderFactory stepBuilderFactory, ResourcelessTransactionManager transactionManager,
			ItemReader<Client> reader, ItemWriter<Client> writer, ItemProcessor<Client, Client> processor) {
		return stepBuilderFactory.get("step1").transactionManager(transactionManager).<Client, Client>chunk(2)
				.reader(reader).processor(processor).writer(writer).build();
	}

}
