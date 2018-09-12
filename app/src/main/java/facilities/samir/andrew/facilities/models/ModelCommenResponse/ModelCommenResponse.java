package facilities.samir.andrew.facilities.models.ModelCommenResponse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by andre on 21-Jan-18.
 */

public class ModelCommenResponse
{

    @SerializedName("Status")
    private String Status;
    @SerializedName(value = "ResponseMessage", alternate = "Message")
    private String ResponseMessage;

    @SerializedName("Data")
    private Object Data;

    public String getStatus()
    {
        return Status;
    }

    public void setStatus(String Status)
    {
        this.Status = Status;
    }

    public String getResponseMessage()
    {
        return ResponseMessage;
    }

    public void setResponseMessage(String ResponseMessage)
    {
        this.ResponseMessage = ResponseMessage;
    }

    public Object getData()
    {
        return Data;
    }

    public void setData(Object data)
    {
        Data = data;
    }
}
