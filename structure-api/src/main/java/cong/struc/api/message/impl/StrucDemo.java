package cong.struc.api.message.impl;

import com.nhb.common.data.PuObject;

import cong.struc.api.message.StrucBaseMessage;
import cong.struc.api.message.StrucMessageType;

public class StrucDemo extends StrucBaseMessage {

	public StrucDemo() {
		super(StrucMessageType.TEST);
	}

	@Override
	public void fromPuObject(PuObject puo) {
		
	}

}
