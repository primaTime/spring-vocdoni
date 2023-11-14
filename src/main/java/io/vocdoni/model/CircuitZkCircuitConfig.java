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
 * CircuitZkCircuitConfig
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-11-10T16:40:32.462750+01:00[Europe/Prague]")
public class CircuitZkCircuitConfig {
  public static final String SERIALIZED_NAME_CIRCUIT_PATH = "circuitPath";
  @SerializedName(SERIALIZED_NAME_CIRCUIT_PATH)
  private String circuitPath;

  public static final String SERIALIZED_NAME_LEVELS = "levels";
  @SerializedName(SERIALIZED_NAME_LEVELS)
  private Integer levels;

  public static final String SERIALIZED_NAME_URI = "uri";
  @SerializedName(SERIALIZED_NAME_URI)
  private String uri;

  public static final String SERIALIZED_NAME_V_KEY_FILENAME = "vKeyFilename";
  @SerializedName(SERIALIZED_NAME_V_KEY_FILENAME)
  private String vKeyFilename;

  public static final String SERIALIZED_NAME_V_KEY_HASH = "vKeyHash";
  @SerializedName(SERIALIZED_NAME_V_KEY_HASH)
  private String vKeyHash;

  public static final String SERIALIZED_NAME_WASM_FILENAME = "wasmFilename";
  @SerializedName(SERIALIZED_NAME_WASM_FILENAME)
  private String wasmFilename;

  public static final String SERIALIZED_NAME_WASM_HASH = "wasmHash";
  @SerializedName(SERIALIZED_NAME_WASM_HASH)
  private String wasmHash;

  public static final String SERIALIZED_NAME_Z_KEY_FILENAME = "zKeyFilename";
  @SerializedName(SERIALIZED_NAME_Z_KEY_FILENAME)
  private String zKeyFilename;

  public static final String SERIALIZED_NAME_Z_KEY_HASH = "zKeyHash";
  @SerializedName(SERIALIZED_NAME_Z_KEY_HASH)
  private String zKeyHash;

  public CircuitZkCircuitConfig() {
  }

  public CircuitZkCircuitConfig circuitPath(String circuitPath) {
    
    this.circuitPath = circuitPath;
    return this;
  }

   /**
   * CircuitPath defines the path from where the files are downloaded. Locally, they will be cached inside circuit.BaseDir path, under that directory it will follow the CircuitPath dir structure
   * @return circuitPath
  **/
  @javax.annotation.Nullable
  public String getCircuitPath() {
    return circuitPath;
  }


  public void setCircuitPath(String circuitPath) {
    this.circuitPath = circuitPath;
  }


  public CircuitZkCircuitConfig levels(Integer levels) {
    
    this.levels = levels;
    return this;
  }

   /**
   * Levels refers the number of levels that the merkle tree associated to the current circuit configuration artifacts has
   * @return levels
  **/
  @javax.annotation.Nullable
  public Integer getLevels() {
    return levels;
  }


  public void setLevels(Integer levels) {
    this.levels = levels;
  }


  public CircuitZkCircuitConfig uri(String uri) {
    
    this.uri = uri;
    return this;
  }

   /**
   * URI defines the URI from where to download the files
   * @return uri
  **/
  @javax.annotation.Nullable
  public String getUri() {
    return uri;
  }


  public void setUri(String uri) {
    this.uri = uri;
  }


  public CircuitZkCircuitConfig vKeyFilename(String vKeyFilename) {
    
    this.vKeyFilename = vKeyFilename;
    return this;
  }

   /**
   * FilenameVerificationKey defines the name of the file of the circom VerificationKey
   * @return vKeyFilename
  **/
  @javax.annotation.Nullable
  public String getvKeyFilename() {
    return vKeyFilename;
  }


  public void setvKeyFilename(String vKeyFilename) {
    this.vKeyFilename = vKeyFilename;
  }


  public CircuitZkCircuitConfig vKeyHash(String vKeyHash) {
    
    this.vKeyHash = vKeyHash;
    return this;
  }

