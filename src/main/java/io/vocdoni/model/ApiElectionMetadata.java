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
import io.vocdoni.model.ApiElectionResultsDetails;
import io.vocdoni.model.ApiProcessMedia;
import io.vocdoni.model.ApiQuestion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openapitools.jackson.nullable.JsonNullable;

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
 * ApiElectionMetadata
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-02-09T17:09:26.351036+01:00[Europe/Prague]")
public class ApiElectionMetadata {
  public static final String SERIALIZED_NAME_DESCRIPTION = "description";
  @SerializedName(SERIALIZED_NAME_DESCRIPTION)
  private Map<String, String> description = new HashMap<>();

  public static final String SERIALIZED_NAME_MEDIA = "media";
  @SerializedName(SERIALIZED_NAME_MEDIA)
  private ApiProcessMedia media;

  public static final String SERIALIZED_NAME_META = "meta";
  @SerializedName(SERIALIZED_NAME_META)
  private Object meta = null;

  public static final String SERIALIZED_NAME_QUESTIONS = "questions";
  @SerializedName(SERIALIZED_NAME_QUESTIONS)
  private List<ApiQuestion> questions;

  public static final String SERIALIZED_NAME_RESULTS = "results";
  @SerializedName(SERIALIZED_NAME_RESULTS)
  private ApiElectionResultsDetails results;

  public static final String SERIALIZED_NAME_TITLE = "title";
  @SerializedName(SERIALIZED_NAME_TITLE)
  private Map<String, String> title = new HashMap<>();

  public static final String SERIALIZED_NAME_VERSION = "version";
  @SerializedName(SERIALIZED_NAME_VERSION)
  private String version;

  public ApiElectionMetadata() {
  }

  public ApiElectionMetadata description(Map<String, String> description) {
    
    this.description = description;
    return this;
  }

