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
import io.vocdoni.model.ApiElectionCensus;
import io.vocdoni.model.ApiElectionMetadata;
import io.vocdoni.model.ApiElectionMode;
import io.vocdoni.model.ApiTallyMode;
import io.vocdoni.model.ApiVoteMode;
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
 * ApiElection
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-11-08T21:49:26.053235+01:00[Europe/Prague]")
public class ApiElection {
  public static final String SERIALIZED_NAME_CENSUS = "census";
  @SerializedName(SERIALIZED_NAME_CENSUS)
  private ApiElectionCensus census;

  public static final String SERIALIZED_NAME_CREATION_TIME = "creationTime";
  @SerializedName(SERIALIZED_NAME_CREATION_TIME)
  private String creationTime;

  public static final String SERIALIZED_NAME_ELECTION_ID = "electionId";
  @SerializedName(SERIALIZED_NAME_ELECTION_ID)
  private String electionId;

  public static final String SERIALIZED_NAME_ELECTION_MODE = "electionMode";
  @SerializedName(SERIALIZED_NAME_ELECTION_MODE)
  private ApiElectionMode electionMode;

  public static final String SERIALIZED_NAME_END_DATE = "endDate";
  @SerializedName(SERIALIZED_NAME_END_DATE)
  private String endDate;

  public static final String SERIALIZED_NAME_FINAL_RESULTS = "finalResults";
  @SerializedName(SERIALIZED_NAME_FINAL_RESULTS)
  private Boolean finalResults;

  public static final String SERIALIZED_NAME_MANUALLY_ENDED = "manuallyEnded";
  @SerializedName(SERIALIZED_NAME_MANUALLY_ENDED)
  private Boolean manuallyEnded;

  public static final String SERIALIZED_NAME_METADATA = "metadata";
  @SerializedName(SERIALIZED_NAME_METADATA)
  private ApiElectionMetadata metadata;

  public static final String SERIALIZED_NAME_METADATA_U_R_L = "metadataURL";
  @SerializedName(SERIALIZED_NAME_METADATA_U_R_L)
  private String metadataURL;

  public static final String SERIALIZED_NAME_ORGANIZATION_ID = "organizationId";
  @SerializedName(SERIALIZED_NAME_ORGANIZATION_ID)
  private String organizationId;

  public static final String SERIALIZED_NAME_RESULT = "result";
  @SerializedName(SERIALIZED_NAME_RESULT)
  private List<List<Object>> result;

  public static final String SERIALIZED_NAME_START_DATE = "startDate";
  @SerializedName(SERIALIZED_NAME_START_DATE)
  private String startDate;

  public static final String SERIALIZED_NAME_STATUS = "status";
  @SerializedName(SERIALIZED_NAME_STATUS)
  private String status;

  public static final String SERIALIZED_NAME_TALLY_MODE = "tallyMode";
  @SerializedName(SERIALIZED_NAME_TALLY_MODE)
  private ApiTallyMode tallyMode;

  public static final String SERIALIZED_NAME_VOTE_COUNT = "voteCount";
  @SerializedName(SERIALIZED_NAME_VOTE_COUNT)
  private Integer voteCount;

  public static final String SERIALIZED_NAME_VOTE_MODE = "voteMode";
  @SerializedName(SERIALIZED_NAME_VOTE_MODE)
  private ApiVoteMode voteMode;

  public ApiElection() {
  }

  public ApiElection census(ApiElectionCensus census) {
    
    this.census = census;
    return this;
  }

   /**
   * Get census
   * @return census
  **/
  @javax.annotation.Nullable
  public ApiElectionCensus getCensus() {
    return census;
  }


  public void setCensus(ApiElectionCensus census) {
    this.census = census;
  }


  public ApiElection creationTime(String creationTime) {
    
    this.creationTime = creationTime;
    return this;
  }

   /**
   * Get creationTime
   * @return creationTime
  **/
  @javax.annotation.Nullable
  public String getCreationTime() {
    return creationTime;
  }


  public void setCreationTime(String creationTime) {
    this.creationTime = creationTime;
  }


  public ApiElection electionId(String electionId) {
    
    this.electionId = electionId;
    return this;
  }

   /**
   * Get electionId
   * @return electionId
  **/
  @javax.annotation.Nullable
  public String getElectionId() {
    return electionId;
  }


  public void setElectionId(String electionId) {
    this.electionId = electionId;
  }


  public ApiElection electionMode(ApiElectionMode electionMode) {
    
    this.electionMode = electionMode;
    return this;
  }

   /**
   * Get electionMode
   * @return electionMode
  **/
  @javax.annotation.Nullable
  public ApiElectionMode getElectionMode() {
    return electionMode;
  }


  public void setElectionMode(ApiElectionMode electionMode) {
    this.electionMode = electionMode;
  }


  public ApiElection endDate(String endDate) {
    
    this.endDate = endDate;
    return this;
  }

