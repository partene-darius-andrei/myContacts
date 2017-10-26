package darius.partene.mycontacts.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestManager {

	private static final int TIMEOUT = 20000;

	/**
	 * the queue :-)
	 */
	private static RequestQueue mRequestQueue;

	/**
	 * Nothing to see here.
	 */
	private RequestManager() {
		// no instances
	}

	/**
	 * @param context application context
	 */
	public static void init(Context context) {
		mRequestQueue = Volley.newRequestQueue(context);

	}

	public static RequestQueue getRequestQueue() {
		if (mRequestQueue != null) {
			return mRequestQueue;
		} else {
			throw new IllegalStateException("Not initialized");
		}
	}


	public static <T> void addRequest(Request<T> request, String tag) {
		if (!TextUtils.isEmpty(tag)) {
			request.setTag(tag);
			Log.e(tag, request.getUrl());
		}
		request.setRetryPolicy(new DefaultRetryPolicy(TIMEOUT, request.getMethod() == Request.Method.POST ? -1 : DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		getRequestQueue().add(request);
	}


}