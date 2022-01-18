package com.khafonline.phoenix4.model;

public class Setting {
    public static final String[] PARAMETER_NAMES={"DataServerAddress","ImageServerAddress","Title"};

    private String ParamteterName;
    private String ParamteterValue;

    public Setting(String paramteterName, String paramteterValue) {
        ParamteterName = paramteterName;
        ParamteterValue = paramteterValue;
    }

    public String getParamteterName() {
        return ParamteterName;
    }

    public void setParamteterName(String paramteterName) {
        ParamteterName = paramteterName;
    }

    public String getParamteterValue() {
        return ParamteterValue;
    }

    public void setParamteterValue(String paramteterValue) {
        ParamteterValue = paramteterValue;
    }
}
