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
 * ApiElectionCensus
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-11-10T16:40:32.462750+01:00[Europe/Prague]")
public class ApiElectionCensus {
  public static final String SERIALIZED_NAME_CENSUS_ORIGIN = "censusOrigin";
  @SerializedName(SERIALIZED_NAME_CENSUS_ORIGIN)
  private String censusOrigin;

  public static final String SERIALIZED_NAME_CENSUS_ROOT = "censusRoot";
  @SerializedName(SERIALIZED_NAME_CENSUS_ROOT)
  private String censusRoot;

  public static final String SERIALIZED_NAME_CENSUS_U_R_L = "censusURL";
  @SerializedName(SERIALIZED_NAME_CENSUS_U_R_L)
  private String censusURL;

  public static final String SERIALIZED_NAME_MAX_CENSUS_SIZE = "maxCensusSize";
  @SerializedName(SERIALIZED_NAME_MAX_CENSUS_SIZE)
  private Integer maxCensusSize;

  public static final String SERIALIZED_NAME_POST_REGISTER_CENSUS_ROOT = "postRegisterCensusRoot";
  @SerializedName(SERIALIZED_NAME_POST_REGISTER_CENSUS_ROOT)
  private String postRegisterCensusRoot;

  public ApiElectionCensus() {
  }

  public ApiElectionCensus censusOrigin(String censusOrigin) {
    
    this.censusOrigin = censusOrigin;
    return this;
  }

   /**
   * Get censusOrigin
   * @return censusOrigin
  **/
  @javax.annotation.Nullable
  public String getCensusOrigin() {
    return censusOrigin;
  }


  public void setCensusOrigin(String censusOrigin) {
    this.censusOrigin = censusOrigin;
  }


  public ApiElectionCensus censusRoot(String censusRoot) {
    
    this.censusRoot = censusRoot;
    return this;
  }

   /**
   * Get censusRoot
   * @return censusRoot
  **/
  @javax.annotation.Nullable
  public String getCensusRoot() {
    return censusRoot;
  }


  public void setCensusRoot(String censusRoot) {
    this.censusRoot = censusRoot;
  }


  public ApiElectionCensus censusURL(String censusURL) {
    
    this.censusURL = censusURL;
    return this;
  }

   /**
   * Get censusURL
   * @return censusURL
  **/
  @javax.annotation.Nullable
  public String getCensusURL() {
    return censusURL;
  }


  public void setCensusURL(String censusURL) {
    this.censusURL = censusURL;
  }


  public ApiElectionCensus maxCensusSize(Integer maxCensusSize) {
    
    this.maxCensusSize = maxCensusSize;
    return this;
  }

   /**
   * Get maxCensusSize
   * @return maxCensusSize
  **/
  @javax.annotation.Nullable
  public Integer getMaxCensusSize() {
    return maxCensusSize;
  }


  public void setMaxCensusSize(Integer maxCensusSize) {
    this.maxCensusSize = maxCensusSize;
  }


  public ApiElectionCensus postRegisterCensusRoot(String postRegisterCensusRoot) {
    
    this.postRegisterCensusRoot = postRegisterCensusRoot;
    return this;
  }

   /**
   * Get postRegisterCensusRoot
   * @return postRegisterCensusRoot
  **/
  @javax.annotation.Nullable
  public String getPostRegisterCensusRoot() {
    return postRegisterCensusRoot;
  }


  public void setPostRegisterCensusRoot(String postRegisterCensusRoot) {
    this.postRegisterCensusRoot = postRegisterCensusRoot;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiElectionCensus apiElectionCensus = (ApiElectionCensus) o;
    return Objects.equals(this.censusOrigin, apiElectionCensus.censusOrigin) &&
        Objects.equals(this.censusRoot, apiElectionCensus.censusRoot) &&
        Objects.equals(this.censusURL, apiElectionCensus.censusURL) &&
        Objects.equals(this.maxCensusSize, apiElectionCensus.maxCensusSize) &&
        Objects.equals(this.postRegisterCensusRoot, apiElectionCensus.postRegisterCensusRoot);
  }

  @Override
  public int hashCode() {
    return Objects.hash(censusOrigin, censusRoot, censusURL, maxCensusSize, postRegisterCensusRoot);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiElectionCensus {\n");
    sb.append("    censusOrigin: ").append(toIndentedString(censusOrigin)).append("\n");
    sb.append("    censusRoot: ").append(toIndentedString(censusRoot)).append("\n");
    sb.append("    censusURL: ").append(toIndentedString(censusURL)).append("\n");
    sb.append("    maxCensusSize: ").append(toIndentedString(maxCensusSize)).append("\n");
    sb.append("    postRegisterCensusRoot: ").append(toIndentedString(postRegisterCensusRoot)).append("\n");
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
    openapiFields.add("censusOrigin");
    openapiFields.add("censusRoot");
    openapiFields.add("censusURL");
    openapiFields.add("maxCensusSize");
    openapiFields.add("postRegisterCensusRoot");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to ApiElectionCensus
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!ApiElectionCensus.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in ApiElectionCensus is not found in the empty JSON string", ApiElectionCensus.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!ApiElectionCensus.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `ApiElectionCensus` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("censusOrigin") != null && !jsonObj.get("censusOrigin").isJsonNull()) && !jsonObj.get("censusOrigin").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `censusOrigin` to be a primitive type in the JSON string but got `%s`", jsonObj.get("censusOrigin").toString()));
      }
      if ((jsonObj.get("censusRoot") != null && !jsonObj.get("censusRoot").isJsonNull()) && !jsonObj.get("censusRoot").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `censusRoot` to be a primitive type in the JSON string but got `%s`", jsonObj.get("censusRoot").toString()));
      }
      if ((jsonObj.get("censusURL") != null && !jsonObj.get("censusURL").isJsonNull()) && !jsonObj.get("censusURL").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `censusURL` to be a primitive type in the JSON string but got `%s`", jsonObj.get("censusURL").toString()));
      }
      if ((jsonObj.get("postRegisterCensusRoot") != null && !jsonObj.get("postRegisterCensusRoot").isJsonNull()) && !jsonObj.get("postRegisterCensusRoot").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `postRegisterCensusRoot` to be a primitive type in the JSON string but got `%s`", jsonObj.get("postRegisterCensusRoot").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ApiElectionCensus.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ApiElectionCensus' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ApiElectionCensus> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ApiElectionCensus.class));

       return (TypeAdapter<T>) new TypeAdapter<ApiElectionCensus>() {
           @Override
           public void write(JsonWriter out, ApiElectionCensus value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public ApiElectionCensus read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of ApiElectionCensus given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of ApiElectionCensus
  * @throws IOException if the JSON string is invalid with respect to ApiElectionCensus
  */
  public static ApiElectionCensus fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ApiElectionCensus.class);
  }

 /**
  * Convert an instance of ApiElectionCensus to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

