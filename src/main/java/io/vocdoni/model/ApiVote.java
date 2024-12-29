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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
 * ApiVote
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-02-09T17:09:26.351036+01:00[Europe/Prague]")
public class ApiVote {
  public static final String SERIALIZED_NAME_BLOCK_HEIGHT = "blockHeight";
  @SerializedName(SERIALIZED_NAME_BLOCK_HEIGHT)
  private Integer blockHeight;

  public static final String SERIALIZED_NAME_DATE = "date";
  @SerializedName(SERIALIZED_NAME_DATE)
  private String date;

  public static final String SERIALIZED_NAME_ELECTION_I_D = "electionID";
  @SerializedName(SERIALIZED_NAME_ELECTION_I_D)
  private String electionID;

  public static final String SERIALIZED_NAME_ENCRYPTION_KEYS = "encryptionKeys";
  @SerializedName(SERIALIZED_NAME_ENCRYPTION_KEYS)
  private List<Integer> encryptionKeys;

  public static final String SERIALIZED_NAME_NUMBER = "number";
  @SerializedName(SERIALIZED_NAME_NUMBER)
  private Integer number;

  public static final String SERIALIZED_NAME_OVERWRITE_COUNT = "overwriteCount";
  @SerializedName(SERIALIZED_NAME_OVERWRITE_COUNT)
  private Integer overwriteCount;

  public static final String SERIALIZED_NAME_PACKAGE = "package";
  @SerializedName(SERIALIZED_NAME_PACKAGE)
  private List<Integer> _package;

  public static final String SERIALIZED_NAME_TRANSACTION_INDEX = "transactionIndex";
  @SerializedName(SERIALIZED_NAME_TRANSACTION_INDEX)
  private Integer transactionIndex;

  public static final String SERIALIZED_NAME_TX_HASH = "txHash";
  @SerializedName(SERIALIZED_NAME_TX_HASH)
  private String txHash;

  public static final String SERIALIZED_NAME_VOTE_I_D = "voteID";
  @SerializedName(SERIALIZED_NAME_VOTE_I_D)
  private String voteID;

  public static final String SERIALIZED_NAME_VOTER_I_D = "voterID";
  @SerializedName(SERIALIZED_NAME_VOTER_I_D)
  private String voterID;

  public static final String SERIALIZED_NAME_WEIGHT = "weight";
  @SerializedName(SERIALIZED_NAME_WEIGHT)
  private String weight;

  public ApiVote() {
  }

