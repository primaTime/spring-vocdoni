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
 * ApiElectionCreate
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-02-09T17:09:26.351036+01:00[Europe/Prague]")
public class ApiElectionCreate {
  public static final String SERIALIZED_NAME_ELECTION_I_D = "electionID";
  @SerializedName(SERIALIZED_NAME_ELECTION_I_D)
  private String electionID;

  public static final String SERIALIZED_NAME_METADATA = "metadata";
  @SerializedName(SERIALIZED_NAME_METADATA)
  private List<Integer> metadata;

  public static final String SERIALIZED_NAME_METADATA_U_R_L = "metadataURL";
  @SerializedName(SERIALIZED_NAME_METADATA_U_R_L)
  private String metadataURL;

  public static final String SERIALIZED_NAME_TX_HASH = "txHash";
  @SerializedName(SERIALIZED_NAME_TX_HASH)
  private String txHash;

  public static final String SERIALIZED_NAME_TX_PAYLOAD = "txPayload";
  @SerializedName(SERIALIZED_NAME_TX_PAYLOAD)
  private List<Integer> txPayload;

  public ApiElectionCreate() {
  }

  public ApiElectionCreate electionID(String electionID) {
    
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


  public ApiElectionCreate metadata(List<Integer> metadata) {
    
    this.metadata = metadata;
    return this;
  }

  public ApiElectionCreate addMetadataItem(Integer metadataItem) {
    if (this.metadata == null) {
      this.metadata = new ArrayList<>();
    }
    this.metadata.add(metadataItem);
    return this;
  }

   /**
   * Get metadata
   * @return metadata
  **/
  @javax.annotation.Nullable
  public List<Integer> getMetadata() {
    return metadata;
  }


  public void setMetadata(List<Integer> metadata) {
    this.metadata = metadata;
  }


  public ApiElectionCreate metadataURL(String metadataURL) {
    
    this.metadataURL = metadataURL;
    return this;
  }

   /**
   * Get metadataURL
   * @return metadataURL
  **/
  @javax.annotation.Nullable
  public String getMetadataURL() {
    return metadataURL;
  }


  public void setMetadataURL(String metadataURL) {
    this.metadataURL = metadataURL;
  }


  public ApiElectionCreate txHash(String txHash) {
    
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


  public ApiElectionCreate txPayload(List<Integer> txPayload) {
    
    this.txPayload = txPayload;
    return this;
  }

  public ApiElectionCreate addTxPayloadItem(Integer txPayloadItem) {
    if (this.txPayload == null) {
      this.txPayload = new ArrayList<>();
    }
    this.txPayload.add(txPayloadItem);
    return this;
  }

   /**
   * Get txPayload
   * @return txPayload
  **/
  @javax.annotation.Nullable
  public List<Integer> getTxPayload() {
    return txPayload;
  }


  public void setTxPayload(List<Integer> txPayload) {
    this.txPayload = txPayload;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiElectionCreate apiElectionCreate = (ApiElectionCreate) o;
    return Objects.equals(this.electionID, apiElectionCreate.electionID) &&
        Objects.equals(this.metadata, apiElectionCreate.metadata) &&
        Objects.equals(this.metadataURL, apiElectionCreate.metadataURL) &&
        Objects.equals(this.txHash, apiElectionCreate.txHash) &&
        Objects.equals(this.txPayload, apiElectionCreate.txPayload);
  }

  @Override
  public int hashCode() {
    return Objects.hash(electionID, metadata, metadataURL, txHash, txPayload);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiElectionCreate {\n");
    sb.append("    electionID: ").append(toIndentedString(electionID)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    metadataURL: ").append(toIndentedString(metadataURL)).append("\n");
    sb.append("    txHash: ").append(toIndentedString(txHash)).append("\n");
    sb.append("    txPayload: ").append(toIndentedString(txPayload)).append("\n");
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
    openapiFields.add("electionID");
    openapiFields.add("metadata");
    openapiFields.add("metadataURL");
    openapiFields.add("txHash");
    openapiFields.add("txPayload");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to ApiElectionCreate
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!ApiElectionCreate.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in ApiElectionCreate is not found in the empty JSON string", ApiElectionCreate.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!ApiElectionCreate.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `ApiElectionCreate` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("electionID") != null && !jsonObj.get("electionID").isJsonNull()) && !jsonObj.get("electionID").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `electionID` to be a primitive type in the JSON string but got `%s`", jsonObj.get("electionID").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("metadata") != null && !jsonObj.get("metadata").isJsonNull() && !jsonObj.get("metadata").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `metadata` to be an array in the JSON string but got `%s`", jsonObj.get("metadata").toString()));
      }
      if ((jsonObj.get("metadataURL") != null && !jsonObj.get("metadataURL").isJsonNull()) && !jsonObj.get("metadataURL").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `metadataURL` to be a primitive type in the JSON string but got `%s`", jsonObj.get("metadataURL").toString()));
      }
      if ((jsonObj.get("txHash") != null && !jsonObj.get("txHash").isJsonNull()) && !jsonObj.get("txHash").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `txHash` to be a primitive type in the JSON string but got `%s`", jsonObj.get("txHash").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("txPayload") != null && !jsonObj.get("txPayload").isJsonNull() && !jsonObj.get("txPayload").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `txPayload` to be an array in the JSON string but got `%s`", jsonObj.get("txPayload").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ApiElectionCreate.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ApiElectionCreate' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ApiElectionCreate> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ApiElectionCreate.class));

       return (TypeAdapter<T>) new TypeAdapter<ApiElectionCreate>() {
           @Override
           public void write(JsonWriter out, ApiElectionCreate value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public ApiElectionCreate read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of ApiElectionCreate given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of ApiElectionCreate
  * @throws IOException if the JSON string is invalid with respect to ApiElectionCreate
  */
  public static ApiElectionCreate fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ApiElectionCreate.class);
  }

 /**
  * Convert an instance of ApiElectionCreate to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

