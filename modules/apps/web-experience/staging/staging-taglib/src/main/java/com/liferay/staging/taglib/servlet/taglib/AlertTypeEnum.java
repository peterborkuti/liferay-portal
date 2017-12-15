package com.liferay.staging.taglib.servlet.taglib;

public enum AlertTypeEnum {
	info	("info"),
	warning	("warning"),
	success	("success"),
	error	("error");

	private final String alertCode;

	AlertTypeEnum(String alertCode) {
		this.alertCode = alertCode;
	}

	public String getAlertCode() {
		return this.alertCode;
	}
}
