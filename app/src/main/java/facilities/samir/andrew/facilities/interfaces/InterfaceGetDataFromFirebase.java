package facilities.samir.andrew.facilities.interfaces;

import com.google.firebase.database.DataSnapshot;

public interface InterfaceGetDataFromFirebase {
    // id is selected id from dialog
    // name is selected name
    // flag witch dialog clciked
    void onGetDataFromFirebase(DataSnapshot dataSnapshot, String flag);

//    void onGetStageChairs(List<ModelChair> modelChairLists, String flag);

//    void onChairChanged(ModelChair modelChair, String flag);
}
