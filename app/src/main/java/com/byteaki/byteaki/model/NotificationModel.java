package com.byteaki.byteaki.model;

import java.util.List;

/**
 * Created by macbook on 28/05/17.
 */

public class NotificationModel {
    private String notificationTitle;
    private String notificationContent;
    private String notificationUser;
    private String notificationGroup;

    public NotificationModel(String title, String description, String user, String idGroup) {
        notificationTitle=title;
        notificationUser=user;
        notificationContent=description;
        setNotificationGroup(idGroup);
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }

    public String getNotificationUser() {
        return notificationUser;
    }

    public void setNotificationUser(String notificationUser) {
        this.notificationUser = notificationUser;
    }

    public String getNotificationGroup() {
        return notificationGroup;
    }

    public void setNotificationGroup(String notificationGroup) {
        this.notificationGroup = notificationGroup;
    }
}
