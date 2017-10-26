package darius.partene.mycontacts.utils;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONObject;

/**
 * Custom jsonObjectRequest (can be updated with params or other stuff)
 */

public class JsonRequest extends JsonObjectRequest {

    private Listener<JSONObject> listener;

    public JsonRequest(String url, JSONObject jsonRequest, Listener<JSONObject> listener, ErrorListener errorListener) {
        super(Method.GET, url, jsonRequest, listener, errorListener);
        this.listener = listener;
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        if (listener != null) {
            listener.onResponse(response);
        }
    }
}
