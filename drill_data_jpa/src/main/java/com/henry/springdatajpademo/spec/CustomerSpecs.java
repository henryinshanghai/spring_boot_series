package com.henry.springdatajpademo.spec;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Iterables.toArray;

public class CustomerSpecs {

	// 属于类的静态方法byAuto()
	public static <T> Specification<T> byAuto(final EntityManager entityManager, final T example) { //1 参数列表：entityManager、封装查询条件的实体类

		final Class<T> type = (Class<T>) example.getClass();//2 获取到实体类的Class对象

		return new Specification<T>() {

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				//3 新建Predicate列表 - 用于存储构造的查询条件；
				List<Predicate> predicates = new ArrayList<>();

				//4 获取封装了查询条件的具体对象 - 手段：反射？？？
				EntityType<T> entity = entityManager.getMetamodel().entity(type);

				//5 遍历查询对象中的每个属性 - 找到可能的查询条件
				for (Attribute<T, ?> attr : entity.getDeclaredAttributes()) {
					//6 获取到某个属性的值
					Object attrValue = getValue(example, attr);
					if (attrValue != null) {
						//7 如果值的属性类型为String...
						if (attr.getJavaType() == String.class) {
							if (!StringUtils.isEmpty(attrValue)) { //8 不为空...
								// 9 构造 like查询条件，并添加到predicate列表中
								predicates.add(cb.like(root.get(attribute(entity, attr.getName(), String.class)),
										pattern((String) attrValue)));
							}
						} else { //10 对于其他的属性值，构造 equal查询条件
							predicates.add(cb.equal(root.get(attribute(entity, attr.getName(), attrValue.getClass())),
									attrValue));
						}
					}

				}
				//11 最后，把所有条件组成的列表 转化成为一个Predicate返回
				return predicates.isEmpty() ? cb.conjunction() : cb.and(toArray(predicates, Predicate.class));
			}

			/**
			 * 12 获取到对象的某个属性的值；	- 手段：反射
			 */
			private <T> Object getValue(T example, Attribute<T, ?> attr) {
				return ReflectionUtils.getField((Field) attr.getJavaMember(), example);
			}
			
			/**
			 * 13 获取到 指定对象的指定属性的值； - 手段：反射； 特征：SingularAttribute所包含的是 实体类的某个单个属性???
			 */
			private <E, T> SingularAttribute<T, E> attribute(EntityType<T> entity, String fieldName,
					Class<E> fieldClass) {
				return entity.getDeclaredSingularAttribute(fieldName, fieldClass);
			}

		};

	}
	
	/**
	 * 14
	 */
	static private String pattern(String str) {
		return "%" + str + "%";
	}

}
