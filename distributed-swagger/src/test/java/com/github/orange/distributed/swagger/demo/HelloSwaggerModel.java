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

package com.github.orange.distributed.swagger.demo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;

@Api(value = "demo")
@IocBean
@At("/demo")
public class HelloSwaggerModel {

	@POST
	@ApiOperation(value = "回显接口", notes = "发我一个字符串,原样回复一个字符串", httpMethod = "POST")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "text", paramType = "form", value = "想发啥就发啥", dataType = "string", required = true) })
	@At
	@Ok("raw")
	public String echo(String text) {
		return "echo!" + text;
	}

	@Ok("raw")
	@At("/now")
	public long now() {
		return System.currentTimeMillis();
	}

}