   /**
   * Get endDate
   * @return endDate
  **/
  @javax.annotation.Nullable
  public String getEndDate() {
    return endDate;
  }


  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }


  public ApiElection finalResults(Boolean finalResults) {
    
    this.finalResults = finalResults;
    return this;
  }

   /**
   * Get finalResults
   * @return finalResults
  **/
  @javax.annotation.Nullable
  public Boolean getFinalResults() {
    return finalResults;
  }


  public void setFinalResults(Boolean finalResults) {
    this.finalResults = finalResults;
  }


  public ApiElection manuallyEnded(Boolean manuallyEnded) {
    
    this.manuallyEnded = manuallyEnded;
    return this;
  }

   /**
   * Get manuallyEnded
   * @return manuallyEnded
  **/
  @javax.annotation.Nullable
  public Boolean getManuallyEnded() {
    return manuallyEnded;
  }


  public void setManuallyEnded(Boolean manuallyEnded) {
    this.manuallyEnded = manuallyEnded;
  }


  public ApiElection metadata(ApiElectionMetadata metadata) {
    
    this.metadata = metadata;
    return this;
  }

   /**
   * Get metadata
   * @return metadata
  **/
  @javax.annotation.Nullable
  public ApiElectionMetadata getMetadata() {
    return metadata;
  }


  public void setMetadata(ApiElectionMetadata metadata) {
    this.metadata = metadata;
  }


  public ApiElection metadataURL(String metadataURL) {
    
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


  public ApiElection organizationId(String organizationId) {
    
    this.organizationId = organizationId;
    return this;
  }

   /**
   * Get organizationId
   * @return organizationId
  **/
  @javax.annotation.Nullable
  public String getOrganizationId() {
    return organizationId;
  }


  public void setOrganizationId(String organizationId) {
    this.organizationId = organizationId;
  }


  public ApiElection result(List<List<Object>> result) {
    
    this.result = result;
    return this;
  }

  public ApiElection addResultItem(List<Object> resultItem) {
    if (this.result == null) {
      this.result = new ArrayList<>();
    }
    this.result.add(resultItem);
    return this;
  }

   /**
   * Get result
   * @return result
  **/
  @javax.annotation.Nullable
  public List<List<Object>> getResult() {
    return result;
  }


  public void setResult(List<List<Object>> result) {
    this.result = result;
  }


  public ApiElection startDate(String startDate) {
    
    this.startDate = startDate;
    return this;
  }

   /**
   * Get startDate
   * @return startDate
  **/
  @javax.annotation.Nullable
  public String getStartDate() {
    return startDate;
  }


  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }


  public ApiElection status(String status) {
    
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  @javax.annotation.Nullable
  public String getStatus() {
    return status;
  }


  public void setStatus(String status) {
    this.status = status;
  }


  public ApiElection tallyMode(ApiTallyMode tallyMode) {
    
    this.tallyMode = tallyMode;
    return this;
  }

   /**
   * Get tallyMode
   * @return tallyMode
  **/
  @javax.annotation.Nullable
  public ApiTallyMode getTallyMode() {
    return tallyMode;
  }


  public void setTallyMode(ApiTallyMode tallyMode) {
    this.tallyMode = tallyMode;
  }


  public ApiElection voteCount(Integer voteCount) {
    
    this.voteCount = voteCount;
    return this;
  }

   /**
   * Get voteCount
   * @return voteCount
  **/
  @javax.annotation.Nullable
  public Integer getVoteCount() {
    return voteCount;
  }


  public void setVoteCount(Integer voteCount) {
    this.voteCount = voteCount;
  }


  public ApiElection voteMode(ApiVoteMode voteMode) {
    
    this.voteMode = voteMode;
    return this;
  }

   /**
   * Get voteMode
   * @return voteMode
  **/
  @javax.annotation.Nullable
  public ApiVoteMode getVoteMode() {
    return voteMode;
  }


  public void setVoteMode(ApiVoteMode voteMode) {
    this.voteMode = voteMode;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiElection apiElection = (ApiElection) o;
    return Objects.equals(this.census, apiElection.census) &&
        Objects.equals(this.creationTime, apiElection.creationTime) &&
        Objects.equals(this.electionId, apiElection.electionId) &&
        Objects.equals(this.electionMode, apiElection.electionMode) &&
        Objects.equals(this.endDate, apiElection.endDate) &&
        Objects.equals(this.finalResults, apiElection.finalResults) &&
        Objects.equals(this.manuallyEnded, apiElection.manuallyEnded) &&
        Objects.equals(this.metadata, apiElection.metadata) &&
        Objects.equals(this.metadataURL, apiElection.metadataURL) &&
        Objects.equals(this.organizationId, apiElection.organizationId) &&
        Objects.equals(this.result, apiElection.result) &&
        Objects.equals(this.startDate, apiElection.startDate) &&
        Objects.equals(this.status, apiElection.status) &&
        Objects.equals(this.tallyMode, apiElection.tallyMode) &&
        Objects.equals(this.voteCount, apiElection.voteCount) &&
        Objects.equals(this.voteMode, apiElection.voteMode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(census, creationTime, electionId, electionMode, endDate, finalResults, manuallyEnded, metadata, metadataURL, organizationId, result, startDate, status, tallyMode, voteCount, voteMode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiElection {\n");
    sb.append("    census: ").append(toIndentedString(census)).append("\n");
    sb.append("    creationTime: ").append(toIndentedString(creationTime)).append("\n");
    sb.append("    electionId: ").append(toIndentedString(electionId)).append("\n");
    sb.append("    electionMode: ").append(toIndentedString(electionMode)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    finalResults: ").append(toIndentedString(finalResults)).append("\n");
    sb.append("    manuallyEnded: ").append(toIndentedString(manuallyEnded)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    metadataURL: ").append(toIndentedString(metadataURL)).append("\n");
    sb.append("    organizationId: ").append(toIndentedString(organizationId)).append("\n");
    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    tallyMode: ").append(toIndentedString(tallyMode)).append("\n");
    sb.append("    voteCount: ").append(toIndentedString(voteCount)).append("\n");
    sb.append("    voteMode: ").append(toIndentedString(voteMode)).append("\n");
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
    openapiFields.add("census");
    openapiFields.add("creationTime");
    openapiFields.add("electionId");
    openapiFields.add("electionMode");
    openapiFields.add("endDate");
    openapiFields.add("finalResults");
    openapiFields.add("manuallyEnded");
    openapiFields.add("metadata");
    openapiFields.add("metadataURL");
    openapiFields.add("organizationId");
    openapiFields.add("result");
    openapiFields.add("startDate");
    openapiFields.add("status");
    openapiFields.add("tallyMode");
    openapiFields.add("voteCount");
    openapiFields.add("voteMode");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to ApiElection
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!ApiElection.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in ApiElection is not found in the empty JSON string", ApiElection.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!ApiElection.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `ApiElection` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      // validate the optional field `census`
      if (jsonObj.get("census") != null && !jsonObj.get("census").isJsonNull()) {
        ApiElectionCensus.validateJsonElement(jsonObj.get("census"));
      }
      if ((jsonObj.get("creationTime") != null && !jsonObj.get("creationTime").isJsonNull()) && !jsonObj.get("creationTime").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `creationTime` to be a primitive type in the JSON string but got `%s`", jsonObj.get("creationTime").toString()));
      }
      if ((jsonObj.get("electionId") != null && !jsonObj.get("electionId").isJsonNull()) && !jsonObj.get("electionId").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `electionId` to be a primitive type in the JSON string but got `%s`", jsonObj.get("electionId").toString()));
      }
      // validate the optional field `electionMode`
      if (jsonObj.get("electionMode") != null && !jsonObj.get("electionMode").isJsonNull()) {
        ApiElectionMode.validateJsonElement(jsonObj.get("electionMode"));
      }
      if ((jsonObj.get("endDate") != null && !jsonObj.get("endDate").isJsonNull()) && !jsonObj.get("endDate").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `endDate` to be a primitive type in the JSON string but got `%s`", jsonObj.get("endDate").toString()));
      }
      // validate the optional field `metadata`
      if (jsonObj.get("metadata") != null && !jsonObj.get("metadata").isJsonNull()) {
        ApiElectionMetadata.validateJsonElement(jsonObj.get("metadata"));
      }
      if ((jsonObj.get("metadataURL") != null && !jsonObj.get("metadataURL").isJsonNull()) && !jsonObj.get("metadataURL").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `metadataURL` to be a primitive type in the JSON string but got `%s`", jsonObj.get("metadataURL").toString()));
      }
      if ((jsonObj.get("organizationId") != null && !jsonObj.get("organizationId").isJsonNull()) && !jsonObj.get("organizationId").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `organizationId` to be a primitive type in the JSON string but got `%s`", jsonObj.get("organizationId").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("result") != null && !jsonObj.get("result").isJsonNull() && !jsonObj.get("result").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `result` to be an array in the JSON string but got `%s`", jsonObj.get("result").toString()));
      }
      if ((jsonObj.get("startDate") != null && !jsonObj.get("startDate").isJsonNull()) && !jsonObj.get("startDate").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `startDate` to be a primitive type in the JSON string but got `%s`", jsonObj.get("startDate").toString()));
      }
      if ((jsonObj.get("status") != null && !jsonObj.get("status").isJsonNull()) && !jsonObj.get("status").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `status` to be a primitive type in the JSON string but got `%s`", jsonObj.get("status").toString()));
      }
      // validate the optional field `tallyMode`
      if (jsonObj.get("tallyMode") != null && !jsonObj.get("tallyMode").isJsonNull()) {
        ApiTallyMode.validateJsonElement(jsonObj.get("tallyMode"));
      }
      // validate the optional field `voteMode`
      if (jsonObj.get("voteMode") != null && !jsonObj.get("voteMode").isJsonNull()) {
        ApiVoteMode.validateJsonElement(jsonObj.get("voteMode"));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ApiElection.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ApiElection' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ApiElection> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ApiElection.class));

       return (TypeAdapter<T>) new TypeAdapter<ApiElection>() {
           @Override
           public void write(JsonWriter out, ApiElection value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public ApiElection read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of ApiElection given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of ApiElection
  * @throws IOException if the JSON string is invalid with respect to ApiElection
  */
  public static ApiElection fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ApiElection.class);
  }

 /**
  * Convert an instance of ApiElection to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}
