package facilities.samir.andrew.facilities.utlities;

import android.util.Log;

import facilities.samir.andrew.facilities.BuildConfig;


/**
 * Created by andre on 17-Jan-18.
 */

public class LoggerHelper
{

    public void i(String tag, String message)
    {
        if (BuildConfig.DEBUG)
            Log.i(tag, message);
    }

    public void d(String tag, String message)
    {
        if (BuildConfig.DEBUG)
            Log.d(tag, message);
    }

    public void e(String tag, String message)
    {
        if (BuildConfig.DEBUG)
            Log.e(tag, message);
    }

    public void e(Throwable throwable)
    {

        if (BuildConfig.DEBUG)
        {
            Log.e("Logger", ">>>>>> Crash happened Check it!! <<<<<<");
            throwable.printStackTrace();
        }
    }

}
