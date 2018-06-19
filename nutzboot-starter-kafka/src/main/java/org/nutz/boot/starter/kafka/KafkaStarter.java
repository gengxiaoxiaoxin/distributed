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

package org.nutz.boot.starter.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.nutz.boot.annotation.PropDoc;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.Properties;

/**
 * The type Kafka starter.
 *
 * @author Orange
 * @date 2018 -6-19
 */
@IocBean
public class KafkaStarter {

	protected static final String PRE = "kafka.";

	@PropDoc(group = "kafka", value = "Kafka集群地址，可以只写其中一部分", type = "String", need = true)
	public static final String PROP_BOOTSTRAP_SERVERS = PRE + "bootstrap.servers";

	@PropDoc(group = "kafka", value = "key-序列化类型", type = "String", need = true)
	public static final String PROP_KEY_SERIALIZER = PRE + "key.serializer";

	@PropDoc(group = "kafka", value = "value-序列化类型", type = "String", need = true)
	public static final String PROP_VALUE_SERIALIZER = PRE + "value.serializer";

	/**
	 * The Conf.
	 */
	@Inject
	protected PropertiesProxy conf;

	/**
	 * 将producer写成单例模式，有助于减少zookeeper端占用的资源。<br/>
	 * Producer自身是线程安全的类，只要封装得当就能最恰当的发挥好producer的作用。<br/>
	 * <ul>
	 * <li>ZkClient去连接zookeeper的server时候都会创建sendThread和eventThread两个线程，其中sendThread主要用于client与server端之间的网络连接，真正的处理线程由eventThread来执行。</li>
	 * <li>Zookeeper是一个分布式的协调框架，而分布式应用中经常会出现动态的增加或删除节点的操作，所以为了实时了解分布式整个节点的数量和基本信息，就有必要维护一个长连接的线程与服务端保持连接。</li>
	 * <li>另外zookeeper连接时占用的时间也比较长，如果每次生产数据时都连接发起一次连接势必造成了大量时间的耗费。</li>
	 * </ul>
	 *
	 * @return iocBean-producer
	 */
	@IocBean
	public Producer producer() {
		Properties properties = new Properties();
		conf.keys().forEach(k -> {
			if (k.startsWith(PRE)) {
				properties.put(k.substring(PRE.length()), conf.get(k));
			}
		});
		return new KafkaProducer(properties);
	}

}
