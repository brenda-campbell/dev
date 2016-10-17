package com.mcreceiverdemo.et;


import java.util.Date;

import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.InternalName;
import com.exacttarget.fuelsdk.annotations.SoapObject;
import com.exacttarget.fuelsdk.internal.DataExtensionField;

/**
 * An <code>ETDataExtensionColumn</code> object represents a
 * data extension column in the Salesforce Marketing Cloud.
 */

@SoapObject(internalType = DataExtensionField.class, unretrievable = {
    "ID", "Description", "DataExtension", "Precision"
})
public class ETDataExtensionColumnObject extends ETSoapObjectExtended {
    @ExternalName("id")
    private String id = null;
    @ExternalName("key")
    @InternalName("customerKey")
    private String key = null;
    @ExternalName("name")
    private String name = null;
    @ExternalName("description")
    private String description = null;
    @ExternalName("createdDate")
    private Date createdDate = null;
    @ExternalName("modifiedDate")
    private Date modifiedDate = null;
    @ExternalName("dataExtension")
    private ETDataExtensionObject dataExtension = null;
    @ExternalName("type")
    @InternalName("fieldType")
    private Type type = null;
    @ExternalName("defaultValue")
    private String defaultValue = null;
    @ExternalName("isPrimaryKey")
    private Boolean isPrimaryKey = null;
    @ExternalName("isRequired")
    private Boolean isRequired = null;
    @ExternalName("length")
    @InternalName("maxLength")
    private Integer length = null;
    @ExternalName("precision")
    private Integer precision = null;
    @ExternalName("scale")
    private Integer scale = null;

    public ETDataExtensionColumnObject() {}

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toLowerCase();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public ETDataExtensionObject getDataExtension() {
        return dataExtension;
    }

    public void setDataExtension(ETDataExtensionObject dataExtension) {
        this.dataExtension = dataExtension;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Boolean getIsPrimaryKey() {
        return isPrimaryKey;
    }

    public void setIsPrimaryKey(Boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }

    public Boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    /**
     * @deprecated
     * Use <code>getKey()</code>.
     */
    @Deprecated
    public String getCustomerKey() {
        return getKey();
    }

    /**
     * @deprecated
     * Use <code>setKey()</code>.
     */
    @Deprecated
    public void setCustomerKey(String customerKey) {
        setKey(customerKey);
    }

    public enum Type {
        BOOLEAN("Boolean"),
        DATE("Date"),
        DECIMAL("Decimal"),
        EMAIL_ADDRESS("EmailAddress"),
        LOCALE("Locale"),
        NUMBER("Number"),
        PHONE("Phone"),
        TEXT("Text");
        private final String value;

        Type(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }

        public static Type fromValue(String value) {
            for (Type v : Type.values()) {
                if (v.value.equals(value)) {
                    return v;
                }
            }
            throw new IllegalArgumentException(value);
        }
    }
}
