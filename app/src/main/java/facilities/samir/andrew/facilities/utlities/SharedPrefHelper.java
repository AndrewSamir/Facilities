package facilities.samir.andrew.facilities.utlities;

import android.content.Context;
import android.content.SharedPreferences;


import static android.content.Context.MODE_PRIVATE;

/**
 * Created by andre on 22-Jan-18.
 */

public class SharedPrefHelper
{
    private static Context context;
    private static SharedPrefHelper instance = null;
    private static SharedPreferences prefs = null;
    private static SharedPreferences.Editor editor;

    public static SharedPrefHelper getInstance(Context context)
    {
        SharedPrefHelper.context = context;

        if (instance == null)
        {
            instance = new SharedPrefHelper();
            SharedPrefHelper.prefs = context.getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        }
        return instance;
    }



    public String getAccessToken()
    {
        return prefs.getString(DataEnum.shAccessToken.name(), null);
    }


}
