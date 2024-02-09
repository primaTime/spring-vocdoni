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
import io.vocdoni.model.ModelsTxType;
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
 * ModelsSetKeykeeperTx
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-02-09T17:09:26.351036+01:00[Europe/Prague]")
public class ModelsSetKeykeeperTx {
  public static final String SERIALIZED_NAME_KEYKEEPER = "keykeeper";
  @SerializedName(SERIALIZED_NAME_KEYKEEPER)
  private List<Integer> keykeeper;

  public static final String SERIALIZED_NAME_NONCE = "nonce";
  @SerializedName(SERIALIZED_NAME_NONCE)
  private Integer nonce;

  public static final String SERIALIZED_NAME_TXTYPE = "txtype";
  @SerializedName(SERIALIZED_NAME_TXTYPE)
  private ModelsTxType txtype;

  public ModelsSetKeykeeperTx() {
  }

  public ModelsSetKeykeeperTx keykeeper(List<Integer> keykeeper) {
    
    this.keykeeper = keykeeper;
    return this;
  }

  public ModelsSetKeykeeperTx addKeykeeperItem(Integer keykeeperItem) {
    if (this.keykeeper == null) {
      this.keykeeper = new ArrayList<>();
    }
    this.keykeeper.add(keykeeperItem);
    return this;
  }

   /**
   * Get keykeeper
   * @return keykeeper
  **/
  @javax.annotation.Nullable
  public List<Integer> getKeykeeper() {
    return keykeeper;
  }


  public void setKeykeeper(List<Integer> keykeeper) {
    this.keykeeper = keykeeper;
  }


  public ModelsSetKeykeeperTx nonce(Integer nonce) {
    
    this.nonce = nonce;
    return this;
  }

   /**
   * Get nonce
   * @return nonce
  **/
  @javax.annotation.Nullable
  public Integer getNonce() {
    return nonce;
  }


  public void setNonce(Integer nonce) {
    this.nonce = nonce;
  }


  public ModelsSetKeykeeperTx txtype(ModelsTxType txtype) {
    
    this.txtype = txtype;
    return this;
  }

   /**
   * Get txtype
   * @return txtype
  **/
  @javax.annotation.Nullable
  public ModelsTxType getTxtype() {
    return txtype;
  }


  public void setTxtype(ModelsTxType txtype) {
    this.txtype = txtype;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelsSetKeykeeperTx modelsSetKeykeeperTx = (ModelsSetKeykeeperTx) o;
    return Objects.equals(this.keykeeper, modelsSetKeykeeperTx.keykeeper) &&
        Objects.equals(this.nonce, modelsSetKeykeeperTx.nonce) &&
        Objects.equals(this.txtype, modelsSetKeykeeperTx.txtype);
  }

  @Override
  public int hashCode() {
    return Objects.hash(keykeeper, nonce, txtype);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelsSetKeykeeperTx {\n");
    sb.append("    keykeeper: ").append(toIndentedString(keykeeper)).append("\n");
    sb.append("    nonce: ").append(toIndentedString(nonce)).append("\n");
    sb.append("    txtype: ").append(toIndentedString(txtype)).append("\n");
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
    openapiFields.add("keykeeper");
    openapiFields.add("nonce");
    openapiFields.add("txtype");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to ModelsSetKeykeeperTx
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!ModelsSetKeykeeperTx.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in ModelsSetKeykeeperTx is not found in the empty JSON string", ModelsSetKeykeeperTx.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!ModelsSetKeykeeperTx.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `ModelsSetKeykeeperTx` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      // ensure the optional json data is an array if present
      if (jsonObj.get("keykeeper") != null && !jsonObj.get("keykeeper").isJsonNull() && !jsonObj.get("keykeeper").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `keykeeper` to be an array in the JSON string but got `%s`", jsonObj.get("keykeeper").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ModelsSetKeykeeperTx.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ModelsSetKeykeeperTx' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ModelsSetKeykeeperTx> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ModelsSetKeykeeperTx.class));

       return (TypeAdapter<T>) new TypeAdapter<ModelsSetKeykeeperTx>() {
           @Override
           public void write(JsonWriter out, ModelsSetKeykeeperTx value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public ModelsSetKeykeeperTx read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of ModelsSetKeykeeperTx given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of ModelsSetKeykeeperTx
  * @throws IOException if the JSON string is invalid with respect to ModelsSetKeykeeperTx
  */
  public static ModelsSetKeykeeperTx fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ModelsSetKeykeeperTx.class);
  }

 /**
  * Convert an instance of ModelsSetKeykeeperTx to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