  public ApiElectionMetadata putDescriptionItem(String key, String descriptionItem) {
    if (this.description == null) {
      this.description = new HashMap<>();
    }
    this.description.put(key, descriptionItem);
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @javax.annotation.Nullable
  public Map<String, String> getDescription() {
    return description;
  }


  public void setDescription(Map<String, String> description) {
    this.description = description;
  }


  public ApiElectionMetadata media(ApiProcessMedia media) {
    
    this.media = media;
    return this;
  }

   /**
   * Get media
   * @return media
  **/
  @javax.annotation.Nullable
  public ApiProcessMedia getMedia() {
    return media;
  }


  public void setMedia(ApiProcessMedia media) {
    this.media = media;
  }


  public ApiElectionMetadata meta(Object meta) {
    
    this.meta = meta;
    return this;
  }

   /**
   * Get meta
   * @return meta
  **/
  @javax.annotation.Nullable
  public Object getMeta() {
    return meta;
  }


  public void setMeta(Object meta) {
    this.meta = meta;
  }


  public ApiElectionMetadata questions(List<ApiQuestion> questions) {
    
    this.questions = questions;
    return this;
  }

  public ApiElectionMetadata addQuestionsItem(ApiQuestion questionsItem) {
    if (this.questions == null) {
      this.questions = new ArrayList<>();
    }
    this.questions.add(questionsItem);
    return this;
  }

   /**
   * Get questions
   * @return questions
  **/
  @javax.annotation.Nullable
  public List<ApiQuestion> getQuestions() {
    return questions;
  }


  public void setQuestions(List<ApiQuestion> questions) {
    this.questions = questions;
  }


  public ApiElectionMetadata results(ApiElectionResultsDetails results) {
    
    this.results = results;
    return this;
  }

   /**
   * Get results
   * @return results
  **/
  @javax.annotation.Nullable
  public ApiElectionResultsDetails getResults() {
    return results;
  }


  public void setResults(ApiElectionResultsDetails results) {
    this.results = results;
  }


  public ApiElectionMetadata title(Map<String, String> title) {
    
    this.title = title;
    return this;
  }

  public ApiElectionMetadata putTitleItem(String key, String titleItem) {
    if (this.title == null) {
      this.title = new HashMap<>();
    }
    this.title.put(key, titleItem);
    return this;
  }

   /**
   * Get title
   * @return title
  **/
  @javax.annotation.Nullable
  public Map<String, String> getTitle() {
    return title;
  }


  public void setTitle(Map<String, String> title) {
    this.title = title;
  }


  public ApiElectionMetadata version(String version) {
    
    this.version = version;
    return this;
  }

   /**
   * Get version
   * @return version
  **/
  @javax.annotation.Nullable
  public String getVersion() {
    return version;
  }


  public void setVersion(String version) {
    this.version = version;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiElectionMetadata apiElectionMetadata = (ApiElectionMetadata) o;
    return Objects.equals(this.description, apiElectionMetadata.description) &&
        Objects.equals(this.media, apiElectionMetadata.media) &&
        Objects.equals(this.meta, apiElectionMetadata.meta) &&
        Objects.equals(this.questions, apiElectionMetadata.questions) &&
        Objects.equals(this.results, apiElectionMetadata.results) &&
        Objects.equals(this.title, apiElectionMetadata.title) &&
        Objects.equals(this.version, apiElectionMetadata.version);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(description, media, meta, questions, results, title, version);
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiElectionMetadata {\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    media: ").append(toIndentedString(media)).append("\n");
    sb.append("    meta: ").append(toIndentedString(meta)).append("\n");
    sb.append("    questions: ").append(toIndentedString(questions)).append("\n");
    sb.append("    results: ").append(toIndentedString(results)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
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
    openapiFields.add("description");
    openapiFields.add("media");
    openapiFields.add("meta");
    openapiFields.add("questions");
    openapiFields.add("results");
    openapiFields.add("title");
    openapiFields.add("version");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to ApiElectionMetadata
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!ApiElectionMetadata.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in ApiElectionMetadata is not found in the empty JSON string", ApiElectionMetadata.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!ApiElectionMetadata.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `ApiElectionMetadata` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      // validate the optional field `media`
      if (jsonObj.get("media") != null && !jsonObj.get("media").isJsonNull()) {
        ApiProcessMedia.validateJsonElement(jsonObj.get("media"));
      }
      if (jsonObj.get("questions") != null && !jsonObj.get("questions").isJsonNull()) {
        JsonArray jsonArrayquestions = jsonObj.getAsJsonArray("questions");
        if (jsonArrayquestions != null) {
          // ensure the json data is an array
          if (!jsonObj.get("questions").isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `questions` to be an array in the JSON string but got `%s`", jsonObj.get("questions").toString()));
          }

          // validate the optional field `questions` (array)
          for (int i = 0; i < jsonArrayquestions.size(); i++) {
            ApiQuestion.validateJsonElement(jsonArrayquestions.get(i));
          };
        }
      }
      // validate the optional field `results`
      if (jsonObj.get("results") != null && !jsonObj.get("results").isJsonNull()) {
        ApiElectionResultsDetails.validateJsonElement(jsonObj.get("results"));
      }
      if ((jsonObj.get("version") != null && !jsonObj.get("version").isJsonNull()) && !jsonObj.get("version").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `version` to be a primitive type in the JSON string but got `%s`", jsonObj.get("version").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ApiElectionMetadata.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ApiElectionMetadata' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ApiElectionMetadata> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ApiElectionMetadata.class));

       return (TypeAdapter<T>) new TypeAdapter<ApiElectionMetadata>() {
           @Override
           public void write(JsonWriter out, ApiElectionMetadata value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public ApiElectionMetadata read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of ApiElectionMetadata given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of ApiElectionMetadata
  * @throws IOException if the JSON string is invalid with respect to ApiElectionMetadata
  */
  public static ApiElectionMetadata fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ApiElectionMetadata.class);
  }

 /**
  * Convert an instance of ApiElectionMetadata to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

