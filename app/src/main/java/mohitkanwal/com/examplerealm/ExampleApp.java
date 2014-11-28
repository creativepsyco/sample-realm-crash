package mohitkanwal.com.examplerealm;

import android.app.Application;
import android.content.Context;

/**
 * User: msk
 * Date: 28/11/14
 */
public class ExampleApp extends Application {
    private static Application mInstance;

    public static Context getApplication() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
