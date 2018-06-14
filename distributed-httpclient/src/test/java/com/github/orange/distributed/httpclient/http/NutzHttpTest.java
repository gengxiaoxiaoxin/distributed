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

package com.github.orange.distributed.httpclient.http;

import org.junit.Assert;
import org.junit.Test;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.http.Sender;
import org.nutz.lang.Stopwatch;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class NutzHttpTest extends Assert {

	@Test
	public void httpTest() {

		Response response = Http.get("https://nutz.cn/");
		assertNotNull(response);
		assertNotNull(response.getContent());
		assertNotNull(response.getDetail());
		System.out.println(response.getDetail());
		assertNotNull(response.getHeader());
		assertNotNull(response.getProtocal());
		assertTrue(response.getStatus() > 0);
		assertNotNull(response.getStream());

	}

	@Test
	public void httpExecutorsTest() {
		List<Future<Response>> list = new LinkedList<>();

		int taskSize = 512;
		AtomicInteger atomic = new AtomicInteger();
		ExecutorService executorService = Executors.newFixedThreadPool(taskSize);
		Sender.setup(executorService);
		//		Sender.setup(null);

		//loop
		for (int i = 0; i < 1000; i++) {
			Future<Response> responseFuture = Sender.create("https://nutz.cn").setConnTimeout(7 * 1000).send(response -> {
				//response.getContent();
				System.out.println(Thread.currentThread().getName() + ":" + response.getStatus());
			});
			list.add(responseFuture);
		}

		System.out.println(atomic);
		/*for (Future<Response> responseFuture : list) {
			System.out.println(responseFuture.get());
		}*/

		//秒表计时
		Stopwatch sw = new Stopwatch();
		sw.start();

		list.forEach(f -> {
			try {
				System.out.println(f.get());
				atomic.incrementAndGet();
			}
			catch (InterruptedException | ExecutionException e) {
				// ignore
			}
		});

		System.out.println(atomic);
		sw.stop();
		System.out.println(sw);

		// Good Habits
		Sender.shutdown();
	}

	@Test
	public void fileTest() {
		/*Request req = Request.create("E:/upload/image", Request.METHOD.POST);
		File f = File.createTempFile("nutz", "data");
		FileWriter fw = new FileWriter(f);
		fw.write("abc");
		fw.flush();
		fw.close();
		req.getParams().put("file", f);
		FilePostSender sender = new FilePostSender(req);*/
		
		Map<String, Object> map = new HashMap<>();
		File file = new File("D:\\images\\26.jpg");
		map.put("file", file);
		Response response = Http.upload("localhost:8080/upload", map, null, 10 * 1000);
		System.out.println(response.getContent());
	}
}
