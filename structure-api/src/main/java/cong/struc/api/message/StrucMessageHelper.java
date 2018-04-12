package cong.struc.api.message;

import com.nhb.common.data.PuArray;
import com.nhb.common.data.PuDataType;
import com.nhb.common.data.PuElement;
import com.nhb.common.data.PuObject;
import com.nhb.common.data.PuObjectRO;

import cong.struc.api.statics.F;

public class StrucMessageHelper {
	public static void setApplicationId(StrucBaseMessage message, String applicationId) {
		message.setApplicationId(applicationId);
	}

	public static <T extends StrucBaseMessage> T deserialize(PuElement puElement) {
		if (puElement instanceof PuArray) {
			String applicationId = ((PuArray) puElement).remove(0).getString();
			StrucMessageType storeMessageType = StrucMessageType.fromId(((PuArray) puElement).remove(0).getInteger());
			if (storeMessageType.getClassMessageType() != null) {
				@SuppressWarnings("unchecked")
				Class<T> classMessageType = (Class<T>) storeMessageType.getClassMessageType();
				T storeMessage;
				try {
					storeMessage = classMessageType.newInstance();
					if (storeMessage instanceof StrucBaseMessage) {
						storeMessage.setApplicationId(applicationId);
						storeMessage.setType(storeMessageType);
						storeMessage.readPuArray((PuArray) puElement);
					}
					return storeMessage;
				} catch (Exception e) {
					throw new RuntimeException("Error while creating new message Object", e);
				}
			}
		} else if (puElement instanceof PuObjectRO) {
			PuObject puObject = (PuObject) puElement;
			StrucMessageType type = puObject.variableExists(F.COMMAND)
					? StrucMessageType.forName(puObject.getString(F.COMMAND))
					: StrucMessageType.fromId(puObject.getInteger(F.TYPE, -1));
			if (puObject.variableExists(F.APP_ID) && puObject.typeOf(F.APP_ID) == PuDataType.STRING) {
				puObject.decodeBase64(F.APP_ID);
			}
			String applicationId = puObject.getString(F.APP_ID, null);
			if (type != null) {
				@SuppressWarnings("unchecked")
				Class<T> clazz = (Class<T>) type.getClassMessageType();
				T message = null;
				try {
					message = clazz.newInstance();
				} catch (Exception e) {
					throw new RuntimeException("Error while creating new message Object", e);
				}
				if (message instanceof StrucBaseMessage) {
					((StrucBaseMessage) message).setApplicationId(applicationId);
					((StrucBaseMessage) message).setPuObjectData(true);
					((StrucBaseMessage) message).fromPuObject(puObject);
				}
				return message;
			}

		}
		return null;
	}
}
