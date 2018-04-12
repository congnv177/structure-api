package cong.struc.api.message;

import cong.struc.api.message.impl.StrucDemo;;

public enum StrucMessageType {
	
	TEST(1, StrucDemo.class);
	
	private int	id;
	private Class<? extends StrucBaseMessage>	classMessageType;

	private StrucMessageType(int id, Class<? extends StrucBaseMessage> clazz) {
		this.id = id;
		this.classMessageType = clazz;
	}

	public static StrucMessageType fromId(int id) {
		for (StrucMessageType type : values()) {
			if (type.getId() == id) {
				return type;
			}
		}
		return null;
	}

	public int getId() {
		return id;
	}

	public Class<? extends StrucBaseMessage> getClassMessageType() {
		return classMessageType;
	}

	public static StrucMessageType forName(String type) {
		for (StrucMessageType messageType : values()) {
			if (messageType.name().equalsIgnoreCase(type)
					|| type.replaceAll("-", "_").equalsIgnoreCase(messageType.name())) {
				return messageType;
			}
		}
		return null;
	}
	
}
