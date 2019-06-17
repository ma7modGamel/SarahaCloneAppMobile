package com.xx.SarahaCloneAppMobile.utils;

public class MsgModel {
    String contentMsg;
    String timeMsg;

    public String getContentMsg() {
        return contentMsg;
    }

    public void setContentMsg(String contentMsg) {
        this.contentMsg = contentMsg;
    }

    public String getTimeMsg() {
        return timeMsg;
    }

    public void setTimeMsg(String timeMsg) {
        this.timeMsg = timeMsg;
    }

    public MsgModel(String contentMsg, String timeMsg) {
        this.contentMsg = contentMsg;
        this.timeMsg = timeMsg;
    }
}
