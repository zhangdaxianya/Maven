package net.dgsr.comment;

public enum ResponseCode {
	
	 //成功
	SUCCESS(0,"SUCCESS"), 
	
	//失败
	ERROR(1,"ERROR"),	
	
	//非法数据
	ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT"),  
	
	//登陆成功
	NEED_LOGIN(3,"NEED_LOGIN");
	
	
	private final int code;
	private final String desc;
	
	private ResponseCode(int code , String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getDesc() {
		return desc;
	}

}