   /**
   * VerificationKeyHash contains the expected hash for the file filenameVK
   * @return vKeyHash
  **/
  @javax.annotation.Nullable
  public String getvKeyHash() {
    return vKeyHash;
  }


  public void setvKeyHash(String vKeyHash) {
    this.vKeyHash = vKeyHash;
  }


  public CircuitZkCircuitConfig wasmFilename(String wasmFilename) {
    
    this.wasmFilename = wasmFilename;
    return this;
  }

   /**
   * FilenameWasm defines the name of the file of the circuit wasm compiled version
   * @return wasmFilename
  **/
  @javax.annotation.Nullable
  public String getWasmFilename() {
    return wasmFilename;
  }


  public void setWasmFilename(String wasmFilename) {
    this.wasmFilename = wasmFilename;
  }


  public CircuitZkCircuitConfig wasmHash(String wasmHash) {
    
    this.wasmHash = wasmHash;
    return this;
  }

   /**
   * WasmHash contains the expected hash for the file filenameWasm
   * @return wasmHash
  **/
  @javax.annotation.Nullable
  public String getWasmHash() {
    return wasmHash;
  }


  public void setWasmHash(String wasmHash) {
    this.wasmHash = wasmHash;
  }


  public CircuitZkCircuitConfig zKeyFilename(String zKeyFilename) {
    
    this.zKeyFilename = zKeyFilename;
    return this;
  }

   /**
   * FilenameProvingKey defines the name of the file of the circom ProvingKey
   * @return zKeyFilename
  **/
  @javax.annotation.Nullable
  public String getzKeyFilename() {
    return zKeyFilename;
  }


  public void setzKeyFilename(String zKeyFilename) {
    this.zKeyFilename = zKeyFilename;
  }


  public CircuitZkCircuitConfig zKeyHash(String zKeyHash) {
    
    this.zKeyHash = zKeyHash;
    return this;
  }

   /**
   * ProvingKeyHash contains the expected hash for the file filenameZKey
   * @return zKeyHash
  **/
  @javax.annotation.Nullable
  public String getzKeyHash() {
    return zKeyHash;
  }


  public void setzKeyHash(String zKeyHash) {
    this.zKeyHash = zKeyHash;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CircuitZkCircuitConfig circuitZkCircuitConfig = (CircuitZkCircuitConfig) o;
    return Objects.equals(this.circuitPath, circuitZkCircuitConfig.circuitPath) &&
        Objects.equals(this.levels, circuitZkCircuitConfig.levels) &&
        Objects.equals(this.uri, circuitZkCircuitConfig.uri) &&
        Objects.equals(this.vKeyFilename, circuitZkCircuitConfig.vKeyFilename) &&
        Objects.equals(this.vKeyHash, circuitZkCircuitConfig.vKeyHash) &&
        Objects.equals(this.wasmFilename, circuitZkCircuitConfig.wasmFilename) &&
        Objects.equals(this.wasmHash, circuitZkCircuitConfig.wasmHash) &&
        Objects.equals(this.zKeyFilename, circuitZkCircuitConfig.zKeyFilename) &&
        Objects.equals(this.zKeyHash, circuitZkCircuitConfig.zKeyHash);
  }

