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

import org.nutz.boot.NbApp;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.UrlMappingBy;
import org.nutz.plugins.apidoc.ApidocUrlMapping;

@IocBean
@Ok("json")
@UrlMappingBy(ApidocUrlMapping.class)
public class MainLauncher {

	public static void main(String[] args) {
		new NbApp(MainLauncher.class).setArgs(args).setPrintProcDoc(true).run();
	}

	@Ok("raw")
	@At("/time/now")
	public long now() {
		return System.currentTimeMillis();
	}

}
