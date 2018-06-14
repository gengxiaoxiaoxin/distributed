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

package com.github.orange.distributed.swagger.apidoc;

import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;
import org.nutz.plugins.apidoc.annotation.Api;
import org.nutz.plugins.apidoc.annotation.ApiMatchMode;
import org.nutz.plugins.apidoc.annotation.ApiParam;
import org.nutz.plugins.apidoc.annotation.ReturnKey;

import java.util.HashMap;
import java.util.Map;

@Api(name = "Demo-name测试", match = ApiMatchMode.ONLY, author = "耿鑫", description = "HelloApidocModel-测试描述")
@IocBean
@At("/demo")
public class HelloApidocModel {

	@Inject
	private Ioc ioc;

	@POST
	@At
	@Api(name = "回显接口1", params = {
			@ApiParam(name = "text", index = 1, type = "String", description = "echo!String", ignore = false) }, ok = {
			@ReturnKey(key = "echo!String", description = "echo!+text") })
	public String echo(@Param(value = "text") String text) {
		return "echo!" + text;
	}

	@At("/now")
	@Api(name = "回显接口2", ok = { @ReturnKey(key = "millis", description = "当前系统时间") })
	public long now() {
		return System.currentTimeMillis();
	}

	@At("/sysparam")
	@Api(name = "回显接口3", ok = { @ReturnKey(key = "map", description = "当前系统参数Map") })
	public Map systemParam() {
		Map<String, java.io.Serializable> map = new HashMap<>();
		map.put("name", "耿鑫的初代机");
		map.put("life", -1);
		return map;
	}

}
