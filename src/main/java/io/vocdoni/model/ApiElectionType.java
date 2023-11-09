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
 * ApiElectionType
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-11-08T21:49:26.053235+01:00[Europe/Prague]")
public class ApiElectionType {
  public static final String SERIALIZED_NAME_ANONYMOUS = "anonymous";
  @SerializedName(SERIALIZED_NAME_ANONYMOUS)
  private Boolean anonymous;

  public static final String SERIALIZED_NAME_AUTOSTART = "autostart";
  @SerializedName(SERIALIZED_NAME_AUTOSTART)
  private Boolean autostart;

  public static final String SERIALIZED_NAME_DYNAMIC_CENSUS = "dynamicCensus";
  @SerializedName(SERIALIZED_NAME_DYNAMIC_CENSUS)
  private Boolean dynamicCensus;

  public static final String SERIALIZED_NAME_INTERRUPTIBLE = "interruptible";
  @SerializedName(SERIALIZED_NAME_INTERRUPTIBLE)
  private Boolean interruptible;

  public static final String SERIALIZED_NAME_SECRET_UNTIL_THE_END = "secretUntilTheEnd";
  @SerializedName(SERIALIZED_NAME_SECRET_UNTIL_THE_END)
  private Boolean secretUntilTheEnd;

  public ApiElectionType() {
  }

  public ApiElectionType anonymous(Boolean anonymous) {
    
    this.anonymous = anonymous;
    return this;
  }

   /**
   * Get anonymous
   * @return anonymous
  **/
  @javax.annotation.Nullable
  public Boolean getAnonymous() {
    return anonymous;
  }


  public void setAnonymous(Boolean anonymous) {
    this.anonymous = anonymous;
  }


  public ApiElectionType autostart(Boolean autostart) {
    
    this.autostart = autostart;
    return this;
  }

   /**
   * Get autostart
   * @return autostart
  **/
  @javax.annotation.Nullable
  public Boolean getAutostart() {
    return autostart;
  }


  public void setAutostart(Boolean autostart) {
    this.autostart = autostart;
  }


  public ApiElectionType dynamicCensus(Boolean dynamicCensus) {
    
    this.dynamicCensus = dynamicCensus;
    return this;
  }

   /**
   * Get dynamicCensus
   * @return dynamicCensus
  **/
  @javax.annotation.Nullable
  public Boolean getDynamicCensus() {
    return dynamicCensus;
  }


  public void setDynamicCensus(Boolean dynamicCensus) {
    this.dynamicCensus = dynamicCensus;
  }


  public ApiElectionType interruptible(Boolean interruptible) {
    
    this.interruptible = interruptible;
    return this;
  }

   /**
   * Get interruptible
   * @return interruptible
  **/
  @javax.annotation.Nullable
  public Boolean getInterruptible() {
    return interruptible;
  }


  public void setInterruptible(Boolean interruptible) {
    this.interruptible = interruptible;
  }


  public ApiElectionType secretUntilTheEnd(Boolean secretUntilTheEnd) {
    
    this.secretUntilTheEnd = secretUntilTheEnd;
    return this;
  }

   /**
   * Get secretUntilTheEnd
   * @return secretUntilTheEnd
  **/
  @javax.annotation.Nullable
  public Boolean getSecretUntilTheEnd() {
    return secretUntilTheEnd;
  }


  public void setSecretUntilTheEnd(Boolean secretUntilTheEnd) {
    this.secretUntilTheEnd = secretUntilTheEnd;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiElectionType apiElectionType = (ApiElectionType) o;
    return Objects.equals(this.anonymous, apiElectionType.anonymous) &&
        Objects.equals(this.autostart, apiElectionType.autostart) &&
        Objects.equals(this.dynamicCensus, apiElectionType.dynamicCensus) &&
        Objects.equals(this.interruptible, apiElectionType.interruptible) &&
        Objects.equals(this.secretUntilTheEnd, apiElectionType.secretUntilTheEnd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(anonymous, autostart, dynamicCensus, interruptible, secretUntilTheEnd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiElectionType {\n");
    sb.append("    anonymous: ").append(toIndentedString(anonymous)).append("\n");
    sb.append("    autostart: ").append(toIndentedString(autostart)).append("\n");
    sb.append("    dynamicCensus: ").append(toIndentedString(dynamicCensus)).append("\n");
    sb.append("    interruptible: ").append(toIndentedString(interruptible)).append("\n");
    sb.append("    secretUntilTheEnd: ").append(toIndentedString(secretUntilTheEnd)).append("\n");
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
    openapiFields.add("anonymous");
    openapiFields.add("autostart");
    openapiFields.add("dynamicCensus");
    openapiFields.add("interruptible");
    openapiFields.add("secretUntilTheEnd");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to ApiElectionType
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!ApiElectionType.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in ApiElectionType is not found in the empty JSON string", ApiElectionType.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!ApiElectionType.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `ApiElectionType` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ApiElectionType.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ApiElectionType' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ApiElectionType> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ApiElectionType.class));

       return (TypeAdapter<T>) new TypeAdapter<ApiElectionType>() {
           @Override
           public void write(JsonWriter out, ApiElectionType value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public ApiElectionType read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of ApiElectionType given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of ApiElectionType
  * @throws IOException if the JSON string is invalid with respect to ApiElectionType
  */
  public static ApiElectionType fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ApiElectionType.class);
  }

 /**
  * Convert an instance of ApiElectionType to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}
