package cong.struc.api.message;

import com.nhb.common.data.PuArray;
import com.nhb.common.data.PuObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class StrucBaseMessage implements StrucMessage {
	
	private String applicationId;
	private StrucMessageType type;
	private boolean isPuObjectData = false;
	
	public StrucBaseMessage(StrucMessageType type) {
		this.type = type;
	}
	
	@Override
	public PuArray serialize(PuArray puArray) {
		puArray.addFrom(applicationId);
		puArray.addFrom(type.getId());
		writePuArray(puArray); 
		return puArray;
	}

	@Override
	public int getTypeId() {
		return type.getId();
	}

	protected void writePuArray(PuArray puArray) {

	}

	public void readPuArray(PuArray puArray) {

	}

	public abstract void fromPuObject(PuObject puo);

}