  @Override
  public int hashCode() {
    return Objects.hash(circuitPath, levels, uri, vKeyFilename, vKeyHash, wasmFilename, wasmHash, zKeyFilename, zKeyHash);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CircuitZkCircuitConfig {\n");
    sb.append("    circuitPath: ").append(toIndentedString(circuitPath)).append("\n");
    sb.append("    levels: ").append(toIndentedString(levels)).append("\n");
    sb.append("    uri: ").append(toIndentedString(uri)).append("\n");
    sb.append("    vKeyFilename: ").append(toIndentedString(vKeyFilename)).append("\n");
    sb.append("    vKeyHash: ").append(toIndentedString(vKeyHash)).append("\n");
    sb.append("    wasmFilename: ").append(toIndentedString(wasmFilename)).append("\n");
    sb.append("    wasmHash: ").append(toIndentedString(wasmHash)).append("\n");
    sb.append("    zKeyFilename: ").append(toIndentedString(zKeyFilename)).append("\n");
    sb.append("    zKeyHash: ").append(toIndentedString(zKeyHash)).append("\n");
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
    openapiFields.add("circuitPath");
    openapiFields.add("levels");
    openapiFields.add("uri");
    openapiFields.add("vKeyFilename");
    openapiFields.add("vKeyHash");
    openapiFields.add("wasmFilename");
    openapiFields.add("wasmHash");
    openapiFields.add("zKeyFilename");
    openapiFields.add("zKeyHash");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to CircuitZkCircuitConfig
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!CircuitZkCircuitConfig.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in CircuitZkCircuitConfig is not found in the empty JSON string", CircuitZkCircuitConfig.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!CircuitZkCircuitConfig.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `CircuitZkCircuitConfig` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("circuitPath") != null && !jsonObj.get("circuitPath").isJsonNull()) && !jsonObj.get("circuitPath").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `circuitPath` to be a primitive type in the JSON string but got `%s`", jsonObj.get("circuitPath").toString()));
      }
      if ((jsonObj.get("uri") != null && !jsonObj.get("uri").isJsonNull()) && !jsonObj.get("uri").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `uri` to be a primitive type in the JSON string but got `%s`", jsonObj.get("uri").toString()));
      }
      if ((jsonObj.get("vKeyFilename") != null && !jsonObj.get("vKeyFilename").isJsonNull()) && !jsonObj.get("vKeyFilename").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `vKeyFilename` to be a primitive type in the JSON string but got `%s`", jsonObj.get("vKeyFilename").toString()));
      }
      if ((jsonObj.get("vKeyHash") != null && !jsonObj.get("vKeyHash").isJsonNull()) && !jsonObj.get("vKeyHash").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `vKeyHash` to be a primitive type in the JSON string but got `%s`", jsonObj.get("vKeyHash").toString()));
      }
      if ((jsonObj.get("wasmFilename") != null && !jsonObj.get("wasmFilename").isJsonNull()) && !jsonObj.get("wasmFilename").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `wasmFilename` to be a primitive type in the JSON string but got `%s`", jsonObj.get("wasmFilename").toString()));
      }
      if ((jsonObj.get("wasmHash") != null && !jsonObj.get("wasmHash").isJsonNull()) && !jsonObj.get("wasmHash").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `wasmHash` to be a primitive type in the JSON string but got `%s`", jsonObj.get("wasmHash").toString()));
      }
      if ((jsonObj.get("zKeyFilename") != null && !jsonObj.get("zKeyFilename").isJsonNull()) && !jsonObj.get("zKeyFilename").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `zKeyFilename` to be a primitive type in the JSON string but got `%s`", jsonObj.get("zKeyFilename").toString()));
      }
      if ((jsonObj.get("zKeyHash") != null && !jsonObj.get("zKeyHash").isJsonNull()) && !jsonObj.get("zKeyHash").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `zKeyHash` to be a primitive type in the JSON string but got `%s`", jsonObj.get("zKeyHash").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!CircuitZkCircuitConfig.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'CircuitZkCircuitConfig' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<CircuitZkCircuitConfig> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(CircuitZkCircuitConfig.class));

       return (TypeAdapter<T>) new TypeAdapter<CircuitZkCircuitConfig>() {
           @Override
           public void write(JsonWriter out, CircuitZkCircuitConfig value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public CircuitZkCircuitConfig read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of CircuitZkCircuitConfig given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of CircuitZkCircuitConfig
  * @throws IOException if the JSON string is invalid with respect to CircuitZkCircuitConfig
  */
  public static CircuitZkCircuitConfig fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, CircuitZkCircuitConfig.class);
  }

 /**
  * Convert an instance of CircuitZkCircuitConfig to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

