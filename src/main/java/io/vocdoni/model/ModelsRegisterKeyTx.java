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
import io.vocdoni.model.ModelsProof;
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
 * ModelsRegisterKeyTx
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-11-10T16:40:32.462750+01:00[Europe/Prague]")
public class ModelsRegisterKeyTx {
  public static final String SERIALIZED_NAME_NEW_KEY = "newKey";
  @SerializedName(SERIALIZED_NAME_NEW_KEY)
  private List<Integer> newKey;

  public static final String SERIALIZED_NAME_NONCE = "nonce";
  @SerializedName(SERIALIZED_NAME_NONCE)
  private Integer nonce;

  public static final String SERIALIZED_NAME_PROCESS_ID = "processId";
  @SerializedName(SERIALIZED_NAME_PROCESS_ID)
  private List<Integer> processId;

  public static final String SERIALIZED_NAME_PROOF = "proof";
  @SerializedName(SERIALIZED_NAME_PROOF)
  private ModelsProof proof;

  public static final String SERIALIZED_NAME_WEIGHT = "weight";
  @SerializedName(SERIALIZED_NAME_WEIGHT)
  private String weight;

  public ModelsRegisterKeyTx() {
  }

  public ModelsRegisterKeyTx newKey(List<Integer> newKey) {
    
    this.newKey = newKey;
    return this;
  }

  public ModelsRegisterKeyTx addNewKeyItem(Integer newKeyItem) {
    if (this.newKey == null) {
      this.newKey = new ArrayList<>();
    }
    this.newKey.add(newKeyItem);
    return this;
  }

   /**
   * New key to register
   * @return newKey
  **/
  @javax.annotation.Nullable
  public List<Integer> getNewKey() {
    return newKey;
  }


  public void setNewKey(List<Integer> newKey) {
    this.newKey = newKey;
  }


  public ModelsRegisterKeyTx nonce(Integer nonce) {
    
    this.nonce = nonce;
    return this;
  }

   /**
   * Unique number per vote attempt, so that replay attacks can&#39;t reuse this payload
   * @return nonce
  **/
  @javax.annotation.Nullable
  public Integer getNonce() {
    return nonce;
  }


  public void setNonce(Integer nonce) {
    this.nonce = nonce;
  }


  public ModelsRegisterKeyTx processId(List<Integer> processId) {
    
    this.processId = processId;
    return this;
  }

  public ModelsRegisterKeyTx addProcessIdItem(Integer processIdItem) {
    if (this.processId == null) {
      this.processId = new ArrayList<>();
    }
    this.processId.add(processIdItem);
    return this;
  }

   /**
   * The process for which the vote is casted
   * @return processId
  **/
  @javax.annotation.Nullable
  public List<Integer> getProcessId() {
    return processId;
  }


  public void setProcessId(List<Integer> processId) {
    this.processId = processId;
  }


  public ModelsRegisterKeyTx proof(ModelsProof proof) {
    
    this.proof = proof;
    return this;
  }

   /**
   * Get proof
   * @return proof
  **/
  @javax.annotation.Nullable
  public ModelsProof getProof() {
    return proof;
  }


  public void setProof(ModelsProof proof) {
    this.proof = proof;
  }


  public ModelsRegisterKeyTx weight(String weight) {
    
    this.weight = weight;
    return this;
  }

   /**
   * Weight to delegate to newKey
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
    ModelsRegisterKeyTx modelsRegisterKeyTx = (ModelsRegisterKeyTx) o;
    return Objects.equals(this.newKey, modelsRegisterKeyTx.newKey) &&
        Objects.equals(this.nonce, modelsRegisterKeyTx.nonce) &&
        Objects.equals(this.processId, modelsRegisterKeyTx.processId) &&
        Objects.equals(this.proof, modelsRegisterKeyTx.proof) &&
        Objects.equals(this.weight, modelsRegisterKeyTx.weight);
  }

  @Override
  public int hashCode() {
    return Objects.hash(newKey, nonce, processId, proof, weight);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelsRegisterKeyTx {\n");
    sb.append("    newKey: ").append(toIndentedString(newKey)).append("\n");
    sb.append("    nonce: ").append(toIndentedString(nonce)).append("\n");
    sb.append("    processId: ").append(toIndentedString(processId)).append("\n");
    sb.append("    proof: ").append(toIndentedString(proof)).append("\n");
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
    openapiFields.add("newKey");
    openapiFields.add("nonce");
    openapiFields.add("processId");
    openapiFields.add("proof");
    openapiFields.add("weight");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to ModelsRegisterKeyTx
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!ModelsRegisterKeyTx.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in ModelsRegisterKeyTx is not found in the empty JSON string", ModelsRegisterKeyTx.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!ModelsRegisterKeyTx.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `ModelsRegisterKeyTx` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      // ensure the optional json data is an array if present
      if (jsonObj.get("newKey") != null && !jsonObj.get("newKey").isJsonNull() && !jsonObj.get("newKey").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `newKey` to be an array in the JSON string but got `%s`", jsonObj.get("newKey").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("processId") != null && !jsonObj.get("processId").isJsonNull() && !jsonObj.get("processId").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `processId` to be an array in the JSON string but got `%s`", jsonObj.get("processId").toString()));
      }
      // validate the optional field `proof`
      if (jsonObj.get("proof") != null && !jsonObj.get("proof").isJsonNull()) {
        ModelsProof.validateJsonElement(jsonObj.get("proof"));
      }
      if ((jsonObj.get("weight") != null && !jsonObj.get("weight").isJsonNull()) && !jsonObj.get("weight").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `weight` to be a primitive type in the JSON string but got `%s`", jsonObj.get("weight").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ModelsRegisterKeyTx.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ModelsRegisterKeyTx' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ModelsRegisterKeyTx> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ModelsRegisterKeyTx.class));

       return (TypeAdapter<T>) new TypeAdapter<ModelsRegisterKeyTx>() {
           @Override
           public void write(JsonWriter out, ModelsRegisterKeyTx value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public ModelsRegisterKeyTx read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of ModelsRegisterKeyTx given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of ModelsRegisterKeyTx
  * @throws IOException if the JSON string is invalid with respect to ModelsRegisterKeyTx
  */
  public static ModelsRegisterKeyTx fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ModelsRegisterKeyTx.class);
  }

 /**
  * Convert an instance of ModelsRegisterKeyTx to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

