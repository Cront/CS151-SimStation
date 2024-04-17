package mvc;
import java.io.*;
import java.beans.*;


public abstract class Model extends Publisher implements Serializable {
    Boolean unsavedChanges = false;
    String fileName = null;
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    public Boolean getUnsavedChanges(){
        return unsavedChanges;
    }
    public void setUnsavedChanges(Boolean unsavedChanges){
        this.unsavedChanges = unsavedChanges;
    }
    public boolean hasUnsavedChanges(){
        return this.unsavedChanges;
    }
    public void changed(){
        setUnsavedChanges(true);
        notifySubscribers();
    }
}