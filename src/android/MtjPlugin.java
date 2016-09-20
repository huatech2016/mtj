package net.huatech.mobile.plugin.mtj;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;

import com.baidu.mobstat.StatService;

public class MtjPlugin extends CordovaPlugin {
	static String previousPageName = "";

	@Override
	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException {
		try {
			final String pageName = args.getString(0).toLowerCase();
			if (pageName != null && !"".equals(pageName)) {
				final Context ctx = cordova.getActivity();
				if (ctx != null) {
					if (action.equals("changePage")) {
						if (!"".equals(previousPageName)) {
							StatService.onPageEnd(ctx, previousPageName);
						}
						StatService.onPageStart(ctx, pageName);
						previousPageName = pageName;
					}
				}
				callbackContext.success();
				return true;
			}
		} catch (JSONException e) {
			return false;
		}
		return false;
	}
}