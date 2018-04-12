package cong.struc.api.statics;

import lombok.Getter;

@Getter
public enum Status {
	
	OK(0,"Thành công"),
	INVALID_PARAMS(1,"Sai tham số"),
	UNKNOWN(2, "Thất bại");
	
	private final int code;
	private final String message;
	
	private Status(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public static final Status fromCode(int code) {
		for (Status e : values()) {
			if (e.getCode() == code) {
				return e;
			}
		}
		return null;
	}
	
}
