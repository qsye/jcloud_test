/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package com.ht.b2attr.b2attr_service.schema;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class BltNoLeverageColumnList extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"BltNoLeverageColumnList\",\"namespace\":\"com.ht.b2attr.b2attr_service.schema\",\"fields\":[{\"name\":\"list\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"CloudTest\",\"fields\":[{\"name\":\"t_id\",\"type\":\"int\"},{\"name\":\"t_attribute\",\"type\":\"string\"},{\"name\":\"t_desc\",\"type\":[\"string\",\"null\"]},{\"name\":\"t_dt\",\"type\":{\"type\":\"record\",\"name\":\"Date\",\"namespace\":\"java.util\",\"fields\":[]}}]},\"java-class\":\"java.util.List\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.util.List<com.ht.b2attr.b2attr_service.schema.BltNoLeverageColumn> list;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public BltNoLeverageColumnList() {}

  /**
   * All-args constructor.
   */
  public BltNoLeverageColumnList(java.util.List<com.ht.b2attr.b2attr_service.schema.BltNoLeverageColumn> list) {
    this.list = list;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return list;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: list = (java.util.List<com.ht.b2attr.b2attr_service.schema.BltNoLeverageColumn>)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'list' field.
   */
  public java.util.List<com.ht.b2attr.b2attr_service.schema.BltNoLeverageColumn> getList() {
    return list;
  }

  /**
   * Sets the value of the 'list' field.
   * @param value the value to set.
   */
  public void setList(java.util.List<com.ht.b2attr.b2attr_service.schema.BltNoLeverageColumn> value) {
    this.list = value;
  }

  /** Creates a new CloudTestsList RecordBuilder */
  public static com.ht.b2attr.b2attr_service.schema.BltNoLeverageColumnList.Builder newBuilder() {
    return new com.ht.b2attr.b2attr_service.schema.BltNoLeverageColumnList.Builder();
  }
  
  /** Creates a new CloudTestsList RecordBuilder by copying an existing Builder */
  public static com.ht.b2attr.b2attr_service.schema.BltNoLeverageColumnList.Builder newBuilder(com.ht.b2attr.b2attr_service.schema.BltNoLeverageColumnList.Builder other) {
    return new com.ht.b2attr.b2attr_service.schema.BltNoLeverageColumnList.Builder(other);
  }
  
  /** Creates a new CloudTestsList RecordBuilder by copying an existing CloudTestsList instance */
  public static com.ht.b2attr.b2attr_service.schema.BltNoLeverageColumnList.Builder newBuilder(com.ht.b2attr.b2attr_service.schema.BltNoLeverageColumnList other) {
    return new com.ht.b2attr.b2attr_service.schema.BltNoLeverageColumnList.Builder(other);
  }
  
  /**
   * RecordBuilder for CloudTestsList instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<BltNoLeverageColumnList>
    implements org.apache.avro.data.RecordBuilder<BltNoLeverageColumnList> {

    private java.util.List<com.ht.b2attr.b2attr_service.schema.BltNoLeverageColumn> list;

    /** Creates a new Builder */
    private Builder() {
      super(com.ht.b2attr.b2attr_service.schema.BltNoLeverageColumnList.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(com.ht.b2attr.b2attr_service.schema.BltNoLeverageColumnList.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.list)) {
        this.list = data().deepCopy(fields()[0].schema(), other.list);
        fieldSetFlags()[0] = true;
      }
    }
    
    /** Creates a Builder by copying an existing CloudTestsList instance */
    private Builder(com.ht.b2attr.b2attr_service.schema.BltNoLeverageColumnList other) {
            super(com.ht.b2attr.b2attr_service.schema.BltNoLeverageColumnList.SCHEMA$);
      if (isValidValue(fields()[0], other.list)) {
        this.list = data().deepCopy(fields()[0].schema(), other.list);
        fieldSetFlags()[0] = true;
      }
    }

    /** Gets the value of the 'list' field */
    public java.util.List<com.ht.b2attr.b2attr_service.schema.BltNoLeverageColumn> getList() {
      return list;
    }
    
    /** Sets the value of the 'list' field */
    public com.ht.b2attr.b2attr_service.schema.BltNoLeverageColumnList.Builder setList(java.util.List<com.ht.b2attr.b2attr_service.schema.BltNoLeverageColumn> value) {
      validate(fields()[0], value);
      this.list = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'list' field has been set */
    public boolean hasList() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'list' field */
    public com.ht.b2attr.b2attr_service.schema.BltNoLeverageColumnList.Builder clearList() {
      list = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    @Override
    public BltNoLeverageColumnList build() {
      try {
        BltNoLeverageColumnList record = new BltNoLeverageColumnList();
        record.list = fieldSetFlags()[0] ? this.list : (java.util.List<com.ht.b2attr.b2attr_service.schema.BltNoLeverageColumn>) defaultValue(fields()[0]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
