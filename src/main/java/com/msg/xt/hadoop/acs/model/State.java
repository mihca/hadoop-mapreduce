package com.msg.xt.hadoop.acs.model;

public class State {
	
	private String code;
	private String sign;
	private String text;
	
	public State(String code, String text, String sign) {
		super();
		this.code = code;
		this.sign = sign;
		this.text = text;
	}

	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getSign() {
		return sign;
	}
	
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
}
