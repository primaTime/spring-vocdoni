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
 * ApiElectionResults
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-02-09T17:09:26.351036+01:00[Europe/Prague]")
public class ApiElectionResults {
  public static final String SERIALIZED_NAME_CENSUS_ROOT = "censusRoot";
  @SerializedName(SERIALIZED_NAME_CENSUS_ROOT)
  private String censusRoot;

  public static final String SERIALIZED_NAME_ELECTION_ID = "electionId";
  @SerializedName(SERIALIZED_NAME_ELECTION_ID)
  private String electionId;

  public static final String SERIALIZED_NAME_ORGANIZATION_ID = "organizationId";
  @SerializedName(SERIALIZED_NAME_ORGANIZATION_ID)
  private String organizationId;

  public static final String SERIALIZED_NAME_RESULTS = "results";
  @SerializedName(SERIALIZED_NAME_RESULTS)
  private List<List<Object>> results;

  public static final String SERIALIZED_NAME_SOURCE_CONTRACT_ADDRESS = "sourceContractAddress";
  @SerializedName(SERIALIZED_NAME_SOURCE_CONTRACT_ADDRESS)
  private String sourceContractAddress;

  public ApiElectionResults() {
  }

  public ApiElectionResults censusRoot(String censusRoot) {
    
    this.censusRoot = censusRoot;
    return this;
  }

   /**
   * CensusRoot is the root of the census tree
   * @return censusRoot
  **/
  @javax.annotation.Nullable
  public String getCensusRoot() {
    return censusRoot;
  }


  public void setCensusRoot(String censusRoot) {
    this.censusRoot = censusRoot;
  }


  public ApiElectionResults electionId(String electionId) {
    
    this.electionId = electionId;
    return this;
  }

   /**
   * ElectionID is the ID of the election
   * @return electionId
  **/
  @javax.annotation.Nullable
  public String getElectionId() {
    return electionId;
  }


  public void setElectionId(String electionId) {
    this.electionId = electionId;
  }


  public ApiElectionResults organizationId(String organizationId) {
    
    this.organizationId = organizationId;
    return this;
  }

   /**
   * OrganizationID is the ID of the organization that created the election
   * @return organizationId
  **/
  @javax.annotation.Nullable
  public String getOrganizationId() {
    return organizationId;
  }


  public void setOrganizationId(String organizationId) {
    this.organizationId = organizationId;
  }


  public ApiElectionResults results(List<List<Object>> results) {
    
    this.results = results;
    return this;
  }

  public ApiElectionResults addResultsItem(List<Object> resultsItem) {
    if (this.results == null) {
      this.results = new ArrayList<>();
    }
    this.results.add(resultsItem);
    return this;
  }

   /**
   * Results is the list of votes
   * @return results
  **/
  @javax.annotation.Nullable
  public List<List<Object>> getResults() {
    return results;
  }


  public void setResults(List<List<Object>> results) {
    this.results = results;
  }


  public ApiElectionResults sourceContractAddress(String sourceContractAddress) {
    
    this.sourceContractAddress = sourceContractAddress;
    return this;
  }

   /**
   * SourceContractAddress is the address of the smart contract containing the census
   * @return sourceContractAddress
  **/
  @javax.annotation.Nullable
  public String getSourceContractAddress() {
    return sourceContractAddress;
  }


  public void setSourceContractAddress(String sourceContractAddress) {
    this.sourceContractAddress = sourceContractAddress;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiElectionResults apiElectionResults = (ApiElectionResults) o;
    return Objects.equals(this.censusRoot, apiElectionResults.censusRoot) &&
        Objects.equals(this.electionId, apiElectionResults.electionId) &&
        Objects.equals(this.organizationId, apiElectionResults.organizationId) &&
        Objects.equals(this.results, apiElectionResults.results) &&
        Objects.equals(this.sourceContractAddress, apiElectionResults.sourceContractAddress);
  }

  @Override
  public int hashCode() {
    return Objects.hash(censusRoot, electionId, organizationId, results, sourceContractAddress);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiElectionResults {\n");
    sb.append("    censusRoot: ").append(toIndentedString(censusRoot)).append("\n");
    sb.append("    electionId: ").append(toIndentedString(electionId)).append("\n");
    sb.append("    organizationId: ").append(toIndentedString(organizationId)).append("\n");
    sb.append("    results: ").append(toIndentedString(results)).append("\n");
    sb.append("    sourceContractAddress: ").append(toIndentedString(sourceContractAddress)).append("\n");
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
    openapiFields.add("censusRoot");
    openapiFields.add("electionId");
    openapiFields.add("organizationId");
    openapiFields.add("results");
    openapiFields.add("sourceContractAddress");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to ApiElectionResults
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!ApiElectionResults.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in ApiElectionResults is not found in the empty JSON string", ApiElectionResults.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!ApiElectionResults.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `ApiElectionResults` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("censusRoot") != null && !jsonObj.get("censusRoot").isJsonNull()) && !jsonObj.get("censusRoot").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `censusRoot` to be a primitive type in the JSON string but got `%s`", jsonObj.get("censusRoot").toString()));
      }
      if ((jsonObj.get("electionId") != null && !jsonObj.get("electionId").isJsonNull()) && !jsonObj.get("electionId").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `electionId` to be a primitive type in the JSON string but got `%s`", jsonObj.get("electionId").toString()));
      }
      if ((jsonObj.get("organizationId") != null && !jsonObj.get("organizationId").isJsonNull()) && !jsonObj.get("organizationId").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `organizationId` to be a primitive type in the JSON string but got `%s`", jsonObj.get("organizationId").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("results") != null && !jsonObj.get("results").isJsonNull() && !jsonObj.get("results").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `results` to be an array in the JSON string but got `%s`", jsonObj.get("results").toString()));
      }
      if ((jsonObj.get("sourceContractAddress") != null && !jsonObj.get("sourceContractAddress").isJsonNull()) && !jsonObj.get("sourceContractAddress").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `sourceContractAddress` to be a primitive type in the JSON string but got `%s`", jsonObj.get("sourceContractAddress").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ApiElectionResults.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ApiElectionResults' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ApiElectionResults> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ApiElectionResults.class));

       return (TypeAdapter<T>) new TypeAdapter<ApiElectionResults>() {
           @Override
           public void write(JsonWriter out, ApiElectionResults value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public ApiElectionResults read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of ApiElectionResults given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of ApiElectionResults
  * @throws IOException if the JSON string is invalid with respect to ApiElectionResults
  */
  public static ApiElectionResults fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ApiElectionResults.class);
  }

 /**
  * Convert an instance of ApiElectionResults to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

