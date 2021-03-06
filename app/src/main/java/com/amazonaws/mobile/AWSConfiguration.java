//
// Copyright 2017 Amazon.com, Inc. or its affiliates (Amazon). All Rights Reserved.
//
// Code generated by AWS Mobile Hub. Amazon gives unlimited permission to 
// copy, distribute and modify it.
//
// Source code generated from template: aws-my-sample-app-android v0.17
//
package com.amazonaws.mobile;

import com.amazonaws.mobilehelper.config.AWSMobileHelperConfiguration;
import com.amazonaws.regions.Regions;

/**
 * This class defines constants for the developer's resource
 * identifiers and API keys. This configuration should not
 * be shared or posted to any public source code repository.
 */
public class AWSConfiguration {
    // AWS MobileHub user agent string
    public static final String AWS_MOBILEHUB_USER_AGENT =
        "MobileHub d171d1d3-c579-43fd-a316-fba8c3eecb4e aws-my-sample-app-android-v0.17";
    // AMAZON COGNITO
    public static final Regions AMAZON_COGNITO_REGION =
      Regions.fromName("us-east-1");
    public static final String  AMAZON_COGNITO_IDENTITY_POOL_ID =
        "us-east-1:c3cbb3ce-6722-4494-96d2-06f535440f5f";
    // AMAZON MOBILE ANALYTICS
    public static final String  AMAZON_MOBILE_ANALYTICS_APP_ID =
        "74a05e462a634e889345c1550f74096a";
    // Amazon Mobile Analytics region
    public static final Regions AMAZON_MOBILE_ANALYTICS_REGION = Regions.US_EAST_1;
    // GOOGLE CLOUD MESSAGING SENDER ID
    public static final String GOOGLE_CLOUD_MESSAGING_SENDER_ID =
        "127898690739";
    // SNS PLATFORM APPLICATION ARN
    public static final String AMAZON_SNS_PLATFORM_APPLICATION_ARN =
        "arn:aws:sns:us-east-1:551396052115:app/GCM/virtualcaretaker_MOBILEHUB_827344712";
    public static final Regions AMAZON_SNS_REGION =
         Regions.fromName("us-east-1");
    // SNS DEFAULT TOPIC ARN
    public static final String AMAZON_SNS_DEFAULT_TOPIC_ARN =
        "arn:aws:sns:us-east-1:551396052115:virtualcaretaker_alldevices_MOBILEHUB_827344712";
    // SNS PLATFORM TOPIC ARNS
    public static final String[] AMAZON_SNS_TOPIC_ARNS =
        {};
    // S3 BUCKET
    public static final String AMAZON_S3_USER_FILES_BUCKET =
        "virtualcaretaker-userfiles-mobilehub-827344712";
    // S3 BUCKET REGION
    public static final Regions AMAZON_S3_USER_FILES_BUCKET_REGION =
        Regions.fromName("us-east-1");
    public static final String AMAZON_COGNITO_USER_POOL_ID =
        "us-east-1_xR3YfVYPE";
    public static final String AMAZON_COGNITO_USER_POOL_CLIENT_ID =
        "4bqhfm7736sv1ju3gveu3ucher";
    public static final String AMAZON_COGNITO_USER_POOL_CLIENT_SECRET =
        "rc2m3c55oqimbhil0hebqt7fdd88rl0b7eapbpcjdahl2bh63j8";

    private static final AWSMobileHelperConfiguration helperConfiguration = new AWSMobileHelperConfiguration.Builder()
        .withCognitoRegion(AMAZON_COGNITO_REGION)
        .withCognitoIdentityPoolId(AMAZON_COGNITO_IDENTITY_POOL_ID)
        .withCognitoUserPool(AMAZON_COGNITO_USER_POOL_ID,
            AMAZON_COGNITO_USER_POOL_CLIENT_ID, AMAZON_COGNITO_USER_POOL_CLIENT_SECRET)
        .build();
    /**
     * @return the configuration for AWSKit.
     */
    public static AWSMobileHelperConfiguration getAWSMobileHelperConfiguration() {
        return helperConfiguration;
    }
}
