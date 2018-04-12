package cong.struc.api.message;

import cong.struc.api.statics.Status;

public interface StrucResponse extends StrucMessage {
	Status getStatus();

	String getMessage();
}
