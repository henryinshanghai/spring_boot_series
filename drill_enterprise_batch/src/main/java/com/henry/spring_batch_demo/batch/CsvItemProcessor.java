package com.henry.spring_batch_demo.batch;

import com.henry.spring_batch_demo.domain.Person;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

public class CsvItemProcessor extends ValidatingItemProcessor<Person>{

	@Override
	public Person process(Person item) throws ValidationException {
		super.process(item); //1 这里需要调用父类的process()方法 - 这样才能调用自定义的校验器
		
		if(item.getNation().equals("汉族")){ //2 对数据的处理逻辑 - 出于演示目的 越简单越好
			item.setNation("01");
		}else{
			item.setNation("02");
		}
		return item;
	}


}
