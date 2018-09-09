package facilities.samir.andrew.facilities.FirebaseHandler;

import android.app.Dialog;
import android.content.Context;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import developer.mokadim.projectmate.dialog.IndicatorStyle;
import developer.mokadim.projectmate.dialog.ProgressDialog;
import facilities.samir.andrew.facilities.interfaces.InterfaceAddDataToFirebase;
import facilities.samir.andrew.facilities.models.ProfileData;
import facilities.samir.andrew.facilities.models.RegestrationData;

public class HandleAddDataToFirebase {
    private static Context context;
    private static HandleAddDataToFirebase instance = null;
    private InterfaceAddDataToFirebase clickListener;
    private static DatabaseReference myRef;

    public static HandleAddDataToFirebase getInstance(Context context) {

        HandleAddDataToFirebase.context = context;

        if (instance == null) {
            instance = new HandleAddDataToFirebase();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            myRef = database.getReference();
            myRef.keepSynced(true);
        }
        return instance;
    }

    public void setClickDialogListener(InterfaceAddDataToFirebase itemClickListener) {
        this.clickListener = itemClickListener;
    }


    public void callAddProfileData(final String flag, RegestrationData profileData) {
        final Dialog progressDialog = new ProgressDialog(context, IndicatorStyle.BallBeat).show();
        progressDialog.show();

            DatabaseReference myRefJobs = myRef.child("Facilities").child("Users");

            myRefJobs.push().setValue(profileData, new DatabaseReference.CompletionListener() {
                public void onComplete(DatabaseError error, DatabaseReference ref) {

                    if (error == null) {
                        clickListener.onDataAddedSuccess(flag);
                    } else {
                        clickListener.onDataAddedFailed(flag);
                    }

                    progressDialog.dismiss();
                }
            });
        }
    }
/*
    public void callReserveChairs(final String flag, List<ModelChair> modelChairs, List<String> chairs) {
        final Dialog progressDialog = new ProgressDialog(context, IndicatorStyle.BallBeat).show();
        progressDialog.show();

        if (HelpMe.getInstance(context).isOnline()) {

            DatabaseReference myRefJobs = myRef.child(context.getString(R.string.firebase_services))
                    .child(context.getString(R.string.firebase_events))
                    .child(context.getString(R.string.firebase_times));
            for (int i = 0; i < chairs.size(); i++) {

                myRefJobs.child(chairs.get(i)).setValue(modelChairs.get(i), new DatabaseReference.CompletionListener() {
                    public void onComplete(DatabaseError error, DatabaseReference ref) {

                        if (error == null) {
                            clickListener.onDataAddedSuccess(flag);
                        } else {
                            clickListener.onDataAddedFailed(flag);
                        }

                        progressDialog.dismiss();
                    }
                });
            }

        } else {
            TastyToast.makeText(context, context.getString(R.string.connection_error), TastyToast.LENGTH_SHORT, TastyToast.ERROR);
            progressDialog.dismiss();
        }

    }
*/


