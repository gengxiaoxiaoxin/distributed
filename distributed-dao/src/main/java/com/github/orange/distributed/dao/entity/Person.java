/*
 * Copyright 2017-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.github.orange.distributed.dao.entity;

import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * The type Person.
 *
 * @author Orange
 * @date 2018 -6-9
 */
@Data
@Table("t_person")   // 声明了Person对象的数据表
public class Person { // 不会强制要求继承某个类

	@Id       // 表示该字段为一个自增长的Id,注意,是数据库表中自增!!
	private int id; // @Id与属性名称id没有对应关系.

	@Name    // 表示该字段可以用来标识此对象，或者是字符型主键，或者是唯一性约束
	private String name;

	@Column      // 表示该对象属性可以映射到数据库里作为一个字段
	private int age;

	public Person() {
	}

	// 省略getter/setter
}
