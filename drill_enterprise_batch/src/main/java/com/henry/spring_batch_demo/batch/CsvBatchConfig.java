package com.henry.spring_batch_demo.batch;

import com.henry.spring_batch_demo.domain.Person;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
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
import org.springframework.batch.item.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

//@Configuration
@EnableBatchProcessing // Ⅰ 开启Spring对批处理的支持
public class CsvBatchConfig {

	@Bean
	public ItemReader<Person> reader() throws Exception {
		//1 读取文件 - 手段：使用 FlatFileItemReader对象
		FlatFileItemReader<Person> reader = new FlatFileItemReader<Person>();
		//2 设置文件路径 - 手段： setResource()方法
		reader.setResource(new ClassPathResource("people.csv"));
		//3 把csv文件中的数据 映射到 领域类模型	- 手段：setLineMapper()方法	语法复杂不看
		reader.setLineMapper(new DefaultLineMapper<Person>() {{
			setLineTokenizer(new DelimitedLineTokenizer() {{
				setNames(new String[] { "name","age", "nation" ,"address"});
			}});
			setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
				setTargetType(Person.class);
			}});
		}});
		return reader;
	}
	
	@Bean
	public ItemProcessor<Person, Person> processor() {
		//1 实例化processor - 使用自定义的Processor
		CsvItemProcessor processor = new CsvItemProcessor();
		//2 为process指定校验器 - 手段：setValidator(<自定义的校验器>)
		processor.setValidator(csvBeanValidator());
		return processor;
	}
	
	

	@Bean
	public ItemWriter<Person> writer(DataSource dataSource) {//1 注入Spring容器中存在的Bean实例  手段：以方法参数的形式
		//2 写数据到数据库		手段：使用JDBC批处理的JdbcBatchItemWriter
		JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<Person>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
		String sql = "insert into person_batch " + "(id,name,age,nation,address) "
				+ "values(hibernate_sequence.nextval, :name, :age, :nation, :address)";
		//3 设置批处理所需要执行的sql语句；		手段:sql()方法
		writer.setSql(sql);
		writer.setDataSource(dataSource);
		return writer;
	}

	// 定义JobRepo
	/*
		1 创建JobRepo的实例需要 dataSource与transactionManager - 这两个类型都已经由Spring Boot自动配置好了。
		2 Spring支持 - 通过方法参数的形式 来 注入Spring容器中已经存在的Bean实例。
	 */
	@Bean
	public JobRepository jobRepository(DataSource dataSource, PlatformTransactionManager transactionManager)
			throws Exception {
		JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
		jobRepositoryFactoryBean.setDataSource(dataSource);
		jobRepositoryFactoryBean.setTransactionManager(transactionManager);
        jobRepositoryFactoryBean.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
        jobRepositoryFactoryBean.setDatabaseType("oracle");
		return jobRepositoryFactoryBean.getObject();
	}

	@Bean
	public SimpleJobLauncher jobLauncher(DataSource dataSource, PlatformTransactionManager transactionManager)
			throws Exception {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository(dataSource, transactionManager));
		return jobLauncher;
	}

	@Bean
	public Job importJob(JobBuilderFactory jobs, Step s1) {
		return jobs.get("importJob")
				.incrementer(new RunIdIncrementer())
				.flow(s1) //1 指定Job中的Step	手段：flow(step)
				.end()
				.listener(csvJobListener()) //2 为Job指定监听器	手段：listener(listener)
				.build();
	}

	@Bean
	public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<Person> reader, ItemWriter<Person> writer,
			ItemProcessor<Person,Person> processor) {
		return stepBuilderFactory
				.get("step1")
				.<Person, Person>chunk(65000) //1 指定批处理每次提交的数据	手段：chunk(size)
				.reader(reader) //2 指定step所使用的reader	手段: reader(reader)
				.processor(processor) //3 指定step所使用的processor	手段：processor(processor)
				.writer(writer) //4 指定step所使用的writer  手段：writer(writer)
				.build();
	}



	@Bean
	public CsvJobListener csvJobListener() {
		return new CsvJobListener();
	}

	@Bean
	public Validator<Person> csvBeanValidator() {
		return new CsvBeanValidator<Person>();
	}
	

}
