//
// Copyright 2017 Amazon.com, Inc. or its affiliates (Amazon). All Rights Reserved.
//
// Code generated by AWS Mobile Hub. Amazon gives unlimited permission to 
// copy, distribute and modify it.
//
// Source code generated from template: aws-my-sample-app-android v0.17
//
package com.amazonaws.mobile.push;

import android.util.Log;

import com.amazonaws.mobile.AWSMobileClient;
import com.amazonaws.mobileconnectors.pinpoint.targeting.notification.NotificationClient;

public class CampaignPushManager implements GCMTokenHelper.GCMTokenUpdateObserver {

    private static final String LOG_TAG = CampaignPushManager.class.getSimpleName();

    public CampaignPushManager(final GCMTokenHelper gcmTokenHelper) {
        gcmTokenHelper.addTokenUpdateObserver(this);
    }

    @Override
    public void onGCMTokenUpdate(final String gcmToken, final boolean didTokenChange) {
        if (didTokenChange) {
            Log.d(LOG_TAG, "GCM Token changed, registering for Campaign push.");
            // Register the Device Token to receive Campaign Push.
            final NotificationClient notificationClient = AWSMobileClient.defaultMobileClient()
                .getPinpointManager()
                .getNotificationClient();
            notificationClient.registerGCMDeviceToken(gcmToken);
        }
    }

    @Override
    public void onGCMTokenUpdateFailed(final Exception ex) {
        Log.d(LOG_TAG, "GCM Token Update Failed, can't register for Campaign push : " + ex, ex);
    }
}
