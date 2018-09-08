package facilities.samir.andrew.facilities.interfaces;

/**
 * Created by lenovo on 2/23/2016.
 */
public interface InterfaceAddDataToFirebase {

    void onDataAddedSuccess(String flag);

    void onDataAddedFailed(String flag);
    void onDataAddedRepeated(String flag);

}
