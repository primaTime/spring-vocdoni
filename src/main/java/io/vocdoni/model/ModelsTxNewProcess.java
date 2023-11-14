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
import io.vocdoni.model.ModelsNewProcessTx;
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
 * ModelsTxNewProcess
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-11-10T16:40:32.462750+01:00[Europe/Prague]")
public class ModelsTxNewProcess {
  public static final String SERIALIZED_NAME_NEW_PROCESS = "newProcess";
  @SerializedName(SERIALIZED_NAME_NEW_PROCESS)
  private ModelsNewProcessTx newProcess;

  public ModelsTxNewProcess() {
  }

  public ModelsTxNewProcess newProcess(ModelsNewProcessTx newProcess) {
    
    this.newProcess = newProcess;
    return this;
  }

   /**
   * Get newProcess
   * @return newProcess
  **/
  @javax.annotation.Nullable
  public ModelsNewProcessTx getNewProcess() {
    return newProcess;
  }


  public void setNewProcess(ModelsNewProcessTx newProcess) {
    this.newProcess = newProcess;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelsTxNewProcess modelsTxNewProcess = (ModelsTxNewProcess) o;
    return Objects.equals(this.newProcess, modelsTxNewProcess.newProcess);
  }

  @Override
  public int hashCode() {
    return Objects.hash(newProcess);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelsTxNewProcess {\n");
    sb.append("    newProcess: ").append(toIndentedString(newProcess)).append("\n");
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
    openapiFields.add("newProcess");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to ModelsTxNewProcess
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!ModelsTxNewProcess.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in ModelsTxNewProcess is not found in the empty JSON string", ModelsTxNewProcess.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!ModelsTxNewProcess.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `ModelsTxNewProcess` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      // validate the optional field `newProcess`
      if (jsonObj.get("newProcess") != null && !jsonObj.get("newProcess").isJsonNull()) {
        ModelsNewProcessTx.validateJsonElement(jsonObj.get("newProcess"));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ModelsTxNewProcess.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ModelsTxNewProcess' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ModelsTxNewProcess> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ModelsTxNewProcess.class));

       return (TypeAdapter<T>) new TypeAdapter<ModelsTxNewProcess>() {
           @Override
           public void write(JsonWriter out, ModelsTxNewProcess value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public ModelsTxNewProcess read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of ModelsTxNewProcess given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of ModelsTxNewProcess
  * @throws IOException if the JSON string is invalid with respect to ModelsTxNewProcess
  */
  public static ModelsTxNewProcess fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ModelsTxNewProcess.class);
  }

 /**
  * Convert an instance of ModelsTxNewProcess to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

