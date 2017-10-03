package com.kerbio.virtualcaretaker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.*;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.mobile.AWSMobileClient;
import com.amazonaws.mobile.api.CloudLogicAPI;
import com.amazonaws.mobile.api.idkc8m675la9.DevTravelClient;
import com.amazonaws.mobileconnectors.apigateway.ApiClientFactory;
import com.amazonaws.mobileconnectors.apigateway.ApiRequest;
import com.amazonaws.mobileconnectors.apigateway.ApiResponse;
import com.amazonaws.util.StringUtils;

/**
 * Created by Nuwan rathnayaka on 5/26/2017.
 */

public class FragmentMedication extends Fragment {
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.medication_fragment, container,false);
        View view1 = getActivity().findViewById(R.id.main_content);
        ImageView headImage= (ImageView) view1.findViewById(R.id.head_image);

        headImage.setImageResource(R.drawable.abc);
        return view;
    }

    public void invokeAPI() {

        // Set your request method, path, query string parameters, and request body
        final String method = "POST";
        final String path = "/items";
        final String body = "{\"someParameter\":\"someValue\"}";
        final Map<String, String> queryStringParameters = new HashMap<String, String>();
        final Map<String, String> headers = new HashMap<String, String>();

        final byte[] content = body.getBytes(StringUtils.UTF8);

        // Construct the request
        final ApiRequest request =
                new ApiRequest("hghdgjhdgh")
                        .withPath(path)
                        .withHttpMethod(HttpMethodName.valueOf(method))
                        .withHeaders(headers)
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Content-Length", String.valueOf(content.length))
                        .withParameters(queryStringParameters)
                        .withBody(content);


        // Create an instance of your custom SDK client
        final AWSMobileClient mobileClient = AWSMobileClient.defaultMobileClient();
        final CloudLogicAPI client = mobileClient.createAPIClient(DevTravelClient.class);

        // Make network call on background thread

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {

                    // Invoke the API
                    final ApiResponse response = client.execute(request);

                    final int statusCode = response.getStatusCode();
                    final String statusText = response.getStatusText();

                    //Log.d(LOG_TAG, "Response Status: " + statusCode + " " + statusMessage);

                    // TODO: Add your custom handling for server response status codes (e.g., 403 Forbidden)

                } catch (final AmazonClientException exception) {
                    //Log.e(LOG_TAG, exception.getMessage(), exception);

                    // TODO: Put your exception handling code here (e.g., network error)
                }
            }
        }).start();
    }

}


