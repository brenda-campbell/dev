package com.mcreceiverdemo.et;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.InternalName;
import com.exacttarget.fuelsdk.annotations.SoapObject;
import com.exacttarget.fuelsdk.internal.DataExtensionObject;

/**
 * An <code>ETDataExtensionRow</code> object represents a
 * data extension row in the Salesforce Marketing Cloud.
 */

@SoapObject(internalType = DataExtensionObject.class)
public class ETDataExtensionRowObject extends ETSoapObjectExtended {
    @ExternalName("dataExtensionKey")
    @InternalName("customerKey")
    private String dataExtensionKey = null;
    @ExternalName("columns")
    @InternalName("properties")
    private Map<String, String> columns = new HashMap<String, String>();

    public ETDataExtensionRowObject() {}

    @Override
    public String getId() {
        // no ID on this object type
        return null;
    }

    @Override
    public void setId(String id) {
        // no ID on this object type
    }

    public String getDataExtensionKey() {
        return dataExtensionKey;
    }

    public void setDataExtensionKey(String dataExtensionKey) {
        this.dataExtensionKey = dataExtensionKey;
    }

    public String getColumn(String name) {
        return columns.get(name.toLowerCase());
    }

    public void setColumn(String name, String value) {
        setColumn(name, value, true);
    }

    public void setColumn(String name, String value, boolean setModified) {
        if (setModified) {
            setModified(name, true);
        }
        columns.put(name.toLowerCase(), value);
    }

    public Set<String> getColumnNames() {
        return columns.keySet();
    }

    /**
     * @deprecated
     * Use <code>getColumn</code> and <code>setColumn</code>.
     */
    @Deprecated
    public Map<String, String> getColumns() {
        return columns;
    }

    /**
     * @deprecated
     * Use <code>getColumn</code> and <code>setColumn</code>.
     */
    @Deprecated
    public void setColumns(Map<String, String> columns) {
        // note: this needs to be here because reflection needs it
        for (Map.Entry<String, String> column : columns.entrySet()) {
            setColumn(column.getKey(), column.getValue());
        }
    }
    
    public boolean equals(Object obj) {
        if (obj == null)    return false;
        if (!obj.getClass().equals(getClass())) {
            return false;
        }
        // Note: We are using compared object as a source of columns
        // This way we can use it to check for any changes that may apply from this to original object
        ETDataExtensionRowObject src, target;
        src = (ETDataExtensionRowObject) obj;
        target = this;
        for (String column : src.getColumnNames()) {
            if (!src.getColumn(column).equals(target.getColumn(column))) {
                return false;
            }
        }
        return true;
    }	 
}
