package cong.struc.api.client;

import java.util.Properties;

import com.nhb.common.BaseLoggable;
import com.nhb.common.data.PuArrayList;
import com.nhb.common.data.PuElement;
import com.nhb.common.vo.HostAndPort;
import com.nhb.common.vo.UserNameAndPassword;
import com.nhb.messaging.rabbit.RabbitMQQueueConfig;
import com.nhb.messaging.rabbit.connection.RabbitMQConnectionPool;
import com.nhb.messaging.rabbit.producer.RabbitMQRPCProducer;

import cong.struc.api.message.StrucBaseMessage;
import cong.struc.api.message.StrucMessage;
import cong.struc.api.message.StrucMessageHelper;

public class CongStrucClient extends BaseLoggable {
	
	public static final String	ENPOINT_KEY		= "store.rabbitmq.servers";
	public static final String	USER_KEY		= "store.rabbitmq.username";
	public static final String	PASSWORD_KEY	= "store.rabbitmq.password";
	public static final String	QUEUE_NAME_KEY	= "store.rabbitmq.queueName";

	private final RabbitMQRPCProducer	producer;
	private String						appIdString;

	public CongStrucClient(RabbitMQRPCProducer producer) {
		this.producer = producer;
	}

	public CongStrucClient(Properties properties) {
		RabbitMQConnectionPool connectionPool = new RabbitMQConnectionPool();
		String endpoint = properties.getProperty(ENPOINT_KEY);
		String[] arr = endpoint.split(",");
		for (String str : arr) {
			if (str.trim().length() > 0) {
				connectionPool.addEndpoints(HostAndPort.fromString(str));
			}
		}
		if (properties.containsKey(USER_KEY) && properties.containsKey(PASSWORD_KEY)) {
			UserNameAndPassword credential = new UserNameAndPassword();
			credential.setUserName(properties.getProperty(USER_KEY));
			credential.setPassword(properties.getProperty(PASSWORD_KEY));
			connectionPool.setCredential(credential);
		}
		RabbitMQQueueConfig queueConfig = new RabbitMQQueueConfig();
		queueConfig.setQueueName(properties.getProperty(QUEUE_NAME_KEY));

		connectionPool.init();

		this.producer = new RabbitMQRPCProducer(connectionPool, queueConfig);
	}

	public StrucRPCFuture send(StrucMessage message) {
		if (!this.producer.isConnected()) {
			synchronized (this.producer) {
				if (!this.producer.isConnected()) {
					this.producer.start();
				}
			}
		}
		if (message == null) {
			getLogger().warn("Message cannot be null, ignored by default", new Exception());
			return null;
		}
		if (message instanceof StrucBaseMessage) {
			StrucMessageHelper.setApplicationId((StrucBaseMessage) message, this.appIdString);
		}
		PuElement data = message.serialize(new PuArrayList());
		return new StrucRPCFuture(this.producer.publish(data));
	}
	
}
