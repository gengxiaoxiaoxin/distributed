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

import com.github.orange.distributed.MyNuTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.dao.Dao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@RunWith(MyNuTestRunner.class)
@IocBean
public class TravelRecordTest extends Assert {

	// 跟通常的@Inject完全一样.
	@Inject("refer:$ioc")
	protected Ioc ioc;

	@Inject
	private Dao dao;

	@Test
	public void testDao() {
		List<TravelRecord> query = dao.query(TravelRecord.class, null);
		System.out.println(query);
	}

}