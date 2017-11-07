package com.zt.recyclerview.global;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.telephony.PhoneNumberUtils;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Utils {

    public static final String DDMMYYYY = "dd/MM/yyyy";
    public static final String MDYYYY = "M/d/yyyy";
    public static final String HHMMA = "hh:mm a";
    public static final boolean isDebugging = false;

    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    public static void showSnackbar(CoordinatorLayout coordinatorLayout, String strMsg) {
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, strMsg, Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }

    public static void showToast(Context context, String strMsg) {
        Toast toast = Toast.makeText(context, strMsg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static boolean isThisDateValid(String dateToValidate) {
        if (dateToValidate == null || dateToValidate.trim().length() == 0) {
            return false;
        }

        Date date = null;

        SimpleDateFormat sdf = new SimpleDateFormat(DDMMYYYY);
        try {
            sdf.parse(dateToValidate);
            if (!dateToValidate.equals(sdf.format(date))) {
                return false;
            }
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean isValidFormat(String value) {
        if (value.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})"))
            return true;
        else
            return false;
    }

    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(HHMMA);
        try {
            Date date = new Date();
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    public static String getUnixTime(String strDate) {
        if (strDate == null || strDate.trim().length() == 0) {
            return "";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(DDMMYYYY);
        try {
            Date date = sdf.parse(strDate);
            System.out.println(date);

            long unixTime = (long) date.getTime() / 1000;
            return unixTime + "";
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return "";
    }

    public static String getUnixToDateTime(String strDate) {
        if (strDate == null || strDate.trim().length() == 0) {
            return "";
        }

        long unixTime = Long.parseLong(strDate);
        Date date = new Date(unixTime * 1000L); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat(DDMMYYYY);

        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    public static String convertDate(String strDate) {
        if (strDate == null || strDate.trim().length() == 0) {
            return "";
        }
        try {
            SimpleDateFormat spf = new SimpleDateFormat("dd-MM-yyyy");
            Date newDate = spf.parse(strDate);
            spf = new SimpleDateFormat("dd\nMMM");
            String finalDate = spf.format(newDate);
            return finalDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String formatDate(String strDate) {
        if (strDate == null || strDate.trim().length() == 0) {
            return "";
        }
        try {
            SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
            Date newDate = spf.parse(strDate);
            spf = new SimpleDateFormat("MMM-dd,yy");
            String finalDate = spf.format(newDate);
            return finalDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String indianDateFormat(String strDate) {
        if (strDate == null || strDate.trim().length() == 0) {
            return "";
        }
        try {
            SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
            Date newDate = spf.parse(strDate);
            spf = new SimpleDateFormat("dd-MM-yyyy");
            String finalDate = spf.format(newDate);
            return finalDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String usaDateFormat(String strDate) {
        if (strDate == null || strDate.trim().length() == 0) {
            return "";
        }
        try {
            SimpleDateFormat spf = new SimpleDateFormat("dd-MM-yyyy");
            Date newDate = spf.parse(strDate);
            spf = new SimpleDateFormat("yyyy-MM-dd");
            String finalDate = spf.format(newDate);
            return finalDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    // this method uses for the unix time to date of birth
    public static String getUnixToTime(String strDate) {
        if (strDate == null || strDate.trim().length() == 0) {
            return "";
        }

        long unixTime = Long.parseLong(strDate);
        Date date = new Date(unixTime * 1000L); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat(HHMMA);

        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    public static String getUnixToDateConversation(String strDate) {
        if (strDate == null || strDate.trim().length() == 0) {
            return "";
        }

        long unixTime = Long.parseLong(strDate);
        Date date = new Date(unixTime * 1000L); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat(MDYYYY);

        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    public static String getFormattedDate(String strDate, String strFDate, String strNDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(strFDate);
            Date testDate = sdf.parse(strDate);
            SimpleDateFormat formatter = new SimpleDateFormat(strNDate);
            return formatter.format(testDate);

        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public static String getDeviceID(Context context) {
        String android_id = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return android_id;
    }

    public static boolean isAppInstalled(Context context, String name) {
        try {
            context.getPackageManager().getApplicationInfo(name, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }


    public static boolean isStringNull(String str) {
        if (str == "" || str == null || str.equalsIgnoreCase("null") || str.trim().length() == 0 || str.equalsIgnoreCase("false")) {
            return true;
        }
        return false;
    }

    public static boolean isArrayListNull(ArrayList<HashMap<String, String>> list) {
        if (list == null || list.size() < 0 || list.size() == 0 || list.isEmpty()) {
            return true;
        }
        return false;
    }


    public static String formattedNumber(String str) {
        if (str == "" || str == null || str.equalsIgnoreCase("null") || str.trim().length() == 0) {
            return "";
        }
        String strNumber = PhoneNumberUtils.formatNumber(str);
        return strNumber;
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static String local(String latitudeFinal, String longitudeFinal) {
        return "https://maps.googleapis.com/maps/api/staticmap?center=" + latitudeFinal + "," + longitudeFinal + "&zoom=18&size=280x280&markers=color:red|" + latitudeFinal + "," + longitudeFinal;
    }

    public static CharSequence convertTimeStamp(String str) {
        return DateUtils.getRelativeTimeSpanString(Long.parseLong(str), System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
    }

    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    public static Typeface lightFont(Context context) {
        Typeface face = Typeface.createFromAsset(context.getAssets(), "light_font_ubuntu.ttf");
        return face;
    }

    public static Typeface boldFont(Context context) {
        Typeface face = Typeface.createFromAsset(context.getAssets(), "bold_font_ubuntu.ttf");
        return face;
    }

    public static Typeface mediumFont(Context context) {
        Typeface face = Typeface.createFromAsset(context.getAssets(), "medium_font_ubuntu.ttf");
        return face;
    }

    public static Typeface regularFont(Context context) {
        Typeface face = Typeface.createFromAsset(context.getAssets(), "regular_font_ubuntu.ttf");
        return face;
    }

    public static String uniqueDeviceId(Context context) {
        String android_id = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return android_id;
    }

    public static void alertDialog(Context context, String msg) {
        android.support.v7.app.AlertDialog.Builder adb = new android.support.v7.app.AlertDialog.Builder(context);
        adb.setTitle("Alert!");
        adb.setMessage(msg);
        adb.setCancelable(false);
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        adb.create().show();
    }

    public static String base64Convert(String strPath) {
        if (strPath == "" || strPath == null || strPath.equalsIgnoreCase("null") || strPath.trim().length() == 0
                || strPath.equalsIgnoreCase("false")) {
            return "";
        }
        Bitmap bm = BitmapFactory.decodeFile(strPath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encodedImage;
    }

   /* public static String base64Convert(String strPath) {
        if (strPath == "" || strPath == null || strPath.equalsIgnoreCase("null") || strPath.trim().length() == 0
                || strPath.equalsIgnoreCase("false")) {
            return "";
        }
        Bitmap bitmap = BitmapFactory.decodeFile(strPath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = null;
        try {
            System.gc();
            temp = Base64.encodeToString(b, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (OutOfMemoryError e) {
            baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
            b = baos.toByteArray();
            temp = Base64.encodeToString(b, Base64.DEFAULT);
        }
        return temp;
    }*/

    public static String decimalFormat(String str) {
        if (str == "" || str == null || str.equalsIgnoreCase("null") || str.trim().length() == 0) {
            return "";
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        double db = Double.parseDouble(str);
        return decimalFormat.format(db);
    }

    public static String planString(String str) {
        if (str == "" || str == null || str.equalsIgnoreCase("null") || str.trim().length() == 0) {
            return "";
        }
        return str.replaceAll("[^0-9]", "");
    }
}