// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ProtoDemo.proto

package com.seal.protobuf.msg;

public final class ProtoDemo {
  private ProtoDemo() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface demoOrBuilder extends
      // @@protoc_insertion_point(interface_extends:com.seal.protobuf.msg.demo)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int32 id = 1;</code>
     * @return Whether the id field is set.
     */
    boolean hasId();
    /**
     * <code>required int32 id = 1;</code>
     * @return The id.
     */
    int getId();

    /**
     * <code>required string name = 2;</code>
     * @return Whether the name field is set.
     */
    boolean hasName();
    /**
     * <code>required string name = 2;</code>
     * @return The name.
     */
    java.lang.String getName();
    /**
     * <code>required string name = 2;</code>
     * @return The bytes for name.
     */
    com.google.protobuf.ByteString
        getNameBytes();

    /**
     * <code>optional string email = 3;</code>
     * @return Whether the email field is set.
     */
    boolean hasEmail();
    /**
     * <code>optional string email = 3;</code>
     * @return The email.
     */
    java.lang.String getEmail();
    /**
     * <code>optional string email = 3;</code>
     * @return The bytes for email.
     */
    com.google.protobuf.ByteString
        getEmailBytes();

    /**
     * <code>repeated string friends = 4;</code>
     * @return A list containing the friends.
     */
    java.util.List<java.lang.String>
        getFriendsList();
    /**
     * <code>repeated string friends = 4;</code>
     * @return The count of friends.
     */
    int getFriendsCount();
    /**
     * <code>repeated string friends = 4;</code>
     * @param index The index of the element to return.
     * @return The friends at the given index.
     */
    java.lang.String getFriends(int index);
    /**
     * <code>repeated string friends = 4;</code>
     * @param index The index of the value to return.
     * @return The bytes of the friends at the given index.
     */
    com.google.protobuf.ByteString
        getFriendsBytes(int index);
  }
  /**
   * Protobuf type {@code com.seal.protobuf.msg.demo}
   */
  public  static final class demo extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:com.seal.protobuf.msg.demo)
      demoOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use demo.newBuilder() to construct.
    private demo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private demo() {
      name_ = "";
      email_ = "";
      friends_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new demo();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private demo(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {
              bitField0_ |= 0x00000001;
              id_ = input.readInt32();
              break;
            }
            case 18: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000002;
              name_ = bs;
              break;
            }
            case 26: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000004;
              email_ = bs;
              break;
            }
            case 34: {
              com.google.protobuf.ByteString bs = input.readBytes();
              if (!((mutable_bitField0_ & 0x00000008) != 0)) {
                friends_ = new com.google.protobuf.LazyStringArrayList();
                mutable_bitField0_ |= 0x00000008;
              }
              friends_.add(bs);
              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        if (((mutable_bitField0_ & 0x00000008) != 0)) {
          friends_ = friends_.getUnmodifiableView();
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.seal.protobuf.msg.ProtoDemo.internal_static_com_seal_protobuf_msg_demo_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.seal.protobuf.msg.ProtoDemo.internal_static_com_seal_protobuf_msg_demo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.seal.protobuf.msg.ProtoDemo.demo.class, com.seal.protobuf.msg.ProtoDemo.demo.Builder.class);
    }

    private int bitField0_;
    public static final int ID_FIELD_NUMBER = 1;
    private int id_;
    /**
     * <code>required int32 id = 1;</code>
     * @return Whether the id field is set.
     */
    public boolean hasId() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>required int32 id = 1;</code>
     * @return The id.
     */
    public int getId() {
      return id_;
    }

    public static final int NAME_FIELD_NUMBER = 2;
    private volatile java.lang.Object name_;
    /**
     * <code>required string name = 2;</code>
     * @return Whether the name field is set.
     */
    public boolean hasName() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <code>required string name = 2;</code>
     * @return The name.
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          name_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string name = 2;</code>
     * @return The bytes for name.
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int EMAIL_FIELD_NUMBER = 3;
    private volatile java.lang.Object email_;
    /**
     * <code>optional string email = 3;</code>
     * @return Whether the email field is set.
     */
    public boolean hasEmail() {
      return ((bitField0_ & 0x00000004) != 0);
    }
    /**
     * <code>optional string email = 3;</code>
     * @return The email.
     */
    public java.lang.String getEmail() {
      java.lang.Object ref = email_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          email_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string email = 3;</code>
     * @return The bytes for email.
     */
    public com.google.protobuf.ByteString
        getEmailBytes() {
      java.lang.Object ref = email_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        email_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int FRIENDS_FIELD_NUMBER = 4;
    private com.google.protobuf.LazyStringList friends_;
    /**
     * <code>repeated string friends = 4;</code>
     * @return A list containing the friends.
     */
    public com.google.protobuf.ProtocolStringList
        getFriendsList() {
      return friends_;
    }
    /**
     * <code>repeated string friends = 4;</code>
     * @return The count of friends.
     */
    public int getFriendsCount() {
      return friends_.size();
    }
    /**
     * <code>repeated string friends = 4;</code>
     * @param index The index of the element to return.
     * @return The friends at the given index.
     */
    public java.lang.String getFriends(int index) {
      return friends_.get(index);
    }
    /**
     * <code>repeated string friends = 4;</code>
     * @param index The index of the value to return.
     * @return The bytes of the friends at the given index.
     */
    public com.google.protobuf.ByteString
        getFriendsBytes(int index) {
      return friends_.getByteString(index);
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasName()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (((bitField0_ & 0x00000001) != 0)) {
        output.writeInt32(1, id_);
      }
      if (((bitField0_ & 0x00000002) != 0)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, name_);
      }
      if (((bitField0_ & 0x00000004) != 0)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, email_);
      }
      for (int i = 0; i < friends_.size(); i++) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, friends_.getRaw(i));
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) != 0)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, id_);
      }
      if (((bitField0_ & 0x00000002) != 0)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, name_);
      }
      if (((bitField0_ & 0x00000004) != 0)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, email_);
      }
      {
        int dataSize = 0;
        for (int i = 0; i < friends_.size(); i++) {
          dataSize += computeStringSizeNoTag(friends_.getRaw(i));
        }
        size += dataSize;
        size += 1 * getFriendsList().size();
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.seal.protobuf.msg.ProtoDemo.demo)) {
        return super.equals(obj);
      }
      com.seal.protobuf.msg.ProtoDemo.demo other = (com.seal.protobuf.msg.ProtoDemo.demo) obj;

      if (hasId() != other.hasId()) return false;
      if (hasId()) {
        if (getId()
            != other.getId()) return false;
      }
      if (hasName() != other.hasName()) return false;
      if (hasName()) {
        if (!getName()
            .equals(other.getName())) return false;
      }
      if (hasEmail() != other.hasEmail()) return false;
      if (hasEmail()) {
        if (!getEmail()
            .equals(other.getEmail())) return false;
      }
      if (!getFriendsList()
          .equals(other.getFriendsList())) return false;
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      if (hasId()) {
        hash = (37 * hash) + ID_FIELD_NUMBER;
        hash = (53 * hash) + getId();
      }
      if (hasName()) {
        hash = (37 * hash) + NAME_FIELD_NUMBER;
        hash = (53 * hash) + getName().hashCode();
      }
      if (hasEmail()) {
        hash = (37 * hash) + EMAIL_FIELD_NUMBER;
        hash = (53 * hash) + getEmail().hashCode();
      }
      if (getFriendsCount() > 0) {
        hash = (37 * hash) + FRIENDS_FIELD_NUMBER;
        hash = (53 * hash) + getFriendsList().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.seal.protobuf.msg.ProtoDemo.demo parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.seal.protobuf.msg.ProtoDemo.demo parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.seal.protobuf.msg.ProtoDemo.demo parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.seal.protobuf.msg.ProtoDemo.demo parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.seal.protobuf.msg.ProtoDemo.demo parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.seal.protobuf.msg.ProtoDemo.demo parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.seal.protobuf.msg.ProtoDemo.demo parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.seal.protobuf.msg.ProtoDemo.demo parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.seal.protobuf.msg.ProtoDemo.demo parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.seal.protobuf.msg.ProtoDemo.demo parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.seal.protobuf.msg.ProtoDemo.demo parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.seal.protobuf.msg.ProtoDemo.demo parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.seal.protobuf.msg.ProtoDemo.demo prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code com.seal.protobuf.msg.demo}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:com.seal.protobuf.msg.demo)
        com.seal.protobuf.msg.ProtoDemo.demoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.seal.protobuf.msg.ProtoDemo.internal_static_com_seal_protobuf_msg_demo_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.seal.protobuf.msg.ProtoDemo.internal_static_com_seal_protobuf_msg_demo_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.seal.protobuf.msg.ProtoDemo.demo.class, com.seal.protobuf.msg.ProtoDemo.demo.Builder.class);
      }

      // Construct using com.seal.protobuf.msg.ProtoDemo.demo.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        id_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        name_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        email_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        friends_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.seal.protobuf.msg.ProtoDemo.internal_static_com_seal_protobuf_msg_demo_descriptor;
      }

      @java.lang.Override
      public com.seal.protobuf.msg.ProtoDemo.demo getDefaultInstanceForType() {
        return com.seal.protobuf.msg.ProtoDemo.demo.getDefaultInstance();
      }

      @java.lang.Override
      public com.seal.protobuf.msg.ProtoDemo.demo build() {
        com.seal.protobuf.msg.ProtoDemo.demo result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.seal.protobuf.msg.ProtoDemo.demo buildPartial() {
        com.seal.protobuf.msg.ProtoDemo.demo result = new com.seal.protobuf.msg.ProtoDemo.demo(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          result.id_ = id_;
          to_bitField0_ |= 0x00000001;
        }
        if (((from_bitField0_ & 0x00000002) != 0)) {
          to_bitField0_ |= 0x00000002;
        }
        result.name_ = name_;
        if (((from_bitField0_ & 0x00000004) != 0)) {
          to_bitField0_ |= 0x00000004;
        }
        result.email_ = email_;
        if (((bitField0_ & 0x00000008) != 0)) {
          friends_ = friends_.getUnmodifiableView();
          bitField0_ = (bitField0_ & ~0x00000008);
        }
        result.friends_ = friends_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.seal.protobuf.msg.ProtoDemo.demo) {
          return mergeFrom((com.seal.protobuf.msg.ProtoDemo.demo)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.seal.protobuf.msg.ProtoDemo.demo other) {
        if (other == com.seal.protobuf.msg.ProtoDemo.demo.getDefaultInstance()) return this;
        if (other.hasId()) {
          setId(other.getId());
        }
        if (other.hasName()) {
          bitField0_ |= 0x00000002;
          name_ = other.name_;
          onChanged();
        }
        if (other.hasEmail()) {
          bitField0_ |= 0x00000004;
          email_ = other.email_;
          onChanged();
        }
        if (!other.friends_.isEmpty()) {
          if (friends_.isEmpty()) {
            friends_ = other.friends_;
            bitField0_ = (bitField0_ & ~0x00000008);
          } else {
            ensureFriendsIsMutable();
            friends_.addAll(other.friends_);
          }
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        if (!hasId()) {
          return false;
        }
        if (!hasName()) {
          return false;
        }
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.seal.protobuf.msg.ProtoDemo.demo parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.seal.protobuf.msg.ProtoDemo.demo) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int id_ ;
      /**
       * <code>required int32 id = 1;</code>
       * @return Whether the id field is set.
       */
      public boolean hasId() {
        return ((bitField0_ & 0x00000001) != 0);
      }
      /**
       * <code>required int32 id = 1;</code>
       * @return The id.
       */
      public int getId() {
        return id_;
      }
      /**
       * <code>required int32 id = 1;</code>
       * @param value The id to set.
       * @return This builder for chaining.
       */
      public Builder setId(int value) {
        bitField0_ |= 0x00000001;
        id_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 id = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        id_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object name_ = "";
      /**
       * <code>required string name = 2;</code>
       * @return Whether the name field is set.
       */
      public boolean hasName() {
        return ((bitField0_ & 0x00000002) != 0);
      }
      /**
       * <code>required string name = 2;</code>
       * @return The name.
       */
      public java.lang.String getName() {
        java.lang.Object ref = name_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            name_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string name = 2;</code>
       * @return The bytes for name.
       */
      public com.google.protobuf.ByteString
          getNameBytes() {
        java.lang.Object ref = name_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          name_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string name = 2;</code>
       * @param value The name to set.
       * @return This builder for chaining.
       */
      public Builder setName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        name_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string name = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearName() {
        bitField0_ = (bitField0_ & ~0x00000002);
        name_ = getDefaultInstance().getName();
        onChanged();
        return this;
      }
      /**
       * <code>required string name = 2;</code>
       * @param value The bytes for name to set.
       * @return This builder for chaining.
       */
      public Builder setNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        name_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object email_ = "";
      /**
       * <code>optional string email = 3;</code>
       * @return Whether the email field is set.
       */
      public boolean hasEmail() {
        return ((bitField0_ & 0x00000004) != 0);
      }
      /**
       * <code>optional string email = 3;</code>
       * @return The email.
       */
      public java.lang.String getEmail() {
        java.lang.Object ref = email_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            email_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string email = 3;</code>
       * @return The bytes for email.
       */
      public com.google.protobuf.ByteString
          getEmailBytes() {
        java.lang.Object ref = email_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          email_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string email = 3;</code>
       * @param value The email to set.
       * @return This builder for chaining.
       */
      public Builder setEmail(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        email_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string email = 3;</code>
       * @return This builder for chaining.
       */
      public Builder clearEmail() {
        bitField0_ = (bitField0_ & ~0x00000004);
        email_ = getDefaultInstance().getEmail();
        onChanged();
        return this;
      }
      /**
       * <code>optional string email = 3;</code>
       * @param value The bytes for email to set.
       * @return This builder for chaining.
       */
      public Builder setEmailBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        email_ = value;
        onChanged();
        return this;
      }

      private com.google.protobuf.LazyStringList friends_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      private void ensureFriendsIsMutable() {
        if (!((bitField0_ & 0x00000008) != 0)) {
          friends_ = new com.google.protobuf.LazyStringArrayList(friends_);
          bitField0_ |= 0x00000008;
         }
      }
      /**
       * <code>repeated string friends = 4;</code>
       * @return A list containing the friends.
       */
      public com.google.protobuf.ProtocolStringList
          getFriendsList() {
        return friends_.getUnmodifiableView();
      }
      /**
       * <code>repeated string friends = 4;</code>
       * @return The count of friends.
       */
      public int getFriendsCount() {
        return friends_.size();
      }
      /**
       * <code>repeated string friends = 4;</code>
       * @param index The index of the element to return.
       * @return The friends at the given index.
       */
      public java.lang.String getFriends(int index) {
        return friends_.get(index);
      }
      /**
       * <code>repeated string friends = 4;</code>
       * @param index The index of the value to return.
       * @return The bytes of the friends at the given index.
       */
      public com.google.protobuf.ByteString
          getFriendsBytes(int index) {
        return friends_.getByteString(index);
      }
      /**
       * <code>repeated string friends = 4;</code>
       * @param index The index to set the value at.
       * @param value The friends to set.
       * @return This builder for chaining.
       */
      public Builder setFriends(
          int index, java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureFriendsIsMutable();
        friends_.set(index, value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string friends = 4;</code>
       * @param value The friends to add.
       * @return This builder for chaining.
       */
      public Builder addFriends(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureFriendsIsMutable();
        friends_.add(value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string friends = 4;</code>
       * @param values The friends to add.
       * @return This builder for chaining.
       */
      public Builder addAllFriends(
          java.lang.Iterable<java.lang.String> values) {
        ensureFriendsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, friends_);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string friends = 4;</code>
       * @return This builder for chaining.
       */
      public Builder clearFriends() {
        friends_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000008);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string friends = 4;</code>
       * @param value The bytes of the friends to add.
       * @return This builder for chaining.
       */
      public Builder addFriendsBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureFriendsIsMutable();
        friends_.add(value);
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:com.seal.protobuf.msg.demo)
    }

    // @@protoc_insertion_point(class_scope:com.seal.protobuf.msg.demo)
    private static final com.seal.protobuf.msg.ProtoDemo.demo DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.seal.protobuf.msg.ProtoDemo.demo();
    }

    public static com.seal.protobuf.msg.ProtoDemo.demo getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    @java.lang.Deprecated public static final com.google.protobuf.Parser<demo>
        PARSER = new com.google.protobuf.AbstractParser<demo>() {
      @java.lang.Override
      public demo parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new demo(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<demo> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<demo> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.seal.protobuf.msg.ProtoDemo.demo getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_seal_protobuf_msg_demo_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_seal_protobuf_msg_demo_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017ProtoDemo.proto\022\025com.seal.protobuf.msg" +
      "\"@\n\004demo\022\n\n\002id\030\001 \002(\005\022\014\n\004name\030\002 \002(\t\022\r\n\005em" +
      "ail\030\003 \001(\t\022\017\n\007friends\030\004 \003(\tB\"\n\025com.seal.p" +
      "rotobuf.msgB\tProtoDemo"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_com_seal_protobuf_msg_demo_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_seal_protobuf_msg_demo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_seal_protobuf_msg_demo_descriptor,
        new java.lang.String[] { "Id", "Name", "Email", "Friends", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}