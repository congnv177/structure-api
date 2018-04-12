package cong.struc.api.message;

import com.nhb.common.data.PuArray;
import com.nhb.common.data.PuObject;

public interface StrucMessage {
	String getApplicationId();
	
	PuArray serialize(PuArray puArray);
	
	boolean isPuObjectData();
	
	default PuObject toPuObject() {
		return PuObject.fromObject(this);
	}
	
	void fromPuObject(PuObject puo);
	
	StrucMessageType getType();
	
	default int getTypeId() {
		return getType().getId();
	}
}