  public ApiVote blockHeight(Integer blockHeight) {
    
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


  public ApiVote date(String date) {
    
    this.date = date;
    return this;
  }

   /**
   * Date when the vote was emitted
   * @return date
  **/
  @javax.annotation.Nullable
  public String getDate() {
    return date;
  }


  public void setDate(String date) {
    this.date = date;
  }


  public ApiVote electionID(String electionID) {
    
    this.electionID = electionID;
    return this;
  }

   /**
   * Get electionID
   * @return electionID
  **/
  @javax.annotation.Nullable
  public String getElectionID() {
    return electionID;
  }


  public void setElectionID(String electionID) {
    this.electionID = electionID;
  }


  public ApiVote encryptionKeys(List<Integer> encryptionKeys) {
    
    this.encryptionKeys = encryptionKeys;
    return this;
  }

  public ApiVote addEncryptionKeysItem(Integer encryptionKeysItem) {
    if (this.encryptionKeys == null) {
      this.encryptionKeys = new ArrayList<>();
    }
    this.encryptionKeys.add(encryptionKeysItem);
    return this;
  }

   /**
   * Sent only for encrypted elections (no results until the end)
   * @return encryptionKeys
  **/
  @javax.annotation.Nullable
  public List<Integer> getEncryptionKeys() {
    return encryptionKeys;
  }


  public void setEncryptionKeys(List<Integer> encryptionKeys) {
    this.encryptionKeys = encryptionKeys;
  }


  public ApiVote number(Integer number) {
    
    this.number = number;
    return this;
  }

   /**
   * Get number
   * @return number
  **/
  @javax.annotation.Nullable
  public Integer getNumber() {
    return number;
  }


  public void setNumber(Integer number) {
    this.number = number;
  }


  public ApiVote overwriteCount(Integer overwriteCount) {
    
    this.overwriteCount = overwriteCount;
    return this;
  }

   /**
   * Get overwriteCount
   * @return overwriteCount
  **/
  @javax.annotation.Nullable
  public Integer getOverwriteCount() {
    return overwriteCount;
  }


  public void setOverwriteCount(Integer overwriteCount) {
    this.overwriteCount = overwriteCount;
  }


  public ApiVote _package(List<Integer> _package) {
    
    this._package = _package;
    return this;
  }

  public ApiVote addPackageItem(Integer _packageItem) {
    if (this._package == null) {
      this._package = new ArrayList<>();
    }
    this._package.add(_packageItem);
    return this;
  }

   /**
   * For encrypted elections this will be codified
   * @return _package
  **/
  @javax.annotation.Nullable
  public List<Integer> getPackage() {
    return _package;
  }


  public void setPackage(List<Integer> _package) {
    this._package = _package;
  }


  public ApiVote transactionIndex(Integer transactionIndex) {
    
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


  public ApiVote txHash(String txHash) {
    
    this.txHash = txHash;
    return this;
  }

   /**
   * Get txHash
   * @return txHash
  **/
  @javax.annotation.Nullable
  public String getTxHash() {
    return txHash;
  }


  public void setTxHash(String txHash) {
    this.txHash = txHash;
  }


  public ApiVote voteID(String voteID) {
    
    this.voteID = voteID;
    return this;
  }

   /**
   * Get voteID
   * @return voteID
  **/
  @javax.annotation.Nullable
  public String getVoteID() {
    return voteID;
  }


  public void setVoteID(String voteID) {
    this.voteID = voteID;
  }


  public ApiVote voterID(String voterID) {
    
    this.voterID = voterID;
    return this;
  }

   /**
   * Get voterID
   * @return voterID
  **/
  @javax.annotation.Nullable
  public String getVoterID() {
    return voterID;
  }


  public void setVoterID(String voterID) {
    this.voterID = voterID;
  }


  public ApiVote weight(String weight) {
    
    this.weight = weight;
    return this;
  }

   /**
   * Get weight
   * @return weight
  **/
  @javax.annotation.Nullable
  public String getWeight() {
    return weight;
  }


  public void setWeight(String weight) {
    this.weight = weight;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiVote apiVote = (ApiVote) o;
    return Objects.equals(this.blockHeight, apiVote.blockHeight) &&
        Objects.equals(this.date, apiVote.date) &&
        Objects.equals(this.electionID, apiVote.electionID) &&
        Objects.equals(this.encryptionKeys, apiVote.encryptionKeys) &&
        Objects.equals(this.number, apiVote.number) &&
        Objects.equals(this.overwriteCount, apiVote.overwriteCount) &&
        Objects.equals(this._package, apiVote._package) &&
        Objects.equals(this.transactionIndex, apiVote.transactionIndex) &&
        Objects.equals(this.txHash, apiVote.txHash) &&
        Objects.equals(this.voteID, apiVote.voteID) &&
        Objects.equals(this.voterID, apiVote.voterID) &&
        Objects.equals(this.weight, apiVote.weight);
  }

  @Override
  public int hashCode() {
    return Objects.hash(blockHeight, date, electionID, encryptionKeys, number, overwriteCount, _package, transactionIndex, txHash, voteID, voterID, weight);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiVote {\n");
    sb.append("    blockHeight: ").append(toIndentedString(blockHeight)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    electionID: ").append(toIndentedString(electionID)).append("\n");
    sb.append("    encryptionKeys: ").append(toIndentedString(encryptionKeys)).append("\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    overwriteCount: ").append(toIndentedString(overwriteCount)).append("\n");
    sb.append("    _package: ").append(toIndentedString(_package)).append("\n");
    sb.append("    transactionIndex: ").append(toIndentedString(transactionIndex)).append("\n");
    sb.append("    txHash: ").append(toIndentedString(txHash)).append("\n");
    sb.append("    voteID: ").append(toIndentedString(voteID)).append("\n");
    sb.append("    voterID: ").append(toIndentedString(voterID)).append("\n");
    sb.append("    weight: ").append(toIndentedString(weight)).append("\n");
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
    openapiFields.add("date");
    openapiFields.add("electionID");
    openapiFields.add("encryptionKeys");
    openapiFields.add("number");
    openapiFields.add("overwriteCount");
    openapiFields.add("package");
    openapiFields.add("transactionIndex");
    openapiFields.add("txHash");
    openapiFields.add("voteID");
    openapiFields.add("voterID");
    openapiFields.add("weight");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to ApiVote
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!ApiVote.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in ApiVote is not found in the empty JSON string", ApiVote.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!ApiVote.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `ApiVote` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("date") != null && !jsonObj.get("date").isJsonNull()) && !jsonObj.get("date").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `date` to be a primitive type in the JSON string but got `%s`", jsonObj.get("date").toString()));
      }
      if ((jsonObj.get("electionID") != null && !jsonObj.get("electionID").isJsonNull()) && !jsonObj.get("electionID").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `electionID` to be a primitive type in the JSON string but got `%s`", jsonObj.get("electionID").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("encryptionKeys") != null && !jsonObj.get("encryptionKeys").isJsonNull() && !jsonObj.get("encryptionKeys").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `encryptionKeys` to be an array in the JSON string but got `%s`", jsonObj.get("encryptionKeys").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("package") != null && !jsonObj.get("package").isJsonNull() && !jsonObj.get("package").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `package` to be an array in the JSON string but got `%s`", jsonObj.get("package").toString()));
      }
      if ((jsonObj.get("txHash") != null && !jsonObj.get("txHash").isJsonNull()) && !jsonObj.get("txHash").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `txHash` to be a primitive type in the JSON string but got `%s`", jsonObj.get("txHash").toString()));
      }
      if ((jsonObj.get("voteID") != null && !jsonObj.get("voteID").isJsonNull()) && !jsonObj.get("voteID").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `voteID` to be a primitive type in the JSON string but got `%s`", jsonObj.get("voteID").toString()));
      }
      if ((jsonObj.get("voterID") != null && !jsonObj.get("voterID").isJsonNull()) && !jsonObj.get("voterID").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `voterID` to be a primitive type in the JSON string but got `%s`", jsonObj.get("voterID").toString()));
      }
      if ((jsonObj.get("weight") != null && !jsonObj.get("weight").isJsonNull()) && !jsonObj.get("weight").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `weight` to be a primitive type in the JSON string but got `%s`", jsonObj.get("weight").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ApiVote.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ApiVote' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ApiVote> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ApiVote.class));

       return (TypeAdapter<T>) new TypeAdapter<ApiVote>() {
           @Override
           public void write(JsonWriter out, ApiVote value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public ApiVote read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);

             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of ApiVote given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of ApiVote
  * @throws IOException if the JSON string is invalid with respect to ApiVote
  */
  public static ApiVote fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ApiVote.class);
  }

 /**
  * Convert an instance of ApiVote to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

