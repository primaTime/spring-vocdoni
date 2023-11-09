/*
 * Vocdoni API
 * The Vocdoni API is a REST API that substitutes the previous RPCs in order to make it easier for  developers/integrators to build on top of the voting protocol. This API facilitates creating votings using Vocdoni, without the hassle of learning a complex blockchain platform, allowing to perform all the features that enable the voting protocol such as creating an account, entity, voting process, census & vote, abstracting as much as possible the complexity and offering simple and straightforward methods to perform those actions.   You can review the API endpoints documentation in this section, the main entities are:  - [**Chain**](chain): The Vocdoni blockchain is named Vochain. It is a Byzantine fault-tolerant network based on Tendermint that executes the Vocdoni Protocol logic represented as a state machine. Its main purpose is to register votes in a  decentralized and verifiable format. In those endpoints, you can consult the state of the chain, transactions costs,  list organizations and get more Vochain info. - [**Accounts**](accounts): Identified by an Ethereum like address. An account can create and manage elections, transfer tokens, give power to other accounts on his behalf (delegates) and manage its metadata. - [**Elections**](elections): Is a rule-set of options and requirements for creating a process in which people vote a series of options. To know more about the params of an election and its lifecycle go [here](../get-started/intro#23-elections). In this section you will find all information related to an election as its information, election keys, submitted votes & how to create a new election. - [**Censuses**](censuses): The census is a key component of any voting process. It specifies the set of users (identified by a public key or address) eligible for participating in an election. To understand more about the Censuses you can check [here](../get-started/intro#21-the-census). Here you will be able to get censuses information like the Merkle root, total weight & size, import/export the censuses and create new ones. - [**Votes**](votes): All the information related to the vote issued by a participant in a vote, you can check the validity of the vote, consult your information and send a vote. - [**SIK**](sik): The Secret Identity Key is a user-generated piece of information that proves the user's identity without revealing it. It is the hash of the user's address, the signature of a public message and an optional secret part. It is used to ensure anonymous voting. All registered accounts or anonymous voters must register a SIK, and they are all stored in a Merkle tree. The `/siks` endpoints help to generate a proof of membership, get the current valid SIK roots, or check if an account has a valid SIK.   ### Errors   Backend error messages list are defined here: https://github.com/vocdoni/vocdoni-node/blob/master/api/errors.go  About the **204 no content** error: this message will be returned only if the asset being queried cannot be found but no other errors have occurred. This response is commonly used to prevent Javascript errors that may arise when a client is waiting for a  transaction to be published. During this waiting period, the client can repeatedly query the endpoint until a  successful response with a status code of 200 is received, thereby avoiding any errors that may occur due to the transaction not being published yet.
 *
 * The version of the OpenAPI document: 2.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package io.vocdoni.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.vocdoni.invoker.JSON;

/**
 * IndexertypesTransaction
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-11-08T21:49:26.053235+01:00[Europe/Prague]")
public class IndexertypesTransaction {
  public static final String SERIALIZED_NAME_BLOCK_HEIGHT = "blockHeight";
  @SerializedName(SERIALIZED_NAME_BLOCK_HEIGHT)
  private Integer blockHeight;

  public static final String SERIALIZED_NAME_TRANSACTION_HASH = "transactionHash";
  @SerializedName(SERIALIZED_NAME_TRANSACTION_HASH)
  private String transactionHash;

  public static final String SERIALIZED_NAME_TRANSACTION_INDEX = "transactionIndex";
  @SerializedName(SERIALIZED_NAME_TRANSACTION_INDEX)
  private Integer transactionIndex;

  public static final String SERIALIZED_NAME_TRANSACTION_NUMBER = "transactionNumber";
  @SerializedName(SERIALIZED_NAME_TRANSACTION_NUMBER)
  private Long transactionNumber;

  /**
   * Gets or Sets transactionType
   */
  @JsonAdapter(TransactionTypeEnum.Adapter.class)
  public enum TransactionTypeEnum {
    VOTE("vote"),
    
    NEWPROCESS("newProcess"),
    
    ADMIN("admin"),
    
    SETPROCESS("setProcess"),
    
    REGISTERKEY("registerKey"),
    
    MINTTOKENS("mintTokens"),
    
    SENDTOKENS("sendTokens"),
    
    SETTRANSACTIONCOSTS("setTransactionCosts"),
    
    SETACCOUNT("setAccount"),
    
    COLLECTFAUCET("collectFaucet"),
    
    SETKEYKEEPER("setKeykeeper");

    private String value;

    TransactionTypeEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static TransactionTypeEnum fromValue(String value) {
      for (TransactionTypeEnum b : TransactionTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<TransactionTypeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final TransactionTypeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public TransactionTypeEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return TransactionTypeEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_TRANSACTION_TYPE = "transactionType";
  @SerializedName(SERIALIZED_NAME_TRANSACTION_TYPE)
  private TransactionTypeEnum transactionType;

  public IndexertypesTransaction() {
  }

  public IndexertypesTransaction blockHeight(Integer blockHeight) {
    
    this.blockHeight = blockHeight;
    return this;
  }

   /**
   * Get blockHeight
   * @return blockHeight
  **/
  @javax.annotation.Nullable
  public Integer getBlockHeight() {
    return blockHeight;
  }


  public void setBlockHeight(Integer blockHeight) {
    this.blockHeight = blockHeight;
  }


  public IndexertypesTransaction transactionHash(String transactionHash) {
    
    this.transactionHash = transactionHash;
    return this;
  }

   /**
   * Get transactionHash
   * @return transactionHash
  **/
  @javax.annotation.Nullable
  public String getTransactionHash() {
    return transactionHash;
  }


  public void setTransactionHash(String transactionHash) {
    this.transactionHash = transactionHash;
  }


  public IndexertypesTransaction transactionIndex(Integer transactionIndex) {
    
    this.transactionIndex = transactionIndex;
    return this;
  }

   /**
   * Get transactionIndex
   * @return transactionIndex
  **/
  @javax.annotation.Nullable
  public Integer getTransactionIndex() {
    return transactionIndex;
  }


  public void setTransactionIndex(Integer transactionIndex) {
    this.transactionIndex = transactionIndex;
  }


  public IndexertypesTransaction transactionNumber(Long transactionNumber) {
    
    this.transactionNumber = transactionNumber;
    return this;
  }

   /**
   * Get transactionNumber
   * @return transactionNumber
  **/
  @javax.annotation.Nullable
  public Long getTransactionNumber() {
    return transactionNumber;
  }


  public void setTransactionNumber(Long transactionNumber) {
    this.transactionNumber = transactionNumber;
  }


  public IndexertypesTransaction transactionType(TransactionTypeEnum transactionType) {
    
    this.transactionType = transactionType;
    return this;
  }

   /**
   * Get transactionType
   * @return transactionType
  **/
  @javax.annotation.Nullable
  public TransactionTypeEnum getTransactionType() {
    return transactionType;
  }


  public void setTransactionType(TransactionTypeEnum transactionType) {
    this.transactionType = transactionType;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IndexertypesTransaction indexertypesTransaction = (IndexertypesTransaction) o;
    return Objects.equals(this.blockHeight, indexertypesTransaction.blockHeight) &&
        Objects.equals(this.transactionHash, indexertypesTransaction.transactionHash) &&
        Objects.equals(this.transactionIndex, indexertypesTransaction.transactionIndex) &&
        Objects.equals(this.transactionNumber, indexertypesTransaction.transactionNumber) &&
        Objects.equals(this.transactionType, indexertypesTransaction.transactionType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(blockHeight, transactionHash, transactionIndex, transactionNumber, transactionType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IndexertypesTransaction {\n");
    sb.append("    blockHeight: ").append(toIndentedString(blockHeight)).append("\n");
    sb.append("    transactionHash: ").append(toIndentedString(transactionHash)).append("\n");
    sb.append("    transactionIndex: ").append(toIndentedString(transactionIndex)).append("\n");
    sb.append("    transactionNumber: ").append(toIndentedString(transactionNumber)).append("\n");
    sb.append("    transactionType: ").append(toIndentedString(transactionType)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


  public static HashSet<String> openapiFields;
  public static HashSet<String> openapiRequiredFields;

  static {
    // a set of all properties/fields (JSON key names)
    openapiFields = new HashSet<String>();
    openapiFields.add("blockHeight");
    openapiFields.add("transactionHash");
    openapiFields.add("transactionIndex");
    openapiFields.add("transactionNumber");
    openapiFields.add("transactionType");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to IndexertypesTransaction
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!IndexertypesTransaction.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in IndexertypesTransaction is not found in the empty JSON string", IndexertypesTransaction.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!IndexertypesTransaction.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `IndexertypesTransaction` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("transactionHash") != null && !jsonObj.get("transactionHash").isJsonNull()) && !jsonObj.get("transactionHash").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `transactionHash` to be a primitive type in the JSON string but got `%s`", jsonObj.get("transactionHash").toString()));
      }
      if ((jsonObj.get("transactionType") != null && !jsonObj.get("transactionType").isJsonNull()) && !jsonObj.get("transactionType").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `transactionType` to be a primitive type in the JSON string but got `%s`", jsonObj.get("transactionType").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!IndexertypesTransaction.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'IndexertypesTransaction' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<IndexertypesTransaction> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(IndexertypesTransaction.class));

       return (TypeAdapter<T>) new TypeAdapter<IndexertypesTransaction>() {
           @Override
           public void write(JsonWriter out, IndexertypesTransaction value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public IndexertypesTransaction read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of IndexertypesTransaction given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of IndexertypesTransaction
  * @throws IOException if the JSON string is invalid with respect to IndexertypesTransaction
  */
  public static IndexertypesTransaction fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, IndexertypesTransaction.class);
  }

 /**
  * Convert an instance of IndexertypesTransaction to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

