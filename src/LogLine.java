import java.util.Date;

public class LogLine {
    public Date Date;
    public String Id;
    public LogType Type;
    public Integer Status; // add enum with Success or None

    public String GetIdAndType(){
        String IdType = Id +Type;
        return IdType;
    }

}
